package co.vinni.doctor.dominio.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @Column(name = "cedula")
    private String cedula;

    @NotBlank(message = "El nombre completo es obligatorio.")
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @NotBlank(message = "La especialidad es obligatoria.")
    @Column(name = "especialidad")
    private String especialidad;

    @Email(message = "El correo electrónico no tiene un formato válido.")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Column(name = "correo_electronico", unique = true)
    private String correoElectronico;

    @Column(name = "telefono")
    private String telefono;

    public DoctorEntity() {}

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}