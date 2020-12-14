package si.fri.prpo.skupina27.api.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.storitve.izjeme.NeveljavenUporabnikIdIzjema;
import si.fri.prpo.skupina27.storitve.zrna.OsebeZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("osebe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class OsebeVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private OsebeZrno osebeZrno;

    @GET
    public Response vrniOsebe() {
        /*List<Oseba> osebe = osebeZrno.getOsebe();
        return Response.status(Response.Status.OK).entity(osebe).build();*/
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long osebeCount = osebeZrno.getOsebeCount(query);
        return Response
                        .ok(osebeZrno.getOsebe(query))
                        .header("X-Total-Count", osebeCount)
                        .build();
    }

    @GET
    @Path("{id}")
    public Response vrniOsebo(@PathParam("id") Integer id) {
        Oseba oseba = osebeZrno.getOseba(id);

        if (oseba != null) {
            return Response.status(Response.Status.OK).entity(oseba).build();
        } else {
            throw new NeveljavenUporabnikIdIzjema("Uporabnika ni mogoče najti. Neveljaven ID.");
        }
    }

    @POST
    public Response dodajOsebo(Oseba oseba) {
        return Response.status(Response.Status.CREATED).
                entity(osebeZrno.dodajOsebo(oseba)).build();
    }

    @PUT
    @Path("{id}")
    public Response posodobiOsebo(@PathParam("id") Integer id, Oseba oseba) {
        return Response.status(Response.Status.OK)
                .entity(osebeZrno.posodobiOsebo(id, oseba)).build();
    }

    @DELETE
    @Path("{id}")
    public Response odstraniOsebo(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK)
                .entity(osebeZrno.odstraniOsebo(id)).build();
    }
}
