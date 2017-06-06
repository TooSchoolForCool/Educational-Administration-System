package ems.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ems.ui.administrator.JP_Admin_Class;
import ems.ui.administrator.JP_Admin_Course;
import ems.ui.administrator.JP_Admin_Password;
import ems.ui.administrator.JP_Admin_Privileges;
import ems.ui.administrator.JP_Admin_Student;
import ems.ui.administrator.JP_Admin_User_Add;
import ems.user.Teacher;
import ems.utils.UIutils;

public class JF_Admin extends JFrame implements ActionListener,ChangeListener{
	Teacher teacher;
	
	private JTabbedPane TP_ctrl;
	
	public JF_Admin(String LoginID) {
		
		//根据LoginID在数据库查表从MDB获取teacher对象
		
		
		init();
	}
	
	private void init() {
		this.setTitle("教务管理系统-管理员");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(UIutils.font);
		
		TP_ctrl.add("添加用户", new JP_Admin_User_Add("添加用户"));
		TP_ctrl.add("设定权限", new JP_Admin_Privileges("设定权限"));
		TP_ctrl.add("密码管理", new JP_Admin_Password("密码管理"));
		TP_ctrl.add("课程管理", new JP_Admin_Course("课程管理"));
		TP_ctrl.add("学生管理", new JP_Admin_Student("学生管理"));
		TP_ctrl.add("班级管理", new JP_Admin_Class("班级管理"));
		
		
		Container container = this.getContentPane();
        container.add(TP_ctrl, BorderLayout.CENTER);
        
        
        this.setVisible(true);
	}


	@Override
	public void stateChanged(ChangeEvent arg0) {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
}
