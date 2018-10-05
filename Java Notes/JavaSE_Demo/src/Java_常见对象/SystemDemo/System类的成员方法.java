package Java_常见对象.SystemDemo;

import java.util.Arrays;

/*
 * System类包含一些有用的类字段和方法,它不能被实例化
 * 
 * System类的成员方法
 * 
 * 		public static void gc(): 运行垃圾回收器
 * 		public static void exit(int status): 终止当前运行的Java虚拟机,该参数作为状态代码; 按照惯例,非零状态码表示异常终止
 * 		public static long currentTimeMillis(): 返回当前时间,返回在1970年1月1日UTC之间的当前时间和午夜之间的差异(以毫秒为单位)
 * 		public static void arraycopy(Object src,int srcPos,Object dest,int desPos,int length):将指定源数组中的数组从指定位置复制到目标数组的指定位置
 * 			
 */

public class System类的成员方法
{
	public static void main(String[] args)
	{
		int i = 0;
	
		//public static long currentTimeMillis():返回当前时间,返回在1970年1月1日UTC之间的当前时间和午夜之间的差异(以毫秒为单位)
		long start = System.currentTimeMillis();
		for(int j = 0;j < 10000;j++)
			System.out.print("i: " + i++ + "\t");
		long end = System.currentTimeMillis();
		System.out.println("\n运行时间: " + (end - start) + " ms");
		System.out.println("------------------------");
		
		//public static void arraycopy(Object src,int srcPos,Object dest,int desPos,int length):将指定源数组中的数组从指定位置复制到目标数组的指定位置
		int[] arr = {11,22,33,44,55}; 
		int[] arr2 = {6,7,8,9,10};
		System.arraycopy(arr, 1, arr2, 2, 2);
		System.out.println("arr: " + Arrays.toString(arr));
		System.out.println("arr2: " + Arrays.toString(arr2));
		System.out.println("------------------------");
		
		//public static void exit(int status): 终止当前运行的Java虚拟机,该参数作为状态代码; 按照惯例,非零状态码表示异常终止
		while(true)
		{
			System.out.print("i: " + i++ + "\t");
			if(i >= 10005)
				System.exit(0);
		}
		
		
	}
}
