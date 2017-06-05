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
}
