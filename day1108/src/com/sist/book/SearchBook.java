package com.sist.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SearchBook extends JFrame {
    BookDAO dao;
	JTextField jtf_publisher;
	JTable table;
	Vector colNames;
	Vector<Vector> rowData;
	
	public SearchBook() {
		dao = new BookDAO();
	
		colNames = new Vector();
		colNames.add("도서번호");
		colNames.add("도서명");
		colNames.add("출판사명");
		colNames.add("가격");
		rowData = new Vector<Vector>();

		table = new JTable(rowData,colNames);
		JScrollPane jsp = new JScrollPane(table);
		
		jtf_publisher = new JTextField(20);
		JButton btn = new JButton("검색");
		JButton btn1 = new JButton("전체목록");
		
		JPanel p = new JPanel();
		p.add(new JLabel("출판사명:"));
		p.add(jtf_publisher);
		p.add(btn);
		p.add(btn1);
		
		setLayout(new BorderLayout());
		add(p,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);

		setSize(700,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//'검색'버튼 이벤트 생성
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				String keyword = jtf_publisher.getText();
				ArrayList<BookVO> list= dao.searchBook(keyword);
				
				for(BookVO b : list ) {
					Vector v = new Vector();
					v.add(b.getBookid());
					v.add(b.getBookname());
					v.add(b.getPublisher());
					v.add(b.getPrice());
					rowData.add(v);
				}
				
				table.updateUI();

			}});
		
		
		//'전체목록'버튼 이벤트 생성
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				String keyword = jtf_publisher.getText();
				ArrayList<BookVO> list= dao.allBook();
				
				for(BookVO b : list ) {
					Vector v = new Vector();
					v.add(b.getBookid());
					v.add(b.getBookname());
					v.add(b.getPublisher());
					v.add(b.getPrice());
					rowData.add(v);
				}
				
				table.updateUI();

			}});
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new SearchBook();

		/*Scanner sc = new Scanner(System.in);
		String keyword = "";
		System.out.print("검색할 출판사명을 입력하시오=>");
	    keyword = sc.next();*/

	   // ArrayList<BookVO> list= dao.searchBook(keyword);
		
		}
	}


