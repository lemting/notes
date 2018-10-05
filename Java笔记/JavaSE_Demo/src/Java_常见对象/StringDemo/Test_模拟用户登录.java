package Java_常见对象.StringDemo;

import java.util.Scanner;

import Java_常见对象.StringDemo.Test_猜数字小游戏;

public class Test_模拟用户登录
{
	public static void main(String[] args)
	{
		String ID_JiaMi = "Lemting";
		String Password_JiaMi = "Leminting";
		String ID, Password;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 3; i++)
		{
			System.out.print("请输入登录账号: ");
			ID = sc.nextLine();
			System.out.print("\n请输入账号密码: ");
			Password = sc.nextLine();
			if (ID.equals(ID_JiaMi) && Password.equals(Password_JiaMi))
			{
				System.out.println("登陆成功！\n");
				Test_猜数字小游戏.start();
				break;
			} 
			else
			{
				if(2 - i <= 0)
				{
					System.out.println("已冻结账户\n");
					break;
				}
				System.out.println("账号或密码错误！\n剩余 " + (2 - i) + "次机会！\n");
			}
		}
	}
}