package Java_反射.反射;

/*
 *  反射: 就是通过Class文件对象,去使用源文件中的成员变量,构造方法,成员方法
 *  	Java反射机制是在运行的状态中,对于任意一个类,都能够知道这个类的所有属性和方法;
 *  	对于任意一个对象,都能够调用他的任意一个方法和属性;
 *  	这种动态获取的信息以及动态调用对象的方法的功能称为Java语言的反射机制
 *  
 *  	要想解剖一个类,必须先要获取到这个类的字节码文件对象,而解剖使用的就是Class类中的方法,所以先要获取到每一个字节码文件对应的Class类型的对象.
 *  	而解剖使用的就是Class类中的方法,所以先要获取到每一个字节码文件对应的Class类型的对象
 *  	
 *  Class类: 
 *  	成员变量		Filed: Field提供有关类或接口的单个字段的信息和动态访问.反射的字段可以是类（静态）字段或实例字段
 *  	构造方法		Constructor: Constructor提供了一个类的单个构造函数的信息和访问.
 *  	成员方法		Method: 方法提供有关类和接口上单一方法的信息和访问权限.反射的方法可以是类方法或实例方法（包括抽象方法）。 
 * 		
 * 	Class类的方法
 * 		public static Class forName(String className): 返回与给定字符串名称的类或接口相关联的类对象
 * 		public Constructor<T> getConstructor(Class<?>... parameterTypes): 返回一个Constructor对象，该对象反映Constructor对象表示的类的指定的公共构造方法
 * 				参数代表的是: 你要获取的构造方法的参数个数技术局类型的字节码文件对象
 * 		public Constructor<?>[] getConstructors(): 返回一个包含Constructor对象的数组，反映由此Constructor对象表示的类的所有公共构造方法
 * 		public Constructor<T> getDeclaredConstructor(类<?>... parameterTypes): 返回一个Constructor对象，该对象反映Constructor对象表示的类或接口的指定构造方法
 * 		public Constructor<?>[] getDeclaredConstructors(): 返回反映Constructor对象表示的类声明的所有Constructor对象的数组类
 * 		
 * 		public Field getField(String name): 返回一个Field对象，它反映此表示的类或接口的指定公共成员字段类对象
 * 		public Field[] getFields(): 返回包含一个数组Field对象反射由此表示的类或接口的所有可访问的公共字段类对象
 * 		public Field getDeclaredField(String name): 返回一个Field对象，它反映此表示的类或接口的指定已声明字段类对象
 * 		public Field[] getDeclaredFields(): 返回的数组Field对象反映此表示的类或接口声明的所有字段类对象
 * 
 * 		public Method getMethod(String name, Class<?>... parameterTypes): 返回一个方法对象，它反映此表示的类或接口的指定公共成员方法类对象
 * 		public Method[] getMethods(): 返回包含一个数组方法对象反射由此表示的类或接口的所有公共方法类对象，包括那些由类或接口和那些从超类和超接口继承的声明
 * 		public Method getDeclaredMethod(String name, Class<?>... parameterTypes): 返回一个方法对象，它反映此表示的类或接口的指定声明的方法类对象
 * 		public Method[] getDeclaredMethods(): 返回包含一个数组方法对象反射的类或接口的所有声明的方法,通过此表示类对象,包括公共,保护,默认(包)访问和私有方法,但不包括继承的方法
 * 
 *  三种获取Class对象的方法
 *  	A: Object类的getClass()方法
 *  		Person p = new Person();
 *  		Class c = p.getClass();
 *  	B: 数据类型的静态属性class
 *  		Class c = Person.class;		
 *  	C: Class类中的静态方法
 *  		public static Class forName(String className): 返回与给定字符串名称的类或接口相关联的类对象
 *  	开发中常用第三种,第三种是一个字符串,而不是一个具体的类名,这样就可以把这样的字符串配置到配置文件
 */

class Person{} 

public class 获取class文件对象的三种方法
{	
	public static void main(String[] args) throws ClassNotFoundException
	{
		//方式1: 
		Student p = new Student();
		Class<? extends Student> c = p.getClass();
		Student p2 = new Student();
		Class<? extends Student> c2 = p2.getClass();
		
		System.out.println("p == p2: " + (p == p2));//false
		System.out.println("c == c2: " + (c == c2));//true
		
		//方式2: 
		Class<Student> c3 = Student.class;
		System.out.println("c == c3: " + (c == c3));//true
		
		//方式3: 
		//反射.Person
		Class<?> c4 = Class.forName("反射.Person");
		System.out.println("c == c4: " + (c == c4));
	}
}
