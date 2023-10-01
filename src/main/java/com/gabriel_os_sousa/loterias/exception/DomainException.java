package com.gabriel_os_sousa.loterias.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DomainException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private int httpStatus;

  private String error;

  public DomainException(int httpStatus, String error, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.error = error;
  }

  public DomainException(int httpStatus, String error, String message, Throwable cause) {
    super(message, cause);
    this.httpStatus = httpStatus;
    this.error = error;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

  public String getError() {
    return error;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("httpStatus", httpStatus)
        .append("error", error)
        .append("message", this.getMessage())
        .append("exception", this.getCause())
        .toString();
  }
}