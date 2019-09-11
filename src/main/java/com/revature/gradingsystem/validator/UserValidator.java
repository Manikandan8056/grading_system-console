package com.revature.gradingsystem.validator;

import com.revature.gradingsystem.exception.ValidatorException;

public class UserValidator {

	
	public void Login(String name, String password) throws ValidatorException {

		if (name == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Name");
		} else if (password == null || "".equals(name.trim())) {
			throw new ValidatorException("Invalid Password");
		}
	}

}

