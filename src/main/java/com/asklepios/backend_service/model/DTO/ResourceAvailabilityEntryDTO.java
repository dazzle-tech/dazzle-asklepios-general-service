package com.asklepios.backend_service.model.DTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;

import java.util.Date;
import java.util.List;


@Data
public class ResourceAvailabilityEntryDTO {
    private List<AvailabilitySliceDTO> availability;
    private String facility;
    private String resource;

    @Data
    public static class AvailabilitySliceDTO {
        private String day;
        private List<SliceDTO> slices;

        @Data
        public static class SliceDTO {
            private Date from;
            private Date to;
            private Boolean isBreak;
        }
    }
}