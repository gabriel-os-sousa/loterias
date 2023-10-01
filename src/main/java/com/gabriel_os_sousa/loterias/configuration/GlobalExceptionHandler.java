package com.gabriel_os_sousa.loterias.configuration;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;

import com.gabriel_os_sousa.loterias.model.StandardMessage;
import io.netty.channel.ConnectTimeoutException;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.WriteTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);


  private static final String CONTENT_TYPE = "Content-Type";

  @Autowired
  private DataBufferWriter bufferWriter;

  @Override
  public Mono<Void> handle(final ServerWebExchange exchange, final Throwable ex) {
    LOG.info("An exception was thrown by the webfilter [{}]", ex.getMessage());
    var status = INTERNAL_SERVER_ERROR;

    final StandardMessage standardMessage = new StandardMessage("UnknownError", "Ocorreu um erro desconhecido.");

    if (ex instanceof ReadTimeoutException || ex instanceof WriteTimeoutException || ex instanceof ConnectTimeoutException) {
      standardMessage.updateMessage("TIMEOUT_EXCEPTION", "Ocorreu um erro desconhecido. Por favor, tente novamente.");
      status = REQUEST_TIMEOUT;
    }

    if (exchange.getResponse().isCommitted()) {
      return Mono.error(ex);
    }

    LOG.info("Writing response [{}] for exception.", standardMessage);

    exchange.getResponse().getHeaders().add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    exchange.getResponse().setStatusCode(status);

    return bufferWriter.write(exchange.getResponse(), standardMessage);
  }
}
