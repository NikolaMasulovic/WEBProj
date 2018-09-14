package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Domain.Dto.OrderDto;
import ProjWEB.PROJWEB.Domain.Dto.OrderSaveDto;

public interface OrderService {
	
public List<Order> findAll();
	
	public void save(OrderSaveDto order);
	
	public OrderDto findByUserId(long userId) throws SQLException;
	
	public OrderDto getHistoryForUser(long userId) throws SQLException;
	
	public Order payCart(long userId) throws SQLException, MessagingException ;
	
	public Order getCart(long userId) throws SQLException;
	
	public int saveBlankOrder(long userId) throws SQLException;

}
