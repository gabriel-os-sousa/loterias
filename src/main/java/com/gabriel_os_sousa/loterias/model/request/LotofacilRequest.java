package com.gabriel_os_sousa.loterias.model.request;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class LotofacilRequest {

  @Min(value = 1, message = "Número do concurso deve ser maior que 0")
  private Long concurso;

  @Size(min = 15, max = 15, message = "A lista deve conter 15 valores")
  private List<
      @Min(value = 1, message = "Valor deve estar no range [1,25]")
      @Max(value = 25, message = "Valor deve estar no range [1,25]") Integer> dezenasOrdemSorteio;

  @DateTimeFormat(pattern = "yyyy-MM-DD", iso = DATE_TIME)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dataConcurso;

  private LotofacilRequest(final LotofacilRequestBuilder lotofacilRequestBuilder) {
    this.concurso = lotofacilRequestBuilder.concurso;
    this.dezenasOrdemSorteio = lotofacilRequestBuilder.dezenasOrdemSorteio;
    this.dataConcurso = lotofacilRequestBuilder.dataConcurso;
  }

  private LotofacilRequest() {
  }

  public Long getConcurso() {
    return concurso;
  }

  public List<Integer> getDezenasOrdemSorteio() {
    return dezenasOrdemSorteio;
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
        ", dezenas=" + dezenasOrdemSorteio +
        ", dataConcurso=" + dataConcurso +
        '}';
  }

  public static class LotofacilRequestBuilder {
    private final List<Integer> dezenasOrdemSorteio = new ArrayList<>();

    private Long concurso;

    private LocalDate dataConcurso;

    public LotofacilRequestBuilder withConcurso(Long concurso) {
      this.concurso = concurso;
      return this;
    }

    public LotofacilRequestBuilder withDezenasOrdemSorteio(List<Integer> dezenas) {
      this.dezenasOrdemSorteio.addAll(dezenas);
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
