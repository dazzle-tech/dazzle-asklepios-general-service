package com.asklepios.backend_service.model.DTO;

import lombok.Data;
import java.util.List;

@Data
public class ResourceAvailabilityDTO {
    private String key;
    private String resourceKey;
    private String resourceName;
    private String resourceTypeLkey;
    private String facilityKey;

    // Aggregated availability slots (general view)
    private List<Availability> availability;

    // Detailed time slices (optional)
    private List<RowAvailabilitySlice> availabilitySlices;

    // Events like bookings or blocks or maintnance ( optional)
    private List<EventSlice> eventSlices;


    @Data
    public static class Availability {
        private int dayOfWeek;
        private int startHour;
        private int startMinute;
        private int endHour;
        private int endMinute;
    }


    @Data
    public static class RowAvailabilitySlice {
        private String key;
        private String  dayOfWeek;
        private int startHour;
        private int startMinute;
        private int endHour;
        private int endMinute;
        private boolean isBreak;
    }

    @Data
    public static class EventSlice {
        private String from;       // ISO
        private String to;         // ISO datetime
        private String eventType;  // booking/ leave/ maintenance ..etc
        private String description;
    }
}
