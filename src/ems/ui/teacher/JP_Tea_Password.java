package ems.ui.teacher;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ems.Application;
import ems.db.MDB;
import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class JP_Tea_Password extends JPanel implements ActionListener{
	private JTextField newpassword;
	private JTextField newpassword2;
	private JButton  BT_add;
	
	public JP_Tea_Password(String name){
		init(name);
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JLabel label2 = new JLabel("������������");
		label2.setBounds(54, 75, 120, 30);
		label2.setFont(UIutils.font);
		add(label2);
		JLabel label3 = new JLabel("�ٴ�ȷ��������");
		label3.setBounds(34, 118, 140, 30);
		label3.setFont(UIutils.font);
		add(label3);
		
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
				Application ap = Application.getApplication();
				MDB mdb = ap.getMDB();
				String id = ap.getLoginID();
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
