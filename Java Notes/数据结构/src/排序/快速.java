package 排序;

import java.util.Arrays;

public class 快速
{
	// 快速排序
	public static void paixu_kuaisu(int[] arr, int left, int right)
	{
		// 递归终止条件
		if (left >= right)
			return;
		// 记录第一个不小于基准值arr[right]的索引
		int index = left;
		// 遍历从left到right-1
		for (int i = left; i < right; i++)
		{
			// 若遍历的当前值小于基准值
			if (arr[i] < arr[right])
			{
				// 交换arr[i]索引arr[index]的值
				int temp = arr[index];
				arr[index] = arr[i];
				arr[i] = temp;
				// 索引右移一位
				index++;
			}
		}
		// index左边的为小于arr[right]的值，右边为不小于
		// 交换后arr[index]为基准值
		int temp = arr[right];
		arr[right] = arr[index];
		arr[index] = temp;

		// 递归排序基准值左边的子集合
		paixu_kuaisu(arr, left, index - 1);
		// 递归排序基准值右边的子集合
		paixu_kuaisu(arr, index + 1, right);
	}

	public static void main(String[] args)
	{
		int[] arr = {4,8,6,9,7,2,3,1,1,0,10,54,-2};
		System.out.println("arr: " + Arrays.toString(arr));
		paixu_kuaisu(arr, 0, arr.length - 1);
		System.out.println("arr: " + Arrays.toString(arr));
	}
}
