package ems.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JP_Admin_Class extends JPanel implements ActionListener{
	
	private JTabbedPane TP_ctrl;
	
	public JP_Admin_Class(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);

		TP_ctrl = new JTabbedPane(JTabbedPane.LEFT);
		TP_ctrl.setBounds(0, 0, 700, 500);
		TP_ctrl.setFocusable(false);
		TP_ctrl.setFont(UIutils.font);
		
		TP_ctrl.add("添加班级", new JP_Admin_Class_Add("添加班级"));
		TP_ctrl.add("查询班级", new JP_Admin_Class_Query("查询班级"));
		TP_ctrl.add("修改班级", new JP_Admin_Class_Update("修改班级"));

		add(TP_ctrl);
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
