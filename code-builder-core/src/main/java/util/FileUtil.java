package util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类，创建文件
 * @author qhluo
 * @since 2016-05-17
 *
 */

public class FileUtil {

	public static void create(String dir, String fileName, String content) throws IOException {
		File file = new File(dir);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		File f = new File(file.getAbsolutePath() + "/" + fileName);
		create(f, content.getBytes());
	}

	private static void create(File file, byte[] content) throws IOException {
		FileOutputStream out = null;
		BufferedOutputStream bos = null;
		try {
			out = new FileOutputStream(file);
			bos = new BufferedOutputStream(out);
			bos.write(content);
			bos.flush();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}

	}

}
