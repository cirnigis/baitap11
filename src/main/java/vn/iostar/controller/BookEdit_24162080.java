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

@WebServlet("/admin/books/edit")
public class BookEdit_24162080 extends HttpServlet {

    private final IBookService_24162080 bookService =
            new BookService_24162080();

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        Book_24162080 book =
                bookService.findById(id);

        if (book == null) {
            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );
            return;
        }

        request.setAttribute("book", book);

        request.getRequestDispatcher(
                "/WEB-INF/views/admin/book-edit.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Book_24162080 book = new Book_24162080();

        book.setBookid(
                Integer.parseInt(request.getParameter("bookid"))
        );

        book.setIsbn(
                Integer.parseInt(request.getParameter("isbn"))
        );

        book.setTitle(
                request.getParameter("title")
        );

        book.setPublisher(
                request.getParameter("publisher")
        );

        book.setPrice(
                new BigDecimal(request.getParameter("price"))
        );

        book.setDescription(
                request.getParameter("description")
        );

        String publishDate =
                request.getParameter("publishDate");

        if (publishDate != null && !publishDate.isBlank()) {
            book.setPublishDate(
                    Date.valueOf(publishDate)
            );
        }

        book.setCoverImage(
                request.getParameter("coverImage")
        );

        book.setQuantity(
                Integer.parseInt(request.getParameter("quantity"))
        );

        bookService.update(book);

        response.sendRedirect(
                request.getContextPath() + "/admin/books"
        );
    }
}