package com.personalproject.studentinformationportal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteResponse {
    private String successMessage;
    private String failedMessage;

    public DeleteResponse(String successMessage, String failedMessage) {
        this.successMessage = successMessage;
        this.failedMessage = failedMessage;
    }
}
