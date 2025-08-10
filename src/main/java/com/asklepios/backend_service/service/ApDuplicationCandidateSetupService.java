package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.asklepios.backend_service.model.generated.pojo.ApDuplicationCandidateSetup;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApDuplicationCandidateSetupDAO;

@Service
@Slf4j
public class ApDuplicationCandidateSetupService extends ApDuplicationCandidateSetupDAO implements Serializable {

    private static final Map<String, String> candidateToPatientColumn = Map.of(
            "lastName", "last_name",
            "dob", "dob",
            "documentNo", "document_no",
            "mobileNumber", "mobile_number",
            "gender", "gender_lkey"

    );

    public String buildWhereClause(ApDuplicationCandidateSetup candidate, ApPatient sample) {
        List<String> conditions = new ArrayList<>();

        candidateToPatientColumn.forEach((candidateField, patientColumn) -> {
            try {
                Boolean isActive = (Boolean) ApDuplicationCandidateSetup.class
                        .getMethod("get" + capitalize(candidateField))
                        .invoke(candidate);

                if (Boolean.TRUE.equals(isActive)) {
                    Object sampleValue = ApPatient.class
                            .getMethod("get" + capitalize(candidateField))
                            .invoke(sample);

                    if (sampleValue != null && !sampleValue.toString().isEmpty()) {
                        conditions.add(patientColumn + " = '" + sampleValue.toString().replace("'", "''") + "'");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return String.join(" AND ", conditions);
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}