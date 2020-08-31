## 一. Java 基础语法

### 6. Java 修饰符

Java语言提供了很多修饰符, 主要分为以下两类: 

- 访问修饰符
- 非访问修饰符

修饰符用来定义类,方法或者变量, 通常放在语句的最前端

#### 6.1 访问控制修饰符

Java 中, 可以使用访问控制符来保护对类,变量,方法和构造方法的访问. Java 支持 4 种不同的访问权限

- **default** (即默认, 什么也不写): 在同一包内可见, 不使用任何修饰符. 使用对象: 类,接口,变量,方法
- **private**: 在同一类内可见. 使用对象: 变量,方法.  **注意: 不能修饰类(外部类)**
- **public**: 对所有类可见. 使用对象: 类,接口,变量,方法
- **protected**: 对同一包内的类和所有子类可见. 使用对象: 变量,方法.  **注意: 不能修饰类(外部类)**

|   修饰符    | 当前类 | 同一包内 | 子孙类(同一包) | 子孙类(不同包) | 其他包 |
| :---------: | :----: | :------: | :------------: | :------------: | :----: |
|  `public`   |   Y    |    Y     |       Y        |       Y        |   Y    |
| `protected` |   Y    |    Y     |       Y        |      Y/N       |   N    |
|  `default`  |   Y    |    Y     |       Y        |       N        |   N    |
|  `private`  |   Y    |    N     |       N        |       N        |   N    |

#### 6.2 非访问修饰符

为了实现一些其他的功能, Java 也提供了许多非访问修饰符.

##### 6.2.1 static 修饰符

##### 6.2.5 transient 修饰符

序列化的对象包含被 transient 修饰的实例变量时, Java 虚拟机(JVM)跳过该特定的变量. 

该修饰符包含在定义变量的语句中, 用来预处理类和变量的数据类型.

```java
public transient int limit = 55;   // 不会持久化
public int b; // 持久化
```

##### 6.2.6 volatile 修饰符

volatile 修饰的成员变量在每次被线程访问时, 都强制从共享内存中重新读取该成员变量的值. 而且, 当成员变量发生变化时, 会强制线程将变化值回写到共享内存. 这样在任何时刻, 两个不同的线程总是看到某个成员变量的同一个值. 一个 volatile 对象引用可能是 null

```java
public class MyRunnable implements Runnable {
    private volatile boolean active;
    public void run(){
        active = true;
        while (active){ // 第一行
            // 代码
        }
    }
    public void stop(){
        active = false; // 第二行
    }
}
// 通常情况下, 在一个线程调用 run()方法(在 Runnable 开启的线程), 在另一个线程调用 stop()方法.
// 如果 第一行 中缓冲区的 active 值被使用, 那么在 第二行 的 active 值为 false 时循环不会停止
// 但是以上代码中使用了 volatile 修饰 active, 所以该循环会停止
```

### 7. Java 运算符

计算机的最基本用途之一就是执行数学运算, 作为一门计算机语言, Java也提供了一套丰富的运算符来操纵变量. 我们可以把运算符分成以下几组: 

- 算术运算符
- 关系运算符
- 位运算符
- 逻辑运算符
- 赋值运算符
- 其他运算符

#### 7.1 算术运算符

算术运算符用在数学表达式中, 它们的作用和在数学中的作用一样. 下表列出了所有的算术运算符.

表格中的实例假设整数变量 A 的值为 10, 变量 B 的值为 20: 

| 操作符 |               描述                |        例子        |
| :----: | :-------------------------------: | :----------------: |
|   +    |     加法 - 相加运算符两侧的值     |   A + B 等于 30    |
|   -    |    减法 - 左操作数减去右操作数    |   A – B 等于 -10   |
|   *    |     乘法 - 相乘操作符两侧的值     |    A * B等于200    |
|   /    |    除法 - 左操作数除以右操作数    |     B / A等于2     |
|   ％   | 取余 - 左操作数除以右操作数的余数 |      B%A等于0      |
|   ++   |       自增: 操作数的值增加1       | B++ 或 ++B 等于 21 |
|   --   |       自减: 操作数的值减少1       | B-- 或 --B 等于 19 |

#### 7.2 关系运算符

下表为 Java 支持的关系运算符, A = 10, B = 20

| 运算符 |                             描述                             |     例子      |
| :----: | :----------------------------------------------------------: | :-----------: |
|   ==   |     检查如果两个操作数的值是否相等, 如果相等则条件为真.      | (A == B)为假. |
|   !=   |   检查如果两个操作数的值是否相等, 如果值不相等则条件为真.    | (A != B)为真. |
|   >    |  检查左操作数的值是否大于右操作数的值, 如果是那么条件为真.   |  (A> B)为假.  |
|   <    |  检查左操作数的值是否小于右操作数的值, 如果是那么条件为真.   |  (A <B)为真.  |
|   >=   | 检查左操作数的值是否大于或等于右操作数的值, 如果是那么条件为真. | (A> = B)为假. |
|   <=   | 检查左操作数的值是否小于或等于右操作数的值, 如果是那么条件为真. | (A <= B)为真. |

#### 7.3 位运算符

Java 定义了位运算符, 应用于 int, long, short, char, byte等类型; A = 60, B = 13

| 操作符 |                             描述                             |            例子             |
| :----: | :----------------------------------------------------------: | :-------------------------: |
|   ＆   |            如果相对应位都是1, 则结果为1, 否则为0             | (A＆B), 得到12, 即0000 1100 |
|   \|   |            如果相对应位都是0, 则结果为0, 否则为1             | (A\|B)得到 61, 即 0011 1101 |
|   ^    |            如果相对应位值相同, 则结果为0, 否则为1            | (A ^ B)得到49, 即 0011 0001 |
|   〜   |     按位取反运算符翻转操作数的每一位, 即0变成1, 1变成0.      |  (〜A)得到-61, 即1100 0011  |
|   <<   |     按位左移运算符. 左操作数按位左移右操作数指定的位数.      | A << 2得到240, 即 1111 0000 |
|   >>   |     按位右移运算符. 左操作数按位右移右操作数指定的位数.      |     A >> 2得到15即 1111     |
|  >>>   | 按位右移补零操作符. 左操作数的值按右操作数指定的位数右移, 移动得到的空位以零填充. |        A>>>2得到15即        |

#### 7.4 逻辑运算符

下表列出了逻辑运算符的基本运算, 假设布尔变量A为真, 变量B为假

| 操作符 |                             描述                             |       例子       |
| :----: | :----------------------------------------------------------: | :--------------: |
|   &&   |   称为逻辑与运算符. 当且仅当两个操作数都为真, 条件才为真.    |  (A && B)为假.   |
|  \|\|  | 称为逻辑或操作符. 如果任何两个操作数任何一个为真, 条件为真.  | (A \| \| B)为真. |
|   !    | 称为逻辑非运算符. 用来反转操作数的逻辑状态. 如果条件为true, 则逻辑非运算符将得到false. |  !(A && B)为真.  |

#### 7.5 短路逻辑运算符

&&: 当使用与逻辑运算符时, 在两个操作数都为true时, 结果才为true, 但是当得到第一个操作为false时, 其结果就必定是false, 这时候就不会再判断第二个操作了. 

#### 7.6 赋值运算符

| 操作符 |                             描述                             |              例子               |
| :----: | :----------------------------------------------------------: | :-----------------------------: |
|   =    |        简单的赋值运算符, 将右操作数的值赋给左侧操作数        | C = A + B将把A + B得到的值赋给C |
|  + =   |   加和赋值操作符, 它把左操作数和右操作数相加赋值给左操作数   |     C + = A等价于C = C + A      |
|  - =   |   减和赋值操作符, 它把左操作数和右操作数相减赋值给左操作数   |     C - = A等价于C = C - A      |
|  * =   |   乘和赋值操作符, 它把左操作数和右操作数相乘赋值给左操作数   |     C * = A等价于C = C * A      |
|  / =   |   除和赋值操作符, 它把左操作数和右操作数相除赋值给左操作数   |     C / = A等价于C = C / A      |
| (％)=  | 取模和赋值操作符, 它把左操作数和右操作数取模后赋值给左操作数 |      C％= A等价于C = C％A       |
|  << =  |                       左移位赋值运算符                       |    C << = 2等价于C = C << 2     |
|  >> =  |                       右移位赋值运算符                       |    C >> = 2等价于C = C >> 2     |
|  ＆=   |                       按位与赋值运算符                       |      C＆= 2等价于C = C＆2       |
|  ^ =   |                      按位异或赋值操作符                      |     C ^ = 2等价于C = C ^ 2      |
|  \| =  |                       按位或赋值操作符                       |    C \| = 2等价于C = C \| 2     |

#### 7.7 条件运算符(?:)

条件运算符也被称为三元运算符. 该运算符有3个操作数, 并且需要判断布尔表达式的值. 该运算符的主要是决定哪个值应该赋值给变量. 

```java
variable x = (expression)? value if true : value if false
```

#### 7.8 instanceof 运算符

该运算符用于操作对象实例, 检查该对象是否是一个特定类型(类类型或接口类型)

```java
(Object reference variable)instanceof (class/interface type)
```

#### 7.9 Java 运算符优先级

|   类别   |                   操作符                   |  关联性  |
| :------: | :----------------------------------------: | :------: |
|   后缀   |             ()[] . (点操作符)              |  左到右  |
|   一元   |                 + + - ! ~                  | 从右到左 |
|   乘性   |                   * /％                    |  左到右  |
|   加性   |                    + -                     |  左到右  |
|   移位   |                 >> >>> <<                  |  左到右  |
|   关系   |                 >> = << =                  |  左到右  |
|   相等   |                   == !=                    |  左到右  |
|  按位与  |                     ＆                     |  左到右  |
| 按位异或 |                     ^                      |  左到右  |
|  按位或  |                     \|                     |  左到右  |
|  逻辑与  |                     &&                     |  左到右  |
|  逻辑或  |                    \|\|                    |  左到右  |
|   条件   |                    ? :                     | 从右到左 |
|   赋值   | = + = - = * = / =％= >> = << =＆= ^ = \| = | 从右到左 |
|   逗号   |                     ,                      |  左到右  |

## 二. Java 面向对象

### 4. Java 重写(Override)与重载(Overload)

#### 4.1 重写(Override)

重写是子类对父类的允许访问的方法的实现过程进行重新编写,返回值和形参都不能改变. **外壳不变, 核心重写**

重写的好处在于子类可以根据需要, 定义特定于自己的行为. 也就是说子类能够根据需要实现父类的方法

```java
class Animal{
   public void move(){
      System.out.println("动物可以移动");
   }
}
class Dog extends Animal{
   public void move(){ // 重写父类方法 Override
      System.out.println("狗可以跑和走");
   }
}
public class TestDog{
   public static void main(String args[]){
      Animal a = new Animal(); // Animal 对象
      Animal b = new Dog(); // Dog 对象
      a.move();// 执行 Animal 类的方法
      b.move();//执行 Dog 类的方法
   }
}
```

##### 4.1.1 方法的重写规则

- 参数列表必须完全与被重写方法的相同. 
- 返回类型与被重写方法的返回类型可以不相同, 但是必须是父类返回值的派生类(java5 及更早版本返回类型要一样, java7 及更高版本可以不同). 
- 访问权限不能比父类中被重写的方法的访问权限更低. 例如: 如果父类的一个方法被声明为 public, 那么在子类中重写该方法就不能声明为 protected. 
- 父类的成员方法只能被它的子类重写. 
- 声明为 final 的方法不能被重写. 
- 声明为 static 的方法不能被重写, 但是能够被再次声明. 
- 子类和父类在同一个包中, 那么子类可以重写父类所有方法, 除了声明为 private 和 final 的方法. 
- 子类和父类不在同一个包中, 那么子类只能够重写父类的声明为 public 和 protected 的非 final 方法. 
- 重写的方法能够抛出任何非强制异常, 无论被重写的方法是否抛出异常. 但是, 重写的方法不能抛出新的强制性异常, 或者比被重写方法声明的更广泛的强制性异常, 反之则可以. 
- 构造方法不能被重写. 
- 如果不能继承一个方法, 则不能重写这个方法. 

##### 4.1.2 Super 关键字的使用

当需要在子类中调用父类的被重写方法时, 要使用 super 关键字.

super 关键字是父类的一个实例对象

#### 4.2 重载(Overload)

重载(Overloading)是在一个类里面, 方法名字相同, 而参数不同. 返回类型可以相同也可以不同. 

每个重载的方法(或者构造函数)都必须有一个独一无二的参数类型列表. 

最常用的地方就是构造器的重载. 

##### 4.2.1 重载规则

- 被重载的方法必须改变参数列表(参数个数或类型不一样)
- 被重载的方法可以改变返回类型
- 被重载的方法可以改变访问修饰符
- 被重载的方法可以声明新的或更广的检查异常
- 方法能够在同一个类中或者在一个子类中被重载
- 无法以返回值类型作为重载函数的区分标准

#### 4.3 重写与重载之间的区别

|  区别点  | 重载方法 |                    重写方法                    |
| :------: | :------: | :--------------------------------------------: |
| 参数列表 | 必须修改 |                  一定不能修改                  |
| 返回类型 | 可以修改 |                  一定不能修改                  |
|   异常   | 可以修改 | 可以减少或删除, 一定不能抛出新的或者更广的异常 |
|   访问   | 可以修改 |      一定不能做更严格的限制(可以降低限制)      |

方法的重写(Overriding)和重载(Overloading)是 Java 多态性的不同表现, 重写是父类与子类之间多态性的一种表现, 重载可以理解成多态的具体表现形式. 

1. 方法重载是一个类中定义了多个方法名相同, 而他们的参数的数量不同或数量相同而类型和次序不同, 则称为方法的重载(Overloading)
2. 方法重写是在子类存在方法与父类的方法的名字相同, 而且参数的个数与类型一样, 返回值也一样的方法, 就称为重写(Overriding)
3. 方法重载是一个类的多态性表现, 而方法重写是子类与父类的一种多态性表现

## 三. Java 核心 API

### 8. Java 文档注释

#### 8.1 文档注释

在开始的 /** 之后, 第一行或几行是关于类,变量和方法的主要描述. 

之后, 你可以包含一个或多个各种各样的 **@** 标签. 每一个 **@** 标签必须在一个新行的开始或者在一行的开始紧跟星号(*).

多个相同类型的标签应该放成一组. 例如, 如果你有三个 **@see** 标签, 可以将它们一个接一个的放在一起. 

#### 8.2 Javadoc 标签

|   **标签**    |                        **描述**                        |                           **示例**                           |
| :-----------: | :----------------------------------------------------: | :----------------------------------------------------------: |
|    @author    |                    标识一个类的作者                    |                     @author description                      |
|  @deprecated  |                 指名一个过期的类或成员                 |                   @deprecated description                    |
|  {@docRoot}   |                指明当前文档根目录的路径                |                        Directory Path                        |
|  @exception   |                  标志一个类抛出的异常                  |            @exception exception-name explanation             |
| {@inheritDoc} |                  从直接父类继承的注释                  |      Inherits a comment from the immediate surperclass.      |
|    {@link}    |               插入一个到另一个主题的链接               |                      {@link name text}                       |
| {@linkplain}  |  插入一个到另一个主题的链接, 但是该链接显示纯文本字体  |          Inserts an in-line link to another topic.           |
|    @param     |                   说明一个方法的参数                   |              @param parameter-name explanation               |
|    @return    |                     说明返回值类型                     |                     @return explanation                      |
|     @see      |               指定一个到另一个主题的链接               |                         @see anchor                          |
|    @serial    |                   说明一个序列化属性                   |                     @serial description                      |
|  @serialData  | 说明通过writeObject( ) 和 writeExternal( )方法写的数据 |                   @serialData description                    |
| @serialField  |             说明一个ObjectStreamField组件              |              @serialField name type description              |
|    @since     |               标记当引入一个特定的变化时               |                        @since release                        |
|    @throws    |                 和 @exception标签一样.                 | The @throws tag has the same meaning as the @exception tag.  |
|   {@value}    |         显示常量的值, 该常量必须是static属性.          | Displays the value of a constant, which must be a static field. |
|   @version    |                      指定类的版本                      |                        @version info                         |

### 13. Java 多线程编程

Java 给多线程编程提供了内置的支持.  一条线程指的是进程中一个单一顺序的控制流, 一个进程中可以并发多个线程, 每条线程并行执行不同的任务. 

多线程是多任务的一种特别的形式, 但多线程使用了更小的资源开销. 

这里定义和线程相关的另一个术语 - 进程: 一个进程包括由操作系统分配的内存空间, 包含一个或多个线程. 一个线程不能独立的存在, 它必须是进程的一部分. 一个进程一直运行, 直到所有的非守护线程都结束运行后才能结束. 多线程能满足程序员编写高效率的程序来达到充分利用 CPU 的目的. 

#### 13.1 一个线程的生命周期

线程是一个动态执行的过程, 它也有一个从产生到死亡的过程

`新建` **<u>^start()^</u>**-> `就绪` **<u>^得到处理器资源^</u>**-> `运行` **<u>^stop(),Error,Exception,run()/call()执行完成^</u>**-> `死亡` 

`运行` **<u>^yield()或失去处理器资源^</u>**->`就绪`

`运行` **<u>^sleep(),IO阻塞,等待同步锁,等待通知,suspend()^</u>**-> `阻塞` **<u>^sleep()时间到了,IO方法返回,获得同步锁,收到通知,resume()^</u>**->`就绪`

##### 13.1.1 新建状态

使用 **new** 关键字和 **Thread** 类或其子类建立一个线程对象后, 该线程对象就处于新建状态. 它保持这个状态直到程序 **start()** 这个线程

##### 13.1.2 就绪状态

当线程对象调用了start()方法之后, 该线程就进入就绪状态. 就绪状态的线程处于就绪队列中, 要等待JVM里线程调度器的调度. 

##### 13.1.3 运行状态

如果就绪状态的线程获取 CPU 资源, 就可以执行 **run()**, 此时线程便处于运行状态. 处于运行状态的线程最为复杂, 它可以变为阻塞状态,就绪状态和死亡状态. 

##### 13.1.4 阻塞状态

如果一个线程执行了sleep(睡眠),suspend(挂起)等方法, 失去所占用资源之后, 该线程就从运行状态进入阻塞状态. 在睡眠时间已到或获得设备资源后可以重新进入就绪状态. 可以分为三种: 

- 等待阻塞: 运行状态中的线程执行 wait() 方法, 使线程进入到等待阻塞状态. 
- 同步阻塞: 线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用). 
- 其他阻塞: 通过调用线程的 sleep() 或 join() 发出了 I/O 请求时, 线程就会进入到阻塞状态. 当sleep() 状态超时, join() 等待线程终止或超时, 或者 I/O 处理完毕, 线程重新转入就绪状态. 

##### 13.1.5 死亡状态

一个运行状态的线程完成任务或者其他终止条件发生时, 该线程就切换到终止状态. 

#### 13.2 线程的优先级

每一个 Java 线程都有一个优先级, 这样有助于操作系统确定线程的调度顺序. 

Java 线程的优先级是一个整数, 其取值范围是 1 （Thread.MIN_PRIORITY ） ~  10 （Thread.MAX_PRIORITY ）. 

默认情况下, 每一个线程都会分配一个优先级 NORM_PRIORITY（5）. 

具有较高优先级的线程对程序更重要, 并且应该在低优先级的线程之前分配处理器资源. 但是, 线程优先级不能保证线程执行的顺序, 而且非常依赖于平台. 

#### 13.3 创建线程

Java 提供了三种创建线程的方法

- 通过实现 Runnable 接口
- 通过继承 Thread 类本身
- 通过 Callable 和 Future 创建线程

##### 13.3.1 通过实现 Runnable 接口来创建线程

```java
class RunnableDemo implements Runnable {
	public void run() {
    	System.out.println("hello");
    }
}
public class TestThread {
    public static void main(String args[]) {
    	Thread t = new Thread(new RunnableDemo());
        t.start();
        Thread thread = new Thread(() -> { // lambda 表达式
            System.out.println("hello world");
        });
        thread.start();
    }
}
```

```java
// 实现 Runnable 接口, 定义 Thread 成员变量
class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;
	RunnableDemo( String name) {
      threadName = name;
    }
    public void run() {
    	System.out.println("hello");
    }
    public void start () {
        if(t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
public class TestThread {
    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();
    }  
}
```

##### 13.3.2 通过继承Thread来创建线程

```java
class ThreadDemo extends Thread {
    ThreadDemo(String name) {
        super(name);
    }
    public void run() {
        System.out.println("hello");
    }
}
public class TestThread { 
   public static void main(String args[]) {
      ThreadDemo T1 = new ThreadDemo( "Thread-1");
      T1.start();
      ThreadDemo T2 = new ThreadDemo( "Thread-2");
      T2.start();
   }  
}
```

```java
// 继承 Thread 接口, 定义 Thread 成员变量
class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;

    ThreadDemo( String name) {
        threadName = name;
    }
    public void run() {
        System.out.println("hello");
    }
    public void start () {
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
public class TestThread { 
   public static void main(String args[]) {
      ThreadDemo T1 = new ThreadDemo( "Thread-1");
      T1.start();
      ThreadDemo T2 = new ThreadDemo( "Thread-2");
      T2.start();
   }  
}
```

##### 13.3.3 通过 Callable 和 Future 创建线程

#### 13.4 创建线程的三种方式的对比

- 采用实现 Runnable、Callable 接口的方式创建多线程时, 线程类只是实现了 Runnable 接口或 Callable 接口, 还可以继承其他类. 
- 使用继承 Thread 类的方式创建多线程时, 编写简单, 如果需要访问当前线程, 则无需使用 Thread.currentThread() 方法, 直接使用 this 即可获得当前线程. 

#### 13.5 线程的几个主要概念

在多线程编程时, 你需要了解以下几个概念：

- 线程同步
- 线程间通信
- 线程死锁
- 线程控制：挂起、停止和恢复

#### 13.6 死锁

死锁是这样一种情形：多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。

java 死锁产生的四个必要条件：

1. 互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用
2. 不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
3. 请求和保持，即当资源请求者在请求其他的资源的同时保持对原有资源的占有。
4. 循环等待，即存在一个等待队列：P1占有P2的资源，P2占有P3的资源，P3占有P1的资源。这样就形成了一个等待环路。



### 14.  Java 网络编程

网络编程是指编写运行在多个设备（计算机）的程序, 这些设备都通过网络连接起来. 

java.net 包中提供了两种常见的网络协议的支持：

- **TCP**: 是传输控制协议的缩写, 它保障了两个应用程序之间的可靠通信. 通常用于互联网协议, 被称 TCP/IP. 

- **UDP**: 是用户数据报协议的缩写, 一个无连接的协议. 提供了应用程序之间要发送的数据的数据包. 

#### 14.1 Socket 编程

套接字使用TCP提供了两台计算机之间的通信机制.  客户端程序创建一个套接字, 并尝试连接服务器的套接字





## * 其他

### 1. List 遍历测试(ArrayList, LinkedList)

**ArrayList**: foreach 和 for 差不多 O(n)

**LinkedList**: foreach 较快 O(n), for 非常慢 O(n*(n+1)/2)

```java
public static void main(String[] args) {
    List<Integer> list = new ArrayList<>(); // ArrayList, 数据量: 百万
    for (int i = 0; i < 1_000_000; i++) list.add(i);
    listForEach(list); listForEach2(list); 
    listForEach3(list); listForEach4(list);
    
    List<Integer> list2 = new LinkedList<>(); // LinkedList, 数据量 万
    for (int i = 0; i < 10_000; i++) list2.add(i);
    listForEach(list2); listForEach2(list2); 
    listForEach3(list2); listForEach4(list2);
}
// for
public static void listForEach(List<? extends Object> list) {
    System.out.println("测试普通 for , 数据量: " + list.size());
    long start = System.currentTimeMillis();
    for (int i = 0; i < list.size(); i++) {
        Object o = list.get(i);
        if(o == null) {}
    }
    long end = System.currentTimeMillis();
    System.out.println("测试时间: " + (end - start) + " ms");
}
// foreach
public static void listForEach2(List<? extends Object> list) {
    System.out.println("测试 foreach, 数据量: " + list.size());
    long start = System.currentTimeMillis();
    for (Object o: list) {
        if(o == null) {}
    }
    long end = System.currentTimeMillis();
    System.out.println("测试时间: " + (end - start) + " ms");
}
// iterator
public static void listForEach3(List<? extends Object> list) {
    System.out.println("测试 iterator 迭代器, 数据量: " + list.size());
    long start = System.currentTimeMillis();
    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()) {
        Object o = iterator.next();
        if(o == null) { }
    }
    long end = System.currentTimeMillis();
    System.out.println("测试时间: " + (end - start) + " ms");
}
// foreach lambda
public static void listForEach4(List<? extends Object> list) {
    System.out.println("测试 foreach lambda , 数据量: " + list.size());
    long start = System.currentTimeMillis();
    list.forEach((o) -> {
        if(o == null) {}
    });
    long end = System.currentTimeMillis();
    System.out.println("测试时间: " + (end - start) + " ms");
}
```

### 2. Map 遍历测试(HashMap, TreeMap)

**HashMap**: 遍历速度快于 TreeMap, Map.Entry 快于 KeySet, O(1)

**TreeMap**: Map.Entry 快于 KeySet, O(log n)

```java
public static void main(String[] args) {
    Map<Integer, Integer> map = new HashMap<>(); // HashMap, 数据量 千万
    for (int i = 0; i < 10_000_000; i++)
        map.put(i, i);
    mapForEach(map); mapForEach2(map);

    Map<Integer, Integer> map2 = new TreeMap<>(); // TreeMap, 数据量 千万
    for (int i = 0; i < 10_000_000; i++)
        map2.put(i, i);
    mapForEach(map2); mapForEach2(map2);
}
// KeySet
public static void mapForEach(Map<? extends Object, ? extends Object> map) {
    System.out.println("测试 KeySet , 数据量: " + map.size());
    long start = System.currentTimeMillis();
    for (Object key : map.keySet()) {
        if(key != null) {
            Object value = map.get(key);
            if(value == null) {}
        }
    }
    long end = System.currentTimeMillis();
    System.out.println("测试时间: " + (end - start) + " ms");
}
// Map.Entry
public static void mapForEach2(Map<? extends Object, ? extends Object> map) {
    System.out.println("测试 Map.Entry , 数据量: " + map.size());
    long start = System.currentTimeMillis();
    for (Map.Entry<?, ?> entry : map.entrySet()) {
        if(entry != null) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if(key == null) {}
            if(value == null) {}
        }
    }
    long end = System.currentTimeMillis();
    System.out.println("测试时间: " + (end - start) + " ms");
}
```

