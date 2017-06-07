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
	
	public boolean addNewCourse(String cname, String cid, String term, String dept, String tid)
	{
		boolean ret = true;
		
		String sql = generateInsertSQL("Courses", 5);
		
		try {
			Connection conn = getConnection();
			PreparedStatement preStmt = conn.prepareStatement(sql);  
			
	        preStmt.setString(1, cname);  
	        preStmt.setString(2, cid);
	        preStmt.setString(3, term);
	        preStmt.setString(4, dept);
	        preStmt.setString(5, tid);
	        
	        int update_res = preStmt.executeUpdate();
	        
	        if(update_res != 1)
	        	ret = false;
			
	        preStmt.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
	}
	
	public boolean addNewCourse4Student(String sid, String course_info)
	{
		boolean ret = true;
		String[] c_infos = course_info.split(" ");
		String sql = generateInsertSQL("SC", 4);
		
		try {
			Connection conn = getConnection();
	    	
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select * from sc where '" + sid + "' = Sid AND '" + c_infos[0] + 
					"' = Cid AND Term = '" + c_infos[2] + "';");
			
			if( !res.next() )
			{
				// User table
		    	PreparedStatement preStmt = conn.prepareStatement(sql);  
		        preStmt.setString(1, sid);  
		        preStmt.setString(2, c_infos[0]);
		        preStmt.setString(3, c_infos[2]);
		        preStmt.setNull(4, Types.INTEGER);
		        
		        preStmt.executeUpdate();
		        
		        preStmt.close();
			}
			else
				ret = false;
			
			res.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
	}
	
	/**
	 * ָ��ѧ���˿�
	 * 
	 * @param sid ѧ��ѧ��
	 * @param cid �γ̴���
	 * @param term ѧ�ں�
	 * 
	 * @return �˿γɹ�/ʧ��
	 */
	public boolean dropStuCourse(String sid, String cid, String term)
	{
		boolean ret = true;
		
		try {
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("select * from sc where '" + sid + "' = Sid AND '" + cid + 
					"' = Cid AND Term = '" + term + "';");
			
			if( res.next() )
			{
				int sql_res = stmt.executeUpdate("delete from sc where Sid = '" + sid + "' AND Cid = '" + cid
						+ "' AND Term = '" + term + "';");
				
				if(sql_res != 1)
					ret =false;
			}
			else
				ret = false;
			
			res.close();
			stmt.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
	}

	public ArrayList<String> getCourseInfo(String cname)
	{
		ArrayList<String> arraylist = new ArrayList<String>();
		
		try {
			Connection conn = getConnection();

			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select * from courses where cname = '" + cname + "';");

			if(res.next())
			{
				arraylist.add(res.getString(1));
				arraylist.add(res.getString(2));
				arraylist.add(res.getString(3));
				arraylist.add(res.getString(4));
				arraylist.add(res.getString(5));
			}
			
			res.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		if(arraylist.size() == 0)
			return null;
		else
			return arraylist;
	}
	
	public boolean updateCourseInfo(String cname, String cid, String term, String dept, String tid)
	{
		boolean ret = true;
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			
			int update_res = stmt.executeUpdate("update Courses set Cname = '" + cname + "', Term = '"
					+ term + "', Cdepart = '" + dept + "', Tid = '" + tid + "' where Cid = '" + cid + "';");
	        
	        if(update_res != 1)
	        	ret = false;
			
	        stmt.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
	}
	
	/**
	 * ��ȡѧ��ѡ����Ϣ
	 * 
	 * @param Sid ѧ��ѧ��
	 * 
	 * @return ����String ArrayList��ν�� (�γ̴��� �γ��� ѧ�� ����Ժϵ �ڿ���ʦ)
	 */
	public ArrayList<String> getStudentEnrolledCourses(String id) {
		ArrayList<String> arraylist = new ArrayList<String>();
		
		try {
			Connection conn = getConnection();

			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery(
					"select courses.Cid, Cname, courses.Term, Cdepart, Tname from courses, "
					+ "sc, Teachers where '" + id + "' = sc.Sid AND sc.cid=courses.cid AND courses.Tid = Teachers.Tid;");

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
	public boolean updatePassword(String id, String newpass){
		try {
			Connection conn = getConnection();
			
			sql = "update User set Password='"+newpass+"'where LoginID='"+ id + "'";
			
			
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
			
			stmt.close();
			conn.close();
			
			if(result == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ѧ����Ϣ��ѯ����
	 * @param Sid	ѧ��
	 * @param jta	�ı���
	 * @throws SQLException
	 */
	public String queryStudentInfo(String Sid){
		String ret = "";
		
		try {
			Connection conn = getConnection();
			
			sql="select * from Students where Sid='"+Sid+"'";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
			
	        while(rs.next())
	        {
	        	ret += rs.getString(1) + " ";
	        	ret += rs.getString(2) + " ";
	        	ret += rs.getString(3) + " ";
	        	ret += rs.getString(4) + " ";
	        	ret += rs.getString(5) + " ";
	        	ret += rs.getString(6) + " ";
	        }
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	        
	        return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * ѧ����Ϣ��ѯ����
	 * @param Sid	ѧ��
	 * @param jta	�ı���
	 * @throws SQLException
	 */
	public String queryTeacherInfo(String tid){
		String ret = "";
		
		try {
			Connection conn = getConnection();
			
			sql="select * from Teachers where Tid='"+tid+"'";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
			
	        while(rs.next())
	        {
	        	ret += rs.getString(1) + " ";
	        	ret += rs.getString(2) + " ";
	        	ret += rs.getString(3) + " ";
	        	ret += rs.getString(4) + " ";
	        	ret += rs.getString(5) + " ";
	        	ret += rs.getString(6) + " ";
	        }
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	        
	        return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	
	/**
	 * ��ȡһ��ѧ���Ͽ�ʱ��͵ص�
	 * 
	 * @param id ����ѯѧ����ѧ��
	 * 
	 * @return ��ѡ�γ̵���Ϣ([ѧ�� �γ̴���] �γ��� �ڿ���ʦ \r\n ʱ�� �ص�)
	 */
	public ArrayList<String> getStuClassTimeAndPlace(String stuid){
		ArrayList<String> ret = new ArrayList<String>();
		
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select sc.Cid, courses.Cname, courses.Term, CT.Ctime, "
					+ "CT.Cplace, Teachers.Tname from sc, courses, CT, Teachers where sc.Sid = '" 
					+ stuid + "' AND courses.Tid = Teachers.Tid AND CT.Cid = SC.Cid AND SC.Cid = Courses.Cid");
						
			while(res.next()){
				String db_Cid = res.getString(1);
				String db_Cname = res.getString(2);
				String db_Term = res.getString(3);
				String db_Ctime = res.getString(4);
				String db_Cplace = res.getString(5);
				String db_Tname = res.getString(6);
				
				String item = "[" + db_Term + " " + db_Cid + "] " + db_Cname + " " +  db_Tname
						+ " " + db_Ctime + " " + db_Cplace;
				
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
	
	/**
	 * ��ȡ��ʦ���ڿ���Ϣ
	 * 
	 * @param id ����ѯ��ʦ�Ĺ���
	 * 
	 * @return ��ѡ�γ̵���Ϣ([ѧ�� �γ̴���] �γ��� ʱ�� �ص�)
	 */
	public ArrayList<String> getTeacherClassInfo(String tid){
		ArrayList<String> ret = new ArrayList<String>();
		
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select courses.Cid, courses.Cname, courses.Term, CT.Ctime, "
					+ "CT.Cplace from courses, CT where courses.Tid = '" + tid + "' AND courses.Cid = CT.Cid;");
						
			while(res.next()){
				String db_Cid = res.getString(1);
				String db_Cname = res.getString(2);
				String db_Term = res.getString(3);
				String db_Ctime = res.getString(4);
				String db_Cplace = res.getString(5);
				
				String item = "[" + db_Term + " " + db_Cid + "] " + db_Cname + " " + db_Ctime + " " + db_Cplace;
				
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
	
	/**
	 * ��ȡ��ʦ���ڿΰ༶ѧ��
	 * 
	 * @param id ����ѯ��ʦ�Ĺ���
	 * 
	 * @return �γ�ע��ѧ����Ϣ([ѧ�� �γ̴��� �γ���] \n ѧ��1(ѧ�� ���� Ժϵ רҵ) \n ѧ��2 \n ... -----------------------------)
	 */
	public ArrayList<String> getTeacherClassStudent(String tid){
		ArrayList<String> ret = new ArrayList<String>();
		
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select Term, Cid, Cname from Courses where Tid = '" + tid + "';");
						
			while(res.next()){
				String db_Term = res.getString(1);
				String db_Cid = res.getString(2);
				String db_Cname = res.getString(3);
				
				String item = "[" + db_Term + " " + db_Cid + " " + db_Cname + "]\n";
				
				String query_student_sql = "select students.Sid, Sname, Taca, Tdepart from Students, SC "
						+ "where Students.Sid = SC.Sid AND SC.Cid = '" + db_Cid + "';";
				
				Statement stmt_query_students = conn.createStatement();
				ResultSet student_res = stmt_query_students.executeQuery(query_student_sql);
				int cnt = 1;
				
				while(student_res.next())
				{
					item += cnt++ + ". " + student_res.getString(1) + " ";
					item += student_res.getString(2) + " ";
					item += student_res.getString(3) + " ";
					item += student_res.getString(4) + "\n";
				}
				
				ret.add(item);
				
				student_res.close();
				stmt_query_students.close();
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
	
	/**
	 * ��ȡһ��ѧ��������Ϣ
	 * 
	 * @param id ����ѯѧ����ѧ��
	 * 
	 * @return ������Ϣ([ѧ�� �γ̴���] �γ��� ʱ�� �ص�)
	 */
	public ArrayList<String> getStuExamInfo(String stuid){
		ArrayList<String> ret = new ArrayList<String>();
		
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select Exams.Cid, Courses.Cname, Exams.Term, Exams.Etime, "
					+ "Exams.Eplace from Exams, Courses, SC where SC.Sid = '" + stuid + "' AND SC.Cid = Courses.Cid "
					+ "AND Courses.Cid = Exams.Cid");
			
			while(res.next()){
				String db_Cid = res.getString(1);
				String db_Cname = res.getString(2);
				String db_Term = res.getString(3);
				String db_Etime = res.getString(4);
				String db_Eplace = res.getString(5);
				
				String item = "[" + db_Term + " " + db_Cid + "] " + db_Cname + " "
						+ db_Etime + " " + db_Eplace;
				
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
	
	/**
	 * ��ȡһ��ѧ���ɼ���Ϣ
	 * 
	 * @param id ����ѯѧ����ѧ��
	 * 
	 * @return ������Ϣ([ѧ�� �γ̴���] �γ��� �ɼ�)
	 */
	public ArrayList<String> getStuScoreInfo(String stuid){
		ArrayList<String> ret = new ArrayList<String>();
		
		try{
			Connection conn = getConnection();
			
			Statement stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery("select SC.Cid, Courses.Cname, SC.Term, SC.Grade "
					+ "from SC, Courses where SC.Cid = Courses.Cid AND SC.Sid = '" + stuid + "';");
			
			while(res.next()){
				String db_Cid = res.getString(1);
				String db_Cname = res.getString(2);
				String db_Term = res.getString(3);
				int db_Grade = res.getInt(4);
				
				String item = "[" + db_Term + " " + db_Cid + "] " + db_Cname + " " + db_Grade;
				
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
