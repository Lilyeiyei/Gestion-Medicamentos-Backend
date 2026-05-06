package co.vinni.formula.service;

import co.vinni.formula.model.formulaModel;
import co.vinni.formula.repository.formulaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class formulaService {

    @Inject
    formulaRepository repository;

    @Transactional
    public void registrarFormula(formulaModel formula) {
        repository.persist(formula);
    }

    public List<formulaModel> listarTodas() {
        return repository.listAll();
    }
}