package com.personalproject.studentinformationportal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResponse {
    private String successMessage;
    private String failedMessage;
    private StudentInfo studentInfo;

    public UpdateResponse() {
    }
}
