package com.asklepios.backend_service.controller;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApCdt;
import com.asklepios.backend_service.model.generated.pojo.ApDentalAction;
import com.asklepios.backend_service.model.generated.pojo.ApDentalChart;
import com.asklepios.backend_service.model.generated.pojo.ApDentalChartProgressNote;
import com.asklepios.backend_service.model.generated.pojo.ApDentalChartTooth;
import com.asklepios.backend_service.model.generated.pojo.ApDentalPlannedTreatment;
import com.asklepios.backend_service.model.generated.pojo.ApEncounterAppliedService;
import com.asklepios.backend_service.model.generated.pojo.ApService;
import com.asklepios.backend_service.model.generated.pojo.ApServiceCdt;
import com.asklepios.backend_service.model.generated.pojo.ApToothAction;
import com.asklepios.backend_service.model.generated.pojo.ApToothActionLog;
import com.asklepios.backend_service.model.generated.pojo.ApToothCdt;
import com.asklepios.backend_service.model.generated.pojo.ApToothService;
import com.asklepios.backend_service.model.newEntity.PatientEncounter;
import com.asklepios.backend_service.model.newEntity.PatientEncounterService;
import com.asklepios.backend_service.model.newEntity.ServiceSetupRecord;
import com.asklepios.backend_service.model.newEntity.ServiceSetupService;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.request.ToothActionRequest;
import com.asklepios.backend_service.model.pojo.request.ToothServiceRequest;
import com.asklepios.backend_service.model.pojo.response.DentalChartResponse;
import com.asklepios.backend_service.model.pojo.response.DentalTreatmentPlanResponse;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.ApCdtService;
import com.asklepios.backend_service.service.ApDentalActionService;
import com.asklepios.backend_service.service.ApDentalChartProgressNoteService;
import com.asklepios.backend_service.service.ApDentalChartService;
import com.asklepios.backend_service.service.ApDentalChartToothService;
import com.asklepios.backend_service.service.ApDentalPlannedTreatmentService;
import com.asklepios.backend_service.service.ApEncounterAppliedServiceService;
import com.asklepios.backend_service.service.ApServiceCdtService;
import com.asklepios.backend_service.service.ApServiceService;
import com.asklepios.backend_service.service.ApToothActionLogService;
import com.asklepios.backend_service.service.ApToothActionService;
import com.asklepios.backend_service.service.ApToothCdtService;
import com.asklepios.backend_service.service.ApToothServiceService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dental")
//@CrossOrigin
@Slf4j
public class DentalController {

    private final ApDentalActionService apDentalActionService;
    private final ApDentalChartService apDentalChartService;
    private final ApDentalChartToothService apDentalChartToothService;
    private final ApToothActionService apToothActionService;
    private final ApToothActionLogService apToothActionLogService;
    private final ApDentalChartProgressNoteService apDentalChartProgressNoteService;
    private final ApToothCdtService apToothCdtService;
    private final ApToothServiceService apToothServiceService;
    private final ApServiceService apServiceService;
    private final ApCdtService apCdtService;
    private final ApDentalPlannedTreatmentService plannedTreatmentService;
    private final ApServiceCdtService apServiceCdtService;
    private final ApEncounterAppliedServiceService encounterAppliedServiceService;
    private final PatientEncounterService patientEncounterService;
    private final ServiceSetupService serviceSetupService;
    public DentalController(ApDentalActionService apDentalActionService, ApDentalChartService apDentalChartService, ApDentalChartToothService apDentalChartToothService, ApToothActionService apToothActionService, ApToothActionLogService apToothActionLogService, ApDentalChartProgressNoteService apDentalChartProgressNoteService, ApToothCdtService apToothCdtService, ApToothServiceService apToothServiceService, ApServiceService apServiceService, ApCdtService apCdtService, ApDentalPlannedTreatmentService plannedTreatmentService, ApServiceCdtService apServiceCdtService, ApEncounterAppliedServiceService encounterAppliedServiceService, PatientEncounterService patientEncounterService, ServiceSetupService serviceSetupService) {
        this.apDentalActionService = apDentalActionService;
        this.apDentalChartService = apDentalChartService;
        this.apDentalChartToothService = apDentalChartToothService;
        this.apToothActionService = apToothActionService;
        this.apToothActionLogService = apToothActionLogService;
        this.apDentalChartProgressNoteService = apDentalChartProgressNoteService;
        this.apToothCdtService = apToothCdtService;
        this.apToothServiceService = apToothServiceService;
        this.apServiceService = apServiceService;
        this.apCdtService = apCdtService;
        this.plannedTreatmentService = plannedTreatmentService;
        this.apServiceCdtService = apServiceCdtService;
        this.encounterAppliedServiceService = encounterAppliedServiceService;
        this.patientEncounterService = patientEncounterService;
        this.serviceSetupService = serviceSetupService;
    }


