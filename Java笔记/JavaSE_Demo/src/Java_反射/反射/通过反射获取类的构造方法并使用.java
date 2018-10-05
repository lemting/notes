package Java_反射.反射;

import java.lang.reflect.Constructor;

/*	
 * 	通过反射获取构造方法
 * 		获取单个构造方法
 * 		public Constructor<T> getConstructor(Class<?>... parameterTypes): 返回一个Constructor对象，该对象反映Constructor对象表示的类的指定的公共构造方法
 * 				参数代表的是: 你要获取的构造方法的参数个数技术局类型的字节码文件对象
 * 		public Constructor<T> getDeclaredConstructor(类<?>... parameterTypes): 返回一个Constructor对象，该对象反映Constructor对象表示的类或接口的指定构造方法
 * 		获取多个构造方法
 * 		public Constructor<?>[] getConstructors(): 返回一个包含Constructor对象的数组，反映由此Constructor对象表示的类的所有公共构造方法
 * 		public Constructor<?>[] getDeclaredConstructors(): 返回反映Constructor对象表示的类声明的所有Constructor对象的数组类
 *  	
 *  	使用构造方法
 *  		Constructor类的方法: public T newInstance(Object... initargs): 用由此Constructor对象表示的构造函数，使用指定的初始化参数创建和初始化构造函数的声明类的新实例
 *  					暴力访问: public void setAccessible(boolean flag): 值为true则指示反射的对象在使用时应该取消Java语言访问检查
 */

class Student
{
	private String name;
	private int age;
	public Student(){}
	Student(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	@SuppressWarnings("unused")
	private Student(String name){this.name = name;}
	@Override
	public String toString(){return "Student [name=" + name + ", age=" + age + "]";}	
} 

public class 通过反射获取类的构造方法并使用
{
	public static void main(String[] args) throws Exception
	{
		//获取字节码文件对象
		//public static Class forName(String className): 返回与给定字符串名称的类或接口相关联的类对象
		Class<?> c = Class.forName("反射.Student");
		
		//获取构造方法
		//public Constructor<?>[] getConstructors(): 返回一个包含Constructor对象的数组，反映由此Constructor对象表示的类的所有公共构造方法
		//获取所有的构造方法
		Constructor<?>[] cons = c.getDeclaredConstructors();	
		for(Constructor<?> con: cons)
			System.out.println(con);
		System.out.println("-------------------------------");
		//public Constructor<T> getDeclaredConstructor(类<?>... parameterTypes): 返回一个Constructor对象，该对象反映Constructor对象表示的类或接口的指定构造方法
		//获取无参构造方法
		Constructor<?> con = c.getDeclaredConstructor();//返回值是构造方法对象
		//获取带参构造方法
		Constructor<?> con2 = c.getDeclaredConstructor(String.class,int.class);
		//获取私有构造方法
		Constructor<?> con3 = c.getDeclaredConstructor(String.class);
		
		//创建该构造方法的新实例
		//public T newInstance(Object... initargs): 用由此Constructor对象表示的构造函数，使用指定的初始化参数创建和初始化构造函数的声明类的新实例
		//使用无参构造方法
		Student stu = (Student) con.newInstance();
		//使用带参构造方法
		Student stu2 = (Student) con2.newInstance("Lemting",18);
		//使用私有构造方法
		//public void setAccessible(boolean flag): 值为true则指示反射的对象在使用时应该取消Java语言访问检查
		con3.setAccessible(true);
		Student stu3 = (Student) con3.newInstance("Lemting");
		System.out.println(stu + "\n" + stu2 + "\n" + stu3);
 	} 
}
