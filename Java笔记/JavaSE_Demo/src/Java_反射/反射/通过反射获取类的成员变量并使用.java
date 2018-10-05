package Java_反射.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/*	获取一个成员变量
 * 		public Field getField(String name): 返回一个Field对象，它反映此表示的类或接口的指定公共成员字段类对象
 * 		public Field getDeclaredField(String name): 返回一个Field对象，它反映此表示的类或接口的指定已声明字段类对象
 * 	获取全部成员变量
 * 		public Field[] getFields(): 返回包含一个数组Field对象反射由此表示的类或接口的所有可访问的公共字段类对象
 * 		public Field[] getDeclaredFields(): 返回的数组Field对象反映此表示的类或接口声明的所有字段类对象
 * 
 *  	Field类的方法: 
 *  		public void set(Object obj, Object value): 将指定对象参数上的此Field对象表示的字段设置为指定的新值
 *  		暴力访问: public void setAccessible(boolean flag): 值为true则指示反射的对象在使用时应该取消Java语言访问检查
 */

class Teacher
{
	private String name;
	private int age;
	public String id;
	public Teacher(){}
	public Teacher(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString(){return "Teacher [name=" + name + ", age=" + age + ", id=" + id + "]";}	
} 

public class 通过反射获取类的成员变量并使用
{
	public static void main(String[] args) throws Exception
	{
		//获取字节码文件对象
		Class<?> c = Class.forName("反射.Teacher");
		
		//获取所有的成员变量
		//public Field getDeclaredField(String name): 返回一个Field对象，它反映此表示的类或接口的指定已声明字段类对象
		Field[] fields = c.getDeclaredFields();
		for(Field f: fields)
			System.out.println(f);
		
		//通过无参构造创建对象
		Constructor<?> con = c.getDeclaredConstructor();
		Teacher tea = (Teacher) con.newInstance();
		
		//获取单个成员变量
		Field field = c.getDeclaredField("name");
		//public void setAccessible(boolean flag): 值为true则指示反射的对象在使用时应该取消Java语言访问检查
		field.setAccessible(true);
		//为该成员赋值
		//public void set(Object obj, Object value): 将指定对象参数上的此Field对象表示的字段设置为指定的新值
		field.set(tea, "Lemting");
		System.out.println(tea);
		
		//设置属性
		setPropertv(tea, "name", "LiuRong");
		setPropertv(tea, "age", 18);
		setPropertv(tea, "id", "180636");	
		System.out.println(tea);
	}
	
	//将obj对象中名为propertvName的属性的值设置为value
	public static void setPropertv(Object obj,String propertvName,Object value) 
	{
		//获取字节码文件对象
		Class<?> c = obj.getClass();
		try
		{
			//获取名为propertvName的成员变量
			Field field = c.getDeclaredField(propertvName);
			//暴力访问
			field.setAccessible(true);
			//设置成员变量的值为value
			field.set(obj, value);
		}
		catch (NoSuchFieldException e)
		{e.printStackTrace();}
		catch (SecurityException e)
		{e.printStackTrace();}
		catch (IllegalArgumentException e)
		{e.printStackTrace();}
		catch (IllegalAccessException e)
		{e.printStackTrace();}
	}
}
