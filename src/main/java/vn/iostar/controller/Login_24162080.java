package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.User_24162080;
import vn.iostar.service.IUserService_24162080;
import vn.iostar.service.impl.UserService_24162080;

@WebServlet("/login")
public class Login_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IUserService_24162080 userService = new UserService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");

		if (email == null || email.isBlank() || passwd == null || passwd.isBlank()) {

			request.setAttribute("error", "Email and password are required.");

			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

			return;
		}

		User_24162080 user = userService.login(email.trim(), passwd);

		if (user == null) {

			request.setAttribute("error", "Invalid email or password.");

			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

			return;
		}

		HttpSession session = request.getSession();

		// Store logged-in user in session
		session.setAttribute("user", user);

		if (user.isAdmin()) {

			response.sendRedirect(request.getContextPath() + "/admin/books");

		} else {

			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}