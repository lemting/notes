package Java_集合框架.CollectionDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * Iterator是一个接口
 * 	
 * 		集合通过iterator方法获取迭代器
 * 		迭代器,是遍历集合的一种方式
 * 		迭代器是依赖于集合而存在的
 * 
 * Iterator的方法
 * 		E next(): 返回迭代中的下一个元素
 * 		boolean hasNext(): 如果迭代具有更多的元素，则返回true
 */

public class IteratorDemo
{
	public static void main(String[] args)
	{
		Collection<String> c = new ArrayList<String>();
		c.add("wakaka");c.add("wqe");c.add("124");c.add("12.5");
		Iterator<String> it = c.iterator();
		
		//E next(): 返回迭代中的下一个元素
		while(it.hasNext())
		{
			//boolean hasNext(): 如果迭代具有更多的元素，则返回true
			System.out.print(it.next() + "\t");
		}
	}
}
