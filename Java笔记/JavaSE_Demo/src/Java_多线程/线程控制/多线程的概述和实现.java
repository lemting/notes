package Java_多线程.线程控制;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 多线程概述
 *  	进程: 正在运行的程序,是系统进行资源分配和调用分独立单位;每一个进程都有他自己分内存空间和系统资源
 *  	线程: 是进程中的单个顺序控制流,是一条执行路径,一个只有一条执路径的进程成为单线程程序;一个有多条执路径的进程成为多线程程序
 *   
 *  并行和并发
 *  	前者是逻辑上同时放生,指在某一个时间内同时运行多个程序
 *  	后者是物理上同时发生,指在某一个时间点同时运行多个程序
 *  
 *  Java程序的运行原理
 *  	由Java命令启动JVM,JVM启动就相当于启动了一个进程,接着由该进程创建了一个调用main方法
 *  
 *  如何实现多线程的程序
 *  	由于线程是依赖于进程存在的,所以应该先创建一个进程出来,而进程是系统创建的,所以应该去调用系统功能创建一个进程
 *  	Java是不能直接调用系统功能的,所以,没有办法直接实现多线程程序
 *  	由C/C++去调用系统功能创建进程,然后由Java去调用,然后提供一些类供我们使用,我们就可以实现多线程程序了
 *  
 *  
 *  Thread: 是程序中执行的线程.Java虚拟机允许应用程序同时执行多个执行线程
 *  
 *  Thread类的方法: 
 *  		public Thread(): 分配一个新的Thread对象
 *  		public Thread(String name): 分配一个新的Thread对象,该对象的名字为name	
 *  		public Thread(Runnable target): 分配一个新的Thread对象	
 *  		public Thread(Runnable target, String name): 分配一个新的Thread对象,该对象的名字为name	
 *  		public Thread(ThreadGroup group,Runnable target,String name): 创建一个新的Thread对象,该对象属于group线程组,该对象的名字为name
 *  
 *  		public void start(): 导致此线程开始执行,然后Java虚拟机调用此线程的run方法
 *  		public final String getName(): 返回此线程的名称
 * 			public final void setName(String name): 将此线程的名称更改为等于参数name 
 * 			public static Thread currentThread(): 返回对当前正在执行的线程对象的引用
 * 				对于不是Thread的子类 ,获取线程对象名称: Thread.currentThread().getName()
 * 			public final int getPriority(): 返回此线程的优先级
 * 			public final void setPriority(int newPriority): 更改此线程的优先级
 * 			public static void sleep(long millis): 使当前正在执行的线程以指定的毫秒数暂停
 * 			public final void join(): 等待这个线程终止
 * 			public static void yield(): 暂停当前正在执行的线程对象,并执行其他线程
 * 				让多线程执行更和谐,但是不能靠他保证一人一次
 * 			public final void setDaemon(boolean on): 将此线程标记为守护线程或用户线程
 * 				如果正在运行的线程都是守护线程时,Java虚拟机退出,该方法必须在启动线程前调用
 * 			public void interrupt(): 中断这个线程
 * 			public final ThreadGroup getThreadGroup(): 返回此线程所属的线程组
 * 
 *  创建一个新的执行线程有两种方法: 
 *  	方式1: 继承Thread类
 *  		A: 自定义类继承Thread类
 *  		B: 自定义类类中重写run()方法
 *  				run()方法里面封装的是被线程执行的代码,run()直接调用仅仅是普通方法;start()先启动线程,再由JVM调用run()方法
 *  		C: 创建对象
 *  		D: 启动线程
 *  
 *  	方式2: 实现Runnable接口
 *  		A: 自定义类继承Thread类实现Runnable接口	
 *  		B: 自定义类实现run()方法
 *  		C: 创建自定义类对象
 *  		D: 创建Thread类的对象,并把创建的自定义类对象作为构造方法的参数
 *  		E: 启动线程
 *  
 *  	有了方式1,为什么还要方式2?
 *  		A: 为了避免由于Java单继承带来的局限性
 *  		B: 适合多个相同的程序代码去处理同一个资源的情况,把线程同程序的代码,数据有效分离,较好的体现了面向对象思想
 *  
 *  第三种创建一个 新的执行线程方法: 
 *  		A: 创建一个线程池对象,控制要创建几个线程对象
 *			B: 这种线程池的线程可以执行Runnable对象或者Callable对象代表的线程
 *			C: 创建一个实现Callable接口的类,并实现call()方法
 *			D: 调用如下方法即可: 
 *  			<T> Future<T> submit(Callable<T> task): 提交值返回任务以执行，并返回代表任务待处理结果的Future
 *  		E: 结束线程池
 *  
 */

//方式1: 继承Thread类
class MyThread extends Thread
{
	public MyThread() {}
	public MyThread(String string) 
	{
		super(string);
	}

	//自定义类类中重写run方法
	@Override
	public void run() 
	{
		//一般来说,被线程执行的代码肯定是比较耗时的
		for(int i = 0;i < 10;i++)
		{
			//public final String getName(): 返回此线程的名称
			System.out.println(getName() + ": " + i);
		}
	}
}

//方式2: 实现Runnable接口
class MyRunnable implements Runnable
{
	//实现run()方法
	public void run()
	{
		for(int i = 0;i < 10;i++)
		{
			//由于实现接口的方式,就不能直接使用Thread类的方法
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
	}
	
}

//方式3: 实现Callable接口
class MyCallable implements Callable<String>
{
	//创建一个实现Callable接口的类,并实现call()方法
	@Override
	public String call() throws Exception
	{
		for(int i = 0;i < 20;i++)
		{
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
		return null;
	}	
}


public class 多线程的概述和实现
{
	public static void main(String[] args)
	{
		//方式1: 继承Thread类
		//创建对象
		MyThread mt = new MyThread();
		MyThread mt2 = new MyThread("a");
		
		//public final void setName(String name): 将此线程的名称更改为等于参数name 
		mt.setName("A");
		
		//启动线程
		mt.start();
		mt2.start();
		System.out.println("--------------------------");
		
		//方式2: 实现Runnable接口
		//public static Thread currentThread(): 返回对当前正在执行的线程对象的引用
		System.out.println("currentThread: " + Thread.currentThread().getName());
		
		//创建自定义类对象
		MyRunnable mr = new MyRunnable();
		//创建Thread类的对象,并把创建的自定义类对象作为构造方法的参数
		Thread t = new Thread(mr,"AAA");
		Thread t2 = new Thread(mr,"aaa");
		
		//启动线程
		t.start();
		t2.start();
		System.out.println("-------------------------");
		
		//方式3
		//创建一个线程池对象,控制要创建几个线程对象
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		//<T> Future<T> submit(Callable<T> task): 提交值返回任务以执行，并返回代表任务待处理结果的Future
		es.submit(new MyCallable());
		es.submit(new MyCallable());
		
		//结束线程池
		es.shutdown();
	}
}
