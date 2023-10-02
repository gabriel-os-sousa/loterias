package com.gabriel_os_sousa.loterias.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel_os_sousa.loterias.model.Lotofacil;
import com.gabriel_os_sousa.loterias.model.enums.LoteriaEnum;
import com.gabriel_os_sousa.loterias.utils.StringNumberSorterUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LotofacilResponse {
  private String id;

  private Boolean acumulado;

  private Long numero;

  private List<String> dezenas;

  private List<String> dezenasOrdenadas;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dataApuracao;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dataProximoConcurso;

  private LoteriaEnum tipoJogo;

  public LotofacilResponse() {
  }

  private LotofacilResponse(Builder builder) {
    this.id = builder.id;
    this.acumulado = builder.acumulado;
    this.dataApuracao = builder.dataApuracao;
    this.dataProximoConcurso = builder.dataProximoConcurso;
    this.numero = builder.numero;
    this.dezenas = builder.dezenas;
    this.dezenasOrdenadas = builder.dezenasOrdenadas;
    this.tipoJogo = builder.tipoJogo;
  }

  public static LotofacilResponse.Builder builder() {
    return new LotofacilResponse.Builder();
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
    return "LotofacilResponse{" +
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

    public Builder withLotofacil(Lotofacil lotofacil) {
      this.id = lotofacil.getId();
      this.numero = lotofacil.getNumero();
      this.acumulado = lotofacil.getAcumulado();
      this.dezenas.addAll(lotofacil.getDezenas());
      this.dezenasOrdenadas.addAll(StringNumberSorterUtil.sortStringNumbers(this.dezenas));
      this.dataApuracao = lotofacil.getDataApuracao();
      this.dataProximoConcurso = lotofacil.getDataProximoConcurso();
      this.tipoJogo = lotofacil.getTipoJogo();
      return this;
    }

    public LotofacilResponse build() {
      return new LotofacilResponse(this);
    }
  }
}
