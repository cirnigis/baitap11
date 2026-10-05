package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.User_24162080;
import vn.iostar.service.IOrderService_24162080;
import vn.iostar.service.impl.OrderService_24162080;

@WebServlet("/admin/orders/status")
public class AdminOrderStatusController_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IOrderService_24162080 orderService = new OrderService_24162080();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

		String orderIdParam = request.getParameter("orderId");
		String status = request.getParameter("status");

		try {

			int orderId = Integer.parseInt(orderIdParam);

			orderService.updateOrderStatus(orderId, status);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/admin/orders");
	}
}