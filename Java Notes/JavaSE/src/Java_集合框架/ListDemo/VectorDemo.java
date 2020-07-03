package Java_集合框架.ListDemo;

import java.util.Enumeration;
import java.util.Vector;

/*
 * Vector的特有功能
 * 		A:添加功能
 * 			public void addElement(E obj): 将指定的组件添加到此向量的末尾,将其大小增加1
 * 		B:获取功能
 * 			public E elementAt(int index): 返回指定索引处的组件
 * 			public Enumeration<E> elements(): 返回此向量的组件的枚举(类似于迭代器)(Enumeration是一个接口,则该方法返回值为Enumeration的子类对象)
 * 				Enumeration接口: boolean hasMoreElements(): 测试此枚举是否包含更多元素
 * 				Enumeration接口: E nextElement(): 如果此枚举对象至少有一个要提供的元素，则返回此枚举的下一个元素
 * 
 */

public class VectorDemo {
	public static void main(String[] args) {
		// 创建集合对象
		Vector<String> ve = new Vector<String>();

		// public void addElement(E obj): 将指定的组件添加到此向量的末尾,将其大小增加1
		ve.addElement("hello");
		ve.addElement("world");
		ve.addElement("java");

		// public E elementAt(int index): 返回指定索引处的组件
		for (int i = 0; i < ve.size(); i++) {
			String s = (String) ve.elementAt(i);
			System.out.print(s + "\t");
		}
		System.out.println("\n-----------------------");

		// public Enumeration<E> elements(): 返回此向量的组件的枚举(类似于迭代器)
		Enumeration<String> en = ve.elements();
		while (en.hasMoreElements()) {
			String s = (String) en.nextElement();
			System.out.print(s + "\t");
		}
	}
}
