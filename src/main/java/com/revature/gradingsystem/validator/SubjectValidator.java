package com.revature.gradingsystem.validator;

import java.util.List;

import com.revature.gradingsystem.dao.SubjectDAO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.Subject;

public class SubjectValidator {

	public void subjectWiseRankHolder(String subCode) throws ValidatorException {

		if (subCode == null || "".equals(subCode.trim()) || subCode.length() != 5)
			throw new ValidatorException("Invalid Subject Code");
		
		Subject subjectCode = null;
		try {
			subjectCode = new SubjectDAO().findBySubject(subCode);
			if(subjectCode == null)
				throw new ValidatorException("This Subject code Doesn't exist.");
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	public List<Subject> findAllSubject() {

		List<Subject> subjects = null;
		try {
			subjects = new SubjectDAO().findAll();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return subjects;
	}
	
}
