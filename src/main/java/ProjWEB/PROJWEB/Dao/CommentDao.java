package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ProjWEB.PROJWEB.Domain.Comment;

public class CommentDao {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public CommentDao() {};
	
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<Comment> selectAll() throws SQLException {
		ArrayList<Comment> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.comment;";
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
				String comment = rs.getString("comment");
				String date = rs.getString("date");
				
				Comment comm = new Comment(id, comment, date, userId, imageId);
				list.add(comm);
				
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
	
	public ArrayList<Comment> findAllByImageId(long imgId) throws SQLException {
		ArrayList<Comment> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.comment where slikaId= "+imgId+";";
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
				String comment = rs.getString("comment");
				String date = rs.getString("date");
				
				Comment comm = new Comment(id, comment, date, userId, imageId);
				list.add(comm);
				
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
	
	public ArrayList<Comment> findAllByUserId(long uId) throws SQLException {
		ArrayList<Comment> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.comment where userId= "+uId+";";
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
				String comment = rs.getString("comment");
				String date = rs.getString("date");
				
				Comment comm = new Comment(id, comment, date, userId, imageId);
				list.add(comm);
				
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
	
	public int save(Comment comment) throws SQLException{
		loadDB();
		int result = 0;
		String sql = "";
        try {
            con.setAutoCommit(false);
            
            sql = "INSERT into webProjDB.comment (userId,slikaId,comment,date) values ("+comment.getUserId()+","+comment.getSlikaId()+",'"+comment.getComment()+"','"+comment.getDate()+"');";
            System.out.println(sql);
            result = st.executeUpdate(sql);
            System.out.println("____SUCCESS");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL ERROR::Comment");
            con.rollback();
        }
        st.close();
        con.close();
		
        return result;
	}

}
