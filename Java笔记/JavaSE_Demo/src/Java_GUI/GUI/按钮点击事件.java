package Java_GUI.GUI;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 *  把按钮添加到窗体,并对按钮添加一个点击事件
 *  A: 创建窗体对象
 *  B: 创建按钮对象
 *  C: 把按钮添加到窗体
 *  D: 窗体显示
 *  
 *  
 *  Button: 这个类创建一个标记的按钮
 *  
 *  Button的构造方法:
 *  	public Button(): 构造一个带有标签的空字符串的按钮
 *  	public Button(String label): 构造具有指定标签的按钮
 *  
 *  Button的方法: 
 *  	public void addActionListener(ActionListener l): 添加指定的动作侦听器以从此按钮接收动作事件
 */

public class 按钮点击事件
{
	public static void main(String[] args)
	{
		//创建窗体对象
		Frame f = new Frame("按钮点击事件");	
		//设置属性
		f.setBounds(200,300,400,300);
		
		//创建按钮对象
		Button bu = new Button("QwQ");
		bu.setSize(20,10);
			
		//设置布局为流式布局
		f.setLayout(new FlowLayout());
				
		//把按钮添加到窗体
		f.add(bu);
		
		//添加事件
		bu.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("你再点试试");
			}
		});
		
		//设置窗体可以关闭
		f.addWindowListener(new WindowAdapter()
		{		
			@Override
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		
		//窗体显示
		f.setVisible(true);
	}
}
