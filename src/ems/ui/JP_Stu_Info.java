package ems.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class JP_Stu_Info extends JPanel implements ActionListener{
	
	private JTextArea ta_result;
	private JTextField tf_id;
	
	public JP_Stu_Info(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel lb = new JLabel("ID");
		lb.setBounds(33, 24, 72, 30);
		lb.setFont(UIutils.font);
		add(lb);
		
		tf_id = new JTextField();
		tf_id.setBounds(121, 24, 255, 30);
		tf_id.setFont(UIutils.font);
		add(tf_id);
		
		JButton bt = new JButton("查看个人信息");
		bt.setBounds(424, 23, 153, 30);
		bt.setFont(UIutils.font);
		bt.addActionListener(this);
		add(bt);
		
		ta_result = new JTextArea();
		ta_result.setBounds(33, 80, 544, 388);
		ta_result.setFont(UIutils.font);
		ta_result.setText("???");
		add(ta_result);
		
		
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		//查询个人信息调用函数返回字符串
		//tf_id.getText()
		//textArea.setText(String);
		
		
		
	}
}
