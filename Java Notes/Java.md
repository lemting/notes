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

Java 线程的优先级是一个整数, 其取值范围是 1 (Thread.MIN_PRIORITY ) ~  10 (Thread.MAX_PRIORITY ). 

默认情况下, 每一个线程都会分配一个优先级 NORM_PRIORITY(5). 

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

- 采用实现 Runnable,Callable 接口的方式创建多线程时, 线程类只是实现了 Runnable 接口或 Callable 接口, 还可以继承其他类. 
- 使用继承 Thread 类的方式创建多线程时, 编写简单, 如果需要访问当前线程, 则无需使用 Thread.currentThread() 方法, 直接使用 this 即可获得当前线程. 

#### 13.5 线程的几个主要概念

在多线程编程时, 你需要了解以下几个概念: 

- 线程同步
- 线程间通信
- 线程死锁
- 线程控制: 挂起,停止和恢复

#### 13.6 死锁

死锁是这样一种情形: 多个线程同时被阻塞, 它们中的一个或者全部都在等待某个资源被释放. 由于线程被无限期地阻塞, 因此程序不可能正常终止. 

java 死锁产生的四个必要条件: 

1. 互斥使用, 即当资源被一个线程使用(占有)时, 别的线程不能使用
2. 不可抢占, 资源请求者不能强制从资源占有者手中夺取资源, 资源只能由资源占有者主动释放. 
3. 请求和保持, 即当资源请求者在请求其他的资源的同时保持对原有资源的占有. 
4. 循环等待, 即存在一个等待队列: P1占有P2的资源, P2占有P3的资源, P3占有P1的资源. 这样就形成了一个等待环路. 



### 14.  Java 网络编程

网络编程是指编写运行在多个设备(计算机)的程序, 这些设备都通过网络连接起来. 

java.net 包中提供了两种常见的网络协议的支持: 

- **TCP**: 是传输控制协议的缩写, 它保障了两个应用程序之间的可靠通信. 通常用于互联网协议, 被称 TCP/IP. 

- **UDP**: 是用户数据报协议的缩写, 一个无连接的协议. 提供了应用程序之间要发送的数据的数据包. 

#### 14.1 Socket 编程

套接字使用TCP提供了两台计算机之间的通信机制.  客户端程序创建一个套接字, 并尝试连接服务器的套接字





## 四. JVM

### 1. Java 类加载机制

#### 1.1 什么是类的加载

类的加载指的是将类的 .class 文件中的二进制数据读入到内存中, 将其放在运行时数据区的方法区内, 然后在堆区创建一个 `java.lang.Class`对象, 用来封装类在方法区内的数据结构. 类的加载的最终产品是位于堆区中的 `Class`对象,  `Class`对象封装了类在方法区内的数据结构, 并且向Java程序员提供了访问方法区内的数据结构的接口. 

类加载器并不需要等到某个类被“首次主动使用”时再加载它, JVM 规范允许类加载器在预料某个类将要被使用时就预先加载它, 如果在预先加载的过程中遇到了 .class 文件缺失或存在错误, 类加载器必须在程序首次主动使用该类时才报告错误(LinkageError 错误)如果这个类一直没有被程序主动使用, 那么类加载器就不会报告错误	

**加载.class文件的方式**

- 从本地系统中直接加载
- 通过网络下载.class文件
- 从zip, jar等归档文件中加载.class文件
- 从专有数据库中提取.class文件
- 将Java源文件动态编译为.class文件

#### 1.2 类的生命周期

> 加载(Loding) -> 验证(Verification) -> 准备(Preparation) -> 解析(Resolution) -> 初始化(Initialization)
>
> ​	-> 使用(Using) -> 卸载(Unloading)

其中类加载的过程包括了加载,验证,准备,解析,初始化五个阶段. 在这五个阶段中, 加载,验证,准备和初始化这四个阶段发生的顺序是确定的, 而解析阶段则不一定, 它在某些情况下可以在初始化阶段之后开始, 这是为了支持Java语言的运行时绑定(也成为动态绑定或晚期绑定). 另外注意这里的几个阶段是按顺序开始, 而不是按顺序进行或完成, 因为这些阶段通常都是互相交叉地混合进行的, 通常在一个阶段执行的过程中调用或激活另一个阶段. 

##### 1.2.1 加载

查找并加载类的二进制数据加载时类加载过程的第一个阶段, 在加载阶段, 虚拟机需要完成以下三件事情: 

- 通过一个类的全限定名来获取其定义的二进制字节流. 
- 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构. 
- 在Java堆中生成一个代表这个类的 `java.lang.Class`对象, 作为对方法区中这些数据的访问入口. 

相对于类加载的其他阶段而言, 加载阶段(准确地说, 是加载阶段获取类的二进制字节流的动作)是可控性最强的阶段, 因为开发人员既可以使用系统提供的类加载器来完成加载, 也可以自定义自己的类加载器来完成加载. 

