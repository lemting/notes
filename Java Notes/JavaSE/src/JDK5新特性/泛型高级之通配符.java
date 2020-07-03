package JDK5新特性;

import java.util.ArrayList;
import java.util.Collection;

/*
 * 泛型通配符 
 * 		<?>
 * 			任意类型,如果没有明确,那么就是Object以及任意Java类
 * 		<? extends E>
 * 			向下限定,E及其子类
 * 		<? super E>
 * 			向上限定,E及其父类
 */

class Animal{}
class Dog extends Animal{}
class Cat extends Animal{}

public class 泛型高级之通配符 {
	public static void main(String[] args) {
		//泛型如果明确写的时候,前后必须一致
		Collection<Object> c1 = new ArrayList<Object>();
		//Collection<Object> c2 = new ArrayList<String>();
		//Collection<Object> c3 = new ArrayList<Animal>();
		//Collection<Object> c4 = new ArrayList<Dog>();
		
		//<?>  任意类型
		Collection<?> c5 = new ArrayList<Object>();
		Collection<?> c6 = new ArrayList<Animal>();
		Collection<?> c7 = new ArrayList<Dog>();
		Collection<?> c8 = new ArrayList<Cat>();
		
		//<? extends E> 向下限定,E及其子类
		//Collection<? extends Animal> c9 = new ArrayList<Object>();
		Collection<? extends Animal> c10 = new ArrayList<Animal>();
		Collection<? extends Animal> c11 = new ArrayList<Dog>();
		Collection<? extends Animal> c12 = new ArrayList<Cat>();
		
		//<? super E> 向上限定,E及其父类
		Collection<? super Animal> c13 = new ArrayList<Object>();
		Collection<? super Animal> c14 = new ArrayList<Animal>();
		//Collection<? super Animal> c15 = new ArrayList<Dog>();
		//Collection<? super Animal> c16 = new ArrayList<Cat>();
		System.out.println(c1.toString() + c5 + c6 + c7 + c8 + c10 + c11 + c12 + c13 + c14);
	}
}
