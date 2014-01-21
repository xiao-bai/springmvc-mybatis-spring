package xiaobai.mybatis.dao;

import xiaobai.mybatis.model.Authority;

public interface AuthorityMapper {

    int deleteByPrimaryKey(String id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

}