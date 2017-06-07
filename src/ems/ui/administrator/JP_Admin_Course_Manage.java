package ems.ui.administrator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ems.Application;
import ems.utils.UIutils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class JP_Admin_Course_Manage extends JPanel implements ActionListener {

	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JTextField tf4;
	JTextField tf5;
	JButton btquery;
	JButton bt;

	public JP_Admin_Course_Manage(String name) {
		init(name);

	}

	public void init(String name) {
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);

		JLabel Label1 = new JLabel("�γ���");
		Label1.setBounds(30, 60, 70, 30);
		Label1.setFont(UIutils.font);
		add(Label1);

		tf1 = new JTextField();
		tf1.setBounds(100, 60, 250, 30);
		tf1.setFont(UIutils.font);
		add(tf1);

		JLabel Label2 = new JLabel("�γ̺�");
		Label2.setBounds(30, 140, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);

		tf2 = new JTextField();
		tf2.setEditable(false);
		tf2.setBounds(100, 140, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);

		JLabel Label3 = new JLabel("ѧ��");
		Label3.setBounds(30, 180, 70, 30);
		Label3.setFont(UIutils.font);
		add(Label3);

		tf3 = new JTextField();
		tf3.setBounds(100, 180, 250, 30);
		tf3.setFont(UIutils.font);
		add(tf3);

		JLabel Label4 = new JLabel("רҵ");
		Label4.setBounds(30, 220, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);

		tf4 = new JTextField();
		tf4.setBounds(100, 220, 250, 30);
		tf4.setFont(UIutils.font);
		add(tf4);

		JLabel Label5 = new JLabel("��ʦID");
		Label5.setBounds(30, 260, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);

		tf5 = new JTextField();
		tf5.setBounds(100, 260, 250, 30);
		tf5.setFont(UIutils.font);
		add(tf5);


		btquery = new JButton("�����γ�");
		btquery.setBounds(380, 60, 100, 30);
		btquery.addActionListener(this);
		add(btquery);
		
		bt = new JButton("�޸Ŀγ�");
		bt.setBounds(160, 340, 100, 30);
		bt.addActionListener(this);
		add(bt);

	}
	
	/**
	 * ���������Ӧ�������¼���ȡ����btqueryΪ�����γ̣�btΪ�޸Ŀγ�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btquery) {		// ���ݿγ����������γ̣����ò�ѯ����
			String Cname = tf1.getText();  //�γ���
//			System.out.println("Cname="+Cname);
			ArrayList<String> ret = Application.getApplication().getMDB().getCourseInfo(Cname);

			if(ret == null)
			{
				JOptionPane.showMessageDialog(null, "���޴˿�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
			}
			else{
				tf2.setText(ret.get(1));
				tf3.setText(ret.get(2));
				tf4.setText(ret.get(3));
				tf5.setText(ret.get(4));	
			}
		}
		if (e.getSource() == bt) {// �����޸ĺ������޸Ŀγ���Ϣ
			String Cname = tf1.getText();    	//�γ���
			String Cid = tf2.getText();      	//�γ̺�
			String Term = tf3.getText();      	//ѧ��
			String Cdepart = tf4.getText();  	//רҵ
			String Tid = tf5.getText();      	//��ʦID
//			System.out.println(Cname+" "+Cid+" "+Term+" "+Cdepart+" "+Tid);
			
			boolean ret = Application.getApplication().getMDB().updateCourseInfo(Cname, Cid, Term, Cdepart, Tid);
			
			if(ret == true)
				JOptionPane.showMessageDialog(null, "�γ��޸ĳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "�γ��޸�ʧ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
		}
	}
}
