package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.UserChangePasswordDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;
import ProjWEB.PROJWEB.Service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao = new UserDao();

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
		if(userDao.delete(id) > 0) response = true;
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
		return userResponse;
	}

	@Override
	public int changePassword(UserChangePasswordDto dto) throws SQLException {
		return userDao.changePassword(dto);
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

}
