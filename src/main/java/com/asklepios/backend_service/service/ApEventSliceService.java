package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.asklepios.backend_service.model.generated.pojo.ApAppointment;
import com.asklepios.backend_service.model.generated.pojo.ApEventSlice;
import com.asklepios.backend_service.model.generated.pojo.ApResourceAvailabilitySlice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApEventSliceDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ApEventSliceService extends ApEventSliceDAO implements Serializable {
    @Transactional
    public void bookSlicesForAppointment(
            String appointmentId,
            List<ApEventSlice> slicesToBook,
            String createdBy,
            String eventType,
            Optional<String> dayOfWeek,
            Date appointmentDate
    ) throws SQLException {

        if (slicesToBook == null || slicesToBook.isEmpty()) {
            log.info("No slices to book.");
            return;
        }

        BigDecimal now = new BigDecimal(System.currentTimeMillis());

        for (ApEventSlice slice : slicesToBook) {
            slice.setLinkedEventKey(appointmentId);
            slice.setCreatedBy(createdBy);
            slice.setEventType(eventType);
            dayOfWeek.ifPresent(slice::setDayOfWeek);
            slice.setCreatedAt(now);
            slice.setIsValid(true);
            slice.setEventDate(appointmentDate);

            saveRecord(slice);
        }

        log.info("Successfully booked {} slices for appointment {}", slicesToBook.size(), appointmentId);
    }

    public void updateAppointmentTimes(ApAppointment appointment,
                                       List<ApResourceAvailabilitySlice> availabilitySlices) {
        if (availabilitySlices == null || availabilitySlices.isEmpty()) {
            log.warn("updateAppointmentTimes: no availability slices provided");
            return;
        }

        if (appointment.getAppointmentDate() == null) {
            throw new IllegalArgumentException("appointmentDate is required to compute start/end");
        }

         ZoneOffset zoneOffset = ZoneOffset.of("+03:00");

         LocalDate appointmentDate = appointment.getAppointmentDate()
                .toInstant()
                .atOffset(zoneOffset)
                .toLocalDate();

         java.util.function.Function<String, Integer> parseMinutesSafe = s -> {
            if (s == null) return 0;
            try {
                return Integer.parseInt(s.trim());
            } catch (Exception ex) {
                log.warn("Unable to parse minutes '{}', defaulting to 0", s);
                return 0;
            }
        };

         int minStartMinutes = availabilitySlices.stream()
                .map(ApResourceAvailabilitySlice::getStartTimeMinutes)
                .map(parseMinutesSafe)
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new IllegalStateException("No valid start_time_minutes in availabilitySlices"));

        int maxEndMinutes = availabilitySlices.stream()
                .map(ApResourceAvailabilitySlice::getEndTimeMinutes)
                .map(parseMinutesSafe)
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("No valid end_time_minutes in availabilitySlices"));

        // Optional: debug print each slice minutes to be certain
        availabilitySlices.forEach(s -> log.debug("slice key={} start={} end={}",
                s.getKey(), s.getStartTimeMinutes(), s.getEndTimeMinutes()));

        log.info("Computed minutes -> minStartMinutes={}, maxEndMinutes={}, appointmentDate={}",
                minStartMinutes, maxEndMinutes, appointmentDate);

        // LocalTime from minutes (from midnight)
        LocalTime startTime = LocalTime.MIDNIGHT.plusMinutes(minStartMinutes);
        LocalTime endTime   = LocalTime.MIDNIGHT.plusMinutes(maxEndMinutes);

        // Build OffsetDateTime using appointmentDate and zoneOffset
        OffsetDateTime appointmentStart = appointmentDate.atTime(startTime).atOffset(zoneOffset);
        OffsetDateTime appointmentEnd   = appointmentDate.atTime(endTime).atOffset(zoneOffset);

        // Set back to appointment (ISO with offset)
        appointment.setAppointmentStart(appointmentStart.toString());
        appointment.setAppointmentEnd(appointmentEnd.toString());

        log.info("updateAppointmentTimes -> start={}, end={}", appointmentStart, appointmentEnd);
    }


//    @Transactional
//    public void cancelSlicesByAppointment(String appointmentId, String deletedBy) throws SQLException {
//        List<ApEventSlice> booked = getList(
//                "linked_event_key = '" + appointmentId + "' AND deleted_at IS NULL"
//        );
//
//        for (ApEventSlice slice : booked) {
//            slice.setDeletedBy(deletedBy);
//            deleteRecord(slice); // soft delete
//        }
//
//        log.info("Cancelled {} slices for appointment {}", booked.size(), appointmentId);
//    }
}
