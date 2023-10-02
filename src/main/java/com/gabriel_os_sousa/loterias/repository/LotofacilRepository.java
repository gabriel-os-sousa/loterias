package com.gabriel_os_sousa.loterias.repository;

import com.gabriel_os_sousa.loterias.model.mongo.LotofacilItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LotofacilRepository extends ReactiveMongoRepository<LotofacilItem, Long> {
  Mono<LotofacilItem> findByNumero(String numero);
}