加载阶段完成后, 虚拟机外部的二进制字节流就按照虚拟机所需的格式存储在方法区之中, 而且在 Java 堆中也创建一个 `java.lang.Class`类的对象, 这样便可以通过该对象访问方法区中的这些数据. 

##### 1.2.2 连接 - 验证: 确保被加载的类的正确性

验证是连接阶段的第一步, 这一阶段的目的是为了确保 Class 文件的字节流中包含的信息符合当前虚拟机的要求, 并且不会危害虚拟机自身的安全. 验证阶段大致会完成4个阶段的检验动作: 

- **文件格式验证**: 验证字节流是否符合Class文件格式的规范；例如: 是否以 `0xCAFEBABE`开头,主次版本号是否在当前虚拟机的处理范围之内,常量池中的常量是否有不被支持的类型. 
- **元数据验证**: 对字节码描述的信息进行语义分析(注意: 对比javac编译阶段的语义分析), 以保证其描述的信息符合Java语言规范的要求；例如: 这个类是否有父类, 除了 `java.lang.Object`之外. 
- **字节码验证**: 通过数据流和控制流分析, 确定程序语义是合法的,符合逻辑的. 
- **符号引用验证**: 确保解析动作能正确执行. 

验证阶段是非常重要的, 但不是必须的, 它对程序运行期没有影响, 如果所引用的类经过反复验证, 那么可以考虑采用 `-Xverifynone`参数来关闭大部分的类验证措施, 以缩短虚拟机类加载的时间. 

##### 1.2.3 连接 - 准备: 为类的 `静态变量`分配内存, 并将其初始化为默认值

准备阶段是正式为类变量分配内存并设置类变量初始值的阶段, 这些内存都将在方法区中分配. 对于该阶段有以下几点需要注意: 

1. 这时候进行内存分配的仅包括类变量(static), 而不包括实例变量, 实例变量会在对象实例化时随着对象一块分配在Java堆中. 

2. 这里所设置的初始值通常情况下是数据类型默认的零值(如 0,0L,null,false 等), 而不是被在 Java 代码中被显式地赋予的值. 

假设一个类变量的定义为:  `public static int value = 3;`

那么变量value在准备阶段过后的初始值为0, 而不是3, 因为这时候尚未开始执行任何Java方法, 而把value赋值为3的 `publicstatic`指令是在程序编译后, 存放于类构造器 `<clinit>()`方法之中的, 所以把value赋值为3的动作将在初始化阶段才会执行. 

> 这里还需要注意如下几点: 
>
> - 对基本数据类型来说, 对于类变量(static)和全局变量, 如果不显式地对其赋值而直接使用, 则系统会为其赋予默认的零值, 而对于局部变量来说, 在使用前必须显式地为其赋值, 否则编译时不通过. 
> - 对于同时被static和final修饰的常量, 必须在声明的时候就为其显式地赋值, 否则编译时不通过；而只被final修饰的常量则既可以在声明时显式地为其赋值, 也可以在类初始化时显式地为其赋值, 总之, 在使用前必须为其显式地赋值, 系统不会为其赋予默认零值. 
> - 对于引用数据类型reference来说, 如数组引用,对象引用等, 如果没有对其进行显式地赋值而直接使用, 系统都会为其赋予默认的零值, 即null. 
> - 如果在数组初始化时没有对数组中的各元素赋值, 那么其中的元素将根据对应的数据类型而被赋予默认的零值. 

3. 如果类字段的字段属性表中存在 `ConstantValue`属性, 即同时被final和static修饰, 那么在准备阶段变量value就会被初始化为ConstValue属性所指定的值. 

假设上面的类变量value被定义为:  `public static final int value=3`；

编译时Javac将会为value生成ConstantValue属性, 在准备阶段虚拟机就会根据 `ConstantValue`的设置将value赋值为3. 我们可以理解为static final常量在编译期就将其结果放入了调用它的类的常量池中

##### 1.2.4 连接 - 解析: 把类中的符号引用转换为直接引用

解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程, 解析动作主要针对类或接口,字段,类方法,接口方法,方法类型,方法句柄和调用点限定符7类符号引用进行. 符号引用就是一组符号来描述目标, 可以是任何字面量. 

直接引用就是直接指向目标的指针,相对偏移量或一个间接定位到目标的句柄. 

##### 1.2.5 初始化

初始化, 为类的静态变量赋予正确的初始值, JVM负责对类进行初始化, 主要对类变量进行初始化. 在Java中对类变量进行初始值设定有两种方式: 

1. 声明类变量是指定初始值
2. 使用静态代码块为类变量指定初始值

JVM初始化步骤

1. 假如这个类还没有被加载和连接, 则程序先加载并连接该类
2. 假如该类的直接父类还没有被初始化, 则先初始化其直接父类
3. 假如类中有初始化语句, 则系统依次执行这些初始化语句

类初始化时机: 只有当对类的主动使用的时候才会导致类的初始化, 类的主动使用包括以下六种: 

