package Java_IO流.Properties集合;

import java.util.Properties;
import java.util.Set;

/*	
 * 	Properties: 属性集合类,是一个可以和IO流相结合的集合类
 * 			可保存在流中或从流中加载,属性列表中每一个键及其对应值都是一个字符串.
 * 			是Hashtable的子类,说明是一个Map集合
 * 	
 * 	Properties的构造方法
 * 		public Properties()创建一个没有默认值的空属性列表。 	
 * 
 *  Properties的特殊功能
 *  	public Object setProperty(String key,String value): 添加元素(键值对)
 *   	public String getProperty(String key): 获取元素,根据键找值
 * 		public Set<String> stringPropertyNames(): 获取所有键的集合
 * 		
 *		public void load(InputStream inStream): 从输入字节流读取属性列表(键值对)
 *		public void load(Reader reader): 把文件中的数据(键值对)储存到Properties集合中
 *		public void store(OutputStream out,String comments): 将此Properties集合中的数据储存到文件中
 *		public void store(Writer writer,String comments): 将此Properties集合中的数据储存到文件中,comments为文件描述,comments为null时没有文件描述
 *
 */

public class Properties集合的功能
{
	public static void main(String[] args)
	{
		//作为Map集合的使用
		Properties prop = new Properties();	
		//添加元素
		prop.put("it002", "hello");
		prop.put("it003", "world");
		prop.put("it004", "java");
		//遍历集合
		Set<Object> set = prop.keySet();
		for(Object obj: set){
			Object value = prop.get(obj);
			System.out.println(obj + "---" + value);
		}
		System.out.println("---------------------------------");
	
		//Properties的特殊功能
		//public Object setProperty(String key,String value): 添加元素(键值对)
		prop.setProperty("A", "Hello");
		prop.setProperty("B", "World");
		prop.setProperty("C", "Java");
		
		//public Set<String> stringPropertyNames(): 获取所有键的集合
		Set<String> set2 = prop.stringPropertyNames();

		for(String str: set2){
			//public String getProperty(String key): 获取元素,根据键找值
			String value = prop.getProperty(str);
			System.out.println(str + "---" + value);
		}
	}
}
