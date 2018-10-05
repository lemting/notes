package Java_IO流.IO流;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 将GBK编码文件转成UTF-8 	
 * 
 * 问题: 原java文件默认编码为GBK,后改为UTF-8.工作台保存后,默认编码更改为GBK后仍不能使原java文件的中文改回
 * 
 * 其一java文件部分乱码:
 * 		 ArrayList��ʹ��
 * 		�洢�ַ�����
 * 		ɾ������ظ���Ԫ��
 * 			��ʽ1: �����,����ÿһ��Ԫ�����Ԫ�غ����Ԫ�رȽ�,�������Ѿ��и�Ԫ����ɾ��
 * 			��ʽ2: �����µļ��϶���,����ԭ���ϵ�ÿ��Ԫ�ز������ж�: ���¼���û�и�Ԫ��,����ӵ��¼���
 *		ɾ������ظ����Զ������
 *
 * 改正后:
 * 		ArrayList的使用
 * 		存储字符串并遍历
 * 		删除集合中重复的元素
 * 			方式1: 遍历集合,并让每一个元素与该元素后面的元素比较,若集合已经有该元素则删除
 * 			方式2: 创建新的集合对象,遍历原集合的每个元素并对其判断: 若新集合没有该元素,则添加到新集合
 *		删除集合中重复的自定义对象
 */

public class Test_拯救由编码导致的乱码问题
{
	public static void main(String[] args) throws IOException
	{	
		String str = "D:\\Programming\\Java\\MyEclipse\\Workspaces\\Myeclipse 2017 CI\\Java_集合框架\\src\\CollectionDemo\\IteratorDemo.java";
		@SuppressWarnings("resource")
		InputStreamReader isr = new InputStreamReader(new FileInputStream(str),"GBK");
		
		char[] chs = new char[1024];
		int len = 0;
		while((len = isr.read(chs)) != -1)
		{
			System.out.print(new String(chs,0,len));
		}
	}
}
