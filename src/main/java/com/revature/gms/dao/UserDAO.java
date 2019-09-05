package com.revature.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.util.ConnectionUtil;

public class UserDAO implements IUserDAO {

	public StudentDetails findByRegNo(String name, int regno) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		StudentDetails student=null;
		ResultSet rs = null;
		
		try {
			con=ConnectionUtil.getConnection();
			String sql = "select student_name,reg_no,sub1,sub2,sub3,sub4,sub5,total,average,grade from student_details where student_name = ? and reg_no = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, regno);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				student= new StudentDetails();
				
				student.setStudName(rs.getString("student_name"));
				student.setRegNo(rs.getInt("reg_no"));
				student.setSub1(rs.getInt("sub1"));
				student.setSub2(rs.getInt("sub2"));
				student.setSub3(rs.getInt("sub3"));
				student.setSub4(rs.getInt("sub4"));
				student.setSub5(rs.getInt("sub5"));
				student.setTotal(rs.getInt("total"));
				student.setAvg(rs.getInt("average"));
				student.setGrade(rs.getString("grade"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Student-login ",e);
		}finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return student;
	}
	
/*public List<StudentMark> findByStudentMarks(String name, int regno) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		List<StudentMark> list = new ArrayList<StudentMark>();
		
		
		try {
			con=ConnectionUtil.getConnection();
			String sql = "select s.reg_no, m.id, m.subject_name, m.marks from student_marks m, student_details s where s.reg_no = m.reg_no and s.student_name = ? and s.reg_no = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, regno);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				StudentMark mark = new StudentMark();
				mark.setId(rs.getInt("id"));
				mark.setSubjectName(rs.getString("subject_name"));
				mark.setMark(rs.getInt("marks"));
				
				StudentDetails student= new StudentDetails();				
				student.setRegNo(rs.getInt("reg_no"));
				
				mark.setStudentDetail(student);
				
				list.add(mark);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Student-login ",e);
		}finally {
			ConnectionUtil.close(con, pst);
		}
		return list;
	}*/

	public List<StudentDetails> findByGrade(String grade) throws  DBException {
		Connection con = null;
		PreparedStatement pst = null;
		List<StudentDetails> list=null;
		ResultSet rs = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select * from student_details where grade = ? order by average desc;";
			pst = con.prepareStatement(sql);
			pst.setString(1, grade);
			rs = pst.executeQuery();
			list= new ArrayList<StudentDetails>();
			
			while (rs.next()) {
				StudentDetails details = toRow1(rs);
				list.add(details);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Student-login ",e);
		}finally {
			ConnectionUtil.close(con, pst, rs);
		}
		
		return list;
	}
	
	private StudentDetails toRow1(ResultSet rs) throws SQLException {

		StudentDetails details = new StudentDetails();
		details.setStudName(rs.getString("student_name"));
		details.setRegNo(rs.getInt("reg_no"));
		details.setSub1(rs.getInt("sub1"));
		details.setSub2(rs.getInt("sub2"));
		details.setSub3(rs.getInt("sub3"));
		details.setSub4(rs.getInt("sub4"));
		details.setSub5(rs.getInt("sub5"));
		details.setTotal(rs.getInt("total"));
		details.setAvg(rs.getInt("average"));
		details.setGrade(rs.getString("grade"));
	
		return details;
	}

	public List<StudentDetails> findBySubject(String sub) throws SQLException, DBException {
		Connection con1 = null;
		PreparedStatement pst1 = null;
		StudentDetails student1=null;
		List<StudentDetails> det=null;
		ResultSet rs = null;
		try {
			con1 = ConnectionUtil.getConnection();
			String sql = "select student_name,reg_no,"+sub+" as subject from student_details order by "+sub+" desc";
			pst1 = con1.prepareStatement(sql);
			rs = pst1.executeQuery();
			det= new ArrayList<StudentDetails>();
			while(rs.next()) {
				student1= new StudentDetails();
				student1.setStudName(rs.getString("student_name"));
				student1.setRegNo(rs.getInt("reg_no"));
				student1.setSubject(rs.getInt("subject"));
				
				det.add(student1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Student-login ",e);
		}finally {
			ConnectionUtil.close(con1, pst1, rs);
		}
		return det;
	
		
	}

}
