package xml;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

/*
  	SAX方式解析: 
		1: 通过SAXParserFactory的静态newInstance()方法获取SAXParserFactory实例factory
			eg: SAXParserFactory factory = SAXParserFactory.newInstance();
		2: 获取SAXParserFactory实例的newSAXParser()方法返回SAXParser实例parser
			eg: SAXParser parser = factory.newSAXParser();
		3: 创建一个类继承DafaultHandler,重写其中的一些方法进行业务处理并创建这个类的实例handler
			重写方法: 
				public void startDocument(): 用来标识解析开始
				public void endDocument(): 用来标识解析结束
				public void startElement(String uri, String localName, String qName, Attributes attributes): 用来遍历xml文件的开始标签
					uri: 命名空间,localName: 本地名称,qName: 节点名(标签名),attributes: XML属性集合
							Attributes常用方法:
							 	int getLength(): 获取属性个数
						 		String getQName(int index): 获取指定索引的属性名
						 		String getValue(int index): 获取指定索引的属性值
						 		String getValue(String qName): 获取指定属性名的属性值
				public void endElement(String uri, String localName, String qName): 用来遍历xml文件的结束标签
					uri: 命名空间,localName: 本地名称,qName: 节点名(标签名)
				public void characters(char[] ch, int start, int length): 用来接收元素内的字符数据
		4: 通过SAXParser实例的parse(String URL,DafaultHandler handler)方法解析xml文件
			eg: parser.parse("XXX.xml",handler);
  	
  	SAX方式写入:
		1: 创建SAXTransformerFactory对象 	//SAXTransformerFactory SAXTransformerFactory.newInstance();
			eg: SAXTransformerFactory saxtff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
		2: 通过SAXTransformerFactory对象获取TransformerHandler对象 	//TransformerHandler newTransformerHandler();
			eg: TransformerHandler handler =  saxtff.newTransformerHandler();
		3: 通过TransformerHandler对象创建Transformer对象 	//Transformer getTransformer();
			Transformer tf = handler.getTransformer();
		4: 对xml文件进行设置
			eg: tf.setOutputProperty(OutputKeys.INDENT,"yes"); //设置换行 
				tf.setOutputProperty(OutputKeys.ENCODING,"UTF-8");	//设置字符编码
		5: 创建Result对象并创建xml文件 
			Result result = new StreamResult(new FileOutputStream(new File("books.xml")));
		6: 使Result对象与handler产生关联
			handler.setResult(result);
		//写入xml文件信息
		7: 打开Document	
			handler.startDocument();
		8: 创建AttributesImpl对象
			eg: AttributesImpl attr = new AttributesImpl();
		9: 设置属性
			eg: attr.clear();	//清除所有已设属性
				attr.addAttribute("", "", "id", "String", "1");	//添加属性
		10: 创建开始标签
			eg: handler.startElement("", "", "book",attr);
		11: 设置标签间的文本
			eg: String s = "小王子"; handler.characters(s.toCharArray(),0,s.length());
		11: 创建结束标签
			eg: handler.endElement("", "", "book");	
		12: 关闭Document
			handler.endDocument();
 */

//创建一个类继承DafaultHandler,重写其中的一些方法进行业务处理并创建这个类的实例handler
class SAXParserHandler extends DefaultHandler
{
	private int num = 0;
	//用来标识解析开始
	@Override	
	public void startDocument() throws SAXException
	{
		super.startDocument();
		System.out.println("============= 开始SAX解析 =============");
	}
	//用来遍历xml文件的开始标签
	@Override	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		super.startElement(uri, localName, qName, attributes);
		if(qName.equals("book"))
		{
			System.out.println("------------开始解析第" + ++num + "本书------------");
			//已知属性名可直接用 String getValue("属性名"): 获取属性值
			//循环遍历属性集合
			for(int i = 0;i < attributes.getLength();i++)
			{
				//输出属性名和属性值
				System.out.println("属性名: " + attributes.getQName(i) + ", 属性值: " + attributes.getValue(i));
			}
		}
		else if(!qName.equals("books"))
		{
			System.out.print("节点名: " + qName + ", 节点值: ");
		}
	}
	//用来遍历xml文件的结束标签
	@Override	
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		super.endElement(uri, localName, qName);
		if(qName.equals("book"))
			System.out.println("------------结束解析第" + num + "本书------------");
	}
	//用来接收元素内的字符数据
	@Override	
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		super.characters(ch, start, length);
		String string = new String(ch,start,length);
		if(!string.trim().equals(""))
			System.out.println(string);
	}
	//用来标识解析结束
	@Override	
	public void endDocument() throws SAXException
	{
		super.endDocument();
		System.out.println("============= 结束SAX解析 =============");
	}
}

public class SAX方式
{
	//SAX解析
	public static void SAXDemo() throws Exception
	{
		//创建SAXTransformerFactory对象 
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//获取SAXParserFactory实例的newSAXParser()方法返回SAXParser实例parser
		SAXParser parser = factory.newSAXParser();
		//通过SAXParser实例的parse(String URL,DafaultHandler handler)方法解析xml文件
		parser.parse("src\\Demo.xml", new SAXParserHandler());
	}
	
	//SAX写入
	public static void SAXcreate() throws Exception
	{
		//创建SAXTransformerFactory对象
		SAXTransformerFactory saxtff = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
		//通过SAXTransformerFactory对象获取TransformerHandler对象
		TransformerHandler handler =  saxtff.newTransformerHandler();
		//通过TransformerHandler对象创建Transformer对象
		Transformer tf = handler.getTransformer();
		//设置xml文件换行
		tf.setOutputProperty(OutputKeys.INDENT,"yes");
		//设置xml文件字符编码
		tf.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		Result result = new StreamResult(new FileOutputStream(new File("books.xml")));
		//使Result对象与handler产生关联
		handler.setResult(result);
		//打开Document
		handler.startDocument();
		//创建AttributesImpl对象
		AttributesImpl attr = new AttributesImpl();
		//创建开始标签
		handler.startElement("", "", "bookstore",attr);
		//清除所有已设属性
		attr.clear();
		//添加属性
		attr.addAttribute("", "", "id", "String", "1");
		//创建开始标签
		handler.startElement("", "", "book",attr);
		attr.clear();
		//创建开始标签
		handler.startElement("", "", "name",attr);
		String string = "小王子";
		//设置标签间的文本
		handler.characters(string.toCharArray(),0,string.length());
		//创建结束标签
		handler.endElement("", "", "name");
		//创建结束标签
		handler.endElement("", "", "book");
		//创建结束标签
		handler.endElement("", "", "bookstore");
		//关闭Document
		handler.endDocument();
	}

	//测试 
	public static void main(String[] args) throws Exception
	{
		SAXDemo();
		//SAXcreate();
	}
}
