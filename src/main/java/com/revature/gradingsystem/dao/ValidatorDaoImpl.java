package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.util.ConnectionUtil;

public class ValidatorDaoImpl implements ValidatorDao {

	public ScoreRange findGrade(String grade) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ScoreRange scorerange = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade, min, max from score_range where grade = ? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, grade);
			rs = pst.executeQuery();

			if (rs.next()) {
				scorerange = new ScoreRange();
				scorerange.setGrade("grade");
			}

		} catch (SQLException e) {
			throw new DBException("Unable to get the grade", e);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return scorerange;
	}

	public ScoreRange findRange(int mark) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ScoreRange scorerange1 = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select grade, min, max from score_range where ? between min and max";
			pst = con.prepareStatement(sql);
			pst.setInt(1, mark);
			rs = pst.executeQuery();

			if (rs.next()) {
				scorerange1 = new ScoreRange();
				scorerange1.setGrade("grade");
			}

		} catch (SQLException e) {
			throw new DBException("Unable to get the grade", e);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return scorerange1;
	}

	public int findRegNo(int regno) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		int reg_no = 0;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select reg_no from student_details where reg_no = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, regno);
			rs = pst.executeQuery();

			if (rs.next()) {
				reg_no = rs.getInt("reg_no");
			}

		} catch (SQLException e) {
			throw new DBException("Unable to get the grade", e);
		} catch (DBException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return reg_no;
	}

}
