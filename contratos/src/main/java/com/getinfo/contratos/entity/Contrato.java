package com.getinfo.contratos.entity;


import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "contratos")
@Getter
@Setter
@NoArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "contrato_colaboradores",
            joinColumns = @JoinColumn(name = "contrato_id"),
            inverseJoinColumns = @JoinColumn(name = "colaborador_id")
    )
    private Set<Colaborador> colaboradores = new HashSet<>();

    @OneToMany(
            mappedBy = "contrato",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Agregado> agregados = new HashSet<>();

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entregavel> entregaveis = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusContrato status = StatusContrato.ATIVO;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal valor;

    private String nomeResponsavel;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] anexo;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    public void adicionarAgregado(Agregado agregado) {
        this.agregados.add(agregado);
    }
}