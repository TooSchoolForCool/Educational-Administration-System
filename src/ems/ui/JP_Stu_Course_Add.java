package ems.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ems.utils.UIutils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class JP_Stu_Course_Add extends JPanel implements ActionListener{
	
	
	
	public JP_Stu_Course_Add(String name){
		init(name);
		
	}
	
	public void init(String name){
		setLayout(null);
		setName(name);
		setBounds(0, 0, 700, 500);
		setFont(UIutils.font);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(74, 28, 113, 27);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 28, 408, 431);
		add(scrollPane);
		
		String[] strs = {"一","二","三","四","五"};
		
		CheckableItem[] items = createData(strs);
		JList list = new JList(items);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CheckListCellRenderer renderer = new CheckListCellRenderer();
		list.setCellRenderer(renderer);
		scrollPane.setViewportView(list);
//		scrollPane.add(list);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(74, 82, 113, 27);
		add(btnNewButton_1);
		
		
		
		
		
		
		
		
		
		
		

		
	}
	
	private CheckableItem[] createData(String[] strs) { 
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
			
			
			
			
//			System.out.println(index+" "+list.getSelectedIndex());
//			setSelected(isSelected);
			
			
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
			CheckableItem tmp = (CheckableItem)list.getSelectedValue();
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
		
	}
}
