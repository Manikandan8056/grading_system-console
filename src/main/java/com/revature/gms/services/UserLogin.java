package com.revature.gms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.protobuf.ServiceException;
import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.dao.IUserDAO;
import com.revature.gms.dao.UserDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.AdminDetails;
import com.revature.gms.model.StudentDetails;

public class UserLogin implements IUserLogin {

	Scanner sc=new Scanner(System.in);
	IUserDAO userdao=new UserDAO();
	List<StudentDetails> list=new ArrayList<StudentDetails>();
	
	public StudentDetails sLogin(String name, int regno) throws ServiceException {

		IUserDAO userdao=new UserDAO();
		StudentDetails student=new StudentDetails();
		try {
			student=userdao.findByStudent(name, regno);
		
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
	
	public void userLogin(String name, String pass) throws DBException {
		
		
		AdminDetails admin=new AdminDetails();
		
		IAdminDAO admindao=new AdminDAO();
		admin=admindao.findAdmin(name, pass);
		
		try {
			if(admin != null) {
				System.out.println("\nLogin Successfully..");
				System.out.println("\nAdmin Name :"+admin.getName());
				System.out.println("Role :"+admin.getRole());
				studentFeature();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void studentFeature() throws Exception {

		System.out.println("\n1. Check the Result");
		System.out.println("2. List the student of the Specific grade");
		System.out.println("3. Subject wise rank holders");
		System.out.println("4. Logout");
		
		System.out.println("\nEnter your choice :");
		int ch=sc.nextInt();
		switch(ch) {
		
		case 1:
			System.out.println("\nEnter the Student Name :");
			String name=sc.next();
			System.out.println("Enter the Reg-No :");
			int regno=sc.nextInt();
			
			StudentDetails student=new StudentDetails();
			student=sLogin(name, regno);
			
			System.out.println("\nREGISTER NUMBER : "+student.getRegNo());
			System.out.println("Marks [ sub1 :"+student.getSub1()+"  , sub2 :"+student.getSub2()+"  , sub3 :"+student.getSub3()
										+"  , sub4 :"+student.getSub4()+"  , sub5 :"+student.getSub5()+" ]");
			System.out.println("PERCENTAGE : "+student.getAvg());
			System.out.println("GRADE : "+student.getGrade());
			
			studentFeature();
			break;
			
		case 2:
			System.out.println("\nEnter a specific grade :");
			String grade=sc.next();
			
			list=userdao.findByGrade(grade);
	
			for (StudentDetails details : list) {
				System.out.println(details.toString1());
			}
			studentFeature();
			break;
			
		case 3:
			System.out.println("\nEnter the subject name :");
			String sub=sc.next();

			System.out.println("\n");
			
			System.out.println(sub+"      |   REG-NO   |   STUDENT NAME");
			System.out.println("----------------------------------------");
			userdao.findBySubject(sub);

			studentFeature();
			break;
			
		case 4:
			FirstPage.welcomePage();
			break;
			
		}
	}

}
