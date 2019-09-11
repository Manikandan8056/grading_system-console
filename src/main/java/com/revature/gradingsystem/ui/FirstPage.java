package com.revature.gradingsystem.ui;

import java.util.Scanner;

import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserService;
import com.revature.gradingsystem.ui.FirstPage;
import com.revature.gradingsystem.validator.UserValidator;

public class FirstPage {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		FirstPage.welcomePage();
	}

	public static void welcomePage() {

		boolean validInput = true;
		do {
			System.out.println("\n************GRADING SYSTEM APPLICATION*************");

			System.out.println("\n1. Admin Login");
			System.out.println("2. User Login");
			System.out.println("3. Exit");
			System.out.println("\nEnter Your Choice :");

			int val = 0;
			String ch = sc.next();

			try {
				val = Integer.parseInt(ch);
			} catch (Exception e) {
				System.out.println("Invalid choice, Please try again..");
				validInput = false;
			}

			switch (val) {
			case 1:

				boolean askAgain = false;
				do {
					System.out.println("\nEnter your name :");
					String name = sc.next();
					System.out.println("Enter the Password :");
					String pwd = sc.next();

					UserValidator uservalidator = new UserValidator();
					AdminService adminService = new AdminService();
					UserDetails userdetail = new UserDetails();
					try {
						uservalidator.Login(name, pwd);
						userdetail = adminService.adminLogin(name, pwd);

						System.out.println("\nLogin Successfully..");
						System.out.println("\nAdmin Name :" + userdetail.getName());
						System.out.println("Role :" + userdetail.getRole());

						AdminFeature adminfeat = new AdminFeature();
						adminfeat.adminFeature();
						askAgain = false;
						break;
					} catch (ServiceException e) {
						System.out.println(e.getMessage());
						askAgain = true;
					} catch (ValidatorException e) {
						System.out.println(e.getMessage());
						askAgain = true;
					} 
				} while (askAgain);

			case 2:

				boolean askAgain1 = false;
				do {
					System.out.println("\nEnter the User Name :");
					String name1 = sc.next();
					System.out.println("Enter the password :");
					String pass = sc.next();

					UserValidator uservalidator1 = new UserValidator();
					UserService userservice = new UserService();
					UserDetails userdetail1 = null;
					try {
						uservalidator1.Login(name1, pass);
						
						userdetail1 = userservice.userLogin(name1, pass);
						
						System.out.println("\nLogin Successfully..");
						System.out.println("\nUser Name :" + userdetail1.getName());
						System.out.println("Role :" + userdetail1.getRole());

						UserFeature userfeat = new UserFeature();
						userfeat.userFeature();
						askAgain1 = false;
						break;
					} catch (ServiceException e) {
						System.out.println(e.getMessage());
						askAgain1 = true;
					} catch (ValidatorException e) {
						System.out.println(e.getMessage());
						askAgain1 = true;
					}
					

				} while (askAgain1);

				break;
			case 3:
				System.out.println("\nTHANK YOU FOR USING OUR SERVICES.");
				break;
			default:
				System.out.println("Please enter the valid choice (1 to 3)");
				welcomePage();
				break;
			}

		} while (!validInput);
	}

}
