package com.revature.gradingsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.gradingsystem.exception.DBException;
import com.revature.gradingsystem.model.UserDetails;
import com.revature.gradingsystem.util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	public UserDetails findUserByNamePassword(String name, String pass) throws DBException {

		Connection con = null;
		PreparedStatement pst = null;
		UserDetails userdetail = null;
		ResultSet rs = null;

		try {
			con = ConnectionUtil.getConnection();
			String sql = "select name, mob_no, role from app_users where name = ? and password = ? and role = 'T'";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pass);
			rs = pst.executeQuery();

			if (rs.next()) {
				userdetail = new UserDetails();

				userdetail.setName(rs.getString("name"));
				userdetail.setMobno(rs.getLong("mob_no"));
				userdetail.setRole(rs.getString("role"));
			}

		} catch (SQLException e) {
			throw new DBException("Unable to Admin-login", e);
		} finally {
			ConnectionUtil.close(con, pst, rs);
		}
		return userdetail;
	}

}
