package Java_集合框架.ListDemo;

import java.util.LinkedList;

/**
 * 这是模拟栈数据结构创建的类
 * 
 * @author Lemting
 * @version v1.0
 */

public class Test_自建类MyStack模拟栈数据结构<T> {
	private LinkedList<T> ll;

	public Test_自建类MyStack模拟栈数据结构() {
		ll = new LinkedList<T>();
	}

	/**
	 * 这是用于在顶层添加元素的方法
	 * 
	 * @param t 要添加的元素
	 */
	public void add(T t) {
		ll.addFirst(t);
	}

	/**
	 * 这是用于获取顶层元素的方法,每次获取元素都会删除该元素
	 * 
	 * @return 顶层元素
	 */
	public T get() {
		return ll.removeFirst();
	}

	/**
	 * 这是用于判断集合中是否还有元素的方法
	 * 
	 * @return 是否有元素
	 */
	public boolean isEmpty() {
		return ll.isEmpty();
	}
}