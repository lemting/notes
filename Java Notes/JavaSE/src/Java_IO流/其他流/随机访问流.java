package Java_IO流.其他流;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * RandomAccessFile
 * 		RandomAccessFile类不属于流,是Object类的子类
 * 		但他融合了InpuStream和OutpuStream的功能,支持对随机访问文件的读取和写入
 * 
 * RandomAccessFile的构造方法
 * 		public RandomAccessFile(File file,String mode): 创建一个随机访问文件流，以从File参数指定的文件中读取并可选地写入文件
 * 		public RandomAccessFile(String name,String mode): 创建随机访问文件流，以从中指定名称的文件读取，并可选择写入文件
 * 			mode: 只能是  "r"(只读),"rw"(读写),"rws","rwd", 否则抛出异常
 * 
 * RandomAccessFile的方法
 * 		public long getFilePointer(): 返回此文件中的当前偏移量
 * 		public void write(int b): 将指定的字节写入此文件
 * 		public final void writeInt(int v): 将int写入文件作为四个字节，高字节优先
 * 			与之相应功能的方法还有  writeBoolean(),writeByte(),writeBytes,writeChar(),writeChars(),writeDouble(),writeFloat(),writeLong(),writeShort()
 * 		public final String readUTF(): 从该文件写入字符串,编码为UTF-8
 * 		public int read(byte[] b): 从该文件读取最多b.length个字节的数据到字节数组
 * 		public final int readInt(): 从该文件读取一个带符号的32位整数
 * 			与之相应功能的方法还有  readBoolean(),readByte(),readBytes(),readChar(),readChars(),readDouble(),readFloat(),readLong(),readShort()
 * 		public final String readUTF(): 从该文件读取字符串,编码为UTF-8
 * 		public void seek(long pos): 设置文件指针偏移，从该文件的开头测量，发生下一次读取或写入
 */
public class 随机访问流 {
	public static void main(String[] args) throws IOException {
		new File("E:\\demo").mkdirs();
		new File("E:\\demo\\a.txt").createNewFile();

		// 创建随机访问流对象
		RandomAccessFile raf = new RandomAccessFile("E:\\demo\\a.txt", "rw");

		// 写入数据
		raf.writeBoolean(true);
		raf.writeByte(10);
		raf.writeChar('A');
		raf.writeDouble(12.34);
		raf.writeInt(10);
		raf.writeUTF("asdsadsa");
		// 释放资源
		raf.close();

		// 创建新的随机访问流对象
		RandomAccessFile raf2 = new RandomAccessFile("E:\\demo\\a.txt", "rw");
		// 读取数据
		System.out.println(raf2.readBoolean());
		System.out.println(raf2.readByte());
		System.out.println(raf2.readChar());
		System.out.println(raf2.readDouble());
		System.out.println(raf2.readInt());
		System.out.println(raf2.readUTF());
		// public void seek(long pos): 设置文件指针偏移，从该文件的开头测量，发生下一次读取或写入
		raf2.seek(0);
		System.out.println(raf2.readBoolean());
		System.out.println(raf2.readByte());
		System.out.println(raf2.readChar());
		System.out.println(raf2.readDouble());
		System.out.println(raf2.readInt());
		System.out.println(raf2.readUTF());

		// 释放资源
		raf2.close();

		// 删除
		System.out.println("delete: " + new File("E:\\demo\\a.txt").delete());
		System.out.println("delete: " + new File("E:\\demo").delete());
	}
}
