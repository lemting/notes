package Java_IO流.IO流;

import java.io.IOException;
import java.io.Reader;

/*
 *  用Reader模拟和BufferedReader的readLine()功能
 *  
 *  readLine(): 一次读取一行,根据换行符判断是否结束,只返回内容,不返回换行符
 */

public class Test_自建类模拟BufferedReader的readLine
{
	private Reader r;
	
	public Test_自建类模拟BufferedReader的readLine(Reader r){this.r = r;}
	
	//一次读取一行
	public String readLine() throws IOException
	{
		/*  应当一次读取一个字符
		 *	但是读取一个字符后,上一个字符就丢失了
		 *  所以选择字符串缓冲区储存字符串
		 *  读取字符是'\r'且下一个是'\n'就结束
		 */
		StringBuilder sb = new StringBuilder();
		
		int ch = 0;
		while((ch = r.read()) != -1)
		{
			//若字符为回车符'\r',则继续判断下一个字符是否是换行符'\n'
			if(ch == '\r')
				continue;
			//若是换行符'\n',则返回字符串
			if(ch == '\n')
				return sb.toString();
			else 
				sb.append((char)ch);
		}
		//为了防止数据丢失,判断sb的长度不能大于0
		if(sb.length() > 0)
			return sb.toString();
		
		//如果r里没有字符,则返回null
		return null;
	}
	
	
	//关闭方法
	public void close() throws IOException
	{
		this.r.close();
	}
}
