package com.asklepios.backend_service.model.pojo.response;

import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticOrderTestsResult;
import com.asklepios.backend_service.model.generated.pojo.ApDiagnosticTest;

import java.util.List;

public class GroupedTestResult {
    private ApDiagnosticTest test;
    private List<ApDiagnosticOrderTestsResult> results;


    public GroupedTestResult(ApDiagnosticTest test, List<ApDiagnosticOrderTestsResult> results) {
        this.test = test;
        this.results = results;
    }

    public GroupedTestResult() {
    }

    public ApDiagnosticTest getTest() {
        return test;
    }

    public void setTest(ApDiagnosticTest test) {
        this.test = test;
    }

    public List<ApDiagnosticOrderTestsResult> getResults() {
        return results;
    }

    public void setResults(List<ApDiagnosticOrderTestsResult> results) {
        this.results = results;
    }
}
