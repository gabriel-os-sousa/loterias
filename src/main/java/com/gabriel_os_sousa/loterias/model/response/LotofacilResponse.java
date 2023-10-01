package com.gabriel_os_sousa.loterias.model.response;

import com.gabriel_os_sousa.loterias.model.Lotofacil;
import java.time.LocalDate;
import java.util.Set;

public class LotofacilResponse {
  private Long concurso;

  private Set<Integer> dezenas;

  private LocalDate dataConcurso;

  public LotofacilResponse() {
  }

  public LotofacilResponse(Lotofacil lotofacil) {
    this.concurso = lotofacil.getConcurso();
    this.dezenas = lotofacil.getDezenas();
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

  @Override
  public String toString() {
    return "LotofacilResponse{" +
        "concurso=" + concurso +
        ", dezenas=" + dezenas +
        ", dataConcurso=" + dataConcurso +
        '}';
  }
}
