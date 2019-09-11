package com.revature.gradingsystem.dao;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.UserDetails;

public interface UserDao {

	UserDetails findUserByNamePassword(String name, String pass) throws DBException;

}
