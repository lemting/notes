package 栈和队列;

/*
 * 	MyLinkedStack类的测试类
 */

public class Test_MyLinkedStackDemo
{
	public static void main(String[] args)
	{
		MyLinkedStack<Integer> mls = new MyLinkedStack<Integer>();
		//压栈
		for(int i = 0;i < 15;i++)
			mls.push(100 - i);
		//获取栈的大小
		System.out.println("size: " + mls.size());
		System.out.println("--------------------------");
		
		//查找一个数据的位置
		System.out.println("90的位置: " + mls.search(90));
		System.out.println("--------------------------");
		
		//弹栈
		while(!mls.empty())
			System.out.println(mls.pop());
		System.out.println("--------------------------");
		//获取栈的大小
		System.out.println("size: " + mls.size());
	}
}
