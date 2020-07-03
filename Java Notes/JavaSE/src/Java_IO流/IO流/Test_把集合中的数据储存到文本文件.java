package Java_IO流.IO流;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * 把集合中的字符串数据储存到文本文件:
 * 		
 * 		ArrayList集合里储存的是字符串
 * 		遍历ArrayList结集合,把数据获取 到
 * 		然后储存到文本文件中
 * 		使用字符流
 * 
 * 数据源: 
 * 		ArrayList<String>  ---   遍历    -----  得到每一个字符串数据
 * 		
 * 目的地:
 * 		a.txt   ----   FileWriter  ----  BufferedWriter
 */

public class Test_把集合中的数据储存到文本文件 {
	public static void main(String[] args) throws IOException {
		// 封装数据源
		// 创建集合并添加元素
		ArrayList<String> al = new ArrayList<String>();
		al.add("hello");
		al.add("world");
		al.add("java");

		// 封装目的地
		BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\a.txt"));

		// 遍历集合
		for (String s : al) {
			bw.write(s);
			bw.newLine();
			bw.flush();
		}

		// 释放资源
		bw.close();

		// 删除
		System.out.println("delete: " + new File("E:\\a.txt").delete());

	}
}
