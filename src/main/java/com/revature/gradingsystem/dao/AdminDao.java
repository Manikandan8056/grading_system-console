package com.revature.gradingsystem.dao;


import java.util.List;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.model.UserDetails;

public interface AdminDao {

	UserDetails findAdminByNamePassword(String name, String pwd) throws DBException;

	int updateScoreRange(String grade, int min, int max) throws DBException;

	int deleteScoreRange() throws DBException;

	List<ScoreRange> viewScoreRange() throws DBException;

}
