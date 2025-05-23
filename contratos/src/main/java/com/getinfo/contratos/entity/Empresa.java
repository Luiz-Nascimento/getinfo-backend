package com.getinfo.contratos.entity;

import com.getinfo.contratos.enums.Estado;
import com.getinfo.contratos.enums.TipoEmpresa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLRestriction("ativo = true")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Contrato> contratos = new ArrayList<>();

    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipo;

    private Boolean ativo = true;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String telefone;
    private String email;
    private Estado estado;
    private String cidade;
    private String complemento;
    private String nomeResponsavel;
    private String emailResponsavel;
    private String telefoneResponsavel;
    private String cpfResponsavel;
}
