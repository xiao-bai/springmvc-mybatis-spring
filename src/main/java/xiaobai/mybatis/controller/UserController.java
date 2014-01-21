package xiaobai.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xiaobai.mybatis.model.User;
import xiaobai.mybatis.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	private UserService userService;
	
	@RequestMapping("/showUser")
	public String showUser(String id , HttpServletRequest request){
		User u = userService.getUserById(id);
		request.setAttribute("user", u);
		return "showUser";
	}
	
	@RequestMapping("/showUser/{id}")
	public String showUser2(@PathVariable String id , HttpServletRequest request){
		User u = userService.getUserById(id);
		request.setAttribute("user", u);
		return "showUser";
	}

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
