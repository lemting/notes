package Java_集合框架.对象数组和常见数据结构;

/*
 *   用数组存储5个学生对象
 */

import java.util.Scanner;

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

public class Test_ObjectArrayDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Student[] sd = new Student[5];
		int age;
		String name;
		for (int i = 0; i < sd.length; i++) {
			System.out.print("请输入姓名: ");
			name = sc.nextLine();
			System.out.print("请输入年龄: ");
			age = Integer.parseInt(sc.nextLine());
			sd[i] = new Student(name, age);
		}
		for (int i = 0; i < sd.length; i++)
			System.out.println(sd[i].getName() + " --- " + sd[i].getAge());
		sc.close();
	}
}
