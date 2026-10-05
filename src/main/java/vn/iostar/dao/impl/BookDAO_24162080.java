package vn.iostar.dao.impl;

import vn.iostar.dao.IBookDAO_24162080;
import vn.iostar.model.Book_24162080;
import vn.iostar.utils.DBConnection_24162080;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO_24162080 implements IBookDAO_24162080 {

	@Override
	public List<Book_24162080> findAll(int page, int pageSize) {

		List<Book_24162080> list = new ArrayList<>();

		String sql = """
				SELECT bookid, isbn, title, publisher, price,
				       description, publish_date, cover_image, quantity
				FROM books
				ORDER BY bookid DESC
				OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, (page - 1) * pageSize);
			ps.setInt(2, pageSize);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Book_24162080 book = new Book_24162080();

				book.setBookid(rs.getInt("bookid"));
				book.setIsbn(rs.getInt("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPrice(rs.getBigDecimal("price"));
				book.setDescription(rs.getString("description"));
				book.setPublishDate(rs.getDate("publish_date"));
				book.setCoverImage(rs.getString("cover_image"));
				book.setQuantity(rs.getInt("quantity"));

				list.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int count() {

		String sql = "SELECT COUNT(*) FROM books";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public Book_24162080 findById(int id) {

		String sql = """
				SELECT bookid, isbn, title, publisher, price,
				       description, publish_date, cover_image, quantity
				FROM books
				WHERE bookid = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Book_24162080 book = new Book_24162080();

				book.setBookid(rs.getInt("bookid"));
				book.setIsbn(rs.getInt("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPrice(rs.getBigDecimal("price"));
				book.setDescription(rs.getString("description"));
				book.setPublishDate(rs.getDate("publish_date"));
				book.setCoverImage(rs.getString("cover_image"));
				book.setQuantity(rs.getInt("quantity"));

				return book;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean insert(Book_24162080 book) {

		String sql = """
				INSERT INTO books
				(isbn, title, publisher, price, description,
				 publish_date, cover_image, quantity)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getPublisher());
			ps.setBigDecimal(4, book.getPrice());
			ps.setString(5, book.getDescription());
			ps.setDate(6, book.getPublishDate());
			ps.setString(7, book.getCoverImage());
			ps.setInt(8, book.getQuantity());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Book_24162080 book) {

		String sql = """
				UPDATE books
				SET isbn = ?,
				    title = ?,
				    publisher = ?,
				    price = ?,
				    description = ?,
				    publish_date = ?,
				    cover_image = ?,
				    quantity = ?
				WHERE bookid = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getPublisher());
			ps.setBigDecimal(4, book.getPrice());
			ps.setString(5, book.getDescription());
			ps.setDate(6, book.getPublishDate());
			ps.setString(7, book.getCoverImage());
			ps.setInt(8, book.getQuantity());
			ps.setInt(9, book.getBookid());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int id) {

		String sql = "DELETE FROM books WHERE bookid = ?";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Book_24162080> findByAuthor(int authorId, int page, int pageSize) {
		List<Book_24162080> list = new ArrayList<>();

		String sql = """
				SELECT b.bookid, b.isbn, b.title, b.publisher, b.price,
				       b.description, b.publish_date, b.cover_image, b.quantity
				FROM books b
				INNER JOIN book_author ba ON b.bookid = ba.bookid
				WHERE ba.author_id = ?
				ORDER BY b.bookid
				OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, authorId);
			ps.setInt(2, (page - 1) * pageSize);
			ps.setInt(3, pageSize);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book_24162080 book = new Book_24162080();

				book.setBookid(rs.getInt("bookid"));
				book.setIsbn(rs.getLong("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPublisher(rs.getString("publisher"));
				book.setPrice(rs.getBigDecimal("price"));
				book.setDescription(rs.getString("description"));
				book.setPublishDate(rs.getDate("publish_date"));
				book.setCoverImage(rs.getString("cover_image"));
				book.setQuantity(rs.getInt("quantity"));

				list.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int countByAuthor(int authorId) {

		String sql = """
				SELECT COUNT(*)
				FROM book_author
				WHERE author_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, authorId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
}