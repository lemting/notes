package Java_常见对象.PatternDemo_正则表达式;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 正则表达式的获取功能 
 * 		Pattern和Matcher类的使用
 * 			//基本使用顺序
 * 			Pattern p = Pattern.compile("a*b"): 把正则表达式编译成模式对象
 *			Matcher m = p.matcher("aaaaab"): 通过模式对象得到匹配器对象,这个时候需要的是被匹配的字符串
 *			boolean b = m.matches(): 判断字符串是否匹配正则表达式
 *
 *		Matcher的成员方法
 *			public boolean matches(): 判断字符串是否匹配正则表达式
 *			public boolean find(): 查找有没有匹配正则表达式的子字符串	
 *			public String group(): 返回与上一个匹配匹配的输入子序列	
 *
 */

public class 正则表达式的获取功能
{
	public static void main(String[] args)
	{
		//模式和匹配器的典型调用顺序
		//把正则表达式编译成模式对象
		Pattern p = Pattern.compile("a*b");	
		//通过模式对象得到匹配器对象,这个时候需要的是被匹配的字符串
		Matcher m = p.matcher("aaaaab");	
		//调用匹配器的功能 public boolean matches(): 判断字符串是否匹配正则表达式
		boolean b = m.matches();
		System.out.println(b);
		
		//这个是判断功能,但是如果就判断,这样就有点麻烦了,我们直接用字符串的方法做
		String s = "aaaaab";
		String regex = "a*b";
		boolean bb = s.matches(regex);
		System.out.println(bb);
		
		//获取下面字符串中有三个单词组成的单词
		//	da jia ting wo shuo,jijn tian wan shang yao xia yu,bu shang wan zi xi,gao xing bu?
		//定义字符串
		String str = "da jia ting wo shuo,jijn tian wan shang yao xia yu,bu shang wan zi xi,gao xing bu?";
		//把正则表达式编译成模式对象
		Pattern p2 = Pattern.compile("\\b\\w{3}\\b");
		//通过模式对象得到匹配器对象,这个时候需要的是被匹配的字符串
		Matcher m2 = p2.matcher(str);	
		
		//调用匹配器的功能
		////public boolean find():查找有没有匹配正则表达式的子字符串
		//boolean b2 = m2.find();
		//System.out.println(b2);
		
		//如何得到值呢？
		////public String group(): 返回与上一个匹配匹配的输入子序列
		//String ss = m2.group();
		//System.out.println(ss);
		
		//循环获取所有的匹配正则表达式的子字符串
		while(m2.find())
		{
			System.out.print(m2.group() + "\t");
		}
		System.out.println();
	}
}
