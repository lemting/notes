package Java_GUI.GUI;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class 把文本框的值转移到文本域
{
	public static void main(String[] args)
	{
		//创建窗体对象
		Frame f = new Frame("数据转移");
		f.setBounds(300, 200, 400, 300);
		//设置为流式布局
		f.setLayout(new FlowLayout());
		
		//创建文本框
		TextField tf = new TextField();
		//创建按钮
		Button bu = new Button("数据转移");
		//创建文本域
		TextArea ta = new TextArea(10,40);
		
		//把组件添加到窗体
		f.add(tf);
		f.add(bu);
		f.add(ta);
		
		//设置窗体关闭
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e){System.exit(0);}
		});
		
		//对按钮添加事件
		bu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//获取文本框的值
				String tf_string = tf.getText().trim();	
				//清空数据
				tf.setText("");
				//设置给文本域
				//ta.setText(tf_string);
				//追加和换行
				ta.append(tf_string + "\r\n");
				
				//文本框获取光标
				tf.requestFocus();
			}
		});
		
		//设置窗体显示
		f.setVisible(true);
	}
}
