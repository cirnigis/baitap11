package vn.iostar.dao.impl;

import vn.iostar.dao.IAuthorDAO_24162080;
import vn.iostar.model.Author_24162080;
import vn.iostar.utils.DBConnection_24162080;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO_24162080 implements IAuthorDAO_24162080 {

	@Override
	public List<Author_24162080> findAll() {

		List<Author_24162080> list = new ArrayList<>();

		String sql = """
				SELECT author_id, author_name, date_of_birth
				FROM author
				ORDER BY author_id
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				Author_24162080 author = new Author_24162080();

				author.setAuthorId(rs.getInt("author_id"));

				author.setAuthorName(rs.getString("author_name"));

				author.setDateOfBirth(rs.getDate("date_of_birth"));

				list.add(author);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Author_24162080 findById(int id) {

		String sql = """
				SELECT author_id, author_name, date_of_birth
				FROM author
				WHERE author_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Author_24162080 author = new Author_24162080();

				author.setAuthorId(rs.getInt("author_id"));

				author.setAuthorName(rs.getString("author_name"));

				author.setDateOfBirth(rs.getDate("date_of_birth"));

				return author;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}