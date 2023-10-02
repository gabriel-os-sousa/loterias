package com.gabriel_os_sousa.loterias.model.mongo;

import com.gabriel_os_sousa.loterias.model.Lotofacil;
import com.gabriel_os_sousa.loterias.model.enums.LoteriaEnum;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lotofacil")
public class LotofacilItem {

  @Id
  private String id;

  private Boolean acumulado;

  @Indexed(unique = true)
  private Long numero;

  private List<String> dezenas;

  private LocalDate dataApuracao;

  private LocalDate dataProximoConcurso;

  private LoteriaEnum tipoJogo;

  public LotofacilItem() {
  }

  private LotofacilItem(final Builder builder) {
    this.id = builder.id;
    this.acumulado = builder.acumulado;
    this.dataApuracao = builder.dataApuracao;
    this.dataProximoConcurso = builder.dataProximoConcurso;
    this.numero = builder.numero;
    this.dezenas = builder.dezenas;
    this.tipoJogo = builder.tipoJogo;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getId() {
    return id;
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
    return "LotofacilItem{" +
        "id='" + id + '\'' +
        ", acumulado='" + acumulado + '\'' +
        ", numero=" + numero +
        ", dezenas=" + dezenas +
        ", dataApuracao=" + dataApuracao +
        ", dataProximoConcurso=" + dataProximoConcurso +
        ", tipoJogo=" + tipoJogo +
        '}';
  }

  public static final class Builder {
    private final List<String> dezenas = new ArrayList<>();

    private String id;

    private Boolean acumulado;

    private Long numero;

    private LocalDate dataApuracao;

    private LocalDate dataProximoConcurso;

    private LoteriaEnum tipoJogo;

    private Builder() {
    }

    public Builder withId(String id) {
      this.id = id;
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

    public Builder withDataConcurso(LocalDate dataConcurso) {
      this.dataApuracao = dataConcurso;
      return this;
    }

    public Builder withAcumulado(Boolean acumulado) {
      this.acumulado = acumulado;
      return this;
    }

    public Builder withTipoJogo(LoteriaEnum tipoJogo) {
      this.tipoJogo = tipoJogo;
      return this;
    }

    public Builder withDataProximoConcurso(LocalDate dataProximoConcurso) {
      this.dataProximoConcurso = dataProximoConcurso;
      return this;
    }

    public Builder withLotofacilModel(Lotofacil model) {
      this.id = model.getId();
      this.numero = model.getNumero();
      this.acumulado = model.getAcumulado();
      this.dezenas.addAll(model.getDezenas());
      this.dataApuracao = model.getDataApuracao();
      this.dataProximoConcurso = model.getDataProximoConcurso();
      this.tipoJogo = model.getTipoJogo();
      return this;
    }

    public LotofacilItem build() {
      return new LotofacilItem(this);
    }
  }
}
