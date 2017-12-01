package test;

/**
 * Title:
 * Description:
 * Create Time: 2017-08-18 16:21
 * author: wangyan
 * version: 1.0
 */
public class Test {

	int e = 6;
	Test() {
		int c = 1;
		this.f = 5;
		int e = 66;
	}
	int f = 55;
	int c = 11;
	int b = 1;
	{
		int a = 3;
		b = 22;
	}

	int a = 33;

	static {
		d = 4;
	}
	static int d = 44;

	int g = 7;
	int h = 8;
	public int test(){
		g = 77;
		int h = 88;
		System.out.println("h - 成员变量：" + this.h);
		System.out.println("h - 局部变量: " + h);
		return g;
	}

	public static void main(String[] args) {
		System.out.println("a: " + new Test().a);
		System.out.println("b: " + new Test().b);
		System.out.println("c: " + new Test().c);
		System.out.println("d: " + new Test().d);
		System.out.println("f: " + new Test().f);
		System.out.println("e: " + new Test().e);
		System.out.println("g: " + new Test().test());
	}

}
