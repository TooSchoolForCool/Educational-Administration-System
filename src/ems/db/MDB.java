package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ems.user.Student;
import ems.user.User;

public class MDB {
	private final static String DBNAME = "MDB";
	private final static String URL = "jdbc:mysql://localhost:3306/"+"HNU_DB"+"?useSSL=false";
	private final static String DBUSER = "root";
	private final static String DBPASSWORD = "gundanba88";
	
	private static Connection conn;
	
	public MDB(){
		conn = GetDBCnt();
	}

	private Connection GetDBCnt(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
			if (!conn.isClosed()) {
                System.out.println("数据库连接成功!");
            }else{
            	System.out.println("数据库连接失败!");
            }
			return conn;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User Login(String userid,String password){
		
		Student user = new Student();
		user.setIdentity(User.IDEN_STUDENT);
		user.setstuID(userid);
		user.setPassword(password);
		user.setstuName("David");
		user.setstuSex("male");
		user.setstuClass("class a");
		user.setstuAddress("GreenStreet No.234");
		user.setMemo("???");
		user.setstuBirthday("19950505");
		user.setstuPhone("13723336666");
		user.setstuInDate("???");

		return user;
		
		
		
		
		
		
		
//		return null;
		/*
		try {
			Statement stmt = conn.createStatement();
			String sql = new String("select distinct * from User where username='"+username+"';");
			ResultSet rs = stmt.executeQuery(sql);
			if(rs==null){
				System.out.println("没有该用户！");
			}else{
				rs.next();
				if(rs.getString("password").equals(password)){
					System.out.println("登录成功!");
					return true;
				}else{
					System.out.println("密码错误！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		*/
	}
	
	
	
	
	
	
	
	
	
	
	
}
