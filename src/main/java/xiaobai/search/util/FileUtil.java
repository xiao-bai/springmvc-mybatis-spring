package xiaobai.search.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 这个是对搜索时候的热词进行操作的
 * 这里针对每次的搜索都保存到txt中
 * 里面包含对文件的读写操作
 * 写操作时添加了同步锁的
 * @author xiaobai
 *
 */
public enum FileUtil {
	INSTANCE;
	public static FileUtil getInstance(){  
        return INSTANCE;  
    } 
	static String relativelyPath = System.getProperty("user.dir");
	static String name = null;
	static String path = null;
	static {
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		int day = date.getDate();
		int hour = date.getHours();
		path = relativelyPath + "\\" + year + "-" + month+"\\";
		name = day+"-"+hour + ".txt";
	}

	/**
	 * 返回创建文件的名称
	 * 
	 * @author xiaobai
	 */
	public String getTxtName() {
		return name;
	}

	/**
	 * 返回创建文件的路径
	 * 
	 * @author xiaobai
	 */
	public String getTxtPath() {
		return path;
	}
	/**
	 * 用来txt保存的文件夹
	 */
	public boolean createTxt(){
		File filename = new File(path);
		File file = new File(path+name);
		if (!filename.exists()) {
			filename.mkdirs();
			if(!file.exists()){
				try {
					file.createNewFile();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}


	/**
	 * 
	 * @param path 这个代表文件的路径
	 * @param name 这个是文件名(123.txt)
	 * @param str	这是搜索的关键字，如果有多个空格，进行合并为一个，并在最后添加上\n进行换行
	 * @author xiaobai
	 */
	public void writeByRandomAccess(String str) {
		// 为了以可读可写的方式打开文件，这里使用RandomAccessFile来创建文件。
		// rws,s代表同步锁
		FileChannel fc = null;
		str = str.replaceAll("( )+"," ");
		str = str + "\r\n";
		try {
			System.out.println(path+name);
			fc = new RandomAccessFile(path + name, "rws").getChannel();
			byte[] bm;
			
			bm = str.getBytes();
			MappedByteBuffer out = null;
			out = fc.map(FileChannel.MapMode.READ_WRITE, (int) fc.size(),bm.length);
			for (int i = 0; i < bm.length; i++) {
				out.put(bm[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public List<String> readTxt(){
		InputStreamReader read = null;
		List<String> list = new ArrayList<String>();
		File file=new File(path+name);
		if(file.isFile() && file.exists()){ //判断文件是否存在
			try {
				read = new InputStreamReader(
				new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
		        String lineTxt = null;
		        while((lineTxt = bufferedReader.readLine()) != null){
		        	list.add(lineTxt);
		        }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
	        try {
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
