package co.vinni.doctor.infraestructura.persistencia;

import co.vinni.doctor.dominio.modelo.Doctor;
import co.vinni.doctor.dominio.modelo.DoctorEntity;
import co.vinni.doctor.dominio.repositorio.DoctorRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class DoctorPanache implements DoctorRepositorio {

    @Inject
    EntityManager em;

    @Override
    public List<Doctor> findAll() {
        return em.createQuery("SELECT d FROM DoctorEntity d", DoctorEntity.class)
                .getResultList()
                .stream()
                .map(this::toModelo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Doctor> findByCedula(String cedula) {
        DoctorEntity entity = em.find(DoctorEntity.class, cedula);
        return Optional.ofNullable(entity).map(this::toModelo);
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        DoctorEntity entity = toEntity(doctor);
        em.persist(entity);
        return toModelo(entity);
    }

    @Override
    public boolean existsByCedula(String cedula) {
        return em.find(DoctorEntity.class, cedula) != null;
    }

    private Doctor toModelo(DoctorEntity entity) {
        return new Doctor(entity.getCedula(), entity.getNombreCompleto(),
                entity.getEspecialidad(), entity.getCorreoElectronico(), entity.getTelefono());
    }

    private DoctorEntity toEntity(Doctor doctor) {
        DoctorEntity entity = new DoctorEntity();
        entity.setCedula(doctor.getCedula());
        entity.setNombreCompleto(doctor.getNombreCompleto());
        entity.setEspecialidad(doctor.getEspecialidad());
        entity.setCorreoElectronico(doctor.getCorreoElectronico());
        entity.setTelefono(doctor.getTelefono());
        return entity;
    }
}