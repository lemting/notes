package Java_常见对象.排序与查找;

import java.util.Scanner;

public class Test_把字符串的字符排序
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		//通过StringBuffer
		System.out.print("请输入一个字符串: ");
		StringBuffer s = new StringBuffer(sc.nextLine());
		
		char[] ch = s.toString().toCharArray();
		System.out.println("s: " + s);
		System.out.println("--------------------------");
		
		冒泡排序.maopao(ch);
		s.delete(0,s.length()).append(ch);
		System.out.println("s: " + s);

		//通过String
		//System.out.print("请输入一个字符串: ");
		//String s2 = sc.nextLine();
		
		//char[] ch2 = s2.toCharArray();
		//System.out.println("s2: " + s2);
		//System.out.println("--------------------------");
		
		//冒泡排序.maopao(ch2);
		//s2 = String.valueOf(ch2);
		//System.out.println("s2: " + s2);
	}
}
