package com.revature.gms.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidatorException;
import com.revature.gms.model.UserDetails;
import com.revature.gms.services.UserService;
import com.revature.gms.validator.UserValidator;
import com.revature.gms.model.StudentDetails;

public class UserLogin {

	Scanner sc = new Scanner(System.in);

	List<StudentDetails> list = new ArrayList<StudentDetails>();

	public void userLogin(String name, String pass) throws DBException, ValidatorException {

		UserService userservice = new UserService();

		UserDetails userDetails = userservice.userLoginService(name, pass);

		System.out.println("\nLogin Successfully..");
		System.out.println("\nAdmin Name :" + userDetails.getName());
		System.out.println("Role :" + userDetails.getRole());
		try {
			userFeature();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void userFeature() throws Exception {

		System.out.println("\n1. Check the Result");
		System.out.println("2. Update Marks Student Marks");
		System.out.println("3. List the student of the Specific grade");
		System.out.println("4. Subject wise rank holders");
		System.out.println("5. Logout");

		System.out.println("\nEnter your choice :");
		int ch = sc.nextInt();
		
		if(ch>0 && ch<6) {
		switch (ch) {

		case 1:
			System.out.println("\nEnter the Student Name :");
			String name1 = sc.next();
			System.out.println("Enter the Reg-No :");
			int regno1 = sc.nextInt();

			UserValidator uservalid1 = new UserValidator();
			uservalid1.studentValidation(name1, regno1);
			
			StudentDetails student = new StudentDetails();
			UserService userservice = new UserService();

			student = userservice.sLogin(name1, regno1);

			System.out.println("\nREGISTER NUMBER : " + student.getRegNo());
			System.out.println("Marks [ sub1 :" + student.getSub1() + "  , sub2 :" + student.getSub2() + "  , sub3 :"
					+ student.getSub3() + "  , sub4 :" + student.getSub4() + "  , sub5 :" + student.getSub5() + " ]");
			System.out.println("PERCENTAGE : " + student.getAvg());
			System.out.println("GRADE : " + student.getGrade());

			userFeature();
			break;

			
		case 2:
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

			UserValidator uservalid = new UserValidator();
			uservalid.studentValidation(name, regno);
			
			if ((regno > 1000 && regno < 1100) && (0 <= s1 && s1 <= 100) && (0 <= s2 && s2 <= 100) 
					&& (0 <= s3 && s3 <= 100) && (0 <= s4 && s4 <= 100) && (0 <= s5 && s5 <= 100)) {

				StudentDetails studentdetail = new StudentDetails();
				
				studentdetail.setStudName(name);
				studentdetail.setRegNo(regno);
				studentdetail.setSub1(s1);
				studentdetail.setSub2(s2);
				studentdetail.setSub3(s3);
				studentdetail.setSub4(s4);
				studentdetail.setSub5(s5);

				UserService userService = new UserService();

				userService.gradeCalc(studentdetail);

				userFeature();

			} else {
				throw new ValidatorException("Please ensure the given marks are in and arround boundaries(0 - 100)");
			}
			break;
		case 3:
			System.out.println("\nEnter a specific grade :");
			String grade=sc.next();
		
			UserValidator uservalid2 = new UserValidator();
			uservalid2.gradeCheck(grade.toUpperCase());
			
			UserService userserv = new UserService();
			List<StudentDetails> list  = userserv.findGradeService(grade.toUpperCase());
			
			System.out.println("\nGRADE    |  AVERAGE  |  REG-NO   |   STUDENT NAME");
			System.out.println("--------------------------------------------------");
			
			for (StudentDetails details : list) {
				System.out.println(details.getGrade()+"            "+details.getAvg()+"        "+details.getRegNo()+"        "+details.getStudName() );
			}
			System.out.println("--------------------------------------------------");
			userFeature();
			break;

		case 4:
			System.out.println("\nEnter the subject name :");
			String sub = sc.next();

			UserValidator uservalidator = new UserValidator();
			uservalidator.subjectWiseRankHolder(sub.toLowerCase());
			UserService userserv1 = new UserService();
			userserv1.findBySubService(sub.toLowerCase());

			userFeature();
			break;

		case 5:
			FirstPage.welcomePage();
			break;

		}
	}else {
		System.out.println("Please enter the valid choice (1 to 5)");
		userFeature();
	}
}
}
