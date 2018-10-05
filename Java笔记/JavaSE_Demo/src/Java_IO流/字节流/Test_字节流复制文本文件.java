package Java_IO流.字节流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *  复制文本文件
 * 
 * 		数据源: 从哪里来
 * 			a.txt ---  读取数据 --- FileInputStream	
 * 	
 * 		目的地: 到哪里去
 * 			b.txt ---  写数据    --- FileOutputStream
 * 
 */

public class Test_字节流复制文本文件
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		new File("E:\\demo").mkdirs();
		FileOutputStream f = new FileOutputStream("E:\\demo\\a.txt",true);
		f.write("Hello World Java 哇咔咔".getBytes());
		
		//封装数据源
		FileInputStream fis = new FileInputStream("E:\\demo\\a.txt");
		//封装，目的地
		FileOutputStream fos = new FileOutputStream("E:\\demo\\b.txt");
		
		////一次读取写入一个字节
		//int by = 0;
		//while((by = fis.read()) != -1)
		//	fos.write(by);
		
		//一次读取写入一个字节数组
		//数组的一般的长度为1024或1024的整数倍
		byte[] by2 = new byte[1024];
		int len = 0;
		while((len = fis.read(by2)) != -1)
			fos.write(by2,0,len);
		
		//释放资源(先关谁都可以)
		f.close();
		fis.close();
		fos.close();
		
		//删除
		System.out.println("delete: " + new File("E:\\demo\\b.txt").delete());
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}
