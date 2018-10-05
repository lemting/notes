package Java_IO流.文件和递归;

import java.io.File;
import java.io.IOException;

/*
 * 递归删除带内容的目录
 * 
 * 		A: 封装目录
 * 		B: 获取该目录下的所有文件和文件夹的File数组
 * 		C: 遍历File数组,得到每一个File对象
 * 		D: 判断该File对象是否是文件夹
 * 			是: 重复A-E的操作
 * 			否: 删除文件
 * 		E: 删除该目录
 */

public class Test_递归删除带内容的目录
{
	public static void main(String[] args) throws IOException
	{
		new File("E:\\demo\\aaa\\bbb").mkdirs();
		new File("E:\\demo\\ccc").mkdirs();
		new File("E:\\demo\\aaa\\bbb\\a.txt").createNewFile();
		new File("E:\\demo\\aaa\\bbb\\b.txt").createNewFile();
		new File("E:\\demo\\ccc\\b.txt").createNewFile();
		new File("E:\\demo\\d.txt").createNewFile();
		
		//封装目录
		File file = new File("E:\\demo");
		deleAllFile(file);
	}
	
	
	public static void deleAllFile(File file)
	{
		//获取该目录下的所有文件和文件夹的File数组
		File[] files = file.listFiles();
		
		//遍历File数组,得到每一个File对象
		for(File f: files)
		{
			//判断该File对象是否是文件夹
			if(f.isDirectory())
			{
				//重复A-E的操作
				deleAllFile(f);
			}
			else 
			{
				//删除文件
				System.out.println("delete : " + f.getName() + "\t" + f.delete());
			}
		}
		//删除该目录
		System.out.println("delete : " + file.getName() + "\t" + file.delete());
	}
}