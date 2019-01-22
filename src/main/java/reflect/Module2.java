package reflect;

/**
 * @author:wangyan1
 * @create_time: 2018-11-14 10:26
 */

public class Module2  {
	public static String str = "default";

	static {
		System.out.println("Module2 静态代码块被加载");
		str = "module2";
	}

	public void printStr(){
		System.out.println("执行Module2.printStr方法 --> " + str);
	}
}
