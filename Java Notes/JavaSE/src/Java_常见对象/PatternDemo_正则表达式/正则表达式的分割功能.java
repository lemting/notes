package Java_常见对象.PatternDemo_正则表达式;

import java.util.Arrays;

/*
 * 正则表达式的分割功能
 * 		
 * 		String类的public String[] split(String regex): 根据给定正则表达式的匹配拆分此字符串
 */

public class 正则表达式的分割功能 {
	public static void main(String[] args) {
		//定义年龄搜索范围
		String ages = "18-24"; 
		//定义规则
		String regexs = "-";
		//调用方法
		String[] arr = ages.split(regexs);
		
		System.out.println(Arrays.toString(arr));
		
		//定义字符串
		String a = "5 1 3 2 4";
		//获取分割后的字符串数组
		String[] arr2 = a.split(" ");
		//定义int类型数组
		int[] irr = new int[arr2.length];
		//获取字符串数组中的数据
		for(int i = 0;i < arr2.length;i++)
			irr[i] = Integer.parseInt(arr2[i]);
		//排序
		Arrays.sort(irr);
		//输出
		System.out.println(Arrays.toString(irr));
	}
}
