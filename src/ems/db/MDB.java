package ems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	/**
	 * ѧ�����
	 * 
	 * @param Sid
	 *            ѧ��ѧ��
	 * @return ����String ArrayList��ν��
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
