package xiaobai.mybatis.dao;


import xiaobai.mybatis.model.Role;

public interface RoleMapper {

    int deleteByPrimaryKey(String roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	Role selectById(String id);

	Role getManyToOne(String id);
    
}