package Java_IO流.字节字符缓冲流;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Test_字节缓冲流复制文件
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要复制的文件绝对路径: ");
		String srcString = sc.nextLine();
		System.out.print("请输入要复制到的文件绝对路径: ");
		String destString = sc.nextLine();
		
		long l1 = System.currentTimeMillis();
		try
		{method(srcString,destString);}
		catch (IOException e)
		{e.printStackTrace();}
		long l2 = System.currentTimeMillis();
		System.out.println(l2 - l1);
	}
	
	public static void method(String srcString,String destString) throws IOException
	{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));
		
		byte[] bytes = new byte[1024*1024*5];
		int len = 0;
		while((len = bis.read(bytes)) != -1)
		{
			bos.write(bytes,0,len);
			bos.flush();
		}
		bis.close();
		bos.close();
	}
}
