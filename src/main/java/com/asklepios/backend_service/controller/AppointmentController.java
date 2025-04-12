package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointment")
//@CrossOrigin
@Slf4j
public class AppointmentController
{
    private final ApResourcesService apResourcesService;
    private final ApPractitionerService apPractitionerService;
    private final ApDepartmentService apDepartmentService;
    private final ApAppointmentService apAppointmentService;
    private final ApResourcesAvailabilityTimeService apResourcesAvailabilityTimeService;
    private final ApPatientService apPatientService;
    private final ApDiagnosticTestService apDiagnosticTestService;

    public AppointmentController(ApResourcesService apResourcesService, ApPractitionerService apPractitionerService, ApDepartmentService apDepartmentService, ApAppointmentService apAppointmentService, ApResourcesAvailabilityTimeService apResourcesAvailabilityTimeService, ApDiagnosticTestService apDiagnosticTestService) {
        this.apResourcesService = apResourcesService;
        this.apPractitionerService = apPractitionerService;
        this.apDepartmentService = apDepartmentService;
        this.apAppointmentService = apAppointmentService;
        this.apResourcesAvailabilityTimeService = apResourcesAvailabilityTimeService;
        this.apPatientService = new ApPatientService();
        this.apDiagnosticTestService = apDiagnosticTestService;
    }

    @GetMapping(value = "/resources-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resourcesList(@RequestParam Map<String, String> queryParams,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                all.setResourceName(apResourcesService.getResourceName(all.getResourceTypeLkey(), all.getResourceKey())) ;
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
            @RequestParam String resourceKey,
            @jakarta.annotation.Nullable @RequestHeader String facility_id,
            @jakarta.annotation.Nullable @RequestHeader String access_token,
            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApResources> response = new ParentResponse<>();

            String where = "key = '" + resourceKey + "'";
            System.out.println("++++++++++++>>>>>>>>>>>"+where);
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
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
             String appointmentDate = appointment.getAppointmentStart();
 
             List<ApAppointment> existingAppointment = apAppointmentService
                    .getList(" patient_key =  '" + appointment.getPatientKey() + "' and DATE('"+appointmentDate+"') = DATE(appointment_start) " +
                            " and (resource_type_lkey = '" + appointment.getResourceTypeLkey()+ "' and resource_key ='"+appointment.getResourceKey()+"' )");

            if (!existingAppointment.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "The patient already has an appointment on this day."));
            }

             apAppointmentService.saveRecord(appointment);

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
                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
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
    public ResponseEntity<?> resourceTypeList(@RequestHeader String resource_type,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApResources>> response = new ParentResponse<>();
            System.out.println(resource_type);
            // TODO update status to be a LOV value
           if(resource_type.equals("2039534205961578")) //Practitioner
           {
               ParentResponse<List<ApPractitioner>> responsePra = new ParentResponse<>();
               List<ApPractitioner> listPra = apPractitionerService.getList("appointable = true");
               responsePra.setObject(listPra);
               return ResponseEntity.ok(responsePra);

           }
            // TODO update status to be a LOV value
           else if(resource_type.equals("2039516279378421")) //Department
            {
                ParentResponse<List<ApDepartment>> responseDep = new ParentResponse<>();
                List<ApDepartment> listDep = apDepartmentService.getList("appointable = true");
                responseDep.setObject(listDep);
                return ResponseEntity.ok(responseDep);

            }

           // TODO update status to be a LOV value
           else if(resource_type.equals("2039620472612029")) //Medical Test
           {
               ParentResponse<List<ApDiagnosticTest>> responseDia = new ParentResponse<>();
               List<ApDiagnosticTest> listDep = apDiagnosticTestService.getList("appointable = true");
               responseDia.setObject(listDep);
               return ResponseEntity.ok(responseDia);

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
            @RequestParam String resource_type,
            @RequestParam String facility_id,
            @RequestParam List<String> resources,
            @RequestHeader(required = false) String access_token,
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
            @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
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



}