- 创建类的实例, 也就是new的方式
- 访问某个类或接口的静态变量, 或者对该静态变量赋值
- 调用类的静态方法
- 反射 (如 `Class.forName("com.shengsiyuan.Test")`)
- 初始化某个类的子类, 则其父类也会被初始化
- Java虚拟机启动时被标明为启动类的类( `JavaTest`), 直接使用 `java.exe`命令来运行某个主类

##### 1.2.6 结束生命周期

在如下几种情况下, Java 虚拟机将结束生命周期

- 执行了 `System.exit()`方法
- 程序正常执行结束
- 程序在执行过程中遇到了异常或错误而异常终止
- 由于操作系统出现错误而导致Java虚拟机进程终止

#### 1.3  类加载器

寻找类加载器, 先来一个小例子

```java
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }
}
```

运行输出结果

```
sun.misc.Launcher$AppClassLoader@18b4aac2
sun.misc.Launcher$ExtClassLoader@1540e19d
null
```

从上面的结果可以看出, 并没有获取到 `ExtClassLoader`的父 Loader, 原因是 `BootstrapLoader`(引导类加载器)是用C语言实现的, 找不到一个确定的返回父 Loader 的方式, 于是就返回 null. 

##### 1.3.1 类加载器的分类

这几种类加载器的层次关系如下所示: 

> 启动类加载器(BootstrapClassLoader) <- 扩展类加载器(ExtClassLoder) <- 应用类加载器(AppClassLoder) <- 自定义类加载器(User ClassLoder)
>
> 注意: 这里父类加载器并不是通过继承关系来实现的, 而是采用组合实现的

站在Java虚拟机的角度来讲, 只存在两种不同的类加载器: 

1. **启动类加载器**: 它使用C++实现(这里仅限于 Hotspot, 也就是JDK1.5之后默认的虚拟机, 有很多其他的虚拟机是用Java语言实现的), 是虚拟机自身的一部分；
2. **所有其它的类加载器**: 这些类加载器都由 Java 语言实现, 独立于虚拟机之外, 并且全部继承自抽象类 `java.lang.ClassLoader`, 这些类加载器需要由启动类加载器加载到内存中之后才能去加载其他的类. 

站在Java开发人员的角度来看, 类加载器可以大致划分为以下三类: 

1. **启动类加载器**:  `BootstrapClassLoader`, 负责加载存放在 `JDK\jre\lib`(JDK代表JDK的安装目录, 下同)下, 或被 `-Xbootclasspath`参数指定的路径中的, 并且能被虚拟机识别的类库(如rt.jar, 所有的java.开头的类均被 `BootstrapClassLoader`加载). 启动类加载器是无法被 Java 程序直接引用的. 
2. **扩展类加载器**:  `ExtensionClassLoader`, 该加载器由 `sun.misc.Launcher$ExtClassLoader`实现, 它负责加载 `JDK\jre\lib\ext`目录中, 或者由 `java.ext.dirs`系统变量指定的路径中的所有类库(如javax.开头的类), 开发者可以直接使用扩展类加载器. 
3. **应用程序类加载器**:  `ApplicationClassLoader`, 该类加载器由 `sun.misc.Launcher$AppClassLoader`来实现, 它负责加载用户类路径(ClassPath)所指定的类, 开发者可以直接使用该类加载器, 如果应用程序中没有自定义过自己的类加载器, 一般情况下这个就是程序中默认的类加载器. 

应用程序都是由这三种类加载器互相配合进行加载的, 如果有必要, 我们还可以加入自定义的类加载器. 因为JVM自带的 ClassLoader 只是懂得从本地文件系统加载标准的 java class 文件, 因此如果编写了自己的 ClassLoader, 便可以做到如下几点: 

1. 在执行非置信代码之前, 自动验证数字签名. 
2. 动态地创建符合用户特定需要的定制化构建类. 
3. 从特定的场所取得 java class, 例如数据库中和网络中. 

##### 1.3.2 JVM 类加载机制

1. **全盘负责**, 当一个类加载器负责加载某个 Class 时, 该 Class 所依赖的和引用的其他 Class 也将由该类加载器负责载入, 除非显示使用另外一个类加载器来载入
2. **父类委托**, 先让父类加载器试图加载该类, 只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
3. **缓存机制**, 缓存机制将会保证所有加载过的 Class 都会被缓存, 当程序中需要使用某个 Class 时, 类加载器先从缓存区寻找该 Class, 只有缓存区不存在, 系统才会读取该类对应的二进制数据, 并将其转换成 Class 对象, 存入缓存区. 这就是为什么修改了 Class 后, 必须重启JVM, 程序的修改才会生效

#### 1.4 类的加载

类加载有三种方式: 

1. 命令行启动应用时候由 JVM 初始化加载
2. 通过 Class.forName() 方法动态加载
3. 通过 ClassLoader.loadClass() 方法动态加载

