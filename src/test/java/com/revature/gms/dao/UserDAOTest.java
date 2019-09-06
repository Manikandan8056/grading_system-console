package com.revature.gms.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;

public class UserDAOTest {

	@Test
	public void testValidFindByRegNo() {

		String name = "Karthik";
		int regno = 1003;
		IUserDAO userdao = new UserDAO();
		StudentDetails student = null;
		try {
			student = userdao.findByRegNo(name, regno);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(student);
	}

	@Test
	public void testInvalidFindByRegNo() {

		String name = "";
		int regno = 0;

		IUserDAO userdao = new UserDAO();
		StudentDetails student = null;
		try {
			student = userdao.findByRegNo(name, regno);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNull(student);
	}
	
	@Test
	public void testValidFindByGrade() throws SQLException {

		String grade="A";
		IUserDAO userdao = new UserDAO();
		List<StudentDetails> list =null;
		try {
			list = userdao.findByGrade(grade);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}
	
	@Test
	public void testValidFindBySubject() throws SQLException {

		String subject="sub1";
		IUserDAO userdao = new UserDAO();
		List<StudentDetails> list =null;
		try {
			list = userdao.findBySubject(subject);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}
	
}
