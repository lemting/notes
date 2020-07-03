package Java_常见对象.排序与查找;

import java.util.Arrays;

public class 选择排序 {
	public static void main(String[] args) {
		int[] arr = {3,5,7,1,4,9,8,0,2,6};
		System.out.println(Arrays.toString(arr));
		xuanze(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static int[] xuanze(int[] arr) {
		//排序(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++) {
			//记录最小值索引
			int min = i; 
			//每次排序比较(arr.length - i - 1)次
			for(int j = i + 1;j < arr.length;j++) {
				//若遇到比arr[min]小的,则记录该索引
				if(arr[min] > arr[j])
					min = j;
			}
			//若最小值不在当前位置, 则将最小值交换至当前位置
			if(min != i) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
		}
		
		return arr;
	}
}
