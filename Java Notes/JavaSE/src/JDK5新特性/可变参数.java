package JDK5新特性;

import java.util.Arrays;
import java.util.List;

/*
 * 可变参数    定义方法的时候不知道具体有几个参数
 * 		格式: 修饰符  返回值类型  方法名(数据类型...变量名){} 
 * 		注意: 这里的变量其实是一个数组
 * 			   如果一个方法有可变参数,并且有多个参数,那么,可变参数肯定是最后一个,否则可变参数会将参数都接受,从而报错
 * 		
 * Arrays工具类中的一个方法
 * 		public static <T> List<T> asList(T... a): 把数组转成集合,但其本质还是数组,其长度不可变
 */

public class 可变参数 {
	public static void main(String[] args) {
		//求和
		int a = 10;
		int b = 20;
		int c = 30;
		int d = 40;
		
		//两个数据和
		int sum = sum(a, b);
		//三个数据和
		int sum2 = sum(a, b, c);
		//四个数据和
		int sum3 = sum(a, b, c, d);  		
		
		//需求: 要写一个求和功能,到底几个数据求和呢,我不太清楚,但是我知道在调用的时候我肯定就知道了
		//为了解决这个问题,java就提供了一个新的东西: 可变参数
		System.out.println(sum + " --- " + sum2 + " --- " + sum3);
		
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
	
	public static int sum(int... a) {
		int sum = 0;
		for(int num : a)
			sum += num;
		return sum;
	}
	
	//private static int sum(int a, int b, int c, int d) {
	//	return a + b + c + d;
	//}

	//private static int sum(int a, int b, int c) {
	//	return a + b + c;
	//}

	//private static int sum(int a, int b) {
	//	return a + b;
	//}
}