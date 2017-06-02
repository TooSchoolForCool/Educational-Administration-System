package ems.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ems.utils.Utils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JPanel_Stu_Exam extends JPanel implements ActionListener,ChangeListener{
	
	private JTabbedPane TP_ctrl;
	
	private JTextField oripassword;
	private JTextField newpassword;
	private JTextField newpassword2;
	
	public JPanel_Stu_Exam(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		
		TP_ctrl = new JTabbedPane(JTabbedPane.LEFT);
		TP_ctrl.setBounds(0, 0, 700, 500);
		TP_ctrl.setFocusable(false);
		TP_ctrl.addChangeListener(this);
		TP_ctrl.setFont(Utils.font);
		
		TP_ctrl.add("考试信息", new JPanel_Stu_Exam_QueryInfo("考试信息"));
		TP_ctrl.add("成绩信息", new JPanel_Stu_Exam_QueryGrade("成绩信息"));
		
		
//		Container container = this.getRootPane();
//        container.add(TP_ctrl, BorderLayout.CENTER);
        
		add(TP_ctrl);
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
	}
}
