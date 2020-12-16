package si.fri.prpo.skupina27.api;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Column;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(title = "Sledilnik API", version ="v1",
                contact = @Contact(email = "sledilnik@gmail.com"),
                license = @License(name="dev"), description = "APi za sledilnik ljudi."),
                servers = @Server(url="http://localhost:8080/"))

//za ime uporabi mnozinski samostalnik
@ApplicationPath("v1")
public class SledilnikApplication extends javax.ws.rs.core.Application
{


}
