package Java_多线程.线程控制;

/*
 * 线程有两种调度模型
 * 		分时调用模型
 * 			所有线程轮流使用CPu的使用权,平均分配每个线程占用CPu的时间片
 * 		抢占式调度模型
 * 			优先让优先级高的线程使用CPU,如果线程优先级相同,那么会随机选择一个,优先级高的线程获取的CPU时间片相对多一些
 * 		Java是使用抢占式调度模型
 * 
 *  设置和获取程序优先级 
 *  	public final int getPriority(): 返回此线程的优先级
 *  		默认优先级为5	
 *  	public final void setPriority(int newPriority): 更改此线程的优先级
 *  		参数newPriority的范围为: 1-10
 */

class ThreadPriority extends Thread
{
	public ThreadPriority() {}
	public ThreadPriority(String string) 
	{
		super(string);
	}
	@Override
	public void run() 
	{
		for(int i = 0;i < 20;i++)
			System.out.println(getName() + ": " + i);
	}
} 

public class 线程调度 
{
	public static void main(String[] args)
	{
		//创建自定义继承Thread类的类独对象
		ThreadPriority tp1 = new ThreadPriority("A");
		ThreadPriority tp2 = new ThreadPriority("B");
		ThreadPriority tp3 = new ThreadPriority("C");
		
		//public final int getPriority(): 返回此线程的优先级
		System.out.println(tp1.getPriority());
		System.out.println(tp2.getPriority());
		System.out.println(tp3.getPriority());
		
		//public final void setPriority(int newPriority): 更改此线程的优先级
		tp1.setPriority(10);
		tp3.setPriority(1);
		
		//启动线程
		tp1.start();
		tp2.start();
		tp3.start();
	}
}
