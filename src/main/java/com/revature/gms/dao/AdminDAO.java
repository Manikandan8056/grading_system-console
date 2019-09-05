package com.revature.gms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.UserDetails;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.util.*;

public class AdminDAO implements IAdminDAO {
	
	public UserDetails findAdmin(String name, String pwd) throws DBException  {
		
		Connection con = null;
		PreparedStatement pst = null;
		UserDetails admin=null;
		ResultSet rs = null;
		
		try {
			con=ConnectionUtil.getConnection();
			String sql = "select name, mob_no, role from admin where name = ? and password = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				admin= new UserDetails();
				
				admin.setName(rs.getString("name"));
				admin.setMobno(rs.getLong("mob_no"));
				admin.setRole(rs.getString("role"));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to Admin-login",e);
		}finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return admin;
	}

	public void updateMarks( StudentDetails studentdetail) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		con=ConnectionUtil.getConnection();
		ResultSet rs = null;
		
		try {
			String sql = "insert into student_details (student_name, reg_no, sub1, sub2, sub3, sub4, sub5, total, average, grade) values (?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, studentdetail.getStudName());
			pst.setInt(2, studentdetail.getRegNo());
			pst.setInt(3, studentdetail.getSub1());
			pst.setInt(4, studentdetail.getSub2());
			pst.setInt(5, studentdetail.getSub3());
			pst.setInt(6, studentdetail.getSub4());
			pst.setInt(7, studentdetail.getSub5());
			pst.setInt(8, studentdetail.getTotal());
			pst.setFloat(9, studentdetail.getAvg());
			pst.setString(10, studentdetail.getGrade());
			int rows = pst.executeUpdate();
			System.out.println("No of rows inserted:" + rows);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to login",e);
		}finally {
			ConnectionUtil.close(con, pst, rs);
		}
		
	}
	
	/*public void updateMarks(StudentMark mark) throws DBException {

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
			throw new DBException("Unable to update your mark",e);
		}finally {
			ConnectionUtil.close(con, pst);
		}
		
	}*/

	public List<StudentDetails> listOfStudents() throws SQLException, DBException {

		List<StudentDetails> list;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select * from student_details order by grade";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<StudentDetails>();
			while (rs.next()) {
				StudentDetails details = toRow1(rs);
				list.add(details);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records",e);
		}finally {
			ConnectionUtil.close(con, pst, rs);
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
	
	


