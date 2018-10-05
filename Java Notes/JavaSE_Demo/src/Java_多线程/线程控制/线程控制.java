package Java_多线程.线程控制;

/* 
 *  线程的生命周期:
 *  	创建: 创建线程对象
 *   	就绪: 有执行资格,没有执行权
 *   	运行: 有执行资格,有执行权
 *   		阻塞: 没有执行资格,没有执行权.由于一些操作让线程处于了该状态,而另一些操作缺可以把它给激活,激活后处于就绪状态
 *   	死亡: 线程对象编程垃圾,等待被回收
 * 
 *  线程控制:
 * 		线程休眠
 * 			public static void sleep(long millis): 使当前正在执行的线程以指定的毫秒数暂停
 * 		线程加入
 * 			public final void join(): 等待这个线程终止
 * 		线程礼让
 * 			public static void yield(): 暂停当前正在执行的线程对象,并执行其他线程
 * 				让多线程执行更和谐,但是不能靠他保证一人一次
 * 		后台线程
 * 			public final void setDaemon(boolean on): 将此线程标记为守护线程或用户线程
 * 				如果正在运行的线程都是守护线程时,Java虚拟机退出,该方法必须在启动线程前调用
 * 		中断线程	
 * 			public void interrupt(): 中断这个线程
 */

class Xianchengkongzhi extends Thread
{
	public Xianchengkongzhi() {}
	public Xianchengkongzhi(String string) 
	{
		super(string);
	}
	@Override
	public void run() 
	{
		for(int i = 0;i < 10;i++)
		{
			System.out.println(getName() + ": " + i);
			//public static void sleep(long millis): 使当前正在执行的线程以指定的毫秒数暂停
			if(getName().equals("A"))
			{
				try
				{Thread.sleep(1000);}
				catch (InterruptedException e)
				{e.printStackTrace();}
			}
			
			//public static void yield(): 暂停当前正在执行的线程对象,并执行其他线程
			Thread.yield();
		}

		System.out.println("线程终止: " + getName());
	}
}

public class 线程控制
{
	public static void main(String[] args) throws InterruptedException
	{
		Xianchengkongzhi xckz1 = new Xianchengkongzhi("A");
		Xianchengkongzhi xckz2 = new Xianchengkongzhi("B");
		Xianchengkongzhi xckz3 = new Xianchengkongzhi("C");
		
		xckz1.setPriority(10);

		//public final void setDaemon(boolean on): 将此线程标记为守护线程或用户线程
		xckz2.setDaemon(true);
		xckz3.setDaemon(true);
		
		xckz1.start();
		//public final void join(): 等待这个线程终止
		xckz1.join();	
		//public void interrupt(): 中断这个线程
		xckz2.interrupt();
		
		xckz2.start();
		xckz3.start();

		Thread.currentThread().setName("Z");
		for(int i = 0;i < 5;i++)
			System.out.println(Thread.currentThread().getName() + ": " + i);

	}
}
