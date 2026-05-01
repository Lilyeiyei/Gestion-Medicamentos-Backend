package co.vinni.Medicamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Medicamento {

    @Id
    public Long id;

    public String nombreGenerico;
    public String miligramos;
    public String dosis;
    public LocalDate fechaVencimiento;
    public String frecuencia;
    public String horario;
}