package vn.iostar.service.impl;

import vn.iostar.dao.IUserDAO_24162080;
import vn.iostar.dao.impl.UserDAO_24162080;
import vn.iostar.model.User_24162080;
import vn.iostar.service.IUserService_24162080;

public class UserService_24162080 implements IUserService_24162080 {

	private final IUserDAO_24162080 userDAO = new UserDAO_24162080();

	@Override
	public User_24162080 login(String email, String passwd) {

		User_24162080 user = userDAO.findByEmailAndPassword(email, passwd);

		if (user != null) {
			userDAO.updateLastLogin(user.getId());
		}

		return user;
	}

	@Override
	public boolean existsByEmail(String email) {
		return userDAO.existsByEmail(email);
	}

	@Override
	public boolean register(User_24162080 user) {
		return userDAO.register(user);
	}
}