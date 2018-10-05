package Java_常见对象.排序与查找;

public class 选择排序
{
	public static void main(String[] args)
	{
		int[] arr = {3,5,7,1,4,9,8,0,2,6};
		for(int i = 0;i < arr.length;i++)
			System.out.print(arr[i] + "\t");
		System.out.println();
		xuanze(arr);
		for(int i = 0;i < arr.length;i++)
			System.out.print(arr[i] + "\t");
	}

	public static int[] xuanze(int[] arr)
	{
		//排序(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++)
		{
			//每次排序比较(arr.length - i - 1)次
			for(int j = i + 1;j < arr.length;j++)
			{
				//若遇到比arr[i]小的,则互换
				if(arr[i] > arr[j])
				{
					int temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		
		return arr;
	}
}
