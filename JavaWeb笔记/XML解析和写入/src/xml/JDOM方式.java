package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/*
	JDOM方式解析: (需要导入jdom的jar包)
		1: 创建SAXBuilder对象
			eg: SAXBuilder saxbuilder = new SAXBuilder();
		2: 通过SAXBuilder对象的build()方法解析xml文件(org.jdom2.Document)	//Document build(InputStream in);(还有其他build方法,详细请看源码)
			eg: org.jdom.Document doc = saxbuilder.build(new FileInputStream("XXX.xml"));
		3: 通过Document对象获取根节点Element(org.jdom2.Element)	//Element getRootElement();
			eg: Element rootelement = doc.getRootElement();
		4: 通过Element对象获取子节点集合List<Element>	//List<Element> getChildren();
			eg: List<Element> booklist = rootelement.getChildren();
		5: 通过Element对象获取属性集合List<Attribute>	//List<Attribute> getAttributes();
			eg: List<Attribute> attrlist = booklist.get(0).getAttributes();
		6: 通过Attribute对象获取属性名,属性值	//String getName();//String getValue();
			eg: syso("属性名: " + attrlist.get(0).getName() + ", 属性值: " + attrlist.get(0).getValue());
		7: 通过Element对象获取节点名,节点值		//String getName();//String getValue();
			eg: syso("节点名: " + booklist.get(0).getName() + ", 节点值: " + booklist.get(0).getValue());
	
  	JDOM方式写入:
		1: 生成一个根节点
			eg: Element rss = new Element("rss");
		2: 设置根节点版本属性
			eg:	rss.setAttribute("version","2.0"); 
		3: 根据根节点创建Document对象
			eg: Document document = new Document(rss);
		4: 生成子节点
			eg: Element channel = new Element("channel");
		5: 设置节点属性
			eg:	channel.setAttribute("id","1"); 
		6: 设置节点文本内容
			eg: channel.setText("qwer");
		7: 将子节点添加到根节点
			eg: rss.addContent(channel);
		//转换为xml文件
		7: 设置xml文件格式
			eg: Format format = Format.getCompactFormat();
				format.setEncoding("UTF-8"); //设置字符编码	
				format.setIndent(""); //设置自动换行
		9: 创建XMLOutputter对象
			eg: XMLOutputter xmlop = new XMLOutputter(format);
		10: 通过XMLOutputter对象将Document对象转换为xm文件
			eg: xmlop.output(document, new FileOutputStream(new File("rssnews.xml")));	  		
 */


public class JDOM方式
{
	//JDOM解析
	public static void JDOMDemo() throws Exception
	{
		System.out.println("============= 开始JDOM解析 =============");
		//创建SAXBuilder对象saxbuilder
		SAXBuilder saxbuilder = new SAXBuilder();
		//通过SAXBuilder对象的build方法解析xml文件
		Document document = saxbuilder.build(new FileInputStream("src\\Demo.xml"));
		//获取根节点
		Element rootelement = document.getRootElement();
		//获取子节点集合
		List<Element> booklist = rootelement.getChildren();
		//遍历子节点集合
		for(Element book: booklist)
		{
			System.out.println("------------开始解析第" + (booklist.indexOf(book) + 1) + "本书------------");
			//解析book属性
			List<Attribute> attrlist = book.getAttributes();
			//遍历book属性集合
			for(Attribute attr: attrlist)
			{
				//获取属性名和属性值
				System.out.println("属性名: " + attr.getName() + ", 属性值: " + attr.getValue());
			}
			//获取book的子节点集合
			List<Element> elements = book.getChildren();
			//遍历book的子节点集合
			for(Element e: elements)
			{
				//获取子节点的节点名和节点值
				System.out.println("节点名: " + e.getName() + ", 节点值: " + e.getValue());
			}
			System.out.println("------------结束解析第" + (booklist.indexOf(book) + 1) + "本书------------");
		}
		System.out.println("============= 结束JDOM解析 =============");
	}
	
	//JDOM写入
	public static void JDOMcreate() throws Exception
	{
		//生成一个根节点
		Element rss = new Element("rss");
		//设置根节点版本属性
		rss.setAttribute("version","2.0");
		//创建Document对象
		Document document = new org.jdom2.Document(rss);
		//生成子节点
		Element channel = new Element("channel");
		//设置节点属性
		channel.setAttribute("id","1");
		//将子节点添加到根节点
		rss.addContent(channel);
		//生成节点
		Element title = new Element("title");
		//设置节点文本内容
		title.setText("国内最新新闻");
		//将节点添加到子节点
		channel.addContent(title);
		//设置xml文件格式
		Format format = Format.getCompactFormat();
		//设置字符编码
		format.setEncoding("UTF-8");
		//设置自动换行
		format.setIndent("");
		//转换为xml文件
		XMLOutputter xmlop = new XMLOutputter(format);
		xmlop.output(document, new FileOutputStream(new File("rssnews.xml")));
	}
	
	//测试
	public static void main(String[] args) throws Exception
	{
		JDOMDemo();
		//JDOMcreate();
	}
}
