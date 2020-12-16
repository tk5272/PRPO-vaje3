package si.fri.prpo.skupina27.api.mappers;

import si.fri.prpo.skupina27.storitve.izjeme.NeveljavenUporabnikIdIzjema;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class NeveljavenUporabnikIdExceptionMapper implements ExceptionMapper<NeveljavenUporabnikIdIzjema> {
    @Override
    public Response toResponse(NeveljavenUporabnikIdIzjema exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("{\"napaka\":\"" + exception.getMessage() + "\"}")
                .build();
    }
}
