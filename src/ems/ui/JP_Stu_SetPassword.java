package ems.ui;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import ems.db.*;
import ems.*;
public class JP_Stu_SetPassword extends JPanel implements ActionListener{
	
	private JTextField oripassword;
	private JTextField newpassword;
	private JTextField newpassword2;
	private JButton  BT_add;
	public JP_Stu_SetPassword(String name){
		init(name);
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel label1 = new JLabel("原密码");
		label1.setBounds(34, 32, 60, 30);
		label1.setFont(UIutils.font);
		add(label1);
		JLabel label2 = new JLabel("新密码");
		label2.setBounds(34, 75, 60, 30);
		label2.setFont(UIutils.font);
		add(label2);
		JLabel label3 = new JLabel("新密码");
		label3.setBounds(34, 118, 60, 30);
		label3.setFont(UIutils.font);
		add(label3);
		
		oripassword = new JTextField();
		oripassword.setBounds(108, 32, 266, 30);
		oripassword.setFont(UIutils.font);
		add(oripassword);
		newpassword = new JTextField();
		newpassword.setBounds(108, 75, 266, 30);
		newpassword.setFont(UIutils.font);
		add(newpassword);
		newpassword2 = new JTextField();
		newpassword2.setBounds(108, 118, 266, 30);
		newpassword2.setFont(UIutils.font);
		add(newpassword2);
		
		 BT_add = new JButton("确定修改");
		BT_add.setBounds(254, 179, 120, 30);
		BT_add.setFont(UIutils.font);
		BT_add.setFocusable(false);
		add(BT_add);
		
		BT_add.addActionListener(this);
	}
	
	/**
	 * 修改密码接口
	 * 点击修改按钮，比较新旧密码，传参，进行修改
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BT_add){
//			System.out.println("确认修改");
			boolean a=oripassword.getText().equals(newpassword.getText());
			boolean b=newpassword.getText().equals(newpassword2.getText());
			if(a){System.out.println("与原密码相同");}
			else{
				if(b){
					Application ap=new Application();
					ap=ap.getApplication();
					MDB mdb=ap.getMDB();
				try {
					String id = ap.getLoginID();
					mdb.update(id,oripassword.getText(),newpassword.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
}
