package Java_常见对象.日期时间类;

import java.text.ParseException;
import java.util.Date;


public class Test_自建类DateUtil的测试
{
	public static void main(String[] args) throws ParseException
	{
		Date d = new Date();
		String s = Test_自建类DateUtil工具类.dateToString(d, "yyyy年MM月dd日 HH:mm:ss");
		System.out.println("s: " + s);
		
		String s2 = Test_自建类DateUtil工具类.dateToString(d, "yy年MM月dd日");
		System.out.println("s2: " + s2);
		
		String s3 = "2018年02月22日 15:52:40";
		Date dd = Test_自建类DateUtil工具类.stringToDate(s3, "yyyy年MM月dd日 HH:mm:ss");
		System.out.println("dd: " + dd);
	}
}