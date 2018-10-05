package Java_IO流.IO流;

/*
 * IO流: 是用来处理设备之间的数据传输(上传文件和下载文件)
 * 
 * Java对数据的操作是通过流的方式
 * Java用于操作流的对象都在IO包中
 * 
 * IO流的分类
 * 		按照数据流向分:
 * 			输入流
 * 			输出流
 * 		按照数据类型分
 * 			字节流
 * 
 * 				字节输入流      InputStream   --- 	FileInputStream
 * 								 ┕----   BufferedInputStream	字节缓冲输入流 
 * 
 * 				字节输出流      OutputStream  ---	FileOutputStream
 * 								 ┕---- 	 BufferedOutputStream   字节缓冲输出流   
 * 				
 * 			字符流: 为了方便操作文本数据,Java就提供了字符流 
 * 
 * 				字符输入流  	 Reader	  ---  	InputStreamReader --- 	FileReader	
 * 							 ┕----  BufferedReader   字符缓冲输入流 
 * 
 * 				字符输出流   	 Writer   ---	OutputStreamWriter   ---  	FileWriter
 * 							┕----  BufferedWriter   字符缓冲输出流 
 * 
 * 		什么情况下使用哪种流?
 * 			如果数据所在文件通过windows自带的记事本打开并能读懂里面的内容,就用字符流,其他用字节流
 * 			若你什么都不知道,就用字节流
 * 
 * 字节流的抽象基类
 * 		InputStream,OutputStream
 * 字符流的抽象基类
 * 		Reader,Writer
 */

public class IO流的概述
{
	public static void main(String[] args)
	{
		
	}
}
