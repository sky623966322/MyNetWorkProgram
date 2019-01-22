package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author:wangyan1
 * @create_time: 2018-11-05 11:24
 */

public class MyReflectModule {

	@Test
	public void testConstructor() throws Exception {
		Class<?> clazz = Class.forName("reflect.Module1");
		//获取public构造方法
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("" + constructor.getName() +
									   " ,是否能被访问[" + constructor.isAccessible() + "]");
		}
	}

	@Test
	public void testConstructor2() throws Exception {
		Class<?> clazz = Class.forName("reflect.Module1");
		//获取public, 并且指定参数类型的构造方法
		Constructor<?> constructor = clazz.getConstructor(String.class);
		System.out.println("" + constructor.getName() +
								   " ,是否能被访问[" + constructor.isAccessible() + "]");
		Module1 module1 = (Module1) constructor.newInstance("outerValue");
		System.out.println(module1);
		module1.printStr();
	}

	@Test
	public void testDelcaredConstructor() throws Exception {
		Class<?> clazz = Class.forName("reflect.Module1");
		//获取所有构造方法，包括私有构造方法
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("" + constructor.getName() +
									   " ,是否能被访问[" + constructor.isAccessible() + "]");
		}
	}

	@Test
	public void forName() throws ClassNotFoundException {
		/**
		 * Class.forName 会加载static静态代码块, 例如 com.mysql.jdbc.Driver ，静态代码块中注册自己导DriverManager，
		 * 如果使用ClassLoader.loadClass, 静态代码块就不会加载
		 */
		Class<?> aClass = Class.forName("reflect.Module1");
	}

	@Test
	public void classLoad() throws ClassNotFoundException {
		/**
		 * classLoader.loadClass 不会加载static静态代码块
		 */
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class<?> aClass = classLoader.loadClass("reflect.Module2");
	}

	/**
	 * 获得ClassCloader的方法
	 */
	@Test
	public void getClassLoader(){
		System.out.println(Module1.class.getClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader());
	}

	/**
	 * 
	 */
	@Test
	public void getParentClassLoader(){
		ClassLoader classLoader = Module1.class.getClassLoader();
		while (classLoader != null){
			System.out.println(classLoader);
			/**
			 * 系统默认提供三个ClassLoader
			 * 1.BootStrap ClassLoader, 最顶层的类加载器，底层由C++实现，负载加载rt.jar, resource.jar核心库
			 * 2.ExtClassLoader 扩展类加载器，加载JAVA_HOME/jre/lib/ext/*.jar
			 * 3.AppClassLoader,系统类加载器，负载加载应用程序classpath下的jar和class
			 * 4.自定义类加载器，需要继承ClassLoader, 复写findClass方法。自定义加载器可以动态的加载不存在的class文件，比如从网络上加载，文本定义的java类
			 */
			classLoader = classLoader.getParent();
		}
	}

}
