package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.util.ConnectionUtil;
import com.revature.gradingsystem.dto.StudentGradeDTO;
import com.revature.gradingsystem.model.StudentMark;

public class StudentGradeDAO {

	public void insertGrade(int regno, List<StudentMark> marks) throws DBException {

		int total = 0;
		for (StudentMark studentMark : marks) {
			total = total + studentMark.getMark();
		}
		
		float avg = (float) total / marks.size();
		String grade = findGrade(avg);
		
		//insert grade into student_grade 
		Connection con = null;
		PreparedStatement pst = null;
		con = ConnectionUtil.getConnection();

		try {
			String sql = "insert into student_grade ( reg_no, average, grade ) values (?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			pst.setFloat(2, avg);
			pst.setString(3, grade);
			
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DBException("Unable to update the grade", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}
		
	}
	
	//GradeDAO
	public String findGrade(float avg) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String grade = "";
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade from score_range where "+avg+" between min and max ";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				grade = rs.getString("grade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return grade;
	}
	
	public List<StudentGradeDTO> findByGrade(String grade) throws DBException{
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentGradeDTO> list = new ArrayList<StudentGradeDTO>();
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.reg_no, sd.student_name, g.average, g.grade from student_details sd, student_grade g where sd.reg_no = g.reg_no and grade = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, grade);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentGradeDTO sg = new StudentGradeDTO();
				sg.setRegNo(rs.getInt("reg_no"));
				sg.setStudentName(rs.getString("student_name"));
				sg.setAvg(rs.getInt("average"));
				sg.setGrade(rs.getString("grade"));
				
				list.add(sg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		
		return list;
	}

	public String isGradeExist(String grd) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String grade = "";
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade from score_range where grade = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, grd);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				grade = rs.getString("grade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return grade;
	}

	public StudentGradeDTO findByRegNo(int regno) throws DBException {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StudentGradeDTO stud = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.student_name, sd.reg_no, sg.average, sg.grade from student_details sd,"
					+ " student_grade sg where sd.reg_no = sg.reg_no and reg_no = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				stud = new StudentGradeDTO();
				stud.setStudentName(rs.getString("student_name"));
				stud.setRegNo(rs.getInt("reg_no"));
				stud.setAvg(rs.getFloat("average"));
				stud.setGrade(rs.getString("grade"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return stud;
	}

	public List<StudentGradeDTO> findStudentList() throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentGradeDTO> list = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = " select sd.student_name, sd.reg_no, sg.average, sg.grade from student_details sd,"
					+ " student_grade sg where sd.reg_no = sg.reg_no order by sg.average desc";
			pst = con.prepareStatement(sql);
			
			list = new ArrayList<StudentGradeDTO>();
			rs = pst.executeQuery();
			while (rs.next()) {
				StudentGradeDTO studentList = new StudentGradeDTO();
				studentList.setRegNo(rs.getInt("reg_no"));
				studentList.setStudentName(rs.getString("student_name"));
				studentList.setAvg(rs.getInt("average"));
				studentList.setGrade(rs.getString("grade"));
				
				list.add(studentList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		
		return list;
	}

}
