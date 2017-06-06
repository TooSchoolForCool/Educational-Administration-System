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
		
		TP_ctrl.add("��Ӱ༶", new JP_Admin_Class_Add("��Ӱ༶"));
		TP_ctrl.add("��ѯ�༶", new JP_Admin_Class_Query("��ѯ�༶"));
		TP_ctrl.add("�޸İ༶", new JP_Admin_Class_Update("�޸İ༶"));

		add(TP_ctrl);
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
