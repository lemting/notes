package Java_GUI.GUI;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class 文本框不能输入非数字字符
{
	public static void main(String[] args)
	{
		//创建窗体对象
		Frame f = new Frame("不能输入非数字字符");
		f.setBounds(200,300,800,600);
		f.setLayout(new FlowLayout());
		
		//创建label标签对象
		Label label = new Label("请输入你的QQ号码,不能是非数字,不信你试试");
		//创建文本框对象
		TextField tf = new TextField(80);
		
		//输入的如果是非数字字符,就取消键盘录入结果
		tf.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				//如果获取的字符不是数字字符,就取消事件
				//获取字符
				char ch = e.getKeyChar();
				if(ch != 8 && (ch > '9' || ch < '0'))
				{
					e.consume();
				}
			}
		});
		
		//添加到窗体
		f.add(label);
		f.add(tf);
		
		
		//设置窗体关闭
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		
		//设置窗体可见
		f.setVisible(true);
	}
}
