package Java_多线程.设计模式;

/*
 *  简单工厂模式:
 *  	又叫静态工厂方法模式,他定义一个具体的工厂类负责创建一些类的实例
 *  
 *  优点: 客户端不需要在负责对象的创建,从而明确了各个类的职责
 *  缺点: 这个静态工厂类负责对所有对象的创建,如果有新的对象增加,或者某些对象的创建方式不同,就需要不断的修改工厂类,不利于后期的为维护
 */

//动物抽象类
abstract class Animal_1 {
	public abstract void eat();
}

//具体狗类
class Dog_1 extends Animal_1 {
	public void eat() {
		System.out.println("狗吃肉");
	}
}

//具体猫类
class Cat_1 extends Animal_1 {
	public void eat() {
		System.out.println("猫吃鱼");
	}
}

//工厂类
class AnimalFactory {
	// 私有构造,不让创建对象
	private AnimalFactory() {
	}

	// 对象的创建
	public static Dog_1 createDog() {
		return new Dog_1();
	}

	public static Cat_1 createCat() {
		return new Cat_1();
	}

	public static Animal_1 createAnimal(String type) {
		if ("Dog_1".equals(type))
			return new Dog_1();
		else if ("Cat_1".equals(type))
			return new Cat_1();
		return null;
	}

}

//测试类
public class 接口_简单工厂模式 {
	public static void main(String[] args) {
		// 具体类调用
		Dog_1 d = new Dog_1();
		Cat_1 c = new Cat_1();
		d.eat();
		c.eat();
		System.out.println("-----------------------------------");

		// 获取对象
		Dog_1 d2 = AnimalFactory.createDog();
		Cat_1 c2 = AnimalFactory.createCat();

		// 调用方法
		d2.eat();
		c2.eat();
		System.out.println("-----------------------------------");

		// 获取对象
		d2 = (Dog_1) AnimalFactory.createAnimal("Dog_1");
		c2 = (Cat_1) AnimalFactory.createAnimal("Cat_1");

		// 调用方法
		d2.eat();
		c2.eat();
		System.out.println("-----------------------------------");

		// 获取未创建的对象
		Animal_1 a = AnimalFactory.createAnimal("Pig");
		if (a != null)
			a.eat();
		else
			System.out.println("对不起,暂时不提供该对象的创建");

	}
}
