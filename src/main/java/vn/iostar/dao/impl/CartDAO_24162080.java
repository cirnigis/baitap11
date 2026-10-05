package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.dao.ICartDAO_24162080;
import vn.iostar.model.CartItem_24162080;
import vn.iostar.utils.DBConnection_24162080;

public class CartDAO_24162080 implements ICartDAO_24162080 {

	@Override
	public List<CartItem_24162080> findByUserId(int userId) {

		List<CartItem_24162080> list = new ArrayList<>();

		String sql = """
				SELECT c.cart_id,
				       c.user_id,
				       c.bookid,
				       c.quantity,
				       b.title,
				       b.price,
				       b.cover_image,
				       b.quantity AS stock
				FROM cart_items c
				INNER JOIN books b
				    ON c.bookid = b.bookid
				WHERE c.user_id = ?
				ORDER BY c.cart_id DESC
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					CartItem_24162080 item = new CartItem_24162080();

					item.setCartId(rs.getInt("cart_id"));
					item.setUserId(rs.getInt("user_id"));
					item.setBookId(rs.getInt("bookid"));
					item.setQuantity(rs.getInt("quantity"));
					item.setTitle(rs.getString("title"));
					item.setPrice(rs.getBigDecimal("price"));
					item.setCoverImage(rs.getString("cover_image"));
					item.setStock(rs.getInt("stock"));

					list.add(item);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public CartItem_24162080 findByUserIdAndBookId(int userId, int bookId) {

		String sql = """
				SELECT c.cart_id,
				       c.user_id,
				       c.bookid,
				       c.quantity,
				       b.title,
				       b.price,
				       b.cover_image,
				       b.quantity AS stock
				FROM cart_items c
				INNER JOIN books b
				    ON c.bookid = b.bookid
				WHERE c.user_id = ?
				  AND c.bookid = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ps.setInt(2, bookId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					CartItem_24162080 item = new CartItem_24162080();

					item.setCartId(rs.getInt("cart_id"));
					item.setUserId(rs.getInt("user_id"));
					item.setBookId(rs.getInt("bookid"));
					item.setQuantity(rs.getInt("quantity"));
					item.setTitle(rs.getString("title"));
					item.setPrice(rs.getBigDecimal("price"));
					item.setCoverImage(rs.getString("cover_image"));
					item.setStock(rs.getInt("stock"));

					return item;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean add(int userId, int bookId, int quantity) {

		String sql = """
				INSERT INTO cart_items(user_id, bookid, quantity)
				VALUES (?, ?, ?)
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ps.setInt(2, bookId);
			ps.setInt(3, quantity);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateQuantity(int cartId, int userId, int quantity) {

		String sql = """
				UPDATE cart_items
				SET quantity = ?
				WHERE cart_id = ?
				  AND user_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, quantity);
			ps.setInt(2, cartId);
			ps.setInt(3, userId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(int cartId, int userId) {

		String sql = """
				DELETE FROM cart_items
				WHERE cart_id = ?
				  AND user_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, cartId);
			ps.setInt(2, userId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean clear(int userId) {

		String sql = """
				DELETE FROM cart_items
				WHERE user_id = ?
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
}