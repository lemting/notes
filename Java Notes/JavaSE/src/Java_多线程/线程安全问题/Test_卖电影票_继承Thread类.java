package Java_多线程.线程安全问题;

/*
 *  某电影院目前正在上映大片,共有100张票,而他有3个售票窗口,请设计一个程序模拟该电影院售票
 */

class SellTicket_Extends extends Thread {
	public SellTicket_Extends() {
	}

	public SellTicket_Extends(String name) {
		super(name);
	}

	// 定义100张票
	public static int tickets = 100;

	public void run() {
		while (true) {
			if (tickets % 2 == 0) {
				// 同步代码块
				synchronized (this) {
					if (tickets > 0) {
						System.out.println(getName() + "正在出售第  " + (tickets--) + " 张票");
						// 为了模拟真实场景,每出一张票延迟100毫秒
						try {
							sleep(100);
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
				System.out.println(getName() + "正在出售第  " + (tickets--) + " 张票");
				// 为了模拟真实场景,每出一张票延迟100毫秒
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class Test_卖电影票_继承Thread类 {
	public static void main(String[] args) {
		SellTicket_Extends st1 = new SellTicket_Extends("窗口1");
		SellTicket_Extends st2 = new SellTicket_Extends("窗口2");
		SellTicket_Extends st3 = new SellTicket_Extends("窗口3");

		// 启动线程
		st1.start();
		st2.start();
		st3.start();
	}
}
