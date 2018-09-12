package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import ProjWEB.PROJWEB.Domain.Order;
import ProjWEB.PROJWEB.Domain.Order_image;

public class OrderDao {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public OrderDao() {};
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<Order> selectAll() throws SQLException {
		ArrayList<Order> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.order;";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
//				ISPIS U KONU
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long id = Long.parseLong(rs.getString("id"));
				long userId = Long.parseLong(rs.getString("userId"));
				String orderStatus = rs.getString("orderStatus");
				String orderDate = rs.getString("orderDate");
				
				Order order = new Order(id,userId, orderStatus, orderDate);
				list.add(order);
				
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
	/**
	 * izvlaci iz order_slika sve koji su vezni za taj
	 * order
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Order_image> findByOrderId(long id) throws SQLException {
		ArrayList<Order_image> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.order_slika where orderId = "+id+";";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
//				ISPIS U KONU
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long slikaId = Long.parseLong(rs.getString("slikaId"));
				long orderId = Long.parseLong(rs.getString("orderId"));
				String resolution = rs.getString("resolution");
				
				Order_image os = new Order_image(slikaId, orderId, resolution);
				list.add(os);
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
	
	public ArrayList<Order> findByUserId(long id,String status) throws SQLException {
		ArrayList<Order> list = new ArrayList<>();
		
		loadDB();
		String sql = "SELECT * FROM webProjDB.order where userId = "+id+" AND orderStatus = '"+status+"';";
		System.out.println(sql);
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
//				ISPIS U KONU
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long orderId = Long.parseLong(rs.getString("id"));
				long userId = Long.parseLong(rs.getString("userId"));
				String orderStatus = rs.getString("orderStatus");
				String orderDate = rs.getString("orderDate");
				
				Order order = new Order();
				order.setId(orderId);
				order.setUserId(userId);
				order.setOrderDate(orderDate);
				order.setOrderSatus(orderStatus);
				list.add(order);				
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
	
	public int payOrder(long userId) throws SQLException {
		loadDB();
		/*
		 * res is how many row is effected
		 */
		int res = 0;
		String sql = "UPDATE webProjDB.order SET orderStatus = 'paid' WHERE userId="+userId+";";
		System.out.println(sql);
		try {
			res = st.executeUpdate(sql);
		}catch(SQLException ex) {
			System.out.println("SQL Error");
		}finally {
			st.close();
			con.close();
		}
		System.out.println("OVO JE RES:"+res);
		return res;
	}
	
//	public void save(OrderSaveDto order) throws SQLException {
//	loadDB();
//    try {
//        con.setAutoCommit(false);
//        System.out.println(order);
//        String sql = "insert into webProjDB.order_slika (slikaId,orderId,count) values ("+order.getUserId()+","+order.getOrderId()+","+order.getCount()+");";
//        st.executeUpdate(sql);
//        // If there is no error.
//        con.commit();
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        //if have any error
//        con.rollback();
//    } finally {
//        st.close();
//        con.close();
//    
//	}
//	}
    public long saveBlankOrder(Order order) throws SQLException {
    	loadDB();
    	int res =0;
    	String sql ="";
        try {
            con.setAutoCommit(false);
            System.out.println(order);
             sql = "insert into webProjDB.order (userId,orderStatus,orderDate) values ("+order.getUserId()+",'unpaid','"+order.getOrderDate()+"');";
            res = st.executeUpdate(sql);
            // If there is no error.
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //if have any error
            con.rollback();
        }
        st.close();
        long id = 0;
        sql = "SELECT * FROM webProjDB.order WHERE id = LAST_INSERT_ID();";
        System.out.println(sql);
        PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
        	id = resultSet.getInt("id");
        	System.out.println("ORDER ID:"+id);
        }else {
        	System.out.println("ERROR:LAST_INSERT_ID()");
        }
        con.close();
        return id;
    }
    

}
