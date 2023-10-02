package com.gabriel_os_sousa.loterias.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel_os_sousa.loterias.model.enums.LoteriaEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LotofacilRequest {

  private Boolean acumulado;

  @Min(value = 1, message = "Número do concurso deve ser maior que 0")
  private Long numero;

  @Size(min = 15, max = 15, message = "A lista deve conter 15 valores")
  private List<@Pattern(regexp = "\\b(?:0[1-9]|1\\d|2[0-5])\\b", message = "Deve ser um número [1-25] em formato de String e números menores que 10 devem conter 0 à esquerda") String>
      dezenas;

  //  @DateTimeFormat(pattern = "yyyy/MM/DD", iso = DATE_TIME)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dataApuracao;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dataProximoConcurso;

  private LoteriaEnum tipoJogo;

  public LotofacilRequest() {
  }

  private LotofacilRequest(Builder builder) {
    this.acumulado = builder.acumulado;
    this.numero = builder.numero;
    this.dezenas = builder.dezenas;
    this.dataApuracao = builder.dataApuracao;
    this.dataProximoConcurso = builder.dataProximoConcurso;
    this.tipoJogo = builder.tipoJogo;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Boolean getAcumulado() {
    return acumulado;
  }

  public Long getNumero() {
    return numero;
  }

  public List<String> getDezenas() {
    return dezenas;
  }

  public LocalDate getDataApuracao() {
    return dataApuracao;
  }

  public LocalDate getDataProximoConcurso() {
    return dataProximoConcurso;
  }

  public LoteriaEnum getTipoJogo() {
    return tipoJogo;
  }

  @Override
  public String toString() {
    return "LotofacilRequest{" +
        "acumulado='" + acumulado + '\'' +
        ", numero=" + numero +
        ", dezenas=" + dezenas +
        ", dataApuracao=" + dataApuracao +
        ", dataProximoConcurso=" + dataProximoConcurso +
        ", tipoJogo=" + tipoJogo +
        '}';
  }

  public static final class Builder {

    private final List<String> dezenas = new ArrayList<>();

    private Boolean acumulado;

    private Long numero;

    private LocalDate dataApuracao;

    private LocalDate dataProximoConcurso;

    private LoteriaEnum tipoJogo;

    public Builder() {
    }

    public Builder withAcumulado(final Boolean acumulado) {
      this.acumulado = acumulado;
      return this;
    }

    public Builder withNumero(final Long numero) {
      this.numero = numero;
      return this;
    }

    public Builder withDezenas(final List<String> dezenas) {
      this.dezenas.addAll(dezenas);
      return this;
    }

    public Builder withDataApuracao(final LocalDate dataApuracao) {
      this.dataApuracao = dataApuracao;
      return this;
    }

    public Builder withDataProximoConcurso(final LocalDate dataProximoConcurso) {
      this.dataProximoConcurso = dataProximoConcurso;
      return this;
    }

    public Builder withTipoJogo(final LoteriaEnum tipoJogo) {
      this.tipoJogo = tipoJogo;
      return this;
    }

    public LotofacilRequest build() {
      return new LotofacilRequest(this);
    }
  }
}
