package com.revature.gms.services;

import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidationException;
import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;

public class AdminService {

	public UserDetails login(String name, String pwd) throws ValidationException {
		
		IAdminDAO admindao=new AdminDAO();
		UserDetails userdetail = null;
		
		try {
			userdetail=admindao.findAdmin(name, pwd);
			if (userdetail == null) {
				throw new ValidationException("Enter the valid name and password");
			}
		} catch (DBException e) {
			e.printStackTrace();
			throw new ValidationException("Enter the valid name and password");
		}
		return userdetail;
	}

	public void gradeCalc(StudentDetails studentdetail) {
		
		ScoreRangeDetails range = new ScoreRangeDetails();
		
			int total = studentdetail.getSub1() + studentdetail.getSub2() + studentdetail.getSub3() + studentdetail.getSub4() + studentdetail.getSub5();
			float avg = total / 5;
			String grade = getGrade(range, avg);
			
			studentdetail.setTotal(total);
			studentdetail.setAvg(avg);
			studentdetail.setGrade(grade);
			
			IAdminDAO admin=new AdminDAO();
			
			try {
				admin.updateMarks(studentdetail);
			} catch (DBException e) {
				e.printStackTrace();
			}
	
		
	}

	public String getGrade(ScoreRangeDetails range, float avg) {
		String grade;
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
		else
			grade = range.getGrade0();
		return grade;
	}
}
