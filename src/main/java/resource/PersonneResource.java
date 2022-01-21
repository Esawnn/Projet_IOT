package resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.PersonneBusiness;
import business.PublisherBusiness;
import entity.Personne;
import entity.Publisher;
import entity.TabPublisher;

@Path("broker")
public class PersonneResource {

	PersonneBusiness personneBusiness = new PersonneBusiness();
	PublisherBusiness publisherBusiness = new PublisherBusiness();

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(@Context HttpHeaders headers,TabPublisher tabPublisher) {
		String authHeaders = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
		String TOKEN;
	
			if (authHeaders != null && authHeaders.toUpperCase().startsWith("BEARER ")) {
				TOKEN = authHeaders.substring("BEARER ".length());
			} else {
				TOKEN = "";
			}
		
		List<Publisher> mesPublishers = personneBusiness.subscribe(tabPublisher.getPublishers(), TOKEN);
		//return Response.ok(personneBusiness.getAllDataPersonne(mesPublishers)).build();
		System.out.println(publisherBusiness.getData());
		return Response.ok("\"id\":3, \"date\":\"12/10/2011\", \"temp\":25, \"humidite\":12, \"lum\":12\n|\"id\":2, \"date\":\"12/09/2021\", \"temp\":23, \"humidite\":10, \"lum\":10\n").build();
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
