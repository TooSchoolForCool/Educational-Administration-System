package ems.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ems.Application;
import ems.user.Student;
import ems.utils.UIutils;

public class JF_Login extends JFrame implements ActionListener,KeyListener{
	/**�û��������*/
	private JTextField TF_LoginID = null;
	/**���������*/
	private JPasswordField TF_PassWord = null;
	/**����ɼ���ѡ��*/
	private JCheckBox CB_password = null;
	
	OnLoginSuccessListener loginlistener;
	
	public interface OnLoginSuccessListener{
		void OnLoginSuccess(int Authoritiy);
	}
	
	public void setOnLoginSuccessListener(OnLoginSuccessListener loginlistener){
		this.loginlistener = loginlistener;
	}
	
	
	/**���췽��*/
	public JF_Login() {
		
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
		label.setFont(UIutils.font);       //����
		this.getContentPane().add(label);//��ӵ����
		//�����ǩ
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(25, 73, 50, 30);
		label_1.setFont(UIutils.font);
		this.getContentPane().add(label_1);
		//�û��������
		TF_LoginID = new JTextField();
		TF_LoginID.setBounds(94, 23, 274, 30);
		TF_LoginID.setFont(UIutils.font);
		TF_LoginID.setColumns(10);
		this.getContentPane().add(TF_LoginID);
		//���������
		TF_PassWord = new JPasswordField();
		TF_PassWord.setBounds(94, 73, 274, 30);
		TF_PassWord.setFont(UIutils.font);
		TF_PassWord.setEchoChar('*');
		TF_PassWord.addKeyListener(this);
		this.getContentPane().add(TF_PassWord);
		//����ɼ���ѡ��
		CB_password = new JCheckBox("");
		CB_password.setBounds(34, 122, 30, 29);
		CB_password.setActionCommand("����ɼ�");
		this.getContentPane().add(CB_password);
		//��½��
		JButton BT_login = new JButton("��¼");
		BT_login.setBounds(92, 122, 130, 30);
		BT_login.setFont(UIutils.font);
		BT_login.setFocusable(false);
		this.getContentPane().add(BT_login);
		//ȡ����
		JButton BT_cancel = new JButton("ȡ��");
		BT_cancel.setBounds(238, 122, 130, 30);
		BT_cancel.setFont(UIutils.font);
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
			TF_PassWord.setEchoChar(CB_password.isSelected()?(char)0:'*');
		}else if(e.getActionCommand().equals("��¼")){ //���µ�¼��
			Login();//��������ĵ�¼����
		}else if(e.getActionCommand().equals("ȡ��")){  //����ȡ����
			System.exit(0);
		}
	}

	/**
	 * �س�������Ӧ
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10){
			Login();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * ��¼����
	 */
	private void Login(){
		String LoginID = TF_LoginID.getText();
		String PassWord = new String(TF_PassWord.getPassword());
		//�Ϸ��û���������
		Application mapplication = Application.getApplication();
		int Authoritiy = mapplication.getMDB().login(LoginID, PassWord);
		
		if(Authoritiy == -1){// ret Ϊ�˻����ͣ�-1Ϊ��¼ʧ��
			JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�", "��ʾ",JOptionPane.ERROR_MESSAGE);
		}else{
			mapplication.setLoginID(LoginID);
			loginlistener.OnLoginSuccess(Authoritiy);  //
			//System.out.println(Authoritiy);
		}
		TF_LoginID.setText("");
		TF_PassWord.setText("");
	}
	
	
}
