package xiaobai.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaobai.mybatis.dao.AuthorityMapper;
import xiaobai.mybatis.model.Authority;
import xiaobai.mybatis.service.AuthorityService;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	private AuthorityMapper authorityMapper;

	public AuthorityMapper getAuthorityMapper() {
		return authorityMapper;
	}

	@Autowired
	public void setAuthorityMapper(AuthorityMapper authorityMapper) {
		this.authorityMapper = authorityMapper;
	}

	public Authority getAuthorityById(String id) {
		return authorityMapper.selectByPrimaryKey(id);
	}

}
