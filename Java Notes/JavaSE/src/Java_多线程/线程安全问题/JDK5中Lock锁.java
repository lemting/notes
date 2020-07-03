package Java_多线程.线程安全问题;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*	
 *  JDk5以后提供了新的锁对象Lock
 * 	
 * 	Lock: 实现提供比使用synchronized方法和语句可以获得的更广泛的锁定操作
 * 	
 * 	Lock接口的方法:
 * 		void lock(): 获得锁 
 * 		void unlock(): 释放锁
 * 
 * 	ReentrantLock: Lock接口的实现类
 * 		
 */

class sell implements Runnable {
	// 票50张
	private int tickets = 50;
	// 锁对象
	private Lock l = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			try {
				// void lock(): 获得锁
				l.lock();

				if (tickets > 0) {
					System.out.println(Thread.currentThread().getName() + "正在出售第  " + (tickets--) + " 张票");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				// void unlock(): 释放锁
				l.unlock();
			}

		}
	}

}

public class JDK5中Lock锁 {
	public static void main(String[] args) {
		sell s = new sell();
		Thread t1 = new Thread(s, "窗口A");
		Thread t2 = new Thread(s, "窗口B");

		// 启动线程
		t1.start();
		t2.start();
	}
}
