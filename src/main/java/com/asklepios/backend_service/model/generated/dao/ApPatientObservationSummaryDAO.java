package com.asklepios.backend_service.model.generated.dao;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.lang.System;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.asklepios.backend_service.controller.PublicServices;
import java.lang.reflect.Field;
import com.asklepios.backend_service.model.generated.pojo.ApPatientObservationSummary;
import com.asklepios.backend_service.model.generated.entity.ApPatientObservationSummaryEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApPatientObservationSummaryDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApPatientObservationSummary getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_observation_summary where key = '"+key+"'");) {
ApPatientObservationSummary record = new ApPatientObservationSummary();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setLastDate(rs.getDate("last_date"));
record.setLatesttemperature(rs.getBigDecimal("latesttemperature"));
record.setLatestbpSystolic(rs.getBigDecimal("latestbp_systolic"));
record.setLatestbpDiastolic(rs.getBigDecimal("latestbp_diastolic"));
record.setLatestheartrate(rs.getBigDecimal("latestheartrate"));
record.setLatestrespiratoryrate(rs.getBigDecimal("latestrespiratoryrate"));
record.setLatestoxygensaturation(rs.getBigDecimal("latestoxygensaturation"));
record.setLatestglucoselevel(rs.getBigDecimal("latestglucoselevel"));
record.setLatestpainlevelLkey(rs.getString("latestpainlevel_lkey"));
record.setLatestweight(rs.getBigDecimal("latestweight"));
record.setLatestheight(rs.getBigDecimal("latestheight"));
record.setLatestheadcircumference(rs.getBigDecimal("latestheadcircumference"));
record.setLatestlength(rs.getBigDecimal("latestlength"));
record.setLatestbmi(rs.getBigDecimal("latestbmi"));
record.setAge(rs.getBigDecimal("age"));
record.setPrevRecordKey(rs.getString("prev_record_key"));
record.setPlastDate(rs.getDate("plast_date"));
record.setPlatesttemperature(rs.getBigDecimal("platesttemperature"));
record.setPlatestbpSystolic(rs.getBigDecimal("platestbp_systolic"));
record.setPlatestbpDiastolic(rs.getBigDecimal("platestbp_diastolic"));
record.setPlatestheartrate(rs.getBigDecimal("platestheartrate"));
record.setPlatestrespiratoryrate(rs.getBigDecimal("platestrespiratoryrate"));
record.setPlatestoxygensaturation(rs.getBigDecimal("platestoxygensaturation"));
record.setPlatestglucoselevel(rs.getBigDecimal("platestglucoselevel"));
record.setPlatestpainlevelLkey(rs.getString("platestpainlevel_lkey"));
record.setPlatestweight(rs.getBigDecimal("platestweight"));
record.setPlatestheight(rs.getBigDecimal("platestheight"));
record.setPlatestheadcircumference(rs.getBigDecimal("platestheadcircumference"));
record.setPlatestlength(rs.getBigDecimal("platestlength"));
record.setPlatestbmi(rs.getBigDecimal("platestbmi"));
record.setPage(rs.getBigDecimal("page"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setLatestnotes(rs.getString("latestnotes"));
record.setPlatestnotes(rs.getString("platestnotes"));
record.setLatestpaindescription(rs.getString("latestpaindescription"));
record.setPlatestpaindescription(rs.getString("platestpaindescription"));
record.setLatestpainlevel(rs.getBigDecimal("latestpainlevel"));
record.setPlatestpainlevel(rs.getBigDecimal("platestpainlevel"));
record.setPlatesthearingtest(rs.getString("platesthearingtest"));
record.setLatesthearingtest(rs.getString("latesthearingtest"));
record.setLatestDehydration(rs.getBoolean("latest_dehydration"));
record.setPlatestDehydration(rs.getBoolean("platest_dehydration"));
record.setLatestNasalFlaring(rs.getBoolean("latest_nasal_flaring"));
record.setPlatestNasalFlaring(rs.getBoolean("platest_nasal_flaring"));
record.setLatestResponseToLight(rs.getBoolean("latest_response_to_light"));
record.setPlatestResponseToLight(rs.getBoolean("platest_response_to_light"));
record.setLatestPupilResponse(rs.getBoolean("latest_pupil_response"));
record.setPlatestPupilResponse(rs.getBoolean("platest_pupil_response"));
record.setLatestAbilityToFollowTarget(rs.getBoolean("latest_ability_to_follow_target"));
record.setPlatestAbilityToFollowTarget(rs.getBoolean("platest_ability_to_follow_target"));
record.setLatestColorTesting(rs.getBoolean("latest_color_testing"));
record.setPlatestColorTesting(rs.getBoolean("platest_color_testing"));
record.setLatestFallRisk(rs.getBoolean("latest_fall_risk"));
record.setPlatestFallRisk(rs.getBoolean("platest_fall_risk"));
record.setLatestFallRiskDetails(rs.getString("latest_fall_risk_details"));
record.setPlatestFallRiskDetails(rs.getString("platest_fall_risk_details"));
record.setLatestActionToTake(rs.getString("latest_action_to_take"));
record.setPlatestActionToTake(rs.getString("platest_action_to_take"));
record.setLatestFunctionalStatus(rs.getString("latest_functional_status"));
record.setPlatestFunctionalStatus(rs.getString("platest_functional_status"));
record.setLatestCognitiveCheck(rs.getString("latest_cognitive_check"));
record.setPlatestCognitiveCheck(rs.getString("platest_cognitive_check"));
record.setReasonOfVisit(rs.getString("reason_of_visit"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApPatientObservationSummary record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_observation_summary set key = ?, patient_key = ?, visit_key = ?, last_date = ?, latesttemperature = ?, latestbp_systolic = ?, latestbp_diastolic = ?, latestheartrate = ?, latestrespiratoryrate = ?, latestoxygensaturation = ?, latestglucoselevel = ?, latestpainlevel_lkey = ?, latestweight = ?, latestheight = ?, latestheadcircumference = ?, latestlength = ?, latestbmi = ?, age = ?, prev_record_key = ?, plast_date = ?, platesttemperature = ?, platestbp_systolic = ?, platestbp_diastolic = ?, platestheartrate = ?, platestrespiratoryrate = ?, platestoxygensaturation = ?, platestglucoselevel = ?, platestpainlevel_lkey = ?, platestweight = ?, platestheight = ?, platestheadcircumference = ?, platestlength = ?, platestbmi = ?, page = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ?, latestnotes = ?, platestnotes = ?, latestpaindescription = ?, platestpaindescription = ?, latestpainlevel = ?, platestpainlevel = ?, platesthearingtest = ?, latesthearingtest = ?, latest_dehydration = ?, platest_dehydration = ?, latest_nasal_flaring = ?, platest_nasal_flaring = ?, latest_response_to_light = ?, platest_response_to_light = ?, latest_pupil_response = ?, platest_pupil_response = ?, latest_ability_to_follow_target = ?, platest_ability_to_follow_target = ?, latest_color_testing = ?, platest_color_testing = ?, latest_fall_risk = ?, platest_fall_risk = ?, latest_fall_risk_details = ?, platest_fall_risk_details = ?, latest_action_to_take = ?, platest_action_to_take = ?, latest_functional_status = ?, platest_functional_status = ?, latest_cognitive_check = ?, platest_cognitive_check = ? , reason_of_visit = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
if (record.getLastDate() != null) ps.setDate(4, new java.sql.Date(record.getLastDate().getTime()));
else ps.setDate(4, null);
ps.setBigDecimal(5, record.getLatesttemperature());
ps.setBigDecimal(6, record.getLatestbpSystolic());
ps.setBigDecimal(7, record.getLatestbpDiastolic());
ps.setBigDecimal(8, record.getLatestheartrate());
ps.setBigDecimal(9, record.getLatestrespiratoryrate());
ps.setBigDecimal(10, record.getLatestoxygensaturation());
ps.setBigDecimal(11, record.getLatestglucoselevel());
ps.setString(12, record.getLatestpainlevelLkey());
ps.setBigDecimal(13, record.getLatestweight());
ps.setBigDecimal(14, record.getLatestheight());
ps.setBigDecimal(15, record.getLatestheadcircumference());
ps.setBigDecimal(16, record.getLatestlength());
ps.setBigDecimal(17, record.getLatestbmi());
ps.setBigDecimal(18, record.getAge());
ps.setString(19, record.getPrevRecordKey());
if (record.getPlastDate() != null) ps.setDate(20, new java.sql.Date(record.getPlastDate().getTime()));
else ps.setDate(20, null);
ps.setBigDecimal(21, record.getPlatesttemperature());
ps.setBigDecimal(22, record.getPlatestbpSystolic());
ps.setBigDecimal(23, record.getPlatestbpDiastolic());
ps.setBigDecimal(24, record.getPlatestheartrate());
ps.setBigDecimal(25, record.getPlatestrespiratoryrate());
ps.setBigDecimal(26, record.getPlatestoxygensaturation());
ps.setBigDecimal(27, record.getPlatestglucoselevel());
ps.setString(28, record.getPlatestpainlevelLkey());
ps.setBigDecimal(29, record.getPlatestweight());
ps.setBigDecimal(30, record.getPlatestheight());
ps.setBigDecimal(31, record.getPlatestheadcircumference());
ps.setBigDecimal(32, record.getPlatestlength());
ps.setBigDecimal(33, record.getPlatestbmi());
ps.setBigDecimal(34, record.getPage());
ps.setString(35, record.getCreatedBy());
ps.setString(36, record.getUpdatedBy());
ps.setString(37, record.getDeletedBy());
ps.setBigDecimal(38, record.getCreatedAt());
ps.setBigDecimal(39, record.getUpdatedAt());
ps.setBigDecimal(40, record.getDeletedAt());
ps.setBoolean(41, record.getIsValid());
ps.setString(42, record.getLatestnotes());
ps.setString(43, record.getPlatestnotes());
ps.setString(44, record.getLatestpaindescription());
ps.setString(45, record.getPlatestpaindescription());
ps.setBigDecimal(46, record.getLatestpainlevel());
ps.setBigDecimal(47, record.getPlatestpainlevel());
ps.setString(48, record.getPlatesthearingtest());
ps.setString(49, record.getLatesthearingtest());
ps.setBoolean(50, record.getLatestDehydration());
ps.setBoolean(51, record.getPlatestDehydration());
ps.setBoolean(52, record.getLatestNasalFlaring());
ps.setBoolean(53, record.getPlatestNasalFlaring());
ps.setBoolean(54, record.getLatestResponseToLight());
ps.setBoolean(55, record.getPlatestResponseToLight());
ps.setBoolean(56, record.getLatestPupilResponse());
ps.setBoolean(57, record.getPlatestPupilResponse());
ps.setBoolean(58, record.getLatestAbilityToFollowTarget());
ps.setBoolean(59, record.getPlatestAbilityToFollowTarget());
ps.setBoolean(60, record.getLatestColorTesting());
ps.setBoolean(61, record.getPlatestColorTesting());
ps.setBoolean(62, record.getLatestFallRisk());
ps.setBoolean(63, record.getPlatestFallRisk());
ps.setString(64, record.getLatestFallRiskDetails());
ps.setString(65, record.getPlatestFallRiskDetails());
ps.setString(66, record.getLatestActionToTake());
ps.setString(67, record.getPlatestActionToTake());
ps.setString(68, record.getLatestFunctionalStatus());
ps.setString(69, record.getPlatestFunctionalStatus());
ps.setString(70, record.getLatestCognitiveCheck());
ps.setString(71, record.getPlatestCognitiveCheck());
ps.setString(72, record.getReasonOfVisit());
ps.setString(73, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApPatientObservationSummary record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_patient_observation_summary set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApPatientObservationSummary> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_patient_observation_summary where "+ where);) {
List<ApPatientObservationSummary> list = new ArrayList<ApPatientObservationSummary>();
while(rs.next()){
ApPatientObservationSummary record = new ApPatientObservationSummary();
record.setKey(rs.getString("key"));
record.setPatientKey(rs.getString("patient_key"));
record.setVisitKey(rs.getString("visit_key"));
record.setLastDate(rs.getDate("last_date"));
record.setLatesttemperature(rs.getBigDecimal("latesttemperature"));
record.setLatestbpSystolic(rs.getBigDecimal("latestbp_systolic"));
record.setLatestbpDiastolic(rs.getBigDecimal("latestbp_diastolic"));
record.setLatestheartrate(rs.getBigDecimal("latestheartrate"));
record.setLatestrespiratoryrate(rs.getBigDecimal("latestrespiratoryrate"));
record.setLatestoxygensaturation(rs.getBigDecimal("latestoxygensaturation"));
record.setLatestglucoselevel(rs.getBigDecimal("latestglucoselevel"));
record.setLatestpainlevelLkey(rs.getString("latestpainlevel_lkey"));
record.setLatestweight(rs.getBigDecimal("latestweight"));
record.setLatestheight(rs.getBigDecimal("latestheight"));
record.setLatestheadcircumference(rs.getBigDecimal("latestheadcircumference"));
record.setLatestlength(rs.getBigDecimal("latestlength"));
record.setLatestbmi(rs.getBigDecimal("latestbmi"));
record.setAge(rs.getBigDecimal("age"));
record.setPrevRecordKey(rs.getString("prev_record_key"));
record.setPlastDate(rs.getDate("plast_date"));
record.setPlatesttemperature(rs.getBigDecimal("platesttemperature"));
record.setPlatestbpSystolic(rs.getBigDecimal("platestbp_systolic"));
record.setPlatestbpDiastolic(rs.getBigDecimal("platestbp_diastolic"));
record.setPlatestheartrate(rs.getBigDecimal("platestheartrate"));
record.setPlatestrespiratoryrate(rs.getBigDecimal("platestrespiratoryrate"));
record.setPlatestoxygensaturation(rs.getBigDecimal("platestoxygensaturation"));
record.setPlatestglucoselevel(rs.getBigDecimal("platestglucoselevel"));
record.setPlatestpainlevelLkey(rs.getString("platestpainlevel_lkey"));
record.setPlatestweight(rs.getBigDecimal("platestweight"));
record.setPlatestheight(rs.getBigDecimal("platestheight"));
record.setPlatestheadcircumference(rs.getBigDecimal("platestheadcircumference"));
record.setPlatestlength(rs.getBigDecimal("platestlength"));
record.setPlatestbmi(rs.getBigDecimal("platestbmi"));
record.setPage(rs.getBigDecimal("page"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
record.setLatestnotes(rs.getString("latestnotes"));
record.setPlatestnotes(rs.getString("platestnotes"));
record.setLatestpaindescription(rs.getString("latestpaindescription"));
record.setPlatestpaindescription(rs.getString("platestpaindescription"));
record.setLatestpainlevel(rs.getBigDecimal("latestpainlevel"));
record.setPlatestpainlevel(rs.getBigDecimal("platestpainlevel"));
record.setPlatesthearingtest(rs.getString("platesthearingtest"));
record.setLatesthearingtest(rs.getString("latesthearingtest"));
record.setLatestDehydration(rs.getBoolean("latest_dehydration"));
record.setPlatestDehydration(rs.getBoolean("platest_dehydration"));
record.setLatestNasalFlaring(rs.getBoolean("latest_nasal_flaring"));
record.setPlatestNasalFlaring(rs.getBoolean("platest_nasal_flaring"));
record.setLatestResponseToLight(rs.getBoolean("latest_response_to_light"));
record.setPlatestResponseToLight(rs.getBoolean("platest_response_to_light"));
record.setLatestPupilResponse(rs.getBoolean("latest_pupil_response"));
record.setPlatestPupilResponse(rs.getBoolean("platest_pupil_response"));
record.setLatestAbilityToFollowTarget(rs.getBoolean("latest_ability_to_follow_target"));
record.setPlatestAbilityToFollowTarget(rs.getBoolean("platest_ability_to_follow_target"));
record.setLatestColorTesting(rs.getBoolean("latest_color_testing"));
record.setPlatestColorTesting(rs.getBoolean("platest_color_testing"));
record.setLatestFallRisk(rs.getBoolean("latest_fall_risk"));
record.setPlatestFallRisk(rs.getBoolean("platest_fall_risk"));
record.setLatestFallRiskDetails(rs.getString("latest_fall_risk_details"));
record.setPlatestFallRiskDetails(rs.getString("platest_fall_risk_details"));
record.setLatestActionToTake(rs.getString("latest_action_to_take"));
record.setPlatestActionToTake(rs.getString("platest_action_to_take"));
record.setLatestFunctionalStatus(rs.getString("latest_functional_status"));
record.setPlatestFunctionalStatus(rs.getString("platest_functional_status"));
record.setLatestCognitiveCheck(rs.getString("latest_cognitive_check"));
record.setPlatestCognitiveCheck(rs.getString("platest_cognitive_check"));
record.setReasonOfVisit(rs.getString("reason_of_visit"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApPatientObservationSummary record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_patient_observation_summary values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getPatientKey());
ps.setString(3, record.getVisitKey());
if (record.getLastDate() != null) ps.setDate(4, new java.sql.Date(record.getLastDate().getTime()));
else ps.setDate(4, null);
ps.setBigDecimal(5, record.getLatesttemperature());
ps.setBigDecimal(6, record.getLatestbpSystolic());
ps.setBigDecimal(7, record.getLatestbpDiastolic());
ps.setBigDecimal(8, record.getLatestheartrate());
ps.setBigDecimal(9, record.getLatestrespiratoryrate());
ps.setBigDecimal(10, record.getLatestoxygensaturation());
ps.setBigDecimal(11, record.getLatestglucoselevel());
ps.setString(12, record.getLatestpainlevelLkey());
ps.setBigDecimal(13, record.getLatestweight());
ps.setBigDecimal(14, record.getLatestheight());
ps.setBigDecimal(15, record.getLatestheadcircumference());
ps.setBigDecimal(16, record.getLatestlength());
ps.setBigDecimal(17, record.getLatestbmi());
ps.setBigDecimal(18, record.getAge());
ps.setString(19, record.getPrevRecordKey());
if (record.getPlastDate() != null) ps.setDate(20, new java.sql.Date(record.getPlastDate().getTime()));
else ps.setDate(20, null);
ps.setBigDecimal(21, record.getPlatesttemperature());
ps.setBigDecimal(22, record.getPlatestbpSystolic());
ps.setBigDecimal(23, record.getPlatestbpDiastolic());
ps.setBigDecimal(24, record.getPlatestheartrate());
ps.setBigDecimal(25, record.getPlatestrespiratoryrate());
ps.setBigDecimal(26, record.getPlatestoxygensaturation());
ps.setBigDecimal(27, record.getPlatestglucoselevel());
ps.setString(28, record.getPlatestpainlevelLkey());
ps.setBigDecimal(29, record.getPlatestweight());
ps.setBigDecimal(30, record.getPlatestheight());
ps.setBigDecimal(31, record.getPlatestheadcircumference());
ps.setBigDecimal(32, record.getPlatestlength());
ps.setBigDecimal(33, record.getPlatestbmi());
ps.setBigDecimal(34, record.getPage());
ps.setString(35, record.getCreatedBy());
ps.setString(36, record.getUpdatedBy());
ps.setString(37, record.getDeletedBy());
ps.setBigDecimal(38, record.getCreatedAt());
ps.setBigDecimal(39, record.getUpdatedAt());
ps.setBigDecimal(40, record.getDeletedAt());
ps.setBoolean(41, record.getIsValid());
ps.setString(42, record.getLatestnotes());
ps.setString(43, record.getPlatestnotes());
ps.setString(44, record.getLatestpaindescription());
ps.setString(45, record.getPlatestpaindescription());
ps.setBigDecimal(46, record.getLatestpainlevel());
ps.setBigDecimal(47, record.getPlatestpainlevel());
ps.setString(48, record.getPlatesthearingtest());
ps.setString(49, record.getLatesthearingtest());
ps.setBoolean(50, record.getLatestDehydration());
ps.setBoolean(51, record.getPlatestDehydration());
ps.setBoolean(52, record.getLatestNasalFlaring());
ps.setBoolean(53, record.getPlatestNasalFlaring());
ps.setBoolean(54, record.getLatestResponseToLight());
ps.setBoolean(55, record.getPlatestResponseToLight());
ps.setBoolean(56, record.getLatestPupilResponse());
ps.setBoolean(57, record.getPlatestPupilResponse());
ps.setBoolean(58, record.getLatestAbilityToFollowTarget());
ps.setBoolean(59, record.getPlatestAbilityToFollowTarget());
ps.setBoolean(60, record.getLatestColorTesting());
ps.setBoolean(61, record.getPlatestColorTesting());
ps.setBoolean(62, record.getLatestFallRisk());
ps.setBoolean(63, record.getPlatestFallRisk());
ps.setString(64, record.getLatestFallRiskDetails());
ps.setString(65, record.getPlatestFallRiskDetails());
ps.setString(66, record.getLatestActionToTake());
ps.setString(67, record.getPlatestActionToTake());
ps.setString(68, record.getLatestFunctionalStatus());
ps.setString(69, record.getPlatestFunctionalStatus());
ps.setString(70, record.getLatestCognitiveCheck());
ps.setString(71, record.getPlatestCognitiveCheck());
ps.setString(72, record.getReasonOfVisit());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApPatientObservationSummaryEntity entity, String lang) {
        Class<?> myClass = ApPatientObservationSummaryEntity.class;
        Field[] fields = myClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.contains("Lkey")) {
                try {
                    Object fieldValue = field.get(entity);
                    if (fieldValue != null) {
                        String _lovKey = fieldValue.toString();
                        Field valueField = myClass.getDeclaredField (fieldName.replaceAll("Lkey", "Lvalue"));
                        valueField.setAccessible(true);
                        if (lang == null) {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey));
                        } else {
                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey,lang));
                        }                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
public void translateObject(ApPatientObservationSummaryEntity entity, String lang) {
        ApPatientObservationSummaryEntity translated = (ApPatientObservationSummaryEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}