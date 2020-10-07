package myPkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MymemDao {
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private String user="jspid";
	private String password="jsppw";
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public MymemDao() { //1,2단계
		
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
		
	} //MymemDao()

	public int insertMymem(MymemBean bean) {
		
		String sql = "insert into mymem values(seqmem.nextval,?,?)";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getPassword());
			
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
		
			}catch (Exception e) {
				
			}
		}
		
		return cnt;
	} //insertMymem()
	
	public ArrayList<MymemBean> getMymemList() {
		
		String sql = "select * from mymem order by id asc";
		ArrayList<MymemBean> lists = new ArrayList<MymemBean>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int id =rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				
				MymemBean bean = new MymemBean(id,name,password);
				
				lists.add(bean);
			}
			
		} catch (SQLException e) {
			
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
		
			}catch (Exception e) {
				
			}
		}
		return lists;
	}//getMymemList()

	public int deleteMymem(String id) {
		
		String sql = "delete from mymem where id=?";
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
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
	} //deleteMymem

	public MymemBean getOneSelect(String id) {
		
		String sql = "select * from mymem where id=?";
		MymemBean mb = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int id2 = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				
				mb = new MymemBean(id2, name, password);
			}
			
		} catch (SQLException e) {
			
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
		
			}catch (Exception e) {
				
			}
		}		
		return mb;	
	}//getOneSelect

	public int updateMymem(MymemBean bean) {
		
		String sql = "update mymem set name=?, password=? where id=?";
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getName()); 
			ps.setString(2, bean.getPassword()); 
			ps.setInt(3, bean.getId()); 
			
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
	} //updateMymem
	
	
	
	
	
	
	
	
	
	
}
