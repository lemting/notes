package Java_常见对象.StringDemo;

/*
 * String类的判断功能
 * 
 * 	 boolean matches(String regex):是否匹配给定的正则表达式
 *	 boolean equals(Object obj):比较字符串的内容是否相同,区分大小写
 *	 boolean equalsIgnoreCase(String str):比较字符串的内容是否相同,不区分大小写
 *	 boolean contains(String str):判断大字符串中是否包含小字符串
 * 	 boolean startsWith(String str):判断字符串是否是以某个指定的字符串开头
 * 	 boolean endsWith(String str):判断字符串是否是以某个指定的字符串结尾
 * 	 boolean isEmpty():判断字符串是否为空
 * 		String s = "";//字符串为空
 * 		String s = null;//字符串对象为空,即空指针
 */

public class String类的判断功能 {
	public static void main(String[] args) {
		String s = "helloworld";
		String s2 = "helloworld";
		String s3 = "HelloWorld";
		
		//boolean equals(Object obj):比较字符串的内容是否相同,区分大小写
		System.out.println("equals: " + s.equals(s2));
		System.out.println("equals: " + s.equals(s3));
		System.out.println("-----------------------");
		
		//boolean equalsIgnoreCase(String str):比较字符串的内容是否相同,不区分大小写
		System.out.println("equalsIgnoreCase: " + s.equalsIgnoreCase(s2));
		System.out.println("equalsIgnoreCase: " + s.equalsIgnoreCase(s3));
		System.out.println("-----------------------");
		
		//boolean contains(String str):判断大字符串中是否包含小字符串
		System.out.println("contains: " + s.contains("hello"));
		System.out.println("contains: " + s.concat("hw"));
		System.out.println("--------------------------------");
		
		//boolean startsWith(String str):判断字符串是否是以某个指定的字符串开头
		System.out.println("startsWith: " + s.startsWith("h"));
		System.out.println("startsWith: " + s.startsWith("hello"));
		System.out.println("------------------------------------");
		
		// boolean endsWith(String str):判断字符串是否是以某个指定的字符串结尾
		System.out.println("endsWith: " + s.endsWith("hello"));
		System.out.println("endsWith: " + s.endsWith("world"));
		System.out.println("--------------------------------");
		
		//boolean isEmpty():判断字符串是否为空
		System.out.println("isEmpty: " + s.isEmpty());
		
		String s4 = "";
		//String s5 = null;
		System.out.println("isEmpty: " + s4.isEmpty());
		//空指针异常,s5对象不存在,不能调方法
		//System.out.println("isEmpty: " + s5.isEmpty());
	}
}
