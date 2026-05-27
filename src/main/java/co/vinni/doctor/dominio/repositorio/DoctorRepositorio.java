package co.vinni.doctor.dominio.repositorio;

import co.vinni.doctor.dominio.modelo.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorRepositorio {
    List<Doctor> findAll();
    Optional<Doctor> findByCedula(String cedula);
    Doctor save(Doctor doctor);
    boolean existsByCedula(String cedula);
}