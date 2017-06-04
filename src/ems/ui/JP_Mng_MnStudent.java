package ems.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JP_Mng_MnStudent extends JPanel implements ActionListener{
	
	private JTabbedPane TP_ctrl;
	
	public JP_Mng_MnStudent(String name){
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
		
		TP_ctrl.add("添加学生", new JP_Mng_MnStudent_Add("添加学生"));
		TP_ctrl.add("删除学生", new JP_Mng_MnStudent_Delete("删除学生"));
		TP_ctrl.add("修改学生", new JP_Mng_MnStudent_Update("修改学生"));
		TP_ctrl.add("查询学生", new JP_Mng_MnStudent_Query("查询学生"));
		
		add(TP_ctrl);
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
