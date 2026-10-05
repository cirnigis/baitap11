package vn.iostar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.CartItem_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.service.ICartService_24162080;
import vn.iostar.service.impl.CartService_24162080;

@WebServlet("/cart")
public class Cart_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ICartService_24162080 cartService = new CartService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User_24162080 user = (User_24162080) session.getAttribute("user");

		List<CartItem_24162080> cartItems = cartService.findByUserId(user.getId());

		request.setAttribute("cartItems", cartItems);

		request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
	}
}