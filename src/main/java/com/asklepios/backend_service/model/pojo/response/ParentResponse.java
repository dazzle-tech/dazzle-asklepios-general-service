package com.asklepios.backend_service.model.pojo.response;

import com.asklepios.backend_service.model.generated.pojo.ApTranslation;
import com.asklepios.backend_service.model.pojo.ValidationResult;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ParentResponse<T> {
    T object;
    String msg;
    boolean error;
    List<ParentError> errors;
    List<ApTranslation> translations; // TODO replace with actual structure of translations of fields
    String extraString;
    BigDecimal extraNumeric;
    ValidationResult validationResult;

    public void addError(String errorMessage) {
        error = true;

        if (errors == null)
            errors = new ArrayList<>();

        errors.add(new ParentError(null, errorMessage));
    }

    public void addError(String fieldId, String errorMessage) {
        error = true;

        if (errors == null)
            errors = new ArrayList<>();

        errors.add(new ParentError(fieldId, errorMessage));
    }

    public void addGeneralError(String errorMessage) {
        error = true;
        msg = errorMessage;
    }

    public void setData(T t){
        setObject(t);
    }
}
