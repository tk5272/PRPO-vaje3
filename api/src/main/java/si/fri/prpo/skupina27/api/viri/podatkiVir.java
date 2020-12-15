package si.fri.prpo.skupina27.api.viri;


import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.prpo.skupina27.entitete.Oseba;
import si.fri.prpo.skupina27.entitete.Soba;
import si.fri.prpo.skupina27.storitve.dtos.ZapisDto;
import si.fri.prpo.skupina27.storitve.zrna.OsebeZrno;
import si.fri.prpo.skupina27.storitve.zrna.PoslovneMetodeZrno;
import si.fri.prpo.skupina27.storitve.zrna.SobeZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped //hkrati bo CDI zrno
@Path("podatki") //mnozinski samostalnik
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class podatkiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private SobeZrno sobeZrno;

    @Inject
    private OsebeZrno osebeZrno;

    @Inject
    private PoslovneMetodeZrno pZrno;

    @POST
    @Path("{idOsebe}/{idSobe}")
    public Response dodajZapis(
             @PathParam("idOsebe") Integer osebaId,
             @PathParam("idSobe") Integer sobaId) {

        System.out.println(osebaId);

        Oseba o = osebeZrno.getOseba(osebaId);
        Soba s = sobeZrno.getSoba(sobaId);

        pZrno.dodajZapis(o, s);

        return Response.status(Response.Status.OK).build();
    }







}
