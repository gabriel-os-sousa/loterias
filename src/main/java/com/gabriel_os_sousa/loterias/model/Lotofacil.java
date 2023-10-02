package com.gabriel_os_sousa.loterias.model;

import com.gabriel_os_sousa.loterias.model.request.LotofacilRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Lotofacil {

  private Long concurso;

  private TreeSet<Integer> dezenas;

  private List<Integer> dezenasOrdemSorteio;

  private LocalDate dataConcurso;

  public Lotofacil() {
  }

  private Lotofacil(final LotofacilBuilder lotofacilBuilder) {
    this.concurso = lotofacilBuilder.concurso;
    this.dezenas = lotofacilBuilder.dezenas;
    this.dezenasOrdemSorteio = lotofacilBuilder.dezenasOrdemSorteio;
    this.dataConcurso = lotofacilBuilder.dataConcurso;
  }

  public static LotofacilBuilder builder() {
    return new LotofacilBuilder();
  }

  public Long getConcurso() {
    return concurso;
  }

  public TreeSet<Integer> getDezenas() {
    return dezenas;
  }

  public List<Integer> getDezenasOrdemSorteio() {
    return dezenasOrdemSorteio;
  }

  public LocalDate getDataConcurso() {
    return dataConcurso;
  }

  @Override
  public String toString() {
    return "Lotofacil{" +
        "concurso=" + concurso +
        ", dezenas=" + dezenas +
        ", dezenasOrdemSorteio=" + dezenasOrdemSorteio +
        ", dataConcurso=" + dataConcurso +
        '}';
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

    public LotofacilBuilder withDezenas(List<Integer> dezenas) {
      this.dezenas.addAll(dezenas);
      return this;
    }

    public LotofacilBuilder withDezenasOrdemSorteio(TreeSet<Integer> dezenasOrdemSorteio) {
      this.dezenasOrdemSorteio.addAll(dezenasOrdemSorteio);
      return this;
    }

    public LotofacilBuilder withDataConcurso(LocalDate dataConcurso) {
      this.dataConcurso = dataConcurso;
      return this;
    }

    public LotofacilBuilder withLotofacilRequest(LotofacilRequest request) {
      this.concurso = request.getConcurso();
      this.dezenas.addAll(request.getDezenasOrdemSorteio());
      this.dezenasOrdemSorteio.addAll(request.getDezenasOrdemSorteio());
      this.dataConcurso = request.getDataConcurso();
      return this;
    }

    public Lotofacil build() {
      return new Lotofacil(this);
    }
  }
}
