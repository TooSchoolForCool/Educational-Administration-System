package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JP_Admin_Course_Query extends JPanel implements ActionListener{
	
	
	public JP_Admin_Course_Query(String name){
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
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
}
