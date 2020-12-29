package si.fri.prpo.skupina27.api;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Column;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

//keycloak username: prpoprojekt in geslo: prpoprojekt
//docker run -e KEYCLOAK_USER=prpoprojekt -e KEYCLOAK_PASSWORD=prpoprojekt -p 8082:8080 jboss/keycloak
//keycloak user: prpoprojekt:prpoprojekt


@DeclareRoles({"user", "employee", "admin"})
@OpenAPIDefinition(
        info = @Info(title = "Sledilnik API", version ="v1",
                contact = @Contact(email = "sledilnik@gmail.com"),
                license = @License(name="dev"), description = "APi za sledilnik ljudi."),
                servers = @Server(url="http://localhost:8080/"))


@ApplicationPath("v1")
public class SledilnikApplication extends javax.ws.rs.core.Application
{


}
