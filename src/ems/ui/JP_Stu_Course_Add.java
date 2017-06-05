package ems.ui;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ems.utils.UIutils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class JP_Stu_Course_Add extends JPanel implements ActionListener{
	
	CheckableItem[] items;
	JScrollPane scrollPane;
	JList jlist;
	
	public JP_Stu_Course_Add(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton bt1 = new JButton("检索");
		bt1.setBounds(51, 26, 113, 27);
		bt1.setFont(UIutils.font);
		bt1.addActionListener(this);
		add(bt1);
		
		JButton bt2 = new JButton("选课");
		bt2.setBounds(51, 85, 113, 27);
		bt2.setFont(UIutils.font);
		bt2.addActionListener(this);
		add(bt2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 28, 408, 431);
		add(scrollPane);
		
		
		
		
		
	}
	
	private CheckableItem[] createItems(String[] strs) { 
	    int n = strs.length; 
	    CheckableItem[] items = new CheckableItem[n]; 
	    for (int i = 0; i < n; i++) { 
	      items[i] = new CheckableItem(strs[i]); 
	    } 
	    return items; 
	  } 

	class CheckableItem {
		private boolean isSelected; 
		private String str; 
		public CheckableItem(String str) { 
			this.str = str; 
			this.isSelected = false; 
		}
		public void setSelected(boolean b) { 
			this.isSelected = b; 
		}
		public boolean isSelected() { 
			return this.isSelected; 
		}
		public String toString() { 
			return this.str;
		}
	}
	
	class CheckListCellRenderer extends JCheckBox implements ListCellRenderer {
		private Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
		public CheckListCellRenderer() {
			super();
			setOpaque(true);
			setBorder(m_noFocusBorder);
		}
		
		public Component getListCellRendererComponent(JList list, Object value, 
				int index, boolean isSelected, boolean cellHasFocus) { 
			
			setBackground(isSelected ? list.getSelectionBackground() : list.getBackground()); 
			setForeground(isSelected ? list.getSelectionForeground() : list.getForeground()); 
			setText(value.toString());
			CheckableItem data = (CheckableItem) value;
			setSelected(data.isSelected());
			setFont(list.getFont());
			setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : m_noFocusBorder);
			
			return this;
		}
	}
	
	class CheckListener implements MouseListener {
		private JList list; 
		public CheckListener(JList list){
			this.list = list;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(list.getSelectedIndex()<0){
				return;
			}
			CheckableItem tmp = (CheckableItem)list.getSelectedValue();
			tmp.setSelected(!tmp.isSelected());
			list.repaint();
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("检索")){
			//调用函数获得String数组如strs
			String[] strs = {"一","二","三","四","五"};
			items = createItems(strs);
			
			//添加到滚动界面
			jlist = new JList(items);
			jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlist.setCellRenderer(new CheckListCellRenderer());
			jlist.addMouseListener(new CheckListener(jlist));
			scrollPane.setViewportView(jlist);
			
			
			
		}else if(e.getActionCommand().equals("选课")){
			//遍历items获得已选的
			
			
			
		}
		
		
	}
}
