package com.gabriel_os_sousa.loterias.model.response;

import com.gabriel_os_sousa.loterias.model.mongo.LotofacilEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LotofacilResponse {
  private Long concurso;

  private TreeSet<Integer> dezenas;

  private List<Integer> dezenasOrdemSorteio;

  private LocalDate dataConcurso;

  public LotofacilResponse() {
  }

  public LotofacilResponse(LotofacilEntity lotofacil) {
    this.concurso = lotofacil.getConcurso();
    this.dezenas = lotofacil.getDezenas();
    this.dezenasOrdemSorteio = lotofacil.getDezenasOrdemSorteio();
    this.dataConcurso = lotofacil.getDataConcurso();
  }

  public Long getConcurso() {
    return concurso;
  }

  public Set<Integer> getDezenas() {
    return dezenas;
  }

  public LocalDate getDataConcurso() {
    return dataConcurso;
  }

  public List<Integer> getDezenasOrdemSorteio() {
    return dezenasOrdemSorteio;
  }

  @Override
  public String toString() {
    return "LotofacilResponse{" +
        "concurso=" + concurso +
        ", dezenas=" + dezenas +
        ", dezenasOrdemSorteio=" + dezenasOrdemSorteio +
        ", dataConcurso=" + dataConcurso +
        '}';
  }
}
