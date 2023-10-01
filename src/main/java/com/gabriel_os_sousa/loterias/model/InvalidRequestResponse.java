package com.gabriel_os_sousa.loterias.model;

import java.util.Map;

public class InvalidRequestResponse {

  private Integer httpStatusCode;

  private String errorCode;

  private String message;

  private Map<String, String> fields;

  public InvalidRequestResponse(Integer httpStatusCode, String errorCode, String message, Map<String, String> fields) {
    this.httpStatusCode = httpStatusCode;
    this.errorCode = errorCode;
    this.message = message;
    this.fields = fields;
  }

  public Integer getHttpStatusCode() {
    return httpStatusCode;
  }

  public void setHttpStatusCode(Integer httpStatusCode) {
    this.httpStatusCode = httpStatusCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, String> getFields() {
    return fields;
  }

  public void setFields(Map<String, String> fields) {
    this.fields = fields;
  }
}