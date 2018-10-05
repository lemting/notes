package Java_集合框架.ListDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * List: 有序集合(也称为序列 ),可重复
 * 		  该界面的用户可以精确控制列表中每个元素的插入位置.用户可以通过整数索引(列表中的位置)访问元素,并搜索列表中的元素
 * 		  与Set不同,列表通常允许重复元素
 * 
 * List的子类特点
 * 		ArrayList
 * 			底层数据结构是数组,查询快,增删慢
 * 			线程不安全,效率高
 * 		Vector
 * 			底层数据结构是数组,查询快,增删慢
 * 			线程安全,效率低
 * 		LinkedList
 * 			底层数据结构是链表,增删快,查询慢
 * 			线程不安全,效率高
 * 		到底使用谁呢？看需求
 * 			要安全吗？
 * 				要: Vector(即使要,也不适用这个)
 * 				不要: ArrayList或LinkedList(查询多用ArrayList;增删多用LinkedList)
 * 					
 * 
 * List集合的特有功能
 * 		A:添加功能
 * 			void add(int index,E element): 将指定的元素插入此列表中的指定位置
 * 			boolean addAll(int index,Collection<? extends E> c): 将指定集合中的所有元素插入到此列表中的指定位置
 * 		B:获取功能
 * 			E get(int index): 返回此列表中指定位置的元素
 * 			int indexOf(Object o): 返回此列表中指定元素的第一次出现的索引
 * 		C:删除功能
 * 			E remove(int index): 删除该列表中指定位置的元素
 * 		D:列表迭代器
 * 			ListIterator<E> listIterator(): 返回列表中的列表迭代器(按适当的顺序)List特有的迭代器
 *			ListIterator<E> listIterator(int index): 从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器	
 *		F:修改功能
 *			E set(int index,E element)用指定的元素替换此列表中指定位置的元素
 *			
 *	List集合特有遍历功能
 *		size()和get()方法结合使用
 */

public class List集合的特点和特有功能
{
	public static void main(String[] args)
	{
		List<String> list = new ArrayList<String>();
		
		//可重复
		list.add("hello");list.add("hello");list.add("world");list.add("java");list.add("world");
		
		Iterator<String> it = list.iterator();
		//有序
		while(it.hasNext())
			System.out.print(it.next() + "\t");
		System.out.println("\n---------------------------");

		//创建集合对象
		List<String> li = new ArrayList<String>();
		li.add("hello");li.add("world");li.add("java");
		
		//void add(int index,E element): 将指定的元素插入此列表中的指定位置
		//li.add(3,"javaee"); //该索引可以为 li.size() ,即相当于在末尾添加元素
		//System.out.println("li: " + li);
		li.add(1,"android");
		System.out.println("li: " + li);
		System.out.println("------------------------------------------");
		
		//E get(int index): 返回此列表中指定位置的元素
		System.out.println("get: " + li.get(2));
		
		//int indexOf(Object o): 返回此列表中指定元素的第一次出现的索引
		System.out.println("indexOf: " + li.indexOf("java"));
		System.out.println("-----------------------------------------");
		
		//E remove(int index): 删除该列表中指定位置的元素
		System.out.println("index: " + li.remove(1));
		System.out.println("li: " + li);
		System.out.println("-----------------------------------------");
		
		//创建集合对象
		List<String> li2 = new ArrayList<String>();
		li2.add("hello");li2.add("world");li2.add("java");
		
		//ListIterator<E> listIterator(): 返回列表中的列表迭代器(按适当的顺序)List特有的迭代器
		ListIterator<String> lit = li2.listIterator();
		while(lit.hasNext())
			System.out.print(lit.next() + "\t");
		System.out.println("\n-------------------------------------------");
		
		//ListIterator<E> listIterator(int index): 从列表中的指定位置开始，返回列表中的元素（按正确顺序）的列表迭代器	
		ListIterator<String> lit2 = li2.listIterator(1);
		while(lit2.hasNext())
			System.out.print(lit2.next() + "\t");
		System.out.println("\n-------------------------------------------");
		
		//E set(int index,E element)用指定的元素替换此列表中指定位置的元素
		System.out.println("set: " + li2.set(2, "JavaEE"));
		System.out.println("li2: " + li2);
		System.out.println("-------------------------------------------");
		
		//List集合特有遍历功能: size()和get()方法结合使用
		for(int i = 0;i < li2.size();i++)
		{
			String str = (String)li2.get(i);
			System.out.print(str + "\t");
		}
	}
}
