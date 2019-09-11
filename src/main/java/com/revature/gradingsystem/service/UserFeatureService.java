package com.revature.gradingsystem.service;

import java.util.List;

import com.revature.gradingsystem.dao.StudentGradeDAO;
import com.revature.gradingsystem.dao.StudentMarkDAO;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.dto.StudentMarkDTO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.StudentMark;

public class UserFeatureService {

	public void updateMarksAndGradeService(int regno, List<StudentMark> marks) {
		
		try {
			
			new StudentMarkDAO().insertMarks(marks);
			new StudentGradeDAO().insertGrade(regno, marks);
			
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<StudentGradeDTO> findByGradeService(String grade) {

		List<StudentGradeDTO> list = null;
		try {
			list = new StudentGradeDAO().findByGrade(grade);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public List<StudentMarkDTO> findBySubjectCodeService(String subCode) {

		List<StudentMarkDTO> list = null;
		 try {
			list = new StudentMarkDAO().findBySubjectCode(subCode);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public StudentGradeDTO getStudentResult(int regno) {

		StudentGradeDTO studentDetail =null;
		
		try {
			studentDetail =new StudentGradeDAO().findByRegNo(regno);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return studentDetail;
	}

	public List<StudentMark> getStudentMarks(int regno) {

		List<StudentMark> marks = null;
		
		try {
			marks = new StudentMarkDAO().getMarksByRegNo(regno);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return marks;
	}

	public List<StudentGradeDTO> listOfStudent() {
		
		List<StudentGradeDTO> list= null;
		try {
			list= new StudentGradeDAO().findStudentList();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

}
