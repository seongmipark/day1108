package com.sist.dept;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;

public class DeptTest extends JFrame {
	
	JFrame f;
	
	JTextField jtf_dno;
	JTextField jtf_dname;
	JTextField jtf_dloc;
	
	JTable table;
	Vector colNames;
	Vector<Vector> rowData;
	
	DeptDAO dao;
	
	
	public void loadList() {
		
		ArrayList<DeptVO> list = dao.listAll();
		rowData.clear();
		for(DeptVO d : list ) {
			Vector v = new Vector();
			v.add(d.getDno());
			v.add(d.getDname());
			v.add(d.getDloc());	
			rowData.add(v);
		}
		 table.updateUI();
	}
	
	
	public DeptTest() {
	f = this;	
	dao = new DeptDAO();
	
	jtf_dno = new JTextField();
	jtf_dname = new JTextField();
	jtf_dloc = new JTextField();
	
	colNames = new Vector();
	colNames.add("부서번호");
	colNames.add("부서이름");
	colNames.add("부서위치");
	rowData = new Vector<Vector>();
	
	table = new JTable(rowData, colNames);
	
	
	JPanel p_input = new JPanel();
	JPanel p_button = new JPanel();
	JPanel p_center = new JPanel();
	
	p_input.setLayout(new GridLayout(3,2));
	p_input.add(new JLabel("부서번호:"));
	p_input.add(jtf_dno);
	p_input.add(new JLabel("부서이름:"));
	p_input.add(jtf_dname);
	p_input.add(new JLabel("부서위치:"));
	p_input.add(jtf_dloc);	
		
	JButton btn_insert = new JButton("추가");
	JButton btn_select = new JButton("목록");
	JButton btn_update = new JButton("수정");
	JButton btn_delete = new JButton("삭제");
	p_button.add(btn_insert);
	p_button.add(btn_select);
	p_button.add(btn_update);
	p_button.add(btn_delete);
	
	p_center.setLayout(new BorderLayout());
	p_center.add(p_input,BorderLayout.CENTER);
	p_center.add(p_button,BorderLayout.SOUTH);
	
	JScrollPane jsp = new JScrollPane(table);
	
	add(p_center, BorderLayout.CENTER);
	add(jsp, BorderLayout.SOUTH);
	
	setSize(800,600);
	setVisible(true);
	
	table.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int index = table.getSelectedRow();
			Vector v = rowData.get(index);
			jtf_dno.setText(v.get(0)+"");
			jtf_dname.setText(v.get(1)+"");
			jtf_dloc.setText(v.get(2)+"");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}});
	
	
	btn_insert.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int dno= Integer.parseInt(jtf_dno.getText());
			String dname = jtf_dname.getText();
			String dloc = jtf_dloc.getText();
			
			DeptVO d = new DeptVO(dno, dname, dloc);
			int re = dao.insert(d);
			if(re ==1) {
				JOptionPane.showMessageDialog(f, "부서를 등록하였습니다.");	
				//JOptionPane.showMessageDialog(this, "부서를 등록하였습니다.");
				//첫번째 매개변수에는 Frame객체를 전달해야한다.
				//지금 여기에는 ActionListener인터페이스를 구현한 객체이고 Frame이 아니다.
				//따라서 this로 설정하면 ActionListener객체이기 때문에 에러가 난다.
				loadList();
			}else {
				JOptionPane.showMessageDialog(f, "부서등록에 실패하였습니다.");				
			}
			
		}});
	
	
	btn_select.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			loadList();
			
		}});
	
	
	btn_update.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int dno= Integer.parseInt(jtf_dno.getText());
			String dname = jtf_dname.getText();
			String dloc = jtf_dloc.getText();
			
			DeptVO d = new DeptVO(dno, dname, dloc);
			int re = dao.update(d);
			if(re ==1) {
				JOptionPane.showMessageDialog(f, "부서를 수정하였습니다.");	
				loadList();
			}else {
				JOptionPane.showMessageDialog(f, "부서수정에 실패하였습니다.");				
			}
		}});
	
	
	btn_delete.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int dno= Integer.parseInt(jtf_dno.getText());

			int re = dao.delete(dno);
			if(re > 0 ) {
				JOptionPane.showMessageDialog(f, "부서를 삭제하였습니다.");	
				loadList();
			}else {
				JOptionPane.showMessageDialog(f, "부서삭제에 실패하였습니다.");				
			}
		}});

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DeptTest();
	}

}
