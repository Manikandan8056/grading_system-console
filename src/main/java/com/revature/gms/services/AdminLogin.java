package com.revature.gms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidationException;
import com.revature.gms.model.AdminDetails;
import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.model.StudentDetails;

public class AdminLogin implements IAdminLogin {
	
	Scanner sc=new Scanner(System.in);
	AdminDAO admin =new AdminDAO();
	ScoreRangeDetails range=new ScoreRangeDetails();

	public void aLogin(String name, String pwd) throws DBException, ValidationException {

		IAdminDAO admindao=new AdminDAO();
		AdminDetails admin=new AdminDetails();
		
		admin=admindao.findAdmin(name, pwd);
		
		try {
			if(admin != null) {
				
				System.out.println("Admin Name :"+admin.getName());
				System.out.println("Role :"+admin.getRole());
				feature();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void feature() throws Exception {

		System.out.println("1. List the students based on Top-Low grade");
		System.out.println("2. Define the score range for the grading-calculation");
		System.out.println("3. Logout");
		
		System.out.println("\nEnter your choice :");
		int ch=sc.nextInt();
		
		switch(ch) {
		
		case 1:
			System.out.println("Enter the Student name :");
			String name=sc.next();
			System.out.println("Enter the Student Reg-No :");
			int regno=sc.nextInt();
			System.out.println("Enter the Sub:1 Mark :");
			int s1=sc.nextInt();
			System.out.println("Enter the Sub:2 Mark :");
			int s2=sc.nextInt();
			System.out.println("Enter the Sub:3 Mark :");
			int s3=sc.nextInt();
			System.out.println("Enter the Sub:4 Mark :");
			int s4=sc.nextInt();
			System.out.println("Enter the Sub:5 Mark :");
			int s5=sc.nextInt();
	
			if((s1<= 100 && s1>=0) && (s2<= 100 && s2>=0) && (s3<= 100 && s3>=0) && (s4<= 100 && s4>=0) && (s5 <= 100 && s5>=0) ) {
				
			int total= s1+s2+s3+s4+s5;
			int avg=total/5;
			String grade;
			
			if(avg>=90)
				grade = range.getGrade90();
			else if(avg>=80 && avg<90)
				grade = range.getGrade80();
			else if(avg>=70 && avg<80)
				grade = range.getGrade70();
			else if(avg>=60 && avg<70)
				grade = range.getGrade60();
			else if(avg>=50 && avg<60)
				grade = range.getGrade50();
			else
				grade = range.getGrade0();
			
			admin.updateMarks(name, regno, s1, s2, s3, s4, s5, total, avg, grade);
			
			feature();
			
			}else {
				throw new ValidationException("Please ensure the given marks are in and arround boundaries(0 - 100)");
			}
			break;
			
		case 2:
			
			List<StudentDetails> list=new ArrayList<StudentDetails>();
			list=admin.listOfStudents();
	
			for (StudentDetails details : list) {
				System.out.println(details);
			}
			break;
			
		case 3:
			System.out.println("\nEnter the Grade for range(90-100) :");
			String grade90=sc.next();
			System.out.println("Enter the Grade for range(80-90) :");
			String grade80=sc.next();
			System.out.println("Enter the Grade for range(70-80) :");
			String grade70=sc.next();
			System.out.println("Enter the Grade for range(60-70) :");
			String grade60=sc.next();
			System.out.println("Enter the Grade for range(50-60) :");
			String grade50=sc.next();
			System.out.println("Enter the Grade for range(0-50) :");
			String grade0=sc.next();
			
			range.setGrade90(grade90);
			range.setGrade80(grade80);
			range.setGrade70(grade70);
			range.setGrade60(grade60);
			range.setGrade50(grade50);
			range.setGrade0(grade0);
			
			System.out.println("Score range Updated.");
			feature();
			break;
			
		case 4:
			FirstPage.welcomePage();
			break;
		}
	}

}
