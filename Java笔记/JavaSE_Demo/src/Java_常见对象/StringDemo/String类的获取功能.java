package Java_常见对象.StringDemo;

/*
 * String类的获取功能
 * 
 *	 int length(): 获取字符串的长度
 *	 char charAt(int index): 获取指定索引位置的字符
 *	 int indexOf(int ch): 返回指定字符在此字符串中第一次出现处的索引
 *	 int indexOf(String str): 返回指定字符串在此字符串中第一次出现处的索引
 *	 int indexOf(int ch,int fromIndex): 返回指定字符在此字符串中从指定位置后第一次出现处的索引	- 
 *	 int indexOf(String str,int fromIndex): 返回指定字符串在此字符串中从指定位置后第一次出现处的索引
 *	 String substring(int start): 从指定位置开始截取字符串,默认到末尾
 *	 String substring(int start,int end): 从指定位置开始到指定位置结束截取字符串
 */


public class String类的获取功能
{
	public static void main(String[] args)
	{
		//定义一个字符串对象
		String s = "helloworld";
		
		//int length(): 获取字符串的长度
		System.out.println("s.length: " + s.length());
		System.out.println("------------------------");
		
		//char charAt(int index):获取指定索引位置的字符
		System.out.println("charAt: " + s.charAt(7));
		System.out.println("------------------------");
		
		//int indexOf(int ch):返回指定字符在此字符串中第一次出现处的索引
		System.out.println("indexOf: " + s.indexOf('l'));
		System.out.println("--------------------------");
		
		//int indexOf(String str):返回指定字符串在此字符串中第一次出现处的索引
		System.out.println("indexOf: " + s.indexOf("llo"));
		System.out.println("----------------------------");
	
		//int indexOf(int ch,int fromIndex):返回指定字符在此字符串中从指定位置后第一次出现处的索引
		System.out.println("indexOf: " + s.indexOf('l',3));
		System.out.println("----------------------------");
		
		//int indexOf(String str,int fromIndex):返回指定字符串在此字符串中从指定位置后第一次出现处的索引
		System.out.println("indexOf: " + s.indexOf("rld",4));
		System.out.println("------------------------------");
		
		//String substring(int start):从指定位置开始截取字符串,默认到末尾
		System.out.println("substring: " + s.substring(3));
		System.out.println("----------------------------");
	
		//String substring(int start,int end):从指定位置开始到指定位置结束截取字符串
		System.out.println("substring: " + s.substring(3,6));
	}
}
