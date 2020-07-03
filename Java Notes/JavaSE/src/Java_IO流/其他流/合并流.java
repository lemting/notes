package Java_IO流.其他流;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/*
 * SequenceInputStream
 * 		SequenceInputStream类可以将多个输入流串流在一起,合并为一个输入流,因此,该留也被称合并流
 * 		InputStream类的子类
 * 
 * SequenceInputStream的构造方法
 * 		public SequenceInputStream(InputStream s1,InputStream s2): 通过记住两个输入流来初始化新创建的合并流,这两个参数将按顺序读,首先是 s1,后是 s2
 * 		public SequenceInputStream(Enumeration<? extends InputStream> e): 
 */

public class 合并流 {
	public static void main(String[] args) throws IOException {
		InputStream fis1 = new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\其他流\\合并流.java");
		@SuppressWarnings("resource")
		InputStream fis2 = new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\其他流\\合并流.java");

		// public SequenceInputStream(InputStream s1,InputStream s2):
		// 通过记住两个输入流来初始化新创建的合并流,这两个参数将按顺序读,首先是 s1,后是 s2
		SequenceInputStream sis = new SequenceInputStream(fis1, fis2);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\a.txt"));

		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = sis.read(bytes)) != -1) {
			bos.write(bytes, 0, len);
			bos.flush();
		}

		// 释放资源
		sis.close();
		bos.close();

		InputStream fis3 = new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\其他流\\合并流.java");
		InputStream fis4 = new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\其他流\\合并流.java");
		InputStream fis5 = new FileInputStream(
				"D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\其他流\\合并流.java");

		// public SequenceInputStream(Enumeration<? extends InputStream> e):
		Vector<InputStream> v = new Vector<InputStream>(); // 创建Vector<InputStream>集合对象
		v.add(fis3);
		v.add(fis4);
		v.add(fis5); // 添加InputStream输入流
		Enumeration<InputStream> en = v.elements(); // public Enumeration<E> elements(): 返回此向量的组件的枚举(类似于迭代器)
		SequenceInputStream sis2 = new SequenceInputStream(en);

		BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream("E:\\b.txt"));

		byte[] bytes2 = new byte[1024];
		int len2 = 0;
		while ((len2 = sis2.read(bytes2)) != -1) {
			bos2.write(bytes2, 0, len2);
			bos2.flush();
		}

		// 释放资源
		sis2.close();
		bos2.close();

		// 删除
		System.out.println("delete； " + new File("E:\\a.txt").delete());
		System.out.println("delete； " + new File("E:\\b.txt").delete());
	}
}
