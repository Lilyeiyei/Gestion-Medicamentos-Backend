package co.vinni.doctor.infraestructura;

import co.vinni.doctor.aplicacion.DoctorServicio;
import co.vinni.doctor.dominio.modelo.Doctor;
import co.vinni.doctor.infraestructura.dto.DoctorDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/doctores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorRecursos {

    @Inject
    DoctorServicio doctorServicio;

    @GET
    public Response listar() {
        try {
            List<DoctorDto> doctores = doctorServicio.listarDoctores()
                    .stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
            return Response.ok(doctores).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener los doctores").build();
        }
    }

    @POST
    public Response registrar(DoctorDto dto) {
        try {
            if (dto.getCedula() == null || dto.getCedula().isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("La cédula es obligatoria").build();
            }
            if (dto.getCorreoElectronico() == null || dto.getCorreoElectronico().isBlank()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("El correo electrónico es obligatorio").build();
            }
            Doctor doctor = new Doctor(dto.getCedula(), dto.getNombreCompleto(),
                    dto.getEspecialidad(), dto.getCorreoElectronico(), dto.getTelefono());
            Doctor nuevo = doctorServicio.registrarDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(toDto(nuevo)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al registrar el doctor").build();
        }
    }

    private DoctorDto toDto(Doctor doctor) {
        DoctorDto dto = new DoctorDto();
        dto.setCedula(doctor.getCedula());
        dto.setNombreCompleto(doctor.getNombreCompleto());
        dto.setEspecialidad(doctor.getEspecialidad());
        dto.setCorreoElectronico(doctor.getCorreoElectronico());
        dto.setTelefono(doctor.getTelefono());
        return dto;
    }
}