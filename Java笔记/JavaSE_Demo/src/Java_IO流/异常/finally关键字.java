package Java_IO流.异常;

//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

/*
 * finally   异常处理格式try...catch中的一部分
 * 		特点:
 * 			被finally控制的语句一定会执行
 * 			特殊情况下,在执行到finally之前JVM退出了(比如 System.exit(0)) 
 * 		作用:
 * 			用于释放资源,在IO流操作和数据库操作中会见到
 * 	
 * final , finally 和  finalize 的区别
 * 		final: 最终的意思,可以修饰类,成员变量,成员方法
 * 				修饰的类不能被继承;修饰的成员变量是常量;修饰的成员方法不能被重写
 * 		finally: 是异常处理的一部分,用于释放资源
 * 				一般来说,代码一定会执行,特殊情况:在执行到finally之前JVM退出了
 * 		finalize: 是Object类的一个方法,用于垃圾回收	
 * 
 * 若catch语句里有return语句,finally的代码还会执行吗?若果会,是在return前还是在return后?
 * 		会,是在return前
 */

public class finally关键字
{
	public static void main(String[] args)
	{
		System.out.println("a: " + getA());//a: 30
//		String s = "2018-03-02";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d = null;
//		try
//		{
//			d = sdf.parse(s);
//		} 
//		catch (ParseException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();	
//			System.out.println("=-=-");
//			return;
//			//System.exit(0);
//		}
//		finally
//		{
//			System.out.println("---");
//			System.out.println(d);
//		}
	}
	
	public static int getA()
	{
		int a = 10;
		try
		{
			System.out.println(a / 0);
			a = 20;
		} catch (ArithmeticException e)
		{
			a = 30;
			return a;
			/*
			 *  return a 在程序执行到这一步的时候,这里不是返回return, 而是 return 30;这个回路已经完成了
			 *  但是呢,它发现后面finally,所以继续执行finally的内容, a = 40;
			 *  再次回到以前的返回路径,继续走 return 30;
			 */
		}
		finally
		{
			a = 40;
		}
		return a;
	}
}