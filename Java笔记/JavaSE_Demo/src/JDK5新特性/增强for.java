package JDK5新特性;

import java.util.ArrayList;
//import java.util.List;

/*
 * 增强for是for的一种  (增强for其实是用来替代迭代器的)
 * 		格式: 
 * 			for(元素数据类型  变量: 数组或Collection集合){使用变量即可,该变量就是元素}
 * 		好处:
 * 			简化了数组和集合的遍历
 * 		弊端:
 * 			增强for的目标不能是null
 * 			如何解决: 对增强for的目标先进行部位null的判断,然后再使用
 */

public class 增强for
{
	public static void main(String[] args)
	{
		//定义一个int数组
		int[] arr = {1,2,3,4,5};
		//for
		for(int x = 0;x < arr.length;x++)
			System.out.print(arr[x] + "\t");
		System.out.println("\n---------------------------");	
		
		//增强for
		for(int x: arr)
			System.out.print(x + "\t");
		System.out.println("\n---------------------------");	
		
		//定义一个String数组
		String[] str = {"001","002","003","004"};
		for(String s: str)
		{
			System.out.print(s + "\t");
		}
		System.out.println("\n---------------------------");
		
		//定义一个集合
		ArrayList<String> array = new ArrayList<String>(); 
		array.add("hello");array.add("world");array.add("java");
		for(String s: array)
		{
			System.out.print(s + "\t");
		}
		System.out.println("\n---------------------------");
		
//		//弊端
//		List<String> list = null;
//		//for(String s: list) 
//		//{
//		//	System.out.print(s + "\t");
//		//}
//		if(list != null)
//		{
//			for(String s: list) 
//				System.out.print(s + "\t");
//		}
		
		//增强for其实是用来替代迭代器的
		//ConcurrentModificationException  并发修改异常
		//for(String s: array)
		//{
		//	if(s.equals("world"))
		//		array.add("JavaEE");
		//}
		//System.out.println("array: " + array);
	}

}