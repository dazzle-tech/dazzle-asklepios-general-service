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
import com.asklepios.backend_service.model.generated.pojo.ApIcdCode;
import com.asklepios.backend_service.model.generated.entity.ApIcdCodeEntity;
import com.asklepios.backend_service.database.DS;

@Getter
@Setter
@Slf4j
@Service
public class ApIcdCodeDAO implements Serializable {

@Autowired private PublicServices publicServices;
public ApIcdCode getRecord(String key) throws SQLException {
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_icd_code where key = '"+key+"'");) {
ApIcdCode record = new ApIcdCode();
if(rs.next()){
record.setKey(rs.getString("key"));
record.setIcdVersion(rs.getString("icd_version"));
record.setIcdCode(rs.getString("icd_code"));
record.setDescription(rs.getString("description"));
record.setChapter(rs.getString("chapter"));
record.setBlock(rs.getString("block"));
record.setCategory(rs.getString("category"));
record.setSubcategory(rs.getString("subcategory"));
record.setFulldescription(rs.getString("fulldescription"));
record.setIncludes(rs.getString("includes"));
record.setExcludes1(rs.getString("excludes1"));
record.setExcludes2(rs.getString("excludes2"));
record.setUseadditionalcode(rs.getString("useadditionalcode"));
record.setCodefirst(rs.getString("codefirst"));
record.setCodingguidelines(rs.getString("codingguidelines"));
record.setClinicaldescription(rs.getString("clinicaldescription"));
record.setSeverity(rs.getString("severity"));
record.setSynonyms(rs.getString("synonyms"));
record.setAbbreviations(rs.getString("abbreviations"));
record.setNotes(rs.getString("notes"));
record.setRequireSide(rs.getString("require_side"));
record.setRequireDetails(rs.getString("require_details"));
record.setLinkedWithAge(rs.getString("linked_with_age"));
record.setLinkedWithGender(rs.getString("linked_with_gender"));
record.setLinkedWithDisease(rs.getString("linked_with_disease"));
record.setMoreSpecification(rs.getString("more_specification"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
} else { record = null; }
return record;
}
}
public void updateRecord(ApIcdCode record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_icd_code set key = ?, icd_version = ?, icd_code = ?, description = ?, chapter = ?, block = ?, category = ?, subcategory = ?, fulldescription = ?, includes = ?, excludes1 = ?, excludes2 = ?, useadditionalcode = ?, codefirst = ?, codingguidelines = ?, clinicaldescription = ?, severity = ?, synonyms = ?, abbreviations = ?, notes = ?, require_side = ?, require_details = ?, linked_with_age = ?, linked_with_gender = ?, linked_with_disease = ?, more_specification = ?, created_by = ?, updated_by = ?, deleted_by = ?, created_at = ?, updated_at = ?, deleted_at = ?, is_valid = ? where key = ?");
) {
record.setUpdatedAt(new BigDecimal(System.currentTimeMillis()));
ps.setString(1, record.getKey());
ps.setString(2, record.getIcdVersion());
ps.setString(3, record.getIcdCode());
ps.setString(4, record.getDescription());
ps.setString(5, record.getChapter());
ps.setString(6, record.getBlock());
ps.setString(7, record.getCategory());
ps.setString(8, record.getSubcategory());
ps.setString(9, record.getFulldescription());
ps.setString(10, record.getIncludes());
ps.setString(11, record.getExcludes1());
ps.setString(12, record.getExcludes2());
ps.setString(13, record.getUseadditionalcode());
ps.setString(14, record.getCodefirst());
ps.setString(15, record.getCodingguidelines());
ps.setString(16, record.getClinicaldescription());
ps.setString(17, record.getSeverity());
ps.setString(18, record.getSynonyms());
ps.setString(19, record.getAbbreviations());
ps.setString(20, record.getNotes());
ps.setString(21, record.getRequireSide());
ps.setString(22, record.getRequireDetails());
ps.setString(23, record.getLinkedWithAge());
ps.setString(24, record.getLinkedWithGender());
ps.setString(25, record.getLinkedWithDisease());
ps.setString(26, record.getMoreSpecification());
ps.setString(27, record.getCreatedBy());
ps.setString(28, record.getUpdatedBy());
ps.setString(29, record.getDeletedBy());
ps.setBigDecimal(30, record.getCreatedAt());
ps.setBigDecimal(31, record.getUpdatedAt());
ps.setBigDecimal(32, record.getDeletedAt());
ps.setBoolean(33, record.getIsValid());
ps.setString(34, record.getKey());
ps.executeUpdate();
}
}
public void deleteRecord(ApIcdCode record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("update ap_icd_code set  deleted_at = '"+System.currentTimeMillis()+"' where key = ?");
) {
ps.setString(1, record.getKey());
ps.executeUpdate();
}
}
public List<ApIcdCode> getList(String where) throws SQLException {
if (where == null || where.isEmpty()) where = "1=1";
try (
Connection con = DS.getConnection();
Statement st = con.createStatement();
ResultSet rs = st.executeQuery("select * from ap_icd_code where "+ where);) {
List<ApIcdCode> list = new ArrayList<ApIcdCode>();
while(rs.next()){
ApIcdCode record = new ApIcdCode();
record.setKey(rs.getString("key"));
record.setIcdVersion(rs.getString("icd_version"));
record.setIcdCode(rs.getString("icd_code"));
record.setDescription(rs.getString("description"));
record.setChapter(rs.getString("chapter"));
record.setBlock(rs.getString("block"));
record.setCategory(rs.getString("category"));
record.setSubcategory(rs.getString("subcategory"));
record.setFulldescription(rs.getString("fulldescription"));
record.setIncludes(rs.getString("includes"));
record.setExcludes1(rs.getString("excludes1"));
record.setExcludes2(rs.getString("excludes2"));
record.setUseadditionalcode(rs.getString("useadditionalcode"));
record.setCodefirst(rs.getString("codefirst"));
record.setCodingguidelines(rs.getString("codingguidelines"));
record.setClinicaldescription(rs.getString("clinicaldescription"));
record.setSeverity(rs.getString("severity"));
record.setSynonyms(rs.getString("synonyms"));
record.setAbbreviations(rs.getString("abbreviations"));
record.setNotes(rs.getString("notes"));
record.setRequireSide(rs.getString("require_side"));
record.setRequireDetails(rs.getString("require_details"));
record.setLinkedWithAge(rs.getString("linked_with_age"));
record.setLinkedWithGender(rs.getString("linked_with_gender"));
record.setLinkedWithDisease(rs.getString("linked_with_disease"));
record.setMoreSpecification(rs.getString("more_specification"));
record.setCreatedBy(rs.getString("created_by"));
record.setUpdatedBy(rs.getString("updated_by"));
record.setDeletedBy(rs.getString("deleted_by"));
record.setCreatedAt(rs.getBigDecimal("created_at"));
record.setUpdatedAt(rs.getBigDecimal("updated_at"));
record.setDeletedAt(rs.getBigDecimal("deleted_at"));
record.setIsValid(rs.getBoolean("is_valid"));
list.add(record);
}
return list;
}
}
public String saveRecord(ApIcdCode record) throws SQLException {
try (
Connection con = DS.getConnection();
PreparedStatement ps = con.prepareStatement("insert into ap_icd_code values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
) {
if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}
if (record.getCreatedAt() == null) record.setCreatedAt(new BigDecimal(System.currentTimeMillis()));
String key = "" + System.nanoTime();
record.setKey(key);

ps.setString(1, key);
ps.setString(2, record.getIcdVersion());
ps.setString(3, record.getIcdCode());
ps.setString(4, record.getDescription());
ps.setString(5, record.getChapter());
ps.setString(6, record.getBlock());
ps.setString(7, record.getCategory());
ps.setString(8, record.getSubcategory());
ps.setString(9, record.getFulldescription());
ps.setString(10, record.getIncludes());
ps.setString(11, record.getExcludes1());
ps.setString(12, record.getExcludes2());
ps.setString(13, record.getUseadditionalcode());
ps.setString(14, record.getCodefirst());
ps.setString(15, record.getCodingguidelines());
ps.setString(16, record.getClinicaldescription());
ps.setString(17, record.getSeverity());
ps.setString(18, record.getSynonyms());
ps.setString(19, record.getAbbreviations());
ps.setString(20, record.getNotes());
ps.setString(21, record.getRequireSide());
ps.setString(22, record.getRequireDetails());
ps.setString(23, record.getLinkedWithAge());
ps.setString(24, record.getLinkedWithGender());
ps.setString(25, record.getLinkedWithDisease());
ps.setString(26, record.getMoreSpecification());
ps.setString(27, record.getCreatedBy());
ps.setString(28, record.getUpdatedBy());
ps.setString(29, record.getDeletedBy());
ps.setBigDecimal(30, record.getCreatedAt());
ps.setBigDecimal(31, record.getUpdatedAt());
ps.setBigDecimal(32, record.getDeletedAt());
ps.setBoolean(33, record.getIsValid());
ps.executeUpdate();
return key;
}
}
public void populateLovFields(ApIcdCodeEntity entity, String lang) {
        Class<?> myClass = ApIcdCodeEntity.class;
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
public void translateObject(ApIcdCodeEntity entity, String lang) {
        ApIcdCodeEntity translated = (ApIcdCodeEntity) publicServices.getObjectTranslation(entity.getKey(), entity, lang);
        entity.setTranslatedObject(translated);
    }

}