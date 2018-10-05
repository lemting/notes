package Java_多线程.线程间通信;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *  线程池: 
 *  	程序每次启动一个新线程成本是比较高的,使用线程池可以很好的提高性能,尤其是当程序中要创建大量生存周期很短的线程时,更改考虑使用线程池
 *  	线程池里的每一个线程结束后,并不会死亡,而是再次会带线程池中成为空闲状态,等待下一个对象来使用
 *  
 *  Executors工厂类的方法: 
 *  	public static ExecutorService newCachedThreadPool(): 创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程
 *  	public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory): 
 *  		创建一个根据需要创建新线程的线程池，但在可用时将重新使用以前构造的线程，并在需要时使用提供的ThreadFactory创建新线程
 *  	public static ExecutorService newFixedThreadPool(int nThreads): 创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程
 *  	public static ExecutorService newSingleThreadExecutor(): 创建一个使用从无界队列运行的单个工作线程的执行程序
 *  
 *  	ExecutorService对象表示一个线程池,可以执行Runnable对象或者Callable对象代表的线程		
 *  	ExecutorService类的方法:
 *  		Future<?> submit(Runnable task): 提交一个可运行的任务执行，并返回一个表示该任务的未来
 *  		<T> Future<T> submit(Callable<T> task): 提交值返回任务以执行，并返回代表任务待处理结果的Future
 *  				Future接口的方法: V get(): 等待计算完成，然后检索其结果	
 *  		void shutdown(): 启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务
 *  
 *  怎么实现线程池
 *  	A: 创建一个线程池对象,控制要创建几个线程对象
 *		B: 这种线程池的线程可以执行Runnable对象或者Callable对象代表的线程
 *		C: 创建一个实现Runnable或Callable接口的类
 *		D: 调用如下方法即可: 
 *			Future<?> submit(Runnable task): 提交一个可运行的任务执行，并返回一个表示该任务的未来
 *  		<T> Future<T> submit(Callable<T> task): 提交值返回任务以执行，并返回代表任务待处理结果的Future
 *  	E: 结束线程池
 *  		
 */

//创建一个实现Runnable接口的类
class XianChengChi implements Runnable
{
	@Override
	public void run()
	{	
		for(int i = 0;i < 20;i++)
		{
			System.out.println(Thread.currentThread().getName() + ": " + i);
		}
	}
}

public class 线程池
{
	public static void main(String[] args)
	{
		//创建一个线程池对象,控制要创建几个线程对象
		//public static ExecutorService newFixedThreadPool(int nThreads): 创建一个线程池，该线程池重用固定数量的从共享无界队列中运行的线程
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		//可以执行Runnable对象或者Callable对象代表的线程
		//Future<?> submit(Runnable task): 提交一个可运行的任务执行，并返回一个表示该任务的未来
		es.submit(new XianChengChi());
		es.submit(new XianChengChi());
		
		//结束线程池
		es.shutdown();
	}
}
