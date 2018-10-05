package Java_常见对象.PatternDemo_正则表达式;

/*
 *	 正则表达式的替换功能
 *
 *		String类的public String replaceAll(String regex,String replacement): 用给定的replacement替换此字符串所有匹配正则表达式的子字符串。
 */

public class 正则表达式的替换功能
{
	public static void main(String[] args)
	{
		//定义一个字符串
		String s = "helloqq12345worldkh622112345678java"; 

		//我要去除所有的的数字,用 * 替换
		String regex = "\\d";
		String ss = "*";
		String result = s.replaceAll(regex,ss);
		System.out.println("result: " + result);
	}
}