    @GetMapping(value = "/dental-action-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> dentalActionList(@RequestParam Map<String, String> queryParams,
                                              @Nullable @RequestHeader String facility_id,
                                              // @Nullable @RequestHeader String access_token,
                                              @Nullable @RequestHeader Integer access_level,
                                              @Nullable @RequestHeader String lang) {
        try {

            ParentResponse<List<ApDentalAction>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApDentalAction> actions = apDentalActionService.getList(where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_dental_action where " + whereForTotal);
            response.setObject(actions);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @GetMapping(value = "/dental-charts-by-encounter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchDentalCharts(@RequestHeader("encounter-key") Long encounterKey,

                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<DentalChartResponse> response = new ParentResponse<>();

            PatientEncounter encounter = patientEncounterService.getRecord(encounterKey);
            if (encounter == null) {
                response.addGeneralError("invalid encounter");
                return ResponseEntity.status(400).body(response);
            }
            if (encounter.getPatientKey() == null) {
                response.addGeneralError("invalid patient");
                return ResponseEntity.status(400).body(response);
            }

            DentalChartResponse dentalChartResponse = new DentalChartResponse();
            ApDentalChart currentChart = null;

            // check if encounter already has dental chart
            List<ApDentalChart> existingDentalChartForEncounterList
                    = apDentalChartService.getList("encounter_key = '" + encounterKey + "'");
            if (existingDentalChartForEncounterList == null || existingDentalChartForEncounterList.isEmpty()) {
                // create a new dental chart for this encounter
                currentChart = new ApDentalChart();
                currentChart.setEncounterKey(String.valueOf(encounterKey));
                currentChart.setChartDate(new Date());
                currentChart.setCreatedAt(BigDecimal.valueOf(System.currentTimeMillis()));
                currentChart.setPatientKey(encounter.getPatientKey().toString());
                currentChart.setType("ADULT");
                // TODO handle the chart type (adult/child/mix)
                currentChart.setIsValid(true);
                currentChart.setStatus("NEW"); // TODO update status to be a LOV value
                apDentalChartService.saveRecord(currentChart);

                // add the 32 tooth to the chart (adult)
                currentChart.setChartTeeth(new ArrayList<>());
                for (int i = 1; i <= 32; i++) {
                    String toothNumber = i + "";
                    if (i < 10)
                        toothNumber = "0" + toothNumber;
                    ApDentalChartTooth tooth = new ApDentalChartTooth();
                    tooth.setToothNumber(toothNumber);
                    tooth.setToothNumberNumeric(BigDecimal.valueOf(i));
                    tooth.setChartKey(currentChart.getKey());
                    tooth.setMissing(false);
                    tooth.setCreatedAt(BigDecimal.valueOf(System.currentTimeMillis()));
                    apDentalChartToothService.saveRecord(tooth);
                    currentChart.getChartTeeth().add(tooth);
                }

                // TODO apply latest chart tooth conditions [tooth, tooth actions, tooth history and progress notes] (to get accumulative work on the chart)
                // apply all actions to new chart from latest chart, alongside tooth history
                // fetch latest chart for patient (based on closed visit only)

                String query = "select key from ap_dental_chart where key <> '" + currentChart.getKey() + "'" +
                        " and patient_key = '" + currentChart.getPatientKey() + "' and encounter_key in " +
                        " (select e.key from ap_encounter e where e.patient_key = '" + currentChart.getPatientKey() + "' and e.encounter_status_lkey = '91109811181900') " +
                        "order by created_at desc";

                System.out.println(query);
                try (Connection connection = DS.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)
                     // TODO replace with redis by lov code (ENC_STATUS/CLOSED)
                ) {
                    if (resultSet.next()) {
                        ApDentalChart lastChartToClone = apDentalChartService.getRecord(resultSet.getString(1));
                        // clone teeth
                        // delete already generated teeth
                        DS.executeQuery("delete from ap_dental_chart_tooth where chart_key = '" + currentChart.getKey() + "'");
                        //fetch teeth from the latest chart
                        List<ApDentalChartTooth> _teeth = apDentalChartToothService.getList("chart_key = '" + lastChartToClone.getKey() + "'");
                        List<ApDentalChartTooth> newTeeth = new ArrayList<>();

                        for (ApDentalChartTooth _tooth : _teeth) {
                            ApDentalChartTooth newTooth = new ApDentalChartTooth();
                            newTooth.setChartKey(currentChart.getKey());
                            newTooth.setToothNumber(_tooth.getToothNumber());
                            newTooth.setToothNumberNumeric(_tooth.getToothNumberNumeric());
                            newTooth.setMissing(_tooth.getMissing());
                            apDentalChartToothService.saveRecord(newTooth);

                            // load old tooth actions and history
                            List<ApToothAction> _toothActions = apToothActionService.getList("tooth_key = '" + _tooth.getKey() + "'");
                            List<ApToothActionLog> _toothActionsLog = apToothActionLogService.getList("tooth_key = '" + _tooth.getKey() + "' order by created_at desc");

                            List<ApToothAction> newActions = new ArrayList<>();
                            List<ApToothActionLog> newActionsLog = new ArrayList<>();

                            for (ApToothAction _toothAction : _toothActions) {
                                ApToothAction newToothAction = new ApToothAction();
                                newToothAction.setToothKey(newTooth.getKey());
                                newToothAction.setActionKey(_toothAction.getActionKey());
                                newToothAction.setNote(_toothAction.getNote());
                                newToothAction.setToothNumber(_toothAction.getToothNumber());
                                newToothAction.setImageName(_toothAction.getImageName());
                                newToothAction.setExisting(_toothAction.getExisting());
                                newToothAction.setSurfaceLkey(_toothAction.getSurfaceLkey());
                                apToothActionService.saveRecord(newToothAction);
                                newActions.add(newToothAction);
                            }

                            for (ApToothActionLog _toothActionLog : _toothActionsLog) {
                                ApToothActionLog newToothActionLog = new ApToothActionLog();
                                newToothActionLog.setToothKey(newTooth.getKey());
                                newToothActionLog.setActionKey(_toothActionLog.getActionKey());
                                newToothActionLog.setLogType(_toothActionLog.getLogType());
                                newToothActionLog.setLogTime(_toothActionLog.getLogTime());
                                newToothActionLog.setLogOwner(_toothActionLog.getLogOwner());
                                apToothActionLogService.saveRecord(newToothActionLog);
                                newActionsLog.add(newToothActionLog);
                            }

                            for (ApToothAction toothAction : newActions) {
                                apToothActionService.populateLovFields(toothAction, lang);
                            }

                            newTooth.setToothActions(newActions);
                            newTooth.setToothHistory(newActionsLog);
                            newTeeth.add(newTooth);
                        }
                        currentChart.setChartTeeth(newTeeth);

                        List<ApDentalChartProgressNote> _progressNotes = apDentalChartProgressNoteService.getList("chart_key = '" + lastChartToClone.getKey() + "' order by created_at desc");
                        List<ApDentalChartProgressNote> newProgressNotes = new ArrayList<>();

                        for (ApDentalChartProgressNote _note : _progressNotes) {
                            ApDentalChartProgressNote newNote = new ApDentalChartProgressNote();
                            newNote.setChartKey(currentChart.getKey());
                            newNote.setNote(_note.getNote());
                            newNote.setCreatedAt(_note.getCreatedAt());
                            newNote.setCreatedBy(_note.getCreatedBy());
                            newNote.setUpdatedAt(_note.getUpdatedAt());
                            newNote.setUpdatedBy(_note.getUpdatedBy());
                            apDentalChartProgressNoteService.saveRecord(newNote);
                            newProgressNotes.add(newNote);
                        }

                        currentChart.setProgressNotes(newProgressNotes);
                    }
                } catch (SQLException ex) {
                    throw ex;
                }

            } else {
                // existing dental chart, fetch and populate
                currentChart = existingDentalChartForEncounterList.get(0);
                List<ApDentalChartTooth> chartTeeth = apDentalChartToothService.getList("chart_key = '" + currentChart.getKey() + "'");
                for (ApDentalChartTooth tooth : chartTeeth) {
                    // list actions on tooth
                    List<ApToothAction> toothActions = apToothActionService.getList("tooth_key = '" + tooth.getKey() + "'");
                    for (ApToothAction toothAction : toothActions) {
                        apToothActionService.populateLovFields(toothAction, lang);
                    }
                    tooth.setToothActions(toothActions);
                    // list actions log on tooth (tooth history)
                    List<ApToothActionLog> actionLog = apToothActionLogService.getList("tooth_key = '" + tooth.getKey() + "' order by created_at desc");
                    tooth.setToothHistory(actionLog);
                    // list services on tooth
                    List<ApToothService> toothServices = apToothServiceService.getList("tooth_key = '" + tooth.getKey() + "'");
                    tooth.setToothServices(toothServices);
                    // list cdt on tooth
                    List<ApToothCdt> toothCdts = apToothCdtService.getList("tooth_key = '" + tooth.getKey() + "'");
                    for (ApToothCdt toothCdt : toothCdts) {
                        apToothCdtService.populateLovFields(toothCdt, lang);
                    }
                    tooth.setToothCdts(toothCdts);
                }
                // list chart progress note
                List<ApDentalChartProgressNote> progressNotes = apDentalChartProgressNoteService.getList(
                        "chart_key = '" + currentChart.getKey() + "' " +
                                " and deleted_at is null order by created_at desc");
                currentChart.setProgressNotes(progressNotes);
                currentChart.setChartTeeth(chartTeeth);
            }

            // load all chart actions
            List<ApToothAction> allChartActions =
                    apToothActionService.getList("tooth_key in (select key from ap_dental_chart_tooth where chart_key = '" + currentChart.getKey() + "')");
            for (ApToothAction toothAction : allChartActions) {
                apToothActionService.populateLovFields(toothAction, lang);
            }
            currentChart.setChartActions(allChartActions);

            // load chartHistory
            List<ApDentalChart> chartHistory = apDentalChartService.getList("key <> '" + currentChart.getKey() + "' and " +
                    "patient_key = '" + currentChart.getPatientKey() + "' order by created_at desc");
            dentalChartResponse.setCurrentChart(currentChart);
            dentalChartResponse.setHistoryCharts(chartHistory);
            response.setObject(dentalChartResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @GetMapping(value = "/fetch-chart-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchChartData(@RequestHeader("chart-key") String chartKey,
                                            @Nullable @RequestHeader String facility_id,
                                            // @Nullable @RequestHeader String access_token,
                                            @Nullable @RequestHeader Integer access_level,
                                            @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDentalChart> response = new ParentResponse<>();

            ApDentalChart chart = apDentalChartService.getRecord(chartKey);

            if (chart == null) {
                response.addGeneralError("Invalid Chart");
                return ResponseEntity.status(400).body(response);
            }

            List<ApDentalChartTooth> chartTeeth = apDentalChartToothService.getList("chart_key = '" + chart.getKey() + "'");

            for (ApDentalChartTooth tooth : chartTeeth) {
                // list actions on tooth
                List<ApToothAction> toothActions = apToothActionService.getList("tooth_key = '" + tooth.getKey() + "'");
                for (ApToothAction toothAction : toothActions) {
                    apToothActionService.populateLovFields(toothAction, lang);
                }
                tooth.setToothActions(toothActions);
                // list actions log on tooth (tooth history)
                List<ApToothActionLog> actionLog = apToothActionLogService.getList("tooth_key = '" + tooth.getKey() + "' order by created_at desc");
                tooth.setToothHistory(actionLog);
                // list services on tooth
                List<ApToothService> toothServices = apToothServiceService.getList("tooth_key = '" + tooth.getKey() + "'");
                tooth.setToothServices(toothServices);
                // list cdt on tooth
                List<ApToothCdt> toothCdts = apToothCdtService.getList("tooth_key = '" + tooth.getKey() + "'");
                for (ApToothCdt toothCdt : toothCdts) {
                    apToothCdtService.populateLovFields(toothCdt, lang);
                }
                tooth.setToothCdts(toothCdts);
            }

            chart.setChartTeeth(chartTeeth);

            // load all chart actions
            List<ApToothAction> allChartActions =
                    apToothActionService.getList("tooth_key in (select key from ap_dental_chart_tooth where chart_key = '" + chart.getKey() + "')");
            for (ApToothAction toothAction : allChartActions) {
                apToothActionService.populateLovFields(toothAction, lang);
            }
            chart.setChartActions(allChartActions);


            response.setObject(chart);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }


    @PostMapping(value = "/modify-tooth-action", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyToothAction(@RequestBody ToothActionRequest request,
                                               @Nullable @RequestHeader String facility_id,
                                               // @Nullable @RequestHeader String access_token,
                                               @Nullable @RequestHeader Integer access_level,
                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDentalChart> response = new ParentResponse<>();

            ApDentalChartTooth tooth = apDentalChartToothService.getRecord(request.getToothKey());
            if (tooth == null) {
                response.addGeneralError("invalid tooth");
                return ResponseEntity.status(400).body(response);
            }
            if (tooth.getMissing()) {
                response.addGeneralError("Cannot modify a missing tooth");
                return ResponseEntity.status(400).body(response);
            }

            ApDentalChart chart = apDentalChartService.getRecord(tooth.getChartKey());


            if (request.getOperation().equals("SAVE")) {
                ApDentalAction actionObject = apDentalActionService.getRecord(request.getActionKey());
                if (actionObject == null) {
                    response.addGeneralError("invalid action");
                    return ResponseEntity.status(400).body(response);
                }

                ApCdt cdtObject = null;
                if (!request.isExisting()
                        && request.getCdtKey() != null
                        && !request.getCdtKey().isBlank()
                        && actionObject.getType().equals("treatment")) {
                    cdtObject = apCdtService.getRecord(request.getCdtKey());
                    if (cdtObject == null) {
                        response.addGeneralError("invalid cdt procedure");
                        return ResponseEntity.status(400).body(response);
                    }
                }

                // check if action already exists on tooth (uniqueness is based on action and surface)
                String checkExistingQuery = "select count(0) from ap_tooth_action where tooth_key = '" +
                        request.getToothKey() + "' and action_key = '" + request.getActionKey() + "' and surface_lkey = '" + request.getSurface() + "'";
                if (request.getKey() != null) {
                    checkExistingQuery += " and key <> '" + request.getKey() + "'";
                }
                BigDecimal res = DS.executeDecimalResultQuery(checkExistingQuery);
                if (res != null && res.intValue() > 0) {
                    response.addGeneralError("Action already applied to this tooth surface");
                    return ResponseEntity.status(400).body(response);
                }

                if (cdtObject != null) {
                    // check if cdt already exists on tooth (uniqueness is based on action and surface)
                    String checkCdtExistingQuery = "select count(0) from ap_tooth_cdt where tooth_key = '" +
                            request.getToothKey() + "' and cdt_key = '" + request.getCdtKey() + "' and surface_lkey = '" + request.getSurface() + "'";
                    BigDecimal res2 = DS.executeDecimalResultQuery(checkCdtExistingQuery);
                    if (res2 != null && res2.intValue() > 0) {
                        response.addGeneralError("CDT already applied to this tooth surface");
                        return ResponseEntity.status(400).body(response);
                    }
                }


                ApToothAction apToothAction = new ApToothAction();
                apToothAction.setKey(request.getKey());
                apToothAction.setActionKey(request.getActionKey());
                apToothAction.setToothKey(request.getToothKey());
                apToothAction.setToothNumber(tooth.getToothNumber());
                apToothAction.setNote(request.getNote());
                apToothAction.setSurfaceLkey(request.getSurface());
                apToothAction.setImageName(actionObject.getImageName());
                apToothAction.setExisting(request.isExisting());
                apToothActionService.saveRecord(apToothAction);

                if (!request.isExisting() && cdtObject != null) {

                    ApToothCdt toothCdt = new ApToothCdt();
                    toothCdt.setCdtKey(request.getCdtKey());
                    toothCdt.setToothKey(request.getToothKey());
                    toothCdt.setSource("DIRECT_TREATMENT");
                    toothCdt.setSourceKey(apToothAction.getKey());
                    toothCdt.setSurfaceLkey(request.getSurface());
                    apToothCdtService.saveRecord(toothCdt);

                    List<ApServiceCdt> linkedCdtServices = apServiceCdtService.getList("cdt_key = '" + request.getCdtKey() + "'");

                    for (ApServiceCdt serviceCdt : linkedCdtServices) {
                        ApService serviceObject = apServiceService.getRecord(serviceCdt.getServiceKey());

                        ApToothService toothService = new ApToothService();
                        toothService.setToothKey(request.getToothKey());
                        toothService.setServiceKey(serviceCdt.getServiceKey());
                        toothService.setSource("DIRECT_TREATMENT");
                        toothService.setSourceKey(apToothAction.getKey());
                        toothService.setPrice(serviceObject.getPrice());
                        apToothServiceService.saveRecord(toothService);

                        ApEncounterAppliedService encounterAppliedService = new ApEncounterAppliedService();
                        encounterAppliedService.setEncounterKey(chart.getEncounterKey());
                        encounterAppliedService.setServiceKey(serviceObject.getKey());
                        encounterAppliedService.setCategoryLkey(serviceObject.getCategoryLkey());
                        encounterAppliedService.setPrice(serviceObject.getPrice());
                        encounterAppliedService.setSource("CDT_TOOTH_SERVICE");
                        encounterAppliedService.setSourceKey(toothService.getKey());
                        encounterAppliedService.setExtraDetails("For Tooth: " + tooth.getToothNumber());
                        encounterAppliedServiceService.saveRecord(encounterAppliedService);
                    }

                    ApDentalPlannedTreatment plannedTreatment = new ApDentalPlannedTreatment();
                    plannedTreatment.setType("CURRENT");
                    plannedTreatment.setEncounterKey(chart.getEncounterKey());
                    plannedTreatment.setPatientKey(chart.getPatientKey());
                    plannedTreatment.setCdtKey(cdtObject.getKey());
                    plannedTreatment.setNote(apToothAction.getNote());
                    plannedTreatment.setDiscount(BigDecimal.ZERO);
                    plannedTreatment.setInsurance(BigDecimal.ZERO);
                    plannedTreatment.setFees(BigDecimal.ZERO);
                    plannedTreatment.setToothKey(tooth.getKey());
                    plannedTreatment.setSurfaceLkey(apToothAction.getSurfaceLkey());
                    plannedTreatment.setSource("DIRECT_TREATMENT");
                    plannedTreatment.setSourceKey(apToothAction.getKey());
                    plannedTreatmentService.saveRecord(plannedTreatment);
                }


                ApToothActionLog actionLog = new ApToothActionLog();
                actionLog.setToothKey(request.getToothKey());
                actionLog.setActionKey(request.getActionKey());
                actionLog.setLogType(request.getKey() == null ? "SAVE" : "CHANGE");
                actionLog.setLogOwner("Administrator"); // TODO change to user derived from access token
                actionLog.setLogTime(BigDecimal.valueOf(System.currentTimeMillis()));
                apToothActionLogService.saveRecord(actionLog);
            } else if (request.getOperation().equals("REMOVE")) {
                int res = DS.executeQuery("delete from ap_tooth_action where key = '" + request.getKey() + "'");
                if (res > 0) {
                    ApToothActionLog actionLog = new ApToothActionLog();
                    actionLog.setToothKey(request.getToothKey());
                    actionLog.setActionKey(request.getActionKey());
                    actionLog.setLogType(request.getOperation());
                    actionLog.setLogOwner("Administrator"); // TODO change to user derived from access token
                    actionLog.setLogTime(BigDecimal.valueOf(System.currentTimeMillis()));
                    apToothActionLogService.saveRecord(actionLog);

                    // delete all encounter applied services from services linked with CDT sourced by this action
                    int subRes0 = DS.executeQuery("delete from ap_encounter_applied_service where source_key in (select ts.key from ap_tooth_service ts" +
                            " where ts.source_key = '" + request.getKey() + "')");

                    // delete all tooth service and tooth CDT sourced from this action
                    int subRes1 = DS.executeQuery("delete from ap_tooth_cdt where source_key = '" + request.getKey() + "'");
                    int subRes2 = DS.executeQuery("delete from ap_tooth_service where source_key = '" + request.getKey() + "'");

                    // delete all planned treatments sourced from tooth CDT sourced from this action
                    int subRes3 = DS.executeQuery("delete from ap_dental_planned_treatment where source_key = '" + request.getKey() + "'");


                }
            }


            // any modifications to chart teeth/actions will return a full record of a fully populated dental chart
            ApDentalChart currentChart = apDentalChartService.getRecord(tooth.getChartKey());
            List<ApDentalChartTooth> chartTeeth = apDentalChartToothService.getList("chart_key = '" + currentChart.getKey() + "'");
            for (ApDentalChartTooth _tooth : chartTeeth) {
                // list actions on tooth
                List<ApToothAction> toothActions = apToothActionService.getList("tooth_key = '" + _tooth.getKey() + "'");
                for (ApToothAction toothAction : toothActions) {
                    apToothActionService.populateLovFields(toothAction, lang);
                }
                _tooth.setToothActions(toothActions);
                // list actions log on tooth (tooth history)
                List<ApToothActionLog> actionLog = apToothActionLogService.getList("tooth_key = '" + _tooth.getKey() + "' order by created_at desc");
                _tooth.setToothHistory(actionLog);
                // list services on tooth
                List<ApToothService> toothServices = apToothServiceService.getList("tooth_key = '" + tooth.getKey() + "'");
                _tooth.setToothServices(toothServices);
                // list cdt on tooth
                List<ApToothCdt> toothCdts = apToothCdtService.getList("tooth_key = '" + tooth.getKey() + "'");
                for (ApToothCdt toothCdt : toothCdts) {
                    apToothCdtService.populateLovFields(toothCdt, lang);
                }
                _tooth.setToothCdts(toothCdts);
            }

            currentChart.setChartTeeth(chartTeeth);

            // load all chart actions
            List<ApToothAction> allChartActions =
                    apToothActionService.getList("tooth_key in (select key from ap_dental_chart_tooth where chart_key = '" + currentChart.getKey() + "')");
            for (ApToothAction toothAction : allChartActions) {
                apToothActionService.populateLovFields(toothAction, lang);
            }
            currentChart.setChartActions(allChartActions);


            response.setObject(currentChart);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @GetMapping(value = "/fetch-treatment-plan", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchTreatmentPlan(@RequestHeader("encounter-key") Long encounterKey,
                                                @Nullable @RequestHeader String facility_id,
                                                // @Nullable @RequestHeader String access_token,
                                                @Nullable @RequestHeader Integer access_level,
                                                @Nullable @RequestHeader String lang) {

        try {
            ParentResponse<DentalTreatmentPlanResponse> response = new ParentResponse<>();
            DentalTreatmentPlanResponse planResponse = new DentalTreatmentPlanResponse();

            PatientEncounter encounter = patientEncounterService.getRecord(encounterKey);
            log.info("ENC ID : " + encounterKey);

            if (encounter == null) {
                response.addGeneralError("Invalid visit");
                return ResponseEntity.status(400).body(response);
            }

            List<ApDentalPlannedTreatment> draftTreatments = plannedTreatmentService.getList("patient_key = '" + encounter.getPatientKey() + "'" +
                    " and deleted_at is null and type = 'DRAFT' order by visit_number");

            List<ApDentalPlannedTreatment> currentVisitTreatments = plannedTreatmentService.getList("encounter_key = '" + encounterKey + "'" +
                    " and deleted_at is null and type = 'CURRENT' order by created_at");

            for (ApDentalPlannedTreatment treatment : currentVisitTreatments) {
                plannedTreatmentService.populateLovFields(treatment, lang);
                treatment.setToothObject(apDentalChartToothService.getRecord(treatment.getToothKey()));
            }

            planResponse.setDraftTreatments(draftTreatments);
            planResponse.setCurrentTreatments(currentVisitTreatments);

            response.setObject(planResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }

    @PostMapping(value = "/save-planned-treatment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePlannedTreatment(@RequestBody ApDentalPlannedTreatment request,
                                                  @Nullable @RequestHeader String facility_id,
                                                  // @Nullable @RequestHeader String access_token,
                                                  @Nullable @RequestHeader Integer access_level,
                                                  @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<DentalTreatmentPlanResponse> response = new ParentResponse<>();
            DentalTreatmentPlanResponse planResponse = new DentalTreatmentPlanResponse();

            PatientEncounter encounter = patientEncounterService.getRecord( Long.valueOf(request.getEncounterKey()));

            if (encounter == null) {
                response.addGeneralError("invalid visit");
                return ResponseEntity.status(400).body(response);
            }

            plannedTreatmentService.saveRecord(request);
            response.setObject(planResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }


    @PostMapping(value = "/delete-planned-treatment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePlannedTreatment(@RequestBody ApDentalPlannedTreatment request,
                                                    @Nullable @RequestHeader String facility_id,
                                                    // @Nullable @RequestHeader String access_token,
                                                    @Nullable @RequestHeader Integer access_level,
                                                    @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<DentalTreatmentPlanResponse> response = new ParentResponse<>();
            DentalTreatmentPlanResponse planResponse = new DentalTreatmentPlanResponse();

            if (request.getKey() == null) {
                response.addGeneralError("planned treatment doesn't exist");
                return ResponseEntity.status(400).body(response);
            }

            if (!request.getType().equals("DRAFT")) {
                response.addGeneralError("Only DRAFT planned treatment can be deleted");
                return ResponseEntity.status(400).body(response);
            }

            plannedTreatmentService.deleteRecord(request);

            response.setObject(planResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }


    @PostMapping(value = "/modify-tooth-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyToothService(@RequestBody ToothServiceRequest request,
                                                @Nullable @RequestHeader String facility_id,
                                                // @Nullable @RequestHeader String access_token,
                                                @Nullable @RequestHeader Integer access_level,
                                                @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDentalChart> response = new ParentResponse<>();

            ApDentalChartTooth tooth = apDentalChartToothService.getRecord(request.getToothKey());
            if (tooth == null) {
                response.addGeneralError("invalid tooth");
                return ResponseEntity.status(400).body(response);
            }
            if (tooth.getMissing()) {
                response.addGeneralError("Cannot modify a missing tooth");
                return ResponseEntity.status(400).body(response);
            }

            ApToothService apToothService = null;
            ApDentalChart currentChart = apDentalChartService.getRecord(tooth.getChartKey());


            ServiceSetupRecord serviceObject = serviceSetupService.getRecord(Long.valueOf(request.getServiceKey()));
            if (serviceObject == null) {
                response.addGeneralError("invalid service");
                return ResponseEntity.status(400).body(response);
            }

            if (request.getOperation().equals("SAVE")) {
                apToothService = new ApToothService();
                apToothService.setKey(request.getKey());
                apToothService.setServiceKey(String.valueOf(request.getServiceKey()));
                apToothService.setToothKey(request.getToothKey());
                apToothService.setSource("MANUAL");
                apToothService.setPrice(serviceObject.getPrice());
                apToothServiceService.saveRecord(apToothService);

                ApEncounterAppliedService encounterAppliedService = new ApEncounterAppliedService();
                encounterAppliedService.setEncounterKey(currentChart.getEncounterKey());
                encounterAppliedService.setServiceKey(String.valueOf(request.getServiceKey()));
                encounterAppliedService.setCategoryLkey(serviceObject.getCategory());
                encounterAppliedService.setPrice(serviceObject.getPrice());
                encounterAppliedService.setSource("MANUAL_TOOTH_SERVICE");
                encounterAppliedService.setSourceKey(apToothService.getKey());
                encounterAppliedService.setExtraDetails("For Tooth: " + tooth.getToothNumber());
                encounterAppliedServiceService.saveRecord(encounterAppliedService);
            } else if (request.getOperation().equals("REMOVE")) {
                int res = DS.executeQuery("delete from ap_tooth_service where tooth_key = '" + request.getToothKey()
                        + "' and service_key = '" + request.getServiceKey() + "'");
                if (res < 0) {
                    response.addGeneralError("service not removed");
                    return ResponseEntity.status(400).body(response);
                }
            }


            // any modifications to chart teeth/actions will return a full record of a fully populated dental chart
            List<ApDentalChartTooth> chartTeeth = apDentalChartToothService.getList("chart_key = '" + currentChart.getKey() + "'");
            for (ApDentalChartTooth _tooth : chartTeeth) {
                // list actions on tooth
                List<ApToothAction> toothActions = apToothActionService.getList("tooth_key = '" + _tooth.getKey() + "'");
                _tooth.setToothActions(toothActions);
                // list actions log on tooth (tooth history)
                List<ApToothActionLog> actionLog = apToothActionLogService.getList("tooth_key = '" + _tooth.getKey() + "' order by created_at desc");
                _tooth.setToothHistory(actionLog);
                // list services on tooth
                List<ApToothService> toothServices = apToothServiceService.getList("tooth_key = '" + tooth.getKey() + "'");
                _tooth.setToothServices(toothServices);
                // list cdt on tooth
                List<ApToothCdt> toothCdts = apToothCdtService.getList("tooth_key = '" + tooth.getKey() + "'");
                for (ApToothCdt toothCdt : toothCdts) {
                    apToothCdtService.populateLovFields(toothCdt, lang);
                }
                _tooth.setToothCdts(toothCdts);
            }

            currentChart.setChartTeeth(chartTeeth);

            // load all chart actions
            List<ApToothAction> allChartActions =
                    apToothActionService.getList("tooth_key in (select key from ap_dental_chart_tooth where chart_key = '" + currentChart.getKey() + "')");
            for (ApToothAction toothAction : allChartActions) {
                apToothActionService.populateLovFields(toothAction, lang);
            }
            currentChart.setChartActions(allChartActions);


            response.setObject(currentChart);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);

        }
    }


    @PostMapping(value = "/save-chart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveChart(@RequestBody ApDentalChart request,
                                       @Nullable @RequestHeader String facility_id,
                                       // @Nullable @RequestHeader String access_token,
                                       @Nullable @RequestHeader Integer access_level,
                                       @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApDentalChart> response = new ParentResponse<>();
            apDentalChartService.saveRecord(request);
            response.setObject(request);
            response.setMsg("Chart saved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-progress-notes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProgressNotes(@RequestBody List<ApDentalChartProgressNote> request,
                                               @Nullable @RequestHeader String facility_id,
                                               // @Nullable @RequestHeader String access_token,
                                               @Nullable @RequestHeader Integer access_level,
                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApDentalChartProgressNote>> response = new ParentResponse<>();
            String chartKey = null;
            for (ApDentalChartProgressNote pn : request) {
                chartKey = pn.getChartKey();
                if (pn.getKey() == null && pn.getNote() != null && !pn.getNote().isBlank()) {
                    pn.setCreatedBy("Administrator"); // TODO change to actual user from access token
                    apDentalChartProgressNoteService.saveRecord(pn);
                } else if (pn.getKey() != null && pn.getNote() != null && !pn.getNote().isBlank()) {
                    // check original note value, only save and mark as updated if change is seen
                    ApDentalChartProgressNote persistedNote = apDentalChartProgressNoteService.getRecord(pn.getKey());
                    if (pn.getNote() != null && !pn.getNote().equals(persistedNote.getNote())) {
                        pn.setUpdatedBy("Administrator"); // TODO change to actual user from access token
                        apDentalChartProgressNoteService.saveRecord(pn);
                    }
                } else if (pn.getKey() != null && (pn.getNote() == null || pn.getNote().isBlank())) {
                    // delete blank note
                    apDentalChartProgressNoteService.deleteRecord(pn);
                }
            }

            List<ApDentalChartProgressNote> updatedList = apDentalChartProgressNoteService.getList("chart_key = '" + chartKey + "' " +
                    " and deleted_at is null order by created_at desc");
            response.setObject(updatedList);
            response.setMsg("Notes saved");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/delete-progress-note", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProgressNote(@RequestBody ApDentalChartProgressNote request,
                                                @Nullable @RequestHeader String facility_id,
                                                // @Nullable @RequestHeader String access_token,
                                                @Nullable @RequestHeader Integer access_level,
                                                @Nullable @RequestHeader String lang) {
        try {
            // TODO add validation that only owner user can delete the note
            ParentResponse<ApDentalChartProgressNote> response = new ParentResponse<>();
            apDentalChartProgressNoteService.deleteRecord(request);
            response.setObject(request);
            response.setMsg("Note saved");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


}
