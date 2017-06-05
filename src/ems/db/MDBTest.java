package ems.db;

import static org.junit.Assert.*;

import org.junit.Test;

import ems.db.DBHelper;
import ems.db.MDB;

public class MDBTest {
	public static MDB mdb = new MDB(DBHelper.DB_DRIVER, DBHelper.DB_TYPE, 
			DBHelper.DB_SERVER_ADD, DBHelper.DB_NAME, 
			DBHelper.DB_USERNAME, DBHelper.DB_PASSWORD);

	@Test
	public void testLogin() {
		assertEquals(0, mdb.login("admin", "admin"));
		assertEquals(1, mdb.login("teacher", "teacher"));
		assertEquals(2, mdb.login("student", "student"));
	}
	
	@Test
	public void testGenerateInsertSQL() {
		mdb.generateInsertSQL("Teachers", 5);
	}
	
	@Test
	public void testAddNewTeacher() {
		assertEquals(true, mdb.addNewTeacher("T20170001", "123456", "Zeyu", "22", "男", "信息院", "CS"));
	}
	
	@Test
	public void testAddNewStudent() {
		assertEquals(true, mdb.addNewStudent("20170001", "123456", "Zeyu", "22", "男", "信息院", "CS"));
	}
	
}
