package Java_常见对象.日期时间类.jdk8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 *  DateTimeFormatter 类, JDK8 以后可以替代 SimpleDateFormat
 * 
 * 	静态方法
 * 		static DateTimeFormatter ofPattern​(String pattern): 指定格式化规则
 * 
 */
public class DateTimeFormatterDemo {
	public static void main(String[] args) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(LocalDateTime.now().format(dateTimeFormatter));
	}
}
