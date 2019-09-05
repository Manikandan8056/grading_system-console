package com.revature.gms.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidationException;
import com.revature.gms.model.UserDetails;
import com.revature.gms.services.UserService;
import com.revature.gms.model.StudentDetails;

public class UserLogin {

	Scanner sc = new Scanner(System.in);

	List<StudentDetails> list = new ArrayList<StudentDetails>();

	public void userLogin(String name, String pass) throws DBException, ValidationException {

		UserService userservice = new UserService();

		UserDetails userDetails = userservice.userLoginService(name, pass);

		System.out.println("\nLogin Successfully..");
		System.out.println("\nAdmin Name :" + userDetails.getName());
		System.out.println("Role :" + userDetails.getRole());
		try {
			studentFeature();
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
		int ch = sc.nextInt();
		switch (ch) {

		case 1:
			System.out.println("\nEnter the Student Name :");
			String name = sc.next();
			System.out.println("Enter the Reg-No :");
			int regno = sc.nextInt();

			StudentDetails student = new StudentDetails();
			UserService userservice = new UserService();

			student = userservice.sLogin(name, regno);

			System.out.println("\nREGISTER NUMBER : " + student.getRegNo());
			System.out.println("Marks [ sub1 :" + student.getSub1() + "  , sub2 :" + student.getSub2() + "  , sub3 :"
					+ student.getSub3() + "  , sub4 :" + student.getSub4() + "  , sub5 :" + student.getSub5() + " ]");
			System.out.println("PERCENTAGE : " + student.getAvg());
			System.out.println("GRADE : " + student.getGrade());

			studentFeature();
			break;

		case 2:
			System.out.println("\nEnter a specific grade :");
			String grade = sc.next();

			UserService userserv = new UserService();
			List<StudentDetails> list  = userserv.findGradeService(grade);
			for (StudentDetails details : list) {
				System.out.println(details.toString1());
			}

			studentFeature();
			break;

		case 3:
			System.out.println("\nEnter the subject name :");
			String sub = sc.next();

			UserService userserv1 = new UserService();
			userserv1.findBySubService(sub);

			studentFeature();
			break;

		case 4:
			FirstPage.welcomePage();
			break;

		}
	}

}
