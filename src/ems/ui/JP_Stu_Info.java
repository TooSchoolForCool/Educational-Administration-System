package ems.ui;

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

public class JP_Stu_Info extends JPanel implements ActionListener{
	
	private JTextArea ta_result;
	private JTextField tf_id;
	
	String stuid;
	
	public JP_Stu_Info(String name, String stuid){
		init(name);
		this.stuid = stuid;
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
//		JLabel lb = new JLabel("学号");
//		lb.setBounds(33, 24, 72, 30);
//		lb.setFont(UIutils.font);
//		add(lb);
		
//		tf_id = new JTextField();
//		tf_id.setBounds(97, 24, 383, 30);
//		tf_id.setFont(UIutils.font);
//		add(tf_id);
		
		JButton bt = new JButton("检索");
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
		if(e.getActionCommand()=="检索"){
			Application ap = Application.getApplication();
			MDB mdb=ap.getMDB();
			try {
				String info = mdb.queryStudentInfo(stuid);
				ta_result.setText(info);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//查询个人信息调用函数返回字符串
		//tf_id.getText()
		//textArea.setText(String);
		
		
		
	}
}
