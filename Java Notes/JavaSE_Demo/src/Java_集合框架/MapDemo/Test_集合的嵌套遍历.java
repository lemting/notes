package Java_集合框架.MapDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/*
 * 集合的嵌套遍历
 * 
 * 		HashMap 嵌套  HashMap	
 * 			HashMap<T,HashMap<T2,V2>>
 * 			HashMap的值为 HashMap
 * 		HashMap 嵌套  ArrayList
 * 			HashMap<T,ArrayList<T2>>
 * 			HashMap的值为 ArrayList
 * 		ArrayList 嵌套  HashMap
 * 			ArrayList<HashMap<T2,V2>>
 * 			ArrayList的元素为 HashMap
 * 		HashMap 嵌套  HashMap 嵌套  ArrayList(三层嵌套)
 * 			HashMap<T,HashMap<T2,ArrayList<T3>>
 * 			HashMap的值为HashMap,值的值为ArrayList
 */

public class Test_集合的嵌套遍历
{
	public static void main(String[] args)
	{
		//HashMap 嵌套  HashMap
		//创建对象并添加元素
		HashMap<String, HashMap<String, Integer>> hashmap = new HashMap<String, HashMap<String,Integer>>();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("a", 97);hm.put("b", 98);hm.put("c", 99);hm.put("d", 100);
		HashMap<String, Integer> hm2 = new HashMap<String, Integer>();
		hm2.put("A", 65);hm2.put("B", 66);hm2.put("C", 67);hm2.put("D", 68);
		hashmap.put("1", hm);hashmap.put("2", hm2);
		//遍历集合
		Set<String> set = hashmap.keySet();
		for(String s: set)
		{
			HashMap<String, Integer> h = hashmap.get(s);
			Set<String> set2 = h.keySet();
			System.out.println(s + ": ");
			for(String str: set2)
			{
				Integer it = h.get(str);
				System.out.println("\t" + str + " --- " + it);
			}
		}
		System.out.println("----------------------------------");
		
		//HashMap 嵌套  ArrayList
		//创建对象并添加元素
		HashMap<String, ArrayList<String>> hashmap2 = new HashMap<String, ArrayList<String>>();
		ArrayList<String> al = new ArrayList<String>();
		al.add("001");al.add("002");al.add("003");al.add("004");
		ArrayList<String> al2 = new ArrayList<String>();
		al2.add("100");al2.add("200");al2.add("300");al2.add("400");
		hashmap2.put("1", al);hashmap2.put("2", al2);
		//遍历集合
		Set<String> set2 = hashmap.keySet();	
		for(String s: set2)
		{
			ArrayList<String> arr = hashmap2.get(s);
			System.out.print(s + ": ");
			for(String str: arr)
			{
				System.out.print(str + "    ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
		
		//ArrayList 嵌套  HashMap
		//创建对象并添加元素
		ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> hashm = new HashMap<String, String>();
		hashm.put("java", "Java");hashm.put("javaee", "JavaEE");hashm.put("javase", "JavaSE");
		HashMap<String, String> hashm2 = new HashMap<String, String>();	
		hashm2.put("c", "C");hashm2.put("c++", "C++");hashm2.put("c#", "C#");
		arraylist.add(hashm);arraylist.add(hashm2);
		//遍历集合
		for(HashMap<String, String> hMap: arraylist)
		{
			Set<String> set3 = hMap.keySet();
			System.out.println("{");
			for(String str: set3)
			{
				String value = hMap.get(str);
				System.out.println(str + "=" + value);
			}
			System.out.println("}");
		}
		System.out.println("-------------------------------------------");
		
		//HashMap 嵌套  HashMap 嵌套  ArrayList(三层嵌套)
		//创建对象并添加元素
		HashMap<String, HashMap<String, ArrayList<String>>> HM = new HashMap<String, HashMap<String,ArrayList<String>>>();
		HashMap<String, ArrayList<String>> hashmap3 = new HashMap<String, ArrayList<String>>();
		ArrayList<String> alist = new ArrayList<String>();
		alist.add("001");alist.add("002");alist.add("003");alist.add("004");
		ArrayList<String> alist2 = new ArrayList<String>();
		alist2.add("100");alist2.add("200");alist2.add("300");alist2.add("400");
		hashmap2.put("1", al);hashmap2.put("2", al2);
		hashmap3.put("a", alist);hashmap3.put("a2", alist2);
		HashMap<String, ArrayList<String>> hashmap4 = new HashMap<String, ArrayList<String>>();
		ArrayList<String> alist3 = new ArrayList<String>();
		alist3.add("005");alist3.add("006");alist3.add("007");alist3.add("008");
		ArrayList<String> alist4 = new ArrayList<String>();
		alist4.add("500");alist4.add("600");alist4.add("700");alist4.add("800");
		hashmap4.put("a", alist3);hashmap4.put("a2", alist4);
		HM.put("1", hashmap3);HM.put("2", hashmap4);	
		//遍历集合
		Set<String> set4 = HM.keySet();	
		for(String s: set4)
		{
			HashMap<String, ArrayList<String>> hashMap5 = HM.get(s);
			Set<String> set5 = hashMap5.keySet();
			System.out.println(s + ": ");
			for(String str: set5)
			{
				ArrayList<String> arrl = hashMap5.get(str);
				System.out.print("\t[");
				for(String string: arrl)
				{
					System.out.print("\t" + string);
				}
				System.out.println("\t]");
			}
		}
	} 
}