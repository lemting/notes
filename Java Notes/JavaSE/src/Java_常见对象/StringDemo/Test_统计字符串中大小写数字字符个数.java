package Java_常见对象.StringDemo;

import java.util.Scanner;

public class Test_统计字符串中大小写数字字符个数 {
	public static void main(String[] args) {
		int num = 0,A = 0,a = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入一个字符串: ");
		String s = sc.nextLine();
		for(int i = 0;i < s.length();i++) {
			char ch = s.charAt(i);
			/*if(ch >= 'a' && ch <= 'z')
				a++;
			else if(ch >= 'A' && ch <= 'Z')
				A++;
			else if(ch >= '0' && ch <= '9')
				num++;*/
			if(Character.isLowerCase(ch))
				a++;
			else if(Character.isUpperCase(ch))
				A++;
			else if(Character.isDigit(ch))
				num++;
		}
		System.out.println("该字符串中大写字母有 " + A + " 个,小写字母有 " + a + " 个,数字有 " + num + " 个");
		sc.close();
	}
}
