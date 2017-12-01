package thread;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Title:
 * Description:
 * Create Time: 2017-04-09 16:43
 * author: wangyan
 * version: 1.0
 */
public class MyAndress {
	public static void main(String[] args) {
		try {
			//InetAddress address = InetAddress.getLocalHost();
			//InetAddress address = InetAddress.getByName("YY-20150902CEVW");//主机名
			//InetAddress address = InetAddress.getByName("ip-10-21-146-50.vm.yyclouds.com");
			InetAddress address = InetAddress.getByName("www.yy.com");//域名
			if (address.getAddress().length == 4){
				System.out.println("ipv4");
			}else if (address.getAddress().length == 16){
				System.out.println("ipv6");
			}
			System.out.println(address.getHostAddress());

			/*InetAddress address = InetAddress.getByName("10.21.146.50");
			System.out.println(address.getCanonicalHostName());*/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
