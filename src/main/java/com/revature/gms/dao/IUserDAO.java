package com.revature.gms.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;

public interface IUserDAO {

	StudentDetails findByRegNo(String name, int regno) throws DBException;

	List<StudentDetails> findByGrade(String grade) throws SQLException, DBException;

	List<StudentDetails> findBySubject(String sub) throws SQLException, DBException;

}