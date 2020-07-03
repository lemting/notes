package Java_反射.反射;

/*
 *  代理: 本来应该自己做的事情,却请了别人来做,被请的人就是代理对象
 * 
 *  动态代理: 通过反射类生成一个代理
 *  	在Java中java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口,通过使用这个类和接口就可以生成动态代理对象
 *  	
 *  	注意: JDK提供的代理只能针对接口做代理,我们有更强大的代理cglib
 *
 *  Proxy类中的方法创建动态代理类对象
 *  	public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h): 返回指定接口的代理类的实例，该接口将方法调用分派给指定的调用处理程序
 *  	
 *  InvocationHandler
 *  	Object invoke(Object proxy,Method method,Object[] args): 处理代理实例上的方法调用并返回结果
 */

public class 动态代理 {

}
