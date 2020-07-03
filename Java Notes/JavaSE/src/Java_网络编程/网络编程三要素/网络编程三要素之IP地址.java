package Java_网络编程.网络编程三要素;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 	IP地址
 * 		网络中计算机的唯一标识
 * 		要想让计算机能够互相通信,必须为每台计算机指定一个标识号,通过这个标识号来指定要接受数据的计算机和识别发送的计算机,在TCP/IP协议中,这个标识号就是IP地址
 * 		为了方便我们队IP地址的获取 和操作,Java提供了有个类 InetAddress 供我们使用	
 * 			
 * 	IP地址的组成: 网络号段  + 主机号段 
 * 		A类: 第一号段为网络号段 + 后三段的主机号段
 * 		B类: 前二号段为网络号段 + 后二段的主机号段
 * 		C类: 前三号段为网络号段 + 后一段的主机号段
 * 		
 * 	IP地址的分类: 
 * 		A类: 1.0.0.1 ---  127.255.255.254
 * 			a: 10.X.X.X 是私有地址(私有地址就是在互联网上不使用,而被用在局域网络中的地址)
 * 			b: 127.X.X.X 是保留地址,用做循环测试用的.
 * 		B类: 128.0.0.1 --- 191.255.255.254
 * 			a: 172.16.0.0 --- 172.31.255.255 是私有地址
 * 			b: 169.254.X.X 是保留地址
 *  	C类: 192.0.0.1 --- 223.255.255.254
 *  		192.168.X.X 是保留地址
 *  	D类: 224.0.0.1 --- 239.255.255.254  是保留地址
 *  	E类: 240.0.0.1 --- 247.255.255.254  是保留地址
 *  
 *  两个DOS命令
 *  	ipconfig 	查看本机IP地址
 *  	ping+IP地址  		测试本机与指定的IP地址间的通信是否有问题
 *  特殊的IP地址
 *  	127.0.0.1	回环地址(表示本机)
 *  	X.X.X.255	广播地址
 *  	X.X.X.0		网络地址
 * 
 * 	InetAddress: 此类表示Internet协议（IP）地址
 * 		IP地址是由IP使用的32位或128位无符号数字，构建UDP和TCP协议的低级协议		
 * 
 * 	InetAddress类的方法: 
 * 		public static InetAddress getByName(String host): 确定主机名称的IP地址
 * 			主机名称可以是机器名称，例如“ java.sun.com ”或其IP地址的文本表示
 * 		public static InetAddress getByAddress(byte[] addr): 给出原始IP地址的InetAddress对象
 * 		public static InetAddress getByAddress(String host, byte[] addr): 根据提供的主机名和IP地址创建InetAddress
 * 
 * 		public String getHostName(): 获取此IP地址的主机名
 * 		public String getHostAddress(): 返回文本显示中的IP地址字符串
 * 		public byte[] getAddress(): 返回此InetAddress对象的原始IP地址
 * 		public static InetAddress[] getAllByName(String host): 给定主机的名称，根据系统上配置的名称服务返回其IP地址数组
 * 
 */

class 网络编程三要素之IP地址
{
	public static void main(String[] args) throws UnknownHostException
	{
		//public static InetAddress getByName(String host): 确定主机名称的IP地址
		InetAddress address = InetAddress.getByName("DESKTOP-AKG2Q86");
		//public static InetAddress getByAddress(byte[] addr): 给出原始IP地址的InetAddress对象
		InetAddress address2 = InetAddress.getByName("192.168.43.47");
		
		//public String getHostName(): 获取此IP地址的主机名
		String name = address.getHostName();
		String name2 = address2.getHostName();
		//public String getHostAddress(): 返回文本显示中的IP地址字符串
		String ip = address.getHostAddress();
		String ip2 = address2.getHostAddress();
		
		System.out.println(name + "---" + ip);
		System.out.println(name2 + "---" + ip2);
		
	}
}
