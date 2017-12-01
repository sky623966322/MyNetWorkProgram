package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Title:
 * Description:
 * Create Time: 2017-05-12 15:35
 * author: wangyan
 * version: 1.0
 */
public class DaytimeClient {

	public static void main(String[] args) {
		try (Socket socket = new Socket("time-a.nist.gov", 13)){
			socket.setSoTimeout(15 * 1000);
			InputStream inputStream = socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ASCII"));

			char[] buff = new char[1024];
			StringBuilder builder = new StringBuilder();
			//read()不带buff参数的方法只读一个char
			while (bufferedReader.read(buff) != -1){
				builder.append(buff);
			}
			System.out.println(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
