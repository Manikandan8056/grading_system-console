package com.revature.gms.services;

import com.google.protobuf.ServiceException;
import com.revature.gms.exception.DBException;
import com.revature.gms.model.StudentDetails;

public interface IUserLogin {

	StudentDetails sLogin(String name, int regno) throws ServiceException;

	void userLogin(String name, String pass) throws DBException;

}