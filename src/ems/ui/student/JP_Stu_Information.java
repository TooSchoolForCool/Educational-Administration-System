package ems.ui.student;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ems.Application;
import ems.db.MDB;
import ems.utils.UIutils;

public class JP_Stu_Information extends JPanel implements ActionListener{
	
	private JTextArea ta_result;
	
	String stuid;
	
	public JP_Stu_Information(String name){
		init(name);
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton bt = new JButton("¼ìË÷");
		bt.setBounds(424, 23, 153, 30);
		bt.setFont(UIutils.font);
		bt.addActionListener(this);
		add(bt);
		
		ta_result = new JTextArea();
		ta_result.setBounds(33, 80, 544, 329);
		ta_result.setFont(UIutils.font);
		ta_result.setEditable(false);
		add(ta_result);
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="¼ìË÷"){
			Application ap = Application.getApplication();
			MDB mdb=ap.getMDB();
			String stuid = ap.getLoginID();
			try {
				String info = mdb.queryStudentInfo(stuid);
				ta_result.setText(info);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
