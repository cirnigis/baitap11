package vn.iostar.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.model.OrderDetail_24162080;
import vn.iostar.dao.IOrderDAO_24162080;
import vn.iostar.model.CartItem_24162080;
import vn.iostar.model.Order_24162080;
import vn.iostar.utils.DBConnection_24162080;

public class OrderDAO_24162080 implements IOrderDAO_24162080 {

	@Override
	public int createOrder(Order_24162080 order, List<CartItem_24162080> cartItems) {

		String orderSql = "INSERT INTO orders " + "(user_id, recipient_name, phone, shipping_address, "
				+ "total_amount, payment_method, payment_status, order_status) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		String detailSql = "INSERT INTO order_details " + "(order_id, bookid, quantity, unit_price, subtotal) "
				+ "VALUES (?, ?, ?, ?, ?)";

		String stockSql = "UPDATE books " + "SET quantity = quantity - ? " + "WHERE bookid = ? AND quantity >= ?";

		String clearCartSql = "DELETE FROM cart_items WHERE user_id = ?";

		try (Connection conn = DBConnection_24162080.getConnection()) {

			conn.setAutoCommit(false);

			try (PreparedStatement orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

					PreparedStatement detailStmt = conn.prepareStatement(detailSql);

					PreparedStatement stockStmt = conn.prepareStatement(stockSql);

					PreparedStatement clearCartStmt = conn.prepareStatement(clearCartSql)) {

				orderStmt.setInt(1, order.getUserId());
				orderStmt.setString(2, order.getRecipientName());
				orderStmt.setString(3, order.getPhone());
				orderStmt.setString(4, order.getShippingAddress());
				orderStmt.setBigDecimal(5, order.getTotalAmount());
				orderStmt.setString(6, order.getPaymentMethod());
				orderStmt.setString(7, order.getPaymentStatus());
				orderStmt.setString(8, order.getOrderStatus());

				int affected = orderStmt.executeUpdate();

				if (affected == 0) {
					conn.rollback();
					return -1;
				}

				int orderId;

				try (ResultSet rs = orderStmt.getGeneratedKeys()) {

					if (!rs.next()) {
						conn.rollback();
						return -1;
					}

					orderId = rs.getInt(1);
				}

				for (CartItem_24162080 item : cartItems) {

					BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

					detailStmt.setInt(1, orderId);
					detailStmt.setInt(2, item.getBookId());
					detailStmt.setInt(3, item.getQuantity());
					detailStmt.setBigDecimal(4, item.getPrice());
					detailStmt.setBigDecimal(5, subtotal);

					detailStmt.executeUpdate();

					stockStmt.setInt(1, item.getQuantity());
					stockStmt.setInt(2, item.getBookId());
					stockStmt.setInt(3, item.getQuantity());

					int stockUpdated = stockStmt.executeUpdate();

					if (stockUpdated == 0) {
						conn.rollback();
						return -1;
					}
				}

				clearCartStmt.setInt(1, order.getUserId());
				clearCartStmt.executeUpdate();

				conn.commit();

				return orderId;

			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Order_24162080> findByUserId(int userId) {

		List<Order_24162080> orders = new ArrayList<>();

		String sql = """
				SELECT order_id,
				       user_id,
				       recipient_name,
				       phone,
				       shipping_address,
				       total_amount,
				       payment_method,
				       payment_status,
				       order_status,
				       created_at
				FROM orders
				WHERE user_id = ?
				ORDER BY created_at DESC
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					Order_24162080 order = new Order_24162080();

					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setRecipientName(rs.getString("recipient_name"));
					order.setPhone(rs.getString("phone"));
					order.setShippingAddress(rs.getString("shipping_address"));
					order.setTotalAmount(rs.getBigDecimal("total_amount"));
					order.setPaymentMethod(rs.getString("payment_method"));
					order.setPaymentStatus(rs.getString("payment_status"));
					order.setOrderStatus(rs.getString("order_status"));
					order.setCreatedAt(rs.getTimestamp("created_at"));

					orders.add(order);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public Order_24162080 findById(int orderId, int userId) {

		String sql = """
				SELECT order_id,
				       user_id,
				       recipient_name,
				       phone,
				       shipping_address,
				       total_amount,
				       payment_method,
				       payment_status,
				       order_status,
				       created_at
				FROM orders
				WHERE order_id = ?
				  AND user_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);
			ps.setInt(2, userId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					Order_24162080 order = new Order_24162080();

					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setRecipientName(rs.getString("recipient_name"));
					order.setPhone(rs.getString("phone"));
					order.setShippingAddress(rs.getString("shipping_address"));
					order.setTotalAmount(rs.getBigDecimal("total_amount"));
					order.setPaymentMethod(rs.getString("payment_method"));
					order.setPaymentStatus(rs.getString("payment_status"));
					order.setOrderStatus(rs.getString("order_status"));
					order.setCreatedAt(rs.getTimestamp("created_at"));

					return order;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<OrderDetail_24162080> findDetailsByOrderId(int orderId) {

		List<OrderDetail_24162080> details = new ArrayList<>();

		String sql = """
				SELECT order_detail_id,
				       order_id,
				       bookid,
				       quantity,
				       unit_price,
				       subtotal
				FROM order_details
				WHERE order_id = ?
				ORDER BY order_detail_id
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					OrderDetail_24162080 detail = new OrderDetail_24162080();

					detail.setOrderDetailId(rs.getInt("order_detail_id"));

					detail.setOrderId(rs.getInt("order_id"));

					detail.setBookId(rs.getInt("bookid"));

					detail.setQuantity(rs.getInt("quantity"));

					detail.setUnitPrice(rs.getBigDecimal("unit_price"));

					detail.setSubtotal(rs.getBigDecimal("subtotal"));

					details.add(detail);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return details;
	}

	@Override
	public List<Order_24162080> findAll() {

		List<Order_24162080> orders = new ArrayList<>();

		String sql = """
				SELECT order_id,
				       user_id,
				       recipient_name,
				       phone,
				       shipping_address,
				       total_amount,
				       payment_method,
				       payment_status,
				       order_status,
				       created_at
				FROM orders
				ORDER BY created_at DESC
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {

				Order_24162080 order = new Order_24162080();

				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setRecipientName(rs.getString("recipient_name"));
				order.setPhone(rs.getString("phone"));
				order.setShippingAddress(rs.getString("shipping_address"));
				order.setTotalAmount(rs.getBigDecimal("total_amount"));
				order.setPaymentMethod(rs.getString("payment_method"));
				order.setPaymentStatus(rs.getString("payment_status"));
				order.setOrderStatus(rs.getString("order_status"));
				order.setCreatedAt(rs.getTimestamp("created_at"));

				orders.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}

	@Override
	public boolean updateStatus(int orderId, String status) {

		String sql = """
				UPDATE orders
				SET order_status = ?
				WHERE order_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, orderId);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Order_24162080 findByIdForAdmin(int orderId) {

		String sql = """
				SELECT order_id,
				       user_id,
				       recipient_name,
				       phone,
				       shipping_address,
				       total_amount,
				       payment_method,
				       payment_status,
				       order_status,
				       created_at
				FROM orders
				WHERE order_id = ?
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, orderId);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {

					Order_24162080 order = new Order_24162080();

					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setRecipientName(rs.getString("recipient_name"));
					order.setPhone(rs.getString("phone"));
					order.setShippingAddress(rs.getString("shipping_address"));
					order.setTotalAmount(rs.getBigDecimal("total_amount"));
					order.setPaymentMethod(rs.getString("payment_method"));
					order.setPaymentStatus(rs.getString("payment_status"));
					order.setOrderStatus(rs.getString("order_status"));
					order.setCreatedAt(rs.getTimestamp("created_at"));

					return order;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Order_24162080> findByUserIdAndStatus(int userId, String status) {

		List<Order_24162080> orders = new ArrayList<>();

		String sql = """
				SELECT order_id,
				       user_id,
				       recipient_name,
				       phone,
				       shipping_address,
				       total_amount,
				       payment_method,
				       payment_status,
				       order_status,
				       created_at
				FROM orders
				WHERE user_id = ?
				  AND order_status = ?
				ORDER BY created_at DESC
				""";

		try (Connection conn = DBConnection_24162080.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			ps.setString(2, status);

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {

					Order_24162080 order = new Order_24162080();

					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setRecipientName(rs.getString("recipient_name"));
					order.setPhone(rs.getString("phone"));
					order.setShippingAddress(rs.getString("shipping_address"));
					order.setTotalAmount(rs.getBigDecimal("total_amount"));
					order.setPaymentMethod(rs.getString("payment_method"));
					order.setPaymentStatus(rs.getString("payment_status"));
					order.setOrderStatus(rs.getString("order_status"));
					order.setCreatedAt(rs.getTimestamp("created_at"));

					orders.add(order);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
	}
}