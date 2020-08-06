package Java_常见对象.日期时间类.jdk8;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/*
 *  LocalDateTime 类, JDK8 以后可以替代 Calendar
 * 		不可变的日期时间对象, 
 * 		java.time.LocalDateTime 
 * 
 *  LocalDateTime 类的静态方法
 *  	static LocalDateTime now(): 获取当前日期时间
 *  	static LocalDateTime of​(int year, int month, int dayOfMonth, int hour, int minute): 指定年月日时分
 *  	static LocalDateTime of​(int year, int month, int dayOfMonth, int hour, int minute, int second): 指定年月日时分秒
 *  	static LocalDateTime of​(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond): 指定年月日时分秒纳秒
 *  	static LocalDateTime of​(int year, Month month, 日,时,分[,秒[,纳秒]]): 指定年月日时分秒纳秒, Month.JANUARY(一月), Month.FEBRUARY(二月),..
 *  	static LocalDateTime of​(LocalDate date, LocalTime time): 根据 LocalDate 和 LocalTime
 *  	static LocalDateTime ofEpochSecond​(long epochSecond, int nanoOfSecond, ZoneOffset offset): 根据毫秒时间戳,纳秒,时区
 *  	static LocalDateTime ofInstant​(Instant instant, ZoneId zone): 根据时间戳,时区
 *  	
 *  	static LocalDateTime parse​(CharSequence text): 解析文本字符串获取 LocalDateTime
 *  	static LocalDateTime parse​(CharSequence text, DateTimeFormatter formatter): 指定 DateTimeFormatter 解析文本字符串获取 LocalDateTime
 *  
 *  LocalDateTime 类的比较方法
 * 		int compareTo​(ChronoLocalDateTime<?> other): 比较
 * 		boolean isAfter​(ChronoLocalDateTime<?> other): 比较是否在之后
 * 		boolean isBefore​​(ChronoLocalDateTime<?> other): 比较是否在之前
 * 		isEqual​(ChronoLocalDateTime<?> other): 比较是否相等
 * 	
 * 	LocalDateTime 类的获取方法
 * 		int get​(TemporalField field): 指定字段的值 ()
 * 		int getYear/getMonthValue/getDayOfMonth(): 年/月/日
 * 		Month getMonth(): 月的枚举值
 * 		int getHour/getMinute/getSecond/getNano(): 时/分/秒/纳秒
 * 		LocalDate toLocalDate(): 获取 LocalDate
 * 		LocalTime toLocalTime(): 获取 LocalTime
 * 	
 * 	LocalDateTime 类的计算方法(返回新的对象, 原对象属性不变)
 * 		LocalDateTime minus​(long amountToSubtract, TemporalUnit unit): 返回新的对象, 减去指定的数量
 * 		LocalDateTime minus​(TemporalAmount amountToSubtract): 返回新的对象, 减去指定的数量
 * 		LocalDateTime minusYears​/minusMonths/minusDays(long years): 返回新的对象, 减去年/月/日
 * 		LocalDateTime minusHours/minusMinutes/minusSeconds/minusNanos​(long nanos): 返回新的对象, 减去时/分/秒/纳秒
 * 		
 * 		plus/plusYears/plusMonths/plusDays/.. : 加
 * 
 * 	LocalDateTime 类的设置方法
 * 		LocalDateTime with​(TemporalAdjuster adjuster): 返回新的对象, 调整
 * 		LocalDateTime with​(TemporalField field, long newValue): 返回新的对象, 设置指定字段
 * 		LocalDateTime withYear​/withMonth​/withDayOfMonth(int year): 返回新的对象, 设置年/月/日
 * 		LocalDateTime withHour/withMinute​/withSecond​/withNano​(int hour): 返回新的对象, 设置时/分/秒/纳秒
 * 	
 * 	LocalDateTime 类的格式化
 * 		String format(DateTimeFormatter formatter): 格式化
 * 
 */
public class LocalDateTimeDemo {
	public static void main(String[] args) {
		LocalDateTime ni = LocalDateTime.of(1999, Month.JULY, 30, 10, 2, 59, 55555);
		System.out.println("自定义时间: " + ni);
		System.out.println("修改到2050年: " + ni.with(ChronoField.YEAR, 2050));
		System.out.println("获取年月日: " + ni.getYear()+ "-" + ni.get(ChronoField.MONTH_OF_YEAR) + "-" + ni.getDayOfMonth());
		System.out.println("今年前一年: " + LocalDateTime.now().minusYears(1));
		System.out.println("根据格式解析: " + LocalDateTime.parse("2020-05-10 12:31:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("格式化输出: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
}
