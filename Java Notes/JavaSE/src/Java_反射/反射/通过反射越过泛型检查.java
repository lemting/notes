package Java_反射.反射;

import java.lang.reflect.Method;
import java.util.ArrayList;

/*
 *  给你一个ArrayList<Integer>的一个对象,想在这个集合中添加一字符串数据,如何实现?
 *  
 *  
 */

public class 通过反射越过泛型检查 {
	public static void main(String[] args) throws Exception {
		// 创建集合对象
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(10);

		// 获取集合al的Class文件对象
		Class<?> c = al.getClass();
		// 获取集合的添加方法(泛型只是给编译器看的,集合的add()方法的参数类型是Object)
		Method m = c.getDeclaredMethod("add", Object.class);

		// 调用方法,添加元素
		m.invoke(al, "hello");
		m.invoke(al, "world");
		m.invoke(al, "java");

		// 输出集合
		System.out.println(al);
	}
}
