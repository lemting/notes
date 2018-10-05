package Java_IO流.IO流;

import java.io.FileReader;
import java.io.IOException;


public class Test_测试自建类模拟BufferedReader的readLine
{
	public static void main(String[] args) throws IOException
	{
		Test_自建类模拟BufferedReader的readLine sda = new Test_自建类模拟BufferedReader的readLine(new FileReader("D:\\Programming\\Java\\MyEclipse\\Workspaces\\MyEclipse 2017 CI\\Java_IO流\\src\\IO流\\Test_自建类模拟BufferedReader的readLine.java"));
		String s = null;
		while((s = sda.readLine()) != null)
			System.out.println(s);
		sda.close();
	}
}
