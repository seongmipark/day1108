package com.sist.dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//DAO(Database Access Object)
//DB테이블에 접근하여 자료를 추가,읽기,수정,삭제를 담당하는 객체

public class DeptDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url ="jdbc:oracle:thin:@localhost:1521:XE";
	private String userName="c##sist";
	private String passWord="sist";
	
	
	//DB테이블에 레코드를 추가하는 메소드
	public int insert(DeptVO d) {
		int re = -1;
		String sql = "insert into dept2 values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, passWord);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d.getDno());
			pstmt.setString(2, d.getDname());
			pstmt.setString(3, d.getDloc());
			
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());	
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());	
			}
		}
		
		return re;
	}
	
	
	//DB테이블에 레코드를 수정하는 메소드
	public int update(DeptVO d) {
		int re = -1;
		String sql = "update dept2 set dname=?,dloc=? where dno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, passWord);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDname());
			pstmt.setString(2, d.getDloc());
			pstmt.setInt(3, d.getDno());
			re = pstmt.executeUpdate();
		} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());	
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}	
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());	
			}
		}
		
		return re;
	}	
	
	
	//DB테이블에 레코드를 삭제하는 메소드
	public int delete(int dno) {
		int re = -1;
		String sql = "delete dept2 where dno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, passWord);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			re = pstmt.executeUpdate();
			
		} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());	
		}finally {
			try {
			if(conn!=null) {
				conn.close();
			}	
			if(pstmt !=null) {
				pstmt.close();
			}
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());	
			}
		}
		return re;
	}	
	
	
	//DB테이블에 레코드를 읽어오는 메소드
	public ArrayList<DeptVO> listAll(){
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();
		String sql = "select*from dept2";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, passWord);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//rs에서 읽어온 모든 레코드들을 list에 담는다.
			while(rs.next()) {
				int dno = rs.getInt(1);
				String dname = rs.getString(2);
				String dloc = rs.getString(3);
				
				//생성시에 변수를 설정해서 
				//deptVO d = new deptVO(dno, dname, dloc);
				//리스트에 담을때 생성해도 되고
				//list.add(new deptVO(dno, dname, dloc));
				DeptVO d = new DeptVO();
				d.setDno(dno);
				d.setDname(dname);
				d.setDloc(dloc);
				list.add(d);
			}
			
		} catch (Exception e) {
		System.out.println("예외발생:"+e.getMessage());	
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());	
			}
		}
		return list;
	}
	
	
}
