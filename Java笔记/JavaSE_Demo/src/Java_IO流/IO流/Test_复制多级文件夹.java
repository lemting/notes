package Java_IO流.IO流;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 复制多级文件夹
 * 
 * 分析: 
 * 		A: 封装数据源File
 * 		B: 封装目的地File
 * 		C: 判断该File是文件还是文件夹
 * 			a: 是文件夹
 * 					1: 就在该目的地目录下创建该文件夹
 * 					2: 获取该File对象下的所有文件或文件夹File对象
 * 					3: 遍历得到每一个File对象
 * 					4: 重复C
 * 			b: 是文件
 * 					就复制
 */

public class Test_复制多级文件夹
{
	public static void main(String[] args) throws IOException
	{
		new File("E:\\demo\\aaa").mkdirs();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\IO流\\Test_复制多级文件夹.java"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\demo\\a.txt"));
		BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream("E:\\demo\\b.txt"));	
		BufferedOutputStream bos3 = new BufferedOutputStream(new FileOutputStream("E:\\demo\\aaa\\c.txt"));
		byte[] bytes = new byte[1024];
		int len = 0;
		while((len = bis.read(bytes)) != -1)
		{
			bos.write(bytes,0,len);bos.flush();
			bos2.write(bytes,0,len);bos2.flush();
			bos3.write(bytes,0,len);bos3.flush();
		}
		bis.close();bos.close();bos2.close();bos3.close();
		
		
		//封装数据源File
		File srcFolder = new File("E:\\demo");
				
		//封装目的地File
		File destFolder = new File("E:\\test");
			
		//调用该方法实现多级文件夹的复制
		copyAll(srcFolder,destFolder);
		
		//删除
		System.out.println("delete: E:\\demo\\aaa\\c.txt"  + "\t" + new File("E:\\demo\\aaa\\c.txt").delete());
		System.out.println("delete: E:\\demo\\a.txt"  + "\t\t" + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: E:\\demo\\b.txt"  + "\t\t" + new File("E:\\demo\\b.txt").delete());
		System.out.println("delete: E:\\demo\\aaa" + "\t\t" + new File("E:\\demo\\aaa").delete());
		System.out.println("delete: E:\\demo" + "\t\t\t" + new File("E:\\demo").delete());
		System.out.println("delete: E:\\demo\\aaa\\c.txt"  + "\t" + new File("E:\\test\\aaa\\c.txt").delete());
		System.out.println("delete: E:\\demo\\a.txt"  + "\t\t" + new File("E:\\test\\a.txt").delete());
		System.out.println("delete: E:\\demo\\b.txt"  + "\t\t" + new File("E:\\test\\b.txt").delete());
		System.out.println("delete: E:\\demo\\aaa" + "\t\t" + new File("E:\\test\\aaa").delete());
		System.out.println("delete: E:\\demo" + "\t\t\t" + new File("E:\\test").delete());
	}

	
	public static void copyAll(File srcFolder,File destFolder) throws IOException
	{
		//判断该File是文件还是文件夹
		if(srcFolder.isDirectory())
		{
			//在该目的地目录下创建该文件夹
			destFolder.mkdir();
			//获取该File对象下的所有文件或文件夹File对象
			File[] files = srcFolder.listFiles();
			//遍历得到每一个File对象
			for(File file: files)
			{
				//重复C
				copyAll(file,new File(destFolder,file.getName()));
			}
		}
		else
		{
			//复制文件
			copyFile(srcFolder,destFolder);
		}
	}
	
	//复制文件的方法
	public static void copyFile(File file, File newFile) throws IOException
	{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
		
		byte[] bytes = new byte[1024];
		int len = 0;
		while((len = bis.read(bytes)) != -1)
		{
			bos.write(bytes,0,len);bos.flush();
		}
		
		bis.close();bos.close();
	}
}
