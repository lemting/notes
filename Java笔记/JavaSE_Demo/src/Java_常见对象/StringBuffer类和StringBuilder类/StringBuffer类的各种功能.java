package Java_常见对象.StringBuffer类和StringBuilder类;

/*
 * StringBuffer   线程安全的可变字符序列	---  安全性高,效率低
 * StringBuilder  一个可变的字符序列		---  效率高,安全性低
 * 
 * StringBuffer类的功能
 * 
 * 		StringBuffer类的添加功能
 *			public StringBuffer append(String str):把字符串(该方法可以把任意类型数据)添加到字符串缓冲区,返回字符串缓冲区本身
 *			public StringBuffer insert(int offset,String str):在指定位置把字符串(该方法可将任意类型数据)插入到字符串缓冲区,返回字符串缓冲区本身
 *		
 *		StringBuffer类的删除功能
 *			public StringBuffer deleteCharAt(int index):删除指定位置的字符,返回字符串缓冲区本身
 *			public StringBuffer delete(int start,int end):删除从指定位置开始到指定位置结束的内容,返回字符串缓冲区本身
 *
 *		StringBuffer类的替换功能
 *			public StringBuffer replace(int start,int end,String str):从指定位置开始到指定位置结束用指定字符串替换
 *
 *		StringBuffer类的反转功能
 *			public StringBuffer reverse():将字符串缓冲区的内容反转,返回字符串缓冲区本身
 *
 *		StringBuffer类的截取功能(返回值为String类型)
 *			public String substring(int start):从指定位置开始截取字符串缓冲区内容,返回给一个字符串
 *			public String substring(int start,int end):从指定位置开始到指定位置结束截取字符串缓冲区内容,返回给一个字符串
 *
 *StringBuffer类的toString()方法可将StringBuffer里的内容转换成String
 *			String s = new StringBuffer("helloworld").toString();	//String s = "helloworld"; 
 */

public class StringBuffer类的各种功能
{
	public static void main(String[] args)
	{
		//public StringBuffer append(String str):把字符串添加到字符串缓冲区(该方法可以把任意类型数据添加到字符串缓冲区),返回值为本身
		StringBuffer sb1 = new StringBuffer();
		//StringBuffer sb2 = sb1.append("hello");
		//System.out.println("sb1: " + sb1);  //hello
		//System.out.println("sb2: " + sb2);  //hello
		//System.out.println(sb1 == sb2);	  //true
		//sb1.append("hello");
		//sb1.append(true);
		//sb1.append(1024);
		//sb1.append(12.6);	
		sb1.append("hello").append(true).append(1024).append(12.6);//链式编程
		System.out.println("sb1: " + sb1);
		System.out.println("--------------------------");
		
		//public StringBuffer insert(int offset,String str):在指定位置把字符串(该方法可将任意类型数据)插入到字符串缓冲区,返回字符串缓冲区本身
		sb1.insert(5, "world");
		System.out.println("sb1: " + sb1);
		System.out.println("--------------------------");
		
		//public StringBuffer deleteCharAt(int index):删除指定位置的字符,返回字符串缓冲区本身
		StringBuffer sb2 = new StringBuffer();
		sb2.append("hello").append("world").append("java");
		sb2.deleteCharAt(1).deleteCharAt(1);
		System.out.println("sb2: " + sb2);
		System.out.println("--------------------------");
		
		//public StringBuffer delete(int start,int end):删除从指定位置开始到指定位置结束的内容,返回字符串缓冲区本身
		sb2.delete(1,4);
		System.out.println("sb2: " + sb2);
		System.out.println("--------------------------");
		
		//public StringBuffer replace(int start,int end,String str):从指定位置开始到指定位置结束用指定字符串替换
		StringBuffer sb3 = new StringBuffer();
		sb3.append("hello").append("world").append("java");
		sb3.replace(5, 10, "新年快乐");
		System.out.println("sb3: " + sb3);
		System.out.println("--------------------------");
		
		//public StringBuffer reverse():将字符串缓冲区的内容反转,返回字符串缓冲区本身
		StringBuffer sb4 = new StringBuffer();
		sb4.append("hello").append("world").append("java");
		sb4.reverse();
		System.out.println("sb4: " + sb4);
		System.out.println("--------------------------");
		
		//public String substring(int start):从指定位置开始截取字符串缓冲区内容,返回给一个字符串
		StringBuffer sb5 = new StringBuffer();
		sb5.append("hello").append("world").append("java");
		String s1 = sb5.substring(5);
		System.out.println("sb5: " + sb5);
		System.out.println("s1: " + s1);
		System.out.println("--------------------------");
		
		//public String sunstring(int start,int end):从指定位置开始到指定位置结束截取字符串缓冲区内容,返回给一个字符串
		String s2 = sb5.substring(5,10);
		System.out.println("sb5: " + sb5);
		System.out.println("s2: " + s2);
	}
}
