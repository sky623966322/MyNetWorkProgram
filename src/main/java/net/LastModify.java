package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Title:
 * Description:
 * Create Time: 2017-04-14 15:32
 * author: wangyan
 * version: 1.0
 */
public class LastModify {

	public static void main(String[] args) {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();

			URL url = new URL(input);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			header(urlConnection);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void header(HttpURLConnection urlConnection) throws ProtocolException {
		urlConnection.setRequestMethod("HEAD");
		//urlConnection.connect();//可要可不要
		System.out.println(urlConnection.getLastModified());
		System.out.println(urlConnection.getContentLength());
		System.out.println(urlConnection.getHeaderFields());
	}
}
