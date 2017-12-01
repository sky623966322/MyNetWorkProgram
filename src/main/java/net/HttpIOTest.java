package net;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Title:
 * Description:
 * Create Time: 2017-04-11 16:12
 * author: wangyan
 * version: 1.0
 */
public class HttpIOTest {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("请输入文件url:");
			String inputStr = reader.readLine();

			URL url = new URL(inputStr);
			//saveImg(url);
			//getContentLength(url);
			//testHttpText(url);
			joddHttp(inputStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getContentLength(URL url) throws IOException {
		System.out.println(url.getProtocol());
		System.out.println(url.getHost());
		System.out.println(url.getPort());
		System.out.println(url.getFile());
		System.out.println(url.getPath());
		System.out.println(url.getQuery());
		System.out.println(url.getRef());//返回锚点
		System.out.println("--------------------");


		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		System.out.println(urlConnection.getContentType());
		System.out.println(urlConnection.getContentLength());
		System.out.println(urlConnection.getContentEncoding());
		System.out.println(urlConnection.getDate());
		System.out.println(urlConnection.getExpiration());
		System.out.println(urlConnection.getLastModified());
		System.out.println(urlConnection.getHeaderField("Cache-Control"));
	}

	private static void saveImg(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		String fileName = url.getFile();
		//System.out.println(fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf(".")));
		File file = new File(fileName.substring(fileName.lastIndexOf("/") + 1));
		try (BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
			 FileOutputStream out = new FileOutputStream(file)) {
			byte[] buffer = new byte[4092];
			while (inputStream.read(buffer) != -1){
				out.write(buffer);
			}
		}
	}

	private static void testHttpText(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		urlConnection.setDoOutput(true);

		//只含有text文本参数时，才能使用PrintWriter
		/*try(
				BufferedOutputStream buffer = new BufferedOutputStream(urlConnection.getOutputStream());
				OutputStreamWriter out= new OutputStreamWriter(buffer, "utf-8");
				PrintWriter writer = new PrintWriter(out, true);
		){
			writer.append("yyuid=1615145").flush();
			//writer.flush();
		}*/

		try (
				PrintStream printStream = new PrintStream(urlConnection.getOutputStream(), true, "utf-8");
		) {
			printStream.append("yyuid=1615145").flush();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		char[] buff = new char[1024];
		StringBuilder sb = new StringBuilder();
		while (reader.read(buff) != -1){
			sb.append(buff);
		}
		System.out.println(sb.toString());
	}

	private static void joddHttp(String urlStr){
		HttpRequest httpRequest = HttpRequest.post(urlStr);
		HttpResponse response = httpRequest.form("yyuid", "1615145").send();
		System.out.println(response.headers());
		System.out.println(response.contentEncoding());
		System.out.println(response.acceptEncoding());
		System.out.println(response.charset());

		System.out.println(response.bodyText());
	}
}
