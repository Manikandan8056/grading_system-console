package com.revature.gms.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.dao.AdminDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidationException;
import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;
import com.revature.gms.services.AdminService;

public class AdminLogin {

	Scanner sc = new Scanner(System.in);
	AdminDAO admin = new AdminDAO();
	ScoreRangeDetails range = new ScoreRangeDetails();

	public void aLogin(String name, String pwd) throws DBException, ValidationException {

		AdminService adminService = new AdminService();
		UserDetails admin = adminService.login(name, pwd);
		
		System.out.println("\nLogin Successfully..");
		System.out.println("Admin Name :" + admin.getName());
		System.out.println("Role :" + admin.getRole());
		
		try {
			feature();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void feature() throws Exception {

		System.out.println("1. List the students based on Top-Low grade");
		System.out.println("2. Define the score range for the grading-calculation");
		System.out.println("3. Logout");

		System.out.println("\nEnter your choice :");
		int ch = sc.nextInt();

		switch (ch) {

		case 1:
			System.out.println("Enter the Student name :");
			String name = sc.next();
			System.out.println("Enter the Student Reg-No :");
			int regno = sc.nextInt();
			System.out.println("Enter the Sub:1 Mark :");
			int s1 = sc.nextInt();
			System.out.println("Enter the Sub:2 Mark :");
			int s2 = sc.nextInt();
			System.out.println("Enter the Sub:3 Mark :");
			int s3 = sc.nextInt();
			System.out.println("Enter the Sub:4 Mark :");
			int s4 = sc.nextInt();
			System.out.println("Enter the Sub:5 Mark :");
			int s5 = sc.nextInt();
			
			StudentDetails studentdetail= new StudentDetails();
			if ((0 <= studentdetail.getSub1() && studentdetail.getSub1() <= 100) && (0 <= studentdetail.getSub2() && studentdetail.getSub2() <= 100) 
					&& (0 <= studentdetail.getSub3() && studentdetail.getSub3() <= 100) && (0 <= studentdetail.getSub4() && studentdetail.getSub4() <= 100)
					&& (0 <= studentdetail.getSub5() && studentdetail.getSub5() <= 100)) {
			
			studentdetail.setStudName(name);
			studentdetail.setRegNo(regno);
			studentdetail.setSub1(s1);
			studentdetail.setSub2(s2);
			studentdetail.setSub3(s3);
			studentdetail.setSub4(s4);
			studentdetail.setSub5(s5);
			
			AdminService adminService = new AdminService();
			
			adminService.gradeCalc(studentdetail);
				
			feature();

			} else {
				throw new ValidationException("Please ensure the given marks are in and arround boundaries(0 - 100)");
			}
			break;

		case 2:

			List<StudentDetails> list = new ArrayList<StudentDetails>();
			list = admin.listOfStudents();

			for (StudentDetails details : list) {
				System.out.println(details);
			}
			break;

		case 3:
			
			System.out.println("\nEnter the Grade for range(90-100) :");
			String grade90 = sc.next();
			System.out.println("Enter the Grade for range(80-90) :");
			String grade80 = sc.next();
			System.out.println("Enter the Grade for range(70-80) :");
			String grade70 = sc.next();
			System.out.println("Enter the Grade for range(60-70) :");
			String grade60 = sc.next();
			System.out.println("Enter the Grade for range(50-60) :");
			String grade50 = sc.next();
			System.out.println("Enter the Grade for range(0-50) :");
			String grade0 = sc.next();

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
