package io;

import com.duowan.leopard.commons.utility.EncryptUtil;
import org.apache.commons.lang.StringUtils;

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
			String fileName = "G:\\imei.csv";
			File src = new File(fileName);
			String descFileName = "imei_md5.txt";
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

	public static void read2(File src, File dest) throws IOException {
		try (
					BufferedReader reader = new BufferedReader(new FileReader(src));
					BufferedWriter writer = new BufferedWriter(new FileWriter(dest, true));//追加模式
		) {
			int count = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				line = StringUtils.substringBetween(line, "\"", "\"");
				if (StringUtils.isBlank(line) || line.length() > 30 || line.length() < 10) continue;
				String lineMd5 = EncryptUtil.md5(line);
				writer.append(lineMd5).append("\n").flush();
				count++;
				System.out.println(count + "->" + line + "->" + lineMd5);
				//if (count == 100) break;
			}
		}
	}

}
