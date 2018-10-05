package Java_GUI.GUI;

/*
 *  问题: 
 *  	接口(方法较多) --- 实现类(仅使用一个,也得把其他的实现给提供了,哪怕是空实现)
 *  	太麻烦了
 *  
 *  解决方案: 
 *  	接口(方法比较多) --- 适配器类(实现接口,仅仅空实现) --- 实现类(用哪个重写哪个)
 */

//用户接口
interface User
{
	public abstract void add();
	public abstract void delete();
	public abstract void update();
	public abstract void find();
}

//适配器类
abstract class UserAdapter implements User
{
	public void add(){}
	public void delete(){}
	public void update(){}
	public void find(){}
}

//实现类
class UserDao extends UserAdapter
{
	@Override
	public void add()
	{
		System.out.println("添加功能");
	}
}

//接口实现类
public class 适配器模式
{
	public static void main(String[] args)
	{
		UserDao ud = new UserDao();
		ud.add();
	}
}
