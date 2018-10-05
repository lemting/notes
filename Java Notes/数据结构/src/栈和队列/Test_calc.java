package 栈和队列;

import java.util.Scanner;

/*
 * 	用栈实现简单计算器
 */

public class Test_calc
{
	//将中缀表达式字符串按照指定规则排布(其他字符  数字  其他字符)
	public static String zhengzeString(String str)
	{
		char[] chs = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < chs.length;i++)
		{
			if(chs[i] >= '0' && chs[i] <= '9')
			{
				sb.append(chs[i]);
				if(i < chs.length - 1 && (chs[i + 1] < '0' || chs[i + 1] > '9'))
					sb.append(" ");		
			}
			else
				sb.append(chs[i]).append(" ");
		}
		return sb.toString();
	}
	
	//中缀转后缀
	/*
	 *  遍历中缀表达式中的数字和符号
	 *  对于数字: 直接输出
	 *  对于符号:
	 *  	左括号: 进栈
	 *  	运算符号: 与栈顶符号进行优先级比较
	 *  		若栈顶符号优先级低: 此符号进栈(默认栈顶若是左括号,左括号优先级最低)
	 *  		若栈顶符号优先级不低: 将此符号弹出并输出,之后进栈
	 *  	右括号: 将栈顶符号弹出并输出,直到匹配到左括号
	 *  遍历结束: 将栈中的所有符号弹出并输出
	*/
	public static String zhongzhuanhou(String str)
	{
		String[] strs = zhengzeString(str).trim().split(" +");
		StringBuffer sb = new StringBuffer();
		
		MyLinkedStack<String> mls = new MyLinkedStack<String>();
		
		for(String s: strs)
		{
			if(s.equals(""))
				continue;
			if(s.matches("\\d+"))
				sb.append(s).append(" ");
			else if(s.equals("("))
				mls.push(s);
			else if(s.equals("/") || s.equals("*"))
			{
				if(!mls.empty() && (mls.peek().equals("/") || mls.peek().equals("*")))
					sb.append(mls.pop()).append(" ");
				mls.push(s);
			}
			else if(s.equals("+") || s.equals("-"))
			{
				if(!mls.empty() && !mls.peek().equals("("))
					sb.append(mls.pop()).append(" ");
				mls.push(s);			
			}
			else if(s.equals(")"))
			{
				String ch;
				while(!(ch = mls.pop()).equals("("))
					sb.append(ch).append(" ");
			}
		}
		while(!mls.empty())
			sb.append(mls.pop()).append(" ");
		return sb.toString();
	}
	
	/*
	 * 计算机后缀表达式的计算
	 * 
	 * 	遍历后缀表达式中的数字和符号
	 * 	
	 * 	对于数字: 进栈
	 * 	对于符号:
	 * 		从栈中弹出右操作数
	 * 		从栈中弹出左操作数
	 * 		根据符号进行运算
	 * 		将运算结果压入栈中
	 * 	遍历结束: 栈中唯一数字为计算结果
	 * 	
	 */
	public static int calc(String str)
	{
		String[] strs = str.trim().split(" ");
		MyLinkedStack<String> mls = new MyLinkedStack<String>();
		
		for(String s: strs)
		{
			if(s.matches("\\d+"))
				mls.push(s);
			else if(s.equals("/") || s.equals("*") || s.equals("+") || s.equals("-"))
			{
				int b = Integer.parseInt(mls.pop());
				int a = Integer.parseInt(mls.pop());
				switch (s)
				{
					case "+": mls.push(String.valueOf(a + b)); break;
					case "-": mls.push(String.valueOf(a - b)); break;
					case "/": mls.push(String.valueOf(a / b)); break;
					case "*": mls.push(String.valueOf(a * b)); break;
				}
			}
			
		}
		return Integer.parseInt(mls.pop());
	}
	
	public static void main(String[] args)
	{
		//   18 + ((8 / 2) * 3 + 7 * 2) * 2
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入算术表达式(整形): ");
		
		String string = scanner.nextLine();
		//System.out.println(zhongzhuanhou(string));
		System.out.println(string.trim() + " = " + calc(zhongzhuanhou(string)));
		scanner.close();
	}
}
