package com.revature.gradingsystem.service;

import com.revature.gradingsystem.dao.AdminDaoImpl;

import java.util.List;

import com.revature.gradingsystem.dao.AdminDao;
import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.exception.ServiceException;
import com.revature.gradingsystem.model.ScoreRange;
import com.revature.gradingsystem.model.UserDetails;

public class AdminService {

	public UserDetails adminLogin(String name, String pwd) throws ServiceException {
		
		AdminDao admindao=new AdminDaoImpl();
		UserDetails userdetail = null;
		
		try {
			userdetail=admindao.findAdminByNamePassword(name, pwd);
			if (userdetail == null) {
				throw new ServiceException("Invalid Username and password, Please enter the valid one.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException("Invalid Username and password, Please enter the valid one.");
		}
		return userdetail;
		
		
	}

	public void updateScoreRangeService(String grade, int min, int max) {

		AdminDao admindao = new AdminDaoImpl();
		try {
			admindao.updateScoreRange(grade, min, max);
			
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}

	public void viewScoreRangeService() {

		AdminDao admindao = new AdminDaoImpl();
		List<ScoreRange> list = null;
		
		try {
			list = admindao.viewScoreRange();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\nGRADE   |  Min-Mark  |  Max-Mark");
		System.out.println("--------------------------------");
		for (ScoreRange scorerange : list) {
			System.out.println(scorerange.getGrade()+"            "+scorerange.getMin()+"            "+scorerange.getMax());
		}
		System.out.println("--------------------------------");
	}
	
	public void deleteScoreRangeService() {

		AdminDao admindao = new AdminDaoImpl();
		
		try {
			admindao.deleteScoreRange();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
	}

}
