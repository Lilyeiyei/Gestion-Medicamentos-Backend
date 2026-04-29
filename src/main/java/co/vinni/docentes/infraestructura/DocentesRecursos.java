package co.vinni.docentes.infraestructura;

import co.vinni.docentes.aplicacion.DocenteServicio;
import co.vinni.docentes.dominio.modelo.Docente;
import co.vinni.docentes.infraestructura.dto.DocenteDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/docentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocentesRecursos {

    @Inject
    DocenteServicio docenteServicio;

    @POST
    @Operation(
        summary = "Crear un nuevo docente",
        description = "Registra el docente con sus datos nombre, apellido y correo"
    )
    @APIResponse(
        responseCode = "201",
        description = "Docente creado"
    )
    @APIResponse(
        responseCode = "400",
        description = "Datos de entrada invalidos"
    )
    public Response crear(@Valid DocenteDto docenteDto) {
        Docente docente = Docente
                .builder()
                .nombre(docenteDto.nombre())
                .apellido(docenteDto.apellido())
                .email(docenteDto.email())
                .build();
        docenteServicio.crear(docente);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Operation(summary = "Lista todos los docentes")
    public Response obtenerTodos() {
        List<DocenteDto> docenteDtos = docenteServicio.listar().stream()
                .map(docente -> new DocenteDto(docente.nombre,  docente.apellido, docente.email) )
                .toList();
        return Response.status(Response.Status.OK).entity(docenteDtos).build();
    }
}
