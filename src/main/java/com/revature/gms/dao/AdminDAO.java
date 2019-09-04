package com.revature.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.AdminDetails;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.StudentMark;
import com.revature.gms.util.*;

public class AdminDAO implements IAdminDAO {
	
	public AdminDetails findAdmin(String name, String pwd) throws DBException  {
		
		Connection con = null;
		PreparedStatement pst = null;
		AdminDetails admin=null;
		
		
		try {
			con=ConnectionUtil.getConnection();
			String sql = "select name, mob_no, role from admin where name = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pwd);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				admin= new AdminDetails();
				
				admin.setName(rs.getString("name"));
				admin.setMobno(rs.getLong("mob_no"));
				admin.setRole(rs.getString("role"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Admin-login",e);
		}finally {
			ConnectionUtil.close(con, pst);
		}
		return admin;
	}

	public void updateMarks(String name, int regno, int s1, int s2, int s3, int s4, int s5, int total, int avg, String grade) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		con=ConnectionUtil.getConnection();
		
		try {
			String sql = "insert into student_details (student_name, reg_no, sub1, sub2, sub3, sub4, sub5, total, average, grade) values (?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, regno);
			pst.setInt(3, s1);
			pst.setInt(4, s2);
			pst.setInt(5, s3);
			pst.setInt(6, s4);
			pst.setInt(7, s5);
			pst.setInt(8, total);
			pst.setInt(9, avg);
			pst.setString(10, grade);
			int rows = pst.executeUpdate();
			System.out.println("No of rows inserted:" + rows);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to login",e);
		}
		
	}
	
	public void updateMarks(StudentMark mark) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		con=ConnectionUtil.getConnection();
		
		try {
			String sql = "insert into student_marks ( reg_no, subject_name,marks) values (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(2, mark.getSubjectName());
			pst.setInt(1, mark.getStudentDetail().getRegNo());
			pst.setInt(3, mark.getMark());
			
			int rows = pst.executeUpdate();
			System.out.println("No of rows inserted:" + rows);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to login",e);
		}
		
	}

	public List<StudentDetails> listOfStudents() throws SQLException {

		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from student_details order by grade";
		PreparedStatement pst = con.prepareStatement(sql);
		
		ResultSet rs = pst.executeQuery();
		List<StudentDetails> list=new ArrayList<StudentDetails>();
		
		while (rs.next()) {
			StudentDetails details = toRow1(rs);
			list.add(details);
		}
		
		return list;
	}
	
	private StudentDetails toRow1(ResultSet rs) throws SQLException {

		String studname=rs.getString("student_name");
		Integer regno=rs.getInt("reg_no");
		Integer avg=rs.getInt("average");
		String grade=rs.getString("grade");
		
		StudentDetails details = new StudentDetails();
		details.setStudName(studname);
		details.setRegNo(regno);
		details.setAvg(avg);
		details.setGrade(grade);
	
		return details;
	}

		
	}
	
	


