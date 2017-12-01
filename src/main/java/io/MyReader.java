package io;

import com.duowan.leopard.commons.utility.EncryptUtil;

import java.io.*;
import java.util.Date;

/**
 * Title:
 * Description:
 * Create Time: 2017-04-15 17:12
 * author: wangyan
 * version: 1.0
 */
public class MyReader {

	public static void main(String[] args) {
		try {
			String fileName = "G:\\third_imei.txt";
			File src = new File(fileName);
			/*String descFileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.indexOf("."))
									  + "_copy_" + new Date().getTime()
									  + fileName.substring(fileName.indexOf(".") + 1);*/
			String descFileName = "third_imei_md5.txt";
			File dest = new File(descFileName);
			//read(src, dest);
			read2(src, dest);//BufferedWriter可以使用追加模式
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void read(File src, File dest) throws IOException {
		try (
					BufferedReader reader = new BufferedReader(new FileReader(src));
					/**
					 * PrintWriter与BufferWriter相比， 可以指定编码, 无追加模式， 可以println
					 */
					PrintWriter writer = new PrintWriter(dest, "utf-8");
		) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				writer.append(line).append("\n\r").flush();
			}
		}
	}

	private static void read2(File src, File dest) throws IOException {
		try (
					BufferedReader reader = new BufferedReader(new FileReader(src));
					BufferedWriter writer = new BufferedWriter(new FileWriter(dest, true));//追加模式
		) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.append(EncryptUtil.md5(line)).append("\n").flush();
			}
		}
	}

}
