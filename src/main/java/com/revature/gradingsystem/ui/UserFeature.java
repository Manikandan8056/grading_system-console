package com.revature.gradingsystem.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.gradingsystem.dao.SubjectDAO;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.dto.StudentMarkDTO;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.validator.StudentValidator;
import com.revature.gradingsystem.validator.SubjectValidator;
import com.revature.gradingsystem.validator.GradeValidator;
import com.revature.gradingsystem.model.StudentDetail;
import com.revature.gradingsystem.model.StudentMark;
import com.revature.gradingsystem.model.Subject;
import com.revature.gradingsystem.service.AdminService;
import com.revature.gradingsystem.service.UserFeatureService;

public class UserFeature {

	Scanner sc = new Scanner(System.in);

	List<StudentDetail> list = new ArrayList<StudentDetail>();
	UserFeatureService userFeatureService = new UserFeatureService();

	public void userFeature() {

		boolean validInput = true;
		do {
			System.out.println("\n1. Check the Result");
			System.out.println("2. Update Student Marks");
			System.out.println("3. List the students based on grade ( Top-bottom ) ");
			System.out.println("4. List the students of the Specific grade");
			System.out.println("5. Subject wise rank holders");
			System.out.println("6. View Score-Range");
			System.out.println("7. Logout");

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

				boolean validInput0 = false;
				do {
					System.out.println("\nEnter the Student Reg-No :");
					String reg = sc.next();

					int regno = 0;
					try {
						regno = new StudentValidator().ischangeInteger(reg);

						// Reg-No validator
						StudentValidator studentValidate = new StudentValidator();
						studentValidate.isRegnoExistService(regno);
						
						//get the StudentName, Average, Grade
						StudentGradeDTO studentResult = userFeatureService.getStudentResult(regno);

						System.out.println("\nStudent Name : " + studentResult.getStudentName());
						System.out.println("Reg-No : " + studentResult.getRegNo());

						//get the Marks and Sub-Code
						List<StudentMark> marks = userFeatureService.getStudentMarks(regno);

						for (StudentMark studentMark : marks) {
							System.out.println(studentMark.getSubjectName() + " : " + studentMark.getMark());
						}
						System.out.println("OverAll Percentage : " + studentResult.getAvg() + "%");
						System.out.println("Grade : " + studentResult.getGrade());

						validInput0 = true;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						validInput0 = true;
					}
				} while (validInput0);

				userFeature();
				break;

			case 2:
				boolean validInput1 = false;
				do {
					System.out.println("Enter the Student Reg-No :");
					String reg = sc.next();

					int regno = 0;
					try {
						regno = new StudentValidator().ischangeInteger(reg);
						
						// Reg-No validator
						StudentValidator studentValidate = new StudentValidator();
						studentValidate.isRegnoExistService(regno);

						List<StudentMark> marks = new ArrayList<StudentMark>();

						List<Subject> subjects = new SubjectDAO().findAll();
						for (Subject subject : subjects) {

							StudentMark sm = new StudentMark();
							sm.setSubject(subject);
							System.out.println("Enter the " + subject.getName() + " Mark :");
							int mark = sc.nextInt();
							sm.setMark(mark);
							StudentDetail studentDetail = new StudentDetail();
							studentDetail.setRegNo(regno);
							sm.setStudentDetail(studentDetail);

							marks.add(sm);
							validInput1 = false;
						}

						// call Service class
						userFeatureService.updateMarksAndGradeService(regno, marks);

					} catch (Exception e) {
						System.out.println(e.getMessage());
						validInput1 = true;
					}
				} while (validInput1);

				userFeature();
				break;

			case 3:
				
				List<StudentGradeDTO> list = userFeatureService.listOfStudent();
				
				System.out.println("\nGRADE    |  AVERAGE  |  REG-NO   |   STUDENT NAME");
				System.out.println("--------------------------------------------------");
				for (StudentGradeDTO studentDTO : list) {
					System.out.println(studentDTO.getGrade()+"       "+studentDTO.getAvg()+"        "+studentDTO.getRegNo()+"       "+studentDTO.getStudentName());
				}
				System.out.println("--------------------------------------------------");
				break;
				
			case 4:

				boolean validInput2 = false;
				do {
					System.out.println("\nEnter a specific grade :");
					String grade = sc.next();
					List<StudentGradeDTO> gradeList = null;
					try {
						// grade Validation
						GradeValidator gradeValidator = new GradeValidator();
						gradeValidator.gradeCheck(grade.toUpperCase());

						// call Service class and get the result
						gradeList = userFeatureService.findByGradeService(grade.toUpperCase());

						System.out.println("\nGRADE    |  AVERAGE  |  REG-NO   |   STUDENT NAME");
						System.out.println("--------------------------------------------------");

						for (StudentGradeDTO details : gradeList) {
							System.out.println(details.getGrade() + "            " + details.getAvg() + "        "
									+ details.getRegNo() + "        " + details.getStudentName());
						}

						System.out.println("--------------------------------------------------");
						validInput2 = false;
					} catch (ValidatorException e) {
						System.out.println(e.getMessage());
						validInput2 = true;
					}
				} while (validInput2);

				userFeature();
				break;

			case 5:

				try {
					List<Subject> subj = new SubjectValidator().findAllSubject();
					System.out.println("--------------------------------------------------------");

					for (Subject subject : subj) {
						System.out.println(
								"Subject Code : " + subject.getCode() + "     Subject Name : " + subject.getName());
					}
					System.out.println("--------------------------------------------------------");

					boolean validInput3 = false;
					do {
						System.out.println("\nEnter the subject code :");
						String sub = sc.next();

						List<StudentMarkDTO> studentMarks = null;
						try {
							// subject_code Validator
							SubjectValidator subValidator = new SubjectValidator();
							subValidator.subjectWiseRankHolder(sub.toUpperCase());

							// call Service class
							studentMarks = userFeatureService.findBySubjectCodeService(sub.toUpperCase());

						System.out.println(sub + "       |   OVERALL-GRADE  |   REG-NO   |   STUDENT NAME");
						System.out.println("-------------------------------------------------------------");
						for (StudentMarkDTO sm : studentMarks) {
							System.out.println(sm.getMark() + "              " + sm.getGrade() + "             "
									+ sm.getRegNo() + "        " + sm.getStudentName());
						}
						System.out.println("-------------------------------------------------------------");
						
						validInput3 = false;
						} catch (Exception e) {
							System.out.println(e.getMessage());
							validInput3 = true;
						}

					} while (validInput3);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				userFeature();
				break;

			case 6:

				AdminService adminservice = new AdminService();
				adminservice.viewScoreRangeService();
				userFeature();
				break;

			case 7:

				FirstPage.welcomePage();
				break;

			default:

				System.out.println("Please enter the valid choice (1 to 6)");
				userFeature();
				break;
			}
		} while (!validInput);
	}
}
