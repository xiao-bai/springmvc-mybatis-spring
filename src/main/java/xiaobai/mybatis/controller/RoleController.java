package xiaobai.mybatis.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xiaobai.mybatis.model.Role;
import xiaobai.mybatis.service.RoleService;

@Controller
@RequestMapping("/roleController")
public class RoleController {
	private RoleService roleService;

	/**
	 *http://localhost:8080/springmvc-mybatis-spring/roleController/showRole/1.do 
	 */
	@RequestMapping("/showRole/{id}")
	public String showRole(@PathVariable String id, HttpServletRequest request) {
		Role r = roleService.getRoelById(id);
		request.setAttribute("role", r);
		return "showRole";
	}
	
	@RequestMapping("/roleAndUser/{id}")
	public String showRoleAndUser(@PathVariable String id, HttpServletRequest request){
		Role r = roleService.getRoel(id);
		request.setAttribute("role", r);
		return "roleAndUser";
	}
	
	/**
	 * 
	 * http://localhost:8080/springmvc-mybatis-spring/roleController/getManyToOne/1.do 
	 */
	@RequestMapping("/getManyToOne/{id}")
	public String getManyToOne(@PathVariable String id, HttpServletRequest request){
		Role r = roleService.getManyToOne(id);
		request.setAttribute("role", r);
		return "getManyToOne";
	}


	public RoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
