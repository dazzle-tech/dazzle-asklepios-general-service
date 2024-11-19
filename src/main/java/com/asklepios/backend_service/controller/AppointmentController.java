package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApAppointment;
import com.asklepios.backend_service.model.generated.pojo.ApDepartment;
import com.asklepios.backend_service.model.generated.pojo.ApPractitioner;
import com.asklepios.backend_service.model.generated.pojo.ApResources;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.ApAppointmentService;
import com.asklepios.backend_service.service.ApDepartmentService;
import com.asklepios.backend_service.service.ApPractitionerService;
import com.asklepios.backend_service.service.ApResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public AppointmentController(ApResourcesService apResourcesService, ApPractitionerService apPractitionerService, ApDepartmentService apDepartmentService, ApAppointmentService apAppointmentService) {
        this.apResourcesService = apResourcesService;
        this.apPractitionerService = apPractitionerService;
        this.apDepartmentService = apDepartmentService;
        this.apAppointmentService = apAppointmentService;
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
            ParentResponse<ApAppointment> response = new ParentResponse<>();
            apAppointmentService.saveRecord(appointment);
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

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


}
