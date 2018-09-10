package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ProjWEB.PROJWEB.Domain.Tag;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.ResolutionStatisticDto;
import ProjWEB.PROJWEB.Domain.Dto.UserChangePasswordDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;
import ProjWEB.PROJWEB.Domain.Dto.UserStatisticDto;
import ProjWEB.PROJWEB.Service.MailService;
import ProjWEB.PROJWEB.Service.StatisticService;
import ProjWEB.PROJWEB.Service.UserService;
import ProjWEB.PROJWEB.Service.Impl.StatisticServiceimpl;
import ProjWEB.PROJWEB.Service.Impl.UserServiceImpl;

@Path("/users")
public class UserApi {
	
	private UserService userService = new UserServiceImpl();
	private MailService mailService = new MailService();
	private StatisticService statisticService = new StatisticServiceimpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() throws SQLException, MessagingException {
		//mailService.sendWithAttachment("nikola.masulovic@netcast.rs", "Proba");
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
	public Response register(User u) throws SQLException {
		System.out.println("REGISTER::");
		return Response.status(200).type("text/plain").entity(userService.register(u)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changePassword")
	public Response changePassword(UserChangePasswordDto changeDto) throws SQLException {
		return Response.status(200).type("text/plain").entity(userService.changePassword(changeDto)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{userId}")
	public boolean delete(@PathParam("userId") long userId) throws SQLException {
		return userService.deleteUser(userId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/forbidAccess/{userId}")
	public void forbidAccess(@PathParam("userId") long userId) throws SQLException {
		System.out.println(userId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/becomeSeller/{userId}")
	public boolean becomeSeller(@PathParam("userId") long userId) throws SQLException {
		return userService.becomeSeller(userId);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addOperater/{userId}")
	public boolean addOperater(@PathParam("userId") long userId) throws SQLException {
		return userService.adminAddOperater(userId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updatePaymentCard")
	public User updatePaymentCard(User user) throws SQLException {
		return userService.editUser(user);
	}
	/*
	 * statistic
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/categoryStatistic")
	public List<Tag> categoryStatistic() throws SQLException {
		return statisticService.categoryStatistic();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userStatistic")
	public List<UserStatisticDto> userStatisitc() throws SQLException {
		return statisticService.userStatistic();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/resolutionStatistic")
	public List<ResolutionStatisticDto> resolutionStatistic() throws SQLException {
		return statisticService.resolutionStatistic();
	}
	
}
