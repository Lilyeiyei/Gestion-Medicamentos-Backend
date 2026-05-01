package co.vinni.Medicamento.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import co.vinni.Medicamento.model.Medicamento;

@ApplicationScoped
public class MedicamentoRepository implements PanacheRepository<Medicamento> {
}