package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import ProjWEB.PROJWEB.Dao.ImageDao;
import ProjWEB.PROJWEB.Dao.OrderDao;
import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Domain.Order_image;
import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.OrderDto;
import ProjWEB.PROJWEB.Domain.Dto.OrderSaveDto;
import ProjWEB.PROJWEB.Service.MailService;
import ProjWEB.PROJWEB.Service.OrderService;
import ProjWEB.PROJWEB.Service.ResolutionService;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao = new OrderDao();
	private ResolutionService resolutionService = new ResolutionServiceImpl();
	private ImageDao imageDao = new ImageDao();
	private MailService mailService = new MailService();

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
        
		OrderDto dto = new OrderDto();
		
		/*
		 * setovanje parametara za odgovor
		 */
		List<Image> dtoImages = new ArrayList<>();
		User user = new User();
		user.setId(userId);
		
		dto.setUser(user);
		dto.setImages(dtoImages);
		
		/*
		 * trazimo ne plceni order
		 */
		ArrayList<Order> orderList = orderDao.findByUserId(userId, "unpaid");
		Order order = orderList.get(0);
		
		/*
		 * iz tabele order_slika trazimo sve slike koje su vezane za taj order
		 * iteriramo kroz te zapise i za svaki izvlacimo sliku pa rezolucij
		 * za nju i stavljamo u dto.setList
		 */
		ArrayList<Order_image> orderImageList = orderDao.findByOrderId(order.getId());
		
		for (Order_image order_image : orderImageList) {
			ArrayList<Image> images = imageDao.findImageById(order_image.getImageId());
			Image image = images.get(0);
			Resolution resolutionForImage = resolutionService.getResolutionForImage(order_image.getImageId(), order_image.getResolution());
			image.setUrl(resolutionForImage.getBase64());
			dto.getImages().add(image);
		}
				
		return dto;
	}

	@Override
	public OrderDto getHistoryForUser(long userId) throws SQLException {
    OrderDto dto = new OrderDto();
		
		/*
		 * setovanje parametara za odgovor
		 */
		List<Image> dtoImages = new ArrayList<>();
		User user = new User();
		user.setId(userId);
		
		dto.setUser(user);
		dto.setImages(dtoImages);
		
		//lista placeinh ordera svaki ima jednu ili vise slika
		ArrayList<Order> orderList = orderDao.findByUserId(userId, "paid");
		for (Order order : orderList) {
			
			ArrayList<Order_image> orderImageList = orderDao.findByOrderId(order.getId());//za svaki order uzmem slike vezane za taj order
			for (Order_image order_image : orderImageList) {
				ArrayList<Image> images = imageDao.findImageById(order_image.getImageId());
				Image image = images.get(0);
				Resolution resolutionDto = resolutionService.getResolutionForImage(order_image.getImageId(), order_image.getResolution());
				image.setUrl(resolutionDto.getBase64());
				dto.getImages().add(image);
			}	
		}
		return dto;
	}

	@Override
	public Order payCart(long userId) throws SQLException, MessagingException {
		
		
		ArrayList<Order> orderUnpaidList = orderDao.findByUserId(userId, "unpaid");
		Order orderUnpaid = orderUnpaidList.get(0);
		
		
       ArrayList<Order_image> orderImageList = orderDao.findByOrderId(orderUnpaid.getId());
       ArrayList<Image> imagesForMail = new ArrayList<>();
       
		List<Resolution> resolutions = new ArrayList<>();
		for (Order_image order_image : orderImageList) {
			ArrayList<Image> images = imageDao.findImageById(order_image.getImageId());
			Image image = images.get(0);
			System.out.println("pre poziva");
			Resolution resolutionDto = resolutionService.getResolutionForImage(order_image.getImageId(), order_image.getResolution());
//			image.setUrl(resolutionDto);
//			imagesForMail.add(image);
			resolutions.add(resolutionDto);
		}
		
		mailService.sendWithAttachment("mmasulovic17@raf.rs", "proba", resolutions);
		Order order = new Order();
		order.setUserId(userId);
		int updateResult = orderDao.payOrder(userId);
		if(updateResult > 0) {
			long saveResult = orderDao.saveBlankOrder(order);
			System.out.println("SAVE RES:"+saveResult);
			if(saveResult > 0) {
				order.setId(saveResult);
				order.setUserId(userId);
				//order.setOrderDate(orderDate);
			}
		}
		System.out.println(updateResult);
		return order;
	}

	@Override
	public Order getCart(long userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long saveBlankOrder(long userId) throws SQLException {
		Order order = new Order();
		order.setUserId(userId);
		order.setOrderSatus("unpaid");
		long orderId = 0;
		orderId = orderDao.saveBlankOrder(order);
		if(orderId > 0) return orderId; 
		
		return 0;
	}

}
