package Java_常见对象.StringBuffer类和StringBuilder类;

/*
 * 线程安全:
 * 
 * 安全 ------ 同步  ------- 效率低
 * 不安全 ---- 不同步 ------ 效率高
 * 
 * 
 * StringBuffer   线程安全的可变字符序列	---  安全性高,效率低
 * StringBuilder  一个可变的字符序列		---  效率高,安全性低
 * 
 * 	StringBuilder类的方法    StringBuffer类方法将方法返回值类型改为StringBuilder,即可使用
 * 
 * 	StringBuilder类提供一个与 StringBuffer 兼容的 API,但不保证同步.该类被设计用作 StringBuffer 的一个简易替换,
 * 		用在字符串缓冲区被单个线程使用的时候.如果可能,建议优先采用该类,因其大多数在现实中比 StringBuffer 要快
*/

public class StringBulider类与StringBuffer类
{
	public static void main(String[] args)
	{
		StringBuilder sb1 = new StringBuilder();
		sb1.append("asdsad").append(true).append(1024).append(12.6);//链式编程
		System.out.println("sb1: " + sb1);
		System.out.println("--------------------------");
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append("hello").append("world").append("java");
		sb2.deleteCharAt(1).deleteCharAt(1);
		System.out.println("sb2: " + sb2);
		System.out.println("--------------------------");
		
		StringBuilder sb3 = new StringBuilder();
		sb3.append("hello").append("world").append("java");
		sb3.replace(5, 10, "新年快乐");
		System.out.println("sb3: " + sb3);
	}
}
