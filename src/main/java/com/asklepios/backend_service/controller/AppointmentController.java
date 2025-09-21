package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.DTO.ResourceAvailabilityDTO;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
//@CrossOrigin
@Slf4j
public class AppointmentController {
    private final ApResourcesService apResourcesService;
    private final ApPractitionerService apPractitionerService;
    private final ApDepartmentService apDepartmentService;
    private final ApAppointmentService apAppointmentService;
    private final ApResourcesAvailabilityTimeService apResourcesAvailabilityTimeService;
    private final ApPatientService apPatientService;
    private final ApDiagnosticTestService apDiagnosticTestService;
    private final ApProcedureSetupService apProcedureSetupService;
    private final ApResourceAvailabilitySliceService apResourceAvailabilitySliceService;
    private final ApEventSliceService apEventSliceService;

    public AppointmentController(ApResourceAvailabilitySliceService apResourceAvailabilitySliceService, ApResourcesService apResourcesService, ApPractitionerService apPractitionerService, ApDepartmentService apDepartmentService, ApAppointmentService apAppointmentService, ApResourcesAvailabilityTimeService apResourcesAvailabilityTimeService, ApDiagnosticTestService apDiagnosticTestService, ApProcedureService apProcedureService, ApProcedureSetupService apProcedureSetupService) {
        this.apResourcesService = apResourcesService;
        this.apPractitionerService = apPractitionerService;
        this.apDepartmentService = apDepartmentService;
        this.apAppointmentService = apAppointmentService;
        this.apResourcesAvailabilityTimeService = apResourcesAvailabilityTimeService;
        this.apProcedureSetupService = apProcedureSetupService;
        this.apPatientService = new ApPatientService();
        this.apDiagnosticTestService = apDiagnosticTestService;
        this.apResourceAvailabilitySliceService = apResourceAvailabilitySliceService;
        this.apEventSliceService = new ApEventSliceService();
    }

