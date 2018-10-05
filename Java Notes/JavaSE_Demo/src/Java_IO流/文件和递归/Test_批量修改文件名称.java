package Java_IO流.文件和递归;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/*
 * 批量修改文件名称
 * 		原名称: 
 * 				三国演义_001_[评书网-今天很高兴,明天就IO]_桃园结义
 * 				三国演义_002_[评书网-今天很高兴,明天就IO]_十常侍之乱
 * 				三国演义_003_[评书网-今天很高兴,明天就IO]_三英战吕布
 * 				三国演义_004_[评书网-今天很高兴,明天就IO]_赤壁之战
 * 				三国演义_005_[评书网-今天很高兴,明天就IO]_月下追貂蝉
 * 		修改为: 
 * 				001_桃园结义
 * 			    002_十常侍之乱
 * 				....
 */

public class Test_批量修改文件名称
{
	public static void main(String[] args) throws IOException
	{
		//生成文件夹和文件
		new File("E:\\三国演义").mkdir();
		new File("E:\\三国演义\\三国演义_001_[评书网-今天很高兴,明天就IO]_桃园结义.txt").createNewFile();
		new File("E:\\三国演义\\三国演义_002_[评书网-今天很高兴,明天就IO]_十常侍之乱.txt").createNewFile();
		new File("E:\\三国演义\\三国演义_003_[评书网-今天很高兴,明天就IO]_三英战吕布.txt").createNewFile();
		new File("E:\\三国演义\\三国演义_004_[评书网-今天很高兴,明天就IO]_赤壁之战.txt").createNewFile();
		new File("E:\\三国演义\\三国演义_005_[评书网-今天很高兴,明天就IO]_月下追貂蝉.txt").createNewFile();
		
		//通过文件过滤器获取所需文件的File对象数组
		File[] files = new File("E:\\三国演义").listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				return new File(dir,name).isFile() && name.startsWith("三国演义") && name.endsWith(".txt");
			}
		});
		
		//遍历数组
		for(File file: files)
		{
			//通过正则表达式获取所需的名字数组
			String[] str = file.getName().split("_");
			//修改名称,输出true则代表修改成功
			System.out.println("renameTo: "+ file.renameTo(new File("E:\\三国演义\\" + str[1] + "_" + str[3])));
		}
		
		//修改完成后全部删除掉
		File[] files2 = new File("E:\\三国演义").listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				return new File(dir,name).isFile() && name.startsWith("三国演义") || name.startsWith("00") && name.endsWith(".txt");
			}
		});
		for(File file: files2)
			file.delete();
		new File("E:\\三国演义").delete();
	}	
}