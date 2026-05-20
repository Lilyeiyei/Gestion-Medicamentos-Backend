package co.vinni.doctor.aplicacion;

import co.vinni.doctor.dominio.modelo.Doctor;
import co.vinni.doctor.dominio.repositorio.DoctorRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DoctorServicio {

    @Inject
    DoctorRepositorio doctorRepositorio;

    public List<Doctor> listarDoctores() {
        return doctorRepositorio.findAll();
    }

    public Doctor registrarDoctor(Doctor doctor) {
        if (doctorRepositorio.existsByCedula(doctor.getCedula())) {
            throw new IllegalArgumentException("Ya existe un doctor con la cédula: " + doctor.getCedula());
        }
        return doctorRepositorio.save(doctor);
    }
}