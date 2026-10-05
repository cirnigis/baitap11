package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.User_24162080;

@WebServlet("/order-success")
public class OrderSuccess_24162080 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String id = request.getParameter("id");

		if (id == null || id.trim().isEmpty()) {

			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		try {

			int orderId = Integer.parseInt(id);

			request.setAttribute("orderId", orderId);

			request.getRequestDispatcher("/WEB-INF/views/order-success.jsp").forward(request, response);

		} catch (NumberFormatException e) {

			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}