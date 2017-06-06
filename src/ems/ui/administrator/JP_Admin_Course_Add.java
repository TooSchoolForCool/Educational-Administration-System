package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JP_Admin_Course_Add extends JPanel implements ActionListener{
	
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	
	
	public JP_Admin_Course_Add(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
//		JLabel label = new JLabel(getName());
//		label.setBounds(34, 32, 90, 30);
//		label.setFont(UIutils.font);
//		add(label);
		
		JLabel Label1 = new JLabel("课程名");
		Label1.setBounds(30, 40, 70, 30);
		Label1.setFont(UIutils.font);
		add(Label1);
		
		tf1 = new JTextField();
		tf1.setBounds(100, 40, 250, 30);
		tf1.setFont(UIutils.font);
		add(tf1);
		
		JLabel Label2 = new JLabel("课程号");
		Label2.setBounds(30, 80, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf2 = new JTextField();
		tf2.setBounds(100, 80, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);
		
		JLabel Label3 = new JLabel("学期");
		Label3.setBounds(30, 120, 70, 30);
		Label3.setFont(UIutils.font);
		add(Label3);

		tf3 = new JTextField();
		tf3.setBounds(100, 120, 250, 30);
		tf3.setFont(UIutils.font);
		add(tf3);
		
		JLabel Label4 = new JLabel("专业");
		Label4.setBounds(30, 160, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);

		tf4 = new JTextField();
		tf4.setBounds(100, 160, 250, 30);
		tf4.setFont(UIutils.font);
		add(tf4);
		
		JLabel Label5 = new JLabel("老师ID");
		Label5.setBounds(30, 200, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);

		tf5 = new JTextField();
		tf5.setBounds(100, 200, 250, 30);
		tf5.setFont(UIutils.font);
		add(tf5);
		
		JButton bt = new JButton("确认添加");
		bt.setBounds(160, 240, 100, 30);
		bt.addActionListener(this);
		add(bt);
		
	}
	
	/**
	 * 鼠标点击确认添加的响应方法，要将文本框获取的数据，添加到courses表中
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String Cname = tf1.getText();    	//课程名
		String Cid = tf2.getText();      	//课程号
		String Term = tf3.getText();      	//学期
		String Cdepart = tf4.getText();  	//专业
		String Tid = tf5.getText();      	//老师ID
//		System.out.println(Cname+" "+Cid+" "+Term+" "+Cdepart+" "+Tid);
		
	}
}
