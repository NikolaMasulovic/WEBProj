package ProjWEB.PROJWEB.Api;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Domain.Dto.OrderDto;
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
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderDto findByUserId(@PathParam("userId") long userId) throws SQLException {
		return orderService.findByUserId(userId);
	}
	
	@GET
	@Path("/history/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderDto getHistoryForUser(@PathParam("userId") long userId) throws SQLException {
		return orderService.getHistoryForUser(userId);
	}
	
//	@GET
//	@Path("/cart/{userId}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public OrderDto getCart(@PathParam("userId") long userId) throws SQLException {
//		return orderService.getHistoryForUser(userId);
//	}
	
	@GET
	@Path("/payCart/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Order payCart(@PathParam("userId") long userId) throws SQLException, MessagingException {
		return orderService.payCart(userId);
	}
	
}
