package Java_IO流.字符流;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/*
 * Writer抽象类的方法:
 *	 	public void write(int c): 写入一个字符
 *		public void write(char[] cbuf): 写入一个字符数组
 *		public abstract void write(char[] cbuf,int off, int len): 写入字符数组的一部分	
 *		public void write(String str): 写入一个字符串
 *		public void write(String str,int off,int len): 写一个字符串的一部分
 * 
 * 		public abstract void flush(): 刷新此流 (流对象还能继续使用)
 * 		public void close(): 先刷新此流,再关闭此流 (流对象不能继续使用)
 *  
 *   Writer抽象类的子类: OutputstreamWriter//转换流: 将字节流转换成字符流
 *   
 * 		OutputStreamWriter的构造方法:
 *  		public OutputStreamWriter(OutputStream out): 根据默认字符集把字节流转换成字符流
 * 			public OutputStreamWriter(OutputStream out,String charsetName): 根据指定字符集把字节流转换成字符流
 * 
 * 		OutputstreamWriter类的子类: FileWriter
 * 
 * 			FileWriter的构造方法: 
 *  			public FileWriter(File file): 给一个File对象构造一个FileWriter对象
 *  			public FileWriter(File file,boolean append): append = true,追加写入; append = false,重头写入
 *  		  	public FileWriter(String fileName): 构造一个给定文件名的FileWriter对象
 *   			public FileWriter(String fileName,boolean append): append = true,追加写入; append = false,重头写入
 * 
 * 	字符流 = 字节流 + 编码表
 */

public class WriterDemo {
	public static void main(String[] args) throws IOException {
		new File("E:\\demo").mkdirs();
		new File("E:\\demo\\a.txt").createNewFile();

		// 创建对象
		Writer wri = new OutputStreamWriter(new FileOutputStream("E:\\demo\\a.txt"));

		// public void write(int c): 写入一个字符
		wri.write('A');

		// public void write(char[] cbuf): 写入一个字符数组
		char[] chars = { 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
		wri.write(chars);

		// public abstract void write(char[] cbuf,int off, int len): 写入字符数组的一部分
		wri.write(chars, 0, 4);

		// public void write(String str): 写入一个字符串
		wri.write("abcdefgh");

		// public void write(String str,int off,int len): 写一个字符串的一部分
		wri.write("abcdefgh", 0, 4);

		// public abstract void flush(): 刷新流
		wri.flush();

		// 释放资源
		wri.close();

		// 创建对象
		// public OutputStreamWriter(OutputStream out): 根据默认字符集把字节流转换成字符流
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("E:\\demo\\a.txt"));
		// public OutputStreamWriter(OutputStream out,String charsetName):
		// 根据指定字符集把字节流转换成字符流
		OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream("E:\\demo\\a.txt"), "GBK");

		// 写数据
		osw.write("中国");
		osw2.write("中国");

		// 释放资源
		osw.close();
		osw2.close();

		// 删除
		System.out.println("delete； " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete； " + new File("E:\\demo").delete());
	}
}
