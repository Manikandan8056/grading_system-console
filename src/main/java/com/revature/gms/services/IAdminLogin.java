package com.revature.gms.services;

import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ValidationException;

public interface IAdminLogin {

	void aLogin(String name, String pwd) throws DBException, ValidationException;

}