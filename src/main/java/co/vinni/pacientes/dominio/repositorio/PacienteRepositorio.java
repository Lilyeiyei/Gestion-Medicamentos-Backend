package co.vinni.pacientes.dominio.repositorio;

import co.vinni.pacientes.dominio.modelo.Paciente;

public interface PacienteRepositorio {
    void crear(Paciente paciente);
    boolean existeEmail(String email);
}
