package Java_反射.反射;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

class a
{
	private String name;
	public a(){}
	public void aaa(){System.out.println("A B C D E F G\nH I J K L M N\nO P Q   R S T\nU V W   X Y Z");}
	@Override
	public String toString(){return "a [name=" + name + "]";}
}

public class 通过反射运行配置文件内容
{
	public static void main(String[] args) throws Exception
	{
		//添加键值对数据
		Properties prop = new Properties();
		FileReader fr = new FileReader("src\\反射\\配置文件.txt");
		prop.load(fr);
		fr.close();

		//获取数据
		String className = prop.getProperty("className");
		String methodName = prop.getProperty("methodName");
		
		//反射
		Class<?> c = Class.forName(className); 
		
		//获取无参构造创建对象
		Constructor<?> con = c.getDeclaredConstructor();
		Object obj = con.newInstance();
		
		//调用方法
		Method m = c.getDeclaredMethod(methodName);
		m.invoke(obj);
	}
}