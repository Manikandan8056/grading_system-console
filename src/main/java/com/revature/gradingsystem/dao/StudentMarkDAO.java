package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gradingsystem.dto.StudentMarkDTO;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.util.ConnectionUtil;
import com.revature.gradingsystem.model.StudentMark;

public class StudentMarkDAO {
	
	public void insertMarks(List<StudentMark> marks) throws DBException {

		for (StudentMark studentMark : marks) {
			 
			Connection con = null;
			PreparedStatement pst = null;
			con = ConnectionUtil.getConnection();

			try {
				String sql = "insert into student_marks (reg_no, subject_code, marks) values (?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setInt(1, studentMark.getStudentDetail().getRegNo());
				pst.setString(2, studentMark.getSubject().getCode());
				pst.setInt(3, studentMark.getMark());
				
				pst.executeUpdate();
			} catch (SQLException e) {
				throw new DBException("Unable to Insert the marks", e);
			} finally {
				ConnectionUtil.close(con, pst);
			}
		}
	}
	
	public List<StudentMarkDTO> findBySubjectCode(String subjectCode) throws DBException{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentMarkDTO> list = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select sd.reg_no, sd.student_name, sm.marks, sg.grade from  student_details sd, student_marks sm,"
					+ " subject s, student_grade sg where sd.reg_no = sm.reg_no and sm.subject_code = s.sub_code and"
					+ " sm.reg_no = sg.reg_no and subject_code = ? order by marks desc";
			pst = con.prepareStatement(sql);
			pst.setString(1, subjectCode);
			
			rs = pst.executeQuery();
			list = new ArrayList<StudentMarkDTO>();
			while (rs.next()) {
				StudentMarkDTO sm = new StudentMarkDTO();
				sm.setRegNo(rs.getInt("reg_no"));
				sm.setStudentName(rs.getString("student_name"));
				sm.setMark(rs.getInt("marks"));
				sm.setGrade(rs.getString("grade"));
				
				list.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to get the records", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		
		return list;
	}

	public List<StudentMark> getMarksByRegNo(int regno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<StudentMark> list = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select subject_code, marks from student_marks where reg_no = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			
			rs = pst.executeQuery();
			list = new ArrayList<StudentMark>();
			while (rs.next()) {
				StudentMark mark = new StudentMark();
				mark.setSubjectName(rs.getString("subject_code"));
				mark.setMark(rs.getInt("marks"));
				
				list.add(mark);
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
