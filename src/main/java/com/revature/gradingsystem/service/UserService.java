package com.revature.gradingsystem.service;

import com.revature.gradingsystem.dao.UserDao;
import com.revature.gradingsystem.dao.UserDaoImpl;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.UserDetails;

public class UserService {

	public UserDetails userLogin(String name, String pass) throws ServiceException {
		
		UserDao userdao=new UserDaoImpl();
		UserDetails userdetail = null;
		
		try {
			userdetail=userdao.findUserByNamePassword(name, pass);
			if (userdetail == null) {
				throw new ServiceException("Invalid Username and password");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return userdetail;

	}

}
