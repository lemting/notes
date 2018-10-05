package Java_网络编程.Test_TCP协议上传文件;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 *  通过while循环可以改进一个服务器接收多个客户端
 *  但是这个有问题
 *  只能依次执行,依次执行一个
 *  故用多线程改进
 */

class UserThread implements Runnable
{
	private Socket socket;
	public UserThread(Socket socket){this.socket = socket;}
	
	@Override
	public void run()
	{	
		//封装通道内的流
		BufferedInputStream bis = null;
		try{bis = new BufferedInputStream(socket.getInputStream());}
		catch (IOException e)
		{e.printStackTrace();}
		
		//为了防止名称冲突
		String newname = System.currentTimeMillis() + ".txt";
		
		//封装文件
		BufferedOutputStream bos = null;
		try{bos = new BufferedOutputStream(new FileOutputStream("src\\" + newname));}
		catch (FileNotFoundException e)
		{e.printStackTrace();}
		
		//写入数据
		byte[] bytes = new byte[1024];
		int len = 0;
		try
		{
			while((len = bis.read(bytes)) != -1)
			{
				bos.write(bytes,0,len);
				bos.flush();
			}
		}
		catch (IOException e1)
		{e1.printStackTrace();}
		
		//给出反馈
		BufferedWriter o = null;
		try{new BufferedWriter(o = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));o.write("上传成功!");o.newLine();o.flush();}
		catch (IOException e1)
		{e1.printStackTrace();};
		
		//释放资源
		try{bis.close();bos.close();socket.close();o.close();}
		catch (IOException e)
		{e.printStackTrace();}
	}
}

public class UploadServer
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		//创建服务器Socket对象
		ServerSocket ss = null;
		try{ss = new ServerSocket(2333);}
		catch (IOException e)
		{e.printStackTrace();}
		
		while(true)
		{
			//监听客户端
			Socket socket = null;
			try
			{socket = ss.accept();}
			catch (IOException e)
			{e.printStackTrace();}
			
			//启动线程
			new Thread(new UserThread(socket)).start();
		}
		
	}
}
