package vn.iostar.service.impl;

import vn.iostar.dao.IAuthorDAO_24162080;
import vn.iostar.dao.impl.AuthorDAO_24162080;
import vn.iostar.model.Author_24162080;
import vn.iostar.service.IAuthorService_24162080;

import java.util.List;

public class AuthorService_24162080 implements IAuthorService_24162080 {

	private final IAuthorDAO_24162080 authorDAO = new AuthorDAO_24162080();

	@Override
	public List<Author_24162080> findAll() {
		return authorDAO.findAll();
	}

	@Override
	public Author_24162080 findById(int id) {
		return authorDAO.findById(id);
	}
}