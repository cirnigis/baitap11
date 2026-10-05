package vn.iostar.service;

import vn.iostar.model.User_24162080;

public interface IUserService_24162080 {

	User_24162080 login(String email, String passwd);

	boolean existsByEmail(String email);

	boolean register(User_24162080 user);
}