package vn.iostar.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.Order_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.service.IOrderService_24162080;
import vn.iostar.service.impl.OrderService_24162080;

@WebServlet("/admin/orders")
public class AdminOrderController_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

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

		if (!user.isAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		List<Order_24162080> orders = orderService.getAllOrders();

		request.setAttribute("orders", orders);

		request.getRequestDispatcher("/WEB-INF/views/admin-order-list.jsp").forward(request, response);
	}
}