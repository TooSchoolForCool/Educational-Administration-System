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
		
		JLabel Label1 = new JLabel("�γ���");
		Label1.setBounds(30, 40, 70, 30);
		Label1.setFont(UIutils.font);
		add(Label1);
		
		tf1 = new JTextField();
		tf1.setBounds(100, 40, 250, 30);
		tf1.setFont(UIutils.font);
		add(tf1);
		
		JLabel Label2 = new JLabel("�γ̺�");
		Label2.setBounds(30, 80, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);
		
		tf2 = new JTextField();
		tf2.setBounds(100, 80, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);
		
		JLabel Label3 = new JLabel("ѧ��");
		Label3.setBounds(30, 120, 70, 30);
		Label3.setFont(UIutils.font);
		add(Label3);

		tf3 = new JTextField();
		tf3.setBounds(100, 120, 250, 30);
		tf3.setFont(UIutils.font);
		add(tf3);
		
		JLabel Label4 = new JLabel("רҵ");
		Label4.setBounds(30, 160, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);

		tf4 = new JTextField();
		tf4.setBounds(100, 160, 250, 30);
		tf4.setFont(UIutils.font);
		add(tf4);
		
		JLabel Label5 = new JLabel("��ʦID");
		Label5.setBounds(30, 200, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);

		tf5 = new JTextField();
		tf5.setBounds(100, 200, 250, 30);
		tf5.setFont(UIutils.font);
		add(tf5);
		
		JButton bt = new JButton("ȷ�����");
		bt.setBounds(160, 240, 100, 30);
		bt.addActionListener(this);
		add(bt);
		
	}
	
	/**
	 * �����ȷ����ӵ���Ӧ������Ҫ���ı����ȡ�����ݣ���ӵ�courses����
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String Cname = tf1.getText();    	//�γ���
		String Cid = tf2.getText();      	//�γ̺�
		String Term = tf3.getText();      	//ѧ��
		String Cdepart = tf4.getText();  	//רҵ
		String Tid = tf5.getText();      	//��ʦID
//		System.out.println(Cname+" "+Cid+" "+Term+" "+Cdepart+" "+Tid);
		
	}
}
