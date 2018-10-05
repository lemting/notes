package Java_网络编程.网络编程三要素;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*	
 * 	TCP传输
 * 		Socket和ServerSocket
 * 
 *  TCP发送数据
 *  	A: 创建发送端的Socket对象
 *  	B: 获取输出流,写数据
 *  	C: 释放资源
 *  
 *  TCP接收数据
 *  	A: 创建接收端的Socket对象
 *  	B: 监听客户端连接,返回一个对应的Socket对象
 *  	C: 获取输入流,读取数据显示在控制台
 *  	D: 释放资源
 *  
 *  Socket套接字
 *  	网络上具有唯一标识的IP地址和端口号组合在一起才能构成唯一能识别的标识符套接字 
 *  Socket原理机制
 *  	通信的两端都有Socket
 *  	网络通信其实就是Socket间的通信
 *  	数据在两个Socket间通过IO传输
 *  
 *  Socket类: 该类实现客户端套接字
 *  Socket类的方法:
 *  	public Socket(InetAddress address,int port): 创建流套接字并将其连接到指定IP地址的指定端口号
 *  	public Socket(String host,int port): 创建流套接字并将其连接到指定主机上的指定端口号
 *  	异常: 连接被拒绝 (TCP协议一定要先开服务器)
 *  		java.net.ConnectException: Connection refused: connect
 *  
 *  	public OutputStream getOutputStream(): 返回此套接字的输出流
 *  	public InputStream getInputStream(): 返回此套接字的输入流
 *  	public InetAddress getInetAddress(): 返回套接字所连接的地址
 *  	public void close(): 关闭此套接字
 *  	public void shutdownInput(): 将此套接字的输入流放置在“流的末尾”。
 *  	public void shutdownOutput(): 禁用此套接字的输出流 
 *  
 *  ServerSocket类的方法: 
 *  	public ServerSocket(int port): 创建绑定到指定端口的服务器套接字
 *  	
 *  	public Socket accept(): 侦听要连接到此套接字并接受它
 *  	public InetAddress getInetAddress(): 返回此服务器套接字的本地地址
 */

public class TCP协议
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("放至两个类中实现数据传输");
		System.out.println("请见Java_Test\\src\\TCP_JieShou.java与TCP_FaSong.java");
	}
	
	public static void jieshou() throws IOException
	{
		//创建接收端的Socket对象
		//public ServerSocket(int port): 创建绑定到指定端口的服务器套接字
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8888);
		
		//监听客户端连接,返回一个对应的Socket对象
		//public Socket accept(): 侦听要连接到此套接字并接受它
		Socket socket = ss.accept();
		
		//获取输入流,读取数据显示在控制台
		InputStream is = socket.getInputStream();
		//public InetAddress getInetAddress(): 返回套接字所连接的地址
		System.out.println("from " + socket.getInetAddress().getHostAddress() + " data is: ");
		byte[] bytes = new byte[1024];
		int len = 0;
		while((len = is.read(bytes)) != -1)
		{
			System.out.println(new String(bytes,0,len));
		}
		//释放资源
		socket.close();
		//ss.close(); //这个不应该关闭
	}
	
	public static void fasong() throws IOException
	{
		//创建发送端的Socket对象
		//public Socket(String host,int port): 创建流套接字并将其连接到指定主机上的指定端口号
		Socket socket = new Socket("DESKTOP-AKG2Q86",8888);
				
		//获取输出流,写数据
		//public OutputStream getOutputStream(): 返回此套接字的输出流
		OutputStream os = socket.getOutputStream();
		os.write("Hello TCP".getBytes());
				
		//释放资源
		socket.close();
	}
}
