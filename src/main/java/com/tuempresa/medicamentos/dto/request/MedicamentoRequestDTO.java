package com.tuempresa.medicamentos.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class MedicamentoRequestDTO {

    @NotBlank
    private String nombreGenerico;

    @NotNull
    @Min(1)
    private Integer miligramos;

    @NotBlank
    private String dosis;

    @NotBlank
    private String frecuencia;

    @NotBlank
    private String horario;

    @NotNull
    private LocalDate fechaVencimiento;

    // getters y setters


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