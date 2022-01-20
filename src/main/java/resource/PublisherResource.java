package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.PublisherBusiness;

@Path("publisher")
public class PublisherResource {

	PublisherBusiness publisherBusiness = new PublisherBusiness();

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(String id, String data) {
		publisherBusiness.publish(id, data);
		return Response.noContent().build();
	}
/*	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		return Response.ok(personneBusiness.getAll()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		return Response.ok(personneBusiness.get(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Personne p) {
		personneBusiness.add(p);
		return Response.noContent().build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Personne p) {
		return Response.ok(personneBusiness.update(p)).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(Personne p) {
		personneBusiness.delete(p);
		return Response.noContent().build();
	}

	@GET
	@Path("_search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom) {
		return Response.ok(personneBusiness.search(nom, prenom)).build();
	} */

}
