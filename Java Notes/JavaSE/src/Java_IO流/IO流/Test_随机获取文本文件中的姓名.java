package Java_IO流.IO流;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*
 * 随机获取文本文件中的姓名
 * 
 * 分析:
 *  	A: 把文本文件中的数据储存到集合中
 *  	B: 随机产生一个索引
 *  	C: 根据索引获取一个值
 */

public class Test_随机获取文本文件中的姓名 {
	public static void main(String[] args) throws IOException {
		BufferedWriter f = new BufferedWriter(new FileWriter("E:\\a.txt"));
		f.write("谭浩强");
		f.newLine();
		f.write("C++");
		f.newLine();
		f.write("JavaEE");
		f.newLine();
		f.close();

		// 把文本文件的数据储存到集合中
		BufferedReader br = new BufferedReader(new FileReader("E:\\a.txt"));
		ArrayList<String> al = new ArrayList<String>();
		String str = null;
		while ((str = br.readLine()) != null)
			al.add(str);
		br.close();

		// 随机产生一个索引
		Random r = new Random();
		int num = r.nextInt(al.size());

		// 根据索引获取一个值
		System.out.println(al.get(num));

		// 删除
		System.out.println("delete: " + new File("E:\\a.txt").delete());
	}
}
