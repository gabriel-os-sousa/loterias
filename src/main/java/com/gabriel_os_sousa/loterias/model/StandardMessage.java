package com.gabriel_os_sousa.loterias.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class StandardMessage implements Serializable {
  private static final long serialVersionUID = 1L;

  private String error;

  private String errorDescription;


  public StandardMessage(String error, String errorDescription) {
    this.error = error;
    this.errorDescription = errorDescription;
  }

  @Deprecated
  public StandardMessage() {
  }

  public String getError() {
    return error;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void updateMessage(String error, String errorDescription) {
    this.error = error;
    this.errorDescription = errorDescription;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("error", error)
        .append("error_description", errorDescription)
        .toString();
  }
}