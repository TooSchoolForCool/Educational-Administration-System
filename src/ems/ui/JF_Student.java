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
import ems.utils.UIutils;

public class JF_Student extends JFrame implements ActionListener,ChangeListener{
	Student student;
	
	private JTabbedPane TP_ctrl;
	private String LoginID;
	public JF_Student(String LoginID) {
		
		//this.student = s;//����LoginID�����ݿ����MDB��ȡStudent����
		
		//System.out.println(this.student.toString());
		this.LoginID=LoginID;
		init();
	}
	
	private void init() {
		this.setTitle("�������ϵͳ-ѧ��");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(UIutils.font);
		
		
		TP_ctrl.add("������Ϣ", new JP_Stu_Info("������Ϣ"));
		TP_ctrl.add("�γ̹���", new JP_Stu_Course("�γ̹���"));		
		TP_ctrl.add("������Ϣ", new JP_Stu_Exam("������Ϣ"));
		TP_ctrl.add("�������", new JP_Stu_SetPassword("�������",LoginID));
		
		
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
