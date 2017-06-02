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
		this.setTitle("�������ϵͳ-ѧ��");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(Utils.font);
		
		TP_ctrl.add("ѧ������", new JPanel_Stu_Security("ѧ������"));		
		TP_ctrl.add("�γ̹���", new JPanel_Stu_Course("�γ̹���"));		
		TP_ctrl.add("���Թ���", new JPanel_Stu_Exam("���Թ���"));
		
		
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
