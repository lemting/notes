package Java_集合框架.SetDemo;

import java.util.TreeSet;

/*
 * TreeSet: 排序,唯一 
 * 		底层数据结构是二叉树结构
 * 		能够对元素按照某种规则进行排序(A:自然排序B:比较器排序)(取决于构造方法,无参构造默认自然排序)
 * 			
 * 			如果一个类的元素要想能够自然排序,就必须实现Comparable接口
 * 
 * TreeSet集合保证元素排序和唯一性的原理
 * 		唯一性: 是根据比较返回是否是0来决定的
 * 		排序: 
 * 			A:自然排序 (元素具备比较性)
 * 				让元素所属的类实现Comparable接口
 * 			B:比较器排序(集合具备比较性)
 * 				让集合的构造方法接收一个比较器接口Comparator的子类对象Comparator
 */

//按照姓名长度排序

//学生类
class Student implements Comparable<Student> {
	private int age;
	private String name;

	public Student(String name, int age) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Student s) {
		// 主要条件: 姓名的长度
		int num = this.name.length() - s.name.length();
		// 姓名的长度相同,不代表姓名的内容相同
		int num2 = (num == 0) ? this.name.compareTo(s.name) : num;
		// 姓名的长度和内容相同,不代表年龄相同,所以还得判断年龄
		int num3 = (num2 == 0) ? this.age - s.age : num2;
		return num3;
	}
}

public class TreeSetDemo {
	public static void main(String[] args) {
		// 创建学生对象
		Student s = new Student("s001", 27);
		Student s2 = new Student("02", 22);
		Student s3 = new Student("00003", 31);
		Student s4 = new Student("4", 18);
		Student s5 = new Student("z005", 32);
		Student s6 = new Student("0001", 30);
		Student s7 = new Student("4", 18);
		Student s8 = new Student("a005", 32);

		// 创建TreeSet对象
		TreeSet<Student> ts = new TreeSet<Student>();
		ts.add(s);
		ts.add(s2);
		ts.add(s3);
		ts.add(s4);
		ts.add(s5);
		ts.add(s6);
		ts.add(s7);
		ts.add(s8);

		// 遍历
		for (Student stu : ts)
			System.out.println(stu.getName() + " --- " + stu.getAge());

		System.out.println(s.getName().compareTo(s5.getName()));
		System.out.println(s.getName().compareTo(s8.getName()));
	}
}
