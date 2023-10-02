package com.gabriel_os_sousa.loterias.model.mongo;

import com.gabriel_os_sousa.loterias.model.Lotofacil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lotofacil")
public class LotofacilEntity {

  @Id
  private Long concurso;

  private TreeSet<Integer> dezenas;

  private List<Integer> dezenasOrdemSorteio;

  private LocalDate dataConcurso;

  public LotofacilEntity() {
  }

  private LotofacilEntity(final LotofacilBuilder lotofacilBuilder) {
    this.concurso = lotofacilBuilder.concurso;
    this.dezenas = lotofacilBuilder.dezenas;
    this.dezenasOrdemSorteio = lotofacilBuilder.dezenasOrdemSorteio;
    this.dataConcurso = lotofacilBuilder.dataConcurso;
  }

  public static LotofacilBuilder builder() {
    return new LotofacilBuilder();
  }

  @Override
  public String toString() {
    return "LotofacilEntity{" +
        "concurso=" + concurso +
        ", dezenas=" + dezenas +
        ", dezenasOrdemSorteio=" + dezenasOrdemSorteio +
        ", dataConcurso=" + dataConcurso +
        '}';
  }

  public Long getConcurso() {
    return concurso;
  }

  public TreeSet<Integer> getDezenas() {
    return dezenas;
  }

  public LocalDate getDataConcurso() {
    return dataConcurso;
  }

  public List<Integer> getDezenasOrdemSorteio() {
    return dezenasOrdemSorteio;
  }

  public static class LotofacilBuilder {
    private final TreeSet<Integer> dezenas = new TreeSet<>();

    private final List<Integer> dezenasOrdemSorteio = new ArrayList<>();

    private Long concurso;

    private LocalDate dataConcurso;

    private LotofacilBuilder() {
    }

    public LotofacilBuilder withConcurso(Long concurso) {
      this.concurso = concurso;
      return this;
    }

    public LotofacilBuilder withDezenas(TreeSet<Integer> dezenas) {
      this.dezenas.addAll(dezenas);
      return this;
    }

    public LotofacilBuilder withDezenasOrdemSorteio(List<Integer> dezenasOrdemSorteio) {
      this.dezenasOrdemSorteio.addAll(dezenasOrdemSorteio);
      return this;
    }

    public LotofacilBuilder withDataConcurso(LocalDate dataConcurso) {
      this.dataConcurso = dataConcurso;
      return this;
    }

    public LotofacilBuilder withLotofacilModel(Lotofacil model) {
      this.concurso = model.getConcurso();
      this.dezenas.addAll(model.getDezenas());
      this.dezenasOrdemSorteio.addAll(model.getDezenasOrdemSorteio());
      this.dataConcurso = model.getDataConcurso();
      return this;
    }

    public LotofacilEntity build() {
      return new LotofacilEntity(this);
    }
  }
}
