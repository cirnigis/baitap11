package vn.iostar.dao;

import java.util.List;

import vn.iostar.model.CartItem_24162080;

public interface ICartDAO_24162080 {

	List<CartItem_24162080> findByUserId(int userId);

	CartItem_24162080 findByUserIdAndBookId(int userId, int bookId);

	boolean add(int userId, int bookId, int quantity);

	boolean updateQuantity(int cartId, int userId, int quantity);

	boolean delete(int cartId, int userId);

	boolean clear(int userId);
}