package com.revature.gms.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ServiceException;
import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.dao.UserDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidationException;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;

public class UserService {
	
	public UserDetails userLoginService(String name, String pwd) throws ValidationException {
		UserDetails userdetail = null;
		IAdminDAO admindao=new AdminDAO();
		
		try {
			userdetail=admindao.findAdmin(name, pwd);
			if (userdetail == null) {
				throw new ValidationException("Enter the valid name and password");
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidationException("Enter the valid name and password");
		} 
		return userdetail;
		
	}

	public StudentDetails sLogin(String name, int regno) throws ServiceException {

		UserDAO userdao=new UserDAO();
		StudentDetails student=new StudentDetails();
		try {
			student=userdao.findByRegNo(name, regno);
		
			if(student != null) {
				System.out.println("\nStudent Name : "+student.getStudName());
				System.out.println("Reg-No : "+student.getRegNo());
			}
			} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("unable to login", e);
		}
		return student;
	}

	public List<StudentDetails> findGradeService(String grade) throws ValidationException {
		UserDAO userdao=new UserDAO();
		
		List<StudentDetails> list = null;
		try {
			list=userdao.findByGrade(grade);
			if (list == null || list.isEmpty() ) {
				throw new ValidationException("No records found for the given grade");
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidationException("Enter the valid grade");
		}

		return list;
	}

	public void findBySubService(String sub) throws ValidationException {

		UserDAO userdao=new UserDAO();
		System.out.println("\n"+sub+"      |   REG-NO   |   STUDENT NAME");
		System.out.println("----------------------------------------");
		
		List<StudentDetails> det=null;
		try {
			det = userdao.findBySubject(sub);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidationException("Enter the valid subject name");
		}

		for (StudentDetails student1 : det) {
			System.out.println(student1.getSubject()+"            "+student1.getRegNo()+"           "+student1.getStudName() );

		}
	}
}
