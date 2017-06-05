package ems.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ems.user.Teacher;
import ems.utils.UIutils;

public class JF_Manager extends JFrame implements ActionListener,ChangeListener{
	Teacher teacher;
	
	private JTabbedPane TP_ctrl;
	
	public JF_Manager(String LoginID) {
		
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
		
		TP_ctrl.add("����û�", new JP_Mng_AddUser("����û�"));
		TP_ctrl.add("�趨Ȩ��", new JP_Mng_SetPrivi("�趨Ȩ��"));
		TP_ctrl.add("�������", new JP_Mng_SetPassword("�������"));
		TP_ctrl.add("�γ̹���", new JP_Mng_MnCourse("�γ̹���"));
		TP_ctrl.add("ѧ������", new JP_Mng_MnStudent("ѧ������"));
		TP_ctrl.add("�༶����", new JP_Mng_MnClass("�༶����"));
		
		
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
