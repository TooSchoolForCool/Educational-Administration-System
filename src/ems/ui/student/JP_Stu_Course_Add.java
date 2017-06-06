package ems.ui.student;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ems.Application;
import ems.db.MDB;
import ems.utils.CheckableItem;
import ems.utils.UIutils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class JP_Stu_Course_Add extends JPanel implements ActionListener,MouseListener{
	
	CheckableItem[] items;
	JScrollPane scrollPane;
	JList jlist;
	
	private String stuid;
	
	public JP_Stu_Course_Add(String name){
		init(name);
		
		Application ap = Application.getApplication();
		this.stuid = ap.getLoginID();
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton bt1 = new JButton("检索");
		bt1.setBounds(488, 15, 120, 30);
		bt1.setFont(UIutils.font);
		bt1.addActionListener(this);
		add(bt1);
		
		JButton bt2 = new JButton("选课");
		bt2.setBounds(488, 60, 120, 30);
		bt2.setFont(UIutils.font);
		bt2.addActionListener(this);
		add(bt2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 15, 460, 380);
		add(scrollPane);
		
		
		
		
		
	}
	
	/**
	 * 鼠标点击复选框的响应方法
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(jlist.getSelectedIndex()<0){
			return;
		}
		CheckableItem tmp = (CheckableItem)jlist.getSelectedValue();
		tmp.setSelected(!tmp.isSelected());
		jlist.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * 按键监听响应方法
	 */
	public void actionPerformed(ActionEvent e) {
		Application application = Application.getApplication();
		MDB mdb = application.getMDB();
		String id = application.getLoginID();
		if(e.getActionCommand().equals("检索")){
			//调用函数获得String数组如strs
			
			ArrayList<String> tmp = mdb.getStuAvailableCourse();
			for(String s:tmp){
				System.out.println(s);
			}
			
			items = CheckableItem.createItems(tmp);
			
			//添加到滚动界面
			jlist = new JList(items);
			jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlist.setCellRenderer(new CheckableItem.CheckListCellRenderer());
			jlist.addMouseListener(this);
			scrollPane.setViewportView(jlist);
			
			
			
		}else if(e.getActionCommand().equals("选课")){
			//遍历items获得已选的
			scrollPane.setViewportView(null);
			for(CheckableItem item:items){
				if(item.isSelected()){
					System.out.println(item.toString());
					mdb.addNewCourse4Student(id, item.toString());
					item.setSelected(!item.isSelected());
				}
			}
		}
		
	}
}
