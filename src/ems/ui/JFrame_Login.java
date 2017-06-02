package ems.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ems.Application;
import ems.user.Student;
import ems.user.User;
import ems.utils.Utils;

public class JFrame_Login extends JFrame implements ActionListener{
	/**�û��������*/
	private JTextField TF_username = null;
	/**���������*/
	private JPasswordField TF_password = null;
	/**����ɼ���ѡ��*/
	private JCheckBox CB_password = null;
	
	OnLoginSuccessListener loginlistener;
	
	public interface OnLoginSuccessListener{
		void OnLoginSuccess(User user);
	}
	
	public void setOnLoginSuccessListener(OnLoginSuccessListener loginlistener){
		this.loginlistener = loginlistener;
	}
	
	
	/**���췽��*/
	public JFrame_Login() {
		
		this.init();//��ʼ��
	}
	/**��ʼ������*/
	private void init() {
		this.setTitle("�������ϵͳ-��¼");
		this.setBounds(100, 100, 400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//�˺ű�ǩ
		JLabel label = new JLabel("�˺�");
		label.setBounds(25, 23, 50, 30); //λ�úͱ���
		label.setFont(Utils.font);       //����
		this.getContentPane().add(label);//��ӵ����
		//�����ǩ
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(25, 73, 50, 30);
		label_1.setFont(Utils.font);
		this.getContentPane().add(label_1);
		//�û��������
		TF_username = new JTextField();
		TF_username.setBounds(94, 23, 274, 30);
		TF_username.setFont(Utils.font);
		TF_username.setColumns(10);
		this.getContentPane().add(TF_username);
		//���������
		TF_password = new JPasswordField();
		TF_password.setBounds(94, 73, 274, 30);
		TF_password.setFont(Utils.font);
		TF_password.setEchoChar('*');
		this.getContentPane().add(TF_password);
		//����ɼ���ѡ��
		CB_password = new JCheckBox("");
		CB_password.setBounds(34, 122, 30, 29);
		CB_password.setActionCommand("����ɼ�");
		this.getContentPane().add(CB_password);
		//��½��
		JButton BT_login = new JButton("��¼");
		BT_login.setBounds(92, 122, 130, 30);
		BT_login.setFont(Utils.font);
		BT_login.setFocusable(false);
		this.getContentPane().add(BT_login);
		//ȡ����
		JButton BT_cancel = new JButton("ȡ��");
		BT_cancel.setBounds(238, 122, 130, 30);
		BT_cancel.setFont(Utils.font);
		BT_cancel.setFocusable(false);
		this.getContentPane().add(BT_cancel);
		//��Ӽ���
		CB_password.addActionListener(this);
		BT_login.addActionListener(this);
		BT_cancel.addActionListener(this);
		//����Ϊ�ɼ�
		this.setVisible(true);
	}
	
	/**�����ȵļ�������*/
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("����ɼ�")){    //����ɼ����ĸ�ѡ��
			TF_password.setEchoChar(CB_password.isSelected()?(char)0:'*');
		}else if(e.getActionCommand().equals("��¼")){ //���µ�¼��
			String username = TF_username.getText();
			String password = new String(TF_password.getPassword());
			
			//�Ϸ��û���������
			Application mapplication = Application.getApplication();
			int ret = mapplication.getMDB().login(username, password);
			
			if(ret == -1)
			{
				JOptionPane.showMessageDialog(null, "�û�����������Ч��", "��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else	// �˻�����Ϸ�
			{
				// ret Ϊ�˻�����
				System.out.println(ret);
			}
			
//			if(isUserNameValid(username)&&isPasswordValid(password)){
//				Application mapplication = Application.getApplication();
//				//��¼
//				loginlistener.OnLoginSuccess(mapplication.getMDB().login(username, password));
//			}else{//�û������벻�Ϸ�
//				JOptionPane.showMessageDialog(null, "�û�����������Ч��", "��ʾ",JOptionPane.ERROR_MESSAGE);
//			}
			
			TF_username.setText("");
			TF_password.setText("");
		}else if(e.getActionCommand().equals("ȡ��")){  //����ȡ����
			System.exit(0);
		}
	}
}
