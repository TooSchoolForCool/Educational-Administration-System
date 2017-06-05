package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ems.user.Student;
import ems.user.User;

public class MDB {
	// JDBC���ݿ�����
	private String DB_driver_;
	// ���ݿ�����
	private String DB_type_;
	// ʹ�õ����ݿ�����
	private String DB_name_;
	// ���ݿ��¼�û���
	private String DB_username_;
	// ���ݿ�����
	private String DB_password_;
	// ���ݿ��������ַ
	private String DB_server_addr_;
	
	/**  
	 * �������ݿ�����
	 *  
	 * @param[in] DB_driver_ JDBC���ݿ�����
	 * @param[in] DB_type ʹ�����ݿ�����
	 * @param[in] DB_server_addr ���ݿ��������ַ
	 * @param[in] DB_name ʹ�õ����ݿ�����
	 * @param[in] DB_username ��¼�û���
	 * @param[in] DB_password ��¼����	 
	 */
	public MDB(String DB_driver, String DB_type, String DB_server_addr, String DB_name, 
		String DB_username, String DB_password)
	{
		DB_driver_ = DB_driver;
		DB_type_= DB_type;
		DB_server_addr_ = DB_server_addr;
		DB_name_ = DB_name;
		DB_username_ = DB_username;
		DB_password_ = DB_password;

		try{
			Connection conn = getConnection();
			
			if (!conn.isClosed()) {
                System.out.println("���ݿ����ӳɹ�!");
            }else{
            	System.out.println("���ݿ�����ʧ��!");
            }
			
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �жϵ�¼�û��Ƿ�Ϸ�
	 * 
	 * @param username ��¼�û���
	 * @param password ��¼����
	 * 
	 * @return �û����ͣ�����Ա(0), ��ʦ(1), ѧ��(2), �������/�û�������(-1)
	 */
	public int login(String username,String password)
	{
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from Users;");
			
			int ret = -1;
			
			while(res.next())
			{
				String db_username = res.getString(1);
				String db_password = res.getString(2);
				String db_user_type = res.getString(3);
				
				if(db_username.equals(username) && db_password.equals(password))
				{
					ret = Integer.parseInt(db_user_type);
					break;
				}
			}
			
			res.close();
			stmt.close();
			conn.close();
			
			return ret;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	private Connection getConnection() throws SQLException
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:" + DB_type_ + "://" + DB_server_addr_ + "/" + DB_name_ + "?useSSL=false";
			return DriverManager.getConnection(url, DB_username_, DB_password_);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}












