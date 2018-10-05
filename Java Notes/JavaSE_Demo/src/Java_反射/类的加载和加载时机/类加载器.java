package Java_反射.类的加载和加载时机;

/*
 *  类加载器
 *  	负责将class文件加载到内存中,并为之生成对应的Class对象
 *  类加载器的组成
 *  	Bootstrap ClassLoader 根类加载器,也被成称为引导类加载器
 *  		负责Java核心类的加载
 *  			比如System.String等.在JDK中的JRE的lib目录下rt.jar文件中
 *  	Extension ClassLoader 扩展类加载器
 *  		负责JRe的扩展目录中的jar包的加载
 *  			在JDK中的JRE的lib目录下的ext目录
 *  	System ClassLoader 系统类加载器
 *  		负责在JVM启动时加载来自java命令的class文件,以及classpath环境变量所指定的jar包和类路径
 */

public class 类加载器
{
	
}
