package Java_常见对象.PatternDemo_正则表达式;

import java.util.Scanner;

/*
 * Pattren类是正则表达式的编译表示形式
 * 
 * 正则表达式:符合一定规则的字符串
 *		是指一个用来描述或者匹配一系列符合某个句法规则的字符串的单个字符串,	其实就是一种规则,有自己特殊的应用
 *	
 *	
 *	正则表达式的规则
 *		"a[1-8](\\d{5}[a-z]{6,9})+" (不能用空格隔开) //错误: "a [1-8] (\\d {5} [a-z] {6,9})+"
 *		
 *		A: 字符	
 *			x     		字符x.(任意字符代表字符本身) eg: 'a' 表示字符 a
 *			\\  		反斜线字符
 *			\n			换行符
 *			\r			回车符
 *		B: 字符类
 *			[abc] 		a,b或c (简单类)
 *			[^abc]		任何字符,除了a,b或c (否定)
 *			[a-zA-Z]    a到z,或A到Z,两头字母包括在内 (范围)
 *			[0-9]		0到9的字符都包括 (范围)
 *		
 *		C: 自定义字符类
 *			.			任意字符 (\. 表示字符'.')
 *			\d			数字 [0-9]
 *			\D			非数字 [^0-9]
 *			\s			空白字符 [\t\n\x0B\f\r]
 *			\S			非空白字符 [^\s] 
 *			\w 			单词字符 [a-zA-Z_0-9]
 *			\W  		非单词字符 [^\w]
 *		
 *		D: 边界匹配器
 *			^			行的开头
 *			$			行的结尾
 *			\b			单词边界 (就是不是单词字符的地方) eg: hello world?haha;xixi  边界: ' ' '?' ';'
 *
 *		E: Greedy 数量词
 *			X?			X,一次或一次也没有 (== 0 或 == 1)
 *			X*			X,零次或多次 (>= 0)
 *			X+ 			X,一次或多次 (>= 1)
 *			X{n}		X,恰好n次 (== n)
 *			X{n,}		X,至少n次 (>= n)
 *			X{n,m}		X,至少n次,至多m次 (>= n 且 <= m)
 */

public class 正则表达式的规则
{
	public static void main(String[] args)
	{
		/*校验qq号:
		 *		1: 要求必须5-15位数字
		 *		2: 0不能让开头*/
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		System.out.print("请输入QQ号: ");
		String qq = sc1.nextLine();
		
		System.out.println(checkQQ(qq));	
	}

	public static boolean checkQQ(String qq)
	{
		//String regex = "[1-9][0-9]{4,14}";
		////public boolean matches(String regex):是否匹配给定的正则表达式
		//boolean flag = qq.matches(regex);
		//return flag;
		
		return qq.matches("[1-9]\\d{4,14}");
	}
}
