package thread;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Title:
 * Description:
 * Create Time: 2017-03-25 19:14
 * author: wangyan
 * version: 1.0
 */
public class DigestSign{

	public static void main(String[] args) {
		String[] arr = {"G:\\logs-Error.txt", "G:\\config-电信.txt"};
		for (String fileName : arr) {
			DigestThread thread = new DigestThread(fileName, new DigestThreadCallback() {//主线程对象传入thread中
				public void callback(String fileName, byte[] digest) {
					StringBuilder builder = new StringBuilder(fileName);
					builder.append(":");
					builder.append(DatatypeConverter.printHexBinary(digest));
					System.out.println(builder.toString());
				}
			});
			thread.start();//异步执行
		}
	}

	interface DigestThreadCallback{
		void callback(String fileName, byte[] digest);
	}

	static class DigestThread extends Thread{
		private String fileName;
		//private byte[] sign;
		private DigestThreadCallback digestThreadCallback;

		public DigestThread(String fileName) {
			this.fileName = fileName;
		}

		public DigestThread(String fileName, DigestThreadCallback digestThreadCallback) {
			this.fileName = fileName;
			this.digestThreadCallback = digestThreadCallback;
		}

		@Override
		public void run() {
			try {
				FileInputStream inputStream = new FileInputStream((fileName));
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);
				while (digestInputStream.read() != -1);
				byte[] sign = digest.digest();
				digestThreadCallback.callback(fileName, sign);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//主线程中调用这个方法，很可能得到null， 因为thread.start()异步启动线程，主线程执行到getDigest
		/*public byte[] getDigest() {
			return sign;
		}*/
	}

}
