package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ProjWEB.PROJWEB.Domain.Tag;

public class TagDao {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
    public TagDao() {	}
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	public ArrayList<Tag> findAll() throws SQLException {
		ArrayList<Tag> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.tag;";
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
				long tagId = Long.parseLong(rs.getString("id"));
				String name = rs.getString("tagName");
				int count = Integer.parseInt(rs.getString("count"));
				
				Tag tag = new Tag(tagId, name,count);
				list.add(tag);
				System.out.println();
			}
		}catch(SQLException ex) {
			System.out.println("TAG ERROR::FIND ALL");
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public ArrayList<Tag> findTagById(long id) throws SQLException {
		ArrayList<Tag> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.tag WHERE id = "+id+";";
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
				long tagId = Long.parseLong(rs.getString("id"));
				String name = rs.getString("tagName");
				int count = Integer.parseInt(rs.getString("count"));
				
				
				Tag tag = new Tag(tagId, name,count);
				list.add(tag);
				System.out.println();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL ERROR::TAG FIND BY ID");
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public int save(String name) throws SQLException {
		loadDB();
		int result =0;
        try {
            con.setAutoCommit(false);
            System.out.println(name);
            String sql = "insert into webProjDB.tag (tagName)"
                    + "values ('" +name+"')";
            result = st.executeUpdate(sql);
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            con.rollback();
            System.out.println("SQL ERROR::SAVE TAG");
        } finally {
            st.close();
            con.close();
        }
        return result;
	}
	
	public ArrayList<Long> getTagBySlikaId(long imageId) throws SQLException {
		ArrayList<Long> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.slika_tag WHERE slikaId = "+imageId+";";
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
				long tagId = Long.parseLong(rs.getString("tagId"));
				list.add(tagId);
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
	
	public ArrayList<Long> getImagesByTagId(long tagId) throws SQLException {
		ArrayList<Long> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.slika_tag WHERE tagId = "+tagId+";";
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
				long slikaId = Long.parseLong(rs.getString("slikaId"));
				list.add(slikaId);
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
	
	public ArrayList<Tag> tagStatistic() throws SQLException {
		ArrayList<Tag> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.tag ORDER BY count DESC;";
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
				long tagId = Long.parseLong(rs.getString("id"));
				String name = rs.getString("tagName");
				int count = Integer.parseInt(rs.getString("count"));
				
				Tag tag = new Tag(tagId, name,count);
				list.add(tag);
				System.out.println();
			}
		}catch(SQLException ex) {
			System.out.println("TAG ERROR::STATISTIC");
			ex.printStackTrace();
		}finally {
			rs.close();
			st.close();
			con.close();
		}
		return list;
	}
	
	public int saveTagImage(long tagId,long imageId) throws SQLException {
		loadDB();
		int result =0;
        try {
            con.setAutoCommit(false);
            String sql = "INSERT into webProjDB.slika_tag (slikaid,tagId) values ("+imageId+","+tagId+");";
            result = st.executeUpdate(sql);
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            con.rollback();
            System.out.println("SQL ERROR::SAVE TAG");
        } finally {
            st.close();
            con.close();
        }
        return result;
	}
}
