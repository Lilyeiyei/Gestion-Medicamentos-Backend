package com.tuempresa.medicamentos.controller;

import com.tuempresa.medicamentos.dto.request.MedicamentoRequestDTO;
import com.tuempresa.medicamentos.dto.response.MensajeResponseDTO;
import com.tuempresa.medicamentos.service.MedicamentoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/medicamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicamentoController {

    @Inject
    MedicamentoService service;

    @POST
    public Response registrar(
            @Valid MedicamentoRequestDTO dto){

        service.registrar(dto);

        return Response.ok(
                new MensajeResponseDTO(
                        "Medicamento registrado exitosamente"
                )
        ).build();
    }

    @GET
    public Response listar(){

        return Response.ok(
                service.listar()
        ).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(
            @PathParam("id") Long id,
            @Valid MedicamentoRequestDTO dto){

        return Response.ok(
                service.actualizar(id, dto)
        ).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(
            @PathParam("id") Long id){

        service.eliminar(id);

        return Response.ok().build();
    }
}