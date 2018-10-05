package Java_IO流.字符流;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
 *  public String(byte[] bytes,String charsetName): 通过指定的字符串解码字节数组
 *  public byte[] getBytes(String charsetName): 使用指定的字符集将此字符串编码为字节数组
 *  
 *  编码: 把看得懂的变成看不懂的
 *  
 *  解码: 把看不懂的变成看得懂的
 */

public class String类中的编码和解码
{
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		String s = "你好";
		
		//public byte[] getBytes(): 使用默认的字符集将此字符串编码为字节数组
		byte[] bytes = s.getBytes();
		System.out.println(Arrays.toString(bytes));//UTF-8:  [-28, -67, -96, -27, -91, -67]
		
		//public byte[] getBytes(String charsetName): 使用指定的字符集将此字符串编码为字节数组
		byte[] bytes2 = s.getBytes("GBK");
		System.out.println(Arrays.toString(bytes2));//GBK:   [-60, -29, -70, -61]
		
		//public String(byte[] bytes): 通过默认的字符串解码字节数组
		String ss = new String(bytes);
		System.out.println(ss);  //UTF-8: 你好
		ss = new String(bytes,"GBK");
		System.out.println(ss); //GBK: 浣犲ソ
		
		
		//public String(byte[] bytes,String charsetName): 通过指定的字符串解码字节数组
		String ss2 = new String(bytes2,"GBK");
		System.out.println(ss2);  //GBK:  你好
		ss2 = new String(bytes2,"UTF-8");
		System.out.println(ss2);  //UTF-8: ���
	}
}
