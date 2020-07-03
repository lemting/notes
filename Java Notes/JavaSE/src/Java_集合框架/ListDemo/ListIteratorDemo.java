package Java_集合框架.ListDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
 *  ListIterator的特有功能
 *  	
 *  	(可以实现逆向遍历,但要想逆向遍历就要先正向遍历,所以一般无意义)
 *  	E previous(): 返回列表中的上一个元素,并向后移动光标位置
 *  	boolean hasPrevious(): 如果遍历反向列表,列表迭代器有多个元素,返回true
 */

public class ListIteratorDemo {
	public static void main(String[] args) {
		//创建List集合对象
		List<String> li = new ArrayList<String>();
		li.add("hello");li.add("world");li.add("java");
		
		//ListIterator<E> listIterator(): 返回列表中的列表迭代器(按适当的顺序)List特有的迭代器
		ListIterator<String> lit = li.listIterator();
		while(lit.hasNext()) {
			String str = (String)lit.next();
			System.out.print(str + "\t");
		}
		System.out.println("\n----------------------------------------");
		
		while(lit.hasPrevious()) {
			String str = (String)lit.previous();
			System.out.print(str + "\t");
		}
		System.out.println("\n----------------------------------------");
		
		int i;
		if((i = li.indexOf("world")) != -1) {
			li.set(i, "JavaEE");
		}
		System.out.println("li: " + li);
	}
}