    @GetMapping(value = "/resources-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resourcesList(@RequestParam Map<String, String> queryParams,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApResources>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApResources> list = apResourcesService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_resources where " + whereForTotal);
            for (ApResources all : list) {
                all.setResourceName(apResourcesService.getResourceName(all.getResourceTypeLkey(), all.getResourceKey()));
                apResourcesService.populateLovFields(all, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/resource-by-key", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getResourceByKey(
            @RequestParam ("resource-key") String resourceKey,
            @jakarta.annotation.Nullable @RequestHeader String facility_id,
           // @jakarta.annotation.Nullable @RequestHeader String access_token,
            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApResources> response = new ParentResponse<>();

            String where = "key = '" + resourceKey + "'";
            List<ApResources> list = apResourcesService.getList(where);
            if (list.isEmpty()) {
                return ResponseEntity.status(404).body("Resource not found");
            }

            ApResources resource = list.get(0);
            resource.setResourceName(apResourcesService.getResourceName(resource.getResourceTypeLkey(), resource.getResourceKey()));
            apResourcesService.populateLovFields(resource, lang);

            response.setObject(resource);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-resources", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveResources(@RequestBody ApResources resources,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApResources> response = new ParentResponse<>();
            apResourcesService.saveRecord(resources);
            response.setObject(resources);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAppointment(@RequestBody ApAppointment appointment,
                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Received appointment payload: " + mapper.writeValueAsString(appointment));

            Date appointmentDateRaw = appointment.getAppointmentDate();
            System.out.println("Original appointmentDate: " + appointmentDateRaw);
            System.out.println("Original appointmentStart: " + appointment.getAppointmentStart());
            System.out.println("Original appointmentEnd: " + appointment.getAppointmentEnd());

            if (appointment.getKey() == null) {
                String condition = "patient_key = '" + appointment.getPatientKey() + "'" +
                        " and DATE('" + appointmentDateRaw + "'::timestamp) = DATE(appointment_start)" +
                        " and (resource_type_lkey = '" + appointment.getResourceTypeLkey() + "'" +
                        " and resource_key = '" + appointment.getResourceKey() + "')";

                List<ApAppointment> existingAppointment = apAppointmentService.getList(condition);
                System.out.println("Existing appointments count: " + existingAppointment.size());

                if (!existingAppointment.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                            .body(Map.of("message", "The patient already has an appointment on this day."));
                }
            }

            // إذا كانت هناك سلايسز مختارة، استخدمها لتحديد وقت البداية والنهاية قبل الحفظ
            if (appointment.getSelectedSlices() != null && !appointment.getSelectedSlices().isEmpty()) {
                List<ApEventSlice> slicesToBook = appointment.getSelectedSlices().stream()
                        .map(sliceKey -> {
                            ApEventSlice slice = new ApEventSlice();
                            slice.setSliceKey(sliceKey);
                            return slice;
                        })
                        .collect(Collectors.toList());

                List<ApResourceAvailabilitySlice> availabilitySlices = slicesToBook.stream()
                        .map(slice -> {
                            try {
                                return apResourceAvailabilitySliceService.getRecord(slice.getSliceKey());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                System.out.println("Availability slices count: " + availabilitySlices.size());

                // حدّد وقت الأبويمنت بناءً على أول وآخر سلايس
                apEventSliceService.updateAppointmentTimes(appointment, availabilitySlices);
            } else if (appointmentDateRaw != null && appointment.getAppointmentStart() != null) {
                // إذا لم يكن هناك سلايسز، دمج التاريخ مع الوقت من payload
                LocalDate date = appointmentDateRaw.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
                LocalTime startTime = OffsetDateTime.parse(appointment.getAppointmentStart()).toLocalTime();
                LocalTime endTime = OffsetDateTime.parse(appointment.getAppointmentEnd()).toLocalTime();

                OffsetDateTime localStart = date.atTime(startTime).atOffset(ZoneOffset.of("+03:00"));
                OffsetDateTime localEnd = date.atTime(endTime).atOffset(ZoneOffset.of("+03:00"));

                appointment.setAppointmentStart(localStart.toString());
                appointment.setAppointmentEnd(localEnd.toString());
            }

            // حفظ الأبويمنت بعد تحديث الأوقات
            apAppointmentService.saveRecord(appointment);
            System.out.println("Appointment saved with key: " + appointment.getKey());

            // بعد الحفظ، قم بحجز السلايسز
            if (appointment.getSelectedSlices() != null && !appointment.getSelectedSlices().isEmpty()) {
                List<ApEventSlice> slicesToBook = appointment.getSelectedSlices().stream()
                        .map(sliceKey -> {
                            ApEventSlice slice = new ApEventSlice();
                            slice.setSliceKey(sliceKey);
                            return slice;
                        })
                        .collect(Collectors.toList());

                apEventSliceService.bookSlicesForAppointment(
                        appointment.getKey(),
                        slicesToBook,
                        appointment.getCreatedBy(),
                        "Appointment",
                        Optional.empty(),
                        appointment.getAppointmentDate()
                );

                System.out.println("Booked slices for appointment key: " + appointment.getKey());
            }

            ParentResponse<ApAppointment> response = new ParentResponse<>();
            response.setObject(appointment);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }




    @PostMapping(value = "/change-appointment-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAppointmentStatus(@RequestBody ApAppointment appointment,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                    // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAppointment> response = new ParentResponse<>();
            apAppointmentService.updateRecord(appointment);
            response.setObject(appointment);
            return ResponseEntity.ok(response);


        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/resource-type-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resourceTypeList(@RequestHeader ("resource-type") String resource_type,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                             // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApResources>> response = new ParentResponse<>();
            // TODO update status to be a LOV value
            if (resource_type.equals("2039534205961578")) //Practitioner
            {
                ParentResponse<List<ApPractitioner>> responsePra = new ParentResponse<>();
                List<ApPractitioner> listPra = apPractitionerService.getList("appointable = true");
                responsePra.setObject(listPra);
                return ResponseEntity.ok(responsePra);

            }
            // TODO update status to be a LOV value
            else if (resource_type.equals("2039516279378421")) //Department
            {
                ParentResponse<List<ApDepartment>> responseDep = new ParentResponse<>();
                List<ApDepartment> listDep = apDepartmentService.getList("appointable = true");
                responseDep.setObject(listDep);
                return ResponseEntity.ok(responseDep);

            }
            // TODO update status to be a LOV value
            else if (resource_type.equals("4217389643435490")) //Department InPatient Ward
            {
                ParentResponse<List<ApDepartment>> responseDep = new ParentResponse<>();
                List<ApDepartment> listDep = apDepartmentService.getList("appointable = true AND department_type_lkey = '5673990729647001'");
                responseDep.setObject(listDep);
                return ResponseEntity.ok(responseDep);

            }

            // TODO update status to be a LOV value
            else if (resource_type.equals("2039620472612029")) //Medical Test
            {
                ParentResponse<List<ApDiagnosticTest>> responseDia = new ParentResponse<>();
                List<ApDiagnosticTest> listDep = apDiagnosticTestService.getList("appointable = true");
                responseDia.setObject(listDep);
                return ResponseEntity.ok(responseDia);

            }
            // TODO update status to be a LOV value
            else if (resource_type.equals("2039548173192779")) //Procedure
            {
                ParentResponse<List<ApProcedureSetup>> responseDia = new ParentResponse<>();
                List<ApProcedureSetup> listDep = apProcedureSetupService.getList("is_appointable = true");
                responseDia.setObject(listDep);
                return ResponseEntity.ok(responseDia);

            }
            // TODO update status to be a LOV value
            else if (resource_type.equals("5433343011954425")) //Department Day Case
            {
                ParentResponse<List<ApDepartment>> responseDep = new ParentResponse<>();
                List<ApDepartment> listDep = apDepartmentService.getList("appointable = true AND department_type_lkey = '5673990729647005'");
                responseDep.setObject(listDep);
                return ResponseEntity.ok(responseDep);

            }
            // TODO update status to be a LOV value
            else if (resource_type.equals("6743167799449277")) //Department Emergency
            {
                ParentResponse<List<ApDepartment>> responseDep = new ParentResponse<>();
                List<ApDepartment> listDep = apDepartmentService.getList("appointable = true AND department_type_lkey = '5673990729647004'");
                responseDep.setObject(listDep);
                return ResponseEntity.ok(responseDep);

            }
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/appointments-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAppointments(
            @RequestParam ("resource-type") String resource_type,
            @RequestParam ("facility-id") String facility_id,
            @RequestParam List<String> resources,
//            @RequestHeader(required = false) String access_token,
            @RequestHeader(required = false) String lang) {
        try {
            log.info("Fetching appointments for resource_type: {}, facility_id: {}, resources: {}",
                    resource_type, facility_id, resources);

            // Build the WHERE Based on Provided Params
            String baseCondition = "1=1";
            String resourceTypeCondition = (resource_type != null && !resource_type.equals("null") && !resource_type.isEmpty())
                    ? "resource_type_lkey = '" + resource_type + "'" : "1=1";
            String facilityCondition = (facility_id != null && !facility_id.equals("null") && !facility_id.isEmpty())
                    ? "facility_key = '" + facility_id + "'" : "1=1";
            String resourceKeyCondition = (!resources.isEmpty()
                    && resources.stream().anyMatch(r -> !r.equals("null") && !r.equals("undefined")))
                    ? "resource_key IN (" + resources.stream()
                    .filter(r -> !r.equals("null") && !r.equals("undefined"))
                    .map(r -> "'" + r + "'")
                    .collect(Collectors.joining(", ")) + ")" : "1=1";
            String where = String.join(" AND ", baseCondition, resourceTypeCondition, facilityCondition, resourceKeyCondition);

            log.info("WHERE clause: {}", where);

            List<ApAppointment> appointments = apAppointmentService.getList(where);

            for (ApAppointment appointment : appointments) {
                if (appointment.getPatientKey() != null) {
                    ApPatient patient = apPatientService.getRecord(appointment.getPatientKey());
                    appointment.setPatient(patient);
                }


            }

            ParentResponse<List<ApAppointment>> response = new ParentResponse<>();

            for (ApAppointment a : appointments) {

                apAppointmentService.populateLovFields(a, lang);
            }


            response.setObject(appointments);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error fetching appointments", e);
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/resources-availability-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resourcesAvailabilityList(
            @RequestParam String resource_key,
            @jakarta.annotation.Nullable @RequestHeader String facility_id,
           // @jakarta.annotation.Nullable @RequestHeader String access_token,
            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApResourcesAvailabilityTime>> response = new ParentResponse<>();


            String where = "resource_key = '" + resource_key + "'";
            System.out.println("=========================>" + where);

            List<ApResourcesAvailabilityTime> availabilityTimeList = apResourcesAvailabilityTimeService.getList(where);

            for (ApResourcesAvailabilityTime apResourcesAvailabilityTime : availabilityTimeList) {
                apResourcesAvailabilityTimeService.populateLovFields(apResourcesAvailabilityTime, lang);
            }

            response.setObject(availabilityTimeList);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-resource", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeResource(@RequestBody ApResources resource,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApResources> response = new ParentResponse<>();
            apResourcesService.deleteRecord(resource);
            response.setObject(resource);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/resources-availability-time-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resourcesAvailabilityTimeList(@RequestParam Map<String, String> queryParams,
                                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                          // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApResourcesAvailabilityTime>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApResourcesAvailabilityTime> list = apResourcesAvailabilityTimeService.getList(where);

            for (ApResourcesAvailabilityTime apResourcesAvailabilityTime : list) {
                apResourcesAvailabilityTimeService.populateLovFields(apResourcesAvailabilityTime, lang);
            }

            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_resources_availability_time where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-resources-availability-time", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveResourcesAvailabilityTime(@RequestBody ApResourcesAvailabilityTime resourcesAvailabilityTime,
                                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                          // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApResourcesAvailabilityTime> response = new ParentResponse<>();
            apResourcesAvailabilityTimeService.saveRecord(resourcesAvailabilityTime);
            response.setObject(resourcesAvailabilityTime);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/resources-with-availability", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resourcesWithAvailability(
            @RequestParam Map<String, String> queryParams,
            @jakarta.annotation.Nullable @RequestHeader String facility_id,
           // @jakarta.annotation.Nullable @RequestHeader String access_token,
            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            log.info("Received request for /resources-with-availability");
            log.debug("Query params: {}", queryParams);
            ParentResponse<List<ResourceAvailabilityDTO>> response = new ParentResponse<>();

            if ("true".equalsIgnoreCase(queryParams.get("ignore"))) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            String resourceId = queryParams.get("id");
            log.info("Requested resource ID: {}", resourceId);

            List<ResourceAvailabilityDTO> dtoList = new ArrayList<>();

            if (resourceId != null && !resourceId.isEmpty()) {
                log.info("Fetching single resource with id: {}", resourceId);
                ApResources resource = apResourcesService.getRecord(resourceId);

                if (resource == null) {
                    log.warn("Resource with id {} not found", resourceId);
                    return ResponseEntity.notFound().build();
                }

                resource.setResourceName(apResourcesService.getResourceName(resource.getResourceTypeLkey(), resource.getResourceKey()));
                apResourcesService.populateLovFields(resource, lang);

                ResourceAvailabilityDTO dto = new ResourceAvailabilityDTO();
                dto.setKey(resource.getKey());
                dto.setResourceKey(resource.getResourceKey());
                dto.setResourceName(resource.getResourceName());
                dto.setResourceTypeLkey(resource.getResourceTypeLkey());
                dto.setFacilityKey(resource.getFacilityKey());
                dto.setAvailability(getRealAvailability(resource.getResourceKey()));

                log.info("Fetching availability slices with resource key: {}", resource.getKey());
                String whereClause = "resource_key = '" + resourceId + "'";
                List<ApResourceAvailabilitySlice> rawSlices = apResourceAvailabilitySliceService.getList("resource_key = '"+resource.getKey()+"' and deleted_at is null");
                System.out.println(rawSlices);

                rawSlices.forEach(slice -> log.info(
                        "Slice -> key: {}, facilityKey: {}, dayOfWeek: {}, start: {}, end: {}",
                        slice.getKey(),
                        slice.getFacilityKey(),
                        slice.getDayOfWeek(),
                        slice.getStartTimeMinutes(),
                        slice.getEndTimeMinutes()
                ));

                List<ResourceAvailabilityDTO.RowAvailabilitySlice> availabilitySlices = rawSlices.stream()
                        .map(raw -> {
                            ResourceAvailabilityDTO.RowAvailabilitySlice slice = new ResourceAvailabilityDTO.RowAvailabilitySlice();

                            slice.setKey(raw.getKey());
                            slice.setDayOfWeek(raw.getDayOfWeek());
                            slice.setStartHour(Integer.parseInt(raw.getStartTimeMinutes()));
                          //  slice.setStartMinute(raw.getStartTimeMinutes() % 60);
                            slice.setEndHour(Integer.parseInt(raw.getEndTimeMinutes()));
                         ///   slice.setEndMinute(raw.getEndTimeMinutes() % 60);
                            slice.setBreak(raw.getIsbreak());
                            return slice;
                        })
                        .collect(Collectors.toList());

//                dto.setAvailabilitySlices(availabilitySlices);

                dto.setAvailabilitySlices(availabilitySlices);
//                log.debug("Fetched {} availability slices", availabilitySlices.size());

                // dto.setEventSlices(getEventSlices(resource.getResourceKey()));

                dtoList.add(dto);

                response.setObject(dtoList);
                response.setExtraNumeric(BigDecimal.ONE);
                log.info("Returning single resource response successfully");
                return ResponseEntity.ok(response);

            } else {
                log.info("Fetching all resources (no specific id provided)");

                ListRequest listRequest = new ListRequest(queryParams);
                String where = listRequest.buildWhereStatement();
                String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);

                log.debug("Where clause: {}", where);
                log.debug("Where clause for total count: {}", whereForTotal);

                List<ApResources> resources = apResourcesService.getList(where);
                BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_resources where " + whereForTotal);

                log.info("Number of resources fetched: {}", resources.size());
                log.info("Total records count: {}", totalRecord);

                for (ApResources resource : resources) {
                    resource.setResourceName(apResourcesService.getResourceName(resource.getResourceTypeLkey(), resource.getResourceKey()));
                    apResourcesService.populateLovFields(resource, lang);

                    ResourceAvailabilityDTO dto = new ResourceAvailabilityDTO();
                    dto.setKey(resource.getKey());
                    dto.setResourceKey(resource.getResourceKey());
                    dto.setResourceName(resource.getResourceName());
                    dto.setResourceTypeLkey(resource.getResourceTypeLkey());
                    dto.setFacilityKey(resource.getFacilityKey());

                    List<ResourceAvailabilityDTO.Availability> slots = getRealAvailability(resource.getKey());
                    dto.setAvailability(slots);

                    List<ResourceAvailabilityDTO.Availability> aggregated = buildAvailability(slots);
                    dto.setAvailability(aggregated);

                    dtoList.add(dto);
                }

                response.setObject(dtoList);
                response.setExtraNumeric(totalRecord);
                log.info("Returning all resources response successfully");
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            log.error("Exception in /resources-with-availability: ", e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    private List<ResourceAvailabilityDTO.Availability> getRealAvailability(String resourceKey) {
        // Log the start of the method execution
        log.info("Starting to fetch real availability for resource with key: {}", resourceKey);

        List<ResourceAvailabilityDTO.Availability> slots = new ArrayList<>();

        String query = "SELECT day_of_week, start_time_minutes, end_time_minutes " +
                "FROM ap_resource_availability_slice " +
                "WHERE resource_key = ? AND is_valid = true AND isbreak = false AND deleted_at Is NULL " +
                "ORDER BY day_of_week, start_time_minutes";

        try {
            log.debug("Executing query: {} with resourceKey: {}", query, resourceKey);

            List<Map<String, Object>> result = DS.executeListQuery(query, resourceKey);

            // Check if the result list is not empty before logging
            if (!result.isEmpty()) {
                log.info("Successfully retrieved {} availability records for resource: {}", result.size(), resourceKey);
            } else {
                log.info("No availability records found for resource: {}", resourceKey);
            }

            for (Map<String, Object> row : result) {
                int dayOfWeek = Integer.parseInt((String) row.get("day_of_week"));
                int startMinutes = Integer.parseInt((String) row.get("start_time_minutes"));
                int endMinutes = Integer.parseInt((String) row.get("end_time_minutes"));

                ResourceAvailabilityDTO.Availability slot = new ResourceAvailabilityDTO.Availability();
                slot.setDayOfWeek(dayOfWeek);
                slot.setStartHour(startMinutes / 60);
                slot.setStartMinute(startMinutes % 60);
                slot.setEndHour(endMinutes / 60);
                slot.setEndMinute(endMinutes % 60);

                slots.add(slot);
            }

            log.info("Finished processing availability for resource: {}", resourceKey);

        } catch (SQLException e) {
            log.error("Failed to fetch availability for resource {}. SQL Exception: {}", resourceKey, e.getMessage(), e);
        }

        return slots;
    }

    private List<ResourceAvailabilityDTO.Availability> buildAvailability(List<ResourceAvailabilityDTO.Availability> slots) {
        List<ResourceAvailabilityDTO.Availability> aggregatedSlots = new ArrayList<>();

        // Group by dayOfWeek
        Map<Integer, List<ResourceAvailabilityDTO.Availability>> groupedByDay =
                slots.stream().collect(Collectors.groupingBy(ResourceAvailabilityDTO.Availability::getDayOfWeek));

        for (Map.Entry<Integer, List<ResourceAvailabilityDTO.Availability>> entry : groupedByDay.entrySet()) {
            int day = entry.getKey();
            List<ResourceAvailabilityDTO.Availability> daySlots = entry.getValue();

            // Sort by start time
            daySlots.sort(Comparator.comparingInt(s -> s.getStartHour() * 60 + s.getStartMinute()));

            // Merge consecutive or overlapping slots
            int currentStartHour = daySlots.get(0).getStartHour();
            int currentStartMinute = daySlots.get(0).getStartMinute();
            int currentEndHour = daySlots.get(0).getEndHour();
            int currentEndMinute = daySlots.get(0).getEndMinute();

            for (int i = 1; i < daySlots.size(); i++) {
                ResourceAvailabilityDTO.Availability slot = daySlots.get(i);
                int slotStartMinutes = slot.getStartHour() * 60 + slot.getStartMinute();
                int currentEndMinutes = currentEndHour * 60 + currentEndMinute;

                if (slotStartMinutes <= currentEndMinutes) {
                    // Extend current end if overlapping or touching
                    int slotEndMinutes = slot.getEndHour() * 60 + slot.getEndMinute();
                    if (slotEndMinutes > currentEndMinutes) {
                        currentEndHour = slot.getEndHour();
                        currentEndMinute = slot.getEndMinute();
                    }
                } else {
                    // Save current aggregated slot
                    ResourceAvailabilityDTO.Availability agg = new ResourceAvailabilityDTO.Availability();
                    agg.setDayOfWeek(day);
                    agg.setStartHour(currentStartHour);
                    agg.setStartMinute(currentStartMinute);
                    agg.setEndHour(currentEndHour);
                    agg.setEndMinute(currentEndMinute);
                    aggregatedSlots.add(agg);

                    // Start new aggregation
                    currentStartHour = slot.getStartHour();
                    currentStartMinute = slot.getStartMinute();
                    currentEndHour = slot.getEndHour();
                    currentEndMinute = slot.getEndMinute();
                }
            }

            // Add last aggregated slot
            ResourceAvailabilityDTO.Availability agg = new ResourceAvailabilityDTO.Availability();
            agg.setDayOfWeek(day);
            agg.setStartHour(currentStartHour);
            agg.setStartMinute(currentStartMinute);
            agg.setEndHour(currentEndHour);
            agg.setEndMinute(currentEndMinute);
            aggregatedSlots.add(agg);
        }

        return aggregatedSlots;
    }

}
