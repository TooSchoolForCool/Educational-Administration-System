package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ems.Application;
import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class JP_Admin_User_Manage extends JPanel implements ActionListener{
	
	private JTabbedPane TP_ctrl;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JTextField tf6;
	JTextField tf7;
	JTextField tf8;
	JButton btquery;
	JButton bt;

	public JP_Admin_User_Manage(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
//		TP_ctrl = new JTabbedPane(JTabbedPane.LEFT);
//		TP_ctrl.setBounds(0, 0, 700, 500);
//		TP_ctrl.setFocusable(false);
//		TP_ctrl.setFont(UIutils.font);
		
		//TP_ctrl.add("添加学生", new JP_Admin_Student_Add("添加学生"));
//		TP_ctrl.add("查询用戶", new JP_Admin_Student_Query("查询用戶"));
//		TP_ctrl.add("修改用戶", new JP_Admin_Student_Update("修改用戶"));
		
//		add(TP_ctrl);
		JLabel Label2 = new JLabel("ID");
		Label2.setBounds(30, 60, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf2 = new JTextField();
		tf2.setBounds(100, 60, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);
		
		btquery = new JButton("用户检索");
		btquery.setBounds(380, 60, 100, 30);
		btquery.addActionListener(this);
		add(btquery);
		
		JLabel Label4 = new JLabel("姓名");
		Label4.setBounds(30, 140, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);

		tf4 = new JTextField();
		tf4.setBounds(100, 140, 250, 30);
		tf4.setFont(UIutils.font);
		add(tf4);
		
		JLabel Label5 = new JLabel("年龄");
		Label5.setBounds(30, 180, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);

		tf5 = new JTextField();
		tf5.setBounds(100, 180, 250, 30);
		tf5.setFont(UIutils.font);
		add(tf5);
		
		JLabel Label6 = new JLabel("性别");
		Label6.setBounds(30, 220, 70, 30);
		Label6.setFont(UIutils.font);
		add(Label6);

		tf6 = new JTextField();
		tf6.setBounds(100, 220, 250, 30);
		tf6.setFont(UIutils.font);
		add(tf6);
		
//		TP_ctrl.add("查询用户", new JP_Admin_Student_Query("查询用户"));
//		TP_ctrl.add("修改用户", new JP_Admin_Student_Update("修改用户"));
		JLabel Label7 = new JLabel("学院");
		Label7.setBounds(30, 260, 70, 30);
		Label7.setFont(UIutils.font);
		add(Label7);

		tf7 = new JTextField();
		tf7.setBounds(100, 260, 250, 30);
		tf7.setFont(UIutils.font);
		add(tf7);
		
		JLabel Label8 = new JLabel("专业");
		Label8.setBounds(30, 300, 70, 30);
		Label8.setFont(UIutils.font);
		add(Label8);

		tf8 = new JTextField();
		tf8.setBounds(100, 300, 250, 30);
		tf8.setFont(UIutils.font);
		add(tf8);
		
		bt = new JButton("用户修改");
		bt.setBounds(160, 340, 100, 30);
		bt.addActionListener(this);
		add(bt);
		
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btquery){//调用查询函数，id=tf2.getText();
			String id = tf2.getText();
			
			ArrayList<String> ret = Application.getApplication().getMDB().getUserInfo(id);

			if(ret == null)
			{
				JOptionPane.showMessageDialog(null, "查无此人", "提示", JOptionPane.ERROR_MESSAGE);
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
				tf7.setText("");
				tf8.setText("");
			}
			else{
				tf4.setText(ret.get(0));
				tf5.setText(ret.get(1));
				tf6.setText(ret.get(2));
				tf7.setText(ret.get(3));
				tf8.setText(ret.get(4));
			}
			
			tf2.setEditable(false);
		}
		if(e.getSource()==bt){//调用修改函数，
			//id=tf2.getText();姓名=tf4.getText();年龄=tf5.getText();性别=tf6.getText()
			//学院=tf7.getText();专业=tf8.getText();
			
			String id = tf2.getText();
			String name = tf4.getText();
			String age = tf5.getText();
			String gender = tf6.getText();
			String dept = tf7.getText();
			String major = tf8.getText();
			
			boolean ret = Application.getApplication().getMDB().updateUserInfo(id, name, age, gender, dept, major);
			
			if(ret == true)
				JOptionPane.showMessageDialog(null, "信息修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "信息修改失败", "提示", JOptionPane.ERROR_MESSAGE);
			
			tf2.setText("");
			tf4.setText("");
			tf5.setText("");
			tf6.setText("");
			tf7.setText("");
			tf8.setText("");
			
			tf2.setEditable(true);
		}
	}
}
