package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Dao.UserDao;
import ProjWEB.PROJWEB.Domain.User;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao = new UserDao();

	@Override
	public List<User> getAllUsers() throws SQLException {
		return userDao.selectAllUsers();
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User editUser(long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsersByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
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

}
