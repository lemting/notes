package Java_常见对象.StringDemo;

import java.util.Scanner;

public class Test_首字母大写其他小写 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入一个字符串: ");
		String str = sc.nextLine();
		String s1 = (String)str.subSequence(0,1);
		String s2 = str.substring(1);
		str = s1.toUpperCase() + s2.toLowerCase();
		System.out.println("str: " + str);
		str = str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
		System.out.println("str: " + str);
		sc.close();
	}
}
