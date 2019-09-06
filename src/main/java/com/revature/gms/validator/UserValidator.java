package com.revature.gms.validator;

import com.revature.gms.exception.ValidatorException;
import com.revature.gms.ui.UserLogin;

public class UserValidator {

	public void Login(String name, String password) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		} else if (password == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		}
	}

	public void gradeCheck(String grade) throws ValidatorException {

		UserLogin userlogin = new UserLogin();
		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) {
			System.out.println("Invalid grade, Enter the valid one (A,B,C,D,E,F)");
			try {
				userlogin.userFeature();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void subjectWiseRankHolder(String subject) throws ValidatorException {

		UserLogin userlogin = new UserLogin();
		if (subject == null || "".equals(subject.trim())) {
			System.out.println("Invalid Subject Name");
			try {
				userlogin.userFeature();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void studentValidation(String name, int regno) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		} else if (regno > 1100 || regno < 0 ) {
			throw new ValidatorException("Invalid Register Number");
		}

	}

}