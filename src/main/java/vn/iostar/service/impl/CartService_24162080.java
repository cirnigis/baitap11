package vn.iostar.service.impl;

import java.util.List;

import vn.iostar.dao.ICartDAO_24162080;
import vn.iostar.dao.impl.CartDAO_24162080;
import vn.iostar.model.CartItem_24162080;
import vn.iostar.service.ICartService_24162080;

public class CartService_24162080 implements ICartService_24162080 {

	private final ICartDAO_24162080 cartDAO = new CartDAO_24162080();

	@Override
	public List<CartItem_24162080> findByUserId(int userId) {
		return cartDAO.findByUserId(userId);
	}

	@Override
	public boolean add(int userId, int bookId, int quantity) {

		if (quantity <= 0) {
			return false;
		}

		CartItem_24162080 existing = cartDAO.findByUserIdAndBookId(userId, bookId);

		if (existing != null) {
			return cartDAO.updateQuantity(existing.getCartId(), userId, existing.getQuantity() + quantity);
		}

		return cartDAO.add(userId, bookId, quantity);
	}

	@Override
	public boolean updateQuantity(int cartId, int userId, int quantity) {

		if (quantity <= 0) {
			return cartDAO.delete(cartId, userId);
		}

		return cartDAO.updateQuantity(cartId, userId, quantity);
	}

	@Override
	public boolean delete(int cartId, int userId) {
		return cartDAO.delete(cartId, userId);
	}

	@Override
	public boolean clear(int userId) {
		return cartDAO.clear(userId);
	}
}