package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ems.Application;
import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JP_Admin_Password extends JPanel implements ActionListener{
	private JTextField tf_id;
	private JTextField tf_newpwd;
	
	
	
	public JP_Admin_Password(String name){
		init(name);
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel Label1 = new JLabel("用户ID");
		Label1.setBounds(80, 60, 72, 30);
		Label1.setFont(UIutils.font);
		add(Label1);
		
		tf_id = new JTextField();
		tf_id.setBounds(170, 60, 350, 30);
		tf_id.setFont(UIutils.font);
		add(tf_id);
		
		JLabel Label2 = new JLabel("新密码");
		Label2.setBounds(80, 110, 72, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf_newpwd = new JTextField();
		tf_newpwd.setBounds(170, 110, 350, 30);
		tf_newpwd.setFont(UIutils.font);
		add(tf_newpwd);
		
		JButton bt = new JButton("修改/重置");
		bt.setBounds(270, 160, 150, 30);
		bt.setFont(UIutils.font);
		bt.addActionListener(this);
		add(bt);
		
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
		String ID = tf_id.getText();
		String newPassword = tf_newpwd.getText();
		
		boolean ret = Application.getApplication().getMDB().updatePassword(ID, newPassword);
		
		if(ret == true)
			JOptionPane.showMessageDialog(null, "密码修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "密码修改失败", "提示", JOptionPane.ERROR_MESSAGE);
	}
}
