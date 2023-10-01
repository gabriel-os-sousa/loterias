package com.gabriel_os_sousa.loterias.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends LoteriasException {

  public ResourceNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND.value(), "RESOURCE_NOT_FOUND", message);
  }
}
