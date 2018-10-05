package JDK7新特性;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 	try_with_resources语句
 * 
 *  	格式: try(必须是java.lang.AutoCloseable的子类对象){...}
 *  	好处: 
 * 			资源自动释放,不需要close()了
 * 			把需要关闭资源的部分定义在这里就ok了
 * 			主要是流体系的对象是这个接口的子类
 */

public class try_with_resources语句
{
	public static void main(String[] args)
	{
		method();
	}
	
	public static void method()
	{	
		File f = new File("src\\JDK7新特性\\b.txt");
		//自动释放资源
		try(FileReader fr = new FileReader("src\\JDK7新特性\\try_with_resources语句.java");
				FileWriter fw = new FileWriter("src\\JDK7新特性\\b.txt");)
		{
			int ch = 0;
			while((ch = fr.read()) != -1)
				fw.write(ch);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("delete: "+ f.delete());
	}
}
