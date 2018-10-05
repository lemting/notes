package Java_IO流.其他流;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/*
 * 打印流: 
 *		A: 只能操作目的地,不能操作数据源
 * 		B: 可以操作任意类型的数据
 * 		C: 如果启动了自动刷新,能够自动刷新
 * 		D: 可以操作文件的流
 * 
 * PrintStream: 字节打印流
 * 		public PrintStream(OutputStream out,boolean autoFlush): 创建一个新的打印流
 * 				autoFlush为true则每当写入一个字节数组时,输出缓冲区将被刷新,其中println方法之一被调用，或换行字符（ '\n' ）被写入 
 *		public void print(int i): public void print(long l)打印一个长整数
 *		public void println(): 通过写入行分隔符字符串来终止当前行
 *		public PrintStream printf(String format,Object... args): 使用指定的格式字符串和参数将格式化的字符串写入此输出流的便利方法
 *
 *PrintWriter: 字符打印流
 *		public PrintWriter(Writer out,boolean autoFlush): 创建一个新的PrintWriter,autoFlush为true则println，printf，或format方法将刷新输出缓冲区
 * 		public PrintWriter(OutputStream out,boolean autoFlush): autoFlush为true则println，printf，或format方法将刷新输出缓冲区
 * 		public void print(int i): 该方法能将任意类型打印
 * 		public void println(): 通过写入行分隔符字符串来终止当前行
 * 		public PrintWriter printf(String format,Object... args): 使用指定的格式字符串和参数将格式化的字符串写入该writer的方便方法 
 */

public class 打印流
{
	public static void main(String[] args) throws IOException
	{
		//创建PrintStream对象
		PrintStream ps = new PrintStream(new FileOutputStream("E:\\a.txt"),true);
					
		ps.println("hello");
		ps.println(true);
		ps.println(12.34F);
		double a = 10;
		ps.printf("%10.2f",a);
				
		//释放资源
		ps.close();
				
		//删除
		System.out.println("delete: " + new File("E:\\a.txt").delete());
		
		//创建PrintWriter对象
		PrintWriter pw = new PrintWriter(new FileOutputStream("E:\\a.txt")); 
		
		pw.println("hello");
		pw.println(true);
		pw.println(12.34F);
				
		//释放资源
		pw.close();
				
		//删除
		System.out.println("delete: " + new File("E:\\a.txt").delete());
	}
}
