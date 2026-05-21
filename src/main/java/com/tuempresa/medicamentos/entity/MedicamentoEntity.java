package com.tuempresa.medicamentos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medicamento")
public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_generico",
            nullable = false,
            unique = true)
    private String nombreGenerico;

    @Column(nullable = false)
    private Integer miligramos;

    @Column(nullable = false)
    private String dosis;

    @Column(nullable = false)
    private String frecuencia;

    @Column(nullable = false)
    private String horario;

    @Column(name = "fecha_vencimiento",
            nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}