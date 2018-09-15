package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ProjWEB.PROJWEB.Domain.Resolution;
import ProjWEB.PROJWEB.Domain.Dto.ResolutionStatisticDto;

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
				int count = Integer.parseInt(rs.getString("Soldcount"));
				
				Resolution resolutionDB = new Resolution(resolutionId, slikaId, resolution, path,price,count);
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
				int count = Integer.parseInt(rs.getString("Soldcount"));
				
				Resolution resolutionDB = new Resolution(resolutionId, slikaId, resolution, path,price,count);
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
	
	public ArrayList<ResolutionStatisticDto> resolutionStatistic() throws SQLException {
		ArrayList<ResolutionStatisticDto> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT \n" + 
				"	SUM(CASE WHEN resolution='HD' THEN Soldcount ELSE 0 END) AS Hd,\n" + 
				"	SUM(CASE WHEN resolution='UHD' THEN Soldcount ELSE 0 END) AS uhd,\n" + 
				"	SUM(CASE WHEN resolution='4K' THEN Soldcount ELSE 0 END) AS k4\n" + 
				"FROM \n" + 
				"	webProjDB.resolutions;";
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
				
				int count = Integer.parseInt(rs.getString("Hd"));
				int count1 = Integer.parseInt(rs.getString("uhd"));
				int count2 = Integer.parseInt(rs.getString("k4"));
				
				ResolutionStatisticDto resolutionDB = new ResolutionStatisticDto("HD", count);
				ResolutionStatisticDto resolutionDB1 = new ResolutionStatisticDto("UHD", count1);
				ResolutionStatisticDto resolutionDB2 = new ResolutionStatisticDto("4K", count2);
				
				list.add(resolutionDB);
				list.add(resolutionDB1);
				list.add(resolutionDB2);
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
        public int update(Resolution res) throws SQLException{
		
		loadDB();
		int result =0;
        try {
            con.setAutoCommit(false);
            System.out.println(res);
            String sql = "UPDATE webProjDB.resolutions SET Soldcount = "+res.getCount()+" where id = "+res.getId()+";";
            result = st.executeUpdate(sql);
            System.out.println("____SUCCESS");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            con.rollback();
        } finally {
            st.close();
            con.close();
        }
        return result;
        }
}
