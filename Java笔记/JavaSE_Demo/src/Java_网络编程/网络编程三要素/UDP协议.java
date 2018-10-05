package Java_网络编程.网络编程三要素;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 *	UDP协议发送数据
 *		A: 创建发送端Socket对象
 *		B: 创建数据,并把数据打包
 *		C: 调用Socket对象的发送方法发送数据包
 *		D: 释放资源
 *	
 *	UDP协议接受数据
 *		A: 创建接收端Socket对象
 *		B: 创建一个数据包(接收容器)
 *		C: 调用Socket对象的接收方法
 *		D: 解析数据包,并显示在控制台
 *		E: 释放资源
 *
 *	DatagramSocket: 此类表示用于发送和接收数据报数据包的套接字
 *	
 *	DatagramSocket类的方法:
 *
 *		public DatagramSocket(): 构造数据报套接字并将其绑定到本地主机上的任何可用端口
 *		public DatagramSocket(int port): 构造数据报套接字并将其绑定到本地主机上的指定端口
 *
 *		public void send(DatagramPacket p): 从此套接字发送数据报包.DatagramPacket包括指示要发送的数据，其长度，远程主机的IP地址和远程主机上的端口号的信息
 *		public void receive(DatagramPacket p): 从此套接字接收数据报包	
 *		public void close(): 关闭此数据报套接字
 *
 *
 *	DatagramPacket: 该类表示数据报包
 *
 *		public DatagramPacket(byte[] buf,int length): 构造一个DatagramPacket用于接收长度的数据包length 
 *		public DatagramPacket(byte[] buf,int length,InetAddress address,int port): 构造用于发送长度的分组的数据报包length指定主机上到指定的端口号
 *		
 *		public byte[] getData(): 返回数据缓冲区
 *		public int getLength(): 返回要发送的数据的长度或接收到的数据的长度
 *		public InetAddress getAddress(): 返回该数据报发送或接收数据报的计算机的IP地址
 */

public class UDP协议
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("放至两个类中实现数据传输");
		System.out.println("请见Java_Test\\src\\JieShou.java与FaSong.java");
	}

	// 接收数据
	public static void jieshou() throws IOException
	{
		// 创建接收端Socket对象
		// public DatagramSocket(int port): 构造数据报套接字并将其绑定到本地主机上的指定端口
		DatagramSocket ds2 = new DatagramSocket(10086);

		// 创建一个数据包(接收容器)
		// public DatagramPacket(byte[] buf,int length):
		// 构造一个DatagramPacket用于接收长度的数据包length
		byte[] bytes2 = new byte[1024];
		DatagramPacket dp2 = new DatagramPacket(bytes2, bytes2.length);

		// 调用Socket对象的接收方法
		// public void receive(DatagramPacket p): 从此套接字接收数据报包
		ds2.receive(dp2);

		// 解析数据包,并显示在控制台
		// public byte[] getData(): 返回数据缓冲区
		// public int getLength(): 返回要发送的数据的长度或接收到的数据的长度
		// 获取对方IP地址
		// public InetAddress getAddress()返回该数据报发送或接收数据报的计算机的IP地址
		String str = new String(dp2.getData(), 0, dp2.getLength());
		String s = dp2.getAddress().getHostAddress();
		System.out.println(s + "---" + str);

		// 释放资源
		ds2.close();
	}

	// 发送数据
	public static void fasong() throws IOException
	{
		// 创建发送端Socket对象
		// public DatagramSocket(): 构造数据报套接字并将其绑定到本地主机上的任何可用端口
		DatagramSocket ds = new DatagramSocket();

		// 创建数据,并把数据打包
		// public DatagramPacket(byte[] buf,int length,InetAddress address,int
		// port): 构造用于发送长度的分组的数据报包length指定主机上到指定的端口号
		byte[] bytes = "Hello,UDP".getBytes();
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("DESKTOP-AKG2Q86"), 10086);

		// 调用Socket对象的发送方法发送数据包
		// public void send(DatagramPacket p):
		// 从此套接字发送数据报包.DatagramPacket包括指示要发送的数据，其长度，远程主机的IP地址和远程主机上的端口号的信息
		ds.send(dp);

		// 释放资源
		ds.close();
	}
}
