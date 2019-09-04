package com.revature.gms.dao;

import com.revature.gms.exception.DBException;
import com.revature.gms.model.AdminDetails;

public interface IAdminDAO {

	public  AdminDetails findAdmin(String name, String mobno) throws DBException;

	
}