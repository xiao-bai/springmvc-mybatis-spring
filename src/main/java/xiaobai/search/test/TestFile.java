package xiaobai.search.test;

import java.util.List;

import org.junit.Test;

import xiaobai.search.util.FileUtil;


public class TestFile {
	@Test
	public void insert(){
		FileUtil fileutil = FileUtil.getInstance();
		String str = "my name is xiaobai";
		fileutil.writeByRandomAccess(str);
	}
	@Test
	public void read(){
		FileUtil fileutil = FileUtil.getInstance();
		List<String> list =fileutil.readTxt();
		for(String s : list){
			System.out.println(s.toString());
		}
	}
	
}	
