package Java_常见对象.基本类型包装类;

/*
 * jdk5后新特性: 简化了定义方式(自动装箱和自动拆箱)
 * 		Integer x = new Integer(100);可直接写为
 * 		Integer x = 100;//自动装箱   	Integer x = Integer.valueOf(100);
 * 		x = x + 5;//自动拆箱   		x = Integer.valueOf(x.intValue() + 5);
 * 		注意: 
 * 			在使用时,Integer x = null;上面的代码会出现 NUllPointerException
 * 		针对-128到127之间是数据,做了一个数据缓冲池,如果数据是在该范围内的,每次并不创建新的空间
 * 		Integer的数据赋值,如果在-128到127之间,会直接从缓冲池里获取数据
 * 			Integer i = Integer.valueOf(127); Integer i2 = Integer.valueOf(127); (i == i2) = true;(地址值一样)
 * 			Integer i3 = Integer.valueOf(128);Integer i4 = Integer.valueOf(128); (i3 == i4) = false;(地址值不一样)
 * 
 * Integer类是基本类型 int 的包装类
 * 
 * 	构造方法:
 * 		public Integer(int value):构造一个新分配的 Integer对象,该对象表示指定的 int值
 * 		public Integer(String s):构造一个新分配 Integer对象,表示 int由指示值 String参数(s必须是由数字字符组成的字符串)
 * 			
 * 	成员方法:
 * 		public static String toBinaryString(int i):返回整数参数的无符号二进制数值
 *		public static String toOctalString(int i):返回整数参数的无符号八进制数值
 *		public static String toHexString(int i):返回整数参数的无符号十六进制数值
 *		十进制到其他进制:public static String toString(int i,int radix): 转换范围: 二进制到三十六进制
 *		其他进制到十进制:public static int parseInt(String s,int radix)
 *		public int intValue():将 Integer的值作为 int 
 *		public static int parseInt(String s):String转int
 *		public static String toString(int i):int转String
 *		public static Integer valueOf(int i):类似构造public Integer(int value)
 *		public static Integer valueOf(String s):类似构造public Integer(String s)
 *
 *	成员变量:
 *		public static final MAX_VALUE: int类型的最大值 (2^31 - 1)
 *		public static final MIN_VALUE: int类型的最大值 (-2^31)
 *---------------------------
 *
 *	String转int
 *		public static int parseInt(String s):	Integer.parseInt(s);
 *		
 *	int转String
 *		public static String valueOf(int i): 	String.valueOf(i);(推荐使用)
 *		public static String toString(int i):	Integer.toString(i);
 *	
 *---------------------------
 *	float转String
 *		public static String valueOf(float f): 	String.valueOf(f);(推荐使用)
 *		public static String toString(float f):	Float.toString(f);
 *
 *	String转float
 *		public static float parseFloat(String s):	Float.parseFloat(s);
 *---------------------------
 *	其他基本类型的包装类类似
 *---------------------------
 */

public class IntegerDemo
{
	public static void main(String[] args)
	{
		//public Integer(int value):构造一个新分配的 Integer对象,该对象表示指定的 int值
		int i = 100;
		Integer ii = new Integer(i);
		System.out.println("ii: " + ii);
		System.out.println("-------------------------");
		
		//public Integer(String s):构造一个新分配 Integer对象,表示 int由指示值 String参数
		//String s = "abc"; //会报错  必须是由数字字符组成的字符串
		String s = "100";
		Integer ss = new Integer(s);
		System.out.println("ss: " + ss);
		System.out.println("-------------------------");
		
		//public static String toBinaryString(int i):返回整数参数的无符号二进制数值
		System.out.println(Integer.toBinaryString(100));
		
		//public static String toOctalString(int i):返回整数参数的无符号八进制数值
		System.out.println(Integer.toOctalString(100));
		
		//public static String toHexString(int i):返回整数参数的无符号十六进制数值
		System.out.println(Integer.toHexString(100));
		System.out.println("-----------------------");
		
		//十进制到其他进制:public static String toString(int i,int radix)
		System.out.println("十进制到五进制: 100 -- " + Integer.toString(100,5));
		//其他进制到十进制:public static int parseInt(String s,int radix)
		System.out.println("五进制到十进制: 400 -- " + Integer.parseInt("400",5));
		System.out.println("-----------------------");
		
		//
		
		//public static final MAX_VALUE: int类型的最大值 (2^31 - 1)
		System.out.println(Integer.MAX_VALUE);
		//public static final MIN_VALUE: int类型的最大值 (-2^31)
		System.out.println(Integer.MIN_VALUE);
	}
}
