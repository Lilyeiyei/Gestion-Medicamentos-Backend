package co.vinni.docentes.dominio.repositorio;

import co.vinni.docentes.dominio.modelo.Docente;
import co.vinni.docentes.dominio.modelo.DocenteEntity;

import java.util.List;

public interface DocenteRepositorio {
    void crear(Docente docente);
    List<Docente> obtenerTodos();

}
