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
    private T object;
    private String msg;
    private boolean success;
    private List<ParentError> errors;
    private List<ApTranslation> translations;
    private String extraString;
    private BigDecimal extraNumeric;
    private ValidationResult validationResult;
    private String errorCode;
    private int statusCode;

    public void addGeneralError(String errorMessage) {
        success = false;
        msg = errorMessage;
    }

    public void addError(String fieldId, String errorMessage) {
        success = false;

        if (errors == null) {
            errors = new ArrayList<>();
        }

        errors.add(new ParentError(fieldId, errorMessage));
    }

    public void setData(T t) {
        setObject(t); // تأكد من أن الدالة setObject موجودة إذا كنت تستخدم "object" كاسم
    }
}
