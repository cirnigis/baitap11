package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iostar.model.Book_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.service.IBookService_24162080;
import vn.iostar.service.ICartService_24162080;
import vn.iostar.service.impl.BookService_24162080;
import vn.iostar.service.impl.CartService_24162080;

@WebServlet("/cart/add")
public class CartAdd_24162080 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ICartService_24162080 cartService = new CartService_24162080();

	private final IBookService_24162080 bookService = new BookService_24162080();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("user") == null) {

			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		User_24162080 user = (User_24162080) session.getAttribute("user");

		try {

			int bookId = Integer.parseInt(request.getParameter("bookId"));

			int quantity = Integer.parseInt(request.getParameter("quantity"));

			if (quantity <= 0) {
				response.sendRedirect(request.getContextPath() + "/book-detail?id=" + bookId);
				return;
			}

			Book_24162080 book = bookService.findById(bookId);

			if (book == null || book.getQuantity() <= 0) {
				response.sendRedirect(request.getContextPath() + "/book-detail?id=" + bookId);
				return;
			}

			if (quantity > book.getQuantity()) {
				response.sendRedirect(request.getContextPath() + "/book-detail?id=" + bookId + "&error=quantity");
				return;
			}

			boolean success = cartService.add(user.getId(), bookId, quantity);

			if (success) {
				response.sendRedirect(request.getContextPath() + "/cart");
			} else {
				response.sendRedirect(request.getContextPath() + "/book-detail?id=" + bookId);
			}

		} catch (NumberFormatException e) {

			response.sendRedirect(request.getContextPath() + "/home");
		}
	}
}