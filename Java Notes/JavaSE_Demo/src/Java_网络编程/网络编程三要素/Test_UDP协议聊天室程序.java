package Java_网络编程.网络编程三要素;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/*
 * 通过多线程改进聊天程序,这样就可以实现在一个串口发送和接收数据
 */

class SendThread implements Runnable
{
	private DatagramSocket dsSend;
	public SendThread(){}
	public SendThread(DatagramSocket dsSend){this.dsSend = dsSend;}

	@Override
	public void run()
	{
		Scanner sc = new Scanner(System.in);
		try
		{
			while(true)
			{
				String str = new String(sc.nextLine().getBytes(),"UTF-8");
				if(str.equals("end!"))
					break;
				DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), InetAddress.getByName("DESKTOP-AKG2Q86"), 12306);
				
				dsSend.send(dp);
			}
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		sc.close();
		dsSend.close();
	}
}

class ReceiveThread implements Runnable
{
	private DatagramSocket dsReceive;
	public ReceiveThread(){}
	public ReceiveThread(DatagramSocket dsReceive){this.dsReceive = dsReceive;}

	@Override
	public void run()
	{
		while(true)
		{
			byte[] bytes = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
			
			try
			{dsReceive.receive(dp);}
			catch (IOException e)
			{e.printStackTrace();}
			
			String str = null;
			try
			{
				str = new String(new String(dp.getData(),0,dp.getLength()).getBytes(),"UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}	
			if(str.equals("end!"))
				break;		
			String ip = dp.getAddress().getHostAddress();
			System.out.println("from " + ip + " data is: " + str);
			System.out.println("你回应好");
			
		}
		
		dsReceive.close();
	}
}

public class Test_UDP协议聊天室程序
{
	public static void main(String[] args)
	{
		DatagramSocket dsSend = null;
		try
		{dsSend = new DatagramSocket();}
		catch (SocketException e)
		{e.printStackTrace();}
		
		DatagramSocket dsReceive = null;
		try
		{dsReceive = new DatagramSocket(12306);}
		catch (SocketException e)
		{e.printStackTrace();}
		
		SendThread st = new SendThread(dsSend);
		ReceiveThread rt = new ReceiveThread(dsReceive);
		
		new Thread(st).start();
		new Thread(rt).start();
	}
}
