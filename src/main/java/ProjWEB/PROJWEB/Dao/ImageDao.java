package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.User;
public class ImageDao {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public ImageDao() {	}
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<Image> findAll() throws SQLException {
		ArrayList<Image> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.slika WHERE approved = 1;";
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
				long id = Long.parseLong(rs.getString("id"));
				String datePublished = rs.getString("datePublished");
				int price = Integer.parseInt(rs.getString("price"));
				int numOfCopies = rs.getInt("numOfCopiesSelled");
				String name = rs.getString("name");
				String place = rs.getString("place");
				String description = rs.getString("description");
				long userId = Long.parseLong(rs.getString("userId"));
				String path = rs.getString("path");
				int approved = Integer.parseInt(rs.getString("approved"));
                double rate = rs.getDouble("rate");
				
				Image img = new Image(id,numOfCopies,datePublished,price,name,place,description,userId,path,approved,rate);
				list.add(img);
				System.out.println();
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::FIND ALL");
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public int findAllCount() throws SQLException {
		loadDB();
		int id =0;
		String sql = " SELECT COUNT(*)FROM webProjDB.slika WHERE approved = 1;";
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
			
				id = Integer.parseInt(rs.getString("COUNT(*)"));
				 
			}	
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::FIND ALL");
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return id;
	}
	
	public ArrayList<Image> home(int approvedNum,int offset) throws SQLException{
		ArrayList<Image> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.slika WHERE approved = "+approvedNum+" LIMIT 10 OFFSET "+offset+";";
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
				long id = Long.parseLong(rs.getString("id"));
				String datePublished = rs.getString("datePublished");
				int price = Integer.parseInt(rs.getString("price"));
				int numOfCopies = rs.getInt("numOfCopiesSelled");
				String name = rs.getString("name");
				String place = rs.getString("place");
				String description = rs.getString("description");
				long userId = Long.parseLong(rs.getString("userId"));
				String path = rs.getString("path");
				int approved = Integer.parseInt(rs.getString("approved"));
                double rate = Double.parseDouble(rs.getString("rate"));
				
				Image img = new Image(id,numOfCopies,datePublished,price,name,place,description,userId,path,approved,rate);
				list.add(img);
				System.out.println();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public int save(Image image) throws SQLException{
		loadDB();
		int result = 0;
		int id =0;
		String sql = "";
        try {
            con.setAutoCommit(false);
            System.out.println(image);
            sql = "INSERT into webProjDB.slika (numOfCopiesSelled, datePublished, price, name,place,description,userId,path,approved) values ("+0+",'"+image.getDatePublished()+"',"+image.getPrice()+",'"+image.getName()+"','"+image.getPlace()+"','"+image.getDescription()+"',"+image.getUserId()+",'"+image.getPath()+"',"+0+");";
            System.out.println(sql);
            result = st.executeUpdate(sql);
            System.out.println("____SUCCESS");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL ERROR::SAVE-IMAGE:");
            con.rollback();
        }
        st.close();
        id = 0;
        sql = "SELECT * FROM webProjDB.slika WHERE id = LAST_INSERT_ID();";
        System.out.println(sql);
        PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
        	id = resultSet.getInt("id");
        	System.out.println("IMAGE ID:"+id);
        }else {
        	System.out.println("SQL ERROR:LAST_INSERT_ID()");
        }
        con.close();
		
        return id;
	}
	public int approveTest(long userId) throws SQLException {
		loadDB();
		int res = 0;
		System.out.println("user"+userId);
		String sql = "UPDATE webProjDB.slika SET approved = 1 WHERE userId = "+userId+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::UPDATE APPROVED");
		}finally {
			st.close();
			con.close();
		}
		return res;
	}
	public int approveImage(long imageId) throws SQLException {
		loadDB();
		int res = 0;
		String sql = "UPDATE webProjDB.slika SET approved = 1 WHERE id = "+imageId+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::UPDATE APPROVED");
		}finally {
			st.close();
			con.close();
		}
		return res;
	}
	
	public ArrayList<Image> findAllByUserId(long user,int Isapproved) throws SQLException {
		ArrayList<Image> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.slika WHERE userId = "+user+" AND approved = "+Isapproved+";";
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
				long id = Long.parseLong(rs.getString("id"));
				String datePublished = rs.getString("datePublished");
				int price = Integer.parseInt(rs.getString("price"));
				int numOfCopies = rs.getInt("numOfCopiesSelled");
				String name = rs.getString("name");
				String place = rs.getString("place");
				String description = rs.getString("description");
				long userId = Long.parseLong(rs.getString("userId"));
				String path = rs.getString("path");
				int approved = Integer.parseInt(rs.getString("approved"));
                double rate = Double.parseDouble(rs.getString("rate"));
				
				Image img = new Image(id,numOfCopies,datePublished,price,name,place,description,userId,path,approved,rate);
				list.add(img);
				System.out.println();
			}
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::FIND ALL UNAPPROVED");
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public ArrayList<Image> findImageById(long sid) throws SQLException {
		ArrayList<Image> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.slika WHERE id = "+sid+";";
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
				long id = Long.parseLong(rs.getString("id"));
				String datePublished = rs.getString("datePublished");
				int price = Integer.parseInt(rs.getString("price"));
				int numOfCopies = rs.getInt("numOfCopiesSelled");
				String name = rs.getString("name");
				String place = rs.getString("place");
				String description = rs.getString("description");
				long userId = Long.parseLong(rs.getString("userId"));
				String path = rs.getString("path");
				int approved = Integer.parseInt(rs.getString("approved"));
				double rate = Double.parseDouble(rs.getString("rate"));
				
				Image img = new Image(id,numOfCopies,datePublished,price,name,place,description,userId,path,approved,rate);
				list.add(img);
				System.out.println();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public int updateRate(long imageId,double rate) throws SQLException {
		loadDB();
		int res = 0;
		String sql = "UPDATE webProjDB.slika SET rate = "+rate+" WHERE id = "+imageId+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL ERROR::UPDATE APPROVED");
		}finally {
			st.close();
			con.close();
		}
		return res;
	}
	
	/**
	 * SEARCH
	 */
	
//	public int searchCategoryCount(String searchFor, String sortBy, String direction, String searchTerm) {
//		String sql;
//		sql="SELECT count(*) AS count\n" + 
//				"FROM\n" + 
//				"(SELECT u.id user_id,u.username,u.ocena user_ocena,u.firmaId,b.photo_id,b.photo_name,b.photo_path,b.uploadDate,b.selledCount, b.ocena, b.price, b.deleted, b.approved, b.categoryName,b.category_id\n" + 
//				"FROM\n" + 
//				"unsplash2_db.user u\n" + 
//				"INNER JOIN (\n" + 
//				"SELECT c.id category_id, c.categoryName, p.id photo_id,p.photoName photo_name, p.path photo_path,p.uploadDate,p.selledCount,p.ocena, p.price, p.deleted, p.approved, p.userId\n" + 
//				"FROM \n" + 
//				"unsplash2_db.Category  AS c\n" + 
//				"INNER JOIN unsplash2_db.photo_category  AS p_c ON p_c.categoryId = c.id\n" + 
//				"INNER JOIN unsplash2_db.Photo AS p ON p_c.photoId = p.id\n" + 
//				") b ON u.id = b.userId\n" + 
//				") z \n" + 
//				"LEFT JOIN unsplash2_db.Firma f\n" + 
//				"ON f.id = z.firmaId\n" + 
//				"WHERE deleted = 0 AND approved= 1 AND categoryName LIKE ?;";
//				
//		loadDB();
//		PreparedStatement statement;
//		int count = 0;
//		try {
//			statement = con.prepareStatement(sql);
//			statement.setString(1, '%'+searchTerm+'%');
//			
//			ResultSet resultSet = statement.executeQuery();
////			String executedQuery = resultSet.getStatement().toString();
////			System.out.println(executedQuery);
//			if (resultSet.next()) {
//				count = resultSet.getInt("count");
//			}
//
//			resultSet.close();
//			statement.close();
//
//			//ConnectionManager.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return count;
//	}
//	public int searchKeywordCount(String searchFor, String sortBy, String direction, String searchTerm) {
//		String sql;
//		sql="SELECT count(*) AS count\n" + 
//				"FROM\n" + 
//				"(SELECT u.id user_id,u.username,u.ocena user_ocena,u.firmaId,p.id photo_id,p.photoName photo_name,p.uploadDate,p.path,p.selledCount,p.price,p.ocena photo_ocena,p.approved,p.deleted,p.location,p.opis\n" + 
//				"FROM\n" + 
//				"unsplash2_db.user u\n" + 
//				"INNER JOIN unsplash2_db.Photo p\n" + 
//				"ON u.id = p.userId\n" + 
//				") z\n" + 
//				"LEFT JOIN unsplash2_db.Firma f\n" + 
//				"ON f.id = z.firmaId\n" + 
//				"WHERE deleted = 0 AND approved= 1 AND (photo_name LIKE ? OR username LIKE ? OR f.naziv LIKE ? OR location LIKE ? OR opis LIKE ?)";
//		loadDB();
//		PreparedStatement statement;
//		int count = 0;
//		try {
//			statement = conn.prepareStatement(sql);
//			statement.setString(1, '%'+searchTerm+'%');
//			statement.setString(2, '%'+searchTerm+'%');
//			statement.setString(3, '%'+searchTerm+'%');
//			statement.setString(4, '%'+searchTerm+'%');
//			statement.setString(5, '%'+searchTerm+'%');
//			
//			ResultSet resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				count = resultSet.getInt("count");
//			}
//			
//			resultSet.close();
//			statement.close();
//			
//			ConnectionManager.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return count;
//	}
//	public int searchUserNameCount(String searchFor, String sortBy, String direction, String searchTerm) {
//		String sql;
//		sql="SELECT count(*) AS count\n" + 
//				"FROM\n" + 
//				"(SELECT u.id user_id,u.username,u.ocena user_ocena,u.firmaId,p.id photo_id,p.photoName photo_name,p.uploadDate,p.path,p.selledCount,p.price,p.ocena photo_ocena,p.approved,p.deleted\n" + 
//				"FROM\n" + 
//				"unsplash2_db.user u\n" + 
//				"INNER JOIN unsplash2_db.Photo p\n" + 
//				"ON u.id = p.userId\n" + 
//				") z\n" + 
//				"LEFT JOIN unsplash2_db.Firma f\n" + 
//				"ON f.id = z.firmaId\n" + 
//				"WHERE deleted = 0 AND approved = 1 AND "+searchFor+" LIKE ?";
//		loadDB();
//		PreparedStatement statement;
//		int count = 0;
//		try {
//			statement = conn.prepareStatement(sql);
//			statement.setString(1, '%'+searchTerm+'%');
////			statement.setInt(2, b);
//			
//			ResultSet resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				count = resultSet.getInt("count");
//			}
//			
//			resultSet.close();
//			statement.close();
//			
//			ConnectionManager.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return count;
//	}
//	
//	public List<SeptPhotoDto> searchUserName(String searchFor, String sortBy, String direction, String searchTerm,int itemsPerPage, int pagenumber) {
//		String sql;
//		sql="SELECT f.id firma_id, f.naziv firma_naziv,f.level firma_nivo,z.user_id,z.username,z.user_ocena,z.firmaId, z.photo_id,z.photo_name,z.uploadDate, z.path, z.selledCount, z.price, z.photo_ocena,z.approved,z.deleted\n" + 
//				"FROM\n" + 
//				"(SELECT u.id user_id,u.username,u.ocena user_ocena,u.firmaId,p.id photo_id,p.photoName photo_name,p.uploadDate,p.path,p.selledCount,p.price,p.ocena photo_ocena,p.approved,p.deleted\n" + 
//				"FROM\n" + 
//				"unsplash2_db.user u\n" + 
//				"INNER JOIN unsplash2_db.Photo p\n" + 
//				"ON u.id = p.userId\n" + 
//				") z\n" + 
//				"LEFT JOIN unsplash2_db.Firma f\n" + 
//				"ON f.id = z.firmaId\n" + 
//				"WHERE deleted = 0 AND approved = 1 AND "+searchFor+" LIKE ?\n" + 
//				"ORDER BY CASE WHEN firma_nivo = 'zlatni' THEN '0'\n" + 
//				"								WHEN user_ocena >= 4.0 AND user_ocena <=5.0 THEN '1'\n" + 
//				"                                WHEN firma_nivo = 'srebrni' THEN '2'\n" + 
//				"                                WHEN user_ocena >= 3.0 AND user_ocena <=4.0 THEN '3'\n" + 
//				"                                WHEN firma_nivo = 'bronzani' THEN '4'\n" + 
//				"								ELSE "+sortBy+"\n" + 
//				"							END,"+sortBy+" "+direction+" LIMIT ?,?";
//		
//		
//		loadDB();
//		int a = (pagenumber - 1) * itemsPerPage;
//		int b = itemsPerPage;
//		// ({pagenumber}-1)*{itemsPerPage},{itemsPerPage}
//		List<SeptPhotoDto> list = new ArrayList<>();
//		SeptPhotoDto p = null;
//		PreparedStatement statement;
//		try {
//			statement = conn.prepareStatement(sql);
//			statement.setString(1, '%'+searchTerm+'%');
//			statement.setInt(2, a);
//			statement.setInt(3, b);
//			ResultSet resultSet = statement.executeQuery();
//			String executedQuery = resultSet.getStatement().toString();
//			System.out.println(executedQuery);
//			while (resultSet.next()) {
//				long firmaId = resultSet.getInt("firma_id");
//				String firmaNaziv = resultSet.getString("firma_naziv");
//				String firmaNivo = resultSet.getString("firma_nivo");
//				long userId = resultSet.getInt("user_id");
//				String username = resultSet.getString("username");
//				double userOcena = resultSet.getDouble("user_ocena");
//				long photoId = resultSet.getInt("photo_id");
//				String photoName = resultSet.getString("photo_name");
//				String uploadDate = resultSet.getString("uploadDate");
//				String path = resultSet.getString("path");
//				int selledCount = resultSet.getInt("selledCount");
//				double price = resultSet.getDouble("price");
//				double photoOcena = resultSet.getDouble("photo_ocena");
//				int approved = resultSet.getInt("approved");
//				int deleted = resultSet.getInt("deleted");
//				p = new SeptPhotoDto(firmaId,firmaNaziv,firmaNivo,userId,username,userOcena,photoId,photoName,uploadDate,path,selledCount,price,photoOcena,approved,deleted);
//				System.out.println(p);
//				list.add(p);
//			}
//
//			resultSet.close();
//			statement.close();
//
//			ConnectionManager.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//		
//	}
//	
//	public List<SeptPhotoDto> searchKeyword(String searchFor, String sortBy, String direction, String searchTerm,int itemsPerPage, int pagenumber) {
//		String sql;
//		sql="SELECT f.id firma_id, f.naziv firma_naziv,f.level firma_nivo,z.user_id,z.username,z.user_ocena,z.firmaId, z.photo_id,z.photo_name,z.uploadDate, z.path, z.selledCount, z.price, z.photo_ocena,z.approved,z.deleted, z.location, z.opis\n" + 
//				"FROM\n" + 
//				"(SELECT u.id user_id,u.username,u.ocena user_ocena,u.firmaId,p.id photo_id,p.photoName photo_name,p.uploadDate,p.path,p.selledCount,p.price,p.ocena photo_ocena,p.approved,p.deleted,p.location,p.opis\n" + 
//				"FROM\n" + 
//				"unsplash2_db.user u\n" + 
//				"INNER JOIN unsplash2_db.Photo p\n" + 
//				"ON u.id = p.userId\n" + 
//				") z\n" + 
//				"LEFT JOIN unsplash2_db.Firma f\n" + 
//				"ON f.id = z.firmaId\n" + 
//				"WHERE deleted = 0 AND approved= 1 AND (photo_name LIKE ? OR username LIKE ? OR f.naziv LIKE ? OR location LIKE ? OR opis LIKE ?)\n" + 
//				"ORDER BY CASE WHEN firma_nivo = 'zlatni' THEN '0'\n" + 
//				"								WHEN user_ocena >= 4.0 AND user_ocena <=5.0 THEN '1'\n" + 
//				"                                WHEN firma_nivo = 'srebrni' THEN '2'\n" + 
//				"                                WHEN user_ocena >= 3.0 AND user_ocena <=4.0 THEN '3'\n" + 
//				"                                WHEN firma_nivo = 'bronzani' THEN '4'\n" + 
//				"								ELSE "+sortBy+"\n" + 
//				"							END,"+sortBy+" "+direction+" LIMIT ?,?\n" + 
//				"\n" + 
//				"";
//		loadDB();
//		int a = (pagenumber - 1) * itemsPerPage;
//		int b = itemsPerPage;
//		// ({pagenumber}-1)*{itemsPerPage},{itemsPerPage}
//		List<SeptPhotoDto> list = new ArrayList<>();
//		SeptPhotoDto p = null;
//		PreparedStatement statement;
//		try {
//			statement = conn.prepareStatement(sql);
//			statement.setString(1, '%'+searchTerm+'%');
//			statement.setString(2, '%'+searchTerm+'%');
//			statement.setString(3, '%'+searchTerm+'%');
//			statement.setString(4, '%'+searchTerm+'%');
//			statement.setString(5, '%'+searchTerm+'%');
//			statement.setInt(6, a);
//			statement.setInt(7, b);
//			ResultSet resultSet = statement.executeQuery();
//			String executedQuery = resultSet.getStatement().toString();
//			System.out.println(executedQuery);
//			while (resultSet.next()) {
//				long firmaId = resultSet.getInt("firma_id");
//				String firmaNaziv = resultSet.getString("firma_naziv");
//				String firmaNivo = resultSet.getString("firma_nivo");
//				long userId = resultSet.getInt("user_id");
//				String username = resultSet.getString("username");
//				double userOcena = resultSet.getDouble("user_ocena");
//				long photoId = resultSet.getInt("photo_id");
//				String photoName = resultSet.getString("photo_name");
//				String uploadDate = resultSet.getString("uploadDate");
//				String path = resultSet.getString("path");
//				int selledCount = resultSet.getInt("selledCount");
//				double price = resultSet.getDouble("price");
//				double photoOcena = resultSet.getDouble("photo_ocena");
//				int approved = resultSet.getInt("approved");
//				int deleted = resultSet.getInt("deleted");
//				p = new SeptPhotoDto(firmaId,firmaNaziv,firmaNivo,userId,username,userOcena,photoId,photoName,uploadDate,path,selledCount,price,photoOcena,approved,deleted);
//				System.out.println(p);
//				list.add(p);
//			}
//
//			resultSet.close();
//			statement.close();
//
//			ConnectionManager.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//		
//	}
//	
//	public List<SeptPhotoDto> searchCategory(String searchFor, String sortBy, String direction, String searchTerm,int itemsPerPage, int pagenumber) {
//		String sql;
//		sql="SELECT f.id firma_id, f.naziv firma_naziv, f.level firma_nivo, z.user_id, z.username, z.user_ocena, z.photo_id, z.photo_name, z.photo_path, z.uploadDate, z.selledCount,z.ocena, z.price, z.deleted, z.approved, z.categoryName, z.category_id\n" + 
//				"FROM\n" + 
//				"(SELECT u.id user_id,u.username,u.ocena user_ocena,u.firmaId,b.photo_id,b.photo_name,b.photo_path,b.uploadDate,b.selledCount, b.ocena, b.price, b.deleted, b.approved, b.categoryName,b.category_id\n" + 
//				"FROM\n" + 
//				"unsplash2_db.user u\n" + 
//				"INNER JOIN (\n" + 
//				"SELECT c.id category_id, c.categoryName, p.id photo_id,p.photoName photo_name, p.path photo_path,p.uploadDate,p.selledCount,p.ocena, p.price, p.deleted, p.approved, p.userId\n" + 
//				"FROM \n" + 
//				"unsplash2_db.Category  AS c\n" + 
//				"INNER JOIN unsplash2_db.photo_category  AS p_c ON p_c.categoryId = c.id\n" + 
//				"INNER JOIN unsplash2_db.Photo AS p ON p_c.photoId = p.id\n" + 
//				") b ON u.id = b.userId\n" + 
//				") z \n" + 
//				"LEFT JOIN unsplash2_db.Firma f\n" + 
//				"ON f.id = z.firmaId\n" + 
//				"WHERE deleted = 0 AND approved= 1 AND categoryName LIKE ?\n" + 
//				"ORDER BY CASE WHEN firma_nivo = 'zlatni' THEN '0'\n" + 
//				"								WHEN user_ocena >= 4.0 AND user_ocena <=5.0 THEN '1'\n" + 
//				"                                WHEN firma_nivo = 'srebrni' THEN '2'\n" + 
//				"                                WHEN user_ocena >= 3.0 AND user_ocena <=4.0 THEN '3'\n" + 
//				"                                WHEN firma_nivo = 'bronzani' THEN '4'\n" + 
//				"								ELSE "+sortBy+"\n" + 
//				"							END,"+ sortBy+" "+direction+" LIMIT ?,?\n" + 
//				"";
//		loadDB();
//		int a = (pagenumber - 1) * itemsPerPage;
//		int b = itemsPerPage;
//		// ({pagenumber}-1)*{itemsPerPage},{itemsPerPage}
//		List<SeptPhotoDto> list = new ArrayList<>();
//		SeptPhotoDto p = null;
//		PreparedStatement statement;
//		try {
//			statement = conn.prepareStatement(sql);
//			statement.setString(1, '%'+searchTerm+'%');
//			statement.setInt(2, a);
//			statement.setInt(3, b);
//			ResultSet resultSet = statement.executeQuery();
//			String executedQuery = resultSet.getStatement().toString();
//			System.out.println(executedQuery);
//			while (resultSet.next()) {
//				long firmaId = resultSet.getInt("firma_id");
//				String firmaNaziv = resultSet.getString("firma_naziv");
//				String firmaNivo = resultSet.getString("firma_nivo");
//				long userId = resultSet.getInt("user_id");
//				String username = resultSet.getString("username");
//				double userOcena = resultSet.getDouble("user_ocena");
//				long photoId = resultSet.getInt("photo_id");
//				String photoName = resultSet.getString("photo_name");
//				String uploadDate = resultSet.getString("uploadDate");
//				String path = resultSet.getString("photo_path");
//				int selledCount = resultSet.getInt("selledCount");
//				double price = resultSet.getDouble("price");
//				double photoOcena = resultSet.getDouble("ocena");
//				int approved = resultSet.getInt("approved");
//				int deleted = resultSet.getInt("deleted");
//				long categeryId = resultSet.getInt("category_id");
//				String categeryName = resultSet.getString("categoryName");
//				p = new SeptPhotoDto(firmaId,firmaNaziv,firmaNivo,userId,username,userOcena,photoId,photoName,uploadDate,path,selledCount,price,photoOcena,approved,deleted,categeryId,categeryName);
//				System.out.println(p);
//				list.add(p);
//			}
//
//			resultSet.close();
//			statement.close();
//
//			ConnectionManager.disconnect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;	
//	}
	
	
}
