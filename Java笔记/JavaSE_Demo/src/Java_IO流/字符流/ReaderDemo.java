package Java_IO流.字符流;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/*
 * Reader抽象类的方法:
 * 		public int read(): 读取一个字符		
 * 		public int read(char[] cbuf): 读取一个字符数组  
 * 
 * Reader抽象类的子类: InputStreamReader//转换流: 将字节流转换成字符流
 * 
 * 		InputStreamWriter的构造方法:
 * 			public InputStreamReader(InputStream in): 根据默认字符集把字节流转换成字符流
 * 			public InputStreamReader(InputStream in,String charsetName): 根据指定字符集把字节流转换成字符流 
 * 
 * 		InputStreamReader类的子类: FileReader 
 * 			
 * 			FileReader类的构造方法: 
 * 				public FileReader(File file): 创建一个新的 FileReader ，给定 File读取
 * 				public FileReader(String fileName): 创建一个新的 FileReader ，给定要读取的文件的名称
 */

public class ReaderDemo
{
	public static void main(String[] args) throws IOException
	{
		//创建对象
		Reader rea = new InputStreamReader(new FileInputStream("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\字符流\\ReaderDemo.java"));
		
		//读取数据
		//public int read(): 读取一个字符		
		int ch = rea.read();		
		System.out.print((char)ch);
		
		//public int read(char[] cbuf): 读取一个字符数组
		char[] chs = new char[1024];
		int len = 0;
		while((len = rea.read(chs)) != -1)
			System.out.print(new String(chs,0,len));
			
		//释放资源
		rea.close();
		System.out.println("------------------------------------");
		
		//创建对象
		//public InputStreamReader(InputStream in): 根据默认字符集把字节流转换成字符流
		InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 10\\Java_IO流\\src\\字符流\\ReaderDemo.java"));		
				
		//读取数据
		//一次一个字节
		int ch2 = isr.read();
		System.out.print((char)ch2);
				
		//一次一个字符数组
		char[] chs2 = new char[1024];
		int len2 = 0;
		while((len2 = isr.read(chs2)) != -1)
			System.out.print(new String(chs2,0,len2));
				
		//释放资源
		isr.close();
	}
}
