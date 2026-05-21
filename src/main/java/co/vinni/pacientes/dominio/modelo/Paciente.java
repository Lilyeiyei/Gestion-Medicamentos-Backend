package co.vinni.pacientes.dominio.modelo;

import lombok.Builder;

@Builder
public class Paciente {
    public String nombreCompleto;
    public String documentoIdentidad;
    public String eps;
    public String email;
    public String contrasena;
}