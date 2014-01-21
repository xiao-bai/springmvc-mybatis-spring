package xiaobai.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaobai.mybatis.dao.UserMapper;
import xiaobai.mybatis.model.User;
import xiaobai.mybatis.service.UserService;

import com.alibaba.druid.filter.AutoLoad;


@Service("userService")
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public User getUserById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public int getUserCount() {
		return userMapper.getUserCount();
	}

	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
