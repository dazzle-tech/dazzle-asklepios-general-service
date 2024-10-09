package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.dao.ApAccessRoleAuthorizationDAO;
import com.asklepios.backend_service.model.generated.dao.ApCatalogDiagnosticTestDAO;
import com.asklepios.backend_service.model.generated.dao.ApUserMedicalLicenseDAO;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.NavigationMap;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setup")
@CrossOrigin
@Slf4j
public class SetupController implements Serializable {

    private final AuthService authService;
    private final ApModuleService apModuleService;
    private final ApUomGroupsService apUomGroupsService;
    private final ApScreenService apScreenService;
    private final ApFacilityService apFacilityService;
    private final ApAccessRoleService apAccessRoleService;
    private final ApLovService apLovService;
    private final ApLovValuesService apLovValuesService;
    private final ApUserService apUserService;
    private final ApAccessRoleScreenService apAccessRoleScreenService;
    private final ApPractitionerService apPractitionerService;
    private final ApDepartmentService apDepartmentService;
    private final ApDentalActionService apDentalActionService;
    private final ApCdtService apCdtService;
    private final ApCdtDentalActionService apCdtDentalActionService;
    private final ApServiceService apServiceService;
    private final ApServiceCdtService apServiceCdtService;
    private final ApAllergensService apAllergensService;
    private final ApIcdCodeService apIcdCodeService;
    private final ApDiagnosticTestService apDiagnosticTestService;
    private final ApDiagnosticTestSpecialPopulationService apDiagnosticTestSpecialPopulationService;
    private final ApDiagnosticTestCatalogHeaderService apDiagnosticTestCatalogHeaderService;
    private final ApDiagnosticTestRadiologyService apDiagnosticTestRadiologyService;
    private final ApDiagnosticTestGeneticsService apDiagnosticTestGeneticsService;
    private final ApCatalogDiagnosticTestService apCatalogDiagnosticTestService;
    private final ApPatientDiagnoseService apPatientDiagnoseService;
    private final ApUserFacilitiesService apUserFacilitiesService ;
    private final ApUserMedicalLicenseService apUserMedicalLicenseService;
    private final ApAddressesService apAddressesService;

    public SetupController(ApUserFacilitiyDepartmentsService apUserFacilitiyDepartmentsService, ApUserFacilitiesService apUserFacilitiesService, AuthService authService, ApModuleService apModuleService, ApUomGroupsService apUomGroupsService, ApScreenService apScreenService, ApFacilityService apFacilityService, ApAccessRoleService apAccessRoleService, ApLovService apLovService, ApLovValuesService apLovValuesService, ApUserService apUserService, ApAccessRoleScreenService apAccessRoleScreenService, ApPractitionerService apPractitionerService, ApDepartmentService apDepartmentService, ApDentalActionService apDentalActionService, ApCdtService apCdtService, ApCdtDentalActionService apCdtDentalActionService, ApServiceService apServiceService, ApServiceCdtService apServiceCdtService, ApAllergensService apAllergensService, ApIcdCodeService apIcdCodeService, ApActiveIngredientService apActiveIngredientService, ApActiveIngredientIndicationService apActiveIngredientIndicationService, ApActiveIngredientContraindicationService apActiveIngredientContraindicationService, ApActiveIngredientDrugInteractionService apActiveIngredientDrugInteractionService, ApActiveIngredientFoodInteractionService apActiveIngredientFoodInteractionService, ApActiveIngredientAdverseEffectService apActiveIngredientAdverseEffectService, ApActiveIngredientSynonymService apActiveIngredientSynonymService, ApDiagnosticTestService apDiagnosticTestService, ApDiagnosticTestSpecialPopulationService apDiagnosticTestSpecialPopulationService, ApDiagnosticTestCatalogHeaderService apDiagnosticTestCatalogHeaderService, ApDiagnosticTestRadiologyService apDiagnosticTestRadiologyService, ApDiagnosticTestGeneticsService apDiagnosticTestGeneticsService, ApAddressesService apAddressesService, ApUserMedicalLicenseService apUserMedicalLicenseService, ApCatalogDiagnosticTestService apCatalogDiagnosticTestService, ApPatientDiagnoseService apPatientDiagnoseService, ApUserFacilitiyDepartmentsService apUserFacilitiyDepartmentsService1) {
        this.authService = authService;
        this.apModuleService = apModuleService;
        this.apScreenService = apScreenService;
        this.apFacilityService = apFacilityService;
        this.apAccessRoleService = apAccessRoleService;
        this.apLovService = apLovService;
        this.apLovValuesService = apLovValuesService;
        this.apUserService = apUserService;
        this.apAccessRoleScreenService = apAccessRoleScreenService;
        this.apPractitionerService = apPractitionerService;
        this.apDepartmentService = apDepartmentService;
        this.apDentalActionService = apDentalActionService;
        this.apCdtService = apCdtService;
        this.apCdtDentalActionService = apCdtDentalActionService;
        this.apServiceService = apServiceService;
        this.apServiceCdtService = apServiceCdtService;
        this.apAllergensService = apAllergensService;
        this.apIcdCodeService = apIcdCodeService;
        this.apDiagnosticTestService = apDiagnosticTestService;
        this.apDiagnosticTestSpecialPopulationService = apDiagnosticTestSpecialPopulationService;
        this.apDiagnosticTestCatalogHeaderService = apDiagnosticTestCatalogHeaderService;
        this.apDiagnosticTestRadiologyService = apDiagnosticTestRadiologyService;
        this.apDiagnosticTestGeneticsService = apDiagnosticTestGeneticsService;
        this.apCatalogDiagnosticTestService = apCatalogDiagnosticTestService;
        this.apPatientDiagnoseService = apPatientDiagnoseService;
        this.apUserFacilitiesService = apUserFacilitiesService;
        this.apUserMedicalLicenseService = apUserMedicalLicenseService;
        this.apAddressesService = apAddressesService;
        this.apUomGroupsService = apUomGroupsService;
        this.apUserFacilitiyDepartmentsService = apUserFacilitiyDepartmentsService1;
    }

