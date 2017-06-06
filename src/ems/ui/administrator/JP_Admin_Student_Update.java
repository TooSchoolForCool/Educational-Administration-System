package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class JP_Admin_Student_Update extends JPanel implements ActionListener{
	
	JComboBox cb1;
	JTextField tf2;
	public JP_Admin_Student_Update(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel label = new JLabel(getName());
		label.setBounds(34, 32, 90, 30);
		label.setFont(UIutils.font);
		add(label);
		
		cb1 = new JComboBox();
		cb1.setBounds(100, 40, 250, 30);
		cb1.setFont(UIutils.font);
		cb1.addItem("老师");
		cb1.addItem("学生");
		add(cb1);
		
		JLabel Label2 = new JLabel("ID");
		Label2.setBounds(30, 80, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf2 = new JTextField();
		tf2.setBounds(100, 80, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);
		
		JLabel Label3 = new JLabel("密码");
		Label3.setBounds(30, 120, 70, 30);
		Label3.setFont(UIutils.font);
		add(Label3);
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
