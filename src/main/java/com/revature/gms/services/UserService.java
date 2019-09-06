package com.revature.gms.services;

import java.sql.SQLException;
import java.util.List;

import com.google.protobuf.ServiceException;
import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.dao.IUserDAO;
import com.revature.gms.dao.UserDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidatorException;
import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;

public class UserService {
	
	public UserDetails userLoginService(String name, String pwd) throws ValidatorException {
		UserDetails userdetail = null;
		IAdminDAO admindao=new AdminDAO();
		
		try {
			userdetail=admindao.findAdmin(name, pwd);
			if (userdetail == null) {
				throw new ValidatorException("Enter the valid name and password");
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidatorException("Enter the valid name and password");
		} 
		return userdetail;
		
	}

	public StudentDetails sLogin(String name, int regno) throws ServiceException {

		UserDAO userdao=new UserDAO();
		StudentDetails student=new StudentDetails();
		try {
			student=userdao.findByRegNo(name, regno);
		
			if(student != null) {
				System.out.println("\nStudent Name : "+student.getStudName());
				System.out.println("Reg-No : "+student.getRegNo());
			}
			} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("unable to login", e);
		}
		return student;
	}
	
public void gradeCalc(StudentDetails studentdetail) throws ServiceException {
		
		ScoreRangeDetails range = new ScoreRangeDetails();
		
			int total = studentdetail.getSub1() + studentdetail.getSub2() + studentdetail.getSub3() + studentdetail.getSub4() + studentdetail.getSub5();
			float avg = total / 5;
			String grade = getGrade(range, avg);
			
			studentdetail.setTotal(total);
			studentdetail.setAvg(avg);
			studentdetail.setGrade(grade);
			
			IUserDAO userdao=new UserDAO();
			
			try {
				userdao.updateMarks(studentdetail);
			} catch (DBException e) {
				throw new ServiceException(e.getMessage());
			}
	}

	public String getGrade(ScoreRangeDetails range, float avg) {
		String grade= null;
		
		if (avg >= 90)
			grade = range.getGrade90();
		else if (avg >= 80 && avg < 90)
			grade = range.getGrade80();
		else if (avg >= 70 && avg < 80)
			grade = range.getGrade70();
		else if (avg >= 60 && avg < 70)
			grade = range.getGrade60();
		else if (avg >= 50 && avg < 60)
			grade = range.getGrade50();
		else if (avg >= 0 && avg < 50)
			grade = range.getGrade0();
		
		return grade;
	}

	public List<StudentDetails> findGradeService(String grade) throws ValidatorException {
		UserDAO userdao=new UserDAO();
		
		List<StudentDetails> list = null;
		try {
			list=userdao.findByGrade(grade);
			if (list == null || list.isEmpty() ) {
				throw new ValidatorException("No records found for the given grade");
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidatorException("Enter the valid grade");
		}

		return list;
	}

	public void findBySubService(String sub) throws ValidatorException, SQLException {

		UserDAO userdao=new UserDAO();
		System.out.println("\n"+sub+"      |   REG-NO   |   STUDENT NAME");
		System.out.println("----------------------------------------");
		
		List<StudentDetails> det=null;
		try {
			det = userdao.findBySubject(sub);
		}catch (DBException e) {
			e.printStackTrace();
			throw new ValidatorException("Enter the valid subject name");
		}

		for (StudentDetails student1 : det) {
			System.out.println(student1.getSubject()+"            "+student1.getRegNo()+"           "+student1.getStudName() );
		}
		System.out.println("----------------------------------------");
	}
}
