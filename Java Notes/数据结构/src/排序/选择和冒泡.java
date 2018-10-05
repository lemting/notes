package 排序;

import java.util.Arrays;

/*
 * 	选择法排序
 * 		基本思想: 每一趟(eg: 第i趟,i = 0,1,...,n-2)在后面n-i个待排序的数据元素中选出关键字,最小的元素,作为有序元素序列的第i个元素
 * 
 * 		排序过程: 首先通过n-1次关键字比较,从n个记录中找出关键字最小的记录,将他与第一个记录交换
 * 			             再通过n-2次比较,从剩余的n-1个记录中找出关键字次小的记录,将他与第二个记录交换
 * 				  ...
 * 				   通过n-1趟排序后,排序结束
 * 
 * 	冒泡法排序
 * 		基本思想: 每一趟(eg: 第i趟,i = 0,1,...,n-2)都让0到n-1-i中最大的数下沉到第n-1-i位
 * 
 * 		排序过程: 首先第0个与第1个元素比较,若大则交换,然后第1个元素与第2个元素比较,...,直到第n-2个与第n-1个比较,若大则交换,让0到n-1中最大的数下沉到第n-1位
 * 			            再第0个与第1个元素比较,若大则交换,然后第1个元素与第2个元素比较,...,直到第n-3个与第n-2个比较,若大则交换,让0到n-2中最大的数下沉到第n-2位
 * 				  ...
 * 				   通过n-1趟排序后,排序结束
 */

public class 选择和冒泡
{
	//选择排序
	public static int[] paixu_xuanze(int[] arr)
	{
		//排序(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++)
		{
			//记录最小值的索引
			int min = i;
			//每次排序比较(arr.length - i - 1)次
			for(int j = i + 1;j < arr.length;j++)
			{
				//若遇到比arr[min]小的,则min记录较小值的索引
				if(arr[min] > arr[j])
					min = j;
			}
			//若最小的不是该位置的元素,则该位置的元素与最小值互换
			if(min != i)
			{
				int temp = arr[min];
				arr[min] = arr[i];
				arr[i] = temp;
			}
		}
		return arr;
	}
	
	//冒泡排序
	public static int[] paixu_maopao(int[] arr)
	{
		//排序(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++)
		{
			//记录数组是否排好序
			boolean flag = true;
			//每次排序比较(arr.length - i - 1)次
			for(int j = 0;j < arr.length - i - 1;j++)
			{
				//若遇到大数则下沉,小数上浮
				if(arr[j] > arr[j + 1])
				{
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					//若发生交换了,则数组还未排好序;
					flag = false;
				}
			}
			//若没有发生交换,则代表数组已经排好序了
			if(flag)
				break;
		}
		return arr;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {1,4,5,6,7,2,3,9,0,8};
		int[] arr2 = {1,4,5,6,7,2,3,9,0,8};
		paixu_xuanze(arr);
		paixu_maopao(arr2);
		System.out.println("arr: " + Arrays.toString(arr));
		System.out.println("arr2: " + Arrays.toString(arr2));
	}
}
