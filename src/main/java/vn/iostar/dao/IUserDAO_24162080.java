package vn.iostar.dao;

import vn.iostar.model.User_24162080;

public interface IUserDAO_24162080 {

	User_24162080 findByEmailAndPassword(String email, String passwd);

	boolean updateLastLogin(int userId);

	boolean existsByEmail(String email);

	boolean register(User_24162080 user);
}