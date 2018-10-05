package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/*
  	DOM4J方式解析: (需要导入dom4j的jar包)(具体使用方法于DOM4J官网查看)
		1: 创建SAXReader对象
			eg: SAXReader saxreader = new SAXReader();
		2: 通过SAXReader对象的read()方法解析xml文件(org.dom4j.Document)		//Document read(File url); (还有其他read方法,详细请看源码)
			rg: Document document = saxreader.read(new File("XXX.xml"));
		3: 通过Document对象获取根节点Element(org.dom4j.Element)	//Element getRootElement();
			eg: Element rootelement = document.getRootElement();
		4: 通过根节点迭代器获取子节点Iterator<Element>: Element (org.dom4j.Element) 	//Iterator<Element> elementIterator();
			eg: Iterator<Element> iterator = rootelement.elementIterator();
				Element book = iterator.next();
		5: 通过子节点属性迭代器获取属性Iterator<Attribute>: Attribute   (org.dom4j.Attribute)	//Iterator<Attribute> attributeIterator();//Attribute: String getName();String getData();
			eg: Iterator<Attribute> attriter = book.attributeIterator();
				Attribute attr = attriter.next();
				syso("属性名: " + attr.getName() + ", 属性值: " + attr.getData());
		6: 获取子节点的节点名和节点值  	// String getName();//String getData();
			eg: Element bookChild = book.elementIterator().next();
				syso("节点名: " + bookChild.getName() + ", 节点值: " + bookChild.getData());
  

 	DOM4J方式写入:
		1: 创建Document对象,代表xml文档
			eg: Document document = DocumentHelper.createDocument();
		2: 创建根节点
			eg: Element rss = document.addElement("rss");
		3: 向rss节点中添加版本信息
			eg: rss.addAttribute("version","2.0");
		4: 生成子节点
			eg: Element channel = rss.addElement("channel");
		5: 向子节点添加属性
			eg: channel.addAttribute("id","1");
		6: 向子节点添加文本
			eg: channel.setText("qwer");
		//生成xml文件
		7: 设置生成的xml的格式(自动换行,缩进)
			eg: OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");	//设置字符编码
		8: 创建XMLWriter对象,并指定生成的xml文件路径
			eg: XMLWriter xmlw = new XMLWriter(new FileOutputStream(new FIle("rssnews.xml")),format);
		9: 将Document对象写入xml文件 	
			eg: xmlw.write(document);
				//xmlw.setEscapeText(false); //设置是否转义,默认为true: 转义
		10: 关闭流
			eg: xmlw.close(); 
 */

public class DOM4J方式
{
	// DOM4J解析
	public static void DOM4JDemo() throws Exception
	{
		System.out.println("============= 开始DOM4J解析 =============");
		// 创建SAXReader对象
		SAXReader saxreader = new SAXReader();
		// 通过SAXReader对象的read()方法解析xml文件
		Document document = saxreader.read(new File("src//Demo.xml"));
		// 获取根节点
		Element rootelement = document.getRootElement();
		// 获取根节点迭代器
		Iterator<Element> iterator = rootelement.elementIterator();
		int i = 0;
		// 遍历迭代器
		while (iterator.hasNext())
		{
			System.out.println("------------开始解析第" + ++i + "本书------------");
			// 获取子节点
			Element book = iterator.next();
			// 获取子节点的属性迭代器
			Iterator<org.dom4j.Attribute> attriter = book.attributeIterator();
			// 迭代遍历子节点属性
			while (attriter.hasNext())
			{
				// 获取子节点属性
				Attribute attribute = attriter.next();
				// 输出节点属性名,属性值
				System.out.println("属性名: " + attribute.getName() + ", 属性值: " + attribute.getData());
			}
			// 获取子节点迭代器
			Iterator<Element> childIterator = book.elementIterator();
			// 遍历子节点迭代器
			while (childIterator.hasNext())
			{
				// 获取子节点的子节点
				Element bookChild = childIterator.next();
				System.out.println("节点名: " + bookChild.getName() + ", 节点值: " + bookChild.getData());
			}
			System.out.println("------------结束解析第" + i + "本书------------");
		}
		System.out.println("============= 结束DOM4J解析 =============");
	}

	// DOM4J写入
	public static void DOM4Jcreate() throws Exception
	{
		// 创建Document对象,代表xml文档
		Document document = DocumentHelper.createDocument();
		// 创建根节点
		Element rss = document.addElement("rss");
		// 向rss节点中添加版本信息
		rss.addAttribute("version", "2.0");
		// 生成子节点及节点内容
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("国内最新新闻");
		// 设置生成的xml的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置字符编码
		format.setEncoding("UTF-8");
		// 创建XMLWriter对象,并指定生成的xml文件路径
		XMLWriter xmlw = new XMLWriter(new FileOutputStream(new File("rssnews.xml")), format);
		// 将Document对象写入xml文件
		xmlw.write(document);
		// 关闭流
		xmlw.close();
	}

	// 测试
	public static void main(String[] args) throws Exception
	{
		DOM4JDemo();
		// DOM4Jcreate();
	}
}
