package xiaobai.mybatis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotWordController")
public class HotWordController {
	@RequestMapping("/find/{word}")
	public String find(@PathVariable String word, HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		int length = word.length();
		String name1 = "我叫小白";
		String name2 = "我爱打篮球";
		String name3 = "我喜欢跑步";
		list.add(name1.substring(length));
		list.add(name2.substring(length));
		list.add(name3.substring(length));
		request.setAttribute("words",list);
		request.setAttribute("prefix",word);
		return "page/json/hotword-json";
	}
}
