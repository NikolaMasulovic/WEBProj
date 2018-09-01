package ProjWEB.PROJWEB.Service.Impl;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.User;



public interface UserService {
	
    public List<User> getAllUsers() throws SQLException;
	
	public User getUserByUsername(String username);
	
	public User editUser(long id, User user);
	
	public void deleteUser(long id);
	
	public User addUser(User user);

	//public UserResponseDto login(UserDto user);
	
	public List<User> getUsersByRole(String role);
	
	public boolean deleteUser(int userId);
	
	public User editUser(User user);
	
	//public int changePassword(ChangePasswordDto dto);
	
	public String approveCompany(long companyId) throws SQLException ;
	
	public String changeCompany(String username,long companyId) throws SQLException ;
	
	public List<User> getUsersForCompany(long companyId) throws SQLException ;
	
	public List<User> findAllOperatorsForAdmin() throws SQLException ;

}
