package Java_IO流.其他流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 *  ObjectOutputStream: 序列化流,吧对象按照流 的方式存入文本文件或在网络中传输
 *  
 *  ObjectOutputStream的方法: 
 *  	public ObjectOutputStream(OutputStream out): 创建一个写入指定的输出流的序列化流
 *  	public final void writeObject(Object obj): 将指定的对象写入ObjectOutputStream
 *  	
 *  		Serializable接口: 类的序列化由实现java.io.Serializable接口的类启用
 *  					改接口没有任何方法,类似这类没有方法的接口,被称为标记接口
 *  		看到类实现了序列化接口,要想解决黄色警告线的时候,就可以自动产生一个序列化id的值,而且产生这个值以后,我们对类进行任何改动,读取以前的数据是没有问题的.
 *  
 *  ObjectInputStream: 反序列化流,把文本文件中的该对象数据或网络中的该对象数据还原成对象
 *  		
 *  ObjectInputStream的方法: 
 *  		public ObjectInputStream(InputStream in): 创建从指定的输入流读取的反序列化流
 * 			public final Object readObject(): 从ObjectInputStream读取一个对象
 */

//NotSerializableException: 未序列化异常

class Student implements Serializable {
	private static final long serialVersionUID = -2560026784921870649L;

	public Student(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	private int age;
	private String name;

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
		return "Student [age=" + age + ", name=" + name + "]";
	}

}

public class 序列化流和反序列化流 {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new File("E:\\a.txt").createNewFile();

		// 序列化数据其实就是把对象写入到文件中
		writer();

		// 还原对象并输出对象
		read();

		// 删除
		System.out.println("delete: " + new File("E:\\a.txt").delete());
	}

	public static void read() throws IOException, ClassNotFoundException {
		// 创建反序列化流对象
		// public ObjectInputStream(InputStream in): 创建从指定的输入流读取的反序列化流
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\a.txt"));

		// 还原对象
		Object obj = ois.readObject();

		// 释放资源
		ois.close();

		// 输出对象
		System.out.println(obj);
	}

	public static void writer() throws IOException {
		// 创建序列化流对象
		// public ObjectOutputStream(OutputStream out): 创建一个写入指定的输出流的序列化流
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\a.txt"));

		// 创建学生对象
		Student s = new Student(18, "Lemting");

		// public final void writeObject(Object obj): 将指定的对象写入ObjectOutputStream
		oos.writeObject(s);

		// 释放资源
		oos.close();
	}
}
