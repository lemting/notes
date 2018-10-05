package Java_常见对象.StringDemo;

/*
 * String类的转换功能
 * 
 *	 byte[] getBytes():把字符串转换为字节数组
 *	 char[] toCharArray():把字符串转换成字符数组
 *	 static String valueOf(char[] chs):把字符数组转为字符串(该方法可将任意数据类型转换为字符串)
 *	 static String valueOf(int i):把int类型的数据转为字符串(该方法可将任意数据类型转换为字符串)
 *	 String toLowerCase():把字符串转成小写
 *	 String toUpperCase():把字符串转为大写
 *	 String concat(String str):把字符串拼接
 */

public class String类的转换功能
{
	public static void main(String[] args)
	{
		String s = "HelloWorld";
		//byte[] getBytes():把字符串转换为字节数组
		byte[] byte1 = s.getBytes();
		for(int x = 0;x < byte1.length;x++)
			System.out.println(byte1[x]);
		System.out.println("\n---------------");
		
		//char[] toCharArray():把字符串转换成字符数组
		char[] ch = s.toCharArray();
		for(int x = 0;x < ch.length;x++)
			System.out.println(ch[x] + '\t');
		System.out.println("--------------");
		
		//static String valueOf(char[] chs):把字符数组转为字符串
		String s2 = String.valueOf(ch);
		System.out.println("s2: " + s2);
		System.out.println("---------");
		
		//static String valueOf(int i):把int类型的数据转为字符串
		String s3 = String.valueOf(100);
		System.out.println("s3: " + s3);
		System.out.println("---------");
		
		//String toLowerCase():把字符串转成小写
		System.out.println("s.toLowerCase: " + s.toLowerCase());
		System.out.println("---------");
		
		//String toUpperCase():把字符串转为大写
		System.out.println("s.toUpperCase: " + s.toUpperCase());
		System.out.println("---------");
		
		//String concat(String str):把字符串拼接
		String ss = "hello";
		String ss2 = "world";
		String ss3 = ss.concat(ss2);
		System.out.println("ss3: " + ss3);
	}
}
