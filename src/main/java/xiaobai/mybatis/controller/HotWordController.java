package xiaobai.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotWordController")
public class HotWordController {
	@RequestMapping("/find/{word}")
	public String find(@PathVariable String word, HttpServletRequest request){
		System.out.println(word);
		List<String> list = new ArrayList<String>();
		list.add("小白");
	
		request.setAttribute("words",list);
		return "page/json/hotword-json";
	}
}
