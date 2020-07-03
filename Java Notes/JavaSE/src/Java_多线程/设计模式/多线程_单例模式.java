package Java_多线程.设计模式;

/*
 *  单例模式
 *  	单例模式就是要确保类在内存中只有一个对象,该实例必须自动创建,并且对外提供
 *  		如何保证类在内存中只有一个对象?
 *  			A: 把构造方法私有化
 *  			B: 在成员位置自己创建一个对象
 *  			C: 用一个公共的方法提供访问
 *  		分类: 
 *  			A: 饿汉式 	类一加载就创建对象
 *  				不会出现问题的单例模式
 *  			B: 懒汉式	用的时候,才去创建对象
 *  				可能会出现问题的单例模式
 *  					a: 懒加载(加载延迟)
 *  					b: 线程安全问题
 *  
 *  优点: 在系统内存中只存在一个对象,因此可以节约系统资源,对于一些需要频繁创建和销毁的对象单例模式五一可以提高体统的性能
 *  缺点: 没有抽象层,因此扩展很难;职责过重,在一定程序上违背了单一职责
 */

//饿汉式
class Student {
	// 把构造方法私有化
	private Student() {
	}

	// 在成员位置自己创建一个对象
	// 为了外界不能直接使用修改该对象,使用private
	private static Student s = new Student();

	// 用一个公共的方法提供访问
	public static Student getStudent() {
		return s;
	}
}

//懒汉式
class Teacher {
	// 把构造方法私有化
	private Teacher() {
	}

	// 在成员位置自己创建一个对象
	private static Teacher t = null;

	// 用一个公共的方法提供访问
	public static Teacher getTeacher() {
		if (t == null)
			t = new Teacher();
		return t;
	}

}

public class 多线程_单例模式 {
	public static void main(String[] args) {
		// 获取学生对象
		Student s1 = Student.getStudent();
		Student s2 = Student.getStudent();

		// 比较地址值
		System.out.println(s1 == s2);
		System.out.println(s1 + "---" + s2);
		System.out.println("----------------------------------");

		// 获取老师对象
		Teacher t1 = Teacher.getTeacher();
		Teacher t2 = Teacher.getTeacher();

		// 比较地址值
		System.out.println(t1 == t2);
		System.out.println(t1 + "---" + t2);
	}
}
