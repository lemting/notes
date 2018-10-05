package Java_常见对象.日期时间类;

import java.util.Calendar;
import java.util.Scanner;

public class Test_获取任意年的2月份天数
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("请输入一个年份: ");
		int year = sc.nextInt();	
		System.out.println("该年2月有 " + janHasDay(year) + " 天"); 
		System.out.println("该年2月有 " + janHasDay2(year) + " 天");
	}
	
	public static int janHasDay(int year)
	{
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(year, 3 - 1 , 1);
		rightNow.add(Calendar.DATE,-1);
		return rightNow.get(Calendar.DATE);
	}
	
	public static int janHasDay2(int year)
	{
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
			return 29;
		else 
			return 28;
	}
}
