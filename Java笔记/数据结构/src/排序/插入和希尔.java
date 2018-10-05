package 排序;

import java.util.Arrays;

/*
 *  插入法排序
 *  	基本思想: 每一趟(eg: 第i趟,i = 0,1,...,n-2),都将第i+1个元素,插入该元素前的所有元素子序列中,即插入到0到i所有元素组成的子序列中
 *  	排序过程: 整个排序过程为n-1趟插入,先将序列中第1个记录看成是一个有序子序列,然后从第2个记录开始,逐个进行插入,直到整个序列有序
 */

public class 插入和希尔
{	
	//插入法排序
	public static void paixu_charu(int[] arr)
	{
		//插入(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++)
		{
			//要插入的位置
			int k = i + 1;
			//储存要插入的元素
			int temp = arr[k];
			for(int j = i;j >= 0 && arr[j] > temp;j--)
			{
				//元素后移
				arr[j + 1] = arr[j];
				//k: 需要插入的位置
				k = j;
			}
			//插入元素
			arr[k] = temp;
		}
	}
	
	//希尔(当 gap = 1 时,就是插入排序法)
	public static int[] paixu_xier(int[] arr)
	{
		int gap = arr.length;
		while(gap > 1)
		{
			gap = gap / 3 + 1;
			for(int i = gap;i < arr.length;i+=gap)
			{
				int k = i;
				int temp = arr[k];
				for(int j = i - gap;j >= 0 && arr[j] > temp;j-=gap)
				{
					arr[j + gap] = arr[j];
					k = j;
				}
				arr[k] = temp;
			}
		}
		return arr;
	}
	
	public static void main(String[] args)
	{
		int[] arr = {1,4,5,6,7,2,3,9,0,8};
		int[] arr2 = {1,4,5,6,7,2,3,9,0,8};
		paixu_charu(arr);
		paixu_xier(arr2);
		System.out.println("arr: " + Arrays.toString(arr));
		System.out.println("arr2: " + Arrays.toString(arr2));
	}
}
