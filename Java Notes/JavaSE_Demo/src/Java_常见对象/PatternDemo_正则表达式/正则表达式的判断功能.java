package Java_常见对象.PatternDemo_正则表达式;

import java.util.Scanner;

/*
 * 正则表达式的判断功能
 * 		
 * 		String类的public boolean matches(String regex): 判断字符串是否符合正则表达式
 */

public class 正则表达式的判断功能
{
	public static void main(String[] args)
	{
		//判断手机号是否符合需求
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		System.out.print("请输入手机号: ");
		String PhoneNumber = sc1.nextLine();
		System.out.println(checkPhoneNumber(PhoneNumber));
	}
	
	public static boolean checkPhoneNumber(String PhoneNumber)
	{	
		return PhoneNumber.matches("1[3-8]\\d{9}");
	}
}
