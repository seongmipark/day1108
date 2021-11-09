package com.sist.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO {
	
	public ArrayList<BookVO> allBook(){
		ArrayList<BookVO> list = new ArrayList<BookVO>();

		String sql = "select*from book";
		
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userName="c##madang";
        String passWord="madang";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, userName, passWord);
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		int bookid = 0;
		String bookname = "";
		String dbpublisher = "";
		int price = 0;
		while(rs.next()) {
			bookid = rs.getInt(1);
			bookname = rs.getString(2);
			dbpublisher = rs.getString(3);
			price = rs.getInt(4);

			BookVO b = new BookVO(bookid, bookname, dbpublisher, price);
			list.add(b);
	
		}	
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println("���ܹ߻�:"+e.getMessage());
			}
	
		return list;
	}
	}

	//���ǻ���� �Ű������� ���޹޾� �ش� ���ǻ��� ��� ��������� �˻���
	//ArrayList�� ��ȯ�ϴ� �޼ҵ� ����
	public ArrayList<BookVO> searchBook(String publisher){
		ArrayList<BookVO> list = new ArrayList<BookVO>();

		String sql = "select*from book where publisher =? order by price desc";
		
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userName="c##madang";
        String passWord="madang";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

		try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, userName, passWord);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, publisher);
		rs = pstmt.executeQuery();
		
		int bookid = 0;
		String bookname = "";
		String dbpublisher = "";
		int price = 0;
		while(rs.next()) {
			bookid = rs.getInt(1);
			bookname = rs.getString(2);
			dbpublisher = rs.getString(3);
			price = rs.getInt(4);
			
			System.out.println(bookid+"\t"+bookname+"\t"+publisher+"\t"+price);
			BookVO b = new BookVO(bookid, bookname, dbpublisher, price);
			list.add(b);
	
		}	
		} catch (Exception e) {
			System.out.println("���ܹ߻�:"+e.getMessage());
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				System.out.println("���ܹ߻�:"+e.getMessage());
			}
	
		return list;
	}
	

	}
}
