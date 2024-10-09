package com.asklepios.backend_service.model.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ValidationResult {
    String type;
    Map<String, List<ValidationResultDetail>> details;

    public ValidationResult() {
        details = new HashMap<>();
    }

    public boolean isPass() {
        return type == null || !isError();
    }

    public boolean isError() {
        return type != null && type.equals("ERROR");
    }

    public String getGeneralMessage() {
        String gm = "";
        for (Map.Entry<String, List<ValidationResultDetail>> fieldEntry : details.entrySet()) {
            gm += "[" + fieldEntry.getKey() + "]: ";
            for (ValidationResultDetail detail : fieldEntry.getValue()) {
                gm += detail.getMessage();
                break;
            }
            gm += "\n";
        }
        return gm;
    }
}
