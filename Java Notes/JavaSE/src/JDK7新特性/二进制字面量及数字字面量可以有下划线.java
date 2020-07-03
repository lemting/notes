package JDK7新特性;

/*	
 * 	二进制字面量
 * 
 * 		JDK7开始,终于可以使用二进制来表示整数(byte,short,int和long).
 * 		使用二进制字面量的好处是: 可以使代码更容易被理解.
 * 		语法非常简单,只要在二进制数值前面加 0b 或 0B	eg: int x = 0b110110
 * 
 * 	数字字面量可以有下划线
 * 		为了增强对数值的阅读性,如我们经常把数据用逗号分隔开一样.JDK7提供了_对数据分隔
 * 		eg: int x = 100_1000;
 * 		
 * 		注意事项: 
 * 			A: 不能出现在进制标识和数值之间
 * 			B: 不能出现在数值开头和结尾
 * 			C: 不能出现在小数旁边
 */

public class 二进制字面量及数字字面量可以有下划线 {
	public static void main(String[] args) {
		int x = 0b110110;
		System.out.println("x: " + x);
		
		int y = 100_1000;
		System.out.println("y: " + y);
	}
}
