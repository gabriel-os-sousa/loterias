package com.gabriel_os_sousa.loterias.repository;

import com.gabriel_os_sousa.loterias.model.mongo.LotofacilEntity;
import java.util.Set;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LotofacilRepository extends ReactiveMongoRepository<LotofacilEntity, Long> {
  Mono<LotofacilEntity> findFirstByDezenas(Set<Integer> dezenas);
}
