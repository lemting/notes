package Java_集合框架.ListDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 练习: 用集合储存5个学生对象,并把学生对象进行遍历
 * 		
 * 分析: 
 * 
 * 		A:创建学生类
 * 		B:创建集合对象
 * 		C:创建学生对象
 * 		D:把学生对象添加到集合
 * 		F:遍历集合
 */

//创建学生类
class Student {
	public Student() {
	}

	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

}

public class Test_List储存自定义对象并遍历 {
	public static void main(String[] args) {
		// 创建集合对象
		List<Student> c = new ArrayList<Student>();

		// 创建学生对象
		Student[] stu = new Student[5];
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < stu.length; i++) {
			System.out.print("请输入姓名: ");
			String name = sc.nextLine();
			System.out.print("请输入年龄: ");
			int age = Integer.parseInt(sc.nextLine());
			stu[i] = new Student(name, age);
		}

		// 把学生对象添加到集合
		for (int i = 0; i < stu.length; i++)
			c.add(stu[i]);

		// 遍历集合
		for (int i = 0; i < c.size(); i++) {
			Student s = (Student) c.get(i);
			System.out.println(s);
		}
	}
}