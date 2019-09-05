package com.revature.gms.dao;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;
import com.revature.gms.model.UserDetails;

public interface IAdminDAO {

	public  UserDetails findAdmin(String name, String mobno) throws DBException;

	public void updateMarks(StudentDetails studentdetail) throws DBException;

	
}