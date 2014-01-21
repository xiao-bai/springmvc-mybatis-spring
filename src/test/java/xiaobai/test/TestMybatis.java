package xiaobai.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import xiaobai.mybatis.model.User;
import xiaobai.mybatis.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:mybatis-spring.xml"})
public class TestMybatis {
		
		private static final Logger logger = Logger.getLogger(TestMybatis.class);
		
		private UserService userService;

		
		@Test
		public void test(){
			User u = userService.getUserById("2");
			System.out.println(u);		//这样是无法显示对象的内容的
			logger.info(u);				//这个也无法显示对象中的属性
			logger.info(JSON.toJSON(u));
		}
		
		@Test
		public void testUserCount(){
			int num = userService.getUserCount();
			logger.info(num);
		}
		
		@Test
		public void testUserList(){
			List<User> list = userService.getUserList();
			logger.info(list.size());
			logger.info(JSON.toJSON(list));
		}
		
		
		public UserService getUserService() {
			return userService;
		}

		@Autowired
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		public static Logger getLogger() {
			return logger;
		}
		
		
		
		
		
		
}
