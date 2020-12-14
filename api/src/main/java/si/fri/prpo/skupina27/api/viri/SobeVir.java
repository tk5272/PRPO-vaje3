package si.fri.prpo.skupina27.api.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.storitve.zrna.SobeZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped //hkrati bo CDI zrno
@Path("sobe") //mnozinski samostalnik
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
// v Entitete/Soba dodaj JSONTransient, da se ne zacikla

public class SobeVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private SobeZrno sobeZrno;

    @GET
    @Operation(summary = "Gets all rooms", description = "Returns all the rooms.")
    @APIResponses({
            @APIResponse(description = "Success, all rooms", responseCode = "200",
                content = @Content(schema = @Schema(implementation = Soba.class))),
            @APIResponse(description = "Success, no rooms found", responseCode = "404",
                content = @Content(schema = @Schema(implementation = Soba.class)))
    })
    public Response vrniSobe() {
        /*List<Soba> sobe = sobeZrno.getSobe();
        return Response.status(Response.Status.OK).entity(sobe).build();*/
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();

        Long sobeCount = sobeZrno.getSobeCount(query);
        return Response
                .ok(sobeZrno.getSobe(query))
                .header("X-Total-Count", sobeCount)
                .build();
    }

    @GET
    @Path("{id}")
    public Response vrniSobo(@RequestBody(
            description = "ID of the room we want.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Soba.class)
            ))
            @PathParam("id") Integer id) {
        Soba soba = sobeZrno.getSoba(id);
        return Response.status(Response.Status.OK).entity(soba).build();
    }

    @POST
    public Response dodajSobo(@RequestBody(
            description = "Data of the room we want to add.",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = Soba.class)
            ))
          Soba soba) {
        return Response.status(Response.Status.CREATED).
                entity(sobeZrno.dodajSobo(soba)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiSobo(@PathParam("id") Integer id, Soba soba) {
        return Response.status(Response.Status.OK)
                .entity(sobeZrno.posodobiSobo(id, soba)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniSobo(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK)
                .entity(sobeZrno.odstraniSobo(id)).build();
    }
}
