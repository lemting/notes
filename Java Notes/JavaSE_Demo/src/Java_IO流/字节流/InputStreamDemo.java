package Java_IO流.字节流;

import java.io.FileInputStream;
import java.io.IOException;

/*	
 * InputStream是字节流的顶层抽象类,字节输入
 * 
 * InputStream抽象类的一个子类: 
 * 		FileInputStream,XxxInputStream
 * 		
 * 		FileInputStream: 从文件系统中的文件获取输入字节
 * 		FileInputStream的构造方法: 
 * 			public FileInputStream(String name): 通过打开与实际文件的连接创建一个FileInputStream文件，该文件由文件系统中的路径名name命名
 * 
 * InputStream抽象类的方法:
 * 		public abstract int read(): 从输入流读取数据的下一个字节
 * 		public int read(byte[] b): 从输入流读取一些字节数，并将它们存储到缓冲区b
 * 		public int available(): 返回从该输入流中可以读取（或跳过）的字节数的估计值，而不会被下一次调用此输入流的方法阻塞
 * 		public void close(): 关闭此输入流并释放与流相关联的任何系统资源
 */

public class InputStreamDemo
{
	public static void main(String[] args) throws IOException
	{
		//public FileInputStream(String name): 通过打开与实际文件的连接创建一个FileInputStream文件，该文件由文件系统中的路径名name命名
		FileInputStream fis = new FileInputStream("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\字节流\\InputStreamDemo.java");
		@SuppressWarnings("resource")
		FileInputStream fis2 = new FileInputStream("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\字节流\\InputStreamDemo.java");
		//public abstract int read(): 从输入流读取数据的下一个字节
		//char by = (char)fis.read();
		//System.out.print(by);
		//by = (char)fis.read();
		//System.out.print(by);
		
		//public abstract int read(): 从输入流读取数据的下一个字节
		//一次读取一个字节
		int by = 0;
		while((by = fis.read()) != -1)
			System.out.print((char)by);
		System.out.println("-----------------------");
		
		//public int read(byte[] b): 从输入流读取一些字节数，并将它们存储到缓冲区b
		//一次读取一个字节数组
		//数组的一般的长度为1024或1024的整数倍
		byte[] by2 = new byte[1024];
		int len = 0;
		while((len = fis2.read(by2)) != -1)
			System.out.print(new String(by2,0,len));
		
		//public int available(): 返回从该输入流中可以读取（或跳过）的字节数的估计值，而不会被下一次调用此输入流的方法阻塞
		
		//释放资源
		fis.close();
	}
}


