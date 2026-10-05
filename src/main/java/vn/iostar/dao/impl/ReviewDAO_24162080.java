package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.dao.IReviewDAO_24162080;
import vn.iostar.model.Review_24162080;
import vn.iostar.utils.DBConnection_24162080;

public class ReviewDAO_24162080 implements IReviewDAO_24162080 {

	@Override
	public List<Review_24162080> findByBookId(int bookId) {

		List<Review_24162080> reviews = new ArrayList<>();

		String sql = """
				SELECT r.review_id,
				       r.bookid,
				       r.user_id,
				       u.fullname,
				       r.review_text
				FROM reviews r
				JOIN users u ON r.user_id = u.id
				WHERE r.bookid = ?
				ORDER BY r.review_id DESC
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, bookId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					Review_24162080 review = new Review_24162080();

					review.setReviewId(rs.getInt("review_id"));
					review.setBookId(rs.getInt("bookid"));
					review.setUserId(rs.getInt("user_id"));
					review.setUserName(rs.getString("fullname"));
					review.setReviewText(rs.getString("review_text"));

					reviews.add(review);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return reviews;
	}

	@Override
	public int countByBookId(int bookId) {

		String sql = """
				SELECT COUNT(*)
				FROM reviews
				WHERE bookid = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, bookId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boolean insert(Review_24162080 review) {

		String sql = """
				INSERT INTO reviews(bookid, user_id, review_text)
				VALUES (?, ?, ?)
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, review.getBookId());
			ps.setInt(2, review.getUserId());
			ps.setString(3, review.getReviewText());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}