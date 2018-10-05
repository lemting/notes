package Java_GUI.GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class 通过鼠标移动到按钮上更改背景颜色
{
	public static void main(String[] args)
	{
		//创建窗体
		Frame f = new Frame("更改背景颜色");
		f.setBounds(200,300,800,600);
		f.setLayout(new FlowLayout());
		
		//创建按钮
		Button redButton = new Button("red");
		Button yellowButton = new Button("yellow");
		Button greenButton = new Button("green");
		Button blackButton = new Button("black");
		
		//对按钮添加动作事件
//		redButton.addActionListener(new ActionListener()
//		{	
//			@Override
//			public void actionPerformed(ActionEvent e){f.setBackground(Color.red);}
//		});
//		yellowButton.addActionListener(new ActionListener()
//		{	
//			@Override
//			public void actionPerformed(ActionEvent e){f.setBackground(Color.yellow);}
//		});
//		greenButton.addActionListener(new ActionListener()
//		{	
//			@Override
//			public void actionPerformed(ActionEvent e){f.setBackground(Color.green);}
//		});
//		blackButton.addActionListener(new ActionListener()
//		{	
//			@Override
//			public void actionPerformed(ActionEvent e){f.setBackground(Color.black);}
//		});
		
		//对按钮添加鼠标进入事件
		redButton.addMouseListener(new MouseAdapter()
		{
			//鼠标进入更改背景颜色为红色
			@Override
			public void mouseEntered(MouseEvent e){f.setBackground(Color.RED);}
			//鼠标移出更改背景颜色为白色
			@Override
			public void mouseExited(MouseEvent e){f.setBackground(Color.WHITE);}
		});
		yellowButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e){f.setBackground(Color.YELLOW);}
			@Override
			public void mouseExited(MouseEvent e){f.setBackground(Color.WHITE);}
		});
		greenButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e){f.setBackground(Color.GREEN);}
			@Override
			public void mouseExited(MouseEvent e){f.setBackground(Color.WHITE);}
		});
		blackButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e){f.setBackground(Color.BLACK);}
			@Override
			public void mouseExited(MouseEvent e){f.setBackground(Color.WHITE);}
		});
		
		//添加按钮
		f.add(redButton);
		f.add(yellowButton);
		f.add(greenButton);
		f.add(blackButton);
		
		//窗体关闭
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		
		//窗体显示
		f.setVisible(true);
	}
}
