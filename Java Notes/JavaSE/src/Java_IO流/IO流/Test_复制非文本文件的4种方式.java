package Java_IO流.IO流;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *  A: 字节流一次读写一个字符
 *  B: 字节流一次读写一个字符数组
 *  C: 字节缓冲流一次读写一个字符
 *  D: 字节缓冲流一次读写一个字符数组
 */

public class Test_复制非文本文件的4种方式 {
	public static void main(String[] args) throws IOException {
		new File("E:\\demo").mkdirs();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\IO流\\Test_复制非文本文件的4种方式.java"));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\demo\\a.jpg"));

		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = bis.read(bytes)) != -1) {
			bos.write(bytes, 0, len);
			bos.flush();
		}

		bis.close();
		bos.close();

		String srcString = "E:\\demo\\a.jpg";
		String destString1 = "E:\\demo\\AA.jpg";
		String destString2 = "E:\\demo\\B.jpg";
		String destString3 = "E:\\demo\\C.jpg";
		String destString4 = "E:\\demo\\D.jpg";

		// A: 字节流一次读写一个字符
		method1(srcString, destString1);
		// B: 字节流一次读写一个字符数组
		method2(srcString, destString2);
		// C: 字节缓冲流一次读写一个字符
		method3(srcString, destString3);
		// D: 字节缓冲流一次读写一个字符数组
		method4(srcString, destString4);

		// 删除
		System.out.println("delete: " + srcString + "\t" + new File(srcString).delete());
		System.out.println("delete: " + destString1 + "\t" + new File(destString1).delete());
		System.out.println("delete: " + destString2 + "\t" + new File(destString2).delete());
		System.out.println("delete: " + destString3 + "\t" + new File(destString3).delete());
		System.out.println("delete: " + destString4 + "\t" + new File(destString4).delete());
		System.out.println("delete: " + "E:\\demo" + "\t\t" + new File("E:\\demo").delete());

	}

	public static void method1(String srcString, String destString) throws IOException {
		FileInputStream fis = new FileInputStream(srcString);
		FileOutputStream fos = new FileOutputStream(destString);

		int ch = 0;
		while ((ch = fis.read()) != -1)
			fos.write(ch);

		fis.close();
		fos.close();
	}

	public static void method2(String srcString, String destString) throws IOException {
		FileInputStream fis = new FileInputStream(srcString);
		FileOutputStream fos = new FileOutputStream(destString);

		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = fis.read(bytes)) != -1)
			fos.write(bytes, 0, len);

		fis.close();
		fos.close();
	}

	public static void method3(String srcString, String destString) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

		int ch = 0;
		while ((ch = bis.read()) != -1)
			bos.write(ch);

		bis.close();
		bos.close();
	}

	public static void method4(String srcString, String destString) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = bis.read(bytes)) != -1)
			bos.write(bytes, 0, len);

		bis.close();
		bos.close();
	}

}
