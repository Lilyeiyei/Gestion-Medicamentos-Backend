package co.vinni.Medicamento.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import co.vinni.Medicamento.model.Medicamento;
import co.vinni.Medicamento.service.MedicamentoService;

@Path("/medicamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MedicamentoResource {

    @Inject
    MedicamentoService service;

    @POST
    public Medicamento registrar(Medicamento medicamento){

        return service.registrar(medicamento);

    }

}