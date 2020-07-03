package Java_多线程.线程间通信;

/*
 *  线程间通信
 * 		针对同一个资源的操作有不同种类的线程   	eg: 卖票有进的,也有出的
 * 		
 * 	等待唤醒机制
 * 		Object类的方法: 
 * 			//等待
 * 			public final void wait(): 导致当前线程等待，直到另一个线程调用该对象的notify()方法或notifyAll()方法
 * 			public final void wait(long timeout): 导致当前线程等待，直到另一个线程调用此对象的notify()方法或notifyAll()方法，或指定的时间已过
 * 			//唤醒
 * 			public final void notify(): 唤醒正在等待对象监视器的单个线程
 * 			public final void notifyAll(): 唤醒正在等待对象监视器的所有线程
 * 			注意: 这些方法的调用必须通过锁对象调用
 * 
 * 	Test_通过设置线程(生产者)和获取线程(消费者)针对同一个对象进行操作
 * 		分析: 
 * 			资源类: Student
 * 			设置学生数据: SetThread
 * 			获取学生数据: GetThread
 * 			测试类: 线程间通信 
 * 
 * 		问题1: 按照思路写代码,发现每次的输出结果都是null---0
 * 			原因: 在每个线程中都创建了新的资源,而要求的是设置和获取线程的资源应该是同一个
 * 			解决: 要想让多个类共享同一个数据,可以在外界把这个数据创建出来,通过构造方法传递给其他的类	
 * 		问题2: 为了数据的效果好一些,加入了循环和判断,加入了不同的值,这时候产生了新的问题
 * 			A: 同一个数据出现了多次
 * 				原因: CPU的一点点时间片的执行权就足够执行很多次
 * 			B: 姓名和年龄不匹配
 * 				原因: 线程运行的随机性,属于线程安全问题
 * 			解决: 加锁
 * 				注意: 
 * 					A: 不同种类的线程都要加锁
 * 					B: 必须加同一把锁
 * 		问题3: 虽然数据安全了,但是一次输出一大片
 * 			原因: 
 * 				A: 若消费者先抢到了CPU的执行权,就会去消费数据,但现在的数据是默认值,没有意义,应该等着数据有意义,再消费
 * 				B: 若生产者先抢到了CPU的执行权,就会去产生数据,但是,他产生完数据后,还继续拥有执行权,他就继续产生数据,应该等着消费者把数据消费掉,然后再生产
 * 			解决: 为了处理这样的问题,Java就提供了一种机制,等待唤醒机制
 * 				生产者: 先看是否有数据,有就等待,没有就生产,生产完之后通知消费者来消费
 * 				消费者: 先看是否有数据,有就消费,没有就等待,消费完之后通知生产者生产数据
 * 
 */

//资源类
class Student {
	// 在多个类中共享使用,可以static修饰
	// private static String name;
	// private static int age;
	private String name;
	private int age;
	private boolean flag;// 如果是true就代表有数据

	public Student() {
	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

	// 设置学生数据
	public synchronized void set(String name, int age) {
		// 如果有数据就等待
		if (this.flag) {
			// 等待//public final void wait(): 导致当前线程等待，直到另一个线程调用该对象的notify()方法或notifyAll()方法
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 设置数据
		this.name = name;
		this.age = age;
		// 修改标记
		this.flag = true;
		// 唤醒//public final void notify(): 唤醒正在等待对象监视器的单个线程
		this.notify();
	}

	// 获取学生数据
	public synchronized void get() {
		// 判断有没有数据
		if (!this.flag) {
			// 等待//public final void wait(): 导致当前线程等待，直到另一个线程调用该对象的notify()方法或notifyAll()方法
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 获取数据
		System.out.println(this.getName() + "----" + this.getAge());
		// 修改标记
		this.flag = false;
		// 唤醒//public final void notify(): 唤醒正在等待对象监视器的单个线程
		this.notify();
	}

}

//设置学生数据
class SetThread implements Runnable {
	// 定义学生对象
	private Student s;
	private int i = 0;

	public SetThread() {
	}

	// 通过该构造共享学生对象
	public SetThread(Student s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (true) {
			if (i % 2 == 0)
				s.set("Lemting", 17);
			else
				s.set("AAAAA", 18);
			i++;
		}
	}

}

//获取学生数据
class GetThread implements Runnable {
	// 定义学生对象
	private Student s;

	public GetThread() {
	}

	// 通过该构造共享学生对象
	public GetThread(Student s) {
		this.s = s;
	}

	@Override
	public void run() {
		while (true)
			s.get();
	}

}

public class 线程间通信 {
	public static void main(String[] args) {
		// 创建共享学生对象
		Student s = new Student();

		// 创建设置线程对象和获取线程对象,并通过构造共享同一个学生对象
		SetThread st = new SetThread(s);
		GetThread gt = new GetThread(s);

		// 创建线程对象
		Thread t1 = new Thread(st);
		Thread t2 = new Thread(gt);

		// 启动线程
		t1.start();
		t2.start();
	}
}
