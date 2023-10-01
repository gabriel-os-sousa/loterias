package com.gabriel_os_sousa.loterias.controller;


import com.gabriel_os_sousa.loterias.exception.LoteriasException;
import com.gabriel_os_sousa.loterias.model.InvalidRequestResponse;
import com.gabriel_os_sousa.loterias.model.StandardMessage;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandlerController {
  public static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

  @ExceptionHandler(LoteriasException.class)
  public Mono<ResponseEntity<StandardMessage>> handleAmeException(LoteriasException e, ServerWebExchange exchange) {
    LOG.info("Ocorreu uma excecao. [{}]", e.getMessage());

    return Mono.just(ResponseEntity.status(e.getHttpStatus())
        .body(new StandardMessage(e.getError(), e.getMessage())));
  }

  @ExceptionHandler({BindException.class, WebExchangeBindException.class, ConstraintViolationException.class})
  public Mono<ResponseEntity<InvalidRequestResponse>> handleValidationException(Throwable e) {
    LOG.warn("Ocorreu uma exceção de validação: [{}] [{}]", e.getCause(), e.getMessage());

    Map<String, String> fields = new HashMap<>();

    if (e instanceof BindingResult result) {
      result.getFieldErrors().forEach(error -> fields.put(error.getField(), error.getDefaultMessage()));
    } else if (e instanceof ConstraintViolationException violation) {
      violation.getConstraintViolations().forEach(error -> fields.put(error.getPropertyPath().toString(), error.getMessage()));
    }

    var fieldsMessage = String.join(", ", fields.keySet());

    return Mono.just(ResponseEntity.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new InvalidRequestResponse(HttpStatus.BAD_REQUEST.value(), "INVALID_REQUEST", String.format("Campos inválidos: %s", fieldsMessage),
            fields)));
  }


  // TODO: Remover esse handler genérico
  @ExceptionHandler(Exception.class)
  public Mono<ResponseEntity<StandardMessage>> handleGenericException(Exception e) {
    LOG.warn("Ocorreu uma exceção de validação: [{}] [{}]", e.getCause(), e.getMessage());
    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new StandardMessage("UNKNWON_ERROR", e.getMessage())));
  }

}
