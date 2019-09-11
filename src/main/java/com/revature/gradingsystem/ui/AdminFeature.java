package com.revature.gradingsystem.ui;

import java.util.Scanner;

import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.validator.GradeValidator;

public class AdminFeature {

	Scanner sc = new Scanner(System.in);

	GradeValidator gradeValidator = new GradeValidator();
	ScoreRange scorerange = new ScoreRange();
	AdminService adminservice = new AdminService();

	public void adminFeature(){

		boolean validInput = true;
		do {
			System.out.println("\n1. Define score range for the grading-calculation");
			System.out.println("2. View the score range");
			System.out.println("3. Delete the score range, for define the new Score-Range.");
			System.out.println("4. Logout");

			System.out.println("\nEnter your choice :");
			String str_ch = sc.next();
			int ch = 0;

			try {
				ch = Integer.parseInt(str_ch);
			} catch (Exception e) {
				System.out.println("Invalid choice, Please try again..");
				validInput = false;
			}

			switch (ch) {

			case 1:
				
				for(int i = 1; i <= 6; i++)
					setScoreRange();
				
				adminFeature();
				break;
				
			case 2:
				
				adminservice.viewScoreRangeService();
				adminFeature();
				break;
			case 3:
				
				adminservice.deleteScoreRangeService();
				adminFeature();
				break;
				
			case 4:
				FirstPage.welcomePage();
				break;
				
			default:
				System.out.println("Please enter the valid choice (1 to 4)");
				adminFeature();
				break;
			}
		} while (!validInput);
	}
	/** Get score range **/
	private void setScoreRange() {
		boolean validInput = true;
		do {
			System.out.println("\nEnter the Grade, Min_Mark and Max_Mark");
			String grade = sc.next();
			String stringmin = sc.next();
			String stringmax = sc.next();

			int min = 0;
			int max = 0;

			try {
				min = Integer.parseInt(stringmin);
				max = Integer.parseInt(stringmax);
				validInput = true;
			} catch (Exception e) {
				System.out.println("Invalid Marks.. Please try again");
				validInput = false;
			}

			try {
				//Validate Is Grade already exist
				gradeValidator.isGradeExist(grade.toUpperCase(), min, max);
				
				//Insert the score range
				adminservice.updateScoreRangeService(grade.toUpperCase(), min, max);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				validInput = false;
			}
		} while (!validInput);
	}

}
