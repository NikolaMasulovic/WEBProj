package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.UserChangePasswordDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;
import ProjWEB.PROJWEB.Service.MailService;
import ProjWEB.PROJWEB.Service.UserService;
import ProjWEB.PROJWEB.Service.Impl.UserServiceImpl;



@Path("/users")
public class UserApi {
	
	private UserService userService = new UserServiceImpl();
	private MailService mailService = new MailService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() throws SQLException, MessagingException {
		mailService.sendWithAttachment("nikola.masulovic@netcast.rs", "Proba");
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
	public long register(User u) throws SQLException {
		System.out.println("REGISTER::");
		return userService.register(u);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changePassword")
	public int changePassword(UserChangePasswordDto changeDto) throws SQLException {
		return userService.changePassword(changeDto);
	}

}
