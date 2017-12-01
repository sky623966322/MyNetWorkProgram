package io;

import java.io.*;
import java.util.Date;

/**
 * Title:
 * Description:
 * Create Time: 2017-04-15 14:49
 * author: wangyan
 * version: 1.0
 */
public class MyFileStream {

	private static  final int BUFFER_SIZE = 2048;

	public static void main(String[] args) {
		try {
			File src = new File("E:\\安装包\\adt-bundle-windows-x86_64-20140321.zip");
			long now = System.currentTimeMillis();
			File dest1 = new File("" + now + "_1.zip");
			File dest2 = new File("" + now + "_2.zip");
			testNoBuffer(src, dest1);
			testBuffer(src, dest2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testNoBuffer(File src, File dest) throws IOException {
		Date begin = new Date();
		try (
				FileInputStream inputStream = new FileInputStream(src);
				FileOutputStream outputStream = new FileOutputStream(dest);
		) {
			byte[] buff = new byte[BUFFER_SIZE];
			while (inputStream.read(buff) != -1){
				outputStream.write(buff);
				outputStream.flush();
			}
		}
		System.out.println(new Date().getTime() - begin.getTime());
	}

	private static void testBuffer(File src, File dest) throws IOException{
		Date begin = new Date();
		try (
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(src));
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(dest))
		) {
			int len = 0;
			byte[] buff = new byte[BUFFER_SIZE];
			while ((len = inputStream.read(buff)) != -1){
				//outputStream.write(buff, 0, len);
				outputStream.write(buff);//最终还是调用write(buff, 0, len)
			}
			//outputStream.flush();
		}
		System.out.println(new Date().getTime() - begin.getTime());
	}

}
