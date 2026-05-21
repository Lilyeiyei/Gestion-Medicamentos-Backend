package co.vinni.pacientes.aplicacion;

import co.vinni.pacientes.dominio.modelo.Paciente;
import co.vinni.pacientes.dominio.repositorio.PacienteRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class PacienteServicio {

    @Inject
    PacienteRepositorio repositorio;

    public void registrar(Paciente paciente) {
        if (repositorio.existeEmail(paciente.email)) {
            throw new BadRequestException("El correo ya está registrado en el sistema");
        }
        repositorio.crear(paciente);
    }
}