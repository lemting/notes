package Java_集合框架.MapDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Map集合的遍历
 * 
 * 		方式1:
 * 			A: 获取集合中所有键的集合
 * 			B: 遍历键集合,获取每一个键
 * 			C: 根据键获取值
 * 
 * 		方式2: 
 * 			A: 获取所有键值对对象的集合
 * 			B: 遍历键值对对象的集合,得到每一个键值对对象
 * 			C: 根据键值对对象获取键和值
 * 
 */

public class Map集合的遍历
{
	public static void main(String[] args)
	{
		//创建集合对象
		Map<String, String> map = new HashMap<String, String>();
		
		//添加元素
		map.put("a", "A");map.put("b", "B");map.put("c", "C");map.put("d", "D");
	
		//方式1:
		//获取集合中所有键的集合
		Set<String> set = map.keySet();
		//遍历键集合,获取每一个键值
		for(String s: set)
		{
			//根据键获取值
			String value = map.get(s);
			System.out.println(s + "---" + value);
		}
		System.out.println("-------------------------------------");
		
		//方式2: 
		//获取所有键值对对象的集合
		Set<Map.Entry<String, String>> set2 = map.entrySet();
		//遍历键值对对象的集合,得到每一个键值对对象
		for(Map.Entry<String, String> m: set2)
		{
			String key = m.getKey();
			String value = m.getValue();
			System.out.println(key + "---" + value);
		}
	}
}