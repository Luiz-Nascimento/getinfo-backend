package com.getinfo.contratos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "aditivos")
public class Aditivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

    @DecimalMin("0.0")
    private BigDecimal valorAditivo;

    private Long diasAditivo;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] anexo;
    private String descricao;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "aditivo_entregaveis", // Tabela de junção para mapear a relação
            joinColumns = @JoinColumn(name = "aditivo_id"), // Coluna que referencia o Aditivo na tabela de junção
            inverseJoinColumns = @JoinColumn(name = "entregavel_id") // Coluna que referencia o Entregavel na tabela de junção
    )
    private Set<Entregavel> entregaveis = new HashSet<>();

}
