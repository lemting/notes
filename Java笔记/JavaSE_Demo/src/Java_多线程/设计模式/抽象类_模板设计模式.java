package Java_多线程.设计模式;

/*
 *  模板设计模式
 * 		模板方法模式就是定义一个算法的骨架,而将具体的算法延迟到子类中来实现
 * 	
 * 		优点: 使用模板方法模式,在定义算法骨架的同时,可以很灵活的实现具体的算法,满足用户灵活多变的需求
 * 
 * 		缺点: 如果算法骨架有修改的话,则需要修改抽象类
 */

//获取时间抽象类
abstract class GetTime
{
	//算法骨架,求所用时间的方法
	public long getTime()
	{
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		return end - start;
	}
	//抽象方法
	public abstract void code();
}

//循环输出类
class xunhuan extends GetTime
{
	@Override
	public void code()
	{
		for(int i = 0;i < 10000;i++)
			System.out.println(i);
	}
}

public class 抽象类_模板设计模式
{
	public static void main(String[] args)
	{
		GetTime gt = new xunhuan();
		System.out.println(gt.getTime() + "毫秒");
	}
}
