package co.vinni.docentes.dominio.modelo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DOCENTES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocenteEntity extends PanacheEntity {

    public String nombre;
    public String apellido;
    public String email;
}
