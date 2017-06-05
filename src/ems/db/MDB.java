package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JTextArea;

import com.mysql.jdbc.ResultSetMetaData;

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
	//Statement stmt;
	//��ѯ���
	private String sql;
	
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
		String DB_username, String DB_password){
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
	 * �����޸ĺ�����Ӧ���ٴ����˺�
	 * @param oripass	ԭ����
	 * @param newpass	������
	 * @throws SQLException
	 */
	public void update(String id,String oripass,String newpass) throws SQLException{
		Connection conn = getConnection();
		sql="update User set Password='"+newpass+"'where Password='"+oripass+"' and LoginID='"+id+"'";
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		if(result==-1){System.out.println("�޸�ʧ��");}
		else{System.out.println("�޸ĳɹ�");}
	}
	/**
	 * ѧ����Ϣ��ѯ����
	 * @param Sid	ѧ��
	 * @param jta	�ı���
	 * @throws SQLException
	 */
		public void query(String Sid,JTextArea jta) throws SQLException{
			Connection conn = getConnection();
			//���ڲ���ֻ��һ��Sid����������ʦ��ѯ���԰ѱ���Ҳ�ó���������
			sql="select * from Students where Sid='"+Sid+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
			// ��ȡ����  
	        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();  
	        for (int i = 0; i < metaData.getColumnCount(); i++) {  
	            // rs�����±��1��ʼ  
	            String columnName = metaData.getColumnName(i + 1);  
	            int type = metaData.getColumnType(i + 1);  
	            if (Types.INTEGER == type) {  
	            } else if (Types.VARCHAR == type) {  
	            }  
	            jta.append(columnName + "\t");
	            System.out.print(columnName + "\t");  
	        }  
	        jta.append("\r\n");
	        System.out.println();  
	        // ��ȡ����  
	        while (rs.next()) {  
	            for (int i = 0; i < metaData.getColumnCount(); i++) {  
	                // rs�����±��1��ʼ  
	            	jta.append(rs.getString(i + 1) + "\t");
	                System.out.print(rs.getString(i + 1) + "\t");  
	            }  
	            jta.append("\r\n");
	            System.out.println();
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
	public int login(String username,String password){
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from User;");
			
			int ret = -1;
			
			while(res.next()){
				String db_username = res.getString(1);
				String db_password = res.getString(2);
				String db_user_type = res.getString(3);
				
				if(db_username.equals(username) && db_password.equals(password)){
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
	
	private Connection getConnection() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:" + DB_type_ + "://" + DB_server_addr_ + "/" + DB_name_ + "?useSSL=false";
			return DriverManager.getConnection(url, DB_username_, DB_password_);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}












