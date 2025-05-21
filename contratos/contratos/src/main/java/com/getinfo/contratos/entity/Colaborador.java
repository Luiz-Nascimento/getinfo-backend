package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.ColaboradorStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToMany(mappedBy = "funcionarios")
    //private List<Contrato> contratos;

    private String nome;
    private String sobrenome;
    private ColaboradorStatus status;
    private String cpf;
    private String email;
    private String telefone;
    private String cargo;

}
