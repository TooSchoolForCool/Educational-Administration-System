package ems.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ems.utils.UIutils;

public class JP_Stu_Course_Query extends JPanel implements ActionListener{
	private JTextField sid;
//	private JTextField newpassword;
//	private JTextField newpassword2;
	private  JTextArea textAreaOutput;
	
	public JP_Stu_Course_Query(String name){
		init(name);
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel label1 = new JLabel("学号");
		label1.setBounds(34, 32, 60, 30);
		label1.setFont(UIutils.font);
		add(label1);
		
		sid = new JTextField();
		sid.setBounds(108, 32, 266, 30);
		sid.setFont(UIutils.font);
		add(sid);
		
		JButton  BT_add = new JButton("查询");
		BT_add.setBounds(108, 80, 120, 30);
		BT_add.setFont(UIutils.font);
		BT_add.setFocusable(false);
		add(BT_add);
		
		
		JScrollPane scrop = new JScrollPane();  //滚动窗口
		scrop.setBounds(34,120,400,300);
		add(scrop);
				
		textAreaOutput = new JTextArea("显示查课", 20, 43);
		textAreaOutput.setBackground(Color.WHITE);
		textAreaOutput.setSelectedTextColor(Color.BLACK);
		textAreaOutput.setLineWrap(true);        //激活自动换行功能 
		textAreaOutput.setWrapStyleWord(true);            // 激活断行不断字功能
		scrop.setViewportView(textAreaOutput);
		
		BT_add.addActionListener(this);

	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
