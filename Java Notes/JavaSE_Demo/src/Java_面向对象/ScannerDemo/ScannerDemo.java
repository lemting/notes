package Java_面向对象.ScannerDemo;

import java.util.Scanner;

public class ScannerDemo
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println(x);
		System.out.println(sc.hasNextLine());  
		System.out.println((int)'\t');
		String s = "asd";
		System.out.println(s);
		s = "asdasd";
		System.out.println(s);
	}
}
