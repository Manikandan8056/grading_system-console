package com.revature.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.util.ConnectionUtil;

public class UserDAO implements IUserDAO {

	public StudentDetails findByStudent(String name, int regno) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		StudentDetails student=null;
		
		
		try {
			con=ConnectionUtil.getConnection();
			String sql = "select student_name,reg_no,sub1,sub2,sub3,sub4,sub5,total,average,grade from student_details where student_name = ? and reg_no = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, regno);
			ResultSet rs = pst.executeQuery();
			
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
			ConnectionUtil.close(con, pst);
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

	public List<StudentDetails> findByGrade(String grade) throws SQLException {

		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from student_details where grade = ? order by average desc;";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, grade);
		ResultSet rs = pst.executeQuery();
		List<StudentDetails> list=new ArrayList<StudentDetails>();
		
		while (rs.next()) {
			StudentDetails details = toRow1(rs);
			list.add(details);
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

	public void findBySubject(String sub) throws SQLException {

		Connection con = ConnectionUtil.getConnection();
		String sql = "select student_name,reg_no,"+sub+" as subject from student_details order by "+sub+" desc";
		Statement pst = con.createStatement();
		ResultSet rs = pst.executeQuery(sql);
		
		while (rs.next()) {
			
			int subj=rs.getInt("subject");
			int reg=rs.getInt("reg_no");
			String name=rs.getString("student_name");
			 
			System.out.println(subj+"            "+reg+"           "+name );
		}
		
	}

}
