package Java_常见对象.日期时间类;

import java.util.Calendar;
import java.util.Scanner;

/*
 * 分析:
 * 		A: 键盘录入年份
 * 		B: 创建日历对象
 * 		C: 对12个月份进行遍历
 * 			a: 设置时间为当前遍历月1日
 * 			b: 输出日历格式
 * 			c: 该月1日是星期几,就输出几个 \t
 * 			d: 调用hasDay()方法获取该月份天数
 * 			e: 对该月遍历
 * 				1: 输出日期
 * 				2: 若满7天则换行
 */

public class Test_输出日历 {
	public static void main(String[] args) {
		//键盘录入年份
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入年份: ");
		int year = sc.nextInt();
		//创建日历对象
		Calendar cal = Calendar.getInstance();	
		//对12个月份进行遍历
		for(int month = 0;month < 12;month++) {
			//设置时间为(month + 1)月1日
			cal.set(year, month, 01);		
			//输出日历格式
			System.out.println("\n\n\t\t\t" + (month + 1) + "月\n");
			System.out.println("日\t一\t二\t三\t四\t五\t六");	
			//该月1日是星期几,就输出几个 \t
			for(int j = 1;j < cal.get(Calendar.DAY_OF_WEEK);j++)
				System.out.print("\t");
			//调用hasDay()方法获取该月份天数
			int monthHas = hasDay(year,month);
			//对该月遍历
			for(int k = 1;k <= monthHas;k++) {
				//输出日期
				System.out.print(k + "\t");
				//若满7天则换行
				if((k + cal.get(Calendar.DAY_OF_WEEK) - 1)%7 == 0)
					System.out.println();
			}	
		}	
		sc.close();
	}
	//获取该月份天数
	public static int hasDay(Integer year,int month) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.set(year, month + 1, 1);
		rightNow.add(Calendar.DATE,-1);
		return rightNow.get(Calendar.DAY_OF_MONTH);
	}
}