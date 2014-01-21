package xiaobai.mybatis.dao;

import java.util.List;

import xiaobai.mybatis.model.Role;
import xiaobai.mybatis.model.User;

public interface UserMapper {

	int deleteByPrimaryKey(String username);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String username);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	int getUserCount();

	List<User> getUserList();
}