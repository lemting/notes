package Java_GUI.GUI;

import java.awt.Frame;

/*
 *  Frame的构造方法
 *  	public Frame(): 构造的新实例Frame初始时不可见。 Frame的标题为空
 *  	public Frame(String title): 构造一个新的，最初不可见的 Frame对象与指定的标题
 *  
 *  Frame的类Window的父类Container的父类Component方法: 
 *  	public void setVisible(boolean b): 让窗体可见
 *   	public void setSize(int width,int height): 调整此组件的大小，使其宽度为width ，高度为height
 *  	public void setSize(Dimension d)调整此组件的大小，使其宽度为d.width ，高度为d.height
 *  		Dimension类的构造: public Dimension(int width,int height)构造一个 Dimension并将其初始化为指定的宽度和指定的高度
 *   	public void setLocation(int x,int y): 将此组件移动到新位置
 *   	public void setLocation(Point p): 将此组件移动到新位置
 *   		public Point(int x,int y)在坐标空间中 (x,y)位置构建和初始化一个	
 *   	public void setBounds(int x,int y,int width,int height)移动并调整此组件的大小。 左上角的新位置由x和y ，新尺寸由width和height
 *   Frame的父类Window的方法: 
 *  	public void addWindowListener(WindowListener l): 添加指定的窗口侦听器以从此窗口接收窗口事件
 *  		WindowListener接口: 用于接收窗口事件的侦听器界面
 *  		WindowListener的方法:
 *  			void windowIconified(WindowEvent e): 当窗口从正常状态更改为最小化状态时调用
 *  			void windowClosed(WindowEvent e): 当窗口关闭时调用窗口调用处理结果时调用
 *  			void windowClosing(WindowEvent e): 当用户尝试从窗口的系统菜单中关闭窗口时调用
 *  			void windowDeactivated(WindowEvent e): 当窗口不再是活动窗口时调用
 *  			void windowDeiconified(WindowEvent e): 当窗口从最小化更改为正常状态时调用
 *  			void windowIconified(WindowEvent e): 当窗口从正常状态更改为最小化状态时调用
 *  			void windowOpened(WindowEvent e): 第一次调用窗口可见
 *  Frame的方法: 
 *   	public void setTitle(String title): 将此框架的标题设置为指定的字符串
 */

public class FrameDemo
{
	public static void main(String[] args)
	{
		//创建窗体对象//public Frame(String title): 构造一个新的，最初不可见的 Frame对象与指定的标题
		Frame f = new Frame("helloworld");
		
		//设置窗体大小//public void setSize(int width,int height): 调整此组件的大小，使其宽度为width ，高度为height
		f.setSize(400,300);//默认单位像素
		
		//设置窗口初始位置//public void setLocation(int x,int y): 将此组件移动到新位置
		f.setLocation(200,300);
		
		//设置窗体大小,初始位置
		f.setBounds(200, 300, 400, 300);
		
		//设置让窗口可见//public void setVisible(boolean b): 让窗体可见
		f.setVisible(true);
	}
}
