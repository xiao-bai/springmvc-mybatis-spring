package xiaobai.search.test;

import org.junit.Test;

import xiaobai.mybatis.model.FileInfo;
import xiaobai.search.util.IndexUtil;



public class TestIndex {

	@Test
	public void testIndex(){
		IndexUtil index = new IndexUtil();
		FileInfo f = new FileInfo();
		f.setUsername("xiaobai");
		f.setFileName("文件名");
		f.setDescription("这本书没有多少描述，这是主人太懒了");
		f.setKeyword("关键");
		f.setClassname("科学教育");
		index.createIndex(f);
	}
	
}
