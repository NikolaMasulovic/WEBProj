package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import ProjWEB.PROJWEB.Domain.Image;
import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Domain.Rating;

public class RatingDao {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public RatingDao() {};
	
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<Rating> selectAll() throws SQLException {
		ArrayList<Rating> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.rating;";
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
				long userId = Long.parseLong(rs.getString("userId"));
				long imageId = Long.parseLong(rs.getString("slikaId"));
				int ocena = Integer.parseInt(rs.getString("ocena"));
				String date = rs.getString("date");
				
				Rating rating = new Rating(id, ocena, date, userId, imageId);
				list.add(rating);
				
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
	
	public ArrayList<Rating> findAllByImageId(long imgId) throws SQLException {
		ArrayList<Rating> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.rating where slikaId= "+imgId+";";
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
				long userId = Long.parseLong(rs.getString("userId"));
				long imageId = Long.parseLong(rs.getString("slikaId"));
				int ocena = Integer.parseInt(rs.getString("ocena"));
				String date = rs.getString("date");
				
				Rating rating = new Rating(id, ocena, date, userId, imageId);
				list.add(rating);
				
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
	
	public ArrayList<Rating> findAllByUserId(long uId) throws SQLException {
		ArrayList<Rating> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.rating where userId= "+uId+";";
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
				long userId = Long.parseLong(rs.getString("userId"));
				long imageId = Long.parseLong(rs.getString("slikaId"));
				int ocena = Integer.parseInt(rs.getString("ocena"));
				String date = rs.getString("date");
				
				Rating rating = new Rating(id, ocena, date, userId, imageId);
				list.add(rating);
				
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
	
	public int save(Rating rating) throws SQLException{
		loadDB();
		int result = 0;
		String sql = "";
        try {
            con.setAutoCommit(false);
            
            sql = "INSERT into webProjDB.rating (userId,slikaId,ocena,date) values ("+rating.getUserId()+","+rating.getImageId()+","+rating.getOcena()+",'"+rating.getDate()+"');";
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
        con.close();
		
        return result;
	}

}
