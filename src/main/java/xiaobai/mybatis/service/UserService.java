package xiaobai.mybatis.service;

import java.util.List;

import xiaobai.mybatis.model.User;

public interface UserService {
	public User getUserById(String id);

	public int getUserCount();

	public List<User> getUserList();
}
