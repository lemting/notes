package Java_IO流.字节字符缓冲流;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * BufferedInputStream: 字节缓冲输入流
 * 		该类实现缓冲的输入流,通过设置这种输入流.应用程序就可以将每个字节写入底层输入流中,而不必针对每次字节写入调用底层系统
 * 
 * BufferedInputStream的构造方法:
 * 		public BufferedInputStream(InputStream in): 创建一个字节缓冲输入流并保存其参数，输入流in供以后使用
 * 		public BufferedInputStream(InputStream in, int size): 创建具有字节缓冲区大小的字节缓冲输出流，并保存其参数，输入流in供以后使用
 * 			该构造方法可以指定缓冲区大小,但是我们一般用不上,因为默认缓冲区大小就足够了
 * 		为什么构造方法不传递一个具体的文件或文件夹,而是传递一个InputStream对象呢?
 * 			原因很简单,字节缓冲区流仅仅提供缓冲区,为了高效而设计的.但是呢,真正的读写操作还是靠基本的流对象实践
 * 
 * BufferedOutputStream: 字节缓冲输出流
 * 		该类实现缓冲的输出流,通过设置这种输出流.应用程序就可以将每个字节写入底层输出流中,而不必针对每次字节写入调用底层系统
 * 
 * BufferedInputStream的构造方法:
 * 		public BufferedOutputStream(OutputStream out): 创建一个字节缓冲输出流并保存其参数，输出流out供以后使用
 * 		public BufferedOutputStream(OutputStream out, int size): 创建具有字节缓冲区大小的字节缓冲输出流，并保存其参数，输出流out供以后使用
 * 			该构造方法可以指定缓冲区大小,但是我们一般用不上,因为默认缓冲区大小就足够了
 */

public class 字节缓冲流
{
	public static void main(String[] args) throws IOException
	{
		//public BufferedInputStream(InputStream in): 创建一个字节缓冲输入流并保存其参数，输入流in供以后使用
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\字节字符缓冲流\\字节缓冲流.java"));
			
		//读取数据并输出
		byte[] bytes = new byte[1024];
		int len = 0;
		while((len = bis.read(bytes)) != -1)
			System.out.print(new String(bytes,0,len));
				
		//释放资源
		bis.close();
		System.out.println("-------------------------------------------------");
		
		
		new File("E:\\demo").mkdirs();
		new File("E:\\demo\\a.txt").createNewFile();

		//public BufferedOutputStream(OutputStream in): 创建一个字节缓冲输出流并保存其参数，输入流in供以后使用
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\demo\\a.txt"));
		
		//写数据
		bos.write("Hello World Java".getBytes());
		
		//释放资源
		bos.close();
		
		//删除
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}
