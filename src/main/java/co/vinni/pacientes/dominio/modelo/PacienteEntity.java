package co.vinni.pacientes.dominio.modelo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PACIENTES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteEntity extends PanacheEntity {
    public String nombreCompleto;
    public String documentoIdentidad;
    public String eps;
    public String email;
    public String contrasena;
}