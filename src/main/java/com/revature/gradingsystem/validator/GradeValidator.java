package com.revature.gradingsystem.validator;

import com.revature.gradingsystem.dao.StudentGradeDAO;
import com.revature.gradingsystem.dao.ValidatorDao;
import com.revature.gradingsystem.dao.ValidatorDaoImpl;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ValidatorException;
import com.revature.gradingsystem.model.ScoreRange;

public class GradeValidator {
	
	ValidatorDao validatordao= new ValidatorDaoImpl();

public void isGradeExist(String grade, int min, int max) throws ValidatorException {
		
		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) 
			throw new ValidatorException("Invalid grade, Please try again");
		
		
		try {
			ScoreRange scorerange = validatordao.findGrade(grade);
			if(scorerange != null)
				throw new ValidatorException("Grade already Exist, Please try another.");
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		
		
		if( min > 100 || min < 0 || max > 100 || max < 0)
			throw new ValidatorException("Out of boundaries, Please enter the valid Range.");
		else if(min > max)
			throw new ValidatorException("Min_Rang is Greater than Max_Range, Please enter the valid Range.");
		
		
		try {
			ScoreRange scorerange1 = validatordao.findRange(min);
			if(scorerange1 != null)
				throw new ValidatorException("Min_mark already updated, Please try another.");
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	
		
		try {
			ScoreRange scorerange2 = validatordao.findRange(min);
			if(scorerange2 != null)
				throw new ValidatorException("Max_mark already updated, Please try another.");
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}

	public void gradeCheck(String grade) throws ValidatorException {

		if (grade == null || "".equals(grade.trim()) || grade.length() != 1) 
			throw new ValidatorException("Invalid grade, Please try again");
		
		StudentGradeDAO studentGrade = new StudentGradeDAO();
		try {
			String isGradeExist = studentGrade.isGradeExist(grade);
			
			if(isGradeExist == "")
				throw new ValidatorException("This grade is out of Range");
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
