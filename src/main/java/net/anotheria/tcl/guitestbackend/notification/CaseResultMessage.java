package net.anotheria.tcl.guitestbackend.notification;

import net.anotheria.tcl.guitestbackend.TestCases;

public class CaseResultMessage {

    private String userName;
    private TestCases testCase;
    private boolean success;
    private String status;

    public CaseResultMessage(String userName, TestCases testCase, boolean success) {
        this.userName = userName;
        this.testCase = testCase;
        this.success = success;
        this.status = success ? "Successful" : "Failed";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TestCases getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCases testCase) {
        this.testCase = testCase;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }
}
