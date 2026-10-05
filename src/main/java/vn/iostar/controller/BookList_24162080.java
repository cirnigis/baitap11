package vn.iostar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.model.Book_24162080;
import vn.iostar.service.IBookService_24162080;
import vn.iostar.service.impl.BookService_24162080;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/books")
public class BookList_24162080 extends HttpServlet {

	private final IBookService_24162080 bookService = new BookService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		int pageSize = 10;

		String pageParam = request.getParameter("page");

		if (pageParam != null) {
			try {
				page = Integer.parseInt(pageParam);

				if (page < 1) {
					page = 1;
				}

			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		int totalBooks = bookService.count();

		int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

		if (totalPages > 0 && page > totalPages) {
			page = totalPages;
		}

		List<Book_24162080> books = bookService.findAll(page, pageSize);

		request.setAttribute("books", books);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		request.getRequestDispatcher("/WEB-INF/views/admin/books.jsp").forward(request, response);
	}
}