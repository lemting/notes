package Java_常见对象.日期时间类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * SimpleDateFormat类的构造方法
 * 		public SimpleDateFormat():默认模式  
 * 		public SimpleDateFormat(String pattern): 给定模式 
 * 
 * 		模式: 
 * 			G			Era标识符	eg:AD
 * 			y 			年	
 * 			M			年中的月
 * 			w			年中的周
 * 			W			月份中的周
 * 			D			年中的天
 * 			d			月份中的天
 * 			F			月份中的星期
 * 			E			星期中的天数
 * 			a			Am/pm标记
 * 			H			一天中的小时(0-23)
 * 			k			一天中的小时(1-24)
 * 			m			小时中的分钟数
 * 			s			分钟中的秒数
 * 			S			毫秒数	
 * 
 * 	SimpleDateFormat类的成员方法(继承自DateFormat类)
 * 		//Date -> String
 * 		public final String format(Date date): 将日期格式化成日期/时间字符串。
 * 		//String -> Date
 * 		public Date parse(String source)throws ParseException:从给定字符串的开始解析文本以生成日期. 该方法可能不会使用给定字符串的整个文本
 */

public class SimpleDateFormatDemo
{
	public static void main(String[] args) throws ParseException
	{
		Date d = new Date();
		//public SimpleDateFormat():默认模式 
		SimpleDateFormat sdf = new SimpleDateFormat();
		
		//public SimpleDateFormat(String pattern): 给定模式
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		
		//Date -> String
		//public final String format(Date date): 将日期格式化成日期/时间字符串。
		System.out.println("d: " + sdf.format(d));
		System.out.println("d: " + sdf2.format(d));
		System.out.println("---------------------");
		
		//String -> Date
		String str = "2018-02-22 15:32:24";
		//public Date parse(String source)throws ParseException:从给定字符串的开始解析文本以生成日期. 该方法可能不会使用给定字符串的整个文本
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dd = sdf3.parse(str);
		System.out.println("dd: " + dd);
	}
}
