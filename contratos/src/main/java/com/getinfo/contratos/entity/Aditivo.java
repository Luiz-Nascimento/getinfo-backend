package com.getinfo.contratos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aditivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

    private Long diasAditivo;

    @DecimalMin("0.0")
    private BigDecimal valorAditivo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] anexo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "aditivo_entregaveis",
            joinColumns = @JoinColumn(name = "aditivo_id"),
            inverseJoinColumns = @JoinColumn(name = "entregavel_id")
    )
    private Set<Entregavel> entregaveis = new HashSet<>();

    private String descricao;

}
