package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.Review_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.service.IReviewService_24162080;
import vn.iostar.service.impl.ReviewService_24162080;

@WebServlet("/review-add")
public class ReviewAdd_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IReviewService_24162080 reviewService = new ReviewService_24162080();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String bookIdParam = request.getParameter("bookId");
		String reviewText = request.getParameter("reviewText");

		if (bookIdParam == null || reviewText == null || reviewText.isBlank()) {

			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User_24162080 user = (User_24162080) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try {

			int bookId = Integer.parseInt(bookIdParam);

			Review_24162080 review = new Review_24162080();

			review.setBookId(bookId);
			review.setUserId(user.getId());
			review.setReviewText(reviewText.trim());

			reviewService.insert(review);

			response.sendRedirect(request.getContextPath() + "/book-detail?id=" + bookId);

		} catch (NumberFormatException e) {

			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}