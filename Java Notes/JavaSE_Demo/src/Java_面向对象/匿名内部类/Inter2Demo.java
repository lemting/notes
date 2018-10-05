package Java_面向对象.匿名内部类;

//Inter2的测试类
public class Inter2Demo
{
	//返回值为Inter的实现类对象
	public static Inter2 method()
	{
		return new Inter2()
		{	
			public void show()
			{
				System.out.println("Hello World");		
			}
		};
	}
}