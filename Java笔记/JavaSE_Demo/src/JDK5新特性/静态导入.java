package JDK5新特性;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
//import static java.lang.Math.max;

/*
 * 静态导入
 * 		格式: import static 包名....类名.方法名;
 * 		可以直接导入到方法的级别
 * 
 * 静态导入的注意事项:
 * 		A:方法必须是静态的
 * 		B:如果有多个同名方法,容易不知道使用谁?,这个时候要是用,必须加前缀
 */

public class 静态导入
{
	public static void main(String[] args)
	{
		System.out.println("Math.abs: " + Math.abs(-100));
		//静态导入java.lang.Math.abs后可直接使用
		System.out.println("abs: " + abs(-100));
		
		System.out.println("Math.pow: " + Math.pow(2,3));
		//静态导入java.lang.Math.pow后可直接使用
		System.out.println("pow: " + pow(2,3));
		
		//该处调用的是本类中提供的方法
		System.out.println("max: " + max(1,2));
		//要想使用Math类的max方法必须加前缀
		System.out.println("max: " + Math.max(1,2));
	}
	
	private static int max(int a,int b)
	{
		return (a < b)?a:b;
	}
}