package Java_IO流.IO流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 	A: 字符流一次读写一个字符
 * 	B: 字符流一次读写一个字符数组
 * 	C: 字符缓冲流一次读写一个字符
 *  D: 字符缓冲流一次读写一个字符数组
 *  E: 字符缓冲流一次读写一个字符串
 *  
 */

public class Test_IO流复制文本文件的5种方式
{
	public static void main(String[] args) throws IOException
	{
		new File("E:\\demo").mkdirs();
		BufferedReader br = new BufferedReader(new FileReader("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\IO流\\Test_IO流复制文本文件的5种方式.java"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\demo\\a.txt"));
		
		String str = null;
		while((str = br.readLine()) != null)
		{
			bw.write(str);
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		br.close();
		
		String srcString = "E:\\demo\\a.txt";
		String destString1 = "E:\\demo\\AA.txt";
		String destString2 = "E:\\demo\\B.txt";
		String destString3 = "E:\\demo\\C.txt";
		String destString4 = "E:\\demo\\D.txt";
		String destString5 = "E:\\demo\\E.txt";
		
		//A: 字符流一次读写一个字符
		method1(srcString,destString1);
		//B: 字符流一次读写一个字符数组
		method2(srcString,destString2);
		//C: 字符缓冲流一次读写一个字符
		method3(srcString,destString3);
		//D: 字符缓冲流一次读写一个字符数组
		method4(srcString,destString4);
		//E: 字符缓冲流一次读写一个字符串
		method5(srcString,destString5);
		
		//删除
		System.out.println("delete: " + srcString + "\t" + new File(srcString).delete());
		System.out.println("delete: " + destString1 + "\t" + new File(destString1).delete());
		System.out.println("delete: " + destString2 + "\t" + new File(destString2).delete());
		System.out.println("delete: " + destString3 + "\t" + new File(destString3).delete());
		System.out.println("delete: " + destString4 + "\t" + new File(destString4).delete());
		System.out.println("delete: " + destString5 + "\t" + new File(destString5).delete());
		System.out.println("delete: " + "E:\\demo" + "\t\t" + new File("E:\\demo").delete());
		
	}
	
	//字符流一次读写一个字符
	public static void method1(String srcString, String destString) throws IOException
	{
		FileReader fr = new FileReader(srcString);
		FileWriter fw = new FileWriter(destString);
		
		int ch = 0;
		while((ch = fr.read()) != -1)
			fw.write(ch);
		
		fr.close();
		fw.close();
	}
	
	//字符流一次读写一个字符数组
	public static void method2(String srcString, String destString) throws IOException
	{
		FileReader fr = new FileReader(srcString);
		FileWriter fw = new FileWriter(destString);
		
		char[] chs = new char[1024];
		int len = 0;
		while((len = fr.read(chs)) != -1)
		{
			fw.write(chs,0,len);
			fw.flush();
		}
		
		fr.close();
		fw.close();
	}
	
	//字符缓冲流一次读写一个字符
	public static void method3(String srcString, String destString) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(srcString));
		BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
		
		int ch = 0;
		while((ch = br.read()) != -1)
		{
			bw.write((char)ch);
		}
		
		br.close();
		bw.close();
	}
	
	//字符缓冲流一次读写一个字符数组
	public static void method4(String srcString, String destString) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(srcString));
		BufferedWriter bw = new BufferedWriter(new FileWriter(destString));
		
		char[] chs = new char[1024];
		int len = 0;
		while((len = br.read(chs)) != -1)
			bw.write(new String(chs,0,len));
		
		br.close();
		bw.close();
	}
	
	//字符缓冲流一次读写一个字符串
	public static void method5(String srcString, String destString) throws IOException
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
