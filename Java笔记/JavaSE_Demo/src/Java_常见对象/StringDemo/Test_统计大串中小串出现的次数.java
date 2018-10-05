package Java_常见对象.StringDemo;

import java.util.Scanner;

public class Test_统计大串中小串出现的次数
{
	public static void main(String[] args)
	{
		int num = 0,sum = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入一个字符串: ");
		String str1 = sc.nextLine();
		System.out.print("请输入一个要统计的字符串: ");
		String str2 = sc.nextLine();
		while((num = str1.indexOf(str2,num) + str2.length()) != -1 + str2.length())
				sum++;
		System.out.println("在字符串\"" + str1 + "\"中\"" + str2 + "\"共出现 " + sum + " 次");
	}
}
