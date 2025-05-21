package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.StatusEntregavel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Entregavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "id_contrato", nullable = false)
   // private Contrato contrato;

    private StatusEntregavel status;
    private String descricao;
    private String observacao;
    private String just_canc;
    private LocalDate data_canc;
    private LocalDate data_final;
    private LocalDate data_entrega;

}
