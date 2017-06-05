package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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
                System.out.println("数据库连接成功!");
            }else{
            	System.out.println("数据库连接失败!");
            }
			
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断登录用户是否合法
	 * 
	 * @param username 登录用户名
	 * @param password 登录密码
	 * 
	 * @return 用户类型：管理员(0), 教师(1), 学生(2), 密码错误/用户不存在(-1)
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
	
	/**
	 * 添加新老师
	 * 
	 * @param 
	 * @param 
	 * 
	 * @return 
	 */
	public boolean addNewTeacher(String id, String pw, String name, 
			String age, String gender, String Dept, String major)
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
	        preStmt.setString(5, Dept);
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
	
	public boolean addNewStudent(String id, String pw, String name, 
			String age, String gender, String Dept, String major)
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
	        preStmt.setString(5, Dept);
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
	
	private Connection getConnection() throws SQLException{
		try{
			Class.forName(DB_driver_);
			String url = "jdbc:" + DB_type_ + "://" + DB_server_addr_ + "/" + DB_name_ + "?useSSL=false";
			return DriverManager.getConnection(url, DB_username_, DB_password_);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}












