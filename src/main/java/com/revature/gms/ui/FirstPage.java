package com.revature.gms.ui;

import java.util.Scanner;

import com.revature.gms.validator.UserValidator;

public class FirstPage {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		FirstPage.welcomePage();
	}

	public static void welcomePage() throws Exception {

		System.out.println("\nGRADING SYSTEM");

		System.out.println("\n1. Admin Login");
		System.out.println("2. User Login");
		System.out.println("3. Exit");
		System.out.println("\nEnter Your Choice :");
		int val = sc.nextInt();

		switch (val) {
		case 1:
			
				System.out.println("\nEnter your name :");
				String name = sc.next();
				System.out.println("Enter the Password :");
				String pwd = sc.next();

				UserValidator uservalidator= new UserValidator();
				uservalidator.Login(name, pwd);
				
				AdminLogin admin = new AdminLogin();
				admin.aLogin(name, pwd);
			
			break;
		case 2:
			System.out.println("\nEnter the User Name :");
			String name1 = sc.next();
			System.out.println("Enter the password :");
			String pass = sc.next();
			
			UserValidator uservalidator1= new UserValidator();
			uservalidator1.Login(name1, pass);
			
			UserLogin stud = new UserLogin();
			stud.userLogin(name1, pass);
			
			break;
		case 3:
			System.out.println("\nTHANK YOU FOR USING OUR SERVICES.");
			break;
		}
	}

}
