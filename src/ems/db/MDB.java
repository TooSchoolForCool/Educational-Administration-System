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
	// JDBC数据库驱动
	private String DB_driver_;
	// 数据库类型
	private String DB_type_;
	// 使用的数据库名称
	private String DB_name_;
	// 数据库登录用户名
	private String DB_username_;
	// 数据库密码
	private String DB_password_;
	// 数据库服务器地址
	private String DB_server_addr_;

	//Statement stmt;
	//查询语句
	private String sql;
	
	/**
	 * 启动数据库连接
	 * 
	 * @param[in] DB_driver_ JDBC数据库驱动
	 * @param[in] DB_type 使用数据库类型
	 * @param[in] DB_server_addr 数据库服务器地址
	 * @param[in] DB_name 使用的数据库名称
	 * @param[in] DB_username 登录用户名
	 * @param[in] DB_password 登录密码
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
				System.out.println("数据库连接成功!");
			} else {
				System.out.println("数据库连接失败!");
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断登录用户是否合法
	 * 
	 * @param username
	 *            登录用户名
	 * @param password
	 *            登录密码
	 * 
	 * @return 用户类型：管理员(0), 教师(1), 学生(2), 密码错误/用户不存在(-1)
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
	 * 获取学生选课信息
	 * 
	 * @param Sid 学生学号
	 * 
	 * @return 返回String ArrayList查课结果
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
	 * 密码修改函数，应该再传入账号
	 * @param oripass	原密码
	 * @param newpass	新密码
	 * @throws SQLException
	 */
	public void update(String id,String oripass,String newpass) throws SQLException{
		Connection conn = getConnection();
		sql="update User set Password='"+newpass+"'where Password='"+oripass+"' and LoginID='"+id+"'";
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(sql);// executeQuery会返回结果的集合，否则返回空值
		if(result==-1){System.out.println("修改失败");}
		else{System.out.println("修改成功");}
	}
	/**
	 * 学生信息查询函数
	 * @param Sid	学号
	 * @param jta	文本区
	 * @throws SQLException
	 */
	public void queryStudentInfo(String Sid,JTextArea jta) throws SQLException{
		Connection conn = getConnection();
		//现在参数只有一个Sid，后面做教师查询可以把表名也拿出来做参数
		sql="select * from Students where Sid='"+Sid+"'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		// 获取列名  
        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();  
        for (int i = 0; i < metaData.getColumnCount(); i++) {  
            // rs数据下标从1开始  
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
        // 获取数据  
        while (rs.next()) {  
            for (int i = 0; i < metaData.getColumnCount(); i++) {  
                // rs数据下标从1开始  
            	jta.append(rs.getString(i + 1) + "\t");
                System.out.print(rs.getString(i + 1) + "\t");  
            }  
            jta.append("\r\n");
            System.out.println();
        }  
	}
	/**
	 * 添加新老师
	 * 
	 * @param id 工号
	 * @param pw 密码
	 * @param name 姓名
	 * @param age 年龄
	 * @param gender 性别
	 * @param dept 院系
	 * @param major 专业
	 * 
	 * @return true 添加成功; false 添加失败
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
	 * 添加新老师
	 * 
	 * @param id 学号
	 * @param pw 密码
	 * @param name 姓名
	 * @param age 年龄
	 * @param gender 性别
	 * @param dept 院系
	 * @param major 专业
	 * 
	 * @return true 添加成功; false 添加失败
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
	 * 获取一个学生可以选择的课程
	 * 
	 * @param id 待查询学生的学号
	 * 
	 * @return 可选课程的信息([课程代码] 课程名 学期 所属院系 教师姓名)
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
