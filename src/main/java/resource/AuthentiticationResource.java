package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.JWTHelper;
import entity.Credentials;
import entity.Personne;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("auth")
@Tag(name = "Authentication")
public class AuthentiticationResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(summary = "Authentication with credentials", description = "Authentication with credentials, to return JWT token, needed for each request.", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Personne.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response auth(Credentials credentials) {
		return Response.ok(JWTHelper.generateToken(credentials)).build();
	}

}
