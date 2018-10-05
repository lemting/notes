package Java_常见对象.日期时间类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * 
 */

public class Test_你来到这个世界多少天
{
	public static void main(String[] args) throws ParseException
	{
		//键盘录入出生年月日
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入出生年月日(\"yyyy-MM-dd\"): ");
		String line = sc.nextLine();
		
		//把字符串转换成一个日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(line);
		
		//通过该日期得到一个毫秒值
		long myTime = d.getTime();
		
		//获取当前时间的毫秒值
		long nowTime = System.currentTimeMillis();
		
		//得到毫秒差值
		long time = nowTime - myTime;
		
		//将毫秒差值转换为天数
		int day = (int)(time/1000/60/60/24);
		System.out.println("你来到这个世界 " + day + " 天");
	}
}
