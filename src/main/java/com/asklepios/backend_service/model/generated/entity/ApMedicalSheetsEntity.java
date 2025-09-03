package com.asklepios.backend_service.model.generated.entity;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;

@Getter
@Setter
@Slf4j
public class ApMedicalSheetsEntity implements Serializable {

	private String key;
	private String departmentKey;
	private String facilityKey;
	private Boolean patientDashboard = false;
	private Boolean clinicalVisit = false;
	private Boolean diagnosticsOrder = false;
	private Boolean prescription = false;
	private Boolean drugOrder = false;
	private Boolean consultation = false;
	private Boolean procedures = false;
	private Boolean patientHistory = false;
	private Boolean allergies = false;
	private Boolean medicalWarnings = false;
	private Boolean medicationsRecord = false;
	private Boolean psychologicalExam = false;
	private Boolean audiometryPuretone = false;
	private Boolean optometricExam = false;
	private Boolean vaccineReccord = false;
	private Boolean diagnosticsResult = false;
	private Boolean dentalCare = false;
	private Boolean cardiology = false;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private BigDecimal createdAt;
	private BigDecimal updatedAt;
	private BigDecimal deletedAt;
	private Boolean isValid = true;
	private Boolean observation = false;
	private Boolean vaccination = false;
	private Boolean operationRequests = false;
	private Boolean doctorRound = false;
	private Boolean dayCase = false;
	private Boolean bedsideProceduresRequest = false;
	private Boolean referralRequest = false;
	private Boolean bloodOrder = false;
	private Boolean ivFluidOrder = false;
	private Boolean intakeOutputBalance = false;
	private Boolean riskAssessments = false;
	private Boolean multidisciplinaryTeamNotes = false;
	private Boolean nutritionStateAssessment = false;
	private Boolean physicianOrderSummary = false;
	private Boolean carePlanAndGoals = false;
	private Boolean dischargePlanning = false;
	private Boolean pregnancyFollowUp = false;
	private Boolean morseFallScale = false;
	private Boolean hendrichFallRisk = false;
	private Boolean stratifyScale = false;
	private Boolean johnsHopkinsFallRiskAssessmentTool = false;
	private Boolean bradenScaleForPressureUlcer = false;
	private Boolean glasgowComaScale = false;
	private Boolean vteRiskAssessment = false;
	private Boolean progressNotes = false;
	private Boolean ivFluidAdministration = false;
	private Boolean dietaryRequest = false;
	private Boolean pediatric = false;
	private Boolean gynecology = false;
	private Boolean speechTherapy = false;
	private Boolean rehabilitationPlan = false;
	private Boolean occupationalTherapy = false;
	private Boolean physiotherapyPlan = false;
	private Boolean medicationAdministrationRecord = false;
	private Boolean continuousObservations = false;
	private Boolean dialysisRequest = false;
	private Boolean slidingScale = false;
	private Boolean pointOfCareCests = false;
	private Boolean hospitalCourse = false;
	private Boolean childGrowth = false;
	private Boolean flaccNeonatesPainAssessment = false;
	private Boolean universalPainAssessment = false;
	private Boolean patientRestraint = false;
	private Boolean infectionControl = false;
	private Boolean sofa = false;
	private Boolean medicalCalculators = false;
	private Boolean cpoeResultsManager = false;
	private ApMedicalSheetsEntity translatedObject;

}