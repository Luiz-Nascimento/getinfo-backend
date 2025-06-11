package com.getinfo.contratos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Repactuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

    @DecimalMin("0.0")
    private BigDecimal valorOriginalContrato;

    @DecimalMin("0.0")
    private BigDecimal novoValorContrato;

    private LocalDate dataRepactuacao;

    private LocalDate novaDataFinal;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] anexo;

    private String descricao;

    private String motivoRepactuacao;


}
