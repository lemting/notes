package Java_IO流.文件和递归;

import java.io.File;
import java.io.FilenameFilter;

/*
 * FilenameFilter接口: 文件名称过滤器
 * 	
 * 		public String[] list(FilenameFilter filter): 获取指定目录下的文件过滤器过滤后的所有文件或文件夹的名称数组
 * 		public File[] listFiles(FilenameFilter filter): 获取指定目录下的文件过滤器过滤后的所有文件或文件夹的File数组
 * 		
 * 		用文件名称过滤器改进     输出指定目录下指定后缀名的文件名称
 * 				判断 D:\game 目录下是否有后缀名为 .txt 的文件,如果有,就输出此文件的名称
 */

public class 文件名称过滤器
{
	public static void main(String[] args)
	{
		File file = new File("D:\\game");
				
		//获取该目录下所有后为缀 .txt 的文件File数组
		File[] files = file.listFiles(new FilenameFilter()
		{			
			@Override
			public boolean accept(File dir, String name)
			{
				//File dir为目录路径的File对象, String name为该路径下的文件或文件夹的名字
				//是否为文件,且是否以 .txt 结尾
				return new File(dir,name).isFile() && name.endsWith(".txt");
			}
		});
				
		//遍历该File数组
		for(File f: files)
			System.out.println(f.getName());
		
	}
}
