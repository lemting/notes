package Java_集合框架.JDK5新特性;

/*
 * 泛型类:
 * 		把泛型定义在类上
 * 		格式: public class 类名 <泛型类型1,...>
 * 		注意: 泛型类型必须是引用类型
 * 
 * 泛型方法
 * 		把泛型定义在方法上
 * 		格式: public  <泛型类型>  返回值类型  方法名 (泛型类型)
 *
 * 泛型接口
 * 		把泛型定义在接口上
 * 		格式: public inteface 接口名  <泛型类型1,...>
 *	 	
 */

//泛型接口
//未给定类型
interface fanxing1 <T>{}
//给定String类型
//interface fanxing2 <String> extends fanxing1<String>
//{
//	public abstract <String> void asd();
//}

//泛型类
//未给定类型
class fanxing3<T>
{
	public void wa(T t)
	{
		System.out.println("t: " + t);
	}
}

public class 泛型的概述和使用
{
	public static void main(String[] args)
	{
		fanxing3<Integer> fx3 = new fanxing3<Integer>();
		fx3.wa(new Integer(100));
		wawa("hello");
//		wawa2("wakakka");
	}
	
	//泛型方法
	//未给定类型
	public static <T> void wawa(T t)
	{
		System.out.println("t: " + t);
	}
//	//给定String类型
//	public static <String> void wawa2(String s)
//	{
//		System.out.println("s: " + s);
//	}
}
