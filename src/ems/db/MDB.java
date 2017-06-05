package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Types;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JTextArea;
import com.mysql.jdbc.ResultSetMetaData;
import ems.user.Student;

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
	public MDB(String DB_driver, String DB_type, String DB_server_addr, String DB_name, String DB_username,
			String DB_password) {
		DB_driver_ = DB_driver;
		DB_type_ = DB_type;
		DB_server_addr_ = DB_server_addr;
		DB_name_ = DB_name;
		DB_username_ = DB_username;
		DB_password_ = DB_password;

		try {
			Connection conn = getConnection();

			if (!conn.isClosed()) {
				System.out.println("���ݿ����ӳɹ�!");
			} else {
				System.out.println("���ݿ�����ʧ��!");
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �жϵ�¼�û��Ƿ�Ϸ�
	 * 
	 * @param username
	 *            ��¼�û���
	 * @param password
	 *            ��¼����
	 * 
	 * @return �û����ͣ�����Ա(0), ��ʦ(1), ѧ��(2), �������/�û�������(-1)
	 */
	public int login(String username, String password) {
		try {
			Connection conn = getConnection();

			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from User;");

			int ret = -1;

			while (res.next()) {
				String db_username = res.getString(1);
				String db_password = res.getString(2);
				String db_user_type = res.getString(3);

				if (db_username.equals(username) && db_password.equals(password)) {
					ret = Integer.parseInt(db_user_type);
					break;
				}
			}

			res.close();
			stmt.close();
			conn.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean addNewCourse4Student(String sid, String course_info)
	{
		String[] c_infos = course_info.split(" ");
		String sql = generateInsertSQL("SC", 4);
		
		try {
			Connection conn = getConnection();
	    	
	    	// User table
	    	PreparedStatement preStmt = conn.prepareStatement(sql);  
	        preStmt.setString(1, sid);  
	        preStmt.setString(2, c_infos[0]);
	        preStmt.setString(3, c_infos[2]);
	        preStmt.setNull(4, Types.INTEGER);
	        
	        preStmt.executeUpdate();
			
			preStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * ��ȡѧ��ѡ����Ϣ
	 * 
	 * @param Sid ѧ��ѧ��
	 * 
	 * @return ����String ArrayList��ν��
	 */
	public ArrayList<String> getStudentEnrolledCourses(String id) {
		ArrayList<String> arraylist = new ArrayList<String>();
		
		try {
			Connection conn = getConnection();

			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery(
					"select courses.Cid, Cname, courses.Term, Cdepart, Tname from courses, "
					+ "sc, Teachers where " + id + " = sc.Sid AND sc.cid=courses.cid AND courses.Tid = Teachers.Tid;");

			while (res.next()) {
				// Retrieve by column name
				String db_Cid = res.getString(1);
				String db_Cname = res.getString(2);
				String db_Term = res.getString(3);
				String db_Cdept = res.getString(4);
				String db_Tname = res.getString(5);

				String result = db_Cid + " " + db_Cname + " " + db_Term + " " + db_Cdept + " " + db_Tname;

				arraylist.add(result);
			}
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return arraylist;
	}

	private Connection getConnection() throws SQLException {
		try {
			Class.forName(DB_driver_);
			String url = "jdbc:" + DB_type_ + "://" + DB_server_addr_ + "/" + DB_name_ + "?characterEncoding=utf-8&useSSL=false";
			return DriverManager.getConnection(url, DB_username_, DB_password_);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	public void queryStudentInfo(String Sid,JTextArea jta) throws SQLException{
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
	 * �������ʦ
	 * 
	 * @param id ����
	 * @param pw ����
	 * @param name ����
	 * @param age ����
	 * @param gender �Ա�
	 * @param dept Ժϵ
	 * @param major רҵ
	 * 
	 * @return true ��ӳɹ�; false ���ʧ��
	 */
	public boolean addNewTeacher(String id, String pw, String name, 
			String age, String gender, String dept, String major)
	{
		String sql_user_insert = generateInsertSQL("User", 3);
		String sql_teacher_insert = generateInsertSQL("Teachers", 6);
		
	    try{
	    	Connection conn = getConnection();
	    	
	    	// User table
	    	PreparedStatement preStmt = conn.prepareStatement(sql_user_insert);  
	        preStmt.setString(1, id);  
	        preStmt.setString(2, pw);
	        preStmt.setInt(3, 1);
	        
	        preStmt.executeUpdate(); 
	        
	        // Teachers table
	        preStmt = conn.prepareStatement(sql_teacher_insert);
	        preStmt.setString(1, id);
	        preStmt.setString(2, name);
	        preStmt.setInt(3, Integer.parseInt(age));
	        preStmt.setString(4, gender);
	        preStmt.setString(5, dept);
	        preStmt.setString(6, major);
	        
	        preStmt.executeUpdate();
	        
	        preStmt.close();
			conn.close();
			
	        return true;
	    }  
	    catch (SQLException e)  
	    {  
	        e.printStackTrace();  
	        return false;
	    }
	}
	
	/**
	 * �������ʦ
	 * 
	 * @param id ѧ��
	 * @param pw ����
	 * @param name ����
	 * @param age ����
	 * @param gender �Ա�
	 * @param dept Ժϵ
	 * @param major רҵ
	 * 
	 * @return true ��ӳɹ�; false ���ʧ��
	 */
	public boolean addNewStudent(String id, String pw, String name, 
			String age, String gender, String dept, String major)
	{
		String sql_user_insert = generateInsertSQL("User", 3);
		String sql_teacher_insert = generateInsertSQL("Students", 6);
		
	    try{
	    	Connection conn = getConnection();
	    	
	    	// User table
	    	PreparedStatement preStmt = conn.prepareStatement(sql_user_insert);  
	        preStmt.setString(1, id);  
	        preStmt.setString(2, pw);
	        preStmt.setInt(3, 2);
	        
	        preStmt.executeUpdate(); 
	        
	        // Teachers table
	        preStmt = conn.prepareStatement(sql_teacher_insert);
	        preStmt.setString(1, id);
	        preStmt.setString(2, name);
	        preStmt.setInt(3, Integer.parseInt(age));
	        preStmt.setString(4, gender);
	        preStmt.setString(5, dept);
	        preStmt.setString(6, major);
	        
	        preStmt.executeUpdate();
	        
	        preStmt.close();
			conn.close();
			
	        return true;
	    }  
	    catch (SQLException e)  
	    {  
	        e.printStackTrace();  
	        return false;
	    }
	}
	
	/**
	 * ��ȡһ��ѧ������ѡ��Ŀγ�
	 * 
	 * @param id ����ѯѧ����ѧ��
	 * 
	 * @return ��ѡ�γ̵���Ϣ([�γ̴���] �γ��� ѧ�� ����Ժϵ ��ʦ����)
	 */
	public ArrayList<String> getStuAvailableCourse(){
		ArrayList<String> ret = new ArrayList<String>();
		
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select Cname, Cid, Term, Cdepart, Tname from Courses, Teachers where Courses.Tid = Teachers.Tid;");
						
			while(res.next()){
				String db_Cname = res.getString(1);
				String db_Cid = res.getString(2);
				String db_Term = res.getString(3);
				String db_Cdept = res.getString(4);
				String db_Tname = res.getString(5);
				
				String item = db_Cid + " " + db_Cname + " " + db_Term + " " 
					+ db_Cdept + " " + db_Tname;
			
				ret.add(item);
			}
			
			res.close();
			stmt.close();
			conn.close();
			
			return ret;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String generateInsertSQL(String tbl, int num_items)
	{
		String ret = "insert into " + tbl + " values(";
		
		for(int i = 0; i < num_items - 1; i++)
		{
			ret += "?,";
		}
		ret += "?)";
		
		return ret;
	}
}
