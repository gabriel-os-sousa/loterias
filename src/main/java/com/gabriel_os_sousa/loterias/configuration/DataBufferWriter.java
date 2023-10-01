package com.gabriel_os_sousa.loterias.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel_os_sousa.loterias.model.StandardMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DataBufferWriter {
  private static final Logger LOG = LoggerFactory.getLogger(DataBufferWriter.class);

  private final ObjectMapper objectMapper;

  @Autowired
  public DataBufferWriter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Mono<Void> write(ServerHttpResponse httpResponse, StandardMessage object) {
    return httpResponse
        .writeWith(Mono.fromSupplier(() -> {
          DataBufferFactory bufferFactory = httpResponse.bufferFactory();
          try {
            return bufferFactory.wrap(objectMapper.writeValueAsBytes(object));
          } catch (Exception e) {
            LOG.info("Error Writing response. [{}] [{}]", e.getClass(), e.getMessage());
            return bufferFactory.wrap(new byte[0]);
          }
        }));
  }
}
