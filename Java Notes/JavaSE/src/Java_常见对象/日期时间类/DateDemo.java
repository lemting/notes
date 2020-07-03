package Java_常见对象.日期时间类;

import java.util.Date;

/*
 * Date	 表示特定的瞬间,精确到毫秒。 毫秒值表示1970年1月1日00：00：00.000 GMT之后的毫秒数
 * 
 * Date类的构造方法
 * 		public Date():根据当前的默认毫秒值创建日期对象
 * 		public Date(long date): 根据给定的毫秒值创建日期对象
 * 
 * Date类的成员方法
 * 		public long getTime():获取时间
 * 		public void setTime():设置时间
 */

public class DateDemo {
	public static void main(String[] args) {
		//public Date():根据当前的默认毫秒值创建日期对象
		Date d = new Date();
		System.out.println("d: " + d);
		
		//public Date(long date): 根据给定的毫秒值创建日期对象
		long time = 1000 * 60 * 60; // 1小时
		Date d2 = new Date(time);
		System.out.println("d2: " + d2);
		System.out.println("---------------");
		
		//public long getTime():获取时间
		Date d3 = new Date();
		System.out.println("getTime: " + d3.getTime());//System.out.println(System.currentTimeMillis());
		
		//public void setTime():设置时间
		d3.setTime(time);
		System.out.println("getTime: " + d3.getTime());
		
	}
}
