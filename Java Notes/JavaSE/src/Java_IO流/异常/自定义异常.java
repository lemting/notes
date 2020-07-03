package Java_IO流.异常;

import java.util.Scanner;

/*
 * java不可能对所有是=的情况考虑到,所以,在实际开发中,我们可能需要自己定义异常
 * 
 * 自定义异常
 * 		A: 继承自 Exception
 * 		A: 继承自 RuntimeException
 */

public class 自定义异常 {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入分数: ");
		int score = sc.nextInt();

		try {
			check(score);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 编译期异常需要抛出异常
	public static void check(int score) throws MyException {
		if (score > 100 || score < 0) {
			throw new MyException("分数必须在0-100之间");
		} else {
			System.out.println("分数没有问题");
		}
	}

	// 运行期异常不需要抛出异常
	public static void check2(int score) {
		if (score > 100 || score < 0) {
			throw new MyException2();
		} else {
			System.out.println("分数没有问题");
		}
	}
}

class MyException extends Exception {
	private static final long serialVersionUID = 6662847869056047153L;

	public MyException() {
	}

	public MyException(String message) {
		super(message);
	}
}

class MyException2 extends RuntimeException {
	private static final long serialVersionUID = 6210367510378319770L;
}
