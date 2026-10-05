package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.iostar.dao.IUserDAO_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.utils.DBConnection_24162080;

public class UserDAO_24162080 implements IUserDAO_24162080 {

	@Override
	public User_24162080 findByEmailAndPassword(String email, String passwd) {

		String sql = """
				SELECT id,
				       email,
				       fullname,
				       phone,
				       passwd,
				       signup_date,
				       last_login,
				       is_admin
				FROM users
				WHERE email = ?
				  AND passwd = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);
			ps.setString(2, passwd);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					User_24162080 user = new User_24162080();

					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setFullname(rs.getString("fullname"));
					user.setPhone(rs.getInt("phone"));
					user.setPasswd(rs.getString("passwd"));
					user.setSignupDate(rs.getTimestamp("signup_date"));
					user.setLastLogin(rs.getTimestamp("last_login"));
					user.setAdmin(rs.getBoolean("is_admin"));

					return user;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateLastLogin(int userId) {

		String sql = """
				UPDATE users
				SET last_login = GETDATE()
				WHERE id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean existsByEmail(String email) {

		String sql = "SELECT id FROM users WHERE email = ?";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean register(User_24162080 user) {

		String sql = """
				INSERT INTO users
				(email, fullname, phone, passwd, signup_date, last_login, is_admin)
				VALUES (?, ?, ?, ?, GETDATE(), NULL, 0)
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getFullname());
			ps.setInt(3, user.getPhone());
			ps.setString(4, user.getPasswd());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}