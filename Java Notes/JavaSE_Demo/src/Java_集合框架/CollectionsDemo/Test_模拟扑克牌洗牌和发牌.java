package Java_集合框架.CollectionsDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 模拟扑克牌洗牌和发牌
 * 		
 * 		方式1: 
 * 			A: 创建牌类(实现Comparable接口,重写compareTo()方法,成员变量: 牌面值,牌大小序号)
 * 			B: 创建List集合对象
 * 			C: 创建每张牌的牌对象
 * 			D: 将这些牌对象添加到集合中
 * 			E: 通过Collections类中的shuffle()方法实现洗牌
 * 			F: 创建4个List集合对象
 * 			G: 发牌
 * 			H: 对四个集合对象排序
 * 			I: 遍历4个集合对象
 * 
 * 		方式2: (更为方便,简单)
 * 			A: 创建 Map<String> 集合对象
 * 			B: 添加每一种牌到集合中,以牌的大小作为键,以牌的牌面值作为值
 * 			C: 创建 List<Integer> 集合对象
 * 			D: 将1-54这54个数储存n次(即n副牌)到 List 集合中
 * 			E: 通过Collections类中的shuffle()方法把List集合元素打乱,实现洗牌
 * 			F: 创建4个List<Integer>集合对象
 * 			G: 发牌
 * 			H: 通过Collections类中的sort()方法对对四个List对象排序
 * 			I: 遍历4个List集合对象,并通过每一个值找到Map中对应的牌
 */	

//牌类
class Pokes implements Comparable<Pokes>
{
	private String value;
	private int xuhao;
	public Pokes(){}
	public Pokes(String value, int xuhao)
	{
		super();
		this.value = value;
		this.xuhao = xuhao;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + xuhao;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokes other = (Pokes) obj;
		if (value == null)
		{
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (xuhao != other.xuhao)
			return false;
		return true;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public int getXuhao()
	{
		return xuhao;
	}
	public void setXuhao(int xuaho)
	{
		this.xuhao = xuaho;
	}
	@Override
	public int compareTo(Pokes o)
	{	
		return (o.xuhao - this.xuhao) == 0 ? -1 : (o.xuhao - this.xuhao);
	}
}

public class Test_模拟扑克牌洗牌和发牌
{
	public static void main(String[] args)
	{
		//方式1:
		//创建List集合对象
		List<Pokes> list = new ArrayList<Pokes>();
		//创建每张牌的牌对象
		Pokes pai1 = new Pokes("♠3",1);   Pokes pai2 = new Pokes("♣3",2);   Pokes pai3 = new Pokes("♥3",3);    Pokes pai4 = new Pokes("♦3",4);  
		Pokes pai5 = new Pokes("♠4",5);   Pokes pai6 = new Pokes("♣4",6);   Pokes pai7 = new Pokes("♥4",7);    Pokes pai8 = new Pokes("♦4",8);  
		Pokes pai9 = new Pokes("♠5",9);   Pokes pai10 = new Pokes("♣5",10) ;Pokes pai11 = new Pokes("♥5",11);  Pokes pai12 = new Pokes("♦5",12);  
		Pokes pai13 = new Pokes("♠6",13); Pokes pai14 = new Pokes("♣6",14); Pokes pai15 = new Pokes("♥6",15);  Pokes pai16 = new Pokes("♦6",16); 
		Pokes pai17 = new Pokes("♠7",17); Pokes pai18 = new Pokes("♣7",18); Pokes pai19 = new Pokes("♥7",19);  Pokes pai20 = new Pokes("♦7",20);  
		Pokes pai21 = new Pokes("♠8",21); Pokes pai22 = new Pokes("♣8",22); Pokes pai23 = new Pokes("♥8",23);  Pokes pai24 = new Pokes("♦8",24); 
		Pokes pai25 = new Pokes("♠9",25); Pokes pai26 = new Pokes("♣9",26); Pokes pai27 = new Pokes("♥9",27);  Pokes pai28 = new Pokes("♦9",28);  
		Pokes pai29 = new Pokes("♠10",29);Pokes pai30 = new Pokes("♣10",30);Pokes pai31 = new Pokes("♥10",31); Pokes pai32 = new Pokes("♦10",32);  
		Pokes pai33 = new Pokes("♠J",33); Pokes pai34 = new Pokes("♣J",34); Pokes pai35 = new Pokes("♥J",35);  Pokes pai36 = new Pokes("♦J",36);  
		Pokes pai37 = new Pokes("♠Q",37); Pokes pai38 = new Pokes("♣Q",38); Pokes pai39 = new Pokes("♥Q",39);  Pokes pai40 = new Pokes("♦Q",40); 
		Pokes pai41 = new Pokes("♠K",41); Pokes pai42 = new Pokes("♦K",42); Pokes pai43 = new Pokes("♣K",43);  Pokes pai44 = new Pokes("♥K",44); 
		Pokes pai45 = new Pokes("♠A",45); Pokes pai46 = new Pokes("♣A",46); Pokes pai47 = new Pokes("♥A",47);  Pokes pai48 = new Pokes("♦A",48);  
		Pokes pai49 = new Pokes("♠2",49); Pokes pai50 = new Pokes("♣2",50); Pokes pai51 = new Pokes("♥2",51);  Pokes pai52 = new Pokes("♦2",52); 
		Pokes pai53 = new Pokes("♔",53); Pokes pai54 = new Pokes("♚",54);
		//将这些牌对象添加到集合中
		list.add(pai1); list.add(pai2); list.add(pai3); list.add(pai4);
		list.add(pai5); list.add(pai6); list.add(pai7); list.add(pai8);
		list.add(pai9); list.add(pai10);list.add(pai11);list.add(pai12);
		list.add(pai13);list.add(pai14);list.add(pai15);list.add(pai16);
		list.add(pai17);list.add(pai18);list.add(pai19);list.add(pai20);
		list.add(pai21);list.add(pai22);list.add(pai23);list.add(pai24);
		list.add(pai25);list.add(pai26);list.add(pai27);list.add(pai28);
		list.add(pai29);list.add(pai30);list.add(pai31);list.add(pai32);
		list.add(pai33);list.add(pai34);list.add(pai35);list.add(pai36);
		list.add(pai37);list.add(pai38);list.add(pai39);list.add(pai40);
		list.add(pai41);list.add(pai42);list.add(pai43);list.add(pai44);
		list.add(pai45);list.add(pai46);list.add(pai47);list.add(pai48);
		list.add(pai49);list.add(pai50);list.add(pai51);list.add(pai52);
		list.add(pai53);list.add(pai54);
		list.add(pai1); list.add(pai2); list.add(pai3); list.add(pai4);
		list.add(pai5); list.add(pai6); list.add(pai7); list.add(pai8);
		list.add(pai9); list.add(pai10);list.add(pai11);list.add(pai12);
		list.add(pai13);list.add(pai14);list.add(pai15);list.add(pai16);
		list.add(pai17);list.add(pai18);list.add(pai19);list.add(pai20);
		list.add(pai21);list.add(pai22);list.add(pai23);list.add(pai24);
		list.add(pai25);list.add(pai26);list.add(pai27);list.add(pai28);
		list.add(pai29);list.add(pai30);list.add(pai31);list.add(pai32);
		list.add(pai33);list.add(pai34);list.add(pai35);list.add(pai36);
		list.add(pai37);list.add(pai38);list.add(pai39);list.add(pai40);
		list.add(pai41);list.add(pai42);list.add(pai43);list.add(pai44);
		list.add(pai45);list.add(pai46);list.add(pai47);list.add(pai48);
		list.add(pai49);list.add(pai50);list.add(pai51);list.add(pai52);
		list.add(pai53);list.add(pai54);
		
		//通过Collections类中的shuffle()方法实现洗牌
		Collections.shuffle(list);
		
		//
		List<Pokes> list1 = new ArrayList<Pokes>();
		List<Pokes> list2 = new ArrayList<Pokes>();
		List<Pokes> list3 = new ArrayList<Pokes>();
		List<Pokes> list4 = new ArrayList<Pokes>();
		//发牌
		for(int i = 0;i < list.size();i++)
		{	
			switch(i%4)
			{
				case 1:	list1.add(list.get(i));		break;
				case 2: list2.add(list.get(i));		break;
				case 3: list3.add(list.get(i));		break;	
				case 0: list4.add(list.get(i));		break;
			}
		}
		//对四个集合对象排序排序
		Collections.sort(list1);Collections.sort(list2);Collections.sort(list3);Collections.sort(list4);
		
		//遍历4个集合对象
		for(int i = 0;i < list1.size();i++)
		{
			if(i == 0)
				System.out.print("[");
			Pokes p = list1.get(i);
			System.out.print(p.getValue());
			if(i != list1.size() - 1)
				System.out.print(", ");
			else 
				System.out.println("]");
		}
		
		for(int i = 0;i < list2.size();i++)
		{
			if(i == 0)
				System.out.print("[");
			Pokes p = list2.get(i);
			System.out.print(p.getValue());
			if(i != list2.size() - 1)
				System.out.print(", ");
			else 
				System.out.println("]");
		}
		for(int i = 0;i < list3.size();i++)
		{
			if(i == 0)
				System.out.print("[");
			Pokes p = list3.get(i);
			System.out.print(p.getValue());
			if(i != list3.size() - 1)
				System.out.print(", ");
			else 
				System.out.println("]");
		}
		for(int i = 0;i < list4.size();i++)
		{
			if(i == 0)
				System.out.print("[");
			Pokes p = list4.get(i);
			System.out.print(p.getValue());
			if(i != list4.size() - 1)
				System.out.print(", ");
			else 
				System.out.println("]");
		}
		System.out.println("-----------------------------------------------------");

		
		//方式2:
		//创建Map<String>集合对象
		Map<Integer, String> map = new HashMap<Integer, String>();
		//添加每一种牌到集合中,以牌的大小作为键,以牌的牌面值作为值
		map.put(1, "♠3");  map.put(2, "♣3");  map.put(3, "♥3");  map.put(4, "♦3");
		map.put(5, "♠4");  map.put(6, "♣4");  map.put(7, "♥4");  map.put(8, "♦4");
		map.put(9, "♠5");  map.put(10, "♣5"); map.put(11, "♥5"); map.put(12, "♦5");
		map.put(13, "♠6"); map.put(14, "♣6"); map.put(15, "♥6"); map.put(16, "♦6");
		map.put(17, "♠7"); map.put(18, "♣7"); map.put(19, "♥7"); map.put(20, "♦7");
		map.put(21, "♠8"); map.put(22, "♣8"); map.put(23, "♥8"); map.put(24, "♦8");
		map.put(25, "♠9"); map.put(26, "♣9"); map.put(27, "♥9"); map.put(28, "♦9");
		map.put(29, "♠10");map.put(30, "♣10");map.put(31, "♥10");map.put(32, "♦10");
		map.put(33, "♠J"); map.put(34, "♣J"); map.put(35, "♥J"); map.put(36, "♦J");
		map.put(37, "♠Q"); map.put(38, "♣Q"); map.put(39, "♥Q"); map.put(40, "♦Q");
		map.put(41, "♠K"); map.put(42, "♣K"); map.put(43, "♥K"); map.put(44, "♦K");
		map.put(45, "♠A"); map.put(46, "♣A"); map.put(47, "♥A"); map.put(48, "♦A");
		map.put(49, "♠2"); map.put(50, "♣2"); map.put(51, "♥2"); map.put(52, "♦2");
		map.put(53, "♔"); map.put(54, "♚");
		//创建List<Integer>集合对象
		List<Integer> list5 = new ArrayList<Integer>();
		//将1-54这54个数储存n次(即n副牌)到List集合中
		for(int i = 1;i <= map.size();i++)
		{
			list5.add(i);
			list5.add(i);
		}
		//通过Collections类中的shuffle()方法把List集合元素打乱,实现洗牌
		Collections.shuffle(list5);	
		//创建4个List<Integer>集合对象
		List<Integer> list6 = new ArrayList<Integer>();
		List<Integer> list7 = new ArrayList<Integer>();
		List<Integer> list8 = new ArrayList<Integer>();
		List<Integer> list9 = new ArrayList<Integer>();
		//发牌
		for(int i = 0;i < list5.size();i++)
		{	
			switch(i%4)
			{
				case 1:	list6.add(list5.get(i));		break;
				case 2: list7.add(list5.get(i));		break;
				case 3: list8.add(list5.get(i));		break;	
				case 0: list9.add(list5.get(i));		break;
			}
		}
		//通过Collections类中的sort()方法对对四个List对象排序
		Collections.sort(list6);Collections.sort(list7);Collections.sort(list8);Collections.sort(list9);
		//遍历4个List集合对象,并通过每一个值找到Map中对应的牌
		for(int i = list6.size() - 1;i >= 0;i--)
		{
			if(i == list6.size() - 1)
				System.out.print("[");
			System.out.print(map.get(list6.get(i)));
			if(i != 0)
				System.out.print(", ");
			else 
				System.out.println("]");
		}		
		for(int i = list7.size() - 1;i >= 0;i--)
		{
			if(i == list7.size() - 1)
				System.out.print("[");
			System.out.print(map.get(list7.get(i)));
			if(i != 0)
				System.out.print(", ");
			else 
				System.out.println("]");
		}	
		for(int i = list8.size() - 1;i >= 0;i--)
		{
			if(i == list8.size() - 1)
				System.out.print("[");
			System.out.print(map.get(list8.get(i)));
			if(i != 0)
				System.out.print(", ");
			else 
				System.out.println("]");
		}	
		for(int i = list9.size() - 1;i >= 0;i--)
		{
			if(i == list9.size() - 1)
				System.out.print("[");
			System.out.print(map.get(list9.get(i)));
			if(i != 0)
				System.out.print(", ");
			else 
				System.out.println("]");
		}	
		
	}
}
