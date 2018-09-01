package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;



public interface UserService {
	
    public List<User> getAllUsers() throws SQLException;
	
	public User getUserByUsername(String username);
	
	public User editUser(long id, User user);
	
	public void deleteUser(long id);
	
	public long register(User user) throws SQLException ;

	public UserLoginResponse login(UserLoginDto user) throws SQLException ;
	
	public List<User> getUsersByRole(String role);
	
	public boolean deleteUser(int userId);
	
	public User editUser(User user);
	
	//public int changePassword(ChangePasswordDto dto);
	
	public String approveCompany(long companyId) throws SQLException ;
	
	public String changeCompany(String username,long companyId) throws SQLException ;
	
	public List<User> getUsersForCompany(long companyId) throws SQLException ;
	
	public List<User> findAllOperatorsForAdmin() throws SQLException ;

}
