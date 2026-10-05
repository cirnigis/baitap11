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

@WebServlet("/verify-otp")
public class VerifyRegisterOTP_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IUserService_24162080 userService = new UserService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/register");
			return;
		}

		String inputOtp = request.getParameter("otp");

		String sessionOtp = (String) session.getAttribute("registerOtp");

		Long expireTime = (Long) session.getAttribute("registerOtpExpire");

		if (inputOtp == null || inputOtp.isBlank() || sessionOtp == null || expireTime == null) {

			request.setAttribute("error", "Invalid OTP.");

			request.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(request, response);

			return;
		}

		if (System.currentTimeMillis() > expireTime) {

			request.setAttribute("error", "OTP has expired. Please register again.");

			request.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(request, response);

			return;
		}

		if (!inputOtp.trim().equals(sessionOtp)) {

			request.setAttribute("error", "Incorrect OTP.");

			request.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(request, response);

			return;
		}

		String email = (String) session.getAttribute("registerEmail");

		String fullname = (String) session.getAttribute("registerFullname");

		Integer phone = (Integer) session.getAttribute("registerPhone");

		String passwd = (String) session.getAttribute("registerPasswd");

		if (email == null || fullname == null || phone == null || passwd == null) {

			request.setAttribute("error", "Registration information is missing.");

			request.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(request, response);

			return;
		}

		User_24162080 user = new User_24162080();

		user.setEmail(email);
		user.setFullname(fullname);
		user.setPhone(phone);
		user.setPasswd(passwd);

		boolean success = userService.register(user);

		if (success) {

			session.removeAttribute("registerEmail");
			session.removeAttribute("registerFullname");
			session.removeAttribute("registerPhone");
			session.removeAttribute("registerPasswd");
			session.removeAttribute("registerOtp");
			session.removeAttribute("registerOtpExpire");

			response.sendRedirect(request.getContextPath() + "/register-success");

		} else {

			request.setAttribute("error", "Registration failed.");

			request.getRequestDispatcher("/WEB-INF/views/verify-otp.jsp").forward(request, response);
		}
	}
}