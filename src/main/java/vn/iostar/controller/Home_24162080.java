package vn.iostar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iostar.model.Author_24162080;
import vn.iostar.model.Book_24162080;
import vn.iostar.service.IAuthorService_24162080;
import vn.iostar.service.IBookService_24162080;
import vn.iostar.service.impl.AuthorService_24162080;
import vn.iostar.service.impl.BookService_24162080;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class Home_24162080 extends HttpServlet {

	private final IBookService_24162080 bookService = new BookService_24162080();

	private final IAuthorService_24162080 authorService = new AuthorService_24162080();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		int pageSize = 3;

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

		List<Author_24162080> authors = authorService.findAll();

		if (authors.isEmpty()) {

			request.setAttribute("authors", authors);

			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);

			return;
		}

		int authorId = authors.get(0).getAuthorId();

		String authorParam = request.getParameter("authorId");

		if (authorParam != null) {
			try {
				authorId = Integer.parseInt(authorParam);
			} catch (NumberFormatException e) {
				authorId = authors.get(0).getAuthorId();
			}
		}

		Author_24162080 selectedAuthor = authorService.findById(authorId);

		int totalBooks = bookService.countByAuthor(authorId);

		int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

		if (totalPages > 0 && page > totalPages) {
			page = totalPages;
		}

		List<Book_24162080> books = bookService.findByAuthor(authorId, page, pageSize);

		request.setAttribute("authors", authors);
		request.setAttribute("selectedAuthor", selectedAuthor);
		request.setAttribute("books", books);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}
}