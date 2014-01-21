package xiaobai.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xiaobai.mybatis.model.Role;
import xiaobai.mybatis.dao.RoleMapper;
import xiaobai.mybatis.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	private RoleMapper roleMapper;

	public Role getRoelById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public int getRoleCount() {
		return 0;
	}

	public List<Role> getRoleList() {
		return null;
	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	@Autowired
	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	public Role getRoel(String id) {
		return roleMapper.selectById(id);
	}

	public Role getManyToOne(String id) {
		return roleMapper.getManyToOne(id);
	}

}
