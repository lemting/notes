package Java_常见对象.基本类型包装类;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * BigDecimal 类
 *		不可变的,任意精度的有符号十进制数.可以解决
 * 		float与double类型在运算时容易丢失精度,为了能精确的表示计算浮点数,Java提供了BigDecimal	
 * 
 * BigDecimal 类的构造方法
 * 		public BigDecimal(String val):将BigDecimal的字符串表示转换为BigDecimal 
 * 
 * RoundingMode 类的成员变量
 *		//HALF_UP: [0,0.5),[0.5,1);  HALF_DOWN: [0,0.5],(0.5,1)
 * 		public static final int HALF_UP: 四舍五入模式向“最近邻居”转弯，除非两个邻居都是等距的，在这种情况下是圆括弧的
 * 		public static final int HALF_DOWN: 四舍五入模式向“最近邻居”转弯，除非这两个邻居都是等距离的，在这种情况下，这是倒圆的
 * 		
 * BigDecimal 类的成员方法
 * 		public BigDecimal add(BigDecimal augend):加
 * 		public BigDecimal subtract(BigDecimal subtrahend):减
 * 		public BigDecimal multiply(BigDecimal multiplicand):乘
 * 		public BigDecimal divide(BigDecimal divisor):除
 * 		public BigDecimal divide(BigDecimal divisor,int scale,RoundingMode roundingMode):除    (商,小数点后几位小数,如何舍取)
 */

public class BigDecimalDemo {
	public static void main(String[] args) {
		//public BigDecimal(String val):将BigDecimal的字符串表示转换为BigDecimal 
		BigDecimal bd = new BigDecimal("0.09");
		BigDecimal bd2 = new BigDecimal("0.02");

		//public BigDecimal add(BigDecimal augend):加
		System.out.println("bd + bd2 = " + bd.add(bd2));
		
		//public BigDecimal subtract(BigDecimal subtrahend):减
		System.out.println("bd - bd2 = " + bd.subtract(bd2));
		
		//public BigDecimal multiply(BigDecimal multiplicand):乘
		System.out.println("bd * bd2 = " + bd.multiply(bd2));
		
		//public BigDecimal divide(BigDecimal divisor):除
		System.out.println("bd / bd2 = " + bd.divide(bd2));
		
		//public BigDecimal divide(BigDecimal divisor,int scale,int roundingMode):除    (商,几位小数,如何舍取)
		System.out.println("bd / bd2 = " + bd.divide(bd2,0,RoundingMode.HALF_UP));
		System.out.println("bd / bd2 = " + bd.divide(bd2,5,RoundingMode.HALF_DOWN));
	}
}
