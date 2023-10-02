package com.gabriel_os_sousa.loterias.model;

import com.gabriel_os_sousa.loterias.model.enums.LoteriaEnum;
import com.gabriel_os_sousa.loterias.model.mongo.LotofacilItem;
import com.gabriel_os_sousa.loterias.model.request.LotofacilRequest;
import com.gabriel_os_sousa.loterias.utils.StringNumberSorterUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lotofacil {
  private String id;

  private Boolean acumulado;

  private LocalDate dataApuracao;

  private LocalDate dataProximoConcurso;

  private Long numero;

  private List<String> dezenas;

  private List<String> dezenasOrdenadas;

  private LoteriaEnum tipoJogo;

  public Lotofacil() {
  }

  private Lotofacil(Builder builder) {
    this.id = builder.id;
    this.acumulado = builder.acumulado;
    this.dataApuracao = builder.dataApuracao;
    this.dataProximoConcurso = builder.dataProximoConcurso;
    this.numero = builder.numero;
    this.dezenas = builder.dezenas;
    this.dezenasOrdenadas = builder.dezenasOrdenadas;
    this.tipoJogo = builder.tipoJogo;
  }

  public static Lotofacil.Builder builder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public Boolean getAcumulado() {
    return acumulado;
  }

  public LocalDate getDataApuracao() {
    return dataApuracao;
  }

  public LocalDate getDataProximoConcurso() {
    return dataProximoConcurso;
  }

  public Long getNumero() {
    return numero;
  }

  public List<String> getDezenas() {
    return dezenas;
  }

  public LoteriaEnum getTipoJogo() {
    return tipoJogo;
  }

  public List<String> getDezenasOrdenadas() {
    return dezenasOrdenadas;
  }

  @Override
  public String toString() {
    return "Lotofacil{" +
        "id='" + id + '\'' +
        ", acumulado='" + acumulado + '\'' +
        ", dataApuracao=" + dataApuracao +
        ", dataProximoConcurso=" + dataProximoConcurso +
        ", numero=" + numero +
        ", dezenas=" + dezenas +
        ", dezenasOrdenadas=" + dezenasOrdenadas +
        ", tipoJogo=" + tipoJogo +
        '}';
  }

  public static final class Builder {

    private final List<String> dezenas = new ArrayList<>();

    private final List<String> dezenasOrdenadas = new ArrayList<>();

    private String id;

    private Boolean acumulado;

    private LocalDate dataApuracao;

    private LocalDate dataProximoConcurso;

    private Long numero;

    private LoteriaEnum tipoJogo;

    public Builder() {
    }

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withAcumulado(Boolean acumulado) {
      this.acumulado = acumulado;
      return this;
    }

    public Builder withDataApuracao(LocalDate dataApuracao) {
      this.dataApuracao = dataApuracao;
      return this;
    }

    public Builder withDataProximoConcurso(LocalDate dataProximoConcurso) {
      this.dataProximoConcurso = dataProximoConcurso;
      return this;
    }

    public Builder withNumero(Long numero) {
      this.numero = numero;
      return this;
    }

    public Builder withDezenas(List<String> dezenas) {
      this.dezenas.addAll(dezenas);
      return this;
    }

    public Builder withDezenasOrdenadas(List<String> dezenasOrdenadas) {
      this.dezenasOrdenadas.addAll(dezenasOrdenadas);
      return this;
    }

    public Builder withTipoJogo(LoteriaEnum tipoJogo) {
      this.tipoJogo = tipoJogo;
      return this;
    }

    public Builder withLotofacilRequest(LotofacilRequest request) {
      this.numero = request.getNumero();
      this.acumulado = request.getAcumulado();
      this.dezenas.addAll(request.getDezenas());
      this.dataApuracao = request.getDataApuracao();
      this.dataProximoConcurso = request.getDataProximoConcurso();
      this.tipoJogo = request.getTipoJogo();
      return this;
    }

    public Builder withLotofacilItem(LotofacilItem lotofacilItem) {
      this.id = lotofacilItem.getId();
      this.numero = lotofacilItem.getNumero();
      this.acumulado = lotofacilItem.getAcumulado();
      this.dezenas.addAll(lotofacilItem.getDezenas());
      this.dezenasOrdenadas.addAll(StringNumberSorterUtil.sortStringNumbers(this.dezenas));
      this.dataApuracao = lotofacilItem.getDataApuracao();
      this.dataProximoConcurso = lotofacilItem.getDataProximoConcurso();
      this.tipoJogo = lotofacilItem.getTipoJogo();
      return this;
    }

    public Lotofacil build() {
      return new Lotofacil(this);
    }
  }
}


