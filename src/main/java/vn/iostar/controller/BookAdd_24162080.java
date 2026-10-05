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
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/admin/books/add")
public class BookAdd_24162080 extends HttpServlet {

	private final IBookService_24162080 bookService = new BookService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/admin/book-add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Book_24162080 book = new Book_24162080();

		book.setIsbn(Long.parseLong(request.getParameter("isbn")));
		book.setTitle(request.getParameter("title"));
		book.setPublisher(request.getParameter("publisher"));

		book.setPrice(new BigDecimal(request.getParameter("price")));

		book.setDescription(request.getParameter("description"));

		String publishDate = request.getParameter("publishDate");

		if (publishDate != null && !publishDate.isBlank()) {
			book.setPublishDate(Date.valueOf(publishDate));
		}

		book.setCoverImage(request.getParameter("coverImage"));

		book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

		bookService.insert(book);

		response.sendRedirect(request.getContextPath() + "/admin/books");
	}
}