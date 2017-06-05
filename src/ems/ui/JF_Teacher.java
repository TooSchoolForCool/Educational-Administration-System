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
		
		//����LoginID�����ݿ����MDB��ȡmanager����
		
		
		init();
	}
	
	private void init() {
		this.setTitle("�������ϵͳ-��ʦ");
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.TOP);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(UIutils.font);
		
		
		TP_ctrl.add("������Ϣ", new JP_Tea_Information("������Ϣ"));
		TP_ctrl.add("�γ̹���", new JP_Tea_Course("�γ̹���"));
		TP_ctrl.add("��ʦ����", new JP_Tea_SetPassword("��ʦ����"));
		
		
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
