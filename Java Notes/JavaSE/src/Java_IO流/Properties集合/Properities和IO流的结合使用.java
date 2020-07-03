package Java_IO流.Properties集合;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

/*
 *  Properities集合的方法
 * 		public void load(Reader reader): 把文件中的数据(键值对)储存到Properties集合中
 * 		public void store(Writer writer,String comments): 将此Properties集合中的数据储存到文件中,comments为文件描述,comments为null时没有文件描述
 */

public class Properities和IO流的结合使用 {
	public static void main(String[] args) throws IOException {
		new File("E:\\a.txt").createNewFile();

		// 将此Properties集合中的数据储存到文件中
		myStore();
		// 获取文件中的数据储存到Properties集合对象中,并输出该对象
		myLoad();

		// 删除
		System.out.println("delete: " + new File("E:\\a.txt").delete());
	}

	public static void myStore() throws IOException {
		// 创建Properties集合对象
		Properties prop = new Properties();

		// 添加元素
		prop.setProperty("001", "hello");
		prop.setProperty("002", "world");
		prop.setProperty("003", "java");

		// public void store(Writer writer,String comments):
		// 将此Properties集合中的数据储存到文件中,comments为文件描述,comments为null时没有文件描述
		Writer w = new FileWriter("E:\\a.txt");
		prop.store(w, "helloworld");

		// 释放资源
		w.close();
	}

	public static void myLoad() throws IOException {
		// 创建Properties集合对象
		Properties prop = new Properties();

		// public void load(Reader reader): 把文件中的数据(键值对)储存到Properties集合中
		Reader r = new FileReader("E:\\a.txt");
		prop.load(r);

		// 释放资源
		r.close();

		// 输出Properties集合对象
		System.out.println("prop: " + prop);
	}
}
