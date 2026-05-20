package co.vinni.formula.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class formulaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "El nombre del paciente no puede estar vacío")
    public String nombrePaciente;

    @NotBlank(message = "La identificación del paciente no puede estar vacía")
    public String identificacionPaciente;

    @NotBlank(message = "El medicamento no puede estar vacío")
    public String medicamento;

    @NotBlank(message = "El ID del medicamento no puede estar vacío")
    public String idMedicamento;

    @NotBlank(message = "La dosis no puede estar vacía")
    public String dosis;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser mayor a la actual")
    public LocalDate fecha;
}