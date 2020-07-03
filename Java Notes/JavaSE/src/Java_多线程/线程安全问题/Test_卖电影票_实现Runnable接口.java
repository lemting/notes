package Java_多线程.线程安全问题;

/*
 *  某电影院目前正在上映大片,共有100张票,而他有3个售票窗口,请设计一个程序模拟该电影院售票
 */

class SellTicket_Implements implements Runnable {
	// 定义100张票
	private int tickets = 100;

	@Override
	public void run() {
		while (true) {
			if (tickets % 2 == 0) {
				// 同步代码块
				synchronized (this) {
					if (tickets > 0) {
						System.out.println(Thread.currentThread().getName() + "正在出售第  " + (tickets--) + " 张票");
						// 为了模拟真实场景,每出一张票延迟100毫秒
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} else
				selltick();

		}
	}

	// 同步方法
	public synchronized void selltick() {
		synchronized (this) {
			if (tickets > 0) {
				System.out.println(Thread.currentThread().getName() + "正在出售第  " + (tickets--) + " 张票");
				// 为了模拟真实场景,每出一张票延迟100毫秒
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class Test_卖电影票_实现Runnable接口 {
	public static void main(String[] args) {
		// 创建资源对象
		SellTicket_Implements st = new SellTicket_Implements();

		// 创建三个线程对象
		Thread t1 = new Thread(st, "窗口1");
		Thread t2 = new Thread(st, "窗口2");
		Thread t3 = new Thread(st, "窗口3");

		// 启动线程
		t1.start();
		t2.start();
		t3.start();
	}
}
