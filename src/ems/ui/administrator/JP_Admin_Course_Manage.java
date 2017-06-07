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

		JLabel Label1 = new JLabel("课程名");
		Label1.setBounds(30, 60, 70, 30);
		Label1.setFont(UIutils.font);
		add(Label1);

		tf1 = new JTextField();
		tf1.setBounds(100, 60, 250, 30);
		tf1.setFont(UIutils.font);
		add(tf1);

		JLabel Label2 = new JLabel("课程号");
		Label2.setBounds(30, 140, 70, 30);
		Label2.setFont(UIutils.font);
		add(Label2);

		tf2 = new JTextField();
		tf2.setEditable(false);
		tf2.setBounds(100, 140, 250, 30);
		tf2.setFont(UIutils.font);
		add(tf2);

		JLabel Label3 = new JLabel("学期");
		Label3.setBounds(30, 180, 70, 30);
		Label3.setFont(UIutils.font);
		add(Label3);

		tf3 = new JTextField();
		tf3.setBounds(100, 180, 250, 30);
		tf3.setFont(UIutils.font);
		add(tf3);

		JLabel Label4 = new JLabel("专业");
		Label4.setBounds(30, 220, 70, 30);
		Label4.setFont(UIutils.font);
		add(Label4);

		tf4 = new JTextField();
		tf4.setBounds(100, 220, 250, 30);
		tf4.setFont(UIutils.font);
		add(tf4);

		JLabel Label5 = new JLabel("教师ID");
		Label5.setBounds(30, 260, 70, 30);
		Label5.setFont(UIutils.font);
		add(Label5);

		tf5 = new JTextField();
		tf5.setBounds(100, 260, 250, 30);
		tf5.setFont(UIutils.font);
		add(tf5);


		btquery = new JButton("检索课程");
		btquery.setBounds(380, 60, 100, 30);
		btquery.addActionListener(this);
		add(btquery);
		
		bt = new JButton("修改课程");
		bt.setBounds(160, 340, 100, 30);
		bt.addActionListener(this);
		add(bt);

	}
	
	/**
	 * 鼠标点击的响应方法，事件获取到的btquery为检索课程，bt为修改课程
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btquery) {		// 根据课程名，检索课程，调用查询函数
			String Cname = tf1.getText();  //课程名
//			System.out.println("Cname="+Cname);
			ArrayList<String> ret = Application.getApplication().getMDB().getCourseInfo(Cname);

			if(ret == null)
			{
				JOptionPane.showMessageDialog(null, "查无此课", "提示", JOptionPane.ERROR_MESSAGE);
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
		if (e.getSource() == bt) {// 调用修改函数，修改课程信息
			String Cname = tf1.getText();    	//课程名
			String Cid = tf2.getText();      	//课程号
			String Term = tf3.getText();      	//学期
			String Cdepart = tf4.getText();  	//专业
			String Tid = tf5.getText();      	//老师ID
//			System.out.println(Cname+" "+Cid+" "+Term+" "+Cdepart+" "+Tid);
			
			boolean ret = Application.getApplication().getMDB().updateCourseInfo(Cname, Cid, Term, Cdepart, Tid);
			
			if(ret == true)
				JOptionPane.showMessageDialog(null, "课程修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "课程修改失败", "提示", JOptionPane.ERROR_MESSAGE);
		}
	}
}
