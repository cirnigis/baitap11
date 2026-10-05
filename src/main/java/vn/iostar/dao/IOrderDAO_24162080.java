package vn.iostar.dao;

import java.util.List;

import vn.iostar.model.CartItem_24162080;
import vn.iostar.model.OrderDetail_24162080;
import vn.iostar.model.Order_24162080;

public interface IOrderDAO_24162080 {

	int createOrder(Order_24162080 order, List<CartItem_24162080> cartItems);

	List<Order_24162080> findByUserId(int userId);

	List<Order_24162080> findByUserIdAndStatus(int userId, String status);

	Order_24162080 findById(int orderId, int userId);

	List<OrderDetail_24162080> findDetailsByOrderId(int orderId);

	List<Order_24162080> findAll();

	Order_24162080 findByIdForAdmin(int orderId);

	boolean updateStatus(int orderId, String status);
}