package Java_集合框架.SetDemo;

import java.util.HashSet;
import java.util.Set;

/*
 * Set 不包含重复元素的集合
 *     无序(储存顺序和取出顺序不一致),唯一不重复
 *     
 * 注意: 虽然Set集合的元素无序,但是,作为集合来说,他肯定有自己的存储顺序
 *     	而你的顺序恰好和他的储存一致,这代表不了有序,你可以多储存一些数据,就能看到效果   
 * 
 */

public class SetDemo
{
	public static void main(String[] args)
	{
		//创建集合对象
		Set<String> set = new HashSet<String>();
		
		//添加元素
		set.add("hello");set.add("world");set.add("java");set.add("hello");
		
		for(String s: set)
			System.out.print(s + "\t");
		System.out.println("\n-----------------------");
		
	}
}
