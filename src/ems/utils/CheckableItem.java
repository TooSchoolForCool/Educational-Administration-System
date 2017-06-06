package ems.utils;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CheckableItem {
	private boolean isSelected; //已选
	private String str;    //信息
	
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
	
	
	public static CheckableItem[] createItems(String[] strs) { 
	    int n = strs.length; 
	    CheckableItem[] items = new CheckableItem[n]; 
	    for (int i = 0; i < n; i++) { 
	      items[i] = new CheckableItem(strs[i]); 
	    } 
	    return items; 
	}
	
	public static CheckableItem[] createItems(ArrayList<String> strs) { 
	    int n = strs.size(); 
	    CheckableItem[] items = new CheckableItem[n]; 
	    for (int i = 0; i < n; i++) { 
	      items[i] = new CheckableItem(strs.get(i)); 
	    } 
	    return items; 
	}
	
	public static class CheckListCellRenderer extends JCheckBox implements ListCellRenderer {
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
}
