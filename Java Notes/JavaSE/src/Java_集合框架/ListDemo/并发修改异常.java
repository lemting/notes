package Java_集合框架.ListDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
 * ConcurrentModificationException: 当方法检测到对象的并发修改,但不允许这样的修改时，抛出此异常
 *
 * 产生的原因:
 * 		迭代器是依赖于集合而存在的,在判断成功后,集合中的元素发生了改变,而迭代器缺不知道,所以就会报错,这个错叫并发修改异常
 * 解决方案: 
 * 		A:迭代器遍历元素,迭代器修改元素   
 * 			void add(E e): 将指定的元素插入列表
 * 			但是插入的元素是跟在刚才迭代的元素后面的
 * 		B:集合遍历元素,集合修改元素
 * 			插入的元素是在末尾添加的
 */

public class 并发修改异常 {
	public static void main(String[] args) {
		//创建List集合对象
		List<String> li = new ArrayList<String>();
		li.add("hello");li.add("world");li.add("java");
				
		//ListIterator<E> listIterator(): 返回列表中的列表迭代器(按适当的顺序)List特有的迭代器
		ListIterator<String> lit = li.listIterator();
		
		//遍历集合,若集合中有"world"这个元素,则添加"JavaEE"元素////ConcurrentModificationException
		//while(lit.hasNext()) {
		//	String str = (String)lit.next();
		//	if(str.equals("world"))
		//		li.add("JavaEE");
		//}
		
		//迭代器遍历元素,迭代器修改元素   
		while(lit.hasNext()) {
			String str = (String)lit.next();
			if(str.equals("world"))
				lit.add("JavaEE");
		}
		System.out.println("li: " + li);
			
		//集合遍历元素,集合修改元素
		for(int i = 0;i < li.size();i++) {
			String s = (String)li.get(i);
			if(s.equals("world"))
				li.add("JavaEE");
		}
		System.out.println("li: " + li);
	}
}