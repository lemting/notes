package Java_常见对象.排序与查找;

/*
 * 查找:
 * 		基本查找: 数组元素无序(从头到尾)
 * 		二分查找: 数组元素有序(首尾同时)
 */

public class 二分法查找
{
	public static void main(String[] args)
	{
		int[] arr = {11,22,33,44,55,66,77};
		System.out.println("55 -- arr[" + erfen(arr,55) + "]");
		System.out.println("54 -- arr[" + erfen(arr,54) + "]");
	}

	public static int erfen(int[] arr,int num)
	{
		int min = 0;
		int max = arr.length - 1;
		while(true)
		{
			int mid = (min + max)/2;
			if(num > arr[mid])
				min = mid + 1;
			else if(num < arr[mid])
				max = mid - 1;
			else 
				return mid;
			if(max < min)
				break;
		}
		return -1;
	}
	
	
}
