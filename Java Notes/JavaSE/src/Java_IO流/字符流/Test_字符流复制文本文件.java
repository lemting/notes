package Java_IO流.字符流;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 *  复制文本文件
 * 
 * 		数据源: 从哪里来
 * 			a.txt ---  读取数据 --- FileReader	
 * 	
 * 		目的地: 到哪里去
 * 			b.txt ---  写数据    --- FileWriter
 * 
 */

public class Test_字符流复制文本文件 {
	public static void main(String[] args) throws IOException {
		new File("E:\\demo").mkdirs();
		FileOutputStream f = new FileOutputStream("E:\\demo\\a.txt", true);
		f.write("Hello World Java 哇咔咔 adsadadsdwqasfa".getBytes());

		// 封装数据源
		FileReader isr = new FileReader("E:\\demo\\a.txt");
		// 封装，目的地
		FileWriter osw = new FileWriter("E:\\demo\\b.txt");

		// 一次读取写入一个字节数组
		// 数组的一般的长度为1024或1024的整数倍
		char[] ch2 = new char[1024];
		int len = 0;
		while ((len = isr.read(ch2)) != -1) {
			osw.write(ch2, 0, len);
			// 刷新流
			osw.flush();
		}

		// 释放资源(先关谁都可以)
		f.close();
		isr.close();
		osw.close();

		// 删除
		System.out.println("delete: " + new File("E:\\demo\\b.txt").delete());
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}
