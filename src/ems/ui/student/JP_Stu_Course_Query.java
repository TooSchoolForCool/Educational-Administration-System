package ems.ui.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import ems.Application;
import ems.db.MDB;
import ems.utils.CheckableItem;
import ems.utils.UIutils;

public class JP_Stu_Course_Query extends JPanel implements ActionListener,MouseListener{
	private String stuid;

	CheckableItem[] items;
	JScrollPane scrollPane;
	JList jlist;
	
	public JP_Stu_Course_Query(String name){
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
		
		JButton bt2 = new JButton("退课");
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
	 * 修改密码接口
	 * 点击修改按钮，比较新旧密码，传参，进行修改
	 */
	public void actionPerformed(ActionEvent e) {
		Application application = Application.getApplication();
		MDB mdb = application.getMDB();
		String id = application.getLoginID();
		if(e.getActionCommand().equals("检索")){
			//调用函数获得String数组
			ArrayList<String> class_info = mdb.getStuClassTimeAndPlace(stuid);
			
			for(String s:class_info){
				System.out.println(s);
			}
			//获得CheckableItem
			items = CheckableItem.createItems(class_info);
			
			//添加到滚动界面
			jlist = new JList(items);
			jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlist.setCellRenderer(new CheckableItem.CheckListCellRenderer());
			jlist.addMouseListener(this);
			scrollPane.setViewportView(jlist);
			
			
			
		}else if(e.getActionCommand().equals("退课")){
			//遍历items获得已选的
			scrollPane.setViewportView(null);
			System.out.println("选择的退课：");
			ArrayList<String> listodel = new ArrayList<String>();
			
			for(CheckableItem item:items){
				if(item.isSelected()){
					String tmp = item.toString();
					String tmp2 = tmp.substring(tmp.indexOf('[')+1,tmp.indexOf(']'));
					if(!listodel.contains(tmp2)){
						listodel.add(tmp2);
					}
					item.setSelected(!item.isSelected());
				}
			}
			boolean[] f = new boolean[listodel.size()];
			for(int i=0;i<listodel.size();i++){
				System.out.println(listodel.get(i));
				
			}
		}
	}
}
