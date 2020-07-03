package Java_多线程.设计模式;

/*
 *  工厂方法模式
 *  	工厂方法模式中抽象工厂类负责定义创建对象的接口,具体对象的创建工作由继承抽象工厂类的具体类实现
 *  
 *  优点: 客户端不需要再负责对象的创建,从而明确了各个类的职责.如果有新的对象增加,只需要增加一个具体的类和具体的工厂类即可,不影响已有代码,后期维护容易,增加了系统的扩展性
 *  缺点: 需要额外的编写代码,增加了工作量
 */

//工厂接口
interface Factory {
	public abstract Animal_2 createAnimal_2();
}

//动物抽象类
abstract class Animal_2 {
	public abstract void eat();
}

//具体狗类
class Dog_2 extends Animal_2 {
	public void eat() {
		System.out.println("狗吃肉");
	}
}

//具体猫类
class Cat_2 extends Animal_2 {
	public void eat() {
		System.out.println("猫吃鱼");
	}
}

//狗工厂类
class DogFactory implements Factory {
	public Animal_2 createAnimal_2() {
		return new Dog_2();
	}
}

//猫工厂类
class CatFactory implements Factory {
	public Animal_2 createAnimal_2() {
		return new Cat_2();
	}
}

public class 接口_工厂方法模式 {
	public static void main(String[] args) {
		// 获取狗对象
		Factory f = new DogFactory();
		Animal_2 a = f.createAnimal_2();
		a.eat();
		System.out.println("-------------------------");

		// 获取猫对象
		Factory f2 = new CatFactory();
		Animal_2 a2 = f2.createAnimal_2();
		a2.eat();

	}
}
