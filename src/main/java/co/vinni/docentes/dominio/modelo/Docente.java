package co.vinni.docentes.dominio.modelo;

import lombok.Builder;

@Builder
public class Docente {
    public String nombre;
    public String apellido;
    public String email;
}
