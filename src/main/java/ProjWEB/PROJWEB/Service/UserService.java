package ProjWEB.PROJWEB.Service;

import java.sql.SQLException;
import java.util.List;

import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.UserChangePasswordDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginResponse;



public interface UserService {
	
    public List<User> getAllUsers() throws SQLException;
	
	public User getUserByUsername(String username);
	
	//public User editUser(long id, User user) throws SQLException;
	
	public boolean deleteUser(long id) throws SQLException;
	
	public long register(User user) throws SQLException ;

	public UserLoginResponse login(UserLoginDto user) throws SQLException ;
	
	public List<User> getUsersByRole(String role);
	
	public User editUser(User user) throws SQLException ;
	
	public boolean changePassword(UserChangePasswordDto dto) throws SQLException ;
	
	public String approveCompany(long companyId) throws SQLException ;
	
	public String changeCompany(String username,long companyId) throws SQLException ;
	
	public List<User> getUsersForCompany(long companyId) throws SQLException ;
	
	public List<User> findAllOperatorsForAdmin() throws SQLException ;
	
	public boolean becomeSeller(long userId) throws SQLException ;
	
	public boolean userSendTest(long userId) throws SQLException ;
	
	public boolean adminAddOperater(long userId) throws SQLException ;

}
