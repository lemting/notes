package Java_GUI.GUI;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * 	MenuComponent
 * 		抽象类MenuComponent是所有菜单相关组件的超类
 * 	
 * 	MenuBar,Menu,MenuItem
 *  	先创建菜单栏,再创建菜单,每一个菜单中创立菜单项
 *  	MenuBar: public Menu add(Menu m)将指定的菜单添加到菜单栏
 *  
 *  	也可以菜单添加到菜单中,作为子菜单
 *  	通过setMenuBar()方法,将菜单添加到Frame中
 */

public class 一级菜单
{
	public static void main(String[] args)
	{
		//创建窗体对象
		Frame f = new Frame("一级菜单案例");			
		//设置窗体属性
		f.setBounds(300,200,800,600);
		f.setLayout(new FlowLayout());
		
		//创建菜单栏,菜单和菜单项
		MenuBar mb = new MenuBar();
		Menu m = new Menu("File");
		MenuItem mi1 = new MenuItem("exit");
		
		//将菜单项添加到菜单
		m.add(mi1);
		//将菜单添加到菜单栏
		mb.add(m);
		
		//设置菜单栏
		f.setMenuBar(mb);
		
		//事件处理: 关闭处理
		mi1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		f.addWindowListener(new WindowAdapter()
		{		
			@Override
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
				
		//设置窗体可见
		f.setVisible(true);
	}
}

//获取第一个操作数
