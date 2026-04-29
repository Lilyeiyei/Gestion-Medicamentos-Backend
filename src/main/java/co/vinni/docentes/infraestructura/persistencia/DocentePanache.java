package co.vinni.docentes.infraestructura.persistencia;

import co.vinni.docentes.dominio.modelo.Docente;
import co.vinni.docentes.dominio.modelo.DocenteEntity;
import co.vinni.docentes.dominio.repositorio.DocenteRepositorio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DocentePanache implements DocenteRepositorio, PanacheRepository<DocenteEntity>{

    @Override
    @Transactional
    public void crear(Docente docente) {
        DocenteEntity docenteEntity = DocenteEntity
                .builder()
                .nombre(docente.nombre)
                .apellido(docente.apellido)
                .email(docente.email)
                .build();
        persist(docenteEntity);

    }

    @Override
    public List<Docente> obtenerTodos() {
        return listAll().stream().map(
                entidad ->{
                    Docente docente = Docente
                            .builder()
                            .nombre(entidad.nombre)
                            .apellido(entidad.apellido)
                            .email(entidad.email)
                            .build();
                    return docente;
                }
        ).toList();
    }
}
