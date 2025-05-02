package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.Status;
import com.getinfo.contratos.enums.TipoContrato;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrato;

    @ManyToOne
    @JoinColumn(name= "id_empresa", unique = true, nullable = false)
    private Empresa empresa;

    // Ainda será adicionado
    //@ManyToOne
    //@JoinColumn(name= "id_funcionario", unique = true, nullable = false)
    //private Funcionario idFuncionario;

    @Enumerated(EnumType.STRING)
    private Status status;
    // Ainda será adicionado.
    //private Entregavel entregavel;
    //private Responsavel responsavel;

    private BigDecimal valor;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;
    @Lob
    private byte[] anexo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
