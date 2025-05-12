package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.TipoEmpresa;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratos;

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipo;

    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String telefone;
    private String email;
    private String cidade;
    private String complemento;
    private String nomeResponsavel;
    private String emailResponsavel;
    private String telefoneResponsavel;
    private String cpfResponsavel;
}
