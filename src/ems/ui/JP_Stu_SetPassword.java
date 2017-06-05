package ems.ui;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import ems.db.*;
import ems.*;
public class JP_Stu_SetPassword extends JPanel implements ActionListener{
	
//	private JTextField oripassword;
	private JTextField newpassword;
	private JTextField newpassword2;
	private JButton  BT_add;
	private String id;
	public JP_Stu_SetPassword(String name,String id){
		init(name);
		this.id=id;
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
//		JLabel label1 = new JLabel("ԭ����");
//		label1.setBounds(34, 32, 60, 30);
//		label1.setFont(UIutils.font);
//		add(label1);
		JLabel label2 = new JLabel("������");
		label2.setBounds(114, 75, 60, 30);
		label2.setFont(UIutils.font);
		add(label2);
		JLabel label3 = new JLabel("�ٴ�ȷ��������");
		label3.setBounds(34, 118, 140, 30);
		label3.setFont(UIutils.font);
		add(label3);
		
//		oripassword = new JTextField();
//		oripassword.setBounds(108, 32, 266, 30);
//		oripassword.setFont(UIutils.font);
//		add(oripassword);
		newpassword = new JTextField();
		newpassword.setBounds(180, 75, 266, 30);
		newpassword.setFont(UIutils.font);
		add(newpassword);
		newpassword2 = new JTextField();
		newpassword2.setBounds(180, 118, 266, 30);
		newpassword2.setFont(UIutils.font);
		add(newpassword2);
		
		BT_add = new JButton("ȷ���޸�");
		BT_add.setBounds(254, 179, 120, 30);
		BT_add.setFont(UIutils.font);
		BT_add.setFocusable(false);
		add(BT_add);
		
		BT_add.addActionListener(this);
	}
	
	/**
	 * �޸�����ӿ�
	 * ����޸İ�ť���Ƚ��¾����룬���Σ������޸�
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== BT_add){
			boolean b = newpassword.getText().equals(newpassword2.getText());
			
			if(b){
				Application ap=new Application();
				ap=ap.getApplication();
				MDB mdb=ap.getMDB();
				try {
					if(mdb.updatePassword(id, newpassword.getText()) == true)
						JOptionPane.showMessageDialog(null, "�����޸ĳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "�����޸�ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "�����޸�ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "������������벻ͬ", "��ʾ", JOptionPane.ERROR_MESSAGE);
		}
	}
}
