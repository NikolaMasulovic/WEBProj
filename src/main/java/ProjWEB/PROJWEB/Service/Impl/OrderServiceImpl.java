package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ProjWEB.PROJWEB.Dao.ImageDao;
import ProjWEB.PROJWEB.Dao.OrderDao;
import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Domain.Dto.OrderDto;
import ProjWEB.PROJWEB.Domain.Dto.OrderSaveDto;
import ProjWEB.PROJWEB.Service.OrderService;
import ProjWEB.PROJWEB.Service.ResolutionService;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao = new OrderDao();
	private ResolutionService resolutionService = new ResolutionServiceImpl();
	private ImageDao imageDao = new ImageDao();

	@Override
	public List<Order> findAll() {
		List<Order> orders = new ArrayList<>();
		try {
			orders = orderDao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public void save(OrderSaveDto order) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderDto findByUserId(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getHistoryForUser(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order payCart(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getCart(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveBlankOrder(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
