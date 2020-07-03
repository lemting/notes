package Java_常见对象.Math类和Random类;

/*
 * Math类的成员变量
 * 
 * 		public static final double E:  = e 自然对数的基数
 * 		public static final double PI: = π 圆周长与其直径的比率
 * 
 * Math类的成员方法
 * 
 * 		public static int abs(int a): = │a│ 返回一个int值(该方法可以返回int,long,float,double值)的绝对值
 * 		public static double ceil(double a): 向上取整 	ceil(3.2) = 4.0
 * 		public static double floor(double a): 向下取整	floor(3.6) = 3.0
 * 		public static int max(int a,int b): a,b两者的最大值
 * 		public static int min(int a,int b): a,b两者的最小值
 * 		public static double pow(double a,double b): = a^b a的b次幂
 * 		public static double random(): [0.0 , 1.0) 伪随机数
 * 		public static int round(float a): 四舍五入
 * 		public static int round(double a): 四舍五入
 * 		public static double sqrt(double): 正平方根 
 */

public class Math类的成员 {
	public static void main(String[] args) {
		int a = -5;
		//public static int abs(int a): = │a│ 返回一个int值(该方法可以返回int,long,float,double值)的绝对值
		System.out.println("abs: " + Math.abs(a));
		
		//public static double ceil(double a): 向上取整 	ceil(3.2lf) = 4.0lf
		System.out.println("ceil: " + Math.ceil(3.2));
		//public static double floor(double a): 向下取整	ceil(3.6) = 3.0
		System.out.println("floor: " + Math.floor(3.6));
		//public static int max(int a,int b): a,b两者的最大值
		System.out.println("max: " + Math.max(2, 3));
		System.out.println("max: " + Math.max(Math.max(1,2),3));
		//public static int min(int a,int b): a,b两者的最小值
		System.out.println("max: " + Math.min(2, 3));
		
		//public static double pow(double a,double b): = a^b a的b次幂
		System.out.println("pow: " + Math.pow(2,3));
		
		//public static double random(): [0.0 , 1.0) 伪随机数
		System.out.println("random: " + (int)(Math.random() * 100 + 1));
		
		//public static int round(float a): 四舍五入
		System.out.println("round: " + Math.round(3.4));
		//public static int round(double a): 四舍五入
		System.out.println("round: " + Math.round(3.6));
		
		//public static double sqrt(double): 正平方根 
		System.out.println("sqrt: " + Math.sqrt(2));
		
		for(int i = 0;i < 15;i++) {
			System.out.println(randomAll(2,20));
			System.out.println(randomAll(20,2));
		}
		
	}
	
	//获取任意范围内的随机数
	public static int randomAll(int min,int max) {
		if(min >= max) {
			int temp = max;
			max = min;
			min = temp;
		}
		return (int)(Math.random() * (max - min) + min);
	}
}
