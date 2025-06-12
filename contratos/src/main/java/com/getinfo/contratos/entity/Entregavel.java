package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.StatusEntregavel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Entregavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    // --- Relacionamento Muitos-para-Muitos com Aditivo ---
    // Este é o lado "inverso" do relacionamento.
    // 'mappedBy = "entregaveis"' indica que o campo 'entregaveis' na entidade Aditivo
    // é quem "dona" do mapeamento e define a tabela de junção.
    @ManyToMany(mappedBy = "entregaveis", fetch = FetchType.LAZY)
    private Set<Aditivo> aditivos = new HashSet<>(); // Inicialize a coleção
    // --- Fim do relacionamento ---

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntregavel status = StatusEntregavel.PENDENTE;

    private String descricao;

    private String observacao;

    private String justificativaCancelamento;

    private LocalDate dataCancelamento;

    private LocalDate dataFinal;

    private LocalDate dataEntrega;
}
