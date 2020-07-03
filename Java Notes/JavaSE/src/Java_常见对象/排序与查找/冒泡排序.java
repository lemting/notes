package Java_常见对象.排序与查找;

import java.util.Arrays;

/*
 *  3	2	4	1
 * 
 *  3 -	2
 *  2	3 -	4	
 *  2	3	4 - 1
 *  2	3	1	4
 *  
 *  2 -	3	
 *  2	3 -	1
 *  2	1	3	4
 *  
 *  2 -	1		
 *  1	2	3	4
 */

public class 冒泡排序 {
	public static void main(String[] args) {
		int[] arr = {3,5,7,1,4,9,8,0,2,6};

		System.out.println(Arrays.toString(arr));
		maopao(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static int[] maopao(int[] arr) {
		//排序(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++) {
			//每次排序比较(arr.length - i - 1)次
			for(int j = 0;j < arr.length - i - 1;j++) {
				//若遇到大数则下沉,小数上浮
				if(arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
	
	public static char[] maopao(char[] arr) {
		//排序(arr.length - 1)次
		for(int i = 0;i < arr.length - 1;i++) {
			//每次排序比较(arr.length - i - 1)次
			for(int j = 0;j < arr.length - i - 1;j++) {
				//若遇到大数则下沉,小数上浮
				if(arr[j] > arr[j + 1]) {
					char temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
}
