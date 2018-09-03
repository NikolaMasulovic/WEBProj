package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ProjWEB.PROJWEB.Domain.Resolution;

public class ResolutionDao {

	Connection con;
	Statement st;
	ResultSet rs;
	
	public ResolutionDao() {};
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<Resolution> selectAll() throws SQLException {
		ArrayList<Resolution> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.resolutions;";
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
				long resolutionId = Long.parseLong(rs.getString("id"));
				long slikaId = Long.parseLong(rs.getString("slikaId"));
				String resolution = rs.getString("resolution");
				String path = rs.getString("path");
				int price = Integer.parseInt(rs.getString("price"));
				
				Resolution resolutionDB = new Resolution(resolutionId, slikaId, resolution, path,price);
				list.add(resolutionDB);
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
	
	public ArrayList<Resolution> getResolutionsforImage(long imageId,String res) throws SQLException {
		ArrayList<Resolution> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.resolutions WHERE slikaId = "+imageId+" AND resolution = '"+res+"';";
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
				long resolutionId = Long.parseLong(rs.getString("id"));
				long slikaId = Long.parseLong(rs.getString("slikaId"));
				String resolution = rs.getString("resolution");
				String path = rs.getString("path");
				int price = Integer.parseInt(rs.getString("price"));
				
				Resolution resolutionDB = new Resolution(resolutionId, slikaId, resolution, path,price);
				list.add(resolutionDB);
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
	
	public int save(Resolution res) throws SQLException{
		
		loadDB();
		int result =0;
        try {
            con.setAutoCommit(false);
            System.out.println(res);
            String sql = "insert into webProjDB.resolutions (slikaId, resolution, path, price)"
                    + "values ('" + res.getSlikaId() + "','" + res.getResolution() + "','" + res.getPath()+ "','" +
            		res.getPrice() + "') ;";
            result = st.executeUpdate(sql);
            // If there is no error.
            System.out.println("____SUCCESS");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //if have any error
            con.rollback();
        } finally {
            st.close();
            con.close();
        }
        return result;
}
}
