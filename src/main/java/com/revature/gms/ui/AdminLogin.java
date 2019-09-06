package com.revature.gms.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.exception.ValidatorException;
import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;
import com.revature.gms.services.AdminService;

public class AdminLogin {

	Scanner sc = new Scanner(System.in);
	IAdminDAO admin = new AdminDAO();
	ScoreRangeDetails range = new ScoreRangeDetails();

	public void aLogin(String name, String pwd) throws DBException, ValidatorException, ServiceException {

		AdminService adminService = new AdminService();
		UserDetails admin = adminService.login(name, pwd);

		System.out.println("\nLogin Successfully..");
		System.out.println("Admin Name :" + admin.getName());
		System.out.println("Role :" + admin.getRole());
		
			try {
				feature();
			} catch (ServiceException e) {
				throw new ServiceException(e.getMessage());
			}
	}

	private void feature() throws DBException, ValidatorException, ServiceException  {

		System.out.println("1. List the students based on grade ( Top-bottom )");
		System.out.println("2. Define the score range for the grading-calculation");
		System.out.println("3. Logout");

		System.out.println("\nEnter your choice :");
		int ch = sc.nextInt();

		if (ch > 0 && ch < 4) {
			switch (ch) {

			case 1:

				List<StudentDetails> list = new ArrayList<StudentDetails>();
				list = admin.listOfStudents();

				for (StudentDetails details : list) {
					System.out.println(details);
				}
				break;

			case 2:

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

			case 3:
				
				try {
					FirstPage.welcomePage();
				} catch (Exception e) {
					
				}
				break;
			}
		} else {
			System.out.println("Please enter the valid choice (1 to 3)");
		}
	}

}
