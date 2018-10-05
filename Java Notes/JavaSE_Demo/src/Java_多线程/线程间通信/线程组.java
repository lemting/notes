package Java_多线程.线程间通信;

/*
 * 	线程组: 
 *  	Java中使用ThreadGroup来表示线程组,他可以对一批线程进行分类管理,Java允许程序直接对线程组进行控制
 *  
 *  默认情况下,所有的线程都属于主线程组
 *  	Thread类的成员方法: public final ThreadGroup getThreadGroup(): 返回此线程所属的线程组
 *  我们也可以给线程设置分组
 *  	Thread类的方法构造方法: public Thread(ThreadGroup group,Runnable target,String name)
 *  
 *  ThreadGroup类的方法:
 *  	public ThreadGroup(String name): 构造一个新的线程组。 此新组的父项是当前正在运行的线程的线程组
 *  	public ThreadGroup(ThreadGroup parent,String name): 创建一个新的线程组。 这个新组的父对象是指定的线程组
 *  
 *  	public final void setDaemon(boolean daemon): 更改此线程组的守护程序状态
 *  	public final void interrupt(): 中断此线程组中的所有线程
 *  	public final void setMaxPriority(int pri): 设置组的最大优先级
 */

class MyRunnable implements Runnable
{
	@Override
	public void run()
	{
		
	}
}

public class 线程组
{
	public static void main(String[] args)
	{
		method1();
		System.out.println("------------------");
		method2();
	}
	
	public static void method1()
	{
		//创建线程对象
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(mr);
		Thread t2 = new Thread(mr);
				
		//创建线程组对象
		//public final ThreadGroup getThreadGroup(): 返回此线程所属的线程组
		ThreadGroup tg1 = t1.getThreadGroup();
		ThreadGroup tg2 = t2.getThreadGroup();
				
		//输出线程组的名称
		System.out.println(Thread.currentThread().getThreadGroup().getName());
		System.out.println(tg1.getName());
		System.out.println(tg2.getName());
		
	}
	
	public static void method2()
	{			
		//创建线程组对象
		//public final ThreadGroup getThreadGroup(): 返回此线程所属的线程组
		ThreadGroup tg = new ThreadGroup("这是一个新的组");
		
		//创建线程对象
		MyRunnable mr = new MyRunnable();
		Thread t1 = new Thread(tg,mr,"Lemting");
		Thread t2 = new Thread(tg,mr,"AAAAA");
		
		//输出线程组的名称
		System.out.println(Thread.currentThread().getThreadGroup().getName());
		System.out.println(t1.getName());
		System.out.println(t2.getName());
	}
}
