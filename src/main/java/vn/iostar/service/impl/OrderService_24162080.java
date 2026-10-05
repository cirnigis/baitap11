package vn.iostar.service.impl;

import java.math.BigDecimal;
import java.util.List;

import vn.iostar.dao.IOrderDAO_24162080;
import vn.iostar.dao.impl.OrderDAO_24162080;
import vn.iostar.model.CartItem_24162080;
import vn.iostar.model.OrderDetail_24162080;
import vn.iostar.model.Order_24162080;
import vn.iostar.service.IOrderService_24162080;

public class OrderService_24162080 implements IOrderService_24162080 {

	private final IOrderDAO_24162080 orderDAO = new OrderDAO_24162080();

	@Override
	public int createOrder(Order_24162080 order, List<CartItem_24162080> cartItems) {

		if (order == null || cartItems == null || cartItems.isEmpty()) {
			return -1;
		}

		if (order.getRecipientName() == null || order.getRecipientName().trim().isEmpty()) {
			return -1;
		}

		if (order.getPhone() == null || order.getPhone().trim().isEmpty()) {
			return -1;
		}

		if (order.getShippingAddress() == null || order.getShippingAddress().trim().isEmpty()) {
			return -1;
		}

		BigDecimal total = BigDecimal.ZERO;

		for (CartItem_24162080 item : cartItems) {

			if (item.getQuantity() <= 0 || item.getQuantity() > item.getStock()) {
				return -1;
			}

			BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

			total = total.add(subtotal);
		}

		order.setTotalAmount(total);

		order.setPaymentMethod("COD");
		order.setPaymentStatus("UNPAID");
		order.setOrderStatus("NEW");

		return orderDAO.createOrder(order, cartItems);
	}

	@Override
	public List<Order_24162080> getOrdersByUserId(int userId) {
		return orderDAO.findByUserId(userId);
	}

	@Override
	public Order_24162080 getOrderById(int orderId, int userId) {
		return orderDAO.findById(orderId, userId);
	}

	@Override
	public List<OrderDetail_24162080> getOrderDetails(int orderId) {
		return orderDAO.findDetailsByOrderId(orderId);
	}

	@Override
	public List<Order_24162080> getAllOrders() {
		return orderDAO.findAll();
	}

	@Override
	public boolean updateOrderStatus(int orderId, String status) {

		if (orderId <= 0 || status == null || status.isBlank()) {
			return false;
		}

		Order_24162080 order = orderDAO.findByIdForAdmin(orderId);

		if (order == null) {
			return false;
		}

		String currentStatus = order.getOrderStatus();

		if (!isValidStatus(status)) {
			return false;
		}

		if (!isValidTransition(currentStatus, status)) {
			return false;
		}

		return orderDAO.updateStatus(orderId, status);
	}

	private boolean isValidStatus(String status) {

		return status.equals("NEW") || status.equals("CONFIRMED") || status.equals("DELIVERING")
				|| status.equals("DELIVERED") || status.equals("CANCELED") || status.equals("RETURN_REFUND");
	}

	private boolean isValidTransition(String currentStatus, String newStatus) {

		if (currentStatus.equals("NEW")) {

			return newStatus.equals("CONFIRMED") || newStatus.equals("CANCELED");
		}

		if (currentStatus.equals("CONFIRMED")) {

			return newStatus.equals("DELIVERING") || newStatus.equals("CANCELED");
		}

		if (currentStatus.equals("DELIVERING")) {

			return newStatus.equals("DELIVERED");
		}

		if (currentStatus.equals("DELIVERED")) {

			return newStatus.equals("RETURN_REFUND");
		}

		return false;
	}

	@Override
	public List<Order_24162080> getOrdersByUserIdAndStatus(int userId, String status) {

		if (userId <= 0 || status == null || status.isBlank()) {
			return List.of();
		}

		switch (status) {
		case "NEW":
		case "CONFIRMED":
		case "DELIVERING":
		case "DELIVERED":
		case "CANCELED":
		case "RETURN_REFUND":
			return orderDAO.findByUserIdAndStatus(userId, status);

		default:
			return List.of();
		}
	}
}