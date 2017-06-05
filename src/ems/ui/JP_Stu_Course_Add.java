package ems.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class JP_Stu_Course_Add extends JPanel implements ActionListener{
	
	
	
	public JP_Stu_Course_Add(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(74, 28, 113, 27);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 28, 408, 431);
		add(scrollPane);
		
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(74, 82, 113, 27);
		add(btnNewButton_1);
		
		
		
		
		
		
		
		
		
		
		

		
	}
	

	class CheckableItem { 
		private boolean isSelected; 
		private String str; 
		public CheckableItem(String str) { 
			this.str = str; 
			this.isSelected = false; 
		}
		public void setSelected(boolean b) { 
			this.isSelected = b; 
		}
		public boolean isSelected() { 
			return this.isSelected; 
		}
		public String toString() { 
			return this.str;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
