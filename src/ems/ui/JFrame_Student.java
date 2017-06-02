package ems.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ems.user.Student;
import ems.utils.Utils;

public class JFrame_Student extends JFrame implements ActionListener,ChangeListener{
	Student user;
	
	private JTabbedPane TP_ctrl;
	
	public JFrame_Student(Student user) {
		this.user = user;
		
		System.out.println(this.user.toString());
		
		init();
	}
	
	private void init() {
		this.setTitle("教务管理系统-学生");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(Utils.font);
		
		TP_ctrl.add("学生管理", new JPanel_Stu_Security("学生管理"));		
		TP_ctrl.add("课程管理", new JPanel_Stu_Course("课程管理"));		
		TP_ctrl.add("考试管理", new JPanel_Stu_Exam("考试管理"));
		
		
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
