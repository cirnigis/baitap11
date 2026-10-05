package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.User_24162080;
import vn.iostar.service.ICartService_24162080;
import vn.iostar.service.impl.CartService_24162080;

@WebServlet("/cart/update")
public class CartUpdate_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ICartService_24162080 cartService = new CartService_24162080();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User_24162080 user = (User_24162080) session.getAttribute("user");

		try {

			int cartId = Integer.parseInt(request.getParameter("cartId"));

			int quantity = Integer.parseInt(request.getParameter("quantity"));

			cartService.updateQuantity(cartId, user.getId(), quantity);

		} catch (NumberFormatException e) {
			// Ignore invalid input
		}

		response.sendRedirect(request.getContextPath() + "/cart");
	}
}