package Java_集合框架.CollectionDemo;

import java.util.ArrayList;

public class Test_集合的嵌套遍历
{
	public static void main(String[] args)
	{
		//以字符串为元素的集合
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<String> al2 = new ArrayList<String>();
		ArrayList<String> al3 = new ArrayList<String>();
		
		//添加字符串
		al.add("hello");
		al2.add("hello");al2.add("world");
		al3.add("hello");al3.add("world");al3.add("java");
		
		//以集合为元素的集合
		ArrayList<ArrayList<String>> al4 = new ArrayList<ArrayList<String>>();
		//添加集合
		al4.add(al);al4.add(al2);al4.add(al3);
		
		//集合的嵌套遍历
		for(ArrayList<String> a: al4)
		{
			for(String s: a)
				System.out.print(s + "\t");
			System.out.println("\n-------------");
		}
	}
}
