package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	/**
	 * 学生查课
	 * 
	 * @param Sid
	 *            学生学号
	 * @return 返回String ArrayList查课结果
	 */
	public ArrayList<String> SelectStudentCourse(String Sid) {
		ArrayList<String> arraylist = new ArrayList<String>();
		try {

			Connection conn = getConnection();

			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(
					"select Cname,courses.Cid,courses.Term,Cdepart,Tid from courses,sc where sc.cid=courses.cid;");

			while (res.next()) {
				// Retrieve by column name
				String cname = res.getString("Cname");
				String cid = res.getString("Cid");
				String term = res.getString("Term");
				String cdepart = res.getString("Cdepart");
				String tid = res.getString("Tid");

				String result = cid + " " + cname + " " + term + " " + cdepart + " " + tid;

				arraylist.add(result);
				// // Display values
				// System.out.print("Cname: " + cname);
				// System.out.print(", Cid: " + cid);
				// System.out.print(", Term: " + term);
				// System.out.print(", Cdepart: " + cdepart);
				// System.out.println(", Tid: " + tid);
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
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:" + DB_type_ + "://" + DB_server_addr_ + "/" + DB_name_ + "?characterEncoding=utf-8";
			return DriverManager.getConnection(url, DB_username_, DB_password_);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
