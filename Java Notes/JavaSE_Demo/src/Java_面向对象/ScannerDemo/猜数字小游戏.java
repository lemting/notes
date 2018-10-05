package Java_面向对象.ScannerDemo;

import java.util.Random;
import java.util.Scanner;

public class 猜数字小游戏
{
	private 猜数字小游戏(){}
	
	public static void start()
	{
		System.out.println("\t\t\t猜数字小游戏(1-100)\n\n");
		Random r = new Random();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int x,y = 0;
		y = r.nextInt(100) + 1;//y = (int)(Math.random() * 100) + 1;
		System.out.print("请输入你要猜的数: ");
		while(true)
		{
			if(sc.hasNextInt())
			{
				x = sc.nextInt();
				if(x > 100 || x < 0)
				{
					System.out.print("\n你输入的数据有误！\n请再次输入你要猜的数: ");
					continue;
				}
			}
			else
			{
				System.out.print("\n你输入的数据有误！\n请再次输入你要猜的数: ");
				sc.nextLine();
				continue;
			}
			
			if(x > y)
				System.out.print("\n你猜的数大了！\n请再次输入你要猜的数: ");
			else if(x < y)
				System.out.print("\n你猜的数小了！\n请再次输入你要猜的数: ");
			else
			{
				System.out.println("\n恭喜你！猜中了！");
				break;
			}
		}
	}
}