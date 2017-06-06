package ems.ui.student;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.Application;
import ems.db.MDB;
import ems.utils.UIutils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class JP_Stu_Exam_QueryGrade extends JPanel implements ActionListener{
	private  JTextArea textAreaOutput;
	private JTextField sid;
	private String stuid;
	JButton  BT_add;
	
	public JP_Stu_Exam_QueryGrade(String name){
		init(name);
		
		Application ap = Application.getApplication();
		this.stuid = ap.getLoginID();
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel label = new JLabel(getName());
		label.setBounds(34, 32, 200, 30);
		label.setFont(UIutils.font);
		add(label);
		
		BT_add = new JButton("查询");
		BT_add.setBounds(108, 80, 120, 30);
		BT_add.setFont(UIutils.font);
		BT_add.setFocusable(false);
		add(BT_add);
		
		JScrollPane scrop = new JScrollPane();  //滚动窗口
		scrop.setBounds(34,120,500,300);
		add(scrop);
				
		textAreaOutput = new JTextArea("", 20, 43);
		textAreaOutput.setBackground(Color.WHITE);
		textAreaOutput.setSelectedTextColor(Color.BLACK);
		textAreaOutput.setEditable(false);
		textAreaOutput.setLineWrap(true);        //激活自动换行功能 
		textAreaOutput.setWrapStyleWord(true);   // 激活断行不断字功能
		scrop.setViewportView(textAreaOutput);
		
		BT_add.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BT_add){
			Application ap = Application.getApplication();
			MDB mdb = ap.getMDB();
			
			ArrayList<String> class_info = mdb.getStuScoreInfo(stuid);
			
			String text_output = "";
			
			for(String str : class_info)
			{
				text_output += str + "\r\n";
			}
			
			textAreaOutput.setText(text_output);
		}
	}
}
