package Java_集合框架.MapDemo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * Map: 将键映射到值的对象,无序
 * 		Map集合的数据结构针对键有效,跟值无关
 * 		最大的特点: 将键映射到值的对象,一个映射不能包含重复的键;每个键最多可以映射到一个值
 * 
 * Map集合与Collection集合的区别
 * 		Map集合储存元素是成对存在的,Map集合的键是唯一的,值是可重复的;Map集合的数据结构针对键有效,跟值无关
 * 		Collection集合储存元素是单独出现的,Collection的儿子Set是唯一的,List是可重复的;Collection集合的数据结构针对元素有效
 * 
 * Map集合的功能:
 * 		A: 添加功能
 * 			V put(K key,V value): 将指定的值与该映射中的指定键相关联
 * 				如果键是第一次储存,就直接储存元素,返回null;若不是,就用值替换以前的值,并返回以前的值
 * 			void putAll(Map<? extends K,? extends V> m): 将指定集合的所有映射复制到此集合
 *		B: 删除功能
 *			void clear(): 从该映射中删除所有的映射
 *			V remove(Object key): 根据键删除键值对元素,并把值返回
 *		C: 判断功能
 *			boolean containsKey(Object key): 判断集合是否包含指定的键
 *			boolean containsValue(Object value): 判断集合是否包含指定的值
 *			boolean isEmpty(): 判断集合是否为空
 *		D: 获取功能
 *			Set<Map.Entry<K,V>> entrySet(): 获取集合中所有键值对对象的集合
 *			V get(Object key): 根据键获取值
 *			Set<K> keySet(): 获取集合中所有键的集合
 *			Collection<V> values(): 获取集合中所有值的集合
 *		E: 长度功能
 *			int size(): 返回集合中键值对的对数
 *		F:修改功能
 *			V put(K key,V value): 将指定的值与该映射中的指定键相关联
 * 				如果键是第一次储存,就直接储存元素,返回null;若不是,就用值替换以前的值,并返回以前的值
 *
 * 其子类:
 * 		Hashtable:是基于哈希表的Map接口实现,可以保证键的唯一性
 * 				线程安全,效率低,不允许null键null值
 *		HashMap: 是基于哈希表的Map接口实现,可以保证键的唯一性
 * 				哈希表的作用是用于保证键的唯一性的
 * 				线程不安全,效率高,允许null键null值
 * 			LinkedHashMap: Map接口的哈希表和链表实现,具有可预知的迭代顺序
 * 				哈希表的作用是用于保证键的唯一性的
 * 				链表的作用是用于保证键的迭代有序(储存和取出顺序一致)
 * 		TreeMap: 是基于二叉树的Map接口实现,可以保证键的排序,唯一
 */

public class Map集合的功能
{
	public static void main(String[] args)
	{
		//创建集合对象
		Map<String, String> map = new HashMap<String, String>();
		
		//V put(K key,V value): 将指定的值与该映射中的指定键相关联
		System.out.println("put: " + map.put("w", "W"));//如果键是第一次储存,就直接储存元素,返回null
		System.out.println("put: " + map.put("w", "Q"));//若不是,就用值替换以前的值,并返回以前的值
		System.out.println("map: " + map);
		System.out.println("-------------------------------");
	
		//void clear(): 从该映射中删除所有的映射
		map.clear();
		System.out.println("map: " + map);
		System.out.println("------------------------------");
		
		//添加元素
		map.put("a", "A");map.put("b", "B");map.put("c", "C");
		System.out.println("map: " + map);
		System.out.println("------------------------------");
		
		//V remove(Object key): 根据键删除键值对元素,并把值返回
		System.out.println("remove: " + map.remove("b"));
		System.out.println("remove: " + map.remove("g"));
		System.out.println("map: " + map);
		System.out.println("------------------------------");
		
		//boolean containsKey(Object key): 判断集合是否包含指定的键
		System.out.println("containsKey: " + map.containsKey("a"));
		
		//boolean containsValue(Object value): 判断集合是否包含指定的值
		System.out.println("containsValue: " + map.containsValue("C"));
		
		//boolean isEmpty(): 判断集合是否为空
		System.out.println("isEmpty: " + map.isEmpty());
		System.out.println("------------------------------");
		
		//V get(Object key): 根据键获取值
		System.out.println("get: " + map.get("a"));
		System.out.println("------------------------------");

		//Set<K> keySet(): 获取集合中所有键的集合
		Set<String> set = map.keySet();
		for(String s: set)
			System.out.print(s + "\t");
		System.out.println("\n------------------------------");
				
		//Collection<V> values(): 获取集合中所有值的集合
		Collection<String> al = map.values();
		for(String s: al)
			System.out.print(s + "\t");
		System.out.println("\n------------------------------");
		
		//int size(): 返回集合中键值对的对数
		System.out.println("size: " + map.size());
		
	}
}