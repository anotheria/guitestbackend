package net.anotheria.tcl.guitestbackend;

public enum TestCases {
    REGISTRATION_REGISTRATION("Registration"),
    REGISTRATION_EMAIL("Registration email"),
    REGISTRATION_CONFIRMATION("Registration confirmation"),
    REGISTRATION_MEMBER_PAGE("Registration member page");

    private String caseName;
    private static final String CASE_NAME_END = " test case";

    TestCases(String registration) {
        this.caseName = registration + CASE_NAME_END;
    }

    public String getCaseName() {
        return caseName;
    }
}
