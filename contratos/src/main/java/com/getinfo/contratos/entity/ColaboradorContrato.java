package com.getinfo.contratos.entity;


import jakarta.persistence.*;


@Entity
public class ColaboradorContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    private String cargoContrato;

    public ColaboradorContrato() {
    }

    public ColaboradorContrato(Long id, Contrato contrato, Colaborador colaborador, String cargoContrato) {
        this.id = id;
        this.contrato = contrato;
        this.colaborador = colaborador;
        this.cargoContrato = cargoContrato;
    }

    public Long getId() {
        return id;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getCargoContrato() {
        return cargoContrato;
    }

    public void setCargoContrato(String cargoContrato) {
        this.cargoContrato = cargoContrato;
    }
}
