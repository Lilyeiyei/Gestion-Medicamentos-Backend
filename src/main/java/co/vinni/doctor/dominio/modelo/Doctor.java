package co.vinni.doctor.dominio.modelo;

public class Doctor {

    private String cedula;
    private String nombreCompleto;
    private String especialidad;
    private String correoElectronico;
    private String telefono;

    public Doctor() {}

    public Doctor(String cedula, String nombreCompleto, String especialidad, String correoelectronico, String telefono) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

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