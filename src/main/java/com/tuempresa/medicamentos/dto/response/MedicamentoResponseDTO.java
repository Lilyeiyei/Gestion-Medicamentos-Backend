package com.tuempresa.medicamentos.dto.response;

import java.time.LocalDate;

public class MedicamentoResponseDTO {

    private Long id;
    private String nombreGenerico;
    private Integer miligramos;
    private String dosis;
    private String frecuencia;
    private String horario;
    private LocalDate fechaVencimiento;

    // getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public Integer getMiligramos() {
        return miligramos;
    }

    public void setMiligramos(Integer miligramos) {
        this.miligramos = miligramos;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}