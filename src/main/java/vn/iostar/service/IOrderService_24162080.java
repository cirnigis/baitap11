package vn.iostar.service;

import java.util.List;

import vn.iostar.model.CartItem_24162080;
import vn.iostar.model.OrderDetail_24162080;
import vn.iostar.model.Order_24162080;

public interface IOrderService_24162080 {

	int createOrder(Order_24162080 order, List<CartItem_24162080> cartItems);

	List<Order_24162080> getOrdersByUserId(int userId);

	List<Order_24162080> getOrdersByUserIdAndStatus(int userId, String status);

	Order_24162080 getOrderById(int orderId, int userId);

	List<OrderDetail_24162080> getOrderDetails(int orderId);

	List<Order_24162080> getAllOrders();

	boolean updateOrderStatus(int orderId, String status);
}