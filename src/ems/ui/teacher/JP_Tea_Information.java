package ems.ui.teacher;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.Application;
import ems.db.MDB;
import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class JP_Tea_Information extends JPanel implements ActionListener{
	private String tid;
	
	JTextField tf_id;
	JTextField tf_name;
	JTextField tf_age;
	JTextField tf_sex;
	JTextField tf_aca;
	JTextField tf_depart;


	public JP_Tea_Information(String name){
		init(name);
		tid = Application.getApplication().getLoginID();
	}

	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel Label2 = new JLabel("ID");
		Label2.setBounds(50, 50, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf_id = new JTextField();
		tf_id.setEditable(false);
		tf_id.setBounds(120, 50, 250, 30);
		tf_id.setFont(UIutils.font);
		add(tf_id);
		
		JLabel Label4 = new JLabel("姓名");
		Label4.setBounds(50, 100, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);
	
		tf_name = new JTextField();
		tf_name.setEditable(false);
		tf_name.setBounds(120, 100, 250, 30);
		tf_name.setFont(UIutils.font);
		add(tf_name);
		
		JLabel Label5 = new JLabel("年龄");
		Label5.setBounds(50, 150, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);
	
		tf_age = new JTextField();
		tf_age.setEditable(false);
		tf_age.setBounds(120, 150, 250, 30);
		tf_age.setFont(UIutils.font);
		add(tf_age);
		
		JLabel Label6 = new JLabel("性别");
		Label6.setBounds(50, 200, 70, 30);
		Label6.setFont(UIutils.font);
		add(Label6);
	
		tf_sex = new JTextField();
		tf_sex.setEditable(false);
		tf_sex.setBounds(120, 200, 250, 30);
		tf_sex.setFont(UIutils.font);
		add(tf_sex);
		
		JLabel Label7 = new JLabel("学院");
		Label7.setBounds(50, 250, 70, 30);
		Label7.setFont(UIutils.font);
		add(Label7);
	
		tf_aca = new JTextField();
		tf_aca.setEditable(false);
		tf_aca.setBounds(120, 250, 250, 30);
		tf_aca.setFont(UIutils.font);
		add(tf_aca);
		
		JLabel Label8 = new JLabel("专业");
		Label8.setBounds(50, 300, 70, 30);
		Label8.setFont(UIutils.font);
		add(Label8);
	
		tf_depart = new JTextField();
		tf_depart.setEditable(false);
		tf_depart.setBounds(120, 300, 250, 30);
		tf_depart.setFont(UIutils.font);
		add(tf_depart);
		
		JButton bt = new JButton("检索");
		bt.setBounds(394, 300, 100, 30);
		bt.setFont(UIutils.font);
		bt.addActionListener(this);
		add(bt);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="检索"){
			Application ap = Application.getApplication();
			MDB mdb=ap.getMDB();
			String[] info = mdb.queryTeacherInfo(tid).split(" ");
			
			tf_id.setText(info[0]);
			tf_name.setText(info[1]);
			tf_age.setText(info[2]);
			tf_sex.setText(info[3]);
			tf_aca.setText(info[4]);
			tf_depart.setText(info[5]);
		}
	}
}
