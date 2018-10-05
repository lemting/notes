package Java_反射.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*	
 * 	获取单个方法
 * 		public Method getMethod(String name, Class<?>... parameterTypes): 返回一个方法对象，它反映此表示的类或接口的指定公共成员方法类对象
 * 		public Method getDeclaredMethod(String name, Class<?>... parameterTypes): 返回一个方法对象，它反映此表示的类或接口的指定声明的方法类对象
 * 	获取所有方法
 * 		(获取自己的包括父类的所有公共方法)
 * 		public Method[] getMethods(): 返回包含一个数组方法对象反射由此表示的类或接口的所有公共方法类对象，包括那些由类或接口和那些从超类和超接口继承的声明
 * 		(只能获取自己的所有方法,父类的不能)
 * 		public Method[] getDeclaredMethods(): 返回包含一个数组方法对象反射的类或接口的所有声明的方法,通过此表示类对象,包括公共,保护,默认(包)访问和私有方法,但不包括继承的方法
 * 
 * 		Method类的方法: 
 * 			public Object invoke(Object obj,Object... args): 在具有指定参数的方法对象上调用此方法对象表示的基础方法
 * 			暴力访问: public void setAccessible(boolean flag): 值为true则指示反射的对象在使用时应该取消Java语言访问检查
 */

class People
{
	private String name;
	private int age;
	public People(){}
	People(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	
	public void show(){System.out.println("show");}
	@SuppressWarnings("unused")
	private void show2(String s){System.out.println("show2: " + s);}
	public int iii(int i){return ++i;}
	
	@Override
	public String toString(){return "People [name=" + name + ", age=" + age + "]";}	
}

public class 通过反射获取类的成员方法并使用
{	
	public static void main(String[] args) throws Exception
	{
		//创建字节码文件对象
		Class<?> c = Class.forName("反射.People");
		
		//通过无参构造创建对象
		Constructor<?> con = c.getDeclaredConstructor();
		People peo = (People) con.newInstance();
		
		//获取所有方法
		//public Method[] getDeclaredMethods(): 返回包含一个数组方法对象反射的类或接口的所有声明的方法,通过此表示类对象,包括公共,保护,默认(包)访问和私有方法,但不包括继承的方法
		Method[] methods = c.getDeclaredMethods();
		for(Method method: methods)
			System.out.println(method);
		
		//获取单个方法
		//public Method getDeclaredMethod(String name, Class<?>... parameterTypes): 返回一个方法对象，它反映此表示的类或接口的指定声明的方法类对象
		Method m1 = c.getDeclaredMethod("show");
		Method m2 = c.getDeclaredMethod("show2",String.class);
		Method m3 = c.getDeclaredMethod("iii", int.class);
		
		//使用方法
		//public void setAccessible(boolean flag): 值为true则指示反射的对象在使用时应该取消Java语言访问检查
		m2.setAccessible(true);
		//public Object invoke(Object obj,Object... args): 在具有指定参数的方法对象上调用此方法对象表示的基础方法
		m1.invoke(peo);
		m2.invoke(peo, "wakakaka");
		//带返回值的方法调用
		Object obj = m3.invoke(peo,10);
		System.out.println(obj);
	}
}
