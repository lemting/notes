package Java_IO流.其他流;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*  数据流
 * 		DataOutputStream: 数据输出流
 * 		DataInputStream: 数据输入流
 * 
 *  DataOutputStream: 数据输出流使应用程序以便携式方式将原始Java数据类型写入输出流。 然后应用程序可以使用数据输入流来读取数据
 *  DataOutputStream的方法: 
 *  	public final void writeBoolean(boolean v): 写入底层输出流作为1字节值
 *  	public final void writeByte(int v): 将byte写入基础输出流作为1字节值
 * 		public final void writeBytes(String s): 将字符串作为字节序列写入基础输出流
 * 		public final void writeChar(int v): 将char写入底层输出流作为2字节值，高字节优
 * 		public final void writeChars(String s): 将字符串写入底层输出流作为一系列字符
 * 		public final void writeDouble(double v): 双参数传递给转换long使用doubleToLongBits方法在类Double ，然后写入该long值基础输出流作为8字节的数量，高字节
 * 		public final void writeFloat(float v): 浮子参数的转换int使用floatToIntBits方法在类Float ，然后写入该int值基础输出流作为一个4字节的数量，高字节
 * 		public final void writeInt(int v): 将底层输出流写入int作为四字节，高位字节
 * 		public final void writeLong(long v): 写入底层输出流为8字节，高字节为首
 * 		public final void writeShort(int v): 将short写入底层输出流作为两个字节，高位字节
 * 
 *  DataInputStream: 数据输入流允许应用程序以独立于机器的方式从底层输入流读取原始Java数据类型。 应用程序使用数据输出流来写入稍后可以被数据输入流读取的数据 
 *  DataInputStream的方法: 
 *  		public final boolean readBoolean()
 *  		public final byte readByte()
 * 			public final short readShort()
 *			public final char readChar()
 *			public final float readFloat()
 *			public final int readInt()
 *			public final long readLong()
 *			public final String readUTF(): 读取一个字符串表示形式编码为UTF-8格式
 */

public class 数据流 {
	public static void main(String[] args) throws IOException {
		new File("E:\\demo").mkdir();

		// 创建数据输出流对象
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("E:\\demo\\a.txt"));
		// 写入数据
		dos.writeByte(10);
		dos.writeShort(100);
		dos.writeInt(1000);
		dos.writeLong(10000000);
		dos.writeFloat(12.34f);
		dos.writeDouble(12.34);
		dos.writeChar('A');
		dos.writeBoolean(true);

		// 释放资源
		dos.close();

		// 创建数据输入流对象
		DataInputStream dis = new DataInputStream(new FileInputStream("E:\\demo\\a.txt"));
		// 读取数据
		byte by = dis.readByte();
		short s = dis.readShort();
		int i = dis.readInt();
		long l = dis.readLong();
		float f = dis.readFloat();
		double d = dis.readDouble();
		char c = dis.readChar();
		boolean b = dis.readBoolean();

		// 释放资源
		dis.close();

		System.out.println(by);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(d);
		System.out.println(c);
		System.out.println(b);

		// 删除
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}