```java
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = Test.class.getClassLoader();
        System.out.println(loader);
        // 使用 ClassLoader.loadClass() 来加载类, 不会执行初始化块
        //loader.loadClass("Test2");
        // 使用 Class.forName() 来加载类, 默认会执行初始化块
        Class.forName("Test2");
        // 使用 Class.forName() 来加载类, 并指定ClassLoader, 初始化时不执行静态块
        //Class.forName("Test2", false, loader);
    }
}
public class Test2 {
    static {
        System.out.println("静态初始化块执行了！");
    }
}
```

**Class.forName() 和 ClassLoader.loadClass()区别**

- `Class.forName()`: 将类的 .class 文件加载到jvm中之外, 还会对类进行解释, 执行类中的static块；
- `ClassLoader.loadClass()`: 只干一件事情, 就是将 .class 文件加载到 jvm 中, 不会执行 static 中的内容,只有在 newInstance 才会去执行 static 块. 
- `Class.forName(name,initialize,loader)`: 带参函数也可控制是否加载 static 块. 并且只有调用了newInstance() 方法采用调用构造函数, 创建类的对象 . 

#### 1.5 双亲委派模型

双亲委派模型的工作流程是：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上，因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器中，只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类。

双亲委派机制:

1. 当 `AppClassLoader`加载一个 class 时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器`ExtClassLoader`去完成。
2. 当 `ExtClassLoader`加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给`BootStrapClassLoader`去完成。
3. 如果 `BootStrapClassLoader`加载失败（例如在 `$JAVA_HOME/jre/lib`里未查找到该class），会使用 `ExtClassLoader`来尝试加载；
4. 若`ExtClassLoader`也加载失败，则会使用 `AppClassLoader`来加载，如果 `AppClassLoader`也加载失败，则会报出异常 `ClassNotFoundException`。

ClassLoader 源码分析: 

```java
public Class<?> loadClass(String name)throws ClassNotFoundException {
    return loadClass(name, false);
}

protected Class<?> loadClass(String name, boolean resolve) 
    										throws ClassNotFoundException {
    synchronized(this.getClassLoadingLock(name)) {
        Class c = this.findLoadedClass(name); // 首先判断该类型是否已经被加载
        if (c == null) {
            long var5 = System.nanoTime();
            //如果没有被加载, 就委托给父类加载或者委派给启动类加载器加载
            try {
                if (this.parent != null) { // 如果存在父类加载器, 就委派给父类加载器加载
                    c = this.parent.loadClass(name, false); 
                } else { // 如果不存在父类加载器, 就检查是否是由启动类加载器加载的类
                    c = this.findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
            }
            if (c == null) {
                long var7 = System.nanoTime();
                // 如果父类加载器和启动类加载器都不能完成加载任务, 才调用自身的加载功能
                c = this.findClass(name); 
                PerfCounter.getParentDelegationTime().addTime(var7 - var5);
                PerfCounter.getFindClassTime().addElapsedTimeFrom(var7);
                PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            this.resolveClass(c);
        }
        return c;
    }
}
```

双亲委派模型意义：

- 系统类防止内存中出现多份同样的字节码
- 保证Java程序安全稳定运行

#### 1.6 自定义类加载器

通常情况下，我们都是直接使用系统类加载器。但是，有的时候，我们也需要自定义类加载器。比如应用是通过网络来传输 Java类的字节码，为保证安全性，这些字节码经过了加密处理，这时系统类加载器就无法对其进行加载，这样则需要自定义类加载器来实现。自定义类加载器一般都是继承自 `ClassLoader`类，从上面对 `loadClass`方法来分析来看，我们只需要重写 findClass 方法即可。下面我们通过一个示例来演示自定义类加载器的流程：

```java
public class MyClassLoader extends ClassLoader {
    private String root;

    protected Class findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar
            + className.replace('.', File.separatorChar) + ".class";
        try { // 读取字节码文件
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() { return root;  }
    public void setRoot(String root) { this.root = root; }

    public static void main(String[] args)  {
        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("E:\\temp");
        Class testClass = null;
        try {
            testClass = classLoader.loadClass("com.classloader.Test2");
            Object object = testClass.newInstance();
            System.out.println(object.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
```

自定义类加载器的核心在于对字节码文件的获取，如果是加密的字节码则需要在该类中对文件进行解密。由于这里只是演示，并未对 class 文件进行加密，因此没有解密的过程。这里有几点需要注意：

1. 这里传递的文件名需要是类的全限定性名称，即 `com.paddx.test.classloading.Test`格式的，因为 defineClass 方法是按这种格式进行处理的。
2. 最好不要重写loadClass方法，因为这样容易破坏双亲委托模式。
3. 这类Test 类本身可以被 `AppClassLoader`类加载，因此我们不能把 `com/paddx/test/classloading/Test.class`放在类路径下。否则，由于双亲委托机制的存在，会直接导致该类由 `AppClassLoader`加载，而不会通过我们自定义类加载器来加载。

### 2. JVM 内存结构

JVM 内存结构主要有三大块：**堆内存**、**方法区**和**栈**。堆内存是JVM中最大的一块由年轻代和老年代组成，而年轻代内存又被分成三部分，**Eden空间**、**From Survivor空间**、**To Survivor空间**,默认情况下年轻代按照**8:1:1**的比例来分配；

方法区存储类信息、常量、静态变量等数据，是线程共享的区域，为与 Java 堆区分，方法区还有一个别名Non-Heap(非堆)；栈又分为 java 虚拟机栈和本地方法栈主要用于方法的执行。

控制参数

- -Xms设置堆的最小空间大小。
- -Xmx设置堆的最大空间大小。
- -XX:NewSize设置新生代最小空间大小。
- -XX:MaxNewSize设置新生代最大空间大小。
- -XX:PermSize设置永久代最小空间大小。
- -XX:MaxPermSize设置永久代最大空间大小。
- -Xss设置每个线程的堆栈大小。

没有直接设置老年代的参数，但是可以设置堆空间大小和新生代空间大小两个参数来间接控制。

> 老年代空间大小=堆空间大小-年轻代大空间大小

方法区和对是所有线程共享的内存区域；而java栈、本地方法栈和程序员计数器是运行是线程私有的内存区域。

#### 2.1 堆 (Heap)

对于大多数应用来说，Java 堆（Java Heap）是Java虚拟机所管理的内存中**最大**的一块。Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，**几乎所有的对象实例都在这里分配内存**。

Java堆是垃圾收集器管理的主要区域，因此很多时候也被称做"**GC堆**"。如果从内存回收的角度看，由于现在收集器基本都是采用的分代收集算法，所以 Java 堆中还可以细分为：**新生代和老年代；再细致一点的有Eden空间、From Survivor空间、To Survivor空间等。**

根据 Java 虚拟机规范的规定，Java 堆可以处于物理上不连续的内存空间中，只要逻辑上是连续的即可，就像我们的磁盘空间一样。在实现时，既可以实现成固定大小的，也可以是可扩展的，不过当前主流的虚拟机都是按照可扩展来实现的（通过-Xmx和-Xms控制）。

如果在堆中没有内存完成实例分配，并且堆也无法再扩展时，将会抛出 OutOfMemoryError 异常。

#### 2.2 方法区 (Method Area)

方法区（Method Area）与 Java 堆一样，是各个线程共享的内存区域，**它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。**虽然 Java 虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫做 Non-Heap（非堆），目的应该是与 Java 堆区分开来.

对于习惯在 HotSpot 虚拟机上开发和部署程序的开发者来说，很多人愿意把方法区称为“永久代”（Permanent Generation），本质上两者并不等价，仅仅是因为 HotSpot 虚拟机的设计团队选择把 GC 分代收集扩展至方法区，或者说使用永久代来实现方法区而已。

Java 虚拟机规范对这个区域的限制非常宽松，除了和 Java 堆一样不需要连续的内存和可以选择固定大小或者可扩展外，还可以选择不实现垃圾收集。相对而言，垃圾收集行为在这个区域是比较少出现的，但并非数据进入了方法区就如永久代的名字一样"永久"存在了。这个区域的内存回收目标主要是针对常量池的回收和对类型的卸载，一般来说这个区域的回收"成绩"比较难以令人满意，尤其是类型的卸载，条件相当苛刻，但是这部分区域的回收确实是有必要的。

根据Java虚拟机规范的规定，当方法区无法满足内存分配需求时，将抛出OutOfMemoryError异常。

方法区有时被称为持久代（PermGen）。

所有的对象在实例化后的整个运行周期内，都被存放在堆内存中。堆内存又被划分成不同的部分：伊甸区(Eden)，幸存者区域(Survivor Sapce)，老年代（Old Generation Space）。

方法的执行都是伴随着线程的。原始类型的本地变量以及引用都存放在线程栈中。而引用关联的对象比如String，都存在在堆中.

#### 2.3 程序计数器（Program Counter Register）

程序计数器（Program Counter Register）是一块较小的内存空间，它的作用可以看做是当前线程所执行的字节码的行号指示器。在虚拟机的概念模型里（仅是概念模型，各种虚拟机可能会通过一些更高效的方式去实现），字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。

由于 Java 虚拟机的多线程是通过线程轮流切换并分配处理器执行时间的方式来实现的，在任何一个确定的时刻，一个处理器（对于多核处理器来说是一个内核）只会执行一条线程中的指令。因此，为了线程切换后能恢复到正确的执行位置，每条线程都需要有一个独立的程序计数器，各条线程之间的计数器互不影响，独立存储，我们称这类内存区域为“线程私有”的内存。

如果线程正在执行的是一个 Java 方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行的是 Natvie 方法，这个计数器值则为空（Undefined）。

**此内存区域是唯一一个在 Java 虚拟机规范中没有规定任何 OutOfMemoryError 情况的区域。**

