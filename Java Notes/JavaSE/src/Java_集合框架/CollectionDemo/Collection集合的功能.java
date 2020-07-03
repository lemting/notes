package Java_集合框架.CollectionDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * 集合的由来: 
 * 		我们学习的是面向对象语言,而面向对象语言对事物的描述是通过对象体现的,为了方便对多个对象进行操作,我们就必须把这多个对象进行储存.
 * 		而想要储存多个对象,就不能是一个基本的变量,而应该是一个容器类型的变量.我们所学的有什么是容器类型呢？
 * 		数组和StringBuffer,但是,StringBuffer的结果是一个字符串,不一定能满足我们的需求,所以我们只能选择数组,这就是对象数组
 * 		而对象数组又不能适应变化的需求,因为数组的长度是固定的,这个时候,为了适应变化的需求,Java就提供了集合类供我们使用
 * 
 * 数组和集合的区别
 * 		A:长度区别
 * 			数组长度固定
 * 			集合长度可变
 * 		B:内容不同
 * 			数组储存的是同一种类型的元素
 * 			而集合可以储存不同类型的元素
 * 		C:元素的数据类型问题
 * 			数组可以储存本身数据类型,也可以储存引用数据类型
 * 			只能储存引用数据类型
 * 
 * 集合的使用步骤:
 * 		A:创建集合对象
 * 		B:创建元素对象
 * 		C:把元素添加到集合
 * 		D:遍历集合
 * 			a:通过集合对象获取迭代器对象
 * 			b:通过迭代器对象的hasNext()方法判断时候有元素
 * 			c:通过迭代器对象的next()方法获取元素并移动到下一个位置		
 * 
 * Collection集合是集合的顶层接口,他的子体系有重复的,有唯一的,有有序的,有无序的
 * 集合的集成体系:
 *										 Collection
 * 											 │
 * 						  ┌──────────────────┴─────────────────┐
 * 						  │									   │	
 * 						 List								  Set
 * 						  │                                    │
 * 				┌─────────┼─────────┐				   ┌───────┴───────┐   
 * 				│		  │			│				   │		 	   │		
 * 		   ArrayList	Vector	 LinkedList			HashSet 		TreeSet	  //具体子类
 * 
 * Collection集合的功能		
 * 		A:添加功能
 * 			boolean add(E e): 添加一个元素 
 * 			boolean addAll(Collection<? extends E> c): 添加一个集合的元素
 * 		B:删除功能
 * 			void clear(): 从此集合中删除所有元素
 * 			boolean remove(Object o): 从此集合中删除一个元素
 * 			boolean removeAll(Collection<?> c): 删除指定集合中包含的所有此集合的元素(只要有一个元素被删除都会返回true)
 * 			boolean retainAll(Collection<?> c)仅保留此集合中包含在指定集合中的元素
 * 		C:判断功能
 * 			boolean contains(Object o): 判断此集合是否包含指定的元素
 * 			boolean containsAll(Collection<?> c): 判断此集合是否包含指定 集合中的所有元素(只有所有元素都包含才会返回true)
 * 			boolean isEmpty(): 判断此集合是否元素为空
 * 		D:迭代器
 * 			Iterator<E> iterator(): 返回此集合中的元素的迭代器,集合的专用遍历方式(Iterator是一个接口,实际返回值是其子类对象)
 * 				Iterator接口: E next(): 返回迭代中的下一个元素
 * 				Iterator接口: boolean hasNext(): 如果迭代具有更多的元素，则返回true
 * 		E:长度功能
 * 			int size(): 返回此集合中的元素数
 * 		F:交集功能
 * 			boolean retainAll(Collection<?> c):仅保留此集合中两个集合都有的元素(A = A ∩ B,若A不变,即A ⊆  B,则返回false;若A变了,则返回true)
 *		G:转换成数组功能
 *			Object[] toArray(): 将此集合转成数组,便于实现集合的遍历
 *			<T> T[] toArray(T[] a): 返回包含此集合中所有元素的数组
 *
 *	泛型: 是一种把类型明确的工作推迟到创建对象或调用方法的时候才去明确的特殊类型,参数化类型,把类型发工作参数一样的从传递
 *  	格式: 
 * 			<数据类型>
 * 			此处的类型是能是引用类型
 * 		好处:
 * 			A:把运行期间的问题提前到了编译期间
 * 			B:避免了强制类型转换
 * 			C:优化了程序设计,解决了黄色警告
 */

public class Collection集合的功能 {
	public static void main(String[] args) {
		// 创建集合1
		Collection<String> c1 = new ArrayList<String>();
		// 创建集合2
		Collection<String> c2 = new ArrayList<String>();

		// boolean add(E e): 添加一个元素
		c1.add("abc1");
		c1.add("abc2");
		c1.add("abc3");
		c1.add("abc4");
		c2.add("abc4");
		c2.add("abc5");
		c2.add("abc6");
		c2.add("abc7");
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);

		// boolean addAll(Collection<? extends E> c): 添加一个集合的元素
		System.out.println("addAll: " + c1.addAll(c2));
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		System.out.println("----------------------------------");

		// boolean removeAll(Collection<?> c): 删除指定集合中包含的所有此集合的元素(只要有一个元素被删除都会返回true)
		System.out.println("removeAll: " + c1.removeAll(c2));
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		c1.add("abc4");
		System.out.println("removeAll: " + c1.removeAll(c2));
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		System.out.println("----------------------------------");

		// boolean containsAll(Collection<?> c): 判断此集合是否包含指定 集合中的所有元素(只有所有元素都包含才会返回true)
		c1.add("abc4");
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		System.out.println("containsAll: " + c1.containsAll(c2));
		System.out.println("---------------------------------");

		// boolean retainAll(Collection<?> c): 仅保留此集合中两个集合都有的元素
		System.out.println("retainAll: " + c1.retainAll(c2));
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
		System.out.println("-------------------------------------");

		// Object[] toArray(): 将此集合转成数组,便于实现集合的遍历
		Object[] objs = c2.toArray();
		for (int i = 0; i < objs.length; i++)
			System.out.print(objs[i] + "\t");
		System.out.println("\n------------------------------------------");

		// Iterator<E> iterator(): 返回此集合中的元素的迭代器,集合的专用遍历方式(Iterator是一个接口,实际返回值是其子类对象)
		Iterator<String> it = c2.iterator();
		while (it.hasNext())
			System.out.print(it.next() + "\t");
	}
}
