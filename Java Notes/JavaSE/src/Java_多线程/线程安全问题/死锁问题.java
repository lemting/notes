package Java_多线程.线程安全问题;

/*
 *  死锁问题: 两个或两个以上的线程在执行的过程中,因争夺资源	产生的一种互相等待现象
 *  
 *  举例: 
 *  	中国人,美国人吃饭案例
 *  	
 *  		正常情况:
 *  			中国人: 筷子两支
 *  			美国人: 刀和叉
 *  		现在:
 *  			中国人: 筷子一支,刀一把
 *  			美国人: 筷子一支,叉一把
 */

//两把锁
class MyLock {
	public static final Object objA = new Object();
	public static final Object objB = new Object();
}

class DieLock extends Thread {
	private boolean flag;

	public DieLock(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		if (flag) {
			synchronized (MyLock.objA) {
				System.out.println("if objA");

				synchronized (MyLock.objB) {
					System.out.println("if objB");
				}
			}
		} else {
			synchronized (MyLock.objB) {
				System.out.println("else objA");

				synchronized (MyLock.objB) {
					System.out.println("else objB");
				}
			}
		}

	}

}

public class 死锁问题 {
	public static void main(String[] args) {
		DieLock dl1 = new DieLock(true);
		DieLock dl2 = new DieLock(false);

		dl1.start();
		dl2.start();
	}
}
