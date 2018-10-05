package Java_面向对象.匿名内部类;

//Inter接口测试类
public class InterDemo 
{
	public void method()
	{
		//new Inter(){重写方法;};	//Inter接口的实现类匿名对象
		new Inter()
		{
			public void show(){System.out.println("show");}
			public void show2(){System.out.println("show2");}
		}.show();//直接调用
		new Inter()
		{
			public void show(){System.out.println("show");}
			public void show2(){System.out.println("show2");}
		}.show2();//直接调用
		
		//多态
		Inter i = new Inter()
		{
			public void show(){System.out.println("show");}
			public void show2(){System.out.println("show2");}
		};
		//通过多态调用
		i.show();
		i.show2();
	}
	//接口做形式参数
	public void study(Inter i)
	{
		i.show();
		i.show2();
	}
	
}