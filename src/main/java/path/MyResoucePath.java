package path;

import org.junit.Test;

/**
 * @author:wangyan1
 * @create_time: 2018-11-13 17:51
 */

public class MyResoucePath {

	@Test
	public void test1(){
		//当前路径
		System.out.println(MyResoucePath.class.getResource(""));
		//classpath
		System.out.println(MyResoucePath.class.getResource("/"));

		System.out.println(MyResoucePath.class.getResource("/test1.properties"));

		System.out.println(MyResoucePath.class.getResource("/path/test2.properties"));

		System.out.println(MyResoucePath.class.getResource("/path/subpath/test3.properties"));

	}

}
