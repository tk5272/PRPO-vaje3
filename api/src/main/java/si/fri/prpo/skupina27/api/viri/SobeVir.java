package si.fri.prpo.skupina27.api.viri;


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
    public Response vrniSobe() {

        //QueryParameters query =
        List<Soba> sobe = sobeZrno.getSobe();
        return Response.status(Response.Status.OK).entity(sobe).build();
    }

    @GET
    @Path("{id}")
    public Response vrniSobo(@PathParam("id") Integer id) {
        Soba soba = sobeZrno.getSoba(id);
        return Response.status(Response.Status.OK).entity(soba).build();
    }

    @POST
    public Response dodajSobo(Soba soba) {
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
