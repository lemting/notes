package Java_IO流.文件和递归;

import java.io.File;

/*
 * 把  D:\Programming\Java\MyEclipse\Workspaces\MyEclipse 10\Java_IO流  目录下的所有的java结尾的文件的绝对路径输出在控制台
 * 
 * 分析: 
 * 		A: 封装目录
 * 		B: 获取该目录下的所有文件和文件夹的File数组
 * 		C: 遍历File数组,得到每一个File对象
 * 		D: 判断该File对象是否是文件夹
 * 			是: 重复B-D的操作
 * 
 * 			否: 继续判断是否是以.java结尾
 * 					是: 输出该文件的绝对路径
 * 					否: 继续判断下一个File对象
 */

public class Test_递归遍历目录下指定后缀名的文件名称 {
	public static void main(String[] args) {
		// 封装目录
		File file = new File("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流");

		getAllJavaFilePaths(file);
	}

	public static void getAllJavaFilePaths(File file) {
		// 获取该目录下的所有文件和文件夹的File数组
		File[] files = file.listFiles();

		// 遍历File数组,得到每一个File对象
		for (File f : files) {
			// 判断该File对象是否是文件夹
			if (f.isDirectory()) {
				getAllJavaFilePaths(f);
			} else if (f.getName().endsWith(".java")) { // 继续判断是否是以.java结尾
				// 输出该文件的绝对路径
				System.out.println(f);
			}
		}
	}
}