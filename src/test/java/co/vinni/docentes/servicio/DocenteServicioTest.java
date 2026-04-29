package co.vinni.docentes.servicio;

import co.vinni.docentes.aplicacion.DocenteServicio;
import co.vinni.docentes.dominio.modelo.Docente;
import co.vinni.docentes.dominio.repositorio.DocenteRepositorio;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;


@QuarkusTest
public class DocenteServicioTest {
    @Inject
    DocenteServicio docenteServicio;

    @InjectMock
    DocenteRepositorio docenteRepositorio;
    private Docente docentePrueba;
    @BeforeEach
    public void setup(){
        docentePrueba = Docente
                .builder()
                .nombre("Elsa")
                .apellido("Patero")
                .email("correo@correo.com")
                .build();

    }
    @Test
    public void testCrearDocente(){
        docenteServicio.crear(docentePrueba);
        verify(docenteRepositorio, times(1)).crear(any(Docente.class));
    }
    @Test
    public void testListaDocentes(){
        Mockito.when(docenteRepositorio.obtenerTodos()).thenReturn(List.of(docentePrueba));
        var resultado = docenteServicio.listar();
        Assertions.assertFalse(resultado.isEmpty());
        Assertions.assertEquals("Elsa", resultado.get(0).nombre);
    }
}
