package com.revature.gms.services;

import com.revature.gms.dao.AdminDAO;
import com.revature.gms.dao.IAdminDAO;
import com.revature.gms.exception.DBException;
import com.revature.gms.exception.ServiceException;
import com.revature.gms.model.UserDetails;

public class AdminService {

	public UserDetails login(String name, String pwd) throws ServiceException {
		
		IAdminDAO admindao=new AdminDAO();
		UserDetails userdetail = null;
		
		try {
			userdetail=admindao.findAdmin(name, pwd);
			if (userdetail == null) {
				throw new ServiceException("Enter the valid name and password");
			}
		} catch (DBException e) {
			throw new ServiceException("Enter the valid name and password");
		}
		return userdetail;
	}

}
