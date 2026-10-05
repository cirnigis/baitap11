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
import vn.iostar.model.OrderDetail_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.service.IOrderService_24162080;
import vn.iostar.service.impl.OrderService_24162080;

@WebServlet("/order-detail")
public class OrderDetailController_24162080 extends HttpServlet {

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

		String idParam = request.getParameter("id");

		if (idParam == null || idParam.isBlank()) {
			response.sendRedirect(request.getContextPath() + "/orders");
			return;
		}

		int orderId;

		try {
			orderId = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/orders");
			return;
		}

		User_24162080 user = (User_24162080) session.getAttribute("user");

		Order_24162080 order = orderService.getOrderById(orderId, user.getId());

		if (order == null) {
			response.sendRedirect(request.getContextPath() + "/orders");
			return;
		}

		List<OrderDetail_24162080> details = orderService.getOrderDetails(orderId);

		request.setAttribute("order", order);
		request.setAttribute("orderDetails", details);

		request.getRequestDispatcher("/WEB-INF/views/order-detail.jsp").forward(request, response);
	}
}