package ems;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ems.db.MDB;
import ems.ui.JFrame_Login;
import ems.ui.JFrame_Student;
import ems.ui.JFrame_Login.OnLoginSuccessListener;
import ems.user.Student;
import ems.user.User;

public class Application implements OnLoginSuccessListener{
	
	private static Application mapplication;
	private MDB mdb;
	
	User user;
	
	private static JFrame_Login JFlogin;
	private static JFrame_Student JFStudent;
	
	public static void main(String[] args) {
		mapplication = new Application();
		
//		setUIFont();
		
		JFlogin = new JFrame_Login();
		JFlogin.setOnLoginSuccessListener(mapplication);
		
	}
	
	public Application(){
		mdb = new MDB("com.mysql.jdbc.Driver", "mysql", "localhost:3306", "HNU_DB", "root", "admin8888");
//		mdb = new MDB("com.mysql.jdbc.Driver", "mysql", "45.78.60.183:28324", "HNU_DB", "root", "Admin@2017");
		this.setLookNFeel();
	}
	

	public static Application getApplication(){
		return mapplication;
	}
	
	public MDB getMDB(){
		return mdb;
	}
	
	@Override
	public void OnLoginSuccess(User user) {
		if(user!=null){
			this.user = user;
			JFlogin.setVisible(false);
			
			switch(this.user.getIdentity()){
			case User.IDEN_STUDENT:
				
				JFStudent = new JFrame_Student((Student)this.user);
				
				break;
			case User.IDEN_TEACHER:
				
				
				break;
			case User.IDEN_MANAGER:
				
				
				break;
			}
			
			
		}else{
			JOptionPane.showMessageDialog(null, "µÇÂ¼Ê§°Ü£¡", "ÌáÊ¾",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void setLookNFeel(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if (info.getName().equals("Windows")) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
	}
	
	public static void setUIFont(){
		Font f = new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,20);
		String   names[]={ "JTextField", "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
				"JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
				"TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
				"OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
				"ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
				"PasswordField","TextField", "Table", "Label", "Viewport",
				"RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
		}; 
		for (String item : names) {
			 UIManager.put(item+ ".font",f); 
		}
	}
	
}
