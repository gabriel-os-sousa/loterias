package com.gabriel_os_sousa.loterias.service;

import com.gabriel_os_sousa.loterias.exception.ResourceNotFoundException;
import com.gabriel_os_sousa.loterias.model.Lotofacil;
import com.gabriel_os_sousa.loterias.model.request.LotofacilRequest;
import com.gabriel_os_sousa.loterias.repository.LotofacilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LotofacilService {

  public static final Logger LOG = LoggerFactory.getLogger(LotofacilService.class);

  private final LotofacilRepository lotofacilRepository;

  @Autowired
  public LotofacilService(LotofacilRepository lotofacilRepository) {
    this.lotofacilRepository = lotofacilRepository;
  }

  public Mono<Lotofacil> getByConcursoId(Long concursoId) {
    return lotofacilRepository.findById(concursoId)
        .switchIfEmpty(
            Mono.defer(() -> Mono.error(new ResourceNotFoundException(String.format("Concurso [%s] não encontrado na base de dados", concursoId)))))
        .doOnSuccess(lotofacil -> LOG.info(lotofacil.toString()))
        .doOnError(throwable -> LOG.error(throwable.getMessage()))
        .onErrorResume(ResourceNotFoundException.class, Mono::error);
  }

  public Mono<Lotofacil> save(LotofacilRequest lotofacilRequest) {
    var entity = Lotofacil.builder()
        .withLotofacilRequest(lotofacilRequest)
        .build();

    return lotofacilRepository.save(entity)
        .doOnSuccess(lotofacil -> LOG.info("Concurso lotofacil salvo com sucesso [{}]", lotofacil))
        .doOnError(throwable -> LOG.error("Erro ao salvar concurso [{}]", throwable.getMessage()));
  }
}
