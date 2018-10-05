package Java_集合框架.ListDemo;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * ArrayList的使用
 * 		存储字符串并遍历
 * 		删除集合中重复的元素
 * 			方式1: 遍历集合,并让每一个元素与该元素后面的元素比较,若集合已经有该元素则删除
 * 			方式2: 创建新的集合对象,遍历原集合的每个元素并对其判断: 若新集合没有该元素,则添加到新集合
 *		删除集合中重复的自定义对象
 *			
 */

public class ArrayListDemo
{
	public static void main(String[] args)
	{	
		//创建集合对象
		ArrayList<String> al = new ArrayList<String>();
		//创建元素对象,并添加元素
		al.add("hello");al.add("world");al.add("java");al.add("hello");al.add("hello");al.add("world");al.add("java");
		ArrayList<String> al2 = al;
		
		//遍历
		Iterator<String> iterator = al.iterator();
		while(iterator.hasNext())
		{
			String string = (String)iterator.next();
			System.out.print(string + "\t");
		}
		System.out.println("\n--------------------------");
		for(int i = 0;i < al.size();i++)
			System.out.print(al.get(i) + "\t");
		System.out.println("\n--------------------------");
		
		//方式1:
		//遍历集合,并让每一个元素与该元素后面的元素比较,若集合已经有该元素则删除
		for(int i = 0;i < al.size() - 1;i++)
			for(int j = i + 1;j < al.size();j++)
				if(al.get(i).equals(al.get(j)))
					al.remove(j--);	//删除元素后,后面的元素顶替了前一位元素,故j自减,否则删除后会自动忽略顶替删除元素位置的元素
		System.out.println("al: " + al);
		System.out.println("--------------------------------");
	
		//方式2:
		//创建新的集合对象,将原集合对象的元素添加到新集合对象中,并对其判断: 若新集合已有该元素,则不添加
		ArrayList<String> al3 = new ArrayList<String>();
		Iterator<String> it2 = al2.iterator();
		while(it2.hasNext())
		{
			String s = (String)it2.next();
			if(!al3.contains(s))
				al3.add(s);			
		}
		System.out.println("al3: " + al3);
	}
}