#### 2.4 JVM 栈（JVM Stacks）

与程序计数器一样，Java 虚拟机栈（Java Virtual Machine Stacks）也是线程私有的，**它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的内存模型：**每个方法被执行的时候都会同时创建一个栈帧（Stack Frame）用于存储局部变量表、操作栈、动态链接、方法出口等信息。**每一个方法被调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。**

局部变量表存放了编译期可知的各种基本数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference 类型，它不等同于对象本身，根据不同的虚拟机实现，它可能是一个指向对象起始地址的引用指针，也可能指向一个代表对象的句柄或者其他与此对象相关的位置）和 returnAddress 类型（指向了一条字节码指令的地址）。

其中64位长度的long和double类型的数据会占用2个局部变量空间（Slot），其余的数据类型只占用1个。局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在帧中分配多大的局部变量空间是完全确定的，在方法运行期间不会改变局部变量表的大小。

在 Java 虚拟机规范中，对这个区域规定了两种异常状况：如果线程请求的栈深度大于虚拟机所允许的深度，将抛出 StackOverflowError 异常；如果虚拟机栈可以动态扩展（当前大部分的 Java 虚拟机都可动态扩展，只不过Java 虚拟机规范中也允许固定长度的虚拟机栈），当扩展时无法申请到足够的内存时会抛出 OutOfMemoryError异常。

#### 2.5 本地方法栈（Native Method Stacks）

本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别不过是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而**本地方法栈则是为虚拟机使用到的Native方法服务。**虚拟机规范中对本地方法栈中的方法使用的语言、使用方式与数据结构并没有强制规定，因此具体的虚拟机可以自由实现它。甚至有的虚拟机（譬如Sun HotSpot虚拟机）直接就把本地方法栈和虚拟机栈合二为一。与虚拟机栈一样，本地方法栈区域也会抛出StackOverflowError和OutOfMemoryError异常。

#### 2.6 OutOfMemoryError

对内存结构清晰的认识同样可以帮助理解不同 OutOfMemoryErrors：

```
对象不能被分配到堆内存中
Exception in thread “main”: java.lang.OutOfMemoryError: Java heap space

类或者方法不能被加载到老年代。它可能出现在一个程序加载很多类的时候，比如引用了很多第三方的库
Exception in thread “main”: java.lang.OutOfMemoryError: PermGen space

创建的数组大于堆内存的空间
Exception in thread “main”: java.lang.OutOfMemoryError: Requested array size exceeds VM limit

分配本地分配失败。JNI、本地库或者Java虚拟机都会从本地堆中分配内存空间
Exception in thread “main”: java.lang.OutOfMemoryError: request <size> bytes for <reason>. Out of swap space?

同样是本地方法内存分配失败，只不过是JNI或者本地方法或者Java虚拟机发现
Exception in thread “main”: java.lang.OutOfMemoryError: <reason> <stack trace>(Native method)
```



### 3. JVM GC算法 垃圾收集器

垃圾收集 Garbage Collection 通常被称为“GC”，它诞生于1960年 MIT 的 Lisp 语言，经过半个多世纪，目前已经十分成熟了。 jvm 中，程序计数器、虚拟机栈、本地方法栈都是随线程而生随线程而灭，栈帧随着方法的进入和退出做入栈和出栈操作，实现了自动的内存清理，因此，我们的内存垃圾回收主要集中于 java 堆和方法区中，在程序运行期间，这部分内存的分配和使用都是动态的.

#### 3.1 对象存活判断

判断对象是否存活一般有两种方式：

**引用计数**：每个对象有一个引用计数属性，新增一个引用时计数加1，引用释放时计数减1，计数为0时可以回收。此方法简单，无法解决对象相互循环引用的问题。

**可达性分析**（Reachability Analysis）：从GC Roots开始向下搜索，搜索所走过的路径称为引用链。当一个对象到GC Roots没有任何引用链相连时，则证明此对象是不可用的。不可达对象。

在Java语言中，GC Roots包括: 

- 虚拟机栈中引用的对象。
- 方法区中类静态属性实体引用的对象。
- 方法区中常量引用的对象。
- 本地方法栈中JNI引用的对象。

#### 3.2 垃圾收集算法

##### 3.2.1 标记 -清除算法

“标记-清除”（Mark-Sweep）算法，如它的名字一样，算法分为“标记”和“清除”两个阶段：首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象。之所以说它是最基础的收集算法，是因为后续的收集算法都是基于这种思路并对其缺点进行改进而得到的。

它的主要缺点有两个：一个是效率问题，标记和清除过程的效率都不高；另外一个是空间问题，标记清除之后会产生大量不连续的内存碎片，空间碎片太多可能会导致，当程序在以后的运行过程中需要分配较大对象时无法找到足够的连续内存而不得不提前触发另一次垃圾收集动作。

##### 3.2.2 复制算法

“复制”（Copying）的收集算法，它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉。

这样使得每次都是对其中的一块进行内存回收，内存分配时也就不用考虑内存碎片等复杂情况，只要移动堆顶指针，按顺序分配内存即可，实现简单，运行高效。只是这种算法的代价是将内存缩小为原来的一半，持续复制长生存期的对象则导致效率降低。

##### 3.2.3 标记-压缩算法

复制收集算法在对象存活率较高时就要执行较多的复制操作，效率将会变低。更关键的是，如果不想浪费50%的空间，就需要有额外的空间进行分配担保，以应对被使用的内存中所有对象都100%存活的极端情况，所以在老年代一般不能直接选用这种算法。

根据老年代的特点，有人提出了另外一种“标记-整理”（Mark-Compact）算法，标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存

##### 3.2.4 分代收集算法

GC分代的基本假设：绝大部分对象的生命周期都非常短暂，存活时间短。

“分代收集”（Generational Collection）算法，把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。而老年代中因为对象存活率高、没有额外空间对它进行分配担保，就必须使用“标记-清理”或“标记-整理”算法来进行回收。

#### 3.3 垃圾收集器

如果说收集算法是内存回收的方法论，垃圾收集器就是内存回收的具体实现

##### 3.3.1 Serial 收集器

串行收集器是最古老，最稳定以及效率高的收集器，可能会产生较长的停顿，只使用一个线程去回收。新生代、老年代使用串行回收；新生代复制算法、老年代标记-压缩；垃圾收集的过程中会Stop The World（服务暂停）

参数控制： `-XX:+UseSerialGC` 串行收集器

ParNew 收集器 ParNew 收集器其实就是 Serial 收集器的多线程版本。新生代并行，老年代串行；新生代复制算法、老年代标记-压缩

参数控制：

`-XX:+UseParNewGC` ParNew收集器

`-XX:ParallelGCThreads` 限制线程数量

##### 3.3.2 Parallel 收集器

Parallel Scavenge收集器类似ParNew收集器，Parallel收集器更关注系统的吞吐量。可以通过参数来打开自适应调节策略，虚拟机会根据当前系统的运行情况收集性能监控信息，动态调整这些参数以提供最合适的停顿时间或最大的吞吐量；也可以通过参数控制GC的时间不大于多少毫秒或者比例；新生代复制算法、老年代标记-压缩

参数控制： `-XX:+UseParallelGC` 使用Parallel收集器+ 老年代串行

##### 3.3.3 Parallel Old 收集器

Parallel Old是Parallel Scavenge收集器的老年代版本，使用多线程和“标记－整理”算法。这个收集器是在JDK 1.6中才开始提供

参数控制： `-XX:+UseParallelOldGC` 使用Parallel收集器+ 老年代并行

##### 3.3.4 CMS 收集器

CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。目前很大一部分的Java应用都集中在互联网站或B/S系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。

从名字（包含“Mark Sweep”）上就可以看出CMS收集器是基于“标记-清除”算法实现的，它的运作过程相对于前面几种收集器来说要更复杂一些，整个过程分为4个步骤，包括：

- 初始标记（CMS initial mark）
- 并发标记（CMS concurrent mark）
- 重新标记（CMS remark）
- 并发清除（CMS concurrent sweep）

其中初始标记、重新标记这两个步骤仍然需要“Stop The World”。初始标记仅仅只是标记一下GC Roots能直接关联到的对象，速度很快，并发标记阶段就是进行GC Roots Tracing的过程，而重新标记阶段则是为了修正并发标记期间，因用户程序继续运作而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段稍长一些，但远比并发标记的时间短。

由于整个过程中耗时最长的并发标记和并发清除过程中，收集器线程都可以与用户线程一起工作，所以总体上来说，CMS收集器的内存回收过程是与用户线程一起并发地执行。老年代收集器（新生代使用ParNew）

**优点**: 并发收集、低停顿

**缺点**: 产生大量空间碎片、并发阶段会降低吞吐量

参数控制：

`-XX:+UseConcMarkSweepGC` 使用CMS收集器

`-XX:+ UseCMSCompactAtFullCollection` Full GC后，进行一次碎片整理；整理过程是独占的，会引起停顿时间变长

`-XX:+CMSFullGCsBeforeCompaction` 设置进行几次Full GC后，进行一次碎片整理

`-XX:ParallelCMSThreads` 设定CMS的线程数量（一般情况约等于可用CPU数量）

##### 3.3.5 G1 收集器

G1是目前技术发展的最前沿成果之一，HotSpot开发团队赋予它的使命是未来可以替换掉JDK1.5中发布的CMS收集器。与CMS收集器相比G1收集器有以下特点：

1. **空间整合**，G1收集器采用标记整理算法，不会产生内存空间碎片。分配大对象时不会因为无法找到连续空间而提前触发下一次GC。
2. **可预测停顿**，这是G1的另一大优势，降低停顿时间是G1和CMS的共同关注点，但G1除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为N毫秒的时间片段内，消耗在垃圾收集上的时间不得超过N毫秒，这几乎已经是实时Java（RTSJ）的垃圾收集器的特征了。

上面提到的垃圾收集器，收集的范围都是整个新生代或者老年代，而G1不再是这样。使用G1收集器时，Java堆的内存布局与其他收集器有很大差别，它将整个Java堆划分为多个大小相等的独立区域（Region），虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔阂了，它们都是一部分（可以不连续）Region的集合。

G1的新生代收集跟ParNew类似，当新生代占用达到一定比例的时候，开始出发收集。和CMS类似，G1收集器收集老年代对象会有短暂停顿。

收集步骤：

1. 标记阶段，首先初始标记(Initial-Mark),这个阶段是停顿的(Stop the World Event)，并且会触发一次普通Mintor GC。对应GC log:GC pause (young) (inital-mark)
2. Root Region Scanning，程序运行过程中会回收survivor区(存活到老年代)，这一过程必须在young GC之前完成。
3. Concurrent Marking，在整个堆中进行并发标记(和应用程序并发执行)，此过程可能被young GC中断。在并发标记阶段，若发现区域对象中的所有对象都是垃圾，那个这个区域会被立即回收(图中打X)。同时，并发标记过程中，会计算每个区域的对象活性(区域中存活对象的比例)。
4. Remark, 再标记，会有短暂停顿(STW)。再标记阶段是用来收集 并发标记阶段 产生新的垃圾(并发阶段和应用程序一同运行)；G1中采用了比CMS更快的初始快照算法:snapshot-at-the-beginning (SATB)。
5. Copy/Clean up，多线程清除失活对象，会有STW。G1将回收区域的存活对象拷贝到新区域，清除Remember Sets，并发清空回收区域并把它返回到空闲区域链表中。
6. 复制/清除过程后。回收区域的活性对象已经被集中回收到深蓝色和深绿色区域。

##### 3.3.6 常用的收集器组合

|       | 新生代GC策略      | 老年老代GC策略 | 说明                                                         |
| :---- | :---------------- | :------------- | ------------------------------------------------------------ |
| 组合1 | Serial            | Serial Old     | Serial和Serial Old都是单线程进行GC，特点就是GC时暂停所有应用线程。 |
| 组合2 | Serial            | CMS+Serial Old | CMS（Concurrent Mark Sweep）是并发GC，实现GC线程和应用线程并发工作，不需要暂停所有应用线程。另外，当CMS进行GC失败时，会自动使用Serial Old策略进行GC。 |
| 组合3 | ParNew            | CMS            | 使用 `-XX:+UseParNewGC`选项来开启。ParNew是Serial的并行版本，可以指定GC线程数，默认GC线程数为CPU的数量。可以使用-XX:ParallelGCThreads选项指定GC的线程数。如果指定了选项 `-XX:+UseConcMarkSweepGC`选项，则新生代默认使用ParNew GC策略。 |
| 组合4 | ParNew            | Serial Old     | 使用 `-XX:+UseParNewGC`选项来开启。新生代使用ParNew GC策略，年老代默认使用Serial Old GC策略。 |
| 组合5 | Parallel Scavenge | Serial Old     | Parallel Scavenge策略主要是关注一个可控的吞吐量：应用程序运行时间 / (应用程序运行时间 + GC时间)，可见这会使得CPU的利用率尽可能的高，适用于后台持久运行的应用程序，而不适用于交互较多的应用程序。 |
| 组合6 | Parallel Scavenge | Parallel Old   | Parallel Old是Serial Old的并行版本                           |
| 组合7 | G1GC              | G1GC           | `-XX:+UnlockExperimentalVMOptions` `-XX:+UseG1GC` #开启； `-XX:MaxGCPauseMillis=50` #暂停时间目标； `-XX:GCPauseIntervalMillis=200` #暂停间隔目标； `-XX:+G1YoungGenSize=512m` #年轻代大小； `-XX:SurvivorRatio=6` #幸存区比例 |



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

| 排序方法 | 平均时间复杂度 | 最坏时间复杂度 | 平均空间复杂度 | 稳定性 |
| :------: | :------------: | :------------: | :------------: | :----: |
| 插入排序 |    O(n^2^)     |    O(n^2^)     |      O(1)      |  稳定  |
| 希尔排序 |   O(n^1-ε^)    |    O(n^2^)     |      O(1)      | 不稳定 |
| 冒泡排序 |    O(n^2^)     |    O(n^2^)     |      O(1)      |  稳定  |
| 快速排序 |  O(nlog~2~n)   |    O(n^2^)     |   O(log~2~n)   | 不稳定 |
| 选择排序 |    O(n^2^)     |    O(n^2^)     |      O(1)      | 不稳定 |
|  堆排序  |  O(nlog~2~n)   |  O(nlog~2~n)   |      O(1)      | 不稳定 |
| 归并排序 |  O(nlog~2~n)   |  O(nlog~2~n)   |      O(n)      |  稳定  |

