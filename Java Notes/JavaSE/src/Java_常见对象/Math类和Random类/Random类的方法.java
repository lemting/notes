package Java_常见对象.Math类和Random类;

import java.util.Random;

/*
 * Random类是产生伪随机数的类
 * 
 * Random类的构造方法
 * 
 * 		public Random(): 创建一个新的伪随机数生成器,没有给种子,用的是默认种子,是当前时间的毫秒值
 * 		public Random(long seed): 给出指定的种子
 * 		给定种子后,相同种子的 Random 每次得到的随机数是相同的
 * 
 * Random类的成员方法
 * 		
 * 		public int nextInt():返回的是int范围内的随机数 			 nextBytes(),nextLong(),nextFloat(),nextDouble(),nextBoolean()
 * 		public int nextInt(int n):返回的是 [0,n) 范围的伪随机数	 nextBytes(),nextLong(),nextFloat(),nextDouble(),nextBoolean()
 * 		public void setSeed(int seed): 设置种子(相同种子的 Random 每次得到的随机数是相同的)
 */

public class Random类的方法 {
	public static void main(String[] args) {
		Random r = new Random();
		for(int i = 0;i < 10;i++)
			System.out.println(r.nextInt(100) + 1);
		
		Random r2 = new Random(1111);
		for(int i = 0;i < 10;i++)
			System.out.println(r2.nextInt(100) + 1);
	}
}
