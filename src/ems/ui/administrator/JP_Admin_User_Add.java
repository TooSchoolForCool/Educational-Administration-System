package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.List;

public class JP_Admin_User_Add extends JPanel implements ActionListener{
	
	JComboBox cb1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JTextField tf6;
	JTextField tf7;
	JTextField tf8;
	
	public JP_Admin_User_Add(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);

		JLabel Label1 = new JLabel("身份");
		Label1.setBounds(30, 40, 70, 30);
		Label1.setFont(UIutils.font);
		add(Label1);
		
		cb1 = new JComboBox();
		cb1.setBounds(100, 40, 250, 30);
		cb1.setFont(UIutils.font);
		cb1.addItem("老师");
		cb1.addItem("学生");
		add(cb1);
		
		JLabel Label2 = new JLabel("ID");
		Label2.setBounds(30, 80, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf2 = new JTextField();
		tf2.setBounds(100, 80, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);
		
		JLabel Label3 = new JLabel("密码");
		Label3.setBounds(30, 120, 70, 30);
		Label3.setFont(UIutils.font);
		add(Label3);

		tf3 = new JTextField();
		tf3.setBounds(100, 120, 250, 30);
		tf3.setFont(UIutils.font);
		add(tf3);
		
		JLabel Label4 = new JLabel("姓名");
		Label4.setBounds(30, 160, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);

		tf4 = new JTextField();
		tf4.setBounds(100, 160, 250, 30);
		tf4.setFont(UIutils.font);
		add(tf4);
		
		JLabel Label5 = new JLabel("年龄");
		Label5.setBounds(30, 200, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);

		tf5 = new JTextField();
		tf5.setBounds(100, 200, 250, 30);
		tf5.setFont(UIutils.font);
		add(tf5);
		
		JLabel Label6 = new JLabel("性别");
		Label6.setBounds(30, 240, 70, 30);
		Label6.setFont(UIutils.font);
		add(Label6);

		tf6 = new JTextField();
		tf6.setBounds(100, 240, 250, 30);
		tf6.setFont(UIutils.font);
		add(tf6);
		
		JLabel Label7 = new JLabel("学院");
		Label7.setBounds(30, 280, 70, 30);
		Label7.setFont(UIutils.font);
		add(Label7);

		tf7 = new JTextField();
		tf7.setBounds(100, 280, 250, 30);
		tf7.setFont(UIutils.font);
		add(tf7);
		
		JLabel Label8 = new JLabel("专业");
		Label8.setBounds(30, 320, 70, 30);
		Label8.setFont(UIutils.font);
		add(Label8);

		tf8 = new JTextField();
		tf8.setBounds(100, 320, 250, 30);
		tf8.setFont(UIutils.font);
		add(tf8);
		
		JButton bt = new JButton("确认添加");
		bt.setBounds(160, 360, 100, 30);
		bt.addActionListener(this);
		add(bt);
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		String identity = cb1.getSelectedItem().toString();//身份
		String ID = tf2.getText();                        //id
//		System.out.println(ID);
		String pwd = tf3.getText();                        //密码
		String name = tf4.getText();                       //姓名
		String age = tf5.getText();                        //年龄
		String sex = tf6.getText();                        //性别
		String aca = tf7.getText();                        //学院
		String dep = tf8.getText();                        //专业
		
		
	}
}
