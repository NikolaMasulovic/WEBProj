package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import ProjWEB.PROJWEB.Domain.User;
import ProjWEB.PROJWEB.Domain.Dto.UserChangePasswordDto;
import ProjWEB.PROJWEB.Domain.Dto.UserLoginDto;


public class UserDao {
	Connection con;
	Statement st;
	ResultSet rs;
	
	public UserDao() {	}
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<User> findAll() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.User;";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long userId = Long.parseLong(rs.getString("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = Integer.parseInt(rs.getString("role"));
				String email = rs.getString("email");
				String country = rs.getString("country");
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				long companyId = Long.parseLong(rs.getString("companyId"));
				int deleted = Integer.parseInt(rs.getString("deleted"));
				int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				User user = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
				list.add(user);
				System.out.println();
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::FIND-ALL");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	public long save(User user) throws SQLException {
		loadDB();
		long id =0;
		/*
		 * Prvo provera za username,ako vec postoji ne moze
		 */
		ArrayList<User> userById = findUserById(user.getId());
		if(userById.size() > 0) {
			System.out.println("NO REGISTER::USERNAME IS ALREADY TAKEN");
			return 0;
		}else {
		int res = 0;
		String sql ="";
        try {
            con.setAutoCommit(false);
            System.out.println(user);
            sql = "insert into User (username, password, email, country, role, daily, weekly,companyId,deleted,rate,version)"
                    + "values ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getEmail() + "','" +
            		user.getCountry() + "',"+user.getRole()+ ",'"+ String.valueOf(user.getDaily()) + "','"+String.valueOf(user.getWeekly())+"',"+user.getCompanyId()+","+user.getDeleted()+",0,0)";
            res = st.executeUpdate(sql);
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL ERROR::SAVE-USER:");
            con.rollback();
        }
        st.close();
        id = 0;
        sql = "SELECT * FROM webProjDB.user WHERE id = LAST_INSERT_ID();";
        System.out.println(sql);
        PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
        	id = resultSet.getInt("id");
        	System.out.println("USER ID:"+id);
        }else {
        	System.out.println("SQL ERROR:LAST_INSERT_ID()");
        }
        con.close();
		}
        return id;
	}
	
	public User findUserByUsername(String username) throws SQLException {
		loadDB();
		User user = null;
		String sql = "SELECT * FROM webProjDB.User WHERE username ='"+username+"';";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				long userId = Long.parseLong(rs.getString("id"));
				String nusername = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String country = rs.getString("country");
				int role = Integer.parseInt(rs.getString("role"));
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				long companyId = Long.parseLong(rs.getString("companyId"));
				int deleted = Integer.parseInt(rs.getString("deleted"));
                int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				user = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return user;
	}

	public User login(UserLoginDto user) throws SQLException {
		loadDB();
		User u = null;
		String sql = "SELECT * FROM webProjDB.User\n" + 
				"WHERE username='"+user.getUsername()+"' AND password='"+user.getPassword()+"';";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				long userId = Long.parseLong(rs.getString("id"));
				String nusername = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String country = rs.getString("country");
				int role = Integer.parseInt(rs.getString("role"));
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				long companyId = Long.parseLong(rs.getString("companyId"));
				int deleted = Integer.parseInt(rs.getString("deleted"));
				int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				 u = new User(userId,nusername,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::LOGIN");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return u;
	}
	public ArrayList<User> findUsersByRole(String Userrole) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.User\n" + 
				"WHERE role='"+Userrole+"';";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long userId = Long.parseLong(rs.getString("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = Integer.parseInt(rs.getString("role"));
				String email = rs.getString("email");
				String country = rs.getString("country");
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				long companyId = Long.parseLong(rs.getString("companyId"));
				int deleted = Integer.parseInt(rs.getString("deleted"));
                int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				User user = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
				list.add(user);
				System.out.println();
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}

	public int delete(long id) throws SQLException {
		loadDB();
		int res = 0;
		String sql = "UPDATE webProjDB.user SET deleted = 1 WHERE id="+id+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::DELETE");
		}finally {
			st.close();
			con.close();
		}
		return res;
	}
	
	public User update(User user) throws SQLException {
		loadDB();
		int res = 0;
		String sql = "UPDATE webProjDB.user " + 
				"SET username='"+user.getUsername()+"',password='"+user.getPassword()+"',email='"+user.getEmail()+"',"+
				"country='"+user.getCountry()+"',"+"role="+user.getRole()+","+"daily="+user.getDaily()+","+"weekly="+user.getWeekly()+",companyId="+user.getCompanyId()+",kartica='"+user.getKartica()+
				"' WHERE id="+user.getId()+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::UPDATE");
		}finally {
			st.close();
			con.close();
		}
		return user;
	}
	
	/*
	 * Ako mu se prosledi rola 10 znaci da je samo poslao test ali jos nije approve znaci ceka da bude approve i prodavac,
	 * ako mu se prosledi rola 3 test mu je approved i stvarno je postao prodavac
	 * ako se prosledi rola 1 znaci da je Admin dodao novog operatera
	 */
	public int becomeSeller(long userId,int role) throws SQLException {
		loadDB();
		int res = 0;
		String sql = "UPDATE webProjDB.user " + 
				"SET role="+role+" WHERE id="+userId+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::UPDATE");
		}finally {
			st.close();
			con.close();
		}
		return res;
	}
	
	public ArrayList<User> findUserById(long id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		loadDB();
		User user = new User();
		String sql = "SELECT * FROM webProjDB.User WHERE id = "+id+" ;";
		System.out.println(sql);
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long userId = Long.parseLong(rs.getString("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = Integer.parseInt(rs.getString("role"));
				String email = rs.getString("email");
				String country = rs.getString("country");
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				long companyId = Long.parseLong(rs.getString("companyId"));
				int deleted = Integer.parseInt(rs.getString("deleted"));
                int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				user = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
			 System.out.println("+++"+user);	
			 list.add(user);
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public int changePassword(UserChangePasswordDto user) throws SQLException {
		loadDB();
		String sql ="update webProjDB.user set password ='"+user.getNewPassword()+"' where id = "+user.getUserId()+";";
		System.out.println(sql);
		int res =0;
		try {
			res = st.executeUpdate(sql);
			System.out.println("++++"+res);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			//rs.close();
			st.close();
			con.close();
		}
		return res;
	}
	
//	public User getLastUpdated() throws SQLException {
//		
//		loadDB();
//		String sql ="SELECT * FROM webProjDB.user WHERE id = LAST_INSERT_ID();";
//		System.out.println(sql);
//		try {
//			rs = st.executeQuery(sql);
//			if(rs.next()) {
//				System.out.println("Poslednji update id-"+rs.getInt("id"));
//			}
//			
//		}catch(SQLException ex) {
//			ex.printStackTrace();
//		}finally {
//			rs.close();
//			st.close();
//			con.close();
//		}
//		return null;
//	}
	
	public User getUserByCompanyId(long  companyId) throws SQLException {
		loadDB();
		User u = new User();
		String sql = "SELECT * FROM webProjDB.user where companyId = "+companyId+";";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long userId = Long.parseLong(rs.getString("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = Integer.parseInt(rs.getString("role"));
				String email = rs.getString("email");
				String country = rs.getString("country");
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				int deleted = Integer.parseInt(rs.getString("deleted"));
                int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				u = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
				
				System.out.println();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL ERROR::");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return u;
	}
	
	public List<User> getUserByCompanyIdList(long  companyId) throws SQLException {
		loadDB();
		List<User> users = new ArrayList<>();
		
		String sql = "SELECT * FROM webProjDB.user where companyId = "+companyId+";";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				User u = new User();
				long userId = Long.parseLong(rs.getString("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = Integer.parseInt(rs.getString("role"));
				String email = rs.getString("email");
				String country = rs.getString("country");
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				int deleted = Integer.parseInt(rs.getString("deleted"));
                int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));
				
				u = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
				users.add(u);
				System.out.println();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL ERROR::");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return users;
	}
	
	public List<User> findAllOperatorsForAdmin() throws SQLException {
		loadDB();
		List<User> users = new ArrayList<>();
		
		String sql = "SELECT * FROM webProjDB.user where companyId = 0 AND role=1;";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				User u = new User();
				long userId = Long.parseLong(rs.getString("id"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = Integer.parseInt(rs.getString("role"));
				String email = rs.getString("email");
				String country = rs.getString("country");
				int daily = rs.getInt("daily");
				int weekly = rs.getInt("weekly");
				String kartica = rs.getString("kartica");
				long companyId = Long.parseLong(rs.getString("companyId"));
				int deleted = Integer.parseInt(rs.getString("deleted"));
                int rate = Integer.parseInt(rs.getString("rate"));
				int version = Integer.parseInt(rs.getString("version"));

				u = new User(userId,username,password,role,email,country,daily,weekly,kartica,companyId,deleted,rate,version);
				users.add(u);
				System.out.println();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL ERROR::");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return users;
	}	
}