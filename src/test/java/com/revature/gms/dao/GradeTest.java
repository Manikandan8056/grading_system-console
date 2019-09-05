package com.revature.gms.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.services.AdminService;

public class GradeTest {

	private ScoreRangeDetails range = null;
	
	@Before
	public void init() {
		range = new ScoreRangeDetails();
		range.setGrade80("B");
	}
	
	
	
	@Test
	public void testGrade_B() {
		
		AdminService admin = new AdminService();
		
		float avg = 87f;
		String grade = admin.getGrade(range, avg);
		assertEquals("B", grade);
	}
	
	@Test
	public void testGrade_() {
		
		AdminService admin = new AdminService();
		
		float avg = 87f;
		String grade = admin.getGrade(range, avg);
		assertEquals("B", grade);
	}

}
