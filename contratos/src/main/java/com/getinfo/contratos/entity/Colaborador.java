package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.ColaboradorStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento inverso (opcional)
    @ManyToMany(mappedBy = "colaboradores")
    private Set<Contrato> contratos = new HashSet<>();

    private String nome;
    private String sobrenome;
    private ColaboradorStatus status;
    private String cpf;
    private String email;
    private String telefone;
    private String cargo;

}
