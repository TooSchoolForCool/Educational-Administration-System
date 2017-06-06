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
		
		//����LoginID�����ݿ����MDB��ȡteacher����
		
		
		init();
	}
	
	private void init() {
		this.setTitle("�������ϵͳ-����Ա");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(UIutils.font);
		
		TP_ctrl.add("����û�", new JP_Admin_User_Add("����û�"));
		TP_ctrl.add("�趨Ȩ��", new JP_Admin_Privileges("�趨Ȩ��"));
		TP_ctrl.add("�������", new JP_Admin_Password("�������"));
		TP_ctrl.add("�γ̹���", new JP_Admin_Course("�γ̹���"));
		TP_ctrl.add("ѧ������", new JP_Admin_Student("ѧ������"));
		TP_ctrl.add("�༶����", new JP_Admin_Class("�༶����"));
		
		
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
