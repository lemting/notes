package Java_IO流.文件和递归;

/*
 * 递归: 方法定义中调用方法本身
 * 
 * 注意事项:
 * 		A: 要有出口,否则就是死递归
 * 		B: 次数不能太多,否则就内存溢出
 * 		C: 构造方法不能递归使用
 * 
 * 求阶乘
 * 		求n的阶乘
 * 
 * 不死神兔
 * 		有一对兔子,从出生后第3个月期每个月都生一堆小兔子,小兔子长到第三个月后每个月有生一堆小兔子,假如兔子都不死,问第n个月的兔子对数为多少？
 * 		1,1,2,3,5,8,...
 */

public class 递归概述和注意事项 {
	public static void main(String[] args) {
		System.out.println("阶乘: " + show(5));
		System.out.println("不死神兔: " + shentu(10));
//		System.out.println("不死神兔: " + shentu2(11));
	}

	// 递归求阶乘
	public static int show(int n) {
		if (n > 1)
			return n * show(--n);
		else
			return 1;
	}

	// 不死神兔
	public static int shentu(int munth) {
		if (munth == 1 || munth == 2)
			return 1;
		else
			return shentu(munth - 1) + shentu(munth - 2);
	}

//	public static int shentu2(int munth) {
//		int a = 1;int b = 1;
//		for(int i = 3;i <= munth;i++)
//		{
//			int temp = a;
//			a = b;
//			b += temp;
//		}
//		return b;
//	}
}
