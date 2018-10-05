package 栈和队列;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  Queue: 队列接口
 *  
 *  继承父类: Collection,...
 *  直接子类: LinkedList,...
 *  
 *  Queue接口的方法: 
 *  	boolean add(E e): 将指定的元素插入到此队列中
 * 		E element(): 检索，但不删除，这个队列的头
 * 		boolean offer(E e): 如果在不违反容量限制的情况下立即执行，则将指定的元素插入到此队列中
 *  	E peek(): 检索但不删除此队列的头部，如果此队列为空，则返回 null
 *  	E poll(): 检索并删除此队列的头，如果此队列为空，则返回 null
 *  	E remove(): 检索并删除此队列的头
 */

public class QueueDemo
{
	public static void main(String[] args)
	{
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for(int i = 0;i < 15;i++)
			queue.add(i + 1);
		for(int i = 0;i < 15;i++)
		{
			//获取队列头
			System.out.println(queue.peek());
			//删除队列头
			System.out.println(queue.remove());
		}
	}
}
