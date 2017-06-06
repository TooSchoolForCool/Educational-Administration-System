package ems.ui.student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ems.Application;
import ems.db.MDB;
import ems.utils.UIutils;

public class JP_Stu_Course_Query extends JPanel implements ActionListener{
	private String stuid;
	
	private JTextField sid;
	private  JTextArea textAreaOutput;
	
	public JP_Stu_Course_Query(String name){
		init(name);
		
		Application ap = Application.getApplication();
		this.stuid = ap.getLoginID();
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton  BT_add = new JButton("��ѯ");
		BT_add.setBounds(108, 80, 120, 30);
		BT_add.setFont(UIutils.font);
		BT_add.setFocusable(false);
		add(BT_add);
		
		JScrollPane scrop = new JScrollPane();  //��������
		scrop.setBounds(34,120,500,300);
		add(scrop);
				
		textAreaOutput = new JTextArea("��ʾ���", 20, 43);
		textAreaOutput.setBackground(Color.WHITE);
		textAreaOutput.setSelectedTextColor(Color.BLACK);
		textAreaOutput.setLineWrap(true);        //�����Զ����й��� 
		textAreaOutput.setWrapStyleWord(true);   // ������в����ֹ���
		scrop.setViewportView(textAreaOutput);
		
		BT_add.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
