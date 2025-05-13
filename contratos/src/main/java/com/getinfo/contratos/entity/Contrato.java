package com.getinfo.contratos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "id_empresa")
    private Empresa empresaId;


    @Enumerated(EnumType.STRING)
    private StatusContrato status;

    private BigDecimal valor;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipo;
    @Lob
    private byte[] anexo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Contrato(ContratoCreateDTO contratoCreateDTO) {

    }
}
