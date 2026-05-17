package co.vinni.doctor.dominio.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "especialidad")
    private String especialidad;

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