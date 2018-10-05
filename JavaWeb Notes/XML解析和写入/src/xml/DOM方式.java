package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
	DOM方式解析: 
		1: 创建一个DocumentBuilderFactory对象
			eg: DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //DocumentBuilderFactory类
		2: 创建一个DocumentBuilder对象
			eg: DocumentBuilder db = dbf.newDocumentBuilder();	//DocumentBuilderFactory类
		3: 通过DocumentBuilder对象的 public Document parse(String fileURL) 方法解析xml文件	//DocumentBuilder类
			eg: org.w3c.dom.Document d = db.parse("XXX.xml"); //该Document在org.w3c.dom包下
		1-3: eg: Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("XXX.xml");
		4: 获取节点
			public Element getElementById(String elementId): 获取指定ID的节点  //Document类
				eg: d.getElementById("123");
			public NodeList getElementsByTagName(String tagname): 获取指定标签名的节点集合  //Document类
				eg: NodeList nodelist = d.getElementsByTagName("div");
			public Node item(int index): 获取指定索引值的节点   //NodeList类
				eg: Node node = nodelist.item(0);
		5: 获取节点属性集合
			public NamedNodeMap getAttributes(): 获取节点属性集合	//Node类
				eg: NamedNodeMap nnm = node.getAttributes();
			public Node item(int index): 获取属性集合指定索引的的属性	//NamedNodeMap类
				eg: Node n = nnm.item(0);
			public String getNodeName(): 获取节点名		//Node类
				eg: syso(n.getNodeName());
			public String getNodeValue(): 获取节点值	//Node类
				eg: syso(n.getNodeValue());
		6: 获取节点的子节点
			public NodeList getChildNodes(): 获取节点的子节点集合
				eg: NodeList nodelist = n.getChildNodes();
			public Node item(int index): 获取指定索引的节点
				eg: Node node = nodelist.item(0);
			public short getNodeType(): 获取子节点的节点类型
				eg: node.getNodeType() == Node.ELEMENT_NODE;
			public String getNodeName(): 获取子节点的节点名
				eg: syso("节点名: " + node.getNodeName());
			public String getTextContent(): 获取子节点的节点值
				eg: syso("节点值: " + node.getTextContent());	
		
	DOM方式写入: 	
		1: 创建一个DocumentBuilderFactory对象
			eg: DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //DocumentBuilderFactory类
		2: 创建一个DocumentBuilder对象
			eg: DocumentBuilder db = dbf.newDocumentBuilder();	//DocumentBuilderFactory类
		3: 创建Document对象	(org.w3c.dom.Document)
			eg: Document document = db.newDocument();
		4: 创建根节点	(org.w3c.dom.Element)	//Element createElement(String elementName);
			eg: Element rootElement = document.createElement("bookstore");
		5: 创建子节点	(org.w3c.dom.Element)	//Element createElement(String elementName);
			eg: Element bookElement = document.createElement("book");
		6: 设置子节点属性	//void setAttribute(String key,String value);
			eg: bookElement.setAttribute("id","1");
		7: 设置子节点的文本值	//void setTextContent(String value);
			eg: bookElement.setTextContent("小王子");
		8: 将子节点添加到根节点		//Node appendChild(Node newChild);
			eg: rootElement.appendChild(bookElement);
		9: 将根节点添加到dom树中	//Node appendChild(Node newChild);
			eg: document.appendChild(rootElement);
		//根据Document对象生成xml文件
		10: 创建TransformerFactory对象 
			eg: TransformerFactory tff = TransformerFactory.newInstance();
		11: 获取Transformer对象
			eg: Transformer tf = tff.newTransformer();
		12: 设置xml文件换行
			eg: tf.setOutputProperty(OutputKeys.INDENT,"yes");
		13: 通过Transformer对象的transform()方法将document转换为xml文件 	//void transform(new DOMSource(Document对象),new StreamResult(new File("URL")));
			eg: tf.transform(new DOMSource(document),new StreamResult(new File("books.xml")));
*/

/*
 	DOM常用类:		
	org.w3c.dom包下
	┌──────────────────────────────────────────────────────────────────────────────────────────┐
	│                                       常用的节点类型                                                                                                                  │
	├──────────────┬─────────────┬──────────────────┬─────────────────────┬────────────────────┤
	│	    节点类型 	   │   NodeType	 │  Named Constant  │    nodeName的返回值    	  │   nodeValue的返回值       │
	├──────────────┼─────────────┼──────────────────┼─────────────────────┼────────────────────┤
	│	 Element   │	  1		 │	 ELEMENT_NODE	│	 element name	  │		  null		   │
	│	  Attr	   │ 	  2		 │	ATTRIBUTE_NODE	│	   	属性名称	  	  │		      属性值		   │
	│	  Text	   │	  3		 │    TEXT_NODE		│		#text		  │	                节点内容	       │
	└──────────────┴─────────────┴──────────────────┴─────────────────────┴────────────────────┘					
		
	Node: 节点 (interface org.w3c.dom.Node)
		成员方法: 
			NamedNodeMap getAttributes(): NamedNodeMap包含该节点的所有属性
			NodeList getChildNodes(): NodeList包含此节点的所有子节点
			Node appendChild(Node newChild): 将节点newChild添加到此节点的子节点列表的末尾.如果newChild已经在树中，那么它首先被删除
			Node getFirstChild(): 获取这个节点的第一个孩子
			Node getLastChild(): 获取这个节点的最后一个孩子
			Node getParentNode(): 获取此节点的父节点
			Node removeChild(Node oldChild): 删除指定的子节点oldChild,并将其返回
			Node replaceChild(Node newChild,Node oldChild): 替换子节点oldChild与newChild，并返回oldChild节点
			boolean isEqualNode(Node arg): 测试两个节点是否相等
			String getNodeName(): 获取该节点的名称
			short getNodeType(): 获取代表基础对象的类型的代码
			String getNodeValue(): 获取该节点的值
			String getTextContent(): 获取此节点及其后代的文本内容
			void setNodeValue(String nodeValue): 设置该节点的值
			void setTextContent(String textContent): 设置此节点及其后代的文本内容(可能会将子节点删除)
			
	NodeList: Node节点集合 (interface org.w3c.dom.NodeList)
		成员方法: 
			Node item(int index): 返回index中的index项目
			int getLength(): 获取列表中的节点数
		
	NamedNodeMap: (interface org.w3c.dom.NamedNodeMap)
		成员方法: 
			int getLength(): 该地图中的节点数
			Node getNamedItem(String name): 获取由name指定的节点
			Node item(int index): 获取地图中的index项目
			Node removeNamedItem(String name): 删除由name指定的节点,并返回该节点
			Node setNamedItem(Node arg): 添加节点.如果具有该名称的节点已经存在于该映射中,则会被新映射替换
	
	Node的子接口: 
		Document: 代表整个HTML或XML文档  (interface org.w3c.dom.Document) 
			类型: Node.DOCUMENT_NODE
			成员方法: 
				Element getElementById(String elementId): 获取指定ID属性的Element
				NodeList getElementsByTagName(String tagname): 获取指定标签名的节点集合
		
		Element: 表示HTML或XML文档中的元素 (Interface org.w3c.dom.Element)
			类型: Node.ELEMENT_NODE
			成员方法: 
				String getTagName(): 获取元素的名称
				String getAttribute(String name): 获取指定属性的属性值
				Attr getAttributeNode(String name): 获取指定名称的属性节点
				boolean hasAttribute(String name): 判断此元素上是否具有指定名称的属性或具有缺省值
				void removeAttribute(String name): 删除指定名称的属性
				Attr removeAttributeNode(Attr oldAttr): 删除指定的属性节点,并返回该属性节点
				void setAttribute(String name,String value): 添加一个新属性
				Attr setAttributeNode(Attr newAttr): 添加一个新的属性节点
		
		Attr: 表示Element对象中的属性 (Interface org.w3c.dom.Attr)
			类型: Node.ATTRIBUTE_NODE
			成员方法:
				String getName(): 获取此属性的名称
				String getValue(): 获取此属性的值
				void setValue(String value): 设置此属性的值
				boolean getSpecified(): 判断此属性在实例文档中是否被明确赋予了一个值
 */

public class DOM方式
{	
	//DOM方式解析
	public static void DOMDemo() throws Exception
	{
		System.out.println("============= 开始DOM解析 =============");
		//创建DocumentBuilderFactory对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		//创建DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		//通过DocumentBuilder对象的parse方法加载books.xml文件到当前项目
		Document document = db.parse("src//Demo.xml");
		//获取所有book节点
		NodeList booklist =  document.getElementsByTagName("book");
		//循环遍历每一个book节点
		for(int i = 0;i < booklist.getLength();i++)
		{
			System.out.println("------------开始解析第" + (i + 1) + "本书------------");
			//获取每一个book节点
			Node n = booklist.item(i);
			//获取book节点的所有属性集合
			NamedNodeMap nnm =  n.getAttributes();
			//循环遍历book属性集合
			for(int j = 0;j < nnm.getLength();j++)
			{
				//获取属性集合的属性
				Node no = nnm.item(j);
				System.out.println("属性名: " + no.getNodeName() + ", 属性值: " + no.getNodeValue());
			}
			//获取book子节点集合
			NodeList nodeList = n.getChildNodes(); 
			//遍历book子节点集合
			for(int k = 0;k < nodeList.getLength();k++)
			{
				//获取每一个子节点
				Node node = nodeList.item(k);
				//若果节点是Element节点,则输出节点名和节点值
				if(node.getNodeType() == Node.ELEMENT_NODE)
					System.out.println("节点名: " + node.getNodeName() + ", 节点值: " + node.getTextContent());
			}
			
			System.out.println("------------结束解析第" + (i + 1) + "本书------------");
		}
		System.out.println("============= 结束DOM解析 =============");
	}

	//DOM写入
	public static void DOMCreate() throws Exception
	{
		//创建一个DocumentBuilderFactory对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//创建一个DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		//创建Document对象
		Document document = db.newDocument();
		//设置Standalone属性
		document.setXmlStandalone(true);
		//创建根节点
		Element rootElement = document.createElement("bookstore");
		//创建根节点的子节点
		Element bookElement1 = document.createElement("book");
		Element bookElement2 = document.createElement("book");		
		//设置子节点属性
		bookElement1.setAttribute("id","1");
		bookElement2.setAttribute("id","2");
		//创建子节点的子节点
		Element nameElement1 = document.createElement("name");
		Element nameElement2 = document.createElement("name");
		//设置子节点的子节点的文本值
		nameElement1.setTextContent("小王子");
		nameElement2.setTextContent("大王子");	
		//将节点添加到子节点
		bookElement1.appendChild(nameElement1);
		bookElement2.appendChild(nameElement2);
		//向根节点添加子节点
		rootElement.appendChild(bookElement1);
		rootElement.appendChild(bookElement2);
		//将根节点添加到dom树中
		document.appendChild(rootElement);
		
		//转为xml文件
		//创建TransformerFactory对象
		TransformerFactory tff = TransformerFactory.newInstance();
		//获取Transformer对象
		Transformer tf = tff.newTransformer();
		//设置xml文件换行
		tf.setOutputProperty(OutputKeys.INDENT,"yes");
		//通过Transformer对象的transform()方法将document转换为xml文件
		tf.transform(new DOMSource(document),new StreamResult(new File("books.xml")));
	}

	//测试
	public static void main(String[] args) throws Exception
	{
		DOMDemo();
		//DOMCreate();
	}
}
