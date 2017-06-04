package ems.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JP_Mng_MnClass extends JPanel implements ActionListener{
	
	private JTabbedPane TP_ctrl;
	
	public JP_Mng_MnClass(String name){
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
		
		TP_ctrl.add("��Ӱ༶", new JP_Mng_MnClass_Add("��Ӱ༶"));
		TP_ctrl.add("ɾ���༶", new JP_Mng_MnClass_Delete("ɾ���༶"));
		TP_ctrl.add("�޸İ༶", new JP_Mng_MnClass_Update("�޸İ༶"));
		TP_ctrl.add("��ѯ�༶", new JP_Mng_MnClass_Query("��ѯ�༶"));

		add(TP_ctrl);
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
