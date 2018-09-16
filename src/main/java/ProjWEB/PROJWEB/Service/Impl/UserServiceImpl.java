package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ProjWEB.PROJWEB.Dao.RatingDao;
import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.Rating;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.OrderDto;
import ProjWEB.PROJWEB.Domain.Dto.UserChangePasswordDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;
import ProjWEB.PROJWEB.Service.OrderService;
import ProjWEB.PROJWEB.Service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao = new UserDao();
	private OrderService orderService = new OrderServiceImpl();
	private RatingDao ratingDao = new RatingDao();

	@Override
	public List<User> getAllUsers() throws SQLException {
		return userDao.findAll();
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(long id) throws SQLException {
		
		boolean response = false;
		if(userDao.delete(id,1) > 0) response = true;
		return response;
	}

	@Override
	public long register(User user) throws SQLException {
		return userDao.save(user);
	}

	@Override
	public List<User> getUsersByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(User user) throws SQLException {	
		return userDao.update(user);
	}

	@Override
	public String approveCompany(long companyId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeCompany(String username, long companyId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersForCompany(long companyId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllOperatorsForAdmin() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserLoginResponse login(UserLoginDto user) throws SQLException {
		UserLoginResponse userResponse = new UserLoginResponse();
		User u = userDao.login(user);
		userResponse.setUser(u);
		OrderDto orderDto = orderService.findByUserId(u.getId());
		userResponse.setOrder(orderDto);
		
		return userResponse;
	}

	@Override
	public boolean changePassword(UserChangePasswordDto dto) throws SQLException {
		boolean result = false;
		if(userDao.changePassword(dto) > 0) result = true;
		return result;
	}

	@Override
	public boolean becomeSeller(long userId) throws SQLException {
		boolean queryResult = false;
		if(userDao.becomeSeller(userId, 3) > 0) queryResult = true;
		return queryResult;
	}

	@Override
	public boolean userSendTest(long userId) throws SQLException {
		boolean queryResult = false;
		if(userDao.becomeSeller(userId, 10) > 0) queryResult = true;
		return queryResult;
	}

	@Override
	public boolean adminAddOperater(long userId) throws SQLException {
		boolean queryResult = false;
		if(userDao.becomeSeller(userId, 1) > 0) queryResult = true;
		return queryResult;
	}

	@Override
	public boolean forbidAccess(long userId) throws SQLException {
		
		if(userDao.delete(userId, 1) > 0) return true;
		return false;
	}

	@Override
	public boolean uploadDailyAndWeekly(User user) throws SQLException {
		if(userDao.updateDaily(user) > 0) return true;
		return false;
	}

	@Override
	public boolean rateUser(long userId, int rate) throws SQLException {
		
		ArrayList<User> userList = userDao.findUserById(userId);
		User u = userList.get(0);
		boolean result = false;
		
		double rateDb = 0;
		ArrayList<Rating> ratings = ratingDao.findAllByUserId(userId);
		int ocene = 0;
		int count = 0;
		for (Rating rating : ratings) {
			ocene += rating.getOcena();
			count++;
		}
		ocene += rate;
		count = count+1;
		
		rateDb = (double)ocene/(double)count;
		u.setRate(rateDb);
		User res = userDao.update(u);
		if(res!=null) {
			result = true;
		}
		
		//danasnji datum za save rating
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String datePublished = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
				
		Rating rateNew = new Rating(0, rate, datePublished, userId, 0);
		ratingDao.save(rateNew);
		
		return result;
	}

}
