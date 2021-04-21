package com.example.petrescue.domain;

import java.time.LocalDate;

public class DoacaoDTO {

    private Integer id;
    private Double quantia;
    private LocalDate quando;
    private Integer doador;
    private Integer vaquinha;

    public DoacaoDTO(Integer id, Double quantia, LocalDate quando, Integer doador, Integer vaquinha) {
        this.id = id;
        this.quantia = quantia;
        this.quando = quando;
        this.doador = doador;
        this.vaquinha = vaquinha;
    }

    public DoacaoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantia() {
        return quantia;
    }

    public void setQuantia(Double quantia) {
        this.quantia = quantia;
    }

    public LocalDate getQuando() {
        return quando;
    }

    public void setQuando(LocalDate quando) {
        this.quando = quando;
    }

    public Integer getDoador() {
        return doador;
    }

    public void setDoador(Integer doador) {
        this.doador = doador;
    }

    public Integer getVaquinha() {
        return vaquinha;
    }

    public void setVaquinha(Integer vaquinha) {
        this.vaquinha = vaquinha;
    }
}
