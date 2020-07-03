package Java_IO流.其他流;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/*
 *  内存操作流
 *  		用于处理临时储存信息,程序结束,数据就从内存中消失
 *  		释放资源无意义
 *  
 *  			操作字节数组
 *  				ByteArrayInputStream
 *  					public ByteArrayInputStream(byte[] buf): 创建一个ByteArrayInputStream ，使其使用buf作为其缓冲区数组
 *  				ByteArrayOutputStream
 *  					public byte[] toByteArray(): 创建一个新分配的字节数组
 *  					public String toString(): 使用平台的默认字符集将缓冲区内容转换为字符串解码字节
 *  			操作字符数组
 *  				CharArrayReader
 *  					public CharArrayReader(char[] buf): 从指定的字符数组中创建CharArrayReader
 *  				CharArrayWriter 
 *  					public char[] toCharArray(): 返回输入数据的副本
 *  					public String toString(): 将输入数据转换为字符串
 *  			操作字符串
 *  				StringReader
 *  					public StringReader(String s): 创建一个新的字符串阅读器
 *  				StringWriter
 *  					public StringBuffer getBuffer(): 返回字符串缓冲区本身
 *  					public String toString(): 以缓冲区的当前值作为字符串返回
 */

public class 内存操作流 {
	public static void main(String[] args) throws IOException {
		// 写数据
		// ByteArrayOutputStream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		for (int x = 0; x < 10; x++)
			baos.write(("hello" + x).getBytes());

		// public byte[] toByteArray(): 创建一个新分配的字节数组
		byte[] bys = baos.toByteArray();

		// 读数据
		// public ByteArrayInputStream(byte[] buf): 创建一个ByteArrayInputStream
		// ，使其使用buf作为其缓冲区数组
		ByteArrayInputStream bais = new ByteArrayInputStream(bys);

		int by = 0;
		while ((by = bais.read()) != -1)
			System.out.print((char) by);

	}
}
