package com.gabriel_os_sousa.loterias.controller;

import com.gabriel_os_sousa.loterias.model.request.LotofacilRequest;
import com.gabriel_os_sousa.loterias.model.response.LotofacilResponse;
import com.gabriel_os_sousa.loterias.service.LotofacilService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/loterias/v1/lotofacil")
public class LotofacilController {

  public static final Logger LOG = LoggerFactory.getLogger(LotofacilController.class);

  private final LotofacilService service;

  @Autowired
  public LotofacilController(LotofacilService service) {
    this.service = service;
  }

  @GetMapping("/{numero}")
  public Mono<LotofacilResponse> getConcursoById(@PathVariable(name = "numero") String numero) {
    LOG.info("Iniciando busca de concurso por numero [{}]", numero);

    return service.getByConcursoId(numero)
        .map(lotofacil -> LotofacilResponse.builder()
            .withLotofacil(lotofacil)
            .build())
        .doOnSuccess(response -> LOG.info("Sucesso ao salvar concurso [{}]", numero))
        .doOnError(throwable -> LOG.info("Erro ao consultar concurso por numero [{}]. Erro=[{}]", numero, throwable.getMessage()));
  }

  @PostMapping
  public Mono<LotofacilResponse> saveConcursoFromRequest(@Valid @RequestBody LotofacilRequest lotofacilRequest) {
    // TODO: Verificar se concurso já foi computado no site da CAIXA
    LOG.info("Salvando concurso Lotofacil [{}]", lotofacilRequest);

    return service.save(lotofacilRequest)
        .map(lotofacil -> LotofacilResponse.builder()
            .withLotofacil(lotofacil)
            .build())
        .doOnSuccess(response -> LOG.info(response.toString()))
        .doOnError(
            throwable -> LOG.info("Erro ao salvar concurso Lotofacil [{}]. Erro=[{}]", lotofacilRequest.getNumero(), throwable.getMessage()));
  }

  // TODO: buscar concurso no site da caixa e salvar

}
