package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

}
