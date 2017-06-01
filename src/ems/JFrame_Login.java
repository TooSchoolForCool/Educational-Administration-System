package ems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ems.utils.Utils;

public class JFrame_Login extends JFrame implements ActionListener{
	/**用户名输入框*/
	private JTextField TF_username = null;
	/**密码输入框*/
	private JPasswordField TF_password = null;
	/**密码可见复选框*/
	private JCheckBox CB_password = null;
	/**构造方法*/
	public JFrame_Login() {
		
		this.initialize();//初始化
	}
	/**初始化方法*/
	private void initialize() {
		this.setTitle("教务管理系统-登录");
		this.setBounds(100, 100, 400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//账号标签
		JLabel label = new JLabel("账号");
		label.setBounds(25, 23, 50, 30);
		label.setFont(Utils.font);
		this.getContentPane().add(label);
		//密码标签
		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(25, 73, 50, 30);
		label_1.setFont(Utils.font);
		this.getContentPane().add(label_1);
		//用户名输入框
		TF_username = new JTextField();
		TF_username.setBounds(94, 23, 274, 30);
		TF_username.setFont(Utils.font);
		TF_username.setColumns(10);
		this.getContentPane().add(TF_username);
		//密码输入框
		TF_password = new JPasswordField();
		TF_password.setBounds(94, 73, 274, 30);
		TF_password.setFont(Utils.font);
		TF_password.setEchoChar('*');
		this.getContentPane().add(TF_password);
		//密码可见复选框
		CB_password = new JCheckBox("");
		CB_password.setBounds(34, 122, 30, 29);
		CB_password.setActionCommand("密码可见");
		this.getContentPane().add(CB_password);
		//
		JButton BT_login = new JButton("登录");
		BT_login.setBounds(92, 122, 130, 30);
		BT_login.setFont(Utils.font);
		BT_login.setFocusable(false);
		this.getContentPane().add(BT_login);
		
		JButton BT_cancel = new JButton("取消");
		BT_cancel.setBounds(238, 122, 130, 30);
		BT_cancel.setFont(Utils.font);
		BT_cancel.setFocusable(false);
		this.getContentPane().add(BT_cancel);
		
		CB_password.addActionListener(this);
		BT_login.addActionListener(this);
		BT_cancel.addActionListener(this);
		
		
		this.setVisible(true);
	}
	
	/**按键等的监听方法*/
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("密码可见")){    //密码可见与否的复选框
			TF_password.setEchoChar(CB_password.isSelected()?(char)0:'*');
		}else if(e.getActionCommand().equals("登录")){ //按下登录键
			String username = TF_username.getText();
			String password = new String(TF_password.getPassword());
			
			//合法用户名和密码
			if(isUserNameValid(username)&&isPasswordValid(password)){
				
			}else{//用户名密码不合法
				
			}
			
			
			boolean flag = true;
			if(flag){
				
				
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "登录失败！", "提示",JOptionPane.ERROR_MESSAGE);
			}			
			TF_username.setText("");
			TF_password.setText("");
		}else if(e.getActionCommand().equals("取消")){  //按下取消键
			System.exit(0);
		}
	}
	
	/**判断是否为合法用户名*/
    private boolean isUserNameValid(String unm) {
        if(unm.contains(" ")||unm.contains(",")) {
            return false;
        }else{
            return true;
        }
    }
    
    /**判断是否为合法密码*/
    private boolean isPasswordValid(String pwd) {
        if (pwd.length() < 4 || pwd.contains(" ")) {
            return false;
        } else {
            return true;
        }
    }
	
	
	
	
}
