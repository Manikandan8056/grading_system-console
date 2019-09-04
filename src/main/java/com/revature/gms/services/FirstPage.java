package com.revature.gms.services;

import java.util.Scanner;

public class FirstPage {
  
	static Scanner sc =new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		FirstPage.welcomePage();
	}
	
	public static void welcomePage() throws Exception {

		System.out.println("\nGRADING SYSTEM");
		
		System.out.println("\n1. Admin Login");
		System.out.println("2. User Login");
		System.out.println("3. Exit");
		System.out.println("\nEnter Your Choice :");
		int val=sc.nextInt();
		
		
			switch (val) {
			case 1:
				try {
					System.out.println("\nEnter your name :");
					String name=sc.next();
					System.out.println("Enter the Password :");
					String pwd=sc.next();
					
					IAdminLogin admin=new AdminLogin();
					admin.aLogin(name, pwd);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case 2:
				System.out.println("\nEnter the User Name :");
				String name=sc.next();
				System.out.println("Enter the password :");
				String pass=sc.next();
				
				IUserLogin stud=new UserLogin();
				stud.userLogin(name, pass);
					break;
			case 3:
				System.out.println("\nTHANK YOU FOR USING OUR SERVICES.");
				break;
			}
	}

}
