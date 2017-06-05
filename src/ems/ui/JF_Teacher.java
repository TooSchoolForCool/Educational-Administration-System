package ems.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ems.user.Manager;
import ems.utils.UIutils;

public class JF_Teacher extends JFrame implements ActionListener,ChangeListener{
	Manager manager;
	
	private JTabbedPane TP_ctrl;
	
	public JF_Teacher(String LoginID) {
		
		//根据LoginID在数据库查表从MDB获取manager对象
		
		
		init();
	}
	
	private void init() {
		this.setTitle("教务管理系统-教师");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(UIutils.font);
		
		
		TP_ctrl.add("个人信息", new JP_Tea_Information("个人信息"));
		TP_ctrl.add("课程管理", new JP_Tea_Course("课程管理"));
		TP_ctrl.add("教师管理", new JP_Tea_SetPassword("教师管理"));
		
		
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
