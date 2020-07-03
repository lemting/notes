package Java_常见对象.日期时间类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 这是日期和字符串相互转换的工具类
 * 
 * @author Lemting
 */

public class Test_自建类DateUtil工具类 {
	private Test_自建类DateUtil工具类(){}
	
	/**
	 * 这个方法的作用是把日期转成一个字符串
	 * @param d 被转换的日期对象
	 * @param pattern 传递过来的要被转换的格式
	 * @return 格式化后的字符串
	 */
	public static String dateToString(Date d,String pattern) {
		return new SimpleDateFormat(pattern).format(d);
	}
	
	/**
	 * 这个方法的作用是把一个字符串解析成日期
	 * @param str 要解析的字符串
	 * @param pattern 传过来的解析格式
	 * @return 解析后的日期对象
	 * @throws ParseException 跑出异常
	 */
	public static Date stringToDate(String str,String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(str);
	}
}