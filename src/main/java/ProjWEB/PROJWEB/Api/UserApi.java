package ProjWEB.PROJWEB.Api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/users")
public class UserApi {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void getUsers() {
		System.out.println("GOT IT!");
	}

}
