package Java_IO流.IO流;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 
 */

public class Test_实现键盘录入 {
	public static void main(String[] args) throws IOException {
		// 获取标准输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("请输入一个字符串: ");
		String line = br.readLine();
		System.out.println("你输入的字符串是: " + line);

		System.out.print("请输入一个整数: ");
		int i = Integer.parseInt(br.readLine());
		System.out.println("你输入的整数是: " + i);
	}
}
