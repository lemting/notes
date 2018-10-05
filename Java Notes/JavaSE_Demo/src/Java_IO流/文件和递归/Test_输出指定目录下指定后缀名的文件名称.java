package Java_IO流.文件和递归;

import java.io.File;

/*
 * 判断 D:\game 目录下是否有后缀名为 .txt 的文件,如果有,就输出此文件的名称
 * 
 * 分析:
 * 		A: 封装 D:\game 目录
 * 		B: 获取该目录下所有文件或文件夹的File数组
 * 		C: 遍历该File数组,得到每一个File对象,然后判断
 * 		D: 是否为文件
 * 			   是: 继续判断是否以 .txt 结尾
 * 					是: 就输出该文件的名称
 * 					否: 继续判断下一个File对象
 * 			   否: 继续判断下一个File对象
 */

public class Test_输出指定目录下指定后缀名的文件名称
{
	public static void main(String[] args)
	{
		//封装 D:\game 目录
		File file = new File("D:\\game");
		
		//获取该目录下所有文件或文件夹的File数组
		File[] files = file.listFiles();
		
		//遍历该File数组,得到每一个File对象,然后判断
		for(File f: files)
		{
			//是否为文件,且是否以 .txt 结尾
			if(f.isFile() && f.getName().endsWith(".txt"))
				System.out.println(f.getName());
		}
	}
}
