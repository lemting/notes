package Java_IO流.字节流;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 加入异常处理的字节输出操作
 */

public class 加入异常处理的字节输出操作
{
	public static void main(String[] args)
	{
		new File("E:\\demo").mkdirs();
		
		////异常处理
		// FileOutputStream fos = null;
		// try
		// {
		// 		fos = new FileOutputStream("E:\\demo\\a.txt",true);
		// } catch (FileNotFoundException e)
		// {
		// 		e.printStackTrace();
		// }
		//
		// try
		// {
		// 		fos.write("Java".getBytes());
		// } catch (IOException e)
		// {
		// 		e.printStackTrace();
		// }
		//
		// try
		// {
		// 		fos.close();
		// } catch (IOException e)
		// {
		//		 e.printStackTrace();
		// }

		//// 异常一起处理
		// try
		// {
		// 		FileOutputStream fos = new FileOutputStream("E:\\demo\\a.txt",true);
		// 		fos.write("Java".getBytes());
		// 		fos.close();
		// }
		// catch (FileNotFoundException e) {
		// 		e.printStackTrace();
		// }
		// catch (IOException e) {
		// 		e.printStackTrace();
		// }

		// 改进
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream("E:\\demo\\c.txt",true);
			fos.write("Java".getBytes());		 
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			//如果fos不为null才需要colse()
			if(fos != null)
			{
				try
				{
					fos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		 }
		
		//删除
		System.out.println("delete: " + new File("E:\\demo\\c.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}