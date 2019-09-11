package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.Subject;
import com.revature.gradingsystem.util.ConnectionUtil;

public class SubjectDAO {

	public List<Subject> findAll() throws DBException{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> list = null;
		
		try {
		con = ConnectionUtil.getConnection();
		String sql = "select id, sub_code, subject_name from subject";
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		list= new ArrayList<Subject>();
		while(rs.next()) {
		
		list.add(new Subject(rs.getInt("id"), rs.getString("sub_code"), rs.getString("subject_name")));
		
		}
	} catch (SQLException e) {
		throw new DBException("Unable to get the Subject", e);
	} finally {
		ConnectionUtil.close(con, pst, rs);
	}
		return list;
	}

	public Subject findBySubject(String subCode) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Subject sub = null;
		try {
		con = ConnectionUtil.getConnection();
		String sql = "select id, sub_code, subject_name from subject where sub_code = ?";
		pst = con.prepareStatement(sql);
		pst.setString(1, subCode);
		
		rs = pst.executeQuery();
		if(rs.next()) {
		 sub = new Subject();
		 sub.setCode(rs.getString("sub_code"));
		
		}
	} catch (SQLException e) {
		throw new DBException("Unable to get the Subject", e);
	} finally {
		ConnectionUtil.close(con, pst, rs);
	}
		return sub;
	}
	
}
