package com.getinfo.contratos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Aditivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "id_contrato", unique = true)
    //private Contrato contrato;

    private Integer tempoAditivo;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private byte[] anexo;
    private String descricao;

}
