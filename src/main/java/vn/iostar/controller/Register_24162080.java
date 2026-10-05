package vn.iostar.controller;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.service.IUserService_24162080;
import vn.iostar.service.impl.UserService_24162080;
import vn.iostar.utils.EmailUtil_24162080;

@WebServlet("/register")
public class Register_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IUserService_24162080 userService = new UserService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String phoneParam = request.getParameter("phone");
		String passwd = request.getParameter("passwd");

		if (email == null || email.isBlank() || fullname == null || fullname.isBlank() || phoneParam == null
				|| phoneParam.isBlank() || passwd == null || passwd.isBlank()) {

			request.setAttribute("error", "Please enter all required information.");

			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);

			return;
		}

		email = email.trim();
		fullname = fullname.trim();
		phoneParam = phoneParam.trim();

		if (userService.existsByEmail(email)) {

			request.setAttribute("error", "Email already exists.");

			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);

			return;
		}

		int phone;

		try {
			phone = Integer.parseInt(phoneParam);
		} catch (NumberFormatException e) {

			request.setAttribute("error", "Phone number must contain only digits.");

			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);

			return;
		}

		// Generate 6-digit OTP
		String otp = String.format("%06d", new Random().nextInt(1000000));

		HttpSession session = request.getSession();

		session.setAttribute("registerEmail", email);
		session.setAttribute("registerFullname", fullname);
		session.setAttribute("registerPhone", phone);
		session.setAttribute("registerPasswd", passwd);

		session.setAttribute("registerOtp", otp);

		// OTP expires after 5 minutes
		long otpExpireTime = System.currentTimeMillis() + (5 * 60 * 1000);

		session.setAttribute("registerOtpExpire", otpExpireTime);

		try {

			EmailUtil_24162080.sendOTP(email, otp);

			response.sendRedirect(request.getContextPath() + "/verify-otp");

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Failed to send OTP email.");

			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
		}
	}
}