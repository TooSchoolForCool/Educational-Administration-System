package ems;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ems.db.DBHelper;
import ems.db.MDB;
import ems.ui.JF_Login;
import ems.ui.JF_Student;
import ems.ui.JF_Teacher;
import ems.ui.JF_Login.OnLoginSuccessListener;
import ems.user.Student;

public class Application implements OnLoginSuccessListener{
	
	private static Application mapplication;
	private MDB mdb;
	
	private String LoginID;
	
	private static JF_Login JFlogin;
	private static JF_Student JFStudent;
	private static JF_Teacher JFTeacher;
	
	public static void main(String[] args) {
		mapplication = new Application();
		
//		setUIFont();
		
		JFlogin = new JF_Login();
		JFlogin.setOnLoginSuccessListener(mapplication);
		
	}
	
	public Application(){
		mdb = new MDB(DBHelper.DB_DRIVER, DBHelper.DB_TYPE, 
				DBHelper.DB_SERVER_ADD, DBHelper.DB_NAME, 
				DBHelper.DB_USERNAME, DBHelper.DB_PASSWORD);
		this.setLookNFeel();
	}
	
	public static Application getApplication(){
		return mapplication;
	}
	
	public MDB getMDB(){
		return mdb;
	}
	
	public void setLoginID(String LoginID){
		this.LoginID = LoginID;
	}
	
	public String getLoginID(){
		return this.LoginID;
	}
	
	@Override
	public void OnLoginSuccess(int Authoritiy) {
		
		JFlogin.setVisible(false);
		
		switch(Authoritiy){
		case 0:
			
			
			
			break;
		case 1:
			
			JFTeacher = new JF_Teacher(LoginID);
			
			break;
		case 2:
			
			JFStudent = new JF_Student(LoginID);
			
			break;
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
