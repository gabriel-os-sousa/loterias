package com.gabriel_os_sousa.loterias.service;

import com.gabriel_os_sousa.loterias.exception.ResourceNotFoundException;
import com.gabriel_os_sousa.loterias.model.Lotofacil;
import com.gabriel_os_sousa.loterias.model.mongo.LotofacilItem;
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

  public Mono<Lotofacil> getByConcursoId(String numero) {
    return lotofacilRepository.findByNumero(numero)
        .map(lotofacilItem -> Lotofacil.builder()
            .withLotofacilItem(lotofacilItem)
            .build())
        .switchIfEmpty(Mono.defer(() -> Mono.error(new ResourceNotFoundException("O Concurso solicitado não foi encontrado."))))
        .doOnSuccess(lotofacilItem -> LOG.info(lotofacilItem.toString()))
        .doOnError(throwable -> LOG.error(throwable.getMessage()))
        .onErrorResume(ResourceNotFoundException.class, Mono::error);
  }

  public Mono<Lotofacil> save(LotofacilRequest lotofacilRequest) {
    var model = Lotofacil.builder()
        .withLotofacilRequest(lotofacilRequest)
        .build();

    // Criar entidade para salvar no banco
    var entity = LotofacilItem.builder()
        .withLotofacilModel(model)
        .build();

    return lotofacilRepository.save(entity)
        .map(lotofacilItem -> Lotofacil.builder()
            .withLotofacilItem(lotofacilItem)
            .build())
        .doOnSuccess(lotofacilItem -> LOG.info("Concurso lotofacil salvo com sucesso [{}]", lotofacilItem))
        .doOnError(throwable -> LOG.error("Erro ao salvar concurso [{}]", throwable.getMessage()));
  }
}
