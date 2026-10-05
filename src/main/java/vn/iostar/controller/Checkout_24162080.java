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
import vn.iostar.service.IOrderService_24162080;
import vn.iostar.service.impl.CartService_24162080;
import vn.iostar.service.impl.OrderService_24162080;
import vn.iostar.model.Order_24162080;

@WebServlet("/checkout")
public class Checkout_24162080 extends HttpServlet {

	private final ICartService_24162080 cartService = new CartService_24162080();

	private final IOrderService_24162080 orderService = new OrderService_24162080();

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

		if (cartItems == null || cartItems.isEmpty()) {

			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}

		request.setAttribute("cartItems", cartItems);

		request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User_24162080 user = (User_24162080) session.getAttribute("user");

		String recipientName = request.getParameter("recipientName");

		String phone = request.getParameter("phone");

		String shippingAddress = request.getParameter("shippingAddress");

		List<CartItem_24162080> cartItems = cartService.findByUserId(user.getId());

		if (cartItems == null || cartItems.isEmpty()) {

			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}

		Order_24162080 order = new Order_24162080();

		order.setUserId(user.getId());
		order.setRecipientName(recipientName);
		order.setPhone(phone);
		order.setShippingAddress(shippingAddress);

		int orderId = orderService.createOrder(order, cartItems);

		if (orderId > 0) {

			response.sendRedirect(request.getContextPath() + "/order-success?id=" + orderId);

		} else {

			request.setAttribute("error", "Unable to create the order. " + "Please check the stock and try again.");

			request.setAttribute("cartItems", cartItems);

			request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
		}
	}
}