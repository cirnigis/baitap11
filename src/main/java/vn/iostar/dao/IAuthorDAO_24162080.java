package vn.iostar.dao;

import vn.iostar.model.Author_24162080;

import java.util.List;

public interface IAuthorDAO_24162080 {

	List<Author_24162080> findAll();

	Author_24162080 findById(int id);
}