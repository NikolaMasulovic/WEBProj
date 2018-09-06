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
		String sql = "SELECT * FROM webProjDB.slika;";
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
				
				Image img = new Image(id,numOfCopies,datePublished,price,name,place,description,userId,path,approved);
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
}
