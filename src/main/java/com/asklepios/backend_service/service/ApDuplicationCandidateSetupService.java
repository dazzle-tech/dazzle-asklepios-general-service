package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.lang.reflect.Method;
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


                Method candidateGetter = ApDuplicationCandidateSetup.class.getMethod("get" + capitalize(candidateField));
                Boolean isActive = (Boolean) candidateGetter.invoke(candidate);



                if (Boolean.TRUE.equals(isActive)) {
                    Method sampleGetter = ApPatient.class.getMethod("get" + capitalize(candidateField));
                    Object sampleValue = sampleGetter.invoke(sample);



                    if (sampleValue != null && !sampleValue.toString().isEmpty()) {
                        String condition = patientColumn + " = '" + sampleValue.toString().replace("'", "''") + "'";
                        System.out.println("Adding condition: " + condition);
                        conditions.add(condition);
                    } else {

                    }
                } else {
                    System.out.println("Field not active for matching: " + candidateField);
                }
            } catch (NullPointerException npe) {
                System.err.println("NullPointerException at field: " + candidateField);
                npe.printStackTrace();
            } catch (Exception e) {
                System.err.println("Exception at field: " + candidateField);
                e.printStackTrace();
            }
        });

        String whereClause = String.join(" AND ", conditions);
        System.out.println("Final where clause: " + whereClause);
        return whereClause;
    }


    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}