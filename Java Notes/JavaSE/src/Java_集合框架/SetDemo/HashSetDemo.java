package Java_集合框架.SetDemo;

import java.util.HashSet;
import java.util.LinkedHashSet;

/*
 * HashSet: 他不保证Set的迭代顺序,特别是他不保证该顺序恒久不变
 * 		底层数据结构是以链表为元素的数组
 *	  	HashSet保证元素唯一性的底层依赖于hashCode()和equals()方法
 * 
 *	 	LinkedHashSet类
 * 			元素有序,唯一
 * 			由链表保证元素有序(存储与取出一致)
 * 			由哈希表保证元素唯一 
 */

public class HashSetDemo {
	public static void main(String[] args) {
		HashSet<String> hs = new HashSet<String>();
		hs.add("hello");hs.add("world");hs.add("java");hs.add("hello");
		
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
		
		lhs.add("hello");lhs.add("world");lhs.add("java");lhs.add("hello");
	
		//元素无序,唯一
		for(String s: hs)
			System.out.println(s + "\t");
		System.out.println("\n---------------------");
		
		//元素有序,唯一
		for(String s: lhs)
			System.out.println(s + "\t");
		System.out.println("\n---------------------");
	}
}
