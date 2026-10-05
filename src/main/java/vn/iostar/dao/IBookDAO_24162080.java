package vn.iostar.dao;

import vn.iostar.model.Book_24162080;

import java.util.List;

public interface IBookDAO_24162080 {

	List<Book_24162080> findAll(int page, int pageSize);

	int count();

	Book_24162080 findById(int id);

	boolean insert(Book_24162080 book);

	boolean update(Book_24162080 book);

	boolean delete(int id);

	List<Book_24162080> findByAuthor(int authorId, int page, int pageSize);

	int countByAuthor(int authorId);
}