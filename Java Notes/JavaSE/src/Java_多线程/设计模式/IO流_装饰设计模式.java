package Java_多线程.设计模式;

/*
 *  装饰设计模式
 *  	装饰设计模式是使用被装饰类的一个子类的实例,在客户端将这个子类的实例交给装饰类,是继承的代替方案
 *  
 *  	优点: 使用装饰模式,可以提供比继承更灵活的扩展对象的功能,他可以动态的添加对象的功能,并且可以随意的组合这些功能
 *  	缺点: 正因为可以随意组合,所以就可能出现一些不合理的逻辑
 *  
 *  IO流中的装饰模式
 *  	InputStream is = System.in;
 *  	InputStreamReader isr = new InputStreamReader(is);//将字节流装饰为字符流
 *  	BufferedReader br = new BUfferedReader(isr);//将字符流装饰为高效字符流
 *  
 *  	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *  	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Sysytem.out));
 */

//手机接口
interface Phone {
	public abstract void call();
}

//具体手机类
class vivo implements Phone {
	@Override
	public void call() {
		System.out.println("手机可以打电话了");
	}
}

//手机装饰抽象类
abstract class PhoneZhuangshi implements Phone {
	private Phone p;

	public PhoneZhuangshi(Phone p) {
		this.p = p;
	}

	public void call() {
		this.p.call();
	}
}

//手机彩铃装饰类
class Zhuangshi_Cailing extends PhoneZhuangshi {
	public Zhuangshi_Cailing(Phone p) {
		super(p);
	}

	public void call() {
		System.out.println("手机可以听彩铃了");
		super.call();
	}
}

//手机听音乐装饰类
class Zhuangshi_Yinyue extends PhoneZhuangshi {
	public Zhuangshi_Yinyue(Phone p) {
		super(p);
	}

	@Override
	public void call() {
		super.call();
		System.out.println("手机可以听音乐了");
	}
}

public class IO流_装饰设计模式 {
	public static void main(String[] args) {
		// 手机打电话
		vivo v = new vivo();
		v.call();
		System.out.println("-------------------------");

		// 手机打电话装饰彩铃
		PhoneZhuangshi pz = new Zhuangshi_Cailing(new vivo());
		pz.call();
		System.out.println("-------------------------");

		// 手机打电话装饰听音乐
		PhoneZhuangshi pz2 = new Zhuangshi_Yinyue(new vivo());
		pz2.call();
		System.out.println("-------------------------");

		// 手机打电话装饰彩铃,听音乐
		PhoneZhuangshi pz3 = new Zhuangshi_Cailing(new Zhuangshi_Yinyue(new vivo()));
		pz3.call();
	}
}
