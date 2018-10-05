package Java_多线程.设计模式;

import java.io.IOException;

/*
 *  Runtime类:
 *  	该类属于单例模式的饿汉式
 *  	每个Java应用程序都有一个Runtime类的Runtime,允许应用程序与运行应用程序的环境进行接口.当前运行时可以从getRuntime方法获得
 *  	应用程序无法创建自己的 Runtime 类的实例
 *  Runtime类的使用
 *  	public Process exec(String command): 在单独的进程中执行指定的字符串命令,能调用dos命令
 */

public class RuntimeDemo
{
	public static void main(String[] args) throws IOException
	{
		//获取Runtime对象
		Runtime r = Runtime.getRuntime();
		
		//调用计算器
		r.exec("calc");
		//调用记事本
		r.exec("notepad");
		
		//关闭计算机
		//r.exec("shutdown -s -t 1000000");	
		//取消关闭计算机
		//r.exec("shutdown -a");
	}
}
