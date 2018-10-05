package Java_常见对象.基本类型包装类;

/*
 * 请看IntegerDemo.java或基本类型包装类.java
 * 
 *  Character类的成员方法:
 *  	public static boolean isUpperCase(char ch):判断字符是否是大写字符
 *  	public static boolean isLowerCase(char ch):判断字符是否是小写字符
 *  	public static boolean idDigit(char ch):判断字符是否是数字字符
 *  	public static char toUpperCase(char ch):将字符转成大写
 *  	public static char toLowerCase(char ch):将字符转成小写
 */

public class CharacterDemo
{
	public static void main(String[] args)
	{
		//public static boolean isUpperCase(char ch):判断字符是否是大写字符
		System.out.println("isUpperCase: " + Character.isUpperCase('A'));
		System.out.println("isUpperCase: " + Character.isUpperCase('a'));
		System.out.println("------------------------------------------");
		
		//public static boolean isLowerCase(char ch):判断字符是否是小写字符
		System.out.println("isLowerCase: " + Character.isLowerCase('A'));
		System.out.println("isLowerCase: " + Character.isLowerCase('a'));
		System.out.println("------------------------------------------");
		
		//public static boolean idDigit(char ch):判断字符是否是数字字符
		System.out.println("idDigit: " + Character.isDigit('1'));
		System.out.println("------------------------------------------");
		
		//public static char toUpperCase(char ch):将字符转成大写
		System.out.println("toUpperCase: " + Character.toUpperCase('a'));
		System.out.println("-----------------------------------------");
		
		//public static char toLowerCase(char ch):将字符转成小写
		System.out.println("toLowerCase: " + Character.toLowerCase('a'));
		
	}
}
