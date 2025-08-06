package com.asklepios.backend_service.database;


import com.asklepios.backend_service.model.generated.dao.ApMetadataDAO;
import com.asklepios.backend_service.model.generated.dao.ApMetadataFieldDAO;
import com.asklepios.backend_service.model.generated.pojo.ApMetadata;
import com.asklepios.backend_service.model.generated.pojo.ApMetadataField;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelGenerator {
    private final static String SchemaPrefix = "";
    private final static String BasePackage = "com.asklepios.backend_service";
    private final static String ProjectPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\asklepios\\backend_service\\";
    private final static String EntityFolder = ProjectPath + "model\\generated\\entity\\";
    private final static String DaoFolder = ProjectPath + "model\\generated\\dao\\";
    private final static String PojoFolder = ProjectPath + "model\\generated\\pojo\\";
    private final static String ServiceFolder = ProjectPath + "service\\";
//    private final static String ModelTypesFilePath = "C:\\Work\\Work\\Nafiz\\asklepios-ui\\src\\types\\model-types.ts";
//    private final static String ModelTypesConstructorFilePath = "C:\\Work\\Work\\Nafiz\\asklepios-ui\\src\\types\\model-types-constructor.ts";
private final static String ModelTypesFilePath = "C:\\Users\\user\\Documents\\GitHub\\dazzle-asklepios-ui\\src\\types\\model-types.ts";
    private final static String ModelTypesConstructorFilePath = "C:\\Users\\user\\Documents\\GitHub\\dazzle-asklepios-ui\\src\\types\\model-types-constructor.ts";
    private final static String[] tablesToExclude = new String[]{
            // This matches as 'contains'
            "CX",
    };

    public static void main(String[] args) throws Exception {
        try (Connection con = DS.getConnection()) {


            StringBuilder modelTypesFileContent = new StringBuilder();
            StringBuilder modelTypesConstructorFileContent = new StringBuilder();
            modelTypesConstructorFileContent.append("import * as modelTypes from \"./model-types\";\n");

            ArrayList<String> tableNames = new ArrayList<>();
            ArrayList<String> objectNames = new ArrayList<>();

            DatabaseMetaData md = con.getMetaData();
            ResultSet rss = md.getTables(null, null, TableGenerator.TablePrefix + "_%", new String[]{"TABLE"});
            ApMetadataDAO metadataDAO = new ApMetadataDAO();
            ApMetadataFieldDAO metadataFieldDAO = new ApMetadataFieldDAO();

            big:
            while (rss.next()) {
                String tableName = rss.getString(3);


                System.out.println(EntityFolder);

                for (String excludedTableKeyWord : tablesToExclude) {
                    if (tableName.contains(excludedTableKeyWord))
                        continue big;
                }

                String[] objNameBefore = tableName.split("_");
                String JavaClassName = "";

                for (String tableNamePart : objNameBefore) {
                    JavaClassName += Character.toUpperCase(tableNamePart.charAt(0)) + tableNamePart.substring(1);
                }

                tableNames.add(rss.getString(3));
                objectNames.add(JavaClassName);
                System.out.println(tableName);
            }

            rss.close();

            for (int j = 0; j < tableNames.size(); j++) {

                try (
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("select * from " + SchemaPrefix + tableNames.get(j) + " limit 1");
                ) {
                    ResultSetMetaData rsmd = rs.getMetaData();

                    // generate [entity] class
                    String entityClassName = objectNames.get(j) + "Entity";
                    modelTypesFileContent.append("export interface ").append(objectNames.get(j)).append(" { \n");
                    modelTypesConstructorFileContent.append("export const new").append(objectNames.get(j)).append(":modelTypes.").append(objectNames.get(j)).append(" = { \n");

                    String tableName = tableNames.get(j);
//                    List<ApMetadata> exists = metadataDAO.getList("db_object_name = '" + tableName + "'");
//                    ApMetadata metadata = null;
//                    if (exists == null || exists.isEmpty()) {
//                        metadata = new ApMetadata();
//                        metadata.setObjectName(tableName);
//                        metadata.setDbObjectName(tableName);
//                        metadataDAO.saveRecord(metadata);
//                    } else {
//                        //exists already
//                        metadata = exists.get(0);
//                    }

                    ClassBuilder entityClass = new ClassBuilder();
                    String[] entityImports = new String[]{
                            "lombok.extern.slf4j.Slf4j",
                            "lombok.Getter",
                            "lombok.Setter",
                            "java.util.Date",
                            "com.fasterxml.jackson.annotation.JsonIgnore",
                            "java.math.BigDecimal",
                            "com.asklepios.backend_service.model.generated.pojo.ApLovValues"
                    };

                    String[] entityAnnotations = new String[]{
                            "Getter",
                            "Setter",
                            "Slf4j"
                    };

                    entityClass.openClass(BasePackage.concat(".model.generated.entity"), entityClassName, entityImports, entityAnnotations, null);

                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnDbType = rsmd.getColumnTypeName(i);
                        String javaDataType = fetchJavaDataType(columnDbType.toLowerCase());
                        String columnName = fixColumnName(rsmd.getColumnName(i).toLowerCase(), true);
                        entityClass.declareAttribute(javaDataType, columnName, isAudit(rsmd.getColumnName(i).toLowerCase()));
                        if (columnName.contains("Lkey")) {
                            String newColumnName = columnName.replaceAll("Lkey", "Lvalue");
                            entityClass.declareAttribute("ApLovValues", newColumnName, false);
                        }

                        String actualColumnName = rsmd.getColumnName(i).toLowerCase();
                        BigDecimal metadataFieldExists = DS.executeDecimalResultQuery("select count(0) from ap_metadata_field" +
                                "  where db_object_name = '" + tableName + "' and db_field_name = '" + actualColumnName + "'");
//                        List<ApMetadataField> fieldExists = metadataFieldDAO.getList("db_object_name = '" + tableName + "' and db_field_name = '" + actualColumnName + "'");
//                        ApMetadataField metadataField = null;
//                        if (fieldExists == null || fieldExists.isEmpty()) {
//                            // create new
//                            metadataField = new ApMetadataField();
//                            metadataField.setMetadataKey(metadata.getKey());
//                            metadataField.setDbObjectName(tableName);
//                            metadataField.setDbFieldName(actualColumnName);
//                            metadataField.setFieldName(actualColumnName);
//                            metadataField.setDataType(columnDbType);
//                            metadataFieldDAO.saveRecord(metadataField);
//                        } else {
//                            // exists
//                            metadataField = fieldExists.get(0);
//                        }


                        String tsDataType = javaDataType;
                        if (tsDataType.equals("BigDecimal"))
                            tsDataType = "number";

                        if (tsDataType.equals("byte[]"))
                            tsDataType = "Uint8Array";

                        if (!tsDataType.equals("Date"))
                            tsDataType = tsDataType.toLowerCase();

                        modelTypesFileContent.append("\t").append(columnName).append(":").append(tsDataType).append(";\n");
                        modelTypesConstructorFileContent.append("\t").append(columnName).append(":");

                        if (javaDataType.equals("BigDecimal") && !isAudit(rsmd.getColumnName(i).toLowerCase())) {
                            modelTypesConstructorFileContent.append("0,\n");
                        } else if (javaDataType.equals("Date")) {
                            modelTypesConstructorFileContent.append("null,\n");
                        } else if (javaDataType.equals("byte[]")) {
                            modelTypesConstructorFileContent.append("new Uint8Array(),\n");
                        } else if (javaDataType.equals("String") && !rsmd.getColumnName(i).toLowerCase().contains("key")) {
                            modelTypesConstructorFileContent.append("'',\n");
                        } else {
                            modelTypesConstructorFileContent.append("undefined,\n");
                        }

                    }

                    entityClass.declareAttribute(entityClassName, "translatedObject", false);


                    entityClass.closeClass();
                    entityClass.writeFile(EntityFolder);
                    modelTypesFileContent.append("} \n\n");
                    modelTypesConstructorFileContent.append("} \n\n");

                    FileWriter fw = new FileWriter(ModelTypesFilePath);
                    fw.write(modelTypesFileContent.toString());
                    fw.close();

                    FileWriter fw2 = new FileWriter(ModelTypesConstructorFilePath);
                    fw2.write(modelTypesConstructorFileContent.toString());
                    fw2.close();


                    // generate [pojo] class
                    String pojoClassName = objectNames.get(j);
                    ClassBuilder pojoClass = new ClassBuilder();
                    String[] pojoImports = new String[]{
                            "lombok.extern.slf4j.Slf4j",
                            "lombok.Getter",
                            "lombok.Setter",
                            BasePackage.concat(".model.generated.entity.").concat(entityClassName)
                    };
                    String[] pojoAnnotations = new String[]{
                            "Getter",
                            "Setter",
                            "Slf4j"
                    };

                    pojoClass.openClass(BasePackage.concat(".model.generated.pojo"), pojoClassName, pojoImports, pojoAnnotations, entityClassName);
                    pojoClass.closeClass();
                    File pojoFileExistCheck = new File(PojoFolder.concat(pojoClassName).concat(".java"));
                    if (!pojoFileExistCheck.exists())
                        pojoClass.writeFile(PojoFolder);

                    // generate [dao] class
                    String daoClassName = objectNames.get(j) + "DAO";
                    ClassBuilder daoClass = new ClassBuilder();
                    String[] daoImports = new String[]{
                            "lombok.extern.slf4j.Slf4j",
                            "lombok.Getter",
                            "lombok.Setter",
                            "java.sql.Date",
                            "java.sql.Connection",
                            "java.sql.PreparedStatement",
                            "java.sql.Statement",
                            "java.sql.ResultSet",
                            "java.sql.SQLException",
                            "java.util.List",
                            "java.util.UUID",
                            "java.util.ArrayList",
                            "java.lang.System",
                            "java.math.BigDecimal",
                            "org.springframework.stereotype.Service",
                            "org.springframework.beans.factory.annotation.Autowired",
                            "com.asklepios.backend_service.controller.PublicServices",
                            "java.lang.reflect.Field",
                            BasePackage.concat(".model.generated.pojo.").concat(pojoClassName),
                            BasePackage.concat(".model.generated.entity.").concat(entityClassName),
                            BasePackage.concat(".database.DS")
                    };
                    String[] daoAnnotations = new String[]{
                            "Getter",
                            "Setter",
                            "Slf4j",
                            "Service"
                    };

                    daoClass.openClass(BasePackage.concat(".model.generated.dao"), daoClassName, daoImports, daoAnnotations, null);
                    daoClass.appendLine("@Autowired private PublicServices publicServices;");

                    // [getRecord]
                    daoClass.appendLine("public " + pojoClassName + " getRecord(String key) throws SQLException {");
                    daoClass.appendLine("try (");
                    daoClass.appendLine("Connection con = DS.getConnection();");
                    daoClass.appendLine("Statement st = con.createStatement();");
                    daoClass.append("ResultSet rs = st.executeQuery(\"");
                    daoClass.append("select * from " + SchemaPrefix + tableNames.get(j) + " where " + rsmd.getColumnName(1) + " = '\"+key+\"'");
                    daoClass.append("\");");
                    daoClass.appendLine(") {");
                    daoClass.declareClassInstance(pojoClassName, "record");

                    daoClass.appendLine("if(rs.next()){");
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnDbType = rsmd.getColumnTypeName(i);
                        String javaDataType = fetchJavaDataType(columnDbType.toLowerCase());
                        String columnName = rsmd.getColumnName(i).toLowerCase();
                        String attributeName = fixColumnName(columnName, false);
                        daoClass.addSetterFromResultSet("record", javaDataType, attributeName, columnName);
                    }
                    daoClass.appendLine("} else { record = null; }");

                    daoClass.appendLine("return record;");
                    daoClass.appendLine("}");
                    daoClass.appendLine("}");

                    // [updateRecord]
                    daoClass.appendLine("public void updateRecord(" + pojoClassName + " record) throws SQLException {");
                    daoClass.appendLine("try (");
                    daoClass.appendLine("Connection con = DS.getConnection();");
                    daoClass.append("PreparedStatement ps = con.prepareStatement(\"update " + SchemaPrefix.concat(tableNames.get(j)) + " set ");

                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        daoClass.append(rsmd.getColumnName(i) + " = ?");
                        if (i != rsmd.getColumnCount()) {
                            daoClass.append(", ");
                        }
                    }

                    daoClass.append(" where " + rsmd.getColumnName(1) + " = ?");
                    daoClass.appendLine("\");");

                    daoClass.appendLine(") {");
                    daoClass.addAuditTimestamp("UpdatedAt", null);

                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnDbType = rsmd.getColumnTypeName(i);
                        String javaDataType = fetchJavaDataType(columnDbType.toLowerCase());
                        String columnName = fixColumnName(rsmd.getColumnName(i).toLowerCase(), false);
                        daoClass.addPreparedStatementSetter(i, javaDataType, columnName);
                    }

                    String keyDataType = fetchJavaDataType(rsmd.getColumnTypeName(1).toLowerCase());
                    daoClass.addPreparedStatementSetter(rsmd.getColumnCount() + 1, keyDataType, fixColumnName(rsmd.getColumnName(1), false));

                    daoClass.appendLine("ps.executeUpdate();");

                    daoClass.appendLine("}");
                    daoClass.appendLine("}");


                    // [deleteRecord]
                    daoClass.appendLine("public void deleteRecord(" + pojoClassName + " record) throws SQLException {");
                    daoClass.appendLine("try (");
                    daoClass.appendLine("Connection con = DS.getConnection();");
                    daoClass.append("PreparedStatement ps = con.prepareStatement(\"update " + SchemaPrefix.concat(tableNames.get(j)) + " set ");
                    daoClass.append(" deleted_at = '\"+System.currentTimeMillis()+\"'");
                    daoClass.append(" where " + rsmd.getColumnName(1) + " = ?");
                    daoClass.appendLine("\");");

                    daoClass.appendLine(") {");


                    String keyDataTypeDel = fetchJavaDataType(rsmd.getColumnTypeName(1).toLowerCase());
                    daoClass.addPreparedStatementSetter(1, keyDataTypeDel, fixColumnName(rsmd.getColumnName(1), false));

                    daoClass.appendLine("ps.executeUpdate();");

                    daoClass.appendLine("}");
                    daoClass.appendLine("}");


                    // [getList]
                    daoClass.appendLine("public List<" + pojoClassName + "> getList(String where) throws SQLException {");
                    daoClass.appendLine("if (where == null || where.isEmpty()) where = \"1=1\";");
                    daoClass.appendLine("try (");
                    daoClass.appendLine("Connection con = DS.getConnection();");
                    daoClass.appendLine("Statement st = con.createStatement();");
                    daoClass.append("ResultSet rs = st.executeQuery(\"");
                    daoClass.append("select * from " + SchemaPrefix + tableNames.get(j) + " where \"+ where);");
                    daoClass.appendLine(") {");
                    daoClass.declareListOfClassInstance(pojoClassName, "list");


                    daoClass.appendLine("while(rs.next()){");
                    daoClass.declareClassInstance(pojoClassName, "record");
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnDbType = rsmd.getColumnTypeName(i);
                        String javaDataType = fetchJavaDataType(columnDbType.toLowerCase());
                        String columnName = rsmd.getColumnName(i).toLowerCase();
                        String attributeName = fixColumnName(columnName, false);
                        daoClass.addSetterFromResultSet("record", javaDataType, attributeName, columnName);
                    }
                    daoClass.appendLine("list.add(record);");
                    daoClass.appendLine("}");

                    daoClass.appendLine("return list;");
                    daoClass.appendLine("}");
                    daoClass.appendLine("}");

                    // [insertRecord]
                    daoClass.appendLine("public String saveRecord(" + pojoClassName + " record) throws SQLException {");
                    daoClass.appendLine("try (");
                    daoClass.appendLine("Connection con = DS.getConnection();");
                    daoClass.append("PreparedStatement ps = con.prepareStatement(\"insert into " + SchemaPrefix.concat(tableNames.get(j)) + " values (");

                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        daoClass.append("?");
                        if (i != rsmd.getColumnCount()) {
                            daoClass.append(", ");
                        }
                    }
                    daoClass.append(")");

                    daoClass.appendLine("\");");

                    daoClass.appendLine(") {");
                    daoClass.appendLine("if(record.getKey() != null && !record.getKey().isEmpty()) {updateRecord(record); return record.getKey();}");
                    daoClass.addAuditTimestamp("CreatedAt", "record.getCreatedAt() == null");

                    daoClass.appendLine("String key = \"\" + System.nanoTime();");
                    daoClass.appendLine("record.setKey(key);", 2);

                    daoClass.appendLine("ps.setString(1, key);");
                    for (int i = 2; i <= rsmd.getColumnCount(); i++) {
                        String columnDbType = rsmd.getColumnTypeName(i);
                        String javaDataType = fetchJavaDataType(columnDbType.toLowerCase());
                        String columnName = fixColumnName(rsmd.getColumnName(i).toLowerCase(), false);
                        daoClass.addPreparedStatementSetter(i, javaDataType, columnName);
                    }
                    daoClass.appendLine("ps.executeUpdate();");

                    daoClass.appendLine("return key;");
                    daoClass.appendLine("}");
                    daoClass.appendLine("}");

                    // [populate LOV values from redis cache]
                    daoClass.appendLine("public void populateLovFields(" + entityClassName + " entity, String lang) {\n" +
                            "        Class<?> myClass = " + entityClassName + ".class;\n" +
                            "        Field[] fields = myClass.getDeclaredFields();\n" +
                            "        for (Field field : fields) {\n" +
                            "            field.setAccessible(true);\n" +
                            "            String fieldName = field.getName();\n" +
                            "            if (fieldName.contains(\"Lkey\")) {\n" +
                            "                try {\n" +
                            "                    Object fieldValue = field.get(entity);\n" +
                            "                    if (fieldValue != null) {\n" +
                            "                        String _lovKey = fieldValue.toString();\n" +
                            "                        Field valueField = myClass.getDeclaredField (fieldName.replaceAll(\"Lkey\", \"Lvalue\"));\n" +
                            "                        valueField.setAccessible(true);\n" +
                            "                        if (lang == null) {\n" +
                            "                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey));\n" +
                            "                        } else {\n" +
                            "                            valueField.set(entity, publicServices.getFromRedisLovValue(_lovKey,lang));\n" +
                            "                        }" +
                            "                    }\n" +
                            "                } catch (Exception e) {\n" +
                            "                    e.printStackTrace();\n" +
                            "                }\n" +
                            "            }\n" +
                            "        }\n" +
                            "    }");

                    // trasnlate object
                    daoClass.appendLine("public void translateObject(" + entityClassName + " entity, String lang) {\n" +
                            "        " + entityClassName + " translated = (" + entityClassName + ") publicServices.getObjectTranslation(entity.getKey(), entity, lang);\n" +
                            "        entity.setTranslatedObject(translated);\n" +
                            "    }");

                    daoClass.closeClass();
                    daoClass.writeFile(DaoFolder);


                    // generate [service] class
                    String serviceClassName = objectNames.get(j).concat("Service");
                    File file = new File(ServiceFolder.concat(serviceClassName).concat(".java"));
                    if (!file.exists()) {
                        ClassBuilder serviceClass = new ClassBuilder();
                        String[] serviceImports = new String[]{
                                "lombok.extern.slf4j.Slf4j",
                                "org.springframework.stereotype.Service",
                                BasePackage.concat(".model.generated.dao.").concat(daoClassName)
                        };
                        String[] serviceAnnotations = new String[]{
                                "Service",
                                "Slf4j"
                        };
                        serviceClass.openClass(BasePackage.concat(".service"), serviceClassName, serviceImports, serviceAnnotations, daoClassName);
                        serviceClass.closeClass();
                        serviceClass.writeFile(ServiceFolder);
                    }

                }
            }
        }
    }

    private static boolean isAudit(String columnName) {
        switch (columnName) {
            case "created_at":
            case "created_by":
            case "updated_at":
            case "updated_by":
            case "deleted_at":
            case "deleted_by":
            case "tenant_id":
            case "enabled":
                return true;
        }
        return false;
    }

    private static String fixColumnName(String originalDbColumnName, boolean camelCase) {
        String columnName = originalDbColumnName.toLowerCase();
        String[] columnNameParts = columnName.split("_");
        columnName = "";
        for (String str : columnNameParts) {
            columnName += str.substring(0, 1).toUpperCase();
            columnName += str.substring(1);
        }

        if (camelCase) {
            String newColumnName = columnName.substring(0, 1).toLowerCase();
            columnName = newColumnName + columnName.substring(1);
        }

        return columnName;
    }

    public static String fetchJavaDataType(String columnDbType) {
        String javaType = "";
        switch (columnDbType) {
            case "numeric":
            case "decimal":
            case "bigint":
                return "BigDecimal";
            case "bit":
            case "bool":
            case "boolean":
                return "Boolean";
            case "int":
                return "Integer";
            case "date":
            case "datetime":
            case "time":
                return "Date";
            case "nvarchar":
            case "varchar":
            case "ntext":
            case "text":
                return "String";
            case "bytea":
                return "byte[]";
        }
        return javaType;
    }
}
