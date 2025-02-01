package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.NavigationMap;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medications")
//@CrossOrigin
@Slf4j
public class MedicationsSetupController {

    private final ApActiveIngredientService apActiveIngredientService;
    private final ApActiveIngredientIndicationService apActiveIngredientIndicationService;
    private final ApActiveIngredientContraindicationService apActiveIngredientContraindicationService;
    private final ApActiveIngredientDrugInteractionService apActiveIngredientDrugInteractionService;
    private final ApActiveIngredientFoodInteractionService apActiveIngredientFoodInteractionService;
    private final ApActiveIngredientAdverseEffectService apActiveIngredientAdverseEffectService;
    private final ApActiveIngredientSynonymService apActiveIngredientSynonymService;
    private final ApActiveIngredientRecommendedDosageService apActiveIngredientRecommendedDosageService;
    private final ApActiveIngredientSpecialPopulationService apActiveIngredientSpecialPopulationService;
    private final ApPrescriptionInstructionService apPrescriptionInstructionService;
    private final ApGenericMedicationService apGenericMedicationService;
    private final ApGenericMedicationActiveIngredientService apGenericMedicationActiveIngredientService;
    private final ApGenericMedicationRoaService apGenericMedicationRoaService;

    public MedicationsSetupController(ApActiveIngredientService apActiveIngredientService, ApActiveIngredientIndicationService apActiveIngredientIndicationService, ApActiveIngredientContraindicationService apActiveIngredientContraindicationService, ApActiveIngredientDrugInteractionService apActiveIngredientDrugInteractionService, ApActiveIngredientFoodInteractionService apActiveIngredientFoodInteractionService, ApActiveIngredientAdverseEffectService apActiveIngredientAdverseEffectService, ApActiveIngredientSynonymService apActiveIngredientSynonymService, ApActiveIngredientRecommendedDosageService apActiveIngredientRecommendedDosageService, ApActiveIngredientSpecialPopulationService apActiveIngredientSpecialPopulationService, ApPrescriptionInstructionService apPrescriptionInstructionService, ApGenericMedicationService apGenericMedicationService, ApGenericMedicationActiveIngredientService apGenericMedicationActiveIngredientService, ApGenericMedicationRoaService apGenericMedicationRoaService) {
        this.apActiveIngredientService = apActiveIngredientService;
        this.apActiveIngredientIndicationService = apActiveIngredientIndicationService;
        this.apActiveIngredientContraindicationService = apActiveIngredientContraindicationService;
        this.apActiveIngredientDrugInteractionService = apActiveIngredientDrugInteractionService;
        this.apActiveIngredientFoodInteractionService = apActiveIngredientFoodInteractionService;
        this.apActiveIngredientAdverseEffectService = apActiveIngredientAdverseEffectService;
        this.apActiveIngredientSynonymService = apActiveIngredientSynonymService;
        this.apActiveIngredientRecommendedDosageService = apActiveIngredientRecommendedDosageService;
        this.apActiveIngredientSpecialPopulationService = apActiveIngredientSpecialPopulationService;
        this.apPrescriptionInstructionService = apPrescriptionInstructionService;
        this.apGenericMedicationService = apGenericMedicationService;
        this.apGenericMedicationActiveIngredientService = apGenericMedicationActiveIngredientService;
        this.apGenericMedicationRoaService = apGenericMedicationRoaService;
    }

    @PostMapping(value = "/save-generic-medication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveGenericMedication(@RequestBody ApGenericMedication genericMedication,
                                                         @Nullable @RequestHeader String facility_id,
                                                         @Nullable @RequestHeader String access_token,
                                                         @Nullable @RequestHeader Integer access_level,
                                                         @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApGenericMedication> response = new ParentResponse<>();
            apGenericMedicationService.saveRecord(genericMedication);
            response.setObject(genericMedication);
            apGenericMedicationRoaService.saveROA(genericMedication.getRoaList(), genericMedication.getKey());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-generic-medication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeGenericMedication(@RequestBody ApGenericMedication genericMedication,
                                                           @Nullable @RequestHeader String facility_id,
                                                           @Nullable @RequestHeader String access_token,
                                                           @Nullable @RequestHeader Integer access_level,
                                                           @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApGenericMedication> response = new ParentResponse<>();
            if(genericMedication.getDeletedAt() != null){
                genericMedication.setDeletedAt(null);
                genericMedication.setDeletedBy(null);
              apGenericMedicationService.saveRecord(genericMedication);
            }
            else{
            apGenericMedicationService.deleteRecord(genericMedication);
            }
            response.setObject(genericMedication);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/generic-medication-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> genericMedicationList(@RequestParam Map<String, String> queryParams,
                                                         @Nullable @RequestHeader String facility_id,
                                                         @Nullable @RequestHeader String access_token,
                                                         @Nullable @RequestHeader Integer access_level,
                                                         @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApGenericMedication>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApGenericMedication> list = apGenericMedicationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_generic_medication where " + whereForTotal);
            for(ApGenericMedication all : list){
                apGenericMedicationService.populateLovFields(all, lang);
                List<ApGenericMedicationRoa> roaList = new ApGenericMedicationRoaService().getList("generic_medication_key = '" + all.getKey() + "' and deleted_at is null");
                if(!roaList.isEmpty()){
                    List<String> roaIds = new ArrayList<>();
                    roaList.forEach(roa -> roaIds.add(roa.getRoaLkey()));
                    all.setRoaList(roaIds);
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
    @GetMapping(value = "/generic-medication_act-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> genericMedicationByActiveIngredientList(@RequestHeader  String active,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApGenericMedication>> response = new ParentResponse<>();



            List<ApGenericMedication> list = apGenericMedicationService.getListapv(active,"");

            for(ApGenericMedication all : list){
                apGenericMedicationService.populateLovFields(all, lang);
                List<ApGenericMedicationRoa> roaList = new ApGenericMedicationRoaService().getList("generic_medication_key = '" + all.getKey() + "' and deleted_at is null");
                if(!roaList.isEmpty()){
                    List<String> roaIds = new ArrayList<>();
                    roaList.forEach(roa -> roaIds.add(roa.getRoaLkey()));
                    all.setRoaList(roaIds);
                }
            }
            response.setObject(list);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-generic-medication-active-ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveGenericMedicationActiveIngredient(@RequestBody ApGenericMedicationActiveIngredient genericMedicationActiveIngredient,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApGenericMedicationActiveIngredient> response = new ParentResponse<>();
            apGenericMedicationActiveIngredientService.saveRecord(genericMedicationActiveIngredient);
            response.setObject(genericMedicationActiveIngredient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @PostMapping(value = "/remove-generic-medication-active-ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeGenericMedicationActiveIngredient(@RequestBody ApGenericMedicationActiveIngredient genericMedicationActiveIngredient,
                                                     @Nullable @RequestHeader String facility_id,
                                                     @Nullable @RequestHeader String access_token,
                                                     @Nullable @RequestHeader Integer access_level,
                                                     @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApGenericMedicationActiveIngredient> response = new ParentResponse<>();
            apGenericMedicationActiveIngredientService.deleteRecord(genericMedicationActiveIngredient);
            response.setObject(genericMedicationActiveIngredient);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }
    @GetMapping(value = "/generic-medication-active-ingredient-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> genericMedicationActiveIngredientList(@RequestParam Map<String, String> queryParams,
                                                   @Nullable @RequestHeader String facility_id,
                                                   @Nullable @RequestHeader String access_token,
                                                   @Nullable @RequestHeader Integer access_level,
                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApGenericMedicationActiveIngredient>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApGenericMedicationActiveIngredient> list = apGenericMedicationActiveIngredientService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_generic_medication_active_ingredient where " + whereForTotal);
            for(ApGenericMedicationActiveIngredient all : list){
                apGenericMedicationActiveIngredientService.populateLovFields(all, lang);
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
    @PostMapping(value = "/save-prescription-instruction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePrescriptionInstruction(@RequestBody ApPrescriptionInstruction prescriptionInstruction,
                                                                   @Nullable @RequestHeader String facility_id,
                                                                   @Nullable @RequestHeader String access_token,
                                                                   @Nullable @RequestHeader Integer access_level,
                                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPrescriptionInstruction> response = new ParentResponse<>();
            apPrescriptionInstructionService.saveRecord(prescriptionInstruction);
            response.setObject(prescriptionInstruction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-prescription-instruction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removePrescriptionInstruction(@RequestBody ApPrescriptionInstruction prescriptionInstruction,
                                                                     @Nullable @RequestHeader String facility_id,
                                                                     @Nullable @RequestHeader String access_token,
                                                                     @Nullable @RequestHeader Integer access_level,
                                                                     @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApPrescriptionInstruction> response = new ParentResponse<>();
            apPrescriptionInstructionService.deleteRecord(prescriptionInstruction);
            response.setObject(prescriptionInstruction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/prescription-instruction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> prescriptionInstructionList(@RequestParam Map<String, String> queryParams,
                                                                   @Nullable @RequestHeader String facility_id,
                                                                   @Nullable @RequestHeader String access_token,
                                                                   @Nullable @RequestHeader Integer access_level,
                                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApPrescriptionInstruction>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApPrescriptionInstruction> list = apPrescriptionInstructionService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_prescription_instruction where " + whereForTotal);
            for(ApPrescriptionInstruction all : list){
                apPrescriptionInstructionService.populateLovFields(all, lang);
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

    @PostMapping(value = "/save-active-ingredient-synonym", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientSynonym(@RequestBody ApActiveIngredientSynonym activeIngredientSynonym,
                                                         @Nullable @RequestHeader String facility_id,
                                                         @Nullable @RequestHeader String access_token,
                                                         @Nullable @RequestHeader Integer access_level,
                                                         @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientSynonym> response = new ParentResponse<>();
            apActiveIngredientSynonymService.saveRecord(activeIngredientSynonym);
            response.setObject(activeIngredientSynonym);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-synonym", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientSynonym(@RequestBody ApActiveIngredientSynonym activeIngredientSynonym,
                                                           @Nullable @RequestHeader String facility_id,
                                                           @Nullable @RequestHeader String access_token,
                                                           @Nullable @RequestHeader Integer access_level,
                                                           @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientSynonym> response = new ParentResponse<>();
            apActiveIngredientSynonymService.deleteRecord(activeIngredientSynonym);
            response.setObject(activeIngredientSynonym);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-synonym-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientSynonymList(@RequestParam Map<String, String> queryParams,
                                                         @Nullable @RequestHeader String facility_id,
                                                         @Nullable @RequestHeader String access_token,
                                                         @Nullable @RequestHeader Integer access_level,
                                                         @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientSynonym>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientSynonym> list = apActiveIngredientSynonymService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from  ap_active_ingredient_synonym where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-active-ingredient-special-population", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientSpecialPopulation(@RequestBody ApActiveIngredientSpecialPopulation activeIngredientSpecialPopulation,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientSpecialPopulation> response = new ParentResponse<>();
            apActiveIngredientSpecialPopulationService.saveRecord(activeIngredientSpecialPopulation);
            response.setObject(activeIngredientSpecialPopulation);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-special-population", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientSpecialPopulation(@RequestBody ApActiveIngredientSpecialPopulation activeIngredientSpecialPopulation,
                                                                   @Nullable @RequestHeader String facility_id,
                                                                   @Nullable @RequestHeader String access_token,
                                                                   @Nullable @RequestHeader Integer access_level,
                                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientSpecialPopulation> response = new ParentResponse<>();
            apActiveIngredientSpecialPopulationService.deleteRecord(activeIngredientSpecialPopulation);
            response.setObject(activeIngredientSpecialPopulation);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-special-population-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientSpecialPopulationList(@RequestParam Map<String, String> queryParams,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientSpecialPopulation>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientSpecialPopulation> list = apActiveIngredientSpecialPopulationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_special_population where " + whereForTotal);
            for(ApActiveIngredientSpecialPopulation all : list){
                apActiveIngredientSpecialPopulationService.populateLovFields(all, lang);
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


    @PostMapping(value = "/save-active-ingredient-adverse-effect", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientAdverseEffect(@RequestBody ApActiveIngredientAdverseEffect activeIngredientAdverseEffect,
                                                               @Nullable @RequestHeader String facility_id,
                                                               @Nullable @RequestHeader String access_token,
                                                               @Nullable @RequestHeader Integer access_level,
                                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientAdverseEffect> response = new ParentResponse<>();
            apActiveIngredientAdverseEffectService.saveRecord(activeIngredientAdverseEffect);
            response.setObject(activeIngredientAdverseEffect);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-adverse-effect", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientAdverseEffect(@RequestBody ApActiveIngredientAdverseEffect activeIngredientAdverseEffect,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientAdverseEffect> response = new ParentResponse<>();
            apActiveIngredientAdverseEffectService.deleteRecord(activeIngredientAdverseEffect);
            response.setObject(activeIngredientAdverseEffect);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-adverse-effect-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientAdverseEffectList(@RequestParam Map<String, String> queryParams,
                                                               @Nullable @RequestHeader String facility_id,
                                                               @Nullable @RequestHeader String access_token,
                                                               @Nullable @RequestHeader Integer access_level,
                                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientAdverseEffect>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientAdverseEffect> list = apActiveIngredientAdverseEffectService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_adverse_effect where " + whereForTotal);
            for(ApActiveIngredientAdverseEffect all : list){
                apActiveIngredientAdverseEffectService.populateLovFields(all, lang);
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

    @PostMapping(value = "/save-active-ingredient-food-interaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientFoodInteraction(@RequestBody ApActiveIngredientFoodInteraction activeIngredientFoodInteraction,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientFoodInteraction> response = new ParentResponse<>();
            apActiveIngredientFoodInteractionService.saveRecord(activeIngredientFoodInteraction);
            response.setObject(activeIngredientFoodInteraction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-food-interaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientFoodInteraction(@RequestBody ApActiveIngredientFoodInteraction activeIngredientFoodInteraction,
                                                                   @Nullable @RequestHeader String facility_id,
                                                                   @Nullable @RequestHeader String access_token,
                                                                   @Nullable @RequestHeader Integer access_level,
                                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientFoodInteraction> response = new ParentResponse<>();
            apActiveIngredientFoodInteractionService.deleteRecord(activeIngredientFoodInteraction);
            response.setObject(activeIngredientFoodInteraction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-food-interaction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientFoodInteractionList(@RequestParam Map<String, String> queryParams,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientFoodInteraction>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientFoodInteraction> list = apActiveIngredientFoodInteractionService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_food_interaction where " + whereForTotal);
            for(ApActiveIngredientFoodInteraction all : list){
                apActiveIngredientFoodInteractionService.populateLovFields(all, lang);
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

    @PostMapping(value = "/save-active-ingredient-drug-interaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientDrugInteraction(@RequestBody ApActiveIngredientDrugInteraction activeIngredientDrugInteraction,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientDrugInteraction> response = new ParentResponse<>();
            apActiveIngredientDrugInteractionService.saveRecord(activeIngredientDrugInteraction);
            response.setObject(activeIngredientDrugInteraction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-drug-interaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientDrugInteraction(@RequestBody ApActiveIngredientDrugInteraction activeIngredientDrugInteraction,
                                                                   @Nullable @RequestHeader String facility_id,
                                                                   @Nullable @RequestHeader String access_token,
                                                                   @Nullable @RequestHeader Integer access_level,
                                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientDrugInteraction> response = new ParentResponse<>();
            apActiveIngredientDrugInteractionService.deleteRecord(activeIngredientDrugInteraction);
            response.setObject(activeIngredientDrugInteraction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/active-ingredient-drug-interaction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientDrugInteractionList(@RequestHeader Map<String, String> queryParams,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientDrugInteraction>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
           List<ApActiveIngredientDrugInteraction> list = apActiveIngredientDrugInteractionService.getList(where);
           BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_drug_interaction where " + whereForTotal);
            for(ApActiveIngredientDrugInteraction all : list){
                apActiveIngredientDrugInteractionService.populateLovFields(all, lang);
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

    @GetMapping(value = "/active-ingredient-drug-interaction-by-key-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientDrugInteractionByKeyList(@RequestHeader String activeKey,
                                                                 @Nullable @RequestHeader String facility_id,
                                                                 @Nullable @RequestHeader String access_token,
                                                                 @Nullable @RequestHeader Integer access_level,
                                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientDrugInteraction>> response = new ParentResponse<>();
            List<ApActiveIngredientDrugInteraction> list = apActiveIngredientDrugInteractionService.getActiveIngredientDrug(activeKey);
            for(ApActiveIngredientDrugInteraction all : list){
                apActiveIngredientDrugInteractionService.populateLovFields(all, lang);
            }
            response.setObject(list);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-active-ingredient-recommended-dosage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientRecommendedDosage(@RequestBody ApActiveIngredientRecommendedDosage activeIngredientRecommendedDosage,
                                                                  @Nullable @RequestHeader String facility_id,
                                                                  @Nullable @RequestHeader String access_token,
                                                                  @Nullable @RequestHeader Integer access_level,
                                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientRecommendedDosage> response = new ParentResponse<>();
            apActiveIngredientRecommendedDosageService.saveRecord(activeIngredientRecommendedDosage);
            response.setObject(activeIngredientRecommendedDosage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-recommended-dosage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientRecommendedDosage(@RequestBody ApActiveIngredientRecommendedDosage activeIngredientRecommendedDosage,
                                                                    @Nullable @RequestHeader String facility_id,
                                                                    @Nullable @RequestHeader String access_token,
                                                                    @Nullable @RequestHeader Integer access_level,
                                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientRecommendedDosage> response = new ParentResponse<>();
            apActiveIngredientRecommendedDosageService.deleteRecord(activeIngredientRecommendedDosage);
            response.setObject(activeIngredientRecommendedDosage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-recommended-dosage-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientRecommendedDosageList(@RequestParam Map<String, String> queryParams,
                                                                   @Nullable @RequestHeader String facility_id,
                                                                   @Nullable @RequestHeader String access_token,
                                                                   @Nullable @RequestHeader Integer access_level,
                                                                   @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientRecommendedDosage>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientRecommendedDosage> list = apActiveIngredientRecommendedDosageService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_recommended_dosage where " + whereForTotal);
            for(ApActiveIngredientRecommendedDosage all : list){
                apActiveIngredientRecommendedDosageService.populateLovFields(all, lang);
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


    @GetMapping(value = "/active-ingredient-contraindication-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientContraindicationList(@RequestParam Map<String, String> queryParams,
                                                                  @Nullable @RequestHeader String facility_id,
                                                                  @Nullable @RequestHeader String access_token,
                                                                  @Nullable @RequestHeader Integer access_level,
                                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientContraindication>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientContraindication> list = apActiveIngredientContraindicationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_contraindication where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }



    @PostMapping(value = "/save-active-ingredient-contraindication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientContraindication(@RequestBody ApActiveIngredientContraindication activeIngredientContraindication,
                                                                  @Nullable @RequestHeader String facility_id,
                                                                  @Nullable @RequestHeader String access_token,
                                                                  @Nullable @RequestHeader Integer access_level,
                                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientContraindication> response = new ParentResponse<>();
            apActiveIngredientContraindicationService.saveRecord(activeIngredientContraindication);
            response.setObject(activeIngredientContraindication);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-contraindication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientContraindication(@RequestBody ApActiveIngredientContraindication activeIngredientContraindication,
                                                                    @Nullable @RequestHeader String facility_id,
                                                                    @Nullable @RequestHeader String access_token,
                                                                    @Nullable @RequestHeader Integer access_level,
                                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientContraindication> response = new ParentResponse<>();
            apActiveIngredientContraindicationService.deleteRecord(activeIngredientContraindication);
            response.setObject(activeIngredientContraindication);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-active-ingredient-indication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredientIndication(@RequestBody ApActiveIngredientIndication activeIngredientIndication,
                                                            @Nullable @RequestHeader String facility_id,
                                                            @Nullable @RequestHeader String access_token,
                                                            @Nullable @RequestHeader Integer access_level,
                                                            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientIndication> response = new ParentResponse<>();
            apActiveIngredientIndicationService.saveRecord(activeIngredientIndication);
            response.setObject(activeIngredientIndication);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-active-ingredient-indication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeActiveIngredientIndication(@RequestBody ApActiveIngredientIndication activeIngredientIndication,
                                                              @Nullable @RequestHeader String facility_id,
                                                              @Nullable @RequestHeader String access_token,
                                                              @Nullable @RequestHeader Integer access_level,
                                                              @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredientIndication> response = new ParentResponse<>();
            apActiveIngredientIndicationService.deleteRecord(activeIngredientIndication);
            response.setObject(activeIngredientIndication);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-indication-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientIndicationList(@RequestParam Map<String, String> queryParams,
                                                            @Nullable @RequestHeader String facility_id,
                                                            @Nullable @RequestHeader String access_token,
                                                            @Nullable @RequestHeader Integer access_level,
                                                            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredientIndication>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredientIndication> list = apActiveIngredientIndicationService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient_indication where " + whereForTotal);
            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-active-ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveActiveIngredient(@RequestBody ApActiveIngredient activeIngredient,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApActiveIngredient> response = new ParentResponse<>();
            if(activeIngredient.getKey() == null) {
                List <ApActiveIngredient> activeIngredients=  apActiveIngredientService.getList("code = '"+activeIngredient.getCode()+"' and name = '"+ activeIngredient.getName()+"' ");
                if(activeIngredients.isEmpty()) {
                    apActiveIngredientService.saveRecord(activeIngredient);
                    response.setObject(activeIngredient);
                    response.setMsg("The active ingredient "+activeIngredient.getName()+" is added successfully.");
                }else{
                    response.setObject(activeIngredient);
                    response.setMsg("There is already an active ingredient.");
                }
            } else{

                apActiveIngredientService.saveRecord(activeIngredient);
                response.setObject(activeIngredient);
                response.setMsg("The active ingredient "+activeIngredient.getName()+" is updated successfully.");

                }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/active-ingredient-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> activeIngredientList(@RequestParam Map<String, String> queryParams,
                                                  @Nullable @RequestHeader String facility_id,
                                                  @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApActiveIngredient>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false);
            List<ApActiveIngredient> list = apActiveIngredientService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_active_ingredient where " + whereForTotal);
            for(ApActiveIngredient all : list){
                apActiveIngredientService.populateLovFields(all, lang);
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
