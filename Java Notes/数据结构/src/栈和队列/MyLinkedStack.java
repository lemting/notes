package 栈和队列;

import java.util.LinkedList;

/*
 * 	用链表模拟栈
 * 		
 * 		public int size(): 获取此栈的长度
 * 		public boolean empty(): 测试此堆栈是否为空。 
 * 		public E peek(): 查看此堆栈顶部的对象，而不从堆栈中删除它
 * 		public E pop(): 删除此堆栈顶部的对象，并将该对象作为此函数的值返回
 * 		public E push(E item): 将项目推送到此堆栈的顶部
 * 		public int search(Object o): 返回一个对象在此堆栈上的基于1的位置
 * 				(如果对象o作为该堆栈中的项目发生，则该方法返回距堆栈顶部最靠近堆栈顶部的距离;堆栈中最上面的项目被认为是距离1.equals方法用于比较o与此堆栈中的项目)
 */

public class MyLinkedStack<T>
{
	private LinkedList<T> linkedList = null;
	
	public MyLinkedStack()
	{
		linkedList = new LinkedList<T>();
	}
	
	//获取此栈的长度
	public int size()
	{
		return linkedList.size();
	}
	//测试此堆栈是否为空。 
	public boolean empty()
	{
		return size() <= 0;
	}
	//查看此堆栈顶部的对象，而不从堆栈中删除它
	public T peek()
	{	
		return linkedList.getFirst();
	}
	//删除此堆栈顶部的对象，并将该对象作为此函数的值返回
	public T pop()
	{
		return linkedList.removeFirst();
	}
	//将项目推送到此堆栈的顶部
	public T push(T item)
	{
		linkedList.addFirst(item);
		return peek();
	}
	//返回一个对象在此堆栈上的基于1的位置
	public int search(Object o)
	{
		int i = 1;
		for(T t: linkedList)
		{
			if(t.equals(o))
				return i;
			else 
				i++;
		}
		return -1;
	}
}
