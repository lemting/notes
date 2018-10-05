package Java_GUI.GUI;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 *  事件监听机制: 
 *  	A: 事件源
 *  		时间发生的地方
 *  	B: 事件
 *  		要发生的事情
 *  	C: 事件处理
 *  		针对发生的事情做出的处理方案
 *  	D: 事件监听
 *  		把事件源和事件关联起来
 *  
 *  	举例: 人受伤事件
 *  		事件源: 人(具体的对象)
 *  			Person p1 = new Person("张三");
 *  			Person p2 = new Person("李四");
 *  		事件: 受伤
 *  			interface 受伤接口
 *  			{
 *  				一拳();
 *  				一脚();
 *  				一板砖();
 *  			}			
 *  		事件处理
 *  			受伤处理类 implements 受伤接口
 *  			{
 *  				一拳(){System.out.println("鼻子流血了,到卫生间洗洗");}
 *  				一脚(){System.out.println("晕倒了,到通风处");}
 *  				一板砖(){System.out.println("头破血流,到太平间");}
 *  			} 
 *  		事件监听
 *  			p1.注册监听(受伤接口)
 *  
 *  WindowListener接口: 
 *  	用于接收窗口事件的侦听器界面
 *  WindowListener的方法:
 *  	void windowIconified(WindowEvent e): 当窗口从正常状态更改为最小化状态时调用
 *  	void windowClosed(WindowEvent e): 当窗口关闭时调用窗口调用处理结果时调用
 *  	void windowClosing(WindowEvent e): 当用户尝试从窗口的系统菜单中关闭窗口时调用
 *  	void windowDeactivated(WindowEvent e): 当窗口不再是活动窗口时调用
 *  	void windowDeiconified(WindowEvent e): 当窗口从最小化更改为正常状态时调用
 *  	void windowIconified(WindowEvent e): 当窗口从正常状态更改为最小化状态时调用
 *  	void windowOpened(WindowEvent e): 第一次调用窗口可见
 *  
 *  WindowAdapter: WindowListener接口的适配器
 */

public class 事件监听机制
{
	public static void main(String[] args)
	{
		//创建
		Frame f = new Frame("窗体关闭案例");
		
		//设置窗体属性
		f.setBounds(200,300,400,300);
		
		//让窗体关闭
		//事件源: 对窗体的处理
		//事件: 关闭窗体		(System.exit(0);)
		//事件处理: 关闭处理
		//事件监听
		f.addWindowListener(new WindowAdapter()
		{		
			//事件: 关闭窗体		(System.exit(0);)
			@Override
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		
		//设置窗体可见
		f.setVisible(true);
	}
}
