package com.gabriel_os_sousa.loterias.model.request;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

public class LotofacilRequest {

  private Long concurso;

  private Set<Integer> dezenas;

  @DateTimeFormat(pattern = "yyyy-MM-DD", iso = DATE_TIME)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dataConcurso;

  private LotofacilRequest(final LotofacilRequestBuilder lotofacilRequestBuilder) {
    this.concurso = lotofacilRequestBuilder.concurso;
    this.dezenas = lotofacilRequestBuilder.dezenas;
    this.dataConcurso = lotofacilRequestBuilder.dataConcurso;
  }

  private LotofacilRequest() {
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

  public void setDataConcurso(LocalDate dataConcurso) {
    this.dataConcurso = dataConcurso;
  }

  @Override
  public String toString() {
    return "LotofacilRequest{" +
        "concurso=" + concurso +
        ", dezenas=" + dezenas +
        ", dataConcurso=" + dataConcurso +
        '}';
  }

  public static class LotofacilRequestBuilder {
    private Long concurso;

    private Set<Integer> dezenas = new HashSet<>();

    private LocalDate dataConcurso;

    public LotofacilRequestBuilder withConcurso(Long concurso) {
      this.concurso = concurso;
      return this;
    }

    public LotofacilRequestBuilder withDezenas(Set<Integer> dezenas) {
      this.dezenas.addAll(dezenas);
      return this;
    }

    public LotofacilRequestBuilder withDataConcurso(LocalDate dataConcurso) {
      this.dataConcurso = dataConcurso;
      return this;
    }

    public LotofacilRequest build() {
      return new LotofacilRequest(this);
    }
  }
}
