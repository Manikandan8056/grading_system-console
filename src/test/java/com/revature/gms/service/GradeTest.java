package com.revature.gms.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.gms.model.ScoreRangeDetails;
import com.revature.gms.services.UserService;

public class GradeTest {

	private ScoreRangeDetails range = null;
	
	@Before
	public void init() {
		range = new ScoreRangeDetails();
		range.setGrade90("A");
		range.setGrade80("B");
		range.setGrade70("C");
		range.setGrade60("D");
		range.setGrade50("E");
		range.setGrade0("F");
	}
	
	@Test
	public void testGrade_B() {
		
		UserService userService = new UserService();
		
		float avg = 87f;
		String grade = userService.getGrade(range, avg);
		assertEquals("B", grade);
	}
	
	@Test
	public void testGrade_A() {
		
		UserService userService = new UserService();
		
		float avg = 95f;
		String grade = userService.getGrade(range, avg);
		assertEquals("A", grade);
	}
	
	@Test
	public void testGrade_C() {
		
		UserService userService = new UserService();

		float avg = 75f;
		String grade = userService.getGrade(range, avg);
		assertEquals("C", grade);
	}
	
	@Test
	public void testGrade_D() {
		
		UserService userService = new UserService();
		
		float avg = 63f;
		String grade = userService.getGrade(range, avg);
		assertEquals("D", grade);
	}
	
	@Test
	public void testGrade_E() {
		
		UserService userService = new UserService();
		
		float avg = 57f;
		String grade = userService.getGrade(range, avg);
		assertEquals("E", grade);
	}
	
	@Test
	public void testGrade_F() {
		
		UserService userService = new UserService();
		
		float avg = 13f;
		String grade = userService.getGrade(range, avg);
		assertEquals("F", grade);
	}
	
}
