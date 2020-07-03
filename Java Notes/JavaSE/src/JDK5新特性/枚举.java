package JDK5新特性;

/*
 *  枚举
 *  	指将变量的值一一列出来,变量的值只限于列举出来的值的范围内.eg: 一周有7天,一年有12月等
 *  	
 *  枚举类
 *  	单例模式是一个类只有一个实例
 *  	一个类有多个实例,但不是无线个数的实例,而是有限个数的实例,这才能是枚举类
 *  	
 *  	格式: 只有枚举项的枚举类
 *  		public enum 枚举类名{枚举项1,枚举项2,枚举项3,...;}
 *  
 *  枚举中的几个常见方法
 *  	public final int compareTo(E o): 将此枚举与指定的对象进行比较以进行订购
 *  	public final String name(): 返回此枚举常量的名称，与其枚举声明中声明的完全相同
 *  	public final int ordinal(): 返回此枚举常数的序数（其枚举声明中的位置，其中初始常数的序数为零）。 
 *  	public String toString(): 返回声明中包含的此枚举常量的名称
 *  	public static <T extends Enum<T>> T valueOf(Class<T> enumType,String name): 返回具有指定名称的指定枚举类型的枚举常量
 *  	values(): 此方法虽然在JDK文档中查找不到,但是每个枚举类都具有该方法,他遍历枚举类的所有枚举值非常方便
 *  
 *  枚举类的注意事项: 
 *  	A: 定义枚举类要用关键字enum
 *  	B: 所有的枚举类都是Enum的子类
 *  	C: 枚举类的第一行上必须是枚举项,最后一个枚举项后面的分号是可以省略的,但是如果枚举类有其他的东西,这个分号不能省略.建议不要省略
 *  	D: 枚举类可以有构造器,但是必须是private的.枚举项的用法比较特殊: 枚举();
 *  	E: 枚举类也可以有抽象方法,但是枚举项必须重写该方法
 *  	F: 枚举在switch语句中的使用
 */

//方向枚举类
enum FangXiang_2 {	
	front("前") {public void show(){System.out.println("前");}}
	,behnid("后") {public void show(){System.out.println("后");}}
	,left("左") {public void show(){System.out.println("左");}}
	,right("右") {public void show(){System.out.println("右");}};
	
	private String name;
	private FangXiang_2(String name){this.name = name;}
	public String getName(){return this.name;}
	public abstract void show();
}

//方向抽象枚举类
abstract class FangXiang {
	//创建几个实例
	public static final FangXiang front = new FangXiang("前")
	{public void show(){System.out.println("前");}};
	public static final FangXiang behind = new FangXiang("后")
	{public void show(){System.out.println("后");}};
	public static final FangXiang left = new FangXiang("左")
	{public void show(){System.out.println("左");}};
	public static final FangXiang right = new FangXiang("右")
	{public void show(){System.out.println("右");}};
	
	//构造私有
	private FangXiang(String name){this.name = name;}
	
	//加入成员变量
	private String name;
	
	//抽象方法
	public abstract void show();
	
	public String getName(){return name;}

	public void setName(String name){this.name = name;}

	@Override
	public String toString(){return "FangXiang [name=" + name + "]";}
	
}

public class 枚举 {
	public static void main(String[] args) {
		FangXiang f = FangXiang.front;
		System.out.println(f);
		f.show();
		System.out.println("---------------------");
		
		//enum枚举类
		FangXiang_2 f_2 = FangXiang_2.front;
		FangXiang_2 f_2_2 = FangXiang_2.behnid;
		System.out.println(f_2);
		f_2.show();
		//枚举在switch语句中的使用
		switch(f_2) {
			case front:
				System.out.println("你选择了前");	break;
			case behnid:
				System.out.println("你选择了后");	break;
			case left:
				System.out.println("你选择了左");	break;
			case right:
				System.out.println("你选择了右");	break;
		}
		System.out.println("-------------------");
		
		//public final int compareTo(E o): 将此枚举与指定的对象进行比较以进行订购
		System.out.println("compareTo: " + f_2.compareTo(f_2));
		System.out.println("compareTo: " + f_2.compareTo(f_2_2));
		System.out.println("-------------------");
		
		//public final String name(): 返回此枚举常量的名称，与其枚举声明中声明的完全相同
		System.out.println("name: " + f_2.name());
		System.out.println("-------------------");
		
		//public final int ordinal(): 返回此枚举常数的序数（其枚举声明中的位置，其中初始常数的序数为零）。 
		System.out.println("ordinal: " + f_2.ordinal());
		System.out.println("-------------------");
		
		//public static <T extends Enum<T>> T valueOf(Class<T> enumType,String name): 返回具有指定名称的指定枚举类型的枚举常量
		System.out.println("valueOf: " + Enum.valueOf(FangXiang_2.class, "left").toString());
		System.out.println("-------------------");
		
		//values方法
		FangXiang_2[] fs = FangXiang_2.values();
		for(FangXiang_2 fff: fs)
			System.out.println(fff + ": " + fff.getName());
	}
}