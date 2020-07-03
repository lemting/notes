package Java_IO流.IO流;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 复制单级文件夹
 * 
 * 分析: 
 * 		A: 封装目录
 * 		B: 获取该目录写的所有文本的File数组
 * 		C: 遍历File数组,得到每一个File对象
 * 		D: 把File进行复制
 */

public class Test_复制单级文件夹 {
	public static void main(String[] args) throws IOException {
		new File("E:\\demo").mkdirs();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\IO流\\Test_复制单级文件夹.java"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\demo\\a.txt"));
		BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream("E:\\demo\\b.txt"));
		BufferedOutputStream bos3 = new BufferedOutputStream(new FileOutputStream("E:\\demo\\c.txt"));
		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = bis.read(bytes)) != -1) {
			bos.write(bytes, 0, len);
			bos.flush();
			bos2.write(bytes, 0, len);
			bos2.flush();
			bos3.write(bytes, 0, len);
			bos3.flush();
		}
		bis.close();
		bos.close();
		bos2.close();
		bos3.close();

		// 封装目录
		File srcFolder = new File("E:\\demo");

		// 封装目的地
		File destFolder = new File("E:\\test");
		// 如果目的地文件夹不存在,就创建
		if (!destFolder.exists())
			destFolder.mkdir();

		// 获取该目录写的所有文本的File数组
		File[] files = srcFolder.listFiles();

		// 遍历File数组,得到每一个File对象
		for (File file : files) {
			// 把File进行复制
			File newFile = new File(destFolder, file.getName());
			// 写一个方法,用于复制文件
			copyFile(file, newFile);
		}

		// 删除
		File[] files2 = destFolder.listFiles();
		for (File file : files)
			System.out.println("delete: E:\\demo\\" + file.getName() + "\t" + file.delete());
		System.out.println("delete: E:\\demo\t\t" + new File("E:\\demo").delete());
		for (File file : files2)
			System.out.println("delete: E:\\test\\" + file.getName() + "\t" + file.delete());
		System.out.println("delete: E:\\test\t\t" + new File("E:\\test").delete());
	}

	// 复制文件的方法
	public static void copyFile(File file, File newFile) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));

		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = bis.read(bytes)) != -1) {
			bos.write(bytes, 0, len);
			bos.flush();
		}

		bis.close();
		bos.close();
	}
}
