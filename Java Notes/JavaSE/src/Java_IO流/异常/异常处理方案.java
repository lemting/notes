package Java_IO流.异常;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 异常的体系
 * 					 ┌─── Error   严重问题,我们不处理
 *					 │ 
 * 		Throwable ───┤
 * 					 │				  ┌── RuntimeException		运行期异常,我们需要修正代码
 * 					 └─── Exception ──┤
 * 									  └── 非RuntimeException 	编译期异常,必须处理,否则程序通过不了
 * 
 * 编译期与运行期异常的区别
 * 		编译时异常: Java程序必须显示处理,否则程序就会发生错误,无法通过编译
 * 		运行时异常: 无需显示处理,也可以和编译时异常一样处理
 * 
 * 异常处理方案
 * 		A:try...catch...finally
 * 		B:throws 抛出异常
 * 
 * 		try...catch...finally
 * 			处理格式:
 * 				try{可能出现问题代码}
 * 				catch(异常类名  变量名){针对问题的处理;}
 * 				catch(异常类名  变量名){针对问题的处理;}
 * 				...
 * 				finally{释放资源;}
 * 			变形格式:
 * 				try{可能出现问题代码}
 * 				catch(异常类名  变量名){针对问题的处理;}
 * 				...
 * 			注意: 
 * 				A:try 里面的内容越少越好
 * 				B:catch 里面必须有内容,哪怕只是给出一个简单的提示
 * 				C:一旦try里面出现问题,就会在这里把问题抛出去,然后和catch里的问题进行匹配,一旦匹配,就执行catch里的代码,然后就结束了try...catch
 * 				D:能明确异常类名的的尽量明确,不要用更上层的类来处理
 * 				E:平级关系的异常谁前谁后无所谓,但父子关系必须父类在后面
 * 
 * 			JDk7出现了一个新的异常处理方案
 * 				格式:
 * 					try{可能出现问题代码}
 * 					catch(异常类名  变量名1 | 异常类名  变量名2 | ...){针对问题的处理;}
 * 				注意:这个方案虽然简洁,但是也不好
 * 					A: 处理方式是一致的(实际开发中,好多时可能就是针对同类型的问题,给出同一个处理)
 * 					B: 多个异常之间必须是平级关系
 * 
 * 		throws
 * 			定义功能方法时,需要把出现的问题暴露出来,让调用者去处理,那么就通过throws在方法上标识
 * 			格式: 
 * 				throws 异常类名
 * 			注意: 
 * 				A: 这个格式必须跟在方法的括号后面
 * 				B: 尽量不要在main方法上抛出异常
 * 			小结:
 * 				编译期异常抛出,将来调用者必须处理
 * 				运行期异常排除,将来调用者可以不用处理	
 * 
 * throw: 如果出现了异常情况,我们可以把该异常抛出,这个时候的抛出应该是异常类的对象
 * 
 * throws 和 throw
 * 		throws: 
 * 			用在方法声明后,更多是异常类名
 * 			可以跟多个异常类名,用逗号隔开
 * 			表示抛出异常,由方法的调用者来处理
 * 			throws表示出现异常的一种可能性,并且不一定会发生这些异常
 * 		throw
 * 			用在方法体里,跟的是异常对象名
 * 			只能抛出一个异常对象名
 * 			表示抛出异常,由方法体内的语句处理
 * 			throw则使抛出了异常,执行throw则一定抛出了某种异常
 * 			
 */

public class 异常处理方案 {
	public static void main(String[] args) throws ParseException {
		int a = 10;
		int b = 0;
		int[] arr = { 1, 2, 3 };
		// try...catch...finally
		try {
			System.out.println(a / b);
		} catch (ArithmeticException ae) {
			System.out.println("除数不能为0");
		}

		try {
			System.out.println(arr[3]);
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("数组越界");
		}

		// throws
		method();
		method2();
		// throw
		method3();
	}

	// 运行期问题异常抛出
	public static void method2() throws ArithmeticException {
		int a = 10;
		int b = 0;
		System.out.println(a / b);
	}

	public static void method3() {
		int a = 10;
		int b = 0;
		if (b == 0)
			throw new ArithmeticException();
		else
			System.out.println(a / b);
	}

	// 编译期问题异常抛出
	// 在方法声明上抛出,是为了告诉调用者,你注意了,我有问题
	public static void method() throws ParseException {
		String s = "2014-11-12";
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date d = sdf.parse(s);
		System.out.println(d);
	}

}