package com.gabriel_os_sousa.loterias.repository;

import com.gabriel_os_sousa.loterias.model.Lotofacil;
import java.util.Set;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface LotofacilRepository extends ReactiveMongoRepository<Lotofacil, Long> {
  Mono<Lotofacil> findFirstByDezenas(Set<Integer> dezenas);
}
