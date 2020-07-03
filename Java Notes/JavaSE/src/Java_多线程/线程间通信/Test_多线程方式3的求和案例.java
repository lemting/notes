package Java_多线程.线程间通信;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 *  <T> Future<T> submit(Callable<T> task): 提交值返回任务以执行，并返回代表任务待处理结果的Future
 * 		V get(): 等待计算完成，然后检索其结果
 */

class Test_Callable implements Callable<Integer> {
	private int number;

	public Test_Callable(int number) {
		this.number = number;
	}

	// 求1到number的和
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i <= number; i++) {
			sum += i;
		}
		return sum;
	}

}

public class Test_多线程方式3的求和案例 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 创建线程池对象
		ExecutorService es = Executors.newFixedThreadPool(2);

		// 获取call()方法的结果
		Future<Integer> ft1 = es.submit(new Test_Callable(100));
		Future<Integer> ft2 = es.submit(new Test_Callable(50));
		Integer i1 = ft1.get();
		Integer i2 = ft2.get();

		// 输出结果
		System.out.println("i1: " + i1);
		System.out.println("i2: " + i2);

		// 结束线程池
		es.shutdown();
	}
}
