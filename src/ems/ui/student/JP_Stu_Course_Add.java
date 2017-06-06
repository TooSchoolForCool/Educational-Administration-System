package ems.ui.student;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ems.Application;
import ems.db.MDB;
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

public class JP_Stu_Course_Add extends JPanel implements ActionListener{
	
	CheckableItem[] items;
	JScrollPane scrollPane;
	JList jlist;
	
	private String stuid;
	
	public JP_Stu_Course_Add(String name, String stuid){
		init(name);
		this.stuid = stuid;
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton bt1 = new JButton("����");
		bt1.setBounds(20, 15, 120, 30);
		bt1.setFont(UIutils.font);
		bt1.addActionListener(this);
		add(bt1);
		
		JButton bt2 = new JButton("ѡ��");
		bt2.setBounds(20, 60, 120, 30);
		bt2.setFont(UIutils.font);
		bt2.addActionListener(this);
		add(bt2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(154, 15, 460, 380);
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
	
	private CheckableItem[] createItems(ArrayList<String> strs) { 
	    int n = strs.size(); 
	    CheckableItem[] items = new CheckableItem[n]; 
	    for (int i = 0; i < n; i++) { 
	      items[i] = new CheckableItem(strs.get(i)); 
	    } 
	    return items; 
	}

	private class CheckableItem {
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
		Application application = Application.getApplication();
		MDB mdb = application.getMDB();
		String id = application.getLoginID();
		if(e.getActionCommand().equals("����")){
			//���ú������String������strs
			
			
			ArrayList<String> tmp = mdb.getStuAvailableCourse();
			for(String s:tmp){
				System.out.println(s);
			}
			
			items = createItems(tmp);
			
			//���ӵ���������
			jlist = new JList(items);
			jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jlist.setCellRenderer(new CheckListCellRenderer());
			jlist.addMouseListener(new CheckListener(jlist));
			scrollPane.setViewportView(jlist);
			
			
			
		}else if(e.getActionCommand().equals("ѡ��")){
			//����items�����ѡ��
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