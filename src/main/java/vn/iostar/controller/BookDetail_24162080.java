package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iostar.model.Book_24162080;
import vn.iostar.service.IBookService_24162080;
import vn.iostar.service.IReviewService_24162080;
import vn.iostar.service.impl.BookService_24162080;
import vn.iostar.service.impl.ReviewService_24162080;

@WebServlet("/book-detail")
public class BookDetail_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final IBookService_24162080 bookService = new BookService_24162080();

	private final IReviewService_24162080 reviewService = new ReviewService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");

		if (idParam == null || idParam.isBlank()) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		try {

			int bookId = Integer.parseInt(idParam);

			Book_24162080 book = bookService.findById(bookId);

			if (book == null) {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			}

			request.setAttribute("book", book);

			request.setAttribute("reviews", reviewService.findByBookId(bookId));

			request.setAttribute("reviewCount", reviewService.countByBookId(bookId));

			request.getRequestDispatcher("/WEB-INF/views/book-detail.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}