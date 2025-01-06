package com.personalproject.studentinformationportal.exception;

public class StudentNotFoundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int statusCode;
  private String statusMessage;

  
  public StudentNotFoundException() {
    super();
  }
  
  /**
   * Parameterised constructor of CustomException
   * @param statusCode Exception status code 
   * @param statusMessage Detailed message with reason
   */
  public StudentNotFoundException(int statusCode, String statusMessage) {
      super(statusMessage);
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
  }
}
