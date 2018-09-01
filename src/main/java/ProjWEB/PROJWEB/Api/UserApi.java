package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;
import ProjWEB.PROJWEB.Service.UserService;
import ProjWEB.PROJWEB.Service.Impl.UserServiceImpl;



@Path("/users")
public class UserApi {
	
	private UserService userService = new UserServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() throws SQLException {
		return userService.getAllUsers();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public UserLoginResponse login(UserLoginDto user) throws SQLException {
		System.out.println("LOGIN::");
		return userService.login(user);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register")
	public User addUser(User u) {
		return userService.addUser(u);
	}

}
