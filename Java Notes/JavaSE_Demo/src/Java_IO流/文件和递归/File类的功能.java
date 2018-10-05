package Java_IO流.文件和递归;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * File: 文件和目录路径名的抽象表示形式
 * 
 * 路径以盘符开始: 绝对路径
 * 路径不以盘符开始: 相对路径
 * 
 * File类的构造方法
 * 		public File(String pathname): 根据一个路径得到一个File对象
 * 		public File(String parent,String child): 根据一个目录和 一个子文件或目录 得到File对象
 * 		public File(File parent,String child): 根据一个父File对象和 一个子文件或目录 得到File对象
 * 
 * File类的创建功能
 * 		public boolean createNewFile(): 创建空文件,当且仅当具有该名称的文件尚不存在时
 * 		public boolean mkdir(): 创建文件夹 ,当且仅当该目录被创建时返回true
 * 		public boolean mkdirs(): 创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录
 * 		注意: 如果你创建文件或者文件夹忘了写盘符路径,那么,默认在项目路径下
 * 
 * File类的删除功能
 * 		public boolean delete(): 删除由此抽象路径名表示的文件或文件夹
 * 			注意: Java中的删除不走回收站
 * 				   若删除文件夹中有文件或文件夹,则不能删除,必须先删除里面的东西
 * 
 * File类的重命名功能
 * 		public boolean renameTo(File dest): 重命名由此抽象路径名表示的文件
 * 			如果路径名相同,就是改名;如果不同,就是改名并剪切
 * 
 * File类的判断功能
 * 		public boolean isDirectory(): 判断是否为文件夹
 * 		public boolean isFile(): 判断是否为普通文件
 * 		public boolean exists(): 判断文件或文件夹是否存在
 * 		public boolean canRead(): 判断是否可取
 * 		public boolean canWrite(): 判断是否可写
 * 		public boolean isHidden(): 判断是否为隐藏文件
 * 
 * File类的获取功能
 * 		public String getAbsolutePath(): 获取绝对路径
 * 		public String getPath(): 获取相对路径
 * 		public String getName(): 获取文件或目录的名称
 * 		public long length(): 获取文件的长度
 * 		public long lastModified(): 获取最后一次修改时间,毫秒值
 * 
 * File类的高级获取功能
 * 		public String[] list(): 获取指定目录下的所有文件或文件夹的名称数组
 * 		public File[] listFiles(): 获取指定目录下的所有文件或文件夹的File数组
 * 		public String[] list(FilenameFilter filter): 获取指定目录下的文件过滤器过滤后的所有文件或文件夹的名称数组
 * 		public File[] listFiles(FilenameFilter filter): 获取指定目录下的文件过滤器过滤后的所有文件或文件夹的File数组
 * 		
 */

public class File类的功能
{
	public static void main(String[] args) throws IOException
	{
		//public File(String pathname): 根据一个路径得到一个File对象
		//把D:\demo\a.txt封装成一个对象
		File file = new File("D:\\demo\\a.txt");
		System.out.println(file);
		
		//public File(String parent,String child): 根据一个目录和 一个子文件或目录 得到File对象
		File file2 = new File("D:\\demo","a.txt");
		System.out.println(file2);
		
		//public File(File parent,String child): 根据一个父File对象和 一个子文件或目录 得到File对象
		File file3 = new File("D:\\demo");
		File file4 = new File(file3,"a.txt");
		System.out.println(file3);
		System.out.println(file4);
		System.out.println("---------------------------------");
		//以上三种方式其实一样	
		
		//public boolean mkdir(): 创建文件夹 ,当且仅当该目录被创建时返回true
		File file5 = new File("E:\\demo");
		System.out.println("mkdir: E:\\demo  " + file5.mkdir());
		
		//public boolean createNewFile(): 创建空文件,当且仅当具有该名称的文件尚不存在时
		File file6 = new File("E:\\demo\\a.txt");
		System.out.println("createNewFile: E:\\demo\\a.txt  " + file6.createNewFile());
			
		//public boolean mkdirs(): 创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录
		File file7 = new File("E:\\demo\\text\\b.txt  ");//创建的是b.txt文件夹
		System.out.println("mkdirs: E:\\demo\\text\\b.txt  " + file7.mkdirs());
		System.out.println("------------------------------------------");
		
		//public boolean delete(): 删除由此抽象路径名表示的文件或文件夹
		File file8 = new File("E:\\demo\\text");
		System.out.println("delete: E:\\demo\\text\\b.txt  " + file7.delete());
		System.out.println("delete: E:\\demo\\a.txt  " + file6.delete());
		System.out.println("delete: E:\\demo\\text  " + file8.delete());
		System.out.println("delete: E:\\demo  " + file5.delete());
		System.out.println("------------------------------------------");
		
		//public boolean renameTo(File dest): 重命名由此抽象路径名表示的文件
		File file9 = new File("E:\\demo\\a.txt");
		new File("E:\\demo").mkdir();
		file9.createNewFile();
		File file10 = new File("E:\\demo\\b.txt");
		System.out.println("renameTo: E:\\b.txt  " + file9.renameTo(file10));
		System.out.println("-------------------------------------------");
		
		//public boolean isDirectory(): 判断是否为文件夹
		System.out.println("isDirectory: " + new File("E:\\demo").isDirectory());
		
		//public boolean isFile(): 判断是否为普通文件
		System.out.println("isFile: " + file10.isFile());
		
		//public boolean exists(): 判断文件或文件夹是否存在
		System.out.println("exists: " + file9.exists());
		
		//public boolean canRead(): 判断是否可读
		System.out.println("canRead: " + file10.canRead());
		
		//public boolean canWrite(): 判断是否可写
		System.out.println("canWrite: " + file10.canWrite());
		
		//public boolean isHidden(): 判断是否为隐藏文件
		System.out.println("isHidden: " + file10.isHidden());
		System.out.println("---------------------------------------------");
		
		//public String getAbsolutePath(): 获取绝对路径
		System.out.println("getAbsolutePath: " + file10.getAbsolutePath());
		
		//public String getPath(): 获取相对路径
		System.out.println("getPath: " + file10.getPath());
		
		//public String getName(): 获取文件或目录的名称
		System.out.println("getName: " + file10.getName());
		
		//public long length(): 获取文件的长度
		System.out.println("length: " + file10.length());
		
		//public long lastModified(): 获取最后一次修改时间,毫秒值
		System.out.println("lastModified: " + file10.lastModified());
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file10.lastModified())));
		System.out.println("--------------------------------------------");
		
		//public String[] list(): 获取指定目录下的所有文件或文件夹的名称数组
		String[] strArray = new File("D:\\programming").list();
		System.out.println("D:\\programming: ");
		for(String str: strArray)
			System.out.println("\t" + str);
		System.out.println("--------------------------------------------");
		
		//public File[] listFiles(): 获取指定目录下的所有文件或文件夹的File数组
		File[] files = new File("D:\\programming").listFiles();
		System.out.println("D:\\programming: ");
		for(File f: files)
			System.out.println("\t" + f.getName());

		
		new File("E:\\demo\\b.txt").delete();
		new File("E:\\demo").delete();
	}  
}
