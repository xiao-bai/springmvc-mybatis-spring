package xiaobai.search.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUtil {
	static String relativelyPath = System.getProperty("user.dir");

	public void createTxt() {
		Date date = new Date();
		int year = (date.getYear() + 1900);
		int month = (date.getMonth() + 1);
		int day = (date.getDate());
		String path = relativelyPath + "\\" + year + "-" + month;
		String name = "\\" + day + ".txt";
		File file = new File(path);
		File filename = new File(path + name);
		try {
			if (!filename.exists()) {
				if (!file.exists()) {
					file.mkdirs();
				}

				filename.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
