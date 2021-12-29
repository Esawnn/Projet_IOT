package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.PersonneBusiness;
import entity.Personne;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("personnes")
@Tag(name = "Personne")
public class PersonneResource {

	PersonneBusiness personneBusiness = new PersonneBusiness();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get all Personnes", description = "Get all Personnes", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Personne.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response getAll() {
		return Response.ok(personneBusiness.getAll()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get an Personne", description = "Get an Personne by its id", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personne.class))),
			@ApiResponse(responseCode = "404", description = "Personne not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response get(@PathParam("id") long id) {
		return Response.ok(personneBusiness.get(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Add an Personne", description = "Add an Personne", responses = {
			@ApiResponse(responseCode = "200", description = "Succes"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response add(Personne p) {
		personneBusiness.add(p);
		return Response.noContent().build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Update an Personne", description = "Update an Personne by its id", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personne.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response update(@PathParam("id") long id, Personne p) {
		return Response.ok(personneBusiness.update(p)).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Delete an Personne", description = "Delete an Personne by its id", responses = {
			@ApiResponse(responseCode = "200", description = "Succes"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response delete(Personne p) {
		personneBusiness.delete(p);
		return Response.noContent().build();
	}

	@GET
	@Path("_search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Search an Personne", description = "Search an Personne by its name or nickname", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Personne.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response search(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom) {
		return Response.ok(personneBusiness.search(nom, prenom)).build();
	}

}
