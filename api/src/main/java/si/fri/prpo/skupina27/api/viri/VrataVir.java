package si.fri.prpo.skupina27.api.viri;

import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Vrata;
import si.fri.prpo.skupina27.storitve.zrna.OsebeZrno;
import si.fri.prpo.skupina27.storitve.zrna.VrataZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("vrata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class VrataVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private VrataZrno vrataZrno;

    @GET
    public Response vrniVsaVrata() {
        List<Vrata> vrata = vrataZrno.getAllVrata();
        return Response.status(Response.Status.OK).entity(vrata).build();
    }

    @GET
    @Path("{id}")
    public Response vrniVrata(@PathParam("id") Integer id) {
        Vrata vrata = vrataZrno.getVrata(id);
        return Response.status(Response.Status.OK).entity(vrata).build();
    }

    @POST
    public Response dodajVrata(Vrata vrata) {
        return Response.status(Response.Status.CREATED).
                entity(vrataZrno.dodajVrata(vrata)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniOsebo(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK)
                .entity(vrataZrno.odstraniVrata(id)).build();
    }

}
