package reflect;

import sun.security.pkcs11.Secmod.Module;

/**
 * @author:wangyan1
 * @create_time: 2018-11-05 11:39
 */

public class Module1{

	public String str = "default";
	public String str2 = "default";

	static {
		System.out.println("Module1 静态代码块被加载");
	}

	private Module1() {
		System.out.println("private无参构造函数被调用");
	}

	public Module1(String str) {
		this.str = str;
	}

	public void printStr(){
		System.out.println(str);
	}

	private void changeStr(){
		str = "afterChange";
		System.out.println(str);
	}

	private class SubModule1{
		private SubModule1() {
			System.out.println("SubModule1构造方法被调用");
		}
	}

	@Override
	public String toString() {
		return "Module1{" +
					   "str='" + str + '\'' +
					   '}';
	}
}
