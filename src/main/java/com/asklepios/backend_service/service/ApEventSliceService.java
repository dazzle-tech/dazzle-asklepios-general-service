package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.asklepios.backend_service.model.generated.pojo.ApEventSlice;
import lombok.extern.slf4j.Slf4j;
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
            Optional<String> dayOfWeek
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

            saveRecord(slice);
        }

        log.info("Successfully booked {} slices for appointment {}", slicesToBook.size(), appointmentId);
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
