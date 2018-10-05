package Java_IO流.字节字符缓冲流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * BufferedReader: 字符缓冲输入流
 * 		从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取
 * 
 * BufferedReader的构造方法: 
 * 		public BufferedReader(Reader in)创建使用默认大小的输入缓冲区的缓冲字符输入流
 * 		public BufferedReader(Reader in,int sz): 创建使用指定大小的输入缓冲区的缓冲字符输入流
 *		默认值足够大，可用于大多数用途
 * BufferedReader的特殊功能:
 * 		public String readLine(): 读一行文字。 一行被视为由换行符（'\ n'），回车符（'\ r'）中的任何一个或随后的换行符终止
 * 
 * 
 * BufferedWriter: 字符缓冲输出流
 * 		将文本写入字符输出流，缓冲字符，以提供单个字符，数组和字符串的高效写入
 * 	
 * BufferedWriter的构造方法: 
 * 		public BufferedWriter(Writer out): 创建使用默认大小的输出缓冲区的缓冲字符输出流
 * 		public BufferedWriter(Writer out,int sz): 创建一个新的缓冲字符输出流，使用给定大小的输出缓冲区
 * 		默认值足够大，可用于大多数用途
 * BufferedWriter的特殊功能:
 * 		public void newLine(): 写一行行分隔符
 */

public class 字符缓冲流
{
	public static void main(String[] args) throws IOException
	{
		//public BufferedReader(Reader in)创建使用默认大小的输入缓冲区的缓冲字符输入流
		BufferedReader br = new BufferedReader(new FileReader("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\字节字符缓冲流\\字符缓冲流.java"));
		
		//读取数据
		String str = null;
		//public String readLine(): 读一行文字。 一行被视为由换行符（'\ n'），回车符（'\ r'）中的任何一个或随后的换行符终止
		while((str = br.readLine()) != null)
			System.out.println(str);
		
		//释放资源
		br.close();
		System.out.println("-------------------------------------");
		
		new File("E:\\demo").mkdirs();
		new File("E:\\demo\\a.txt").createNewFile();
		
		//public BufferedWriter(Writer out): 创建使用默认大小的输出缓冲区的缓冲字符输出流
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\demo\\a.txt"));
		
		//写数据
		bw.write("Hello World Java");
		//public void newLine(): 写一行行分隔符
		bw.newLine();
		bw.write("Hello World Java");
		
		//关闭资源
		bw.close();
		System.out.println("---------------------------------------");
		
		//删除
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}
