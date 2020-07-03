package Java_常见对象.StringBuffer类和StringBuilder类;

import java.util.Scanner;

public class Test_判断对称字符串 {
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("asdsadsadsa");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入一个字符串: ");   
		String s = sc.nextLine();
		if(isSame(s))
			System.out.println("这个字符串 是 对称字符串");
		else 
			System.out.println("这个字符串 不是 对称字符串");
		System.out.println("a: " + sb.toString());
		sc.close();
	}
	
	public static boolean isSame(String str) {
 		return new StringBuffer(str).reverse().toString().equals(str);
	}
}

