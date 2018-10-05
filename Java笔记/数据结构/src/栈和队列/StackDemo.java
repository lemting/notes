package 栈和队列;

import java.util.Stack;

/*
 * 	Stack: 栈 
 * 		继承自 Vector
 * 
 * 	Stack类的构造方法:
 * 		public Stack(): 创建一个空堆栈
 * 
 * 	Stack类的成员方法:
 * 		public boolean empty(): 测试此堆栈是否为空。 
 * 		public E peek(): 查看此堆栈顶部的对象，而不从堆栈中删除它
 * 		public E pop(): 删除此堆栈顶部的对象，并将该对象作为此函数的值返回
 * 		public E push(E item): 将项目推送到此堆栈的顶部
 * 		public int search(Object o): 返回一个对象在此堆栈上的基于1的位置
 * 				(如果对象o作为该堆栈中的项目发生，则该方法返回距堆栈顶部最靠近堆栈顶部的距离;堆栈中最上面的项目被认为是距离1.equals方法用于比较o与此堆栈中的项目)
 * 		
 * 		其他方法另见  Collection 集合和  Vector 集合
 * 
 */

public class StackDemo
{
	public static void main(String[] args)
	{
		Stack<Integer> stack = new Stack<Integer>();
		//压栈
		for(int i = 0;i < 15;i++)
			stack.push(100 - i);
		//获取栈的大小
		System.out.println(stack.size());
		System.out.println("--------------------------");
		//弹栈
		while(!stack.empty())
			System.out.println(stack.pop());
		System.out.println("--------------------------");
		//获取栈的大小
		System.out.println(stack.size());
	}
	
}
