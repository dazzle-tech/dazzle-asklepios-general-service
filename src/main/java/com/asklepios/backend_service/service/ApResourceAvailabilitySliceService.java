package com.asklepios.backend_service.service;

import com.asklepios.backend_service.model.DTO.AvailabilityEntryDTO;
import com.asklepios.backend_service.model.DTO.ResourceAvailabilityRequestDTO;
import com.asklepios.backend_service.model.DTO.TimeSliceDTO;
import com.asklepios.backend_service.model.generated.dao.ApResourceAvailabilitySliceDAO;
import com.asklepios.backend_service.model.generated.pojo.ApResourceAvailabilitySlice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApResourceAvailabilitySliceService extends ApResourceAvailabilitySliceDAO implements Serializable {

    public ApResourceAvailabilitySliceService() {
        // Constructor remains as is
    }

    private String buildWhereClause(String resourceKey, String facilityKey, String dayOfWeekLkey, boolean onlyActive) {
        StringBuilder where = new StringBuilder();
        where.append("resource_key = '").append(resourceKey).append("'");

        // Handle facilityKey being null or a specific value
        if (facilityKey == null || "null".equalsIgnoreCase(facilityKey)) {
            // If facilityKey is logically null (from DTO), search for IS NULL in DB
            where.append(" AND facility_key IS NULL");
        } else {
            // Otherwise, search for the specific facilityKey
            where.append(" AND facility_key = '").append(facilityKey).append("'");
        }

        // Handle dayOfWeekLkey if provided
        if (dayOfWeekLkey != null) {
            where.append(" AND day_of_week = '").append(dayOfWeekLkey).append("'");
        }

        // Always include the active status and soft-delete filter
        if (onlyActive) {
            where.append(" AND is_valid = true");
            where.append(" AND deleted_at IS NULL"); // Critical for soft-delete
        }

        return where.toString();
    }


    public List<ApResourceAvailabilitySlice> findByResourceAndFacilityAndDay(
            String resourceKey, String facilityKey, String dayOfWeekLkey) throws SQLException {

        String where = buildWhereClause(resourceKey, facilityKey, dayOfWeekLkey, true);
        log.debug("Executing findByResourceAndFacilityAndDay with where clause: {}", where);
        return getList(where);
    }


    public List<ApResourceAvailabilitySlice> findByResourceAndFacility(
            String resourceKey, String facilityKey) throws SQLException {

        String where = buildWhereClause(resourceKey, facilityKey, null, true); // dayOfWeekLkey is null for all days
        log.debug("Executing findByResourceAndFacility with where clause: {}", where);
        return getList(where);
    }


    public void deleteAll(List<ApResourceAvailabilitySlice> slicesToDelete) throws SQLException {
        if (slicesToDelete == null || slicesToDelete.isEmpty()) {
            log.info("deleteAll: No slices provided for soft deletion.");
            return;
        }

        log.info("deleteAll: Attempting to soft-delete {} slices.", slicesToDelete.size());
        for (ApResourceAvailabilitySlice slice : slicesToDelete) {
            String sliceKey = slice.getKey();
            if (sliceKey == null || sliceKey.isEmpty()) {
                log.warn("deleteAll: Skipping slice with null or empty key: {}", slice);
                continue;
            }
            try {
                super.deleteRecord(slice);
                log.debug("deleteAll: Successfully performed soft delete for slice key: {}", sliceKey);
            } catch (SQLException e) {
                log.error("deleteAll: SQLException during soft deletion of slice {}: {}", sliceKey, e.getMessage(), e);
                throw e; // Re-throw to ensure transaction rollback if an error occurs
            }
        }
        log.info("deleteAll: Finished processing soft deletion for {} slices.", slicesToDelete.size());
    }

    @Transactional
    public void saveFullAvailability(ResourceAvailabilityRequestDTO requestDTO) throws SQLException {
        String facilityKey = requestDTO.getFacility();
        String resourceKey = requestDTO.getResource();

        String departmentKey = "default_department";

        log.info("saveFullAvailability: Received ResourceAvailabilityRequestDTO for Resource: {}, Facility: {}. DTO: {}", resourceKey, facilityKey, requestDTO);

        // Fetch ALL currently active slices for this resource and facility across all days
        List<ApResourceAvailabilitySlice> existingActiveSlices = findByResourceAndFacility(resourceKey, facilityKey);
        log.debug("saveFullAvailability: Found {} existing active slices for Resource: {}, Facility: {}", existingActiveSlices.size(), resourceKey, facilityKey);

        Map<String, ApResourceAvailabilitySlice> existingSlicesMap = existingActiveSlices.stream()
                .collect(Collectors.toMap(
                        slice -> slice.getDayOfWeek() + "_" + slice.getStartTimeMinutes() + "_" + slice.getEndTimeMinutes() + "_" + slice.getIsbreak(),
                        slice -> slice
                ));

        Set<String> newSlicesIdentifiers = new HashSet<>();

        List<String> retainedSliceKeys = new ArrayList<>();

        // Process new availability data from the requestDTO
        if (requestDTO.getAvailability() != null && !requestDTO.getAvailability().isEmpty()) {
            for (AvailabilityEntryDTO newDayEntry : requestDTO.getAvailability()) {
                String dayOfWeek = newDayEntry.getDay();
                List<TimeSliceDTO> newSlicesForDay = newDayEntry.getSlices();

                if (newSlicesForDay != null && !newSlicesForDay.isEmpty()) {
                    newSlicesForDay.sort((s1, s2) -> Long.compare(s1.getStartTimeMinutes(), s2.getStartTimeMinutes()));

                    List<TimeSliceDTO> currentDayProcessedSlices = new ArrayList<>();

                    for (TimeSliceDTO newSliceDTO : newSlicesForDay) {
                        long startTimeMinutes = newSliceDTO.getStartTimeMinutes();
                        long endTimeMinutes = newSliceDTO.getEndTimeMinutes();
                        boolean isBreak = newSliceDTO.isBreak();
                        long sliceDurationMinutes = endTimeMinutes - startTimeMinutes;

                        // 1. Basic validation: Start time must be before end time, duration > 0
                        if (startTimeMinutes >= endTimeMinutes || sliceDurationMinutes <= 0) {
                            log.error("saveFullAvailability: Invalid time slice: Start time ({}) must be before end time ({}) or duration invalid for day {} and resource {}",
                                    startTimeMinutes, endTimeMinutes, dayOfWeek, resourceKey);
                            throw new IllegalArgumentException(
                                    String.format("Invalid time slice: Start time must be before end time or duration invalid for %d-%d on day %s.",
                                            startTimeMinutes, endTimeMinutes, dayOfWeek)
                            );
                        }

                        // 2. Overlap Check: Check for overlaps against slices already processed in this *request* for *this day*
                        boolean overlaps = currentDayProcessedSlices.stream().anyMatch(existing -> {
                            long existingStart = existing.getStartTimeMinutes();
                            long existingEnd = existing.getEndTimeMinutes();

                            // Overlap conditions
                            return (startTimeMinutes >= existingStart && startTimeMinutes < existingEnd) ||
                                    (endTimeMinutes > existingStart && endTimeMinutes <= existingEnd) ||
                                    (startTimeMinutes <= existingStart && endTimeMinutes >= existingEnd);
                        });

                        if (overlaps) {
                            log.error("saveFullAvailability: Time slice overlaps with an already submitted slice in the same request on day {} for resource {}: {}-{}. Request will be rejected.",
                                    dayOfWeek, resourceKey, startTimeMinutes, endTimeMinutes);
                            throw new IllegalArgumentException(
                                    String.format("Time slice %d-%d overlaps with another slice provided in this request for day %s for resource %s. Please ensure no overlaps in your submission.",
                                            startTimeMinutes, endTimeMinutes, dayOfWeek, resourceKey)
                            );
                        }
                        currentDayProcessedSlices.add(newSliceDTO);

                        String currentNewSliceIdentifier = dayOfWeek + "_" + startTimeMinutes + "_" + endTimeMinutes + "_" + isBreak;
                        newSlicesIdentifiers.add(currentNewSliceIdentifier);

                        ApResourceAvailabilitySlice existingSlice = existingSlicesMap.get(currentNewSliceIdentifier);

                        if (existingSlice != null) {
                            // The slice already exists and is active. No action needed
                            // Remove it from the map of existing slices to identify what needs to be deleted later.
                            existingSlicesMap.remove(currentNewSliceIdentifier);
                            log.debug("saveFullAvailability: Existing active slice matched for resource: {} on day: {} from {} to {}. No update/insert needed.",
                                    resourceKey, dayOfWeek, startTimeMinutes, endTimeMinutes);
                        } else {
                            // This is a truly new slice, or a slice whose 'isBreak' status changed.
                            // If it a new  slice,will insert it.
                            // If it an existing slice with a changed 'isBreak' or other property that makes its identifier different,
                            // the old one will be soft-deleted later, and this new version will be inserted.

                            ApResourceAvailabilitySlice record = new ApResourceAvailabilitySlice();
                            record.setResourceKey(resourceKey);
                            record.setFacilityKey(facilityKey);
                            record.setDepartmentKey(departmentKey);
                            record.setDayOfWeek(dayOfWeek);
                            record.setStartTimeMinutes(String.valueOf(startTimeMinutes));
                            record.setEndTimeMinutes(String.valueOf(endTimeMinutes));
                            record.setSliceDurationMinutes(String.valueOf(sliceDurationMinutes));
                            record.setIsbocked("N"); // 'N' for not booked, as these are new availability slots
                            record.setIsbreak(isBreak);
                            record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
                            //TODO: use the actual user insted on the system
                            record.setCreatedBy("system"); // Or actual user
                            record.setIsValid(true);
                            record.setDeletedAt(null);
                            super.saveRecord(record);
                            log.info("saveFullAvailability: Inserted new slice for resource: {} on day: {} from {} to {}",
                                    resourceKey, dayOfWeek, startTimeMinutes, endTimeMinutes);
                        }
                    }
                }
            }
        } else {
            log.warn("saveFullAvailability: No new availability data provided. Will soft-delete all existing active slices for Resource: {}, Facility: {}.", resourceKey, facilityKey);
        }

        List<ApResourceAvailabilitySlice> slicesToSoftDelete = new ArrayList<>();
        for (ApResourceAvailabilitySlice slice : existingSlicesMap.values()) {
            // TODO: [Future Feature] Check if this slice is booked before soft deleting.
            boolean isBooked = false;
            if (isBooked) {
                retainedSliceKeys.add(slice.getKey());
                log.warn("saveFullAvailability: Slice {} for resource {} on day {} from {} to {} is booked and will not be soft-deleted.",
                        slice.getKey(), resourceKey, slice.getDayOfWeek(), slice.getStartTimeMinutes(), slice.getEndTimeMinutes());
            } else {
                slicesToSoftDelete.add(slice);
            }
        }

        if (!slicesToSoftDelete.isEmpty()) {
            deleteAll(slicesToSoftDelete);
            log.info("saveFullAvailability: Successfully soft-deleted {} old slices for resource: {} in facility: {}.",
                    slicesToSoftDelete.size(), resourceKey, facilityKey);
        } else {
            log.info("saveFullAvailability: No old slices found that need soft-deletion for resource: {} in facility: {}.",
                    resourceKey, facilityKey);
        }

        if (!retainedSliceKeys.isEmpty()) {
            log.warn("saveFullAvailability: The following slices for resource {} in facility {} were NOT soft-deleted as they are linked to open appointments (future feature placeholder): {}",
                    resourceKey, facilityKey, retainedSliceKeys);
        }
        log.info("saveFullAvailability: Completed processing for Resource: {}, Facility: {}", resourceKey, facilityKey);
    }
}