package Java_常见对象.日期时间类.jdk8;

import java.time.Instant;

/*
 *  Instant 类, JDK8 以后可以替代 Date
 *  	时间戳, 类存储 long 表示划时代秒钟, 并且 int 表示纳秒的秒
 *  	java.time.Instant
 *  
 *  Instant 类的常量
 *  	static Instant EPOCH: 1970-01-01T00:00:00Z时刻的常数
 *  	static Instant MAX: 支持的最大值为 '1000000000-12-31T23:59:59.999999999Z' 
 *  	static Instant MIN: 支持的最小值为  '-100000000000-01-01T00:00Z'
 *	
 *	Instant 类的静态方法
 *		static Instant now(): 获取当前时间戳 Instant
 *		static Instant now​(Clock clock): 从指定时钟获取当前时间戳 Instant
 *		static Instant ofEpochMilli​(long epochMilli): 根据毫秒时间戳获取 Instant
 *		static Instant ofEpochSecond​(long epochSecond): 根据秒时间搓获取 Instant
 *		static Instant ofEpochSecond​(long epochSecond, long nanoAdjustment): 根据秒和纳秒时间戳获取 Instant
 *
 *		static Instant parse​(CharSequence text): 解析文本字符串获取 Instant
 *	
 *	Instant 类的比较方法
 *		int compareTo​(Instant otherInstant): 与其他  Instant 比较
 *		boolean equals​(Object otherInstant): 比较是否相等
 *		boolean isAfter​(Instant otherInstant): 比较是否在之后
 *		boolean isBefore​(Instant otherInstant): 比较是否在之前
 *		
 *	Instant 类的获取方法
 *		int get​(TemporalField field): 获取指定字段的值(仅支持部分, 如 ChronoField.NANO_OF_SECOND, MILLI_OF_SECOND)
 *		long getLong​(TemporalField field): 获取指定字段的值
 *		long getEpochSecond(): 获取秒时间戳
 *		long toEpochMilli()
 *		long getNano(): 获取纳秒值
 *		
 *	Instant 类的计算方法(返回新的对象, 原对象属性不变)
 *		Instant minus​(long amountToSubtract, TemporalUnit unit): 返回新的对象, 减去指定的数量
 *		Instant minus​(TemporalAmount amountToSubtract): 返回新的对象, 减去指定的数量
 *		Instant minusMillis​(long millisToSubtract): 返回新的对象, 减去指定毫秒
 *		Instant minusSeconds​(long secondsToSubtract): 返回新的对象, 减去指定秒		
 *		Instant minusNanos​(long nanosToSubtract): 返回新的对象, 减去指定纳秒
 *
 *		Instant plus/plusMillis/plusNanos/plusSeconds: 返回新的对象, 加
 *		
 *	Instant 类的设置方法
 *		Instant with​(TemporalAdjuster adjuster): 返回新的对象, 调整
 *		Instant with​(TemporalField field, long newValue): 返回新的对象, 将指定的字段设置为新值
 *		
 *		
 *
 */
public class InstantDemo {
	
	public static void main(String[] args) {
		Instant now = Instant.now();
		System.out.println("当前时间戳: " + now);
		System.out.println("秒数:"+now.getEpochSecond());
		System.out.println("毫秒数:"+now.toEpochMilli());
		System.out.println("纳秒数:"+now.getNano());
		System.out.println("当前前一秒: " + Instant.ofEpochSecond(now.getEpochSecond() - 1));
		System.out.println("当前后一秒: " + now.plusSeconds(1));
	}

}
