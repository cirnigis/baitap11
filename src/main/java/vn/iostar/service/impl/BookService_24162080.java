package vn.iostar.service.impl;

import vn.iostar.dao.IBookDAO_24162080;
import vn.iostar.dao.impl.BookDAO_24162080;
import vn.iostar.model.Book_24162080;
import vn.iostar.service.IBookService_24162080;

import java.util.List;

public class BookService_24162080 implements IBookService_24162080 {

	private final IBookDAO_24162080 bookDAO = new BookDAO_24162080();

	@Override
	public List<Book_24162080> findAll(int page, int pageSize) {
		return bookDAO.findAll(page, pageSize);
	}

	@Override
	public int count() {
		return bookDAO.count();
	}

	@Override
	public Book_24162080 findById(int id) {
		return bookDAO.findById(id);
	}

	@Override
	public boolean insert(Book_24162080 book) {
		return bookDAO.insert(book);
	}

	@Override
	public boolean update(Book_24162080 book) {
		return bookDAO.update(book);
	}

	@Override
	public boolean delete(int id) {
		return bookDAO.delete(id);
	}

	@Override
	public List<Book_24162080> findByAuthor(int authorId, int page, int pageSize) {
		return bookDAO.findByAuthor(authorId, page, pageSize);
	}

	@Override
	public int countByAuthor(int authorId) {
		return bookDAO.countByAuthor(authorId);
	}
}