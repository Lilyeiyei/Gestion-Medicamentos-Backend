package co.vinni.pacientes.infraestructura.persistencia;

import co.vinni.pacientes.dominio.modelo.Paciente;
import co.vinni.pacientes.dominio.modelo.PacienteEntity;
import co.vinni.pacientes.dominio.repositorio.PacienteRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PacientePanache implements PacienteRepositorio, PanacheRepository<PacienteEntity> {

    @Override
    @Transactional
    public void crear(Paciente paciente) {
        PacienteEntity entity = PacienteEntity.builder()
                .nombreCompleto(paciente.nombreCompleto)
                .documentoIdentidad(paciente.documentoIdentidad)
                .eps(paciente.eps)
                .email(paciente.email)
                .contrasena(paciente.contrasena)
                .build();
        persist(entity);
    }

    @Override
    public boolean existeEmail(String email) {
        return find("email", email).firstResultOptional().isPresent();
    }
}