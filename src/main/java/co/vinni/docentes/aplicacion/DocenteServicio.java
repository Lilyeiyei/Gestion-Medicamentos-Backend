package co.vinni.docentes.aplicacion;

import co.vinni.docentes.dominio.modelo.Docente;
import co.vinni.docentes.dominio.repositorio.DocenteRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DocenteServicio {
    @Inject
    DocenteRepositorio repositorio;

    public void crear(Docente docente) {
        repositorio.crear(docente);
    }

    public List<Docente> listar(){return repositorio.obtenerTodos();}

}
