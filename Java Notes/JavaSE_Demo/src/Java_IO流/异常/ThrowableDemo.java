package Java_IO流.异常;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Throwable的几个常见的方法
 * 		
 * 		public String getMessage(): 获取异常信息,返回字符串
 * 		public String toString(): 获取异常类名和异常信息,返回字符串
 * 				结果是: 这个对象的类的name+": "+调用这个对象的getLocalizedMessage()方法的结果,如果getLocalizedMessage返回null,那么只返回类名
 * 		public String getLocalizedMessage(): 创建此可抛出的本地化描述.子类可以覆盖此方法,以生成特定于区域的消息.
 * 				对于不重写此方法的子类,默认实现返回相同的结果为getMessage() 
 * 		public void printStackTrace(): 获取异常类名和异常信息,以及异常出现在程序中的位置,返回值void
 * 		public void printStackTrace(PrintStream s): 通常用该方法将异常内容保存在日志文件中,一遍查阅
 */

public class ThrowableDemo
{
	public static void main(String[] args)
	{
		String s = "2014-11-20";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try
		{
			Date d = sdf.parse(s); 
			System.out.println(d);
		} catch (ParseException e)
		{
			//ParseException
			//e.printStackTrace();
			
			//getMessage
			System.out.println(e.getMessage());//Unparseable date: "2014-11-20"
			
			//toString
			System.out.println(e.toString());//java.text.ParseException: Unparseable date: "2014-11-20"
			
			//printStackTrace
			e.printStackTrace();
		}
	}
}