package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ProjWEB.PROJWEB.Domain.Image;
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
				double price = Double.parseDouble(rs.getString("price"));
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
	
	public int save(Image image){
		loadDB();
		int result = 0;
        try {
            con.setAutoCommit(false);
            System.out.println(image);
            String sql = "INSERT into webProjDB.slika (numOfCopiesSelled, datePublished, price, name,place,description,userId,path,approved) values ("+0+",'"+image.getDatePublished()+"',"+image.getPrice()+",'"+image.getName()+"','"+image.getPlace()+"','"+image.getDescription()+"',"+image.getUserId()+",'"+image.getPath()+"',"+0+");";
            System.out.println(sql);
            result = st.executeUpdate(sql);
            System.out.println("____SUCCESS");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        	try {
				con.rollback();
				st.close();
	            con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        return result;
	}

}
