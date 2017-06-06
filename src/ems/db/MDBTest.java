package ems.db;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MDBTest {
	public static MDB mdb = new MDB(DBHelper.DB_DRIVER, DBHelper.DB_TYPE, 
			DBHelper.DB_SERVER_ADD, DBHelper.DB_NAME, 
			DBHelper.DB_USERNAME, DBHelper.DB_PASSWORD);

	@Test
	public void testLogin() {
		assertEquals(0, mdb.login("admin", "admin"));
		assertEquals(1, mdb.login("T20140801", "123"));
		assertEquals(2, mdb.login("2014080101", "123"));
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
	
	@Test
	public void testGetStudentEnrolledCourses() {
		ArrayList<String> list = mdb.getStudentEnrolledCourses("2014080101");
		
		System.out.println("---------testGetStudentEnrolledCourses----------");
		for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
		System.out.println("------------------------------------------------");
	}
	
	@Test
	public void testGetStuAvailableCourse() {
		ArrayList<String> list = mdb.getStuAvailableCourse();
		
		System.out.println("--------------testGetStuAvailableCourse---------");
		for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
		System.out.println("------------------------------------------------");
	}
	
	
	@Test
	public void testGetStuExamInfo() {
		ArrayList<String> list = mdb.getStuExamInfo("2014080101");
		
		System.out.println("----------------getStuExamInfo------------------");
		for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
		System.out.println("------------------------------------------------");
	}
	
	
	@Test
	public void testGetStuScoreInfo() {
		ArrayList<String> list = mdb.getStuScoreInfo("2014080101");
		
		System.out.println("----------------getStuScoreInfo-----------------");
		for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
		System.out.println("------------------------------------------------");
	}
	
	@Test
	public void testAddNewCourse4Student() {
		System.out.println("Add 2014080101 [0803]");
		assertEquals(true, mdb.addNewCourse4Student("2014080101", "0803 asd 2017S CS 王五"));
	}
	
	@Test
	public void testGetTeacherClassInfo() {
		ArrayList<String> list = mdb.getTeacherClassInfo("T20140801");
		
		System.out.println("----------------getTeacherClassInfo-------------");
		for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
		System.out.println("------------------------------------------------");
	}
	
	@Test
	public void testDropStuCourse() {
		System.out.println("Drop 2014080101 2017S 0803");
		assertEquals(true, mdb.dropStuCourse("2014080101", "0803", "2017S"));
	}
	
	@Test
	public void testGetStuClassTimeAndPlace() {
		ArrayList<String> list = mdb.getStuClassTimeAndPlace("2014080101");
		
		System.out.println("--------------getStuClassTimeAndPlace-----------");
		for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }
		System.out.println("------------------------------------------------");
	}
}
