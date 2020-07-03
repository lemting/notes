package Java_网络编程.Test_TCP协议上传文件;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/*
 * 	按照我们正常的思路加入反馈信息,结果却没有反应,为什么呢?
 *  	读取文件是可以以null作为结束信息的,但是呢,通道内是不可能这样结束信息的
 *  	所以,服务器根本就不知道你结束了,而你还想服务器给你反馈,所以就互相等待了
 *  如何解决
 *  	A: 多写一条数据,告诉服务器,读到这条数据说明就结束了,你也结束吧
 *  	B: Socket提供了解决方案
 */

public class UploadClient {
	public static void main(String[] args) {
		int num = 31;

		long l1 = System.currentTimeMillis();
		// 创建客户端Socket对象
		Socket socket = null;

		try {
			socket = new Socket(InetAddress.getByName("192.168.1.104"), 2333, InetAddress.getByName("DESKTOP-AKG2Q86"),
					2333);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// AKG2Q86
		// 封装文本文件
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入上传文件的绝对路径: ");
		// String string = sc.nextLine();
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(
					"D:\\迅雷下载\\Batman\\Batman.Arkham.Knight.Incl.Dlcs.Build.20161211.CHS.3.3-ALI213.part" + num
							+ ".rar"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		System.out.println(
				"D:\\迅雷下载\\Batman\\Batman.Arkham.Knight.Incl.Dlcs.Build.20161211.CHS.3.3-ALI213.part" + num + ".rar");

		// 封装通道内的流
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(socket.getOutputStream());
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 发送数据
		byte[] bytes = new byte[1024 * 1024];
		int len = 0;
		try {
			// bos.write(string.getBytes());
			while ((len = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, len);
				bos.flush();
			}
			socket.shutdownOutput();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 接收反馈
		BufferedReader i = null;
		try {
			new BufferedReader(i = new BufferedReader(new InputStreamReader(socket.getInputStream())));
			System.out.println(i.readLine());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		long l2 = System.currentTimeMillis();
		System.out.println("共耗时时: " + (l2 - l1) / 1000 + " s");

		// 释放资源
		sc.close();
		try {
			bis.close();
			socket.close();
			i.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
