package Java_集合框架.ListDemo;

import java.util.LinkedList;

/*
 *  LinkedList的特有功能
 *  	A:添加功能
 *  		public void addFirst(E e): 在该列表开头插入指定的元素(利用该方法可实现栈数据结构: 先进后出)
 *  		public void addLast(E e): 将指定的元素追加到此列表的末尾	
 *  	B:获取功能
 *  		public E getFirst(): 返回此列表中的第一个元素
 *  		public E getLast(): 返回此列表中的最后一个元素
 *  	C:删除功能
 *  		public E removeFirst(): 从此列表中删除并返回第一个元素
 * 			public E removeLast(): 从此列表中删除并返回最后一个元素
 */

public class LinkedListDemo {
	public static void main(String[] args) {
		// 创建集合对象
		LinkedList<String> link = new LinkedList<String>();
		// 添加元素
		link.add("hello");
		link.add("world");
		link.add("java");
		// 输出对象名
		System.out.println("link: " + link);
		System.out.println("----------------------------");

		// public void addFirst(E e): 在该列表开头插入指定的元素
		link.addFirst("wakk");
		System.out.println("addFirst: " + link);

		// public void addLast(E e): 将指定的元素追加到此列表的末尾
		link.addLast("kkaw");
		System.out.println("addLast: " + link);
		System.out.println("----------------------------");

		// public E getFirst(): 返回此列表中的第一个元素
		System.out.println("getFirst: " + (String) link.getFirst());

		// public E getLast(): 返回此列表中的最后一个元素
		System.out.println("getLast: " + (String) link.getLast());
		System.out.println("----------------------------");

		// public E removeFirst(): 从此列表中删除并返回第一个元素
		link.removeFirst();
		System.out.println("removeFirst: " + link);

		// public E removeLast()从此列表中删除并返回最后一个元素
		link.removeLast();
		System.out.println("removeFirst: " + link);
	}
}