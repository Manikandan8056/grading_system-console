package com.revature.gradingsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.gradingsystem.exception.DBException;

public class ConnectionUtil {

	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/gms";
	private static final String username = "root";
	private static final String password = "root";

	public static Connection getConnection() throws DBException {

		Connection con = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			throw new DBException("Unable to load the driver class", e);
		} catch (SQLException e) {
			throw new DBException("Unable to get DB Connection", e);
		}

		return con;
	}

	public static void close(Connection con, PreparedStatement pst, ResultSet rs) throws DBException {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			throw new DBException("Unable to close the Connection", e);
		}
	}

	public static void close(Connection con, PreparedStatement pst) throws DBException {

		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			throw new DBException("Unable to close the Connection", e);
		}

	}

}
