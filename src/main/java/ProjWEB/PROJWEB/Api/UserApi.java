package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Service.Impl.UserService;
import ProjWEB.PROJWEB.Service.Impl.UserServiceImpl;



@Path("/users")
public class UserApi {
	
	private UserService userService = new UserServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() throws SQLException {
		return userService.getAllUsers();
	}

}
