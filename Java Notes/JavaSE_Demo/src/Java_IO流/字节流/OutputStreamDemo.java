package Java_IO流.字节流;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * OutputStream是字节流的顶层抽象类,字节输出
 * 
 * OutputStream抽象类的子类: 
 * 		FileOutputStream,XxxOutputStream
 * 
 * 		FileOutputStream: 用于写入诸如图像数据的原始字节流
 * 
 * 		FileOutputStream的构造方法 : 若文件不存在,则会创建文件;若文件上级路径不存在,则会报错
 * 			public FileOutputStream(File file): 创建文件输出流以写入由指定的File对象表示的文件
 * 			public FileOutputStream(String name): 创建文件输出流以指定的名称写入文件		
 *			public FileOutputStream(File file,boolean append): append = true,追加写入;append = false,重头开始写入
 * 			public FileOutputStream(String name,boolean append): append = true,追加写入;append = false,重头开始写入
 * 
 * OutputStream抽象类的方法
 * 
 * 		public void write(byte[] b): 将b.length字节从指定的字节数组写入此输出流
 * 		public void write(byte[] b,int off,int len): 从指定的字节数组写入len字节，从偏移off开始输出到此输出流
 *		public abstract void write(int b): 将指定的字节写入此输出流
 *		public void close(): 关闭此输出流并释放与此流相关联的任何系统资源
 */

public class OutputStreamDemo
{
	public static void main(String[] args) throws IOException
	{	
		new File("E:\\demo").mkdir();
		//创建字节输出流对象
		//public FileOutputStream(File file): 创建文件输出流以写入由指定的File对象表示的文件
		FileOutputStream fos = new FileOutputStream(new File("E:\\demo\\a.txt"));
		
		//public FileOutputStream(String name): 创建文件输出流以指定的名称写入文件			
		FileOutputStream fos2 = new FileOutputStream("E:\\demo\\b.txt");
		
		//public void write(byte[] b): 将b.length字节从指定的字节数组写入此输出流
		fos.write("hello,IO".getBytes());
		fos2.write("HelloJava".getBytes());
		
		//public void write(byte[] b,int off,int len): 从指定的字节数组写入len字节，从偏移off开始输出到此输出流
		fos.write("hello,IO".getBytes(),5,3);//将 ,Io 输出
		
		//public abstract void write(int b): 将指定的字节写入此输出流
		fos2.write("\r\n".getBytes());
		fos2.write(65);
		fos2.write("\tHelloJava".getBytes());
		
		//释放资源
		//public void close(): 关闭此输出流并释放与此流相关联的任何系统资源
		fos.close();
		fos2.close();
		
		//删除
		System.out.println("delete: " + new File("E:\\demo\\b.txt").delete());
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}