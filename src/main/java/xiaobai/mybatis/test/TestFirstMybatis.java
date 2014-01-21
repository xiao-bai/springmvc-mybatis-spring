package xiaobai.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xiaobai.mybatis.model.User;

public class TestFirstMybatis {
	public static void main(String[] args) {
		//1、创建配置文件mybatis-config.xml的输入流
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			//2、创建SQLSessionFactory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			//3、创建SQLSession
			SqlSession session = factory.openSession();
			//4、调用mapper文件插入数据(调用之前需要将mapper文件加入到mybatis-config.xml中)
			User u = new User();
			u.setNickname("孙悟空");
			u.setPassword("123");
			u.setType("1");
			u.setUsername("wukong");
			session.insert("xiaobai.mybatis.model.User.add",u);
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
