package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Service.OrderService;
import ProjWEB.PROJWEB.Service.Impl.OrderServiceImpl;

@Path("/order")
public class OrderApi {

	
	private OrderService orderService = new OrderServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getUsers() throws SQLException{
		return orderService.findAll();
	}
	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/login")
//	public UserLoginResponse login(UserLoginDto user) throws SQLException {
//		System.out.println("LOGIN::");
//		return userService.login(user);
//	}
}
