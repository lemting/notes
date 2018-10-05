package Java_常见对象.StringDemo;

/*
 * 构造方法: 
 *	 public String(): 空构造
 *	 public String(byte[] bytes):把字节数组转成字符串
 *	 public String(byte[] bytes,int index,int length):把字节数组的一部分转成字符串
 *	 public String(char[] value):把字符数组转成字符串
 *	 public String(char[] value,int index,int count):把字符数组的一部分转成字符串
 *	 public String(String original):把字符串常量值转成字符串
 */

public class String类的构造方法
{
	public static void main(String[] args)
	{
		//public String(): 空构造
		String s1 = new String();
		System.out.println("s1: " + s1);
		System.out.println("------------------");
		
		//public String(byte[] bytes):把字节数组转成字符串
		byte[] byte1 = {97,98,99,100,101};
		String s2 = new String(byte1);
		System.out.println("s2: " + s2);
		System.out.println("------------------");
		
		//public String(byte[] bytes,int index,int length):把字节数组的一部分转成字符串
		String s3 = new String(byte1,2,2);
		System.out.println("s3: " + s3);
		System.out.println("------------------");
		
		//public String(char[] value):把字符数组转成字符串
		char[] ch = {'h','e','l','l','o'};
		String s4 = new String(ch);
		System.out.println("s4: " + s4);
		System.out.println("------------------");
		
		//public String(char[] value,int index,int count):把字符数组的一部分转成字符串
		String s5 = new String(ch,2,2);
		System.out.println("s5: " + s5);
		System.out.println("------------------");
		
		//public String(String original):把字符串常量值转成字符串
		String s6 = new String("helloworld");
		System.out.println("s6: " + s6);
	}
}
