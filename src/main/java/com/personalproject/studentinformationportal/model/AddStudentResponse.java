package com.personalproject.studentinformationportal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudentResponse {
    private String successMessage;
    private String failedMessage;
    private StudentInfo studentInfo;

    public AddStudentResponse(String successMessage, String failedMessage, StudentInfo studentInfo) {
        this.successMessage = successMessage;
        this.failedMessage = failedMessage;
        this.studentInfo = studentInfo;
    }

    public AddStudentResponse() {

    }
}
