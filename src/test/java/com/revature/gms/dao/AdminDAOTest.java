package com.revature.gms.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;

public class AdminDAOTest {

	IAdminDAO admindao = new AdminDAO() {
		
		public UserDetails findAdmin(String name, String mobno) throws DBException {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	@Test
	public void testValidLogin() {

		String name = "Mani";
		String password = "pass123";
		admindao = new AdminDAO();
		UserDetails admin = null;
		try {
			admin = admindao.findAdmin(name, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(admin);
	}
	
	@Test
	public void testInValidLogin() {

		String name = "";
		String password = "";
		admindao = new AdminDAO();
		UserDetails admin = null;
		try {
			admin = admindao.findAdmin(name, password);
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNull(admin);
	}

	@Test
	public void testListofStudnet() {

		admindao = new AdminDAO();
		List<StudentDetails> list =null;
		try {
			list = admindao.listOfStudents();
		} catch (DBException e) {
			e.printStackTrace();
		}
		assertNotNull(list);
	}

	
	
}
