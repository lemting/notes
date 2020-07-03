package Java_集合框架.CollectionsDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *  Collections类是针对集合进行操作的工具类
 *  	Collection: 单列集合的顶层接口
 *  	Collections: 针对集合操作的和工具类,有对集合进行排序和二分查找的方法
 *  
 *  Collections类的成员方法
 *  	public static <T> void sort(List<T> list): 排序,默认情况下是自然排序
 *  	public static <E> int binarySearch(List<?> list,T key): 二分查找
 *  	public static <T> T max(Collection<?> coll): 最大值
 *  	public static void reverse(List<?> list): 反转
 *  	public static void shuffle(List<?> list): 随机置换
 *		返回同步的集合: 
 *		public static <T> Collection<T> synchronizedCollection(Collection<T> c): 返回由指定集合支持的同步（线程安全）集合
 *		public static <T> List<T> synchronizedList(List<T> list): 返回由指定列表支持的同步（线程安全）列表
 *		public static <T> Set<T> synchronizedSet(Set<T> s): 返回由指定集合支持的同步（线程安全）集		
 *		public static <K,V> Map<K,V> synchronizedMap(Map<K,V> m): 返回由指定地图支持的同步（线程安全）映射
 */

public class Collections类的方法 {
	public static void main(String[] args) {
		//创建集合对象
		List<Integer> list = new ArrayList<Integer>();
		//添加元素
		list.add(30);list.add(20);list.add(50);list.add(10);list.add(40);
		
		//public static <T> void sort(List<T> list): 排序,默认情况下是自然排序
		Collections.sort(list);
		System.out.println("sort: " + list);
		
		////public static void shuffle(List<?> list): 随机置换
		Collections.shuffle(list);
		System.out.println("shuffle: " + list);
		
		//public static <E> int binarySearch(List<?> list,T key): 二分查找
		Collections.sort(list);
		System.out.println("binarySearch: " + Collections.binarySearch(list,20));
		
		//public static <T> T max(Collection<?> coll): 最大值
		System.out.println("max: " + Collections.max(list));
		
		//public static void reverse(List<?> list): 反转	
		System.out.println("list: " + list);
		Collections.reverse(list);
		System.out.println("reverse: " + list);	
	}
}
