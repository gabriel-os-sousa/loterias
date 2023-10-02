package com.gabriel_os_sousa.loterias.service;

import com.gabriel_os_sousa.loterias.exception.ResourceNotFoundException;
import com.gabriel_os_sousa.loterias.model.Lotofacil;
import com.gabriel_os_sousa.loterias.model.mongo.LotofacilEntity;
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

  public Mono<LotofacilEntity> getByConcursoId(Long concursoId) {
    return lotofacilRepository.findById(concursoId)
        .switchIfEmpty(Mono.defer(() -> Mono.error(new ResourceNotFoundException("O Concurso solicitado não foi encontrado."))))
        .doOnSuccess(lotofacilEntity -> LOG.info(lotofacilEntity.toString()))
        .doOnError(throwable -> LOG.error(throwable.getMessage()))
        .onErrorResume(ResourceNotFoundException.class, Mono::error);
  }

  public Mono<LotofacilEntity> save(LotofacilRequest lotofacilRequest) {
    // TODO: adicionar campos de ordem de dezenas. Dezenas ordenadas e não ordenadas

    var model = Lotofacil.builder()
        .withLotofacilRequest(lotofacilRequest)
        .build();

    // Criar entidade para salvar no banco
    var entity = LotofacilEntity.builder()
        .withConcurso(model.getConcurso())
        .withDezenas(model.getDezenas())
        .withDezenasOrdemSorteio(model.getDezenasOrdemSorteio())
        .withDataConcurso(model.getDataConcurso())
        .build();

    return lotofacilRepository.save(entity)
        .doOnSuccess(lotofacilEntity -> LOG.info("Concurso lotofacil salvo com sucesso [{}]", lotofacilEntity))
        .doOnError(throwable -> LOG.error("Erro ao salvar concurso [{}]", throwable.getMessage()));
  }
}
