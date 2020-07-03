package Java_多线程.线程控制;

import java.util.TimerTask;
import java.util.Timer;

/*
 *  定时器:
 *  	是一个应用十分广泛的线程工具,可用于调度多个定时任务,以后后台线程的方式执行,在Java中,可以通过Timer和TimerTask类来实现定义调的功能
 *  
 *  Timer: 定时器类
 *  	线程调度任务以供将来在后台线程中执行的功能 	
 *  Timer的方法: 
 *  	public Timer(): 创建一个新的计时器
 *  	public void schedule(TimerTask task, long delay): 在指定的延迟之后安排指定的任务执行
 *  	public void schedule(TimerTask task, long delay, long period): 在指定 的延迟之后开始 ，重新执行固定延迟执行的指定任务
 *  	public void cancel(): 终止此计时器，丢弃任何当前计划的任务
 *  
 *  TimerTask: 任务抽象类
 *  	可以由计时器进行一次性或重复执行的任务
 *  TimerTask的方法: 
 * 		public abstract void run(): 该定时器任务要执行的操作
 * 		public boolean cancel(): 取消此计时器任务
 * 		public long scheduledExecutionTime(): 返回此任务最近实际执行的预定执行时间
 * 
 *  开发中使用Quartz,一个完全由Java编写的开源调度框架
 *  
 */

//第一个任务
class MyTimerTask extends TimerTask {
	private Timer t;

	public MyTimerTask() {
	}

	public MyTimerTask(Timer t) {
		this.t = t;
	}

	@Override
	public void run() {
		System.out.println("boom!");
		System.out.println("----------------------");
		// 终止定时器//public void cancel(): 终止此计时器，丢弃任何当前计划的任务
		t.cancel();
	}
}

//第二个任务
class MyTimerTask2 extends TimerTask {
	@Override
	public void run() {
		System.out.println("hong!");
	}
}

public class 定时器 {
	public static void main(String[] args) {
		// 执行一次任务
		// 创建定时器对象//public Timer(): 创建一个新的计时器
		Timer t = new Timer();

		// 创建任务1对象
		MyTimerTask mtt1 = new MyTimerTask(t);

		// 3秒后执行任务1//public void schedule(TimerTask task, long delay): 在指定的延迟之后安排指定的任务执行
		t.schedule(mtt1, 3000);

		// 连续执行任务
		// 创建定时器对象
		Timer t2 = new Timer();

		// 创建任务1对象
		MyTimerTask2 mtt2 = new MyTimerTask2();

		// 4秒后执行任务2,之后每隔两秒再执行任务2//public void schedule(TimerTask task, long delay, long
		// period): 在指定 的延迟之后开始 ，重新执行固定延迟执行的指定任务
		t2.schedule(mtt2, 4000, 2000);
	}
}
