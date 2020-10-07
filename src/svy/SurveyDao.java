package svy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurveyDao {
//	private String driver="oracle.jdbc.driver.OracleDriver";
//	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
//	private String user="jspid";
//	private String password="jsppw";
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
/*
	public SurveyDao() { //1,2단계
		
		try {
			Class.forName(driver);
				
			try {
				conn = DriverManager.getConnection(url,user,password);
				
			} catch (SQLException e) {
				System.out.println("계정 접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		}
		
	} //SurveyDao()
	
*/
	
	public SurveyDao(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
		try {
			Class.forName(driver);
				
			try {
				conn = DriverManager.getConnection(url,user,password);
				
			} catch (SQLException e) {
				System.out.println("계정 접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		}
		
	} //SurveyDao




	public int insertSurveyBean(SurveyBean sb) {
		
		String sql = "insert into survey values(seqmy.nextval,?,?,?,?,?,?,?)";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sb.getName());
			ps.setString(2, sb.getCompany());
			ps.setString(3, sb.getEmail());
			ps.setString(4, sb.getSatisfaction());
			ps.setString(5, sb.getPart());
			ps.setString(6, sb.getHowto());
			ps.setInt(7, sb.getAgree());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
		
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch (Exception e) {
				
			}
		}
		return cnt;
	} //insertSurveyBean

	public ArrayList<SurveyBean> getServeyList() {
		
		String sql = "select * from survey order by no asc";
		ArrayList<SurveyBean> lists = new ArrayList<SurveyBean>();
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String company = rs.getString("company");
				String email = rs.getString("email");
				String satisfaction = rs.getString("satisfaction");
				String part = rs.getString("part");
				String howto = rs.getString("howto");
				int agree = rs.getInt("agree");
				
				SurveyBean sb = new SurveyBean(no, name, company, email, satisfaction, part, howto, agree); 
					
				lists.add(sb);
			}
						
		} catch (SQLException e) {
			
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch (Exception e) {
				
			}
		}
		return lists;
		
	}//getServeyList

	public int deleteSurvey(int no) {
		
		String sql = "delete from survey where no=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch (Exception e) {
				
			}
		}
		return cnt;
	} //deleteSurvey

	public SurveyBean getSelectOne(int no) {
		
		String sql = "select * from survey where no=?";
		SurveyBean sb = null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				sb = new SurveyBean();
				
				sb.setNo(no);
				sb.setName(rs.getString("name"));
				sb.setCompany(rs.getString("company"));
				sb.setEmail(rs.getString("email"));
				sb.setSatisfaction(rs.getString("satisfaction"));
				sb.setPart(rs.getString("part"));
				sb.setHowto(rs.getString("howto"));
				sb.setAgree(rs.getInt("agree"));
				
			}
			
		} catch (SQLException e) {
			
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			}catch (Exception e) {
				
			}
		}
		return sb;
	} //getSelectOne

	public int updateSurveyBean(SurveyBean sb) {

		String sql = "update survey set name=?,company=?,email=?,satisfaction=?,part=?,howto=?,agree=? where no=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sb.getName());
			ps.setString(2, sb.getCompany());
			ps.setString(3, sb.getEmail());
			ps.setString(4, sb.getSatisfaction());
			ps.setString(5, sb.getPart());
			ps.setString(6, sb.getHowto());
			ps.setInt(7, sb.getAgree());
			ps.setInt(8, sb.getNo());
			
			cnt = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch (Exception e) {
				
			}
		}
		return cnt;
				
	} //updateSurveyBean

	
	
}
