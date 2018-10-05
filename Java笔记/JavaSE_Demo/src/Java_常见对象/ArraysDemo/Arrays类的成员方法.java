package Java_常见对象.ArraysDemo;

import java.util.Arrays;
import java.util.List;

/*
 * Arrays: 针对数组进行操作的工具类 eg:排序与查找
 * 
 *		public static String toString(int[] a):(该方法可将任意数据类型的数组转成字符串)返回指定数组的内容的字符串表示形式
 *		public static void sort(int[] a):(该方法可将任意数据类型的数组按升序排序)按照数字顺序排列指定的数组
 *		public static void sort(int[] a,int fromIndex,int toIndex):(该方法可将任意数据类型的数组按升序排序数组的指定范围)按升序排列数组的指定范围
 *		public static int binarySearch(int[] a,int key):(该方法可查找任意数据类型的数组)二分查找
 *		public static int binarySearch(int[] a,int fromIndex,int toIndex,int key):(该方法可查找任意数据类型的数组)二分查找数组的指定范围
 * 		public static<T> List<T> asList(T... a): 把数组转成集合
 */

public class Arrays类的成员方法
{
	public static void main(String[] args)
	{
		int[] arr = {2,4,5,3,1,6};
		
		//public static String toString(int[] a):(该方法可将任意数据类型的数组转成字符串)返回指定数组的内容的字符串表示形式
		System.out.println("arr: " + Arrays.toString(arr));
		
		//public static void sort(int[] a):(该方法可将任意数据类型的数组按升序排序)按照数字顺序排列指定的数组
		System.out.println("排序前: " + Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("排序后: " + Arrays.toString(arr));
		System.out.println("-----------------------------");
		
		//public static int binarySearch(int[] a,int key):(该方法可查找任意数据类型的数组)二分查找
		System.out.println("binarySearch: " + Arrays.binarySearch(arr,2));
		System.out.println("binarySearch: " + Arrays.binarySearch(arr,9));
		
		//public static<T> List<T> asList(T... a): 把数组转成集合,但其本质还是数组,其长度不可变
		String[] strArray = {"hello","world","java"};
		List<String> list = Arrays.asList(strArray);
		for(String string : list)
			System.out.println(string);
		System.out.println("------------------------------");
		List<String> list2 = Arrays.asList("hello");
		List<String> list3 = Arrays.asList("hello","world");	
		List<String> list4 = Arrays.asList("hello","world","java");
		for(String string : list2)
			System.out.println(string);
		System.out.println("------------------------------");
		for(String string : list3)
			System.out.println(string);
		System.out.println("------------------------------");
		for(String string : list4)
			System.out.println(string);
	}
}
