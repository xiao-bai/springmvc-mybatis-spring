package xiaobai.mybatis.service;

import xiaobai.mybatis.model.Role;

public interface RoleService {
	public Role getRoelById(String id);

	public Role getRoel(String id);

	public Role getManyToOne(String id);
}
