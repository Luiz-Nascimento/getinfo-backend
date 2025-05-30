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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntregavel status = StatusEntregavel.PENDENTE;

    @Column(nullable = false)
    private String descricao;

    private String observacao;

    private String justificativaCancelamento;

    private LocalDate dataCancelamento;

    private LocalDate dataFinal;

    private LocalDate dataEntrega;
}
