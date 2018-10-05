package Java_常见对象.StringBuffer类和StringBuilder类;

/*
 * StringBuffer类的构造方法
 * 
 * 	 public StringBuffer():无参构造,默认容量为16
 * 	 public StringBuffer(int capacity):指定容量的字符串缓冲区对象
 *	 public StringBuffer(String str):指定字符串内容的字符串缓冲区对象(容量为: 16 + str.length())
 *
 *StringBuffer类的方法
 *
 *	 public int capacity():返回当前容量.   理论值
 *	 public int length():返回长度(字符数). 实际值
 */

public class StringBuffer类的构造方法
{
	public static void main(String[] args)
	{
		//public StringBuffer():无参构造,默认容量为16
		StringBuffer sb1 = new StringBuffer();
		System.out.println("sb1.capacity(): " + sb1.capacity());
		System.out.println("sb1.length(): " + sb1.length());
		System.out.println("---------------------------------");
		
		//public StringBuffer(int capacity):指定容量的字符串缓冲区对象
		StringBuffer sb2 = new StringBuffer(32);
		System.out.println("sb2.capacity(): " + sb2.capacity());
		System.out.println("sb2.length(): " + sb2.length());
		System.out.println("---------------------------------");
		
		//public StringBuffer(String str):指定字符串内容的字符串缓冲区对象
		String str = "hello";
		StringBuffer sb3 = new StringBuffer(str);
		System.out.println("sb3.capacity(): " + sb3.capacity());
		System.out.println("sb3.length(): " + sb3.length());
	}
}
