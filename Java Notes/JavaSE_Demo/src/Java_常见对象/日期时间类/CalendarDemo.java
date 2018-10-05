package Java_常见对象.日期时间类;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/*
 *  Calendar类
 *	是一个抽象类可以为在某一特定时刻和一组(如MONTH,DAY_OF_MONTH,HOUR等等)日历字段之间的转换提供了一些方法,并未操纵该日历字段提供了一些方法
 *
 *		注意: MONTH从0开始即(0-11),故获取时默认的月时需 (MONTH + 1)
 *
 *	Calendar类的成员方法
 *		public static Calendar getInstance(): 使用默认时区和区域设置获取日历 
 *		public int get(int field): 返回给定日历字段的值
 *		public abstract void add(int field,int amount): 根据日历的规则，将指定的时间量添加或减去给定的日历字段
 *		public final void set(int year,int month,int date): 设置日历字段中的值YEAR ， MONTH和DAY_OF_MONTH
 *
 * 直接子类 GregorianCalendar
 */

public class CalendarDemo
{
	public static void main(String[] args)
	{
		//Calendar类未抽象类,不能直接造对象,Calendar提供了一种类方法getInstance,用于获取此类型的一般有用的对象
		//public static Calendar getInstance(): 使用默认时区和区域设置获取日历 
		Calendar rightNow = Calendar.getInstance();
		
		//public int get(int field): 返回给定日历字段的值
		System.out.print(rightNow.get(Calendar.YEAR) + "年");
		System.out.print((rightNow.get(Calendar.MONTH) + 1)+ "月");
		System.out.println(rightNow.get(Calendar.DATE) + "日");
		System.out.println(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
		System.out.println("--------------------------------------");
		
		//public abstract void add(int field,int amount): 根据日历的规则，将指定的时间量添加或减去给定的日历字段
		rightNow.add(Calendar.DAY_OF_MONTH, -55);
		System.out.print(rightNow.get(Calendar.YEAR) + "年");
		System.out.print((rightNow.get(Calendar.MONTH) + 1) + "月");
		System.out.println(rightNow.get(Calendar.DATE) + "日");
		
		//public final void set(int year,int month,int date): 设置日历字段中的值YEAR ， MONTH和DAY_OF_MONTH
		rightNow.set(2022, 2 - 1, 22);
		System.out.print(rightNow.get(Calendar.YEAR) + "年");
		System.out.print((rightNow.get(Calendar.MONTH) + 1) + "月");
		System.out.println(rightNow.get(Calendar.DATE) + "日");
	}
}