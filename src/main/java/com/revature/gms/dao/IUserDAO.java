package com.revature.gms.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;

public interface IUserDAO {

	StudentDetails findByStudent(String name, int regno) throws DBException;

	List<StudentDetails> findByGrade(String grade) throws SQLException;

	void findBySubject(String sub) throws SQLException;

}