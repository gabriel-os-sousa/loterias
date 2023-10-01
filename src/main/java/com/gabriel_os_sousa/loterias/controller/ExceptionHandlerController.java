package com.gabriel_os_sousa.loterias.controller;


import com.gabriel_os_sousa.loterias.exception.DomainException;
import com.gabriel_os_sousa.loterias.model.StandardMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExceptionHandlerController {
  public static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

  @ExceptionHandler(DomainException.class)
  public Mono<ResponseEntity<StandardMessage>> handleAmeException(DomainException e, ServerWebExchange exchange) {
    LOG.info("Ocorreu uma excecao. [{}]", e.getMessage());

    return Mono.just(ResponseEntity.status(e.getHttpStatus())
        .body(new StandardMessage(e.getError(), e.getMessage())));
  }
}
