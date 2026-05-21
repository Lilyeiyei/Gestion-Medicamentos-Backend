package com.tuempresa.medicamentos.repository;

import com.tuempresa.medicamentos.entity.MedicamentoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MedicamentoRepository
        implements PanacheRepository<MedicamentoEntity> {

    public Optional<MedicamentoEntity>
    buscarPorNombre(String nombre){

        return find(
                "LOWER(nombreGenerico)=LOWER(?1)",
                nombre
        ).firstResultOptional();
    }
}