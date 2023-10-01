package com.gabriel_os_sousa.loterias.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends DomainException {

  public ResourceNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND.value(), "resource_not_found", message);
  }
}
