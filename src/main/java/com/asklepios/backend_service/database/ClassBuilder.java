package com.asklepios.backend_service.database;

import java.io.*;

public class ClassBuilder implements Serializable {
    StringBuilder content = new StringBuilder();
    String className;

    public void openClass(String packageName, String className, String[] imports, String[] annotations, String parentClassName) {
        this.className = className;

        appendLine("package ".concat(packageName).concat(";"), 2);

        appendLine("import java.io.Serializable;");
        for (String importLine : imports) {
            appendLine("import ".concat(importLine).concat(";"));
        }
        appendLine();

        for (String annotation : annotations) {
            appendLine("@".concat(annotation));
        }

        if (parentClassName == null) {
            appendLine("public class ".concat(className).concat(" implements Serializable {"));
        } else {
            appendLine("public class ".concat(className).concat(" extends ").concat(parentClassName).concat(" implements Serializable {"));
        }
        appendLine();
    }

    public void declareAttribute(String dataType, String attributeName, boolean jsonIgnore) {
//        if (jsonIgnore)
//            appendTabbedLine("@JsonIgnore");
        if (dataType.equals("byte[]")) {
//            appendTabbedLine("@JsonIgnore");
        }
        String declaration = "private ".concat(dataType).concat(" ").concat(attributeName);
        if (dataType.equals("Boolean") && !attributeName.equals("isValid")) {
            declaration = declaration.concat(" = false");
        } else if (dataType.equals("Boolean") && attributeName.equals("isValid")) {
            declaration = declaration.concat(" = true");
        }
        if (dataType.equals("Date")) {
            declaration = declaration.concat(" = new Date()");
        }
        declaration = declaration.concat(";");
        appendTabbedLine(declaration);
    }

    public void declareClassInstance(String className, String name) {
        appendLine(className.concat(" ").concat(name).concat(" = new ").concat(className).concat("();"));
    }

    public void declareListOfClassInstance(String className, String name) {
        appendLine("List<".concat(className).concat(">").concat(" ").concat(name).concat(" = new ArrayList<").concat(className).concat(">();"));
    }

    public void addPreparedStatementSetter(int index, String dataType, String attributeName) {
        if (dataType.equals("Integer"))
            dataType = "Int";

        if (dataType.equals("byte[]"))
            dataType = "Bytes";

        if (dataType.equals("Date")) {
            append("if (record.get" + attributeName + "() != null) ");
            appendLine("ps.set".concat(dataType).concat("(" + index + ", new java.sql.Date(").concat("record.get".concat(attributeName).concat("().getTime()));")));
            append("else ");
            appendLine("ps.set".concat(dataType).concat("(" + index + ", null); "));
        } else {
            appendLine("ps.set".concat(dataType).concat("(" + index + ", ").concat("record.get".concat(attributeName).concat("());")));
        }

    }

    public void addAuditTimestamp(String field, String condition) {
        if (condition == null)
            appendLine("record.set".concat(field).concat("(new BigDecimal(System.currentTimeMillis()));"));
        else
            appendLine("if (" + condition + ") record.set".concat(field).concat("(new BigDecimal(System.currentTimeMillis()));"));

    }

    public void addSetterFromResultSet(String recordName, String dataType, String attributeName, String columnName) {
        append(recordName.concat(".set".concat(attributeName).concat("(")));

        if (dataType.equals("Date")) {
//            append("new java.util.Date(");
        }

        if (dataType.equals("Integer"))
            dataType = "Int";


        if (dataType.equals("byte[]")) {
            dataType = "Bytes";
        }

        append("rs.get".concat(dataType).concat("(\"").concat(columnName).concat("\")"));

        if (dataType.equals("Date")) {
//            append(".getTime())");
        }

        appendLine(");");
    }

    public void closeClass() {
        appendLine();
        append("}");
    }

    public void writeFile(String path) throws IOException {
        File file = new File(path.concat(className).concat(".java"));
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(content.toString());
        writer.close();
        fileWriter.close();
    }

    public void append(String string) {
        content.append(string);
    }

    public void appendLine() {
        append("\n");
    }

    public void appendTab() {
        append("\t");
    }

    public void appendLine(String string) {
        append(string);
        appendLine();
    }

    public void appendLine(String string, int lines) {
        append(string);
        for (int i = 0; i < lines; i++)
            appendLine();
    }

    public void appendTabbedLine(String string) {
        appendTab();
        appendLine(string);
    }

    public void appendTabbedLine(String string, int tabs) {
        for (int i = 0; i < tabs; i++)
            appendTab();
        appendLine(string);
    }
}
