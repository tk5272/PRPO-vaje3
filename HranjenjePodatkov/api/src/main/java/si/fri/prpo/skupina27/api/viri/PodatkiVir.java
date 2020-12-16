package si.fri.prpo.skupina27.api.viri;


import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Zapis;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Logger;

@ApplicationScoped
@Path("zasedenost")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PodatkiVir {

    private Logger log = Logger.getLogger(PodatkiVir.class.getName());
    private ArrayList<Zapis> zapisi;

    @PostConstruct
    private void init() {
        zapisi = new ArrayList<>();
    }

    @GET
    public Response vrniOsebe() {
                return Response.status(Response.Status.OK).entity(zapisi).build();
    }

    @POST
    public Response dodajOsebo(Zapis z) {
        zapisi.add(z);
        log.info("Zapis je dodan: " + z);

        return Response.status(Response.Status.CREATED).
                entity(z).build();
    }
    /*
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
    }*/
}