    @GetMapping(value = "/navigation-map", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity navigationMap(@Nullable @RequestHeader String access_token,
                                        @Nullable @RequestHeader String facility_id,
                                        @Nullable @RequestHeader Integer access_level,
                                        @Nullable @RequestHeader String lang) {
        ParentResponse<NavigationMap> response = new ParentResponse<>();
        try {
            NavigationMap navigationMap = new NavigationMap();

            ApAccessToken token = authService.validateToken(access_token);

            // TODO validating tokens should not be done within this service layer, rather in the api gateway
            if (token == null) {
                response.setMsg("Invalid Token");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            ApUser user = apUserService.getRecord(token.getUserKey());


            // TODO conduct a more rich screen access structure, and build queries within services when that is done
            List<ApScreen> directScreens = apScreenService.getList("module_key is null " +
                    " and key in (select screen_key from ap_access_role_screen where can_read = true and access_role_key = '" + user.getAccessRoleKey() + "') " +
                    " order by view_order");
            navigationMap.setScreens(directScreens);
            List<ApModule> modules = apModuleService.getList("1=1 order by view_order");
            List<ApModule> finalModules = new ArrayList<>();
            for (ApModule module : modules) {
                List<ApScreen> moduleScreens = apScreenService.getList("module_key = '" + module.getKey() + "' " +
                        " and key in (select screen_key from ap_access_role_screen where can_read = true and access_role_key = '" + user.getAccessRoleKey() + "') " +
                        " order by view_order");
                module.setScreens(moduleScreens);
                if (!moduleScreens.isEmpty())
                    finalModules.add(module);
            }

            navigationMap.setModules(finalModules);

            response.setObject(navigationMap);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.addGeneralError(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(value = "/facility-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> facilityList(@RequestParam Map<String, String> queryParams,
                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                          @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApFacility>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApFacility> list = apFacilityService.getList( "deleted_at is  null and  "+where);

            for (ApFacility facility : list) {
                String entityId = facility.getKey();
                List<ApDepartment> department = apDepartmentService.getList("facility_key ='" + entityId + "'");
                ApAddresses address = apAddressesService.getRecordByentityId(entityId);
                facility.setAddress(address);
                facility.setDepartment(department);
            }
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_facility where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-facility", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveFacility(@RequestBody ApFacility facility,
                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                          @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApFacility> response = new ParentResponse<>();

            ApAddresses existingAddress = apAddressesService.getRecordByentityId(facility.getKey());

            if (existingAddress != null) {
                existingAddress.setStreetAddressLine1(facility.getAddress().getStreetAddressLine1());
                existingAddress.setCityLkey(facility.getAddress().getCityLkey());
                existingAddress.setCountryLkey(facility.getAddress().getCountryLkey());
                existingAddress.setPostalCode(facility.getAddress().getPostalCode());
                existingAddress.setStateProvinceRegionLkey(facility.getAddress().getStateProvinceRegionLkey());
                existingAddress.setUpdatedBy(facility.getUpdatedBy());

                apAddressesService.updateRecord(existingAddress);
            } else {
                ApAddresses newAddress = new ApAddresses();
                newAddress.setEntityId(facility.getKey());

                if (facility.getAddress() != null) {
                    newAddress.setStreetAddressLine1(facility.getAddress().getStreetAddressLine1());
                    newAddress.setCityLkey(facility.getAddress().getCityLkey());
                    newAddress.setCountryLkey(facility.getAddress().getCountryLkey());
                    newAddress.setPostalCode(facility.getAddress().getPostalCode());
                    newAddress.setStateProvinceRegionLkey(facility.getAddress().getStateProvinceRegionLkey());
                }
                newAddress.setEntityTypeLkey("199086556758300");  // Entity type for facilities
                newAddress.setCreatedBy(facility.getCreatedBy());

                apAddressesService.saveRecord(newAddress);
            }

            apFacilityService.saveRecord(facility);

            response.setObject(facility);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-facility", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeFacility(@RequestBody ApFacility facility,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApFacility> response = new ParentResponse<>();
            List<ApFacility> existingFacility = apFacilityService.getList("key = '"+facility.getKey()+"'");
            if (existingFacility == null || existingFacility.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Facility not found");
            }
            apFacilityService.deleteRecord(facility);
            response.setObject(facility);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error deleting facility: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete facility");
        }
    }


    @GetMapping(value = "/access-role-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessRoleList(@RequestParam Map<String, String> queryParams,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAccessRole>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApAccessRole> list = apAccessRoleService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_access_role where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-access-role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAccessRole(@RequestBody ApAccessRole accessRole,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAccessRole> response = new ParentResponse<>();
            if (accessRole.getKey() == null) {
                // new access role object, re-build screen auth matrix  raw data
                apAccessRoleScreenService.buildMatrixBasedOnData();
            }
            apAccessRoleService.saveRecord(accessRole);
            response.setObject(accessRole);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/lov-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> lovList(@RequestParam Map<String, String> queryParams,
                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApLov>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApLov> list = apLovService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_lov where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-lov", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLov(@RequestBody ApLov lov,
                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApLov> response = new ParentResponse<>();
            boolean isNew = lov.getKey() == null;
            apLovService.saveRecord(lov);
            if (!isNew && lov.getLovCode() != null) {
                DS.executeQuery("update ap_lov_values set lov_code = '" + lov.getLovCode() + "' where lov_key = '" + lov.getKey() + "'");
            }
            response.setObject(lov);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/lov-value-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> lovValueList(@RequestParam Map<String, String> queryParams,
                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                          @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApLovValues>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApLovValues> list = apLovValuesService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_lov_values where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-lov-value", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLovValue(@RequestBody ApLovValues lovValue,
                                          @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                          @jakarta.annotation.Nullable @RequestHeader String access_token,
                                          @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                          @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApLovValues> response = new ParentResponse<>();
            apLovValuesService.saveRecord(lovValue);
            response.setObject(lovValue);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/user-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userList(@RequestParam Map<String, String> queryParams,
                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApUser>> response = new ParentResponse<>();

            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }

            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApUser> users = apUserService.getList("deleted_at is null and " + where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_user where " + whereForTotal);
            for (ApUser user : users) {
                List<ApUserFacilities> existingFacilities = apUserFacilitiesService.getList("user_id ='" + user.getKey() + "' and deleted_at is null");
                System.out.println("user_id ='" + user.getKey() + " and deleted_at is null'");

                List<String> existingFacilityKeys = existingFacilities.stream()
                        .map(ApUserFacilities::getFacilityId)
                        .collect(Collectors.toList());

                System.out.println(existingFacilityKeys);
                user.set_facilitiesInput(existingFacilityKeys);
            }

            for (ApUser user : users) {
                List<ApUserFacilitiyDepartments> existingDepartment = apUserFacilitiyDepartmentsService.getList("user_key ='" + user.getKey() + "' and deleted_at is null");
                System.out.println("user_id ='" + user.getKey() + " and deleted_at is null'");

                List<String> existingFacilityKeys = existingDepartment.stream()
                        .map(ApUserFacilitiyDepartments::getDepartmentKey)
                        .collect(Collectors.toList());

                System.out.println(existingFacilityKeys);
                user.set_depratmentsInput(existingFacilityKeys);
            }

            // Prepare the response
            response.setObject(users);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody ApUser user,
                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApUser> response = new ParentResponse<>();

            user.setFullName(user.getFirstName() + " " + user.getLastName());

            // Handle facilities
            if (user.get_facilitiesInput() != null) {
                // Delete existing facilities
                List<ApUserFacilities> existingFacilities = apUserFacilitiesService.getList(
                        "deleted_at is null and user_id ='" + user.getKey() + "'");

                for (ApUserFacilities facility : existingFacilities) {
                    apUserFacilitiesService.deleteRecord(facility);
                    System.out.println("Deleted facility: " + facility);
                }

                // Add new facilities from user input
                for (String facility : user.get_facilitiesInput()) {
                    ApUserFacilities apUserFacilities = new ApUserFacilities();
                    apUserFacilities.setUserId(user.getKey());
                    apUserFacilities.setFacilityId(facility);
                    apUserFacilitiesService.saveRecord(apUserFacilities);
                    System.out.println("Added facility: " + apUserFacilities);
                }
            }

            // Handle departments
            if (user.get_depratmentsInput() != null && user.getSelectedDepartmentsFacilityKey() != null) {
                // Delete existing departments for the user
                List<ApUserFacilitiyDepartments> existingDepartments = apUserFacilitiyDepartmentsService.getList(
                        "user_key = '" + user.getKey() + "' and facilitiy_key = '" + user.getSelectedDepartmentsFacilityKey() + "' and deleted_at is null");

                for (ApUserFacilitiyDepartments department : existingDepartments) {
                    apUserFacilitiyDepartmentsService.deleteRecord(department);
                    System.out.println("Deleted department: " + department);
                }

                // Add new departments from user input
                for (String departmentKey : user.get_depratmentsInput()) {
                    ApUserFacilitiyDepartments apUserFacilitieyDepartment = new ApUserFacilitiyDepartments();
                    apUserFacilitieyDepartment.setUserKey(user.getKey());
                    apUserFacilitieyDepartment.setDepartmentKey(departmentKey);
                    apUserFacilitieyDepartment.setFacilitiyKey(user.getSelectedDepartmentsFacilityKey());
                    apUserFacilitiyDepartmentsService.saveRecord(apUserFacilitieyDepartment);
                    System.out.println("Added department: " + apUserFacilitieyDepartment);
                }
            }

            // Save the user record
            apUserService.saveRecord(user);

            response.setObject(user);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@RequestBody ApUser user,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApUser> response = new ParentResponse<>();
            apUserService.deleteRecord(user);
            response.setObject(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/module-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> moduleList(@RequestParam Map<String, String> queryParams,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApModule>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApModule> list = apModuleService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_lov where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/uom-groups-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uomGroupsList(@RequestParam Map<String, String> queryParams,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApUomGroups>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false,false);
            System.out.println("  deleted_at is null and " +where);
            List<ApUomGroups> list = apUomGroupsService.getList("  deleted_at is null and " +where );
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_uom_groups where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }




    @PostMapping(value = "/save-module", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveModule(@RequestBody ApModule module,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApModule> response = new ParentResponse<>();
            apModuleService.saveRecord(module);
            response.setObject(module);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-uom-groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUomGroups(@RequestBody ApUomGroups uomGroups,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApUomGroups> response = new ParentResponse<>();
            apUomGroupsService.saveRecord(uomGroups);
            response.setObject(uomGroups);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/remove-uom-groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeUomGroups(@RequestBody ApUomGroups uomGroups,
                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApUomGroups> response = new ParentResponse<>();
            apUomGroupsService.deleteRecord(uomGroups);
            response.setObject(uomGroups);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/screen-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> screenList(@RequestParam Map<String, String> queryParams,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApScreen>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApScreen> list = apScreenService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_screen where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-screen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveScreen(@RequestBody ApScreen screen,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApScreen> response = new ParentResponse<>();
            apScreenService.saveRecord(screen);
            apAccessRoleScreenService.buildMatrixBasedOnData();
            response.setObject(screen);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/screen-access-matrix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> screenAccessMatrix(@RequestParam String accessRoleKey,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAccessRoleScreen>> response = new ParentResponse<>();
            List<ApAccessRoleScreen> list = apAccessRoleScreenService.getList("access_role_key = '" + accessRoleKey + "'");
            List<ApScreen> screensToCache = apScreenService.getList(null);
            Map<String, String> screenNamesCache = new HashMap<>();
            for (ApScreen screen : screensToCache) {
                screenNamesCache.put(screen.getKey(), screen.getName());
            }

            for (ApAccessRoleScreen accessRoleScreen : list) {
                accessRoleScreen.setScreenName(screenNamesCache.get(accessRoleScreen.getScreenKey()));
            }

            response.setObject(list);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-screen-access-matrix", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveScreenAccessMatrix(@RequestBody List<ApAccessRoleScreen> records,
                                                    @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                    @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                    @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                    @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAccessRoleScreen>> response = new ParentResponse<>();

            for (ApAccessRoleScreen rec : records) {
                apAccessRoleScreenService.saveRecord(rec);
            }

            response.setObject(records);
            response.setMsg("Matrix saved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/practitioner-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> practitionerList(@RequestParam Map<String, String> queryParams,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPractitioner>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApPractitioner> list = apPractitionerService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_practitioner where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/user-midical-license-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> licenseList(@RequestParam Map<String, String> queryParams,
                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                         @jakarta.annotation.Nullable @RequestHeader String access_token,
                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                         @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApUserMedicalLicense>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApUserMedicalLicense> list = apUserMedicalLicenseService.getList("deleted_at is null and "+where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_user_medical_license where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-user-midical-license", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePractitioner(@RequestBody ApUserMedicalLicense userLicense,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApUserMedicalLicense> response = new ParentResponse<>();
            apUserMedicalLicenseService.saveRecord(userLicense);
            response.setObject(userLicense);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/remove-user-midical-license", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@RequestBody  ApUserMedicalLicense userLicense,
                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApUserMedicalLicense> response = new ParentResponse<>();
            apUserMedicalLicenseService.deleteRecord(userLicense);
            response.setObject(userLicense);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-practitioner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePractitioner(@RequestBody ApPractitioner practitioner,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPractitioner> response = new ParentResponse<>();
            apPractitionerService.saveRecord(practitioner);
            response.setObject(practitioner);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-department", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDepartment(@RequestBody ApDepartment department,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDepartment> response = new ParentResponse<>();
            apDepartmentService.saveRecord(department);
            response.setObject(department);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/department-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> departmentList(@RequestParam Map<String, String> queryParams,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDepartment>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDepartment> list = apDepartmentService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_department where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-dental-action", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDentalAction(@RequestBody ApDentalAction dentalAction,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDentalAction> response = new ParentResponse<>();
            apDentalActionService.saveRecord(dentalAction);
            response.setObject(dentalAction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/dental-action-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> dentalActionList(@RequestParam Map<String, String> queryParams,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDentalAction>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDentalAction> list = apDentalActionService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_dental_action where " + whereForTotal);

            for (ApDentalAction action : list) {
                action.setLinkedProcedures(apCdtDentalActionService.getList("dental_action_key = '" + action.getKey() + "'"));
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

    @PostMapping(value = "/link-cdt-action", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> linkCdtAction(@RequestBody ApCdtDentalAction request,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApCdtDentalAction> response = new ParentResponse<>();
            List<ApCdtDentalAction> exists = apCdtDentalActionService.getList("cdt_key = '" + request.getCdtKey() + "' and dental_action_key = '" + request.getDentalActionKey() + "'");
            if (exists != null && !exists.isEmpty()) {
                response.addGeneralError("Procedure CDT already added to treatment");
                return ResponseEntity.status(400).body(response);
            }

            apCdtDentalActionService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/unlink-cdt-action", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unlinkCdtAction(@RequestBody ApCdtDentalAction request,
                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApCdtDentalAction> response = new ParentResponse<>();
            List<ApCdtDentalAction> exists = apCdtDentalActionService.getList("cdt_key = '" + request.getCdtKey() + "' and dental_action_key = '" + request.getDentalActionKey() + "'");
            if (exists == null || exists.isEmpty()) {
                response.addGeneralError("Procedure CDT not linked with treatment");
                return ResponseEntity.status(400).body(response);
            } else {
                request = exists.get(0);
            }
            DS.executeQuery("delete from ap_cdt_dental_action where key = '" + request.getKey() + "'");
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-cdt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCdt(@RequestBody ApCdt cdt,
                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApCdt> response = new ParentResponse<>();
            apCdtService.saveRecord(cdt);
            response.setObject(cdt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/cdt-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cdtList(@RequestParam Map<String, String> queryParams,
                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApCdt>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApCdt> list = apCdtService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_cdt where " + whereForTotal);

            if (!queryParams.containsKey("skipDetails")) {
                for (ApCdt cdt : list) {
                    apCdtService.populateLovFields(cdt, lang);
                    cdt.setLinkedServices(apServiceCdtService.getList("cdt_key = '" + cdt.getKey() + "'"));
                }
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

    @GetMapping(value = "/cdt-list-by-treatment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cdtListByTreatment(@RequestHeader String treatmentKey,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApCdt>> response = new ParentResponse<>();
            List<ApCdt> list = apCdtService.getList("key in (select cdt_key from ap_cdt_dental_action where dental_action_key = '" + treatmentKey + "')");
            response.setObject(list);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveService(@RequestBody ApService service,
                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                         @jakarta.annotation.Nullable @RequestHeader String access_token,
                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                         @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApService> response = new ParentResponse<>();
            apServiceService.saveRecord(service);
            response.setObject(service);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/service-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> serviceList(@RequestParam Map<String, String> queryParams,
                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                         @jakarta.annotation.Nullable @RequestHeader String access_token,
                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                         @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApService>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApService> list = apServiceService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_service where " + whereForTotal);

            if (!queryParams.containsKey("skipDetails")) {
                for (ApService service : list) {
                    apServiceService.populateLovFields(service, lang);
                    service.setLinkedProcedures(apServiceCdtService.getList("service_key = '" + service.getKey() + "'"));
                }
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

    @PostMapping(value = "/link-cdt-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> linkCdtService(@RequestBody ApServiceCdt request,
                                            @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                            @jakarta.annotation.Nullable @RequestHeader String access_token,
                                            @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                            @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApServiceCdt> response = new ParentResponse<>();
            List<ApServiceCdt> exists = apServiceCdtService.getList("cdt_key = '" + request.getCdtKey() +
                    "' and service_key = '" + request.getServiceKey() + "'");
            if (exists != null && !exists.isEmpty()) {
                response.addGeneralError("Procedure CDT already added to service");
                return ResponseEntity.status(400).body(response);
            }

            apServiceCdtService.saveRecord(request);
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/unlink-cdt-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unlinkCdtService(@RequestBody ApServiceCdt request,
                                              @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                              @jakarta.annotation.Nullable @RequestHeader String access_token,
                                              @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                              @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApServiceCdt> response = new ParentResponse<>();
            List<ApServiceCdt> exists = apServiceCdtService.getList("cdt_key = '" + request.getCdtKey() + "' and service_key = '" + request.getServiceKey() + "'");
            if (exists == null || exists.isEmpty()) {
                response.addGeneralError("Procedure CDT not linked with service");
                return ResponseEntity.status(400).body(response);
            } else {
                request = exists.get(0);
            }
            DS.executeQuery("delete from ap_service_cdt where key = '" + request.getKey() + "'");
            response.setObject(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-allergens", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAllergens(@RequestBody ApAllergens allergens,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApAllergens> response = new ParentResponse<>();
            apAllergensService.saveRecord(allergens);
            response.setObject(allergens);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/allergens-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allergensList(@RequestParam Map<String, String> queryParams,
                                           @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                           @jakarta.annotation.Nullable @RequestHeader String access_token,
                                           @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                           @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApAllergens>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApAllergens> list = apAllergensService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_allergens where " + whereForTotal);
            for (ApAllergens all : list) {
                apAllergensService.populateLovFields(all, lang);
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

    @GetMapping(value = "/icd-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> icdList(@RequestParam Map<String, String> queryParams,
                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApIcdCode>> response = new ParentResponse<>();
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApIcdCode> list = apIcdCodeService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_icd_code where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-diagnostic-test-genetics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticTestGenetics(@RequestBody ApDiagnosticTestGenetics diagnosticTestGenetics,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTestGenetics> response = new ParentResponse<>();
            apDiagnosticTestGeneticsService.saveRecord(diagnosticTestGenetics);
            response.setObject(diagnosticTestGenetics);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/diagnostic-test-genetics-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestGeneticsList(@RequestParam Map<String, String> queryParams,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTestGenetics>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApDiagnosticTestGenetics> list = apDiagnosticTestGeneticsService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_test_genetics where " + whereForTotal);
            for (ApDiagnosticTestGenetics all : list) {
                apDiagnosticTestGeneticsService.populateLovFields(all, lang);
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


    @PostMapping(value = "/save-diagnostic-test-radiology", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticTestRadiology(@RequestBody ApDiagnosticTestRadiology diagnosticTestRadiology,
                                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                         @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                         @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTestRadiology> response = new ParentResponse<>();
            apDiagnosticTestRadiologyService.saveRecord(diagnosticTestRadiology);
            response.setObject(diagnosticTestRadiology);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/diagnostic-test-radiology-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestRadiologyList(@RequestParam Map<String, String> queryParams,
                                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                         @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                         @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTestRadiology>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApDiagnosticTestRadiology> list = apDiagnosticTestRadiologyService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_test_radiology where " + whereForTotal);
            for (ApDiagnosticTestRadiology all : list) {
                apDiagnosticTestRadiologyService.populateLovFields(all, lang);
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

    @PostMapping(value = "/save-catalog-diagnostic-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCatalogDiagnosticTest(@RequestBody List<ApCatalogDiagnosticTest> catalogDiagnosticTestRecords,
                                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                             @jakarta.annotation.Nullable @RequestHeader String lang) {

        try {
            ParentResponse<List<ApCatalogDiagnosticTest>> response = new ParentResponse<>();
            for (ApCatalogDiagnosticTest rec : catalogDiagnosticTestRecords) {
                System.out.println("Hello every one " + rec);
                apCatalogDiagnosticTestService.saveRecord(rec);
            }
            response.setObject(catalogDiagnosticTestRecords);
            response.setMsg("Test saved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-catalog-diagnostic-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeCatalogDiagnosticTest(@RequestBody ApDiagnosticTest diagnosticTest,
                                                         @RequestHeader String catalogKey,
                                                               @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                               @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                               @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                               @jakarta.annotation.Nullable @RequestHeader String lang) {

        try {
            ParentResponse<ApCatalogDiagnosticTest> response = new ParentResponse<>();
            List<ApCatalogDiagnosticTest> testList = apCatalogDiagnosticTestService.getList("catalog_key = '" + catalogKey + "' and test_key = '"+diagnosticTest.getKey()+"'");
            if(testList.isEmpty()){
                response.setObject(null);
                return ResponseEntity.ok(response);
            }
                apCatalogDiagnosticTestService.deleteRecord(testList.get(0));
            response.setObject(testList.get(0));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/catalog-diagnostic-test-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> catalogDiagnosticTestList(@jakarta.annotation.Nullable @RequestHeader String catalogKey,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTest>> response = new ParentResponse<>();

            List<ApCatalogDiagnosticTest> testList = apCatalogDiagnosticTestService.getList("catalog_key = '" + catalogKey + "' and deleted_at is Null");
            List<String> testID = new ArrayList<>();

            for (ApCatalogDiagnosticTest test : testList ) {
                testID.add(test.getTestKey());
            }
            List<ApDiagnosticTest> systems = apDiagnosticTestService.getList("key IN ('" + String.join("','", testID) + "')");

            for(ApDiagnosticTest all : systems){
                apDiagnosticTestService.populateLovFields(all, lang);
            }
            response.setObject(systems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-diagnostic-test-catalog-header", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticTestCatalogHeader(@RequestBody ApDiagnosticTestCatalogHeader diagnosticTestCatalogHeader,
                                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTestCatalogHeader> response = new ParentResponse<>();
            apDiagnosticTestCatalogHeaderService.saveRecord(diagnosticTestCatalogHeader);
            response.setObject(diagnosticTestCatalogHeader);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-diagnostic-test-catalog-header", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeDiagnosticTestCatalogHeader(@RequestBody ApDiagnosticTestCatalogHeader diagnosticTestCatalogHeader,
                                                               @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                               @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                               @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                               @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTestCatalogHeader> response = new ParentResponse<>();
            apDiagnosticTestCatalogHeaderService.deleteRecord(diagnosticTestCatalogHeader);
            response.setObject(diagnosticTestCatalogHeader);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/diagnostic-test-catalog-header-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestCatalogHeaderList(@RequestParam Map<String, String> queryParams,
                                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTestCatalogHeader>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApDiagnosticTestCatalogHeader> list = apDiagnosticTestCatalogHeaderService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_test_catalog_header where " + whereForTotal);
            for (ApDiagnosticTestCatalogHeader all : list) {
                apDiagnosticTestCatalogHeaderService.populateLovFields(all, lang);
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


    @PostMapping(value = "/remove-diagnostic-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeDiagnosticTest(@RequestBody ApDiagnosticTest diagnosticTest,
                                                  @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                  @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                  @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                  @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTest> response = new ParentResponse<>();
            apDiagnosticTestService.deleteRecord(diagnosticTest);
            response.setObject(diagnosticTest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-diagnostic-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticTest(@RequestBody ApDiagnosticTest diagnosticTest,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTest> response = new ParentResponse<>();
            apDiagnosticTestService.saveRecord(diagnosticTest);
            response.setObject(diagnosticTest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/diagnostic-test-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestList(@RequestParam Map<String, String> queryParams,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTest>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApDiagnosticTest> list = apDiagnosticTestService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_test where " + whereForTotal);
            for (ApDiagnosticTest all : list) {
                apDiagnosticTestService.populateLovFields(all, lang);
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

    @GetMapping(value = "/diagnostic-test-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestType(@RequestHeader String testTypeKey,
                                                @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<String> response = new ParentResponse<>();
            List<ApLovValues> list = apLovValuesService.getList("key = '"+testTypeKey+"'");
            for(ApLovValues all : list){
                apLovValuesService.populateLovFields(all, lang);
            }
            response.setObject(!list.isEmpty() ? list.get(0).getLovDisplayVale(): "");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/diagnostic-test-no-catalog-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestNotSelectOnCatalogList(@jakarta.annotation.Nullable @RequestHeader String catalogKey,
                                                                  @jakarta.annotation.Nullable @RequestHeader String type,
                                                                  @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                  @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                  @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                  @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTest>> response = new ParentResponse<>();
            List<ApCatalogDiagnosticTest> testList = apCatalogDiagnosticTestService.getList("catalog_key = '" + catalogKey + "'");
            List<String> testID = new ArrayList<>();

            for (ApCatalogDiagnosticTest test : testList ) {
                testID.add(test.getTestKey());
            }
            List<ApDiagnosticTest> systems = apDiagnosticTestService.getList("key Not IN ('" + String.join("','", testID) + "') and test_type_lkey = '"+type+"'");
            System.out.println("please print query" + systems);
            for(ApDiagnosticTest all : systems){
                apDiagnosticTestService.populateLovFields(all, lang);
            }
            response.setObject(systems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-diagnostic-test-special-population", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveDiagnosticTestSpecialPopulation(@RequestBody ApDiagnosticTestSpecialPopulation diagnosticTestSpecialPopulation,
                                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                 @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTestSpecialPopulation> response = new ParentResponse<>();
            apDiagnosticTestSpecialPopulationService.saveRecord(diagnosticTestSpecialPopulation);
            response.setObject(diagnosticTestSpecialPopulation);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-diagnostic-test-special-population", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeDiagnosticTestSpecialPopulation(@RequestBody ApDiagnosticTestSpecialPopulation diagnosticTestSpecialPopulation,
                                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                   @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDiagnosticTestSpecialPopulation> response = new ParentResponse<>();
            apDiagnosticTestSpecialPopulationService.deleteRecord(diagnosticTestSpecialPopulation);
            response.setObject(diagnosticTestSpecialPopulation);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/diagnostic-test-special-population-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> diagnosticTestSpecialPopulationList(@RequestParam Map<String, String> queryParams,
                                                                 @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                                 @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                                 @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                                 @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDiagnosticTestSpecialPopulation>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApDiagnosticTestSpecialPopulation> list = apDiagnosticTestSpecialPopulationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_diagnostic_test_special_population where " + whereForTotal);
            for (ApDiagnosticTestSpecialPopulation all : list) {
                apDiagnosticTestSpecialPopulationService.populateLovFields(all, lang);
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


}
