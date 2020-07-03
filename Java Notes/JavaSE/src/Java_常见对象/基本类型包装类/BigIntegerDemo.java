package Java_常见对象.基本类型包装类;

import java.math.BigInteger;

/*
 * BigInteger 类可以让超过Integer范围内的数据进行计算
 * 
 * BigInteger 类的构造方法
 * 		public BigInteger(String val):将BigInteger的十进制字符串表示形式转换为BigInteger
 *
 * BigInteger 类的成员方法
 * 		public BigInteger add(BigInteger val):加
 * 		public BigInteger subtract(BigInteger val):减
 * 		public BigInteger multiply(BigInteger val):乘
 * 		public BigInteger divide(BigInteger val):除
 * 		public BigInteger[] divideAndRemainder(BigInteger val):返回两个BigInteger的数组,其中包含 (this / val)后跟 (this % val)
 */

public class BigIntegerDemo {
	public static void main(String[] args) {
		Integer i = 100;
		System.out.println("i: " + i);
		//System.out.println(Integer.MAX_VALUE);
		Integer ii = 2147483647;
		System.out.println("ii: " + ii);
		//Integer iii = new Integer(2147483648);//超出Integer的最大范围
		//public BigInteger(String val):将BigInteger的十进制字符串表示形式转换为BigInteger
		BigInteger iii = new BigInteger("2147483648");
		System.out.println("iii: " + iii);
		
		BigInteger bi = new BigInteger("100");
		BigInteger bi2 = new BigInteger("200");
		BigInteger bi3 = new BigInteger("23");
		//public BigInteger add(BigInteger val):加
		System.out.println("bi + bi2 = " + bi.add(bi2));
		
		//public BigInteger subtract(BigInteger val):减
		System.out.println("bi - bi2 = " + bi.subtract(bi2));
		
		//public BigInteger multiply(BigInteger val):乘
		System.out.println("bi * bi2 = " + bi.multiply(bi2));
		
		//public BigInteger divide(BigInteger val):除
		System.out.println("bi / bi3 = " + bi.divide(bi3));
		
		//public BigInteger[] divideAndRemainder(BigInteger val):返回两个BigInteger的数组,其中包含 (this / val)后跟 (this % val)
		BigInteger[] bis = bi.divideAndRemainder(bi3);
		System.out.println("bi / bi3 = " + bis[0]);
		System.out.println("bi % bi3 = " + bis[1]);
	}
}
