package com.gabriel_os_sousa.loterias.model;

import com.gabriel_os_sousa.loterias.model.request.LotofacilRequest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lotofacil")
public class Lotofacil {

  @Id
  private Long concurso;

  private Set<Integer> dezenas;

  private LocalDate dataConcurso;

  public Lotofacil() {
  }

  private Lotofacil(final LotofacilBuilder lotofacilBuilder) {
    this.concurso = lotofacilBuilder.concurso;
    this.dezenas = lotofacilBuilder.dezenas;
    this.dataConcurso = lotofacilBuilder.dataConcurso;
  }

  public static LotofacilBuilder builder() {
    return new LotofacilBuilder();
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

  public static class LotofacilBuilder {
    private Long concurso;

    private Set<Integer> dezenas = new HashSet<>();

    private LocalDate dataConcurso;

    private LotofacilBuilder() {
    }

    public LotofacilBuilder withConcurso(Long concurso) {
      this.concurso = concurso;
      return this;
    }

    public LotofacilBuilder withDezenas(Set<Integer> dezenas) {
      this.dezenas.addAll(dezenas);
      return this;
    }

    public LotofacilBuilder withDataConcurso(LocalDate dataConcurso) {
      this.dataConcurso = dataConcurso;
      return this;
    }

    public LotofacilBuilder withLotofacilRequest(LotofacilRequest request) {
      this.concurso = request.getConcurso();
      this.dezenas.addAll(request.getDezenas());
      this.dataConcurso = request.getDataConcurso();
      return this;
    }

    public Lotofacil build() {
      return new Lotofacil(this);
    }
  }
}
