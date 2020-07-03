package Java_多线程.线程控制;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*
 *  A: 创建任务类: 输出当前时间: "HH:mm:ss"
 *  B: 创建SimpleDateFormat类对象格式化时间
 *  C: 创建定时器对象
 *  D: 调用schedule方法,延时0毫秒执行任务,每过1秒再次执行
 */

//任务类
class TaskDemo extends TimerTask {
	private SimpleDateFormat sdf;

	public TaskDemo() {
	}

	public TaskDemo(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	@Override
	public void run() {
		// 输出当前时间: "HH:mm:ss"
		System.out.println(sdf.format(new Date()));
	}

}

public class Test_秒表 {
	public static void main(String[] args) {
		// 创建SimpleDateFormat类对象格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		// 创建定时器对象
		Timer t = new Timer();

		// 调用schedule方法,延时0毫秒执行任务,每过1秒再次执行
		t.schedule(new TaskDemo(sdf), 0, 1000);
	}
}
