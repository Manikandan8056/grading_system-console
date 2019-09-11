package com.revature.gradingsystem.util;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.revature.gradingsystem.exception.DBException;

public class ConnectionUtilTest {

	@Test
	public void testConnection() {
		Connection connection = null;;
		try {
			connection = ConnectionUtil.getConnection();
		} catch (DBException e) {
			System.out.println(e.getMessage());
		}
		assertNotNull(connection);
	}

}
