package Java_常见对象.StringDemo;

/*
 * String类的其他功能
 * 	 
 * 	 去除字符串两端空格
 * 		String trim()
 * 
 * 	 按字典顺序比较两字符串
 * 		(返回第一个不同字符的ascii码差值,若两字符串长度相等,则返回0;若两字符串长度不等且相同长度部分相等,则返回两字符串长度差值)
 * 		int compareTo(String str):区分大小写
 * 		int compareToIgnoreCase(String str):不区分大小写
 * 
 * 	是否匹配给定的正则表达式
 * 		boolean matches(String regex):
 * 			
 */

public class String类的其他功能 {
	public static void main(String[] args) {
		//String replace(char old,char new):
		//String replace(String old,String new):
		String s1 = "helloworld";
		String s2 = s1.replace('l','k');
		String s3 = s1.replace("owo", "ak47");
		System.out.println("s1: " + s1);
		System.out.println("s2: " + s2);
		System.out.println("s3: " + s3);
		System.out.println("---------");
		
		//String trim():
		String s4 = " hello world ";
		String s5 = s4.trim();
		System.out.println("s4: " + s4 + "---");
		System.out.println("s5: " + s5 + "---");
		System.out.println("-----------------");
	
		//int compareTo(String str):比较第一个字符的ask码,若相同则比较第二个,...否则返回差值
		String s6 = "hello";
		String s7 = "hello";
		String s8 = "abc";
		String s9 = "xyz";
		System.out.println(s6.compareTo(s7));
		System.out.println(s6.compareTo(s8));
		System.out.println(s6.compareTo(s9));
		
	}
}
