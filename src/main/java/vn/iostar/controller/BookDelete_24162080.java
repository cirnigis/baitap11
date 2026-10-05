package vn.iostar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.service.IBookService_24162080;
import vn.iostar.service.impl.BookService_24162080;

import java.io.IOException;

@WebServlet("/admin/books/delete")
public class BookDelete_24162080 extends HttpServlet {

	private final IBookService_24162080 bookService = new BookService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");

		if (idParam == null || idParam.isBlank()) {
			response.sendRedirect(request.getContextPath() + "/admin/books");
			return;
		}

		int id = Integer.parseInt(idParam);

		bookService.delete(id);

		response.sendRedirect(request.getContextPath() + "/admin/books");
	}
}