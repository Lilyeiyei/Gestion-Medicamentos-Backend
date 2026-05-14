package co.vinni.pacientes.infraestructura;

import co.vinni.pacientes.aplicacion.PacienteServicio;
import co.vinni.pacientes.dominio.modelo.Paciente;
import co.vinni.pacientes.infraestructura.dto.PacienteDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacientesRecursos {

    @Inject
    PacienteServicio pacienteServicio;

    @POST
    @Operation(
            summary = "Registrar un nuevo paciente",
            description = "Registra un paciente con nombre completo, documento, EPS, correo y contraseña"
    )
    @APIResponse(responseCode = "201", description = "Paciente registrado exitosamente")
    @APIResponse(responseCode = "400", description = "Datos de entrada inválidos o correo ya registrado")
    public Response registrar(@Valid PacienteDto pacienteDto) {
        Paciente paciente = Paciente.builder()
                .nombreCompleto(pacienteDto.nombreCompleto())
                .documentoIdentidad(pacienteDto.documentoIdentidad())
                .eps(pacienteDto.eps())
                .email(pacienteDto.email())
                .contrasena(pacienteDto.contrasena())
                .build();
        pacienteServicio.registrar(paciente);
        return Response.status(Response.Status.CREATED)
                .entity("Paciente registrado exitosamente")
                .build();
    }
}