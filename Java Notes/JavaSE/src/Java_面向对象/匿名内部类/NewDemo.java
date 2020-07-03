package Java_面向对象.匿名内部类;

public class NewDemo  {
	public static void main(String[] args) {
		//创建测试类对象
		InterDemo id = new InterDemo();
		id.method();
		System.out.println("--------------------");
		//Inter接口的实现类对象
		Inter i = new Student();
		id.study(i);
		System.out.println("--------------------");
		id.study (
			//Inter接口的实现类匿名对象
			new Inter() {
				public void show(){System.out.println("show");}
				public void show2(){System.out.println("show2");}
			}
		);
		/*链式编程
			对象.方法1().方法2().、、、.方法n();
			方法1()调用完毕后,返回一个对象；
			方法2()调用完毕后,返回一个对象；
			......
			方法n()调用完毕后,可能是对象，也可能不是对象。
			Inter2Demo.method()返回值为一个对象
		*/
		Inter2Demo.method().show();
		System.out.println(id.getClass().getName() + '@' + id.hashCode());
		System.out.println(id.toString());
	}
}