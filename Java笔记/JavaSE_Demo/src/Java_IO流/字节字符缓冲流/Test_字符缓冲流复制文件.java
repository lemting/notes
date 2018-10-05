package Java_IO流.字节字符缓冲流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test_字符缓冲流复制文件
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要复制的文件绝对路径: ");
		String srcString = sc.nextLine();
		System.out.print("请输入要复制到的文件绝对路径: ");
		String destString = sc.nextLine();
		
		try
		{method(srcString,destString);}
		catch (IOException e)
		{e.printStackTrace();}
		
	}

	public static void method(String srcString, String destString) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(srcString));
		BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
		
		String str = null;
		while((str = br.readLine()) != null)
		{
			bw.write(str);
			bw.newLine();
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
