package co.vinni.formula.repository;


import co.vinni.formula.model.formulaModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class formulaRepository implements PanacheRepository<formulaModel> {
    // Aquí puedes añadir métodos de búsqueda personalizados si los necesitas
}