package ProjWEB.PROJWEB.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ProjWEB.PROJWEB.Domain.Company;

public class CompanyDao {
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public CompanyDao() {	}
	
	public void loadDB() {
		try {
			con = DBContext.getConnection();
			st = con.createStatement();
		}catch(Exception ex) {
			System.out.println("Cant load DB");
		}
	}
	
	public ArrayList<Company> selectAllCompanies() throws SQLException {
		ArrayList<Company> list = new ArrayList<>();
		loadDB();
		String sql = "SELECT * FROM webProjDB.company;";
		try {
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while(rs.next()) {
//				ISPIS U KONZOLU
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue);
			    }
				long id = Long.parseLong(rs.getString("id"));
				String name = rs.getString("name");
				String pib = rs.getString("PIB");
				String location = rs.getString("location");
				int fee = Integer.parseInt(rs.getString("fee"));
				int partnerStatus = Integer.parseInt(rs.getString("partnerStatus"));
				int approved = Integer.parseInt(rs.getString("approved"));
				String email = rs.getString("email");
				
				Company company = new Company(id, name, pib, location, fee, partnerStatus,approved,email);
				list.add(company);
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
	
	public Company save(Company company) {
		loadDB();
		int res =0;
		String sql = "";
		int id = 0;
        try {
            con.setAutoCommit(false);
            System.out.println(company);
            sql = "INSERT INTO webProjDB.company (name,PIB,location,fee,partnerStatus,approved,email) values ('"+company.getName()+"','"+company.getPib()+"','"+company.getLoaction()+"',"+company.getFee()+","+company.getPartnerStatus()+",0,'"+company.getEmail()+"');";
            System.out.println(sql);
            res = st.executeUpdate(sql);
            // If there is no error.
            System.out.println("____SUCCESS");
            con.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //if have any error
            
        }
        
        try {
        sql = "SELECT * FROM webProjDB.company WHERE id = LAST_INSERT_ID();";
        rs = st.executeQuery(sql);
        if(rs.next()) {
        	id = rs.getInt("id");
        	System.out.println(id);////////////////testiraj
        }
        }catch (SQLException ex) {
            ex.printStackTrace();
            //if have any error
            
        }
        finally {
        	try {
				con.rollback();
				st.close();
	            con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        }
        company.setId(id);
		return company;	
	}
	public long approveCompany(long  companyId) throws SQLException {
		loadDB();
//		User u = null;
		String sql = "UPDATE webProjDB.company set approved = 1 where id = "+companyId+";";
		System.out.println(sql);
		int rss = 0;
		try {
			rss = st.executeUpdate(sql);
			//System.out.println("---"+rs.getMetaData());
		}catch(SQLException ex) {
			System.out.println("SQL Error");
		}finally {
			//rs.close();
			st.close();
			con.close();
		}
		return companyId;
	}

}
