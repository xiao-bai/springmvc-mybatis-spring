package xiaobai.mybatis.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xiaobai.mybatis.model.Authority;
import xiaobai.mybatis.model.Role;
import xiaobai.mybatis.service.AuthorityService;

@Controller
@RequestMapping("/authorityController")
public class AuthorityController {
	private AuthorityService authorityService;

	/**
	 * http://localhost:8080/springmvc-mybatis-spring/authorityController/getAuthorityById/1.do
	 */
	@RequestMapping("/getAuthorityById/{id}")
	public String getAuthorityById(@PathVariable String id, HttpServletRequest request) {
		Authority authority =authorityService.getAuthorityById(id);
		request.setAttribute("authority", authority);
		return "showAuthority";
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	@Autowired
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

}
