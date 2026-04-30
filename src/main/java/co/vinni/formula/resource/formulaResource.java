package co.vinni.formula.resource;

import co.vinni.formula.model.formulaModel;
import co.vinni.formula.service.formulaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/formulas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class formulaResource {

    @Inject
    formulaService service;

    @POST
    public Response registrar(@Valid formulaModel formula) {
        try {
            service.registrarFormula(formula);
            return Response.status(Response.Status.CREATED).entity(formula).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al registrar la fórmula: " + e.getMessage())
                    .build();
        }
    }
}