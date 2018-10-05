package Java_多线程.线程控制;

/*
 *  匿名内部类方式使用多线程
 *  	 匿名内部类: 
 *  		new 类名或接口名(){重写方法}
 *  
 *  	new Thread(){代码...}.start();
 *  	new Thread(new Runnable(){代码...}).start();
 */

public class 匿名内部类方式使用多线程
{
	public static void main(String[] args)
	{
		//new Thread(){代码...}.start();
		new Thread()
		{
			@Override
			public void run()
			{
				for(int i = 0;i < 10;i++)
					System.out.println(getName() + ": " + i);
			}
		}.start();
		
		//new Thread(new Runnable{代码...}).start();
		new Thread(new Runnable()
		{			
			@Override
			public void run()
			{
				for(int i = 0;i < 10;i++)
					System.out.println(Thread.currentThread().getName() + ": " + i);
			}
		}).start();
		
		
		//new Thread(new Runnable{代码...}){代码...}.start();
		//这种形式没有意义
		new Thread(new Runnable()
		{			
			@Override
			public void run()
			{
				for(int i = 0;i < 10;i++)
					System.out.println("hello: " + i);
			}
		})
		{
			@Override
			public void run()
			{
				for(int i = 0;i < 10;i++)
					System.out.println("world: " + i);
			}	
		}.start();
	}
}
