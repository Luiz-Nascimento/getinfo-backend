package com.getinfo.contratos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Repactuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@OneToOne
    //@JoinColumn(name = "id_contrato")
    //private Contrato contrato;

    private Integer tempoAditivo;
    private LocalDate data_inicio;
    private LocalDate data_final;
    private byte[] anexo;
    private String descricao;


}
