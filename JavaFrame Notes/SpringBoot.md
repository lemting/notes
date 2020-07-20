

## 一. SpringBoot 简介

### 1. 优点

1. SpringBoot 使(编码,配置,部署,监控)变简单
2. 创建可以独立运行的 Spring 应用
3. 直接嵌入 Tomcat 或 Jetty 服务器, 不需要打包成 WAR 文件
4. 提供推荐的基础 POM 文件(starter)来简化 Apache Maven 配置
5. 尽可能的根据项目依赖来自动配置 Spring 框架
6. 提供可以直接在生产环境中使用的功能, 如性能指标,应用信息和应用健康检查
7. 开箱即用, 没有代码生成, 也无需 XML 配置. 同时也可以修改默认值来满足特定的需求
8. 其他大量的项目都是基于 SpringBoot 之上的, 如 SpringCloud

### 2. 缺点

1. 依赖太多, 随便一个 SpringBoot 应用都有好几十M
2. 缺少服务的注册和发现等解决方案
3. 缺少监控集成方案,安全管理方案
4. 中文的文档和资料太少且不够深入

### 3. 应用场景

1. Spring 能够应用的场景
2. JavaWeb 应用
3. 微服务



## 二. SpringBoot 快速入门

### 1. Spring4 快速入门

#### 1.1 获取 Bean

##### 1,1,1 简单获取 Bean

> 通过 AnnotationConfigApplicationContext 注册 Bean, 使用对象的 getBean() 方法获取 Bean

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//通过多个容器创建: new AnnotationConfigApplicationContext(Class<?>... componentClass);
//通过扫描多个包创建: new AnnotationConfigApplicationContext(String... basePackages);
//注册多个到容器: context.register(Class<?>... componentClass);
context.register(MyConfig.class); context.refresh(); //注册 MyConfig
//也可以直接 context = new AnnotationConfigApplicationContext(MyConfig.class);
context.getBean(MyBean.class); //通过 Bean 的 class 文件获取
context.getBean("myBean"); //通过 Bean 注解的 name 属性值获取(name值默认为注解的方法的方法名)
```

```java
//Bean 的配置类
@Configuration
public class MyConfig {
    @Bean(name="myBean") //默认 name 为注解的方法的名字
    public MyBean createMyBean() {
        return new MyBean();
    }
}
```

```java
//Bean
public class MyBean {}
```

##### 1.1.2 修改默认单例

>   注册的 Bean 被 Spring 所托管, 获取 Bean 时默认是单例的, 可以用过 @Scope 修改

```java
@Configuration
public class MyConfig {
    @Bean(name="myBean") //默认 name 为注解的方法的名字
    @Scope("prototype") //Bean 非单例
    public MyBean createMyBean() {
        return new MyBean();
    }
}
```

##### 1.1.3 通过实现 FactoryBean 接口的工厂类

> 创建工厂类实现 FactoryBean 接口, 在配置类中注入该工厂类, 在获取 getBean() 时自动获取工厂类的 getObject() 返回的对象

```java
//MyBean 的工厂类, 实现 FactoryBean 接口, 实现 getObject(),getObjectType(),isSingleton() 
public class MyBeanFactoryBean implements FactoryBean<MyBean> {
	//获取 MyBean 对象
	public MyBean getObject() throws Exception {
		return new MyBean();
	}
	//获取 MyBean 的 class
	public Class<?> getObjectType() {
		return MyBean.class;
	}
	//生产的 Bean 是否是单例的, 默认非单例
	public boolean isSingleton() {
		return false;
	}

}
```

```java
@Configuration
public class MyConfig {
    @Bean  //注入工厂类
	public MyBeanFactoryBean createMyBeanFactoryBean() {
		return new MyBeanFactoryBean();
	}
}
```

```java
context.getBean("createMyBeanFactoryBean"); //获取 Factory 生产的 MyBean
context.getBean("&createMyBeanFactoryBean"); //获取 Factory 本身
context.getBean(MyBeanFactoryBean.class); //获取 Factory 本身
```

##### 1.1.4  通过工厂类

> 注册 Bean 的时候如果方法上带参数, Spring 会自动在容器中寻找对应参数的对象, 并自动注入参数

```java
//工厂类
public class CarFactory {
    public Car create() {
        return new Car();
    }
}
```

```java
@Configuration
public class MyConfig {
	@Bean  //注入工厂类
    public CarFactory createCarFactory() {
        return new CarFactory();
    }
    @Bean //注入 Car, Spring 会将容器中的 Bean 注入到对应的参数中
    public Car createCar(CarFactory factory) {
        return factory.create(); //通过工厂类的 create() 获取对象
    }
    
}
```

```java
context.getBean(Car.class); //直接获取 Car
context.getBean("createCar"); //直接获取 Car
```

#### 1.2 指定 Bean 的初始化和销毁方法

##### 1.2.1 实现 InitializingBean,DisposableBean 接口

> 实现 InitializingBean,DisposableBean  接口可以在 Bean 的对应生命周期进行操作

```java
//InitializingBean 接口的 afterPropertiesSet()方法在属性设置完毕后执行
//DisposableBean 接口的 destroy()方法在 Bean 销毁时执行
public class Tom implements InitializingBean, DisposableBean {
	public void afterPropertiesSet() throws Exception {
		System.out.println("===== afterPropertiesSet =====");
	}
	public void destroy() throws Exception {
		System.out.println("===== destory =====");
	}
}
```

##### 1.2.2 在 @Bean 注解中设置

> 通过 @Bean 的 initMethod,destroyMethod 属性指定类的方法, 较为方便

```java
public class Bob {
	public void init() {
		System.out.println("===== init =====");
	}
	public void destroy() {
		System.out.println("===== destory =====");
	}
}
```

```java
@Configuration
public class MyConfig {
    @Bean(initMethod="init",destroyMethod="destroy")//设置 initMethod,destroyMethod 属性
	public Bob createBob() {
		return new Bob();
	}
}
```

##### 1.2.3 使用 @PostConstruct,@PreDestroy

```java
public class City {
    @PostConstruct
	public void init() {
		System.out.println("===== init =====");
	}
    @PreDestroy
	public void destroy() {
		System.out.println("===== destory =====");
	}
}
```

#### 1.3 注册 Bean

##### 1.3.1 通过 @Bean注册

> *见 二.1.1*, @Bean 只能注解在方法上

##### 1.3.2 @Component,@Repository,@Service,@Controller

>  @Component,@Repository,@Service,@Controller 注解在类,接口,枚举,注解上

```java
// @Component: 通用注解, 没有任何特殊含义
// @Repository: 常用于注解 DAO 类, 即持久层
// @Service: 常用于注解 Service 类, 即服务层(业务逻辑层)
// @Controller: 常用于注解 Controller 类, 即控制层(MVC)
@Component("user") //指定 Bean 的 name, 默认为首字母小写的类名
public class User{ }
```

```java
context.register(User.class); context.refresh();
context.getBean(User.class);
context.getBean("user");
```

##### 1.3.3 @Indexed

SpringFramework 5.0 作为 SpringBoot 2.0 的底层核心框架, 就目前已经发布的版本来看, 相对于 Spring Framework 4.x 而言, 注解驱动的性能提升不是那么明显. 然而随着 SpringFramework 注解驱动能能力逐渐受到开发人员的关注, 尤其在 SpringBoot 应用场景中, 大量使用注解 **@CompoentScan** 扫描指定的 package, 当扫描的 package 所包含的类越多时, Spring 模式注解解析的耗时就越长. 对于这个问题, SpringFramework 5.0 版本引入的注解 @Indexed, 为 Spring 模式注解添加索引, 以提升应用启动性能.

```java
@Indexed
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {}
```

但是, 注解 @Indexed 不能孤立地存在, 需要在工程 pom.xml 中增加 ***org.springframework:spring-context-indexer*** 依赖：

```xml
<dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context-indexer</artifactId>
       <optional>true</optional>
</dependency>
```

当工程打包为 JAR 或在 IDE 工具中重新构建后, *METE-INF/spring.components* 文件将自动生成. 换言之, 该文件在编译时生成. 当 Spring 应用上下文执行 *@CompoentScan* 扫描时, *METE-INF/spring.components* 将被 *CandidateComponentsIndexLoader* 读取并加载, 转化为 *CandidateComponentsIndex* 对象, 进而 *@CompoentScan* 不再扫描指定的 *package*, 而是读取 *CandidateComponentsIndex* 对象, 从而达到提升性能的目的. 

#### 1.4 注入 Bean(自动装配)

> @Autowired 注解在字段或字段的 set()方法上, Spring 会自动在容器中寻找匹配的 Bean 自动装配注入

```java
@Repository
public class UserDao {}
```

```java
@Service
public class UserService {
	@Autowired  //自动注入
	private UserDao userDao;
}
```

```java
@Service
public class UserService {
	private UserDao userDao;
	
	@Autowired  //自动注入
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
```

### 2. SpringBoot 快速入门

#### 2.1 创建 Maven 项目, 引入依赖

##### 2.1.1 继承父 Module

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.1.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>    
```

##### 2.1.2 当不能继承父 Module 时

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.3.1.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

##### 2.1.3 引入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```

#### 2.2 配置启动类

> 启动类上添加 @SpringBootApplication 注解, 在 main()方法中创建 SpringApplication 对象并调用 run()

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	//该方法会返回一个 ConfigurableApplicationContext 对象, 继承自 ApplicationContext
        SpringApplication.run(Application.class, args);
        
        //第二种, 设置多个启动源
        SpringApplication app = new SpringApplication(App1.class,App2.class,..);
        app.run(args);
        
        //第三种, 设置多个启动源
        SpringApplication app = new SpringApplication();
        Set<Object> sets = new HashSet<>(); 
        sets.add(App1.class); sets.add(App2.class); ... 
        app.addSources(sets);
        app.run(args);
    }
}
```

##### 2.2.1 @SpringBootApplication 

@SpringBootApplication 注解包含以下注解

1. @ComponentScan: 自动扫描 Bean
2. @EnableAutoConfiguration: 
3. @SpringBootConfiguration: 继承自 @Configuration

@SpringBootApplication 有以下属性

1. exclude: 排除类(通过类的 class)
2. excludeName: 排除类(通过类名)
3. scanBasePackages: 要扫描的包, 默认当前包
4. scanBasePackageClasses: 要扫描的类

##### 2.2.2 SpringApplication.run()

> 静态方法 run() 会创建一个 SpringApplication 对象, 并调用 run() 方法, 返回一个继承自 ApplicationContext 的 ConfigurableApplicationContext  对象
>

```java
//SpringApplication 的构造方法, 指定多个启动源的构造函数
SpringApplication(Class<?>... primarySources);
//SpringApplication 的成员方法, 启动运行并返回 ConfigurableApplicationContext 对象
ConfigurableApplicationContext run(String... args);
//SpringApplication 的静态方法, 创建 SpringApplication 对象并调用 run()方法
static ConfigurableApplicationContext run(Class<?> primarySource, String... args);
```



## 三. SpringBoot 配置文件

### 1. application.properties

> 默认位置 classpath:/application.properties 或者 classpath:/config/application.properties
>

#### 1.1获取配置文件属性

> 1. 获取 Environment, 通过 getProperty() 方法获取属性
>     - 通过 ApplicationContext 获取 Environment
>     - 通过 @Autowired 自动注入 Environment
> 2. 获取 @Value 直接获取
> 3. 通过 @ConfigurationProperties 获取

##### 1.1.1 通过 ApplicationContext 获取 Environment

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
String property = context.getEnvironment().getProperty("local.ip");
```

##### 1.1.2 通过 @Autowired 自动注入 Environment

```java
@Component
public class UserConfig {
    @Autowired
    private Environment env;
    
    //env.getProperty(String key, String defaultValue): 当 key 不存在时,设置值为 default
    public void show() {
        System.out.println("local.ip=" + env.getProperty("local.ip"));
    }
}
```

##### 1.1.3 通过 @Value 直接获取

```java
@Component
public class UserConfig {
    @Value("${local.ip}") //@Value 会根据注解的字段类型, 自动转换成对应的数据类型
    private String localIp;
    @Value("${num:1000}") //${key:default}: 当key不存在时, 设置值为 default
    private Integer num;
    
    public void show() {
        System.out.println("local.ip=" + localIp);
    }
}
```

##### 1.1.4 通过 @ConfigurationProperties 获取

```java
@Component
@ConfigurationProperties(prefix = "db") //设置前缀后,自动设置 前缀.字段名 的属性(需要有set方法)
public class FileConfig {
    private String name;
    private String url;
    getXxx()/setXxx();
}
```



#### 1.2 在配置文件中可以使用 ${} 引用配置文件的其他属性

```properties
name=SpringBoot
app.name=this is ${name}  # this is SpringBoot
```

#### 1.3 修改默认配置文件路径和文件名

##### 1.3.1 修改默认配置文件名

通过设置启动参数 --spring.config.name=xxxxx , 只需要指定文件名, 可以不省略文件扩展名 

##### 1.3.2 修改默认配置文件路径

通过设置启动参数 --spring.config.location=xxxxx,xxxxx,...    可以指定多个路径

### 2. 引用外部配置文件

```java
@Configuration
@PropertySource("classpath:/jdbc.properties") //@PropertySource 一次只能引用一个配置文件
@PropertySource("classpath:/log4j.properties") //@PropertySource 可以使用多个
//@PropertySources({@PropertySource(""),..}) //@PropertySources 可以引用多个配置文件
public class FileConfig {
}
```

```java
@Component
public class JdbcConfig {
    @Value("${url}") //获取 jdbc.properties 配置文件的属性 
    private String url;
}
```

### 3. application.yml

跟 application.properties 类似, 文件格式不一样



## 四. SpringBoot 自动配置

### 1. Condition 接口 和 @Conditional 注解

> 实现 Condition 接口, 实现 matchs()方法
>
> Condition 配合 @Conditional 使用, 在该类的 matches()方法返回 true 时才可以装配
>
> @Conditional({c1.class,c2.class,...}) 注解在方法上, matchs()返回 true 时才装配
>
> @Conditional({c1.class,c2.class,..}) 可以注解在类上, 如果 matchs()返回 false 则都不装配

```java
//EncodingConvert 接口
public interface EncodingConvert {}
//GBKEncodingConvert, EncodingConvert 实现类
public class GBKEncodingConvert implements EncodingConvert {}
//UTF8EncodingConvert, EncodingConvert 实现类
public class UTF8EncodingConvert implements EncodingConvert {}
```

```java
//实现 Condition 接口, 配合 @Conditional 使用, 在该类的 matches()方法返回 true 时才可以装配
public class GBKCondition implements Condition {
    public boolean matches(ConditionContext context,AnnotatedTypeMetadata metadata) {
        String encoding = System.getProperty("file.encoding");
        if(encoding != null) return "gbk".equalsIgnoreCase(encoding);
        return false; //返回 false 代表不能装配
    }
}
//UTF8 Condition
public class UTF8Condition implements Condition {
    public boolean matches(ConditionContext context,AnnotatedTypeMetadata metadata) {
        String encoding = System.getProperty("file.encoding");
        if(encoding != null) return "utf-8".equalsIgnoreCase(encoding);
        return false; 
    }
}

```

```java
@SpringBootConfiguration //Bean配置类
//@Conditional(UTF8Condition.class) 可以注解在类上, 如果 matchs()返回 false 则都不装配
//@Conditional({c1.class,c2.class,..}) 可以指定多个 Condition, 都返回 true 时才可以装配
public class EncodingConvertConfiguration {
    @Bean
    @Conditional(UTF8Condition.class) //指定 Condition, matchs()返回 true 时才装配
    public EncodingConvert createUTF8EncodingConvert() {
        return new UTF8EncodingConvert();
    }

    @Bean
    @Conditional(GBKCondition.class) //指定 Condition, matchs()返回 true 时才装配
    public EncodingConvert createGBKEncodingConvert() {
        return new GBKEncodingConvert();
    }
}
```

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
context.getBeansOfType(EncodingConvert.class);
//在没有指定 Condition 时, 可以获得 GBK 和 UTF8 的对象
//在指定了 Condition 后, 根据 matches()方法的返回值决定装配哪些对象, 返回 false 的不会被装配
```

### 2. SpringBoot 提供的 Condition

在 spring-boot-autoconfigure.jar 中的 org.springframework.boot.autoconfigure.condition 包下, 提供了很多的 Condition  以及配置了 Condition  的 @Conditional 注解

1. @ConditionalOnBean: 容器中有指定的 Bean 时才装配
2. @ConditionalOnMissingBean: 容器中没有指定的 Bean 时才装配
3. @ConditionalOnClass: classpath 有指定的类时才装配
4. @ConditionalOnMissingClass: classpath 没有指定的类时才装配
5. @ConditionalOnJava: 项目 JVM 版本在指定范围的时候才装配
6. @ConditionalOnWebApplication: 项目当前有 Web 环境时装配
7. @ConditionalOnNotWebApplication: 项目当前没有 Web 环境时装配
8. @ConditionalOnResource: 当项目有指定资源的时候才装配
9. @ConditionalOnProperty: 当项目指定配置存在或等于指定值的时候才装配

```java
@SpringBootConfiguration
public class UserConfiguration {
    @Bean //指定当配置文件的属性 runnable.enabled 的属性值为 true 时才装配
    @ConditionalOnProperty(name = "runnable.enabled", havingValue = "true")
    public Runnable createRunnable() { return () -> {}; }
}
```

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
context.getBeansOfType("createRunnable"); //当 runnable.enabled 不为 true 时, 获取不到对象
```



## 五. SpringBoot @Enable* 注解的工作原理

### 1. @Import

> 导入一个或多个 类(会被 Spring 容器托管) 或 配置类(配置类中的 Bean 都会被 Spring 托管)
>
> @Import(Xxx.class)  @Import({x1.class,x2.class,...})

```java
@Import({User.class, TomConfiguration.class}) //导入 Bean, 导入 Bean 配置类
@SpringBootApplication
public class App {
    ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    context.getBean(User.class);
    context.getBean("createTom");
}
```

#### 1.1 ImportSelector 接口

> ImportSelector 接口的 selectImports() 方法, 返回 需要导入容器的类,配置类的包名.类名 字符串数组
>
> 需要配合 @Import 使用

```java
public class MyImportSelector implements ImportSelector {
    //将 User, Tom, CatConfiguration 导入容器 
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //可以获取注解的属性, 传入注解包名.类名, 返回一个 Map<String,Object>, Map<属性名,属性值>
        importingClassMetadata.getAllAnnotationAttributes(EnableLog.class.getName());
        return new String[]{"com.lemting.springboot.User", 
                            Tom.class.getName(), CatConfiguration.class.getName()};
    }
}
```

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)  //导入 MyIBDRegistrar 中配置的需要导入容器的类
public @interface EnableLog {
    String name();
}
```

```java
@SpringBootApplication
@EnableLog //使用自定义注解, 该注解中注册了 Bean
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        context.getBean(User.class); context.getBean("createCat");
    }
}
```

#### 1.2 ImportBeanDefinitionRegistrar 接口

> 在 ImportBeanDefinitionRegistrar 接口的 registerBeanDefinitions()方法中可以使用 BeanDefinitionRegistry 手动注入需要导入到容器的 类,配置类
>
> 需要配合 @Import 使用

```java
public class MyIBDRegistrar implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        	BeanDefinitionRegistry registry) {
        //可以获取注解的属性, 传入注解包名.类名, 返回一个 Map<String,Object>, Map<属性名,属性值>
        importingClassMetadata.getAllAnnotationAttributes(EnableLog.class.getName());
        BeanDefinitionBuilder bdb = 
            					BeanDefinitionBuilder.rootBeanDefinition(User.class);
        bdb.addPropertyValue("age", 16); //可以为 Bean 注入属性值
        registry.registerBeanDefinition("user",bdb.getBeanDefinition());//手动导入到容器
    	BeanDefinitionBuilder bdb2 = 
            	BeanDefinitionBuilder.rootBeanDefinition(CatConfiguration.class);
        registry.registerBeanDefinition("CatConfiguration",bdb2.getBeanDefinition());
    }
}
```

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyIBDRegistrar.class)  //导入 MyIBDRegistrar 中配置的需要导入容器的类
public @interface EnableLog {
    String name();
}
```

```java
@SpringBootApplication
@EnableLog //使用自定义注解, 该注解中注册了 Bean
public class App { ... }
```

### 2. @Enable*

> @Enable* 使用 @Import 导入了配置类

1. @EnableConfigurationProperties: 是用来启用一个特性的, 可以把配置文件的属性注入到 Bean 里面去一般要和 @ConfigurationProperties 一起使用
2. @EnableAsync: 启用异步, 一般要和 @Async 一起使用
3. @Async: 注解在 Runnable 接口的 run()方法上, 可以让该线程异步执行, 没有使用 @EnableAsync 时不生效

#### 2.1 @EnableAutoConfiguration

> 从 classpath 中搜索所有 META-INF/spring.factories 配置文件
>
> 将其中 key 为 org.springframework.boot.autoconfigure.EnableAutoConfiguration 对应的配置项加载到 Spring 容器
>
> spring.boot.enableautoconfiguration: 值为 false 时(默认为 true), 不会启用自动配置

> @EnableAutoConfiguration 的 exclude 属性, 可以通过 class 排除不需要配置的类
>
> @EnableAutoConfiguration 的 excludeName 属性, 可以通过 className 排除不需要配置的类

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.lemting.springboot.User,com.lemting.springboot.Tom,com.lemting.springboot.CatConfiguration
spring.boot.enableautoconfiguration=true
```

```java
@EnableAutoConfiguration(exclude={Tom.class, Dog.class}) //排除 Tom, Dog
@ComponentScan
public class App {
	public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        context.getBean(User.class); context.getBean("createCat");
    }
}
```



## 六. SpringBoot 事件监听

### 1. 事件流程

1. 自定义事件, 一般是继承 ApplicationEvent 抽象类
2. 定义事件监听器, 一般是实现 ApplicationListener 接口
3. 将事件监听器注册到 Spring 容器中 
    - 通过 @Component 等注册
    - 通过 SpringApplication 的 addListeners()方法注册 
    - 通过 application.properties 的配置项 context.listener.classes 注册
    - 通过 spring.factories 的配置项
4. 发布事件, 使用 ApplicationContext 的 publishEvent()方法

```java
//定义事件
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
```

```java
//定义事件监听器
@Component //将事件监听器注册到 Spring 容器中
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent>{
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("接收到事件: " + event.getClass());
    }
}
```

```properties
# application.properties 将事件监听器注册到 Spring 容器
# context.listener.classes=com.lemting.springboot.MyApplicationListener
```

```java
SpringApplication app = new SpringApplication(App.class);
//app.addListeners(new MyApplicationListener()); //将事件监听器注册到 Spring 容器
ConfigurableApplicationContext context = app.run(args);
context.publishEvent(new MyApplicationEvent(new Object())); //发布事件
```

#### 1.1  @EventListener

> @EventListener 注解可以让方法监听事件, 方法的参数任意, 所有的该参数事件或其子事件都可以接收
>
> 需要将类注册到 Spring 容器中管理

```java
//定义事件
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
```

```java
//定义事件监听器, 在方法上使用 @EventListener 注解, 该方法会自动监听参数指定的事件
@Component
public class MyEventHandle {
    @EventListener //注解的方法的参数事件或其子事件都可以接收
    public void event(MyApplicationEvent event) {
        System.out.println("接收到事件: " + event.getClass());
    }
    
    @EventListener //监听 Spring 的事件
    public void event2(ContextClosedEvent event) {
        System.out.println("应用关闭事件: " + event.getClass());
    }
}
```

```java
ConfigurableApplicationContext context = new SpringApplication.run(App.class, args);
context.publishEvent(new MyApplicationEvent(new Object())); //发布事件
```

### 2. Spring 部分事件

1. ContextClosedEvent: Context 关闭事件
2. ContextStoppedEvent: Context 停止事件
3. ContextRefreshedEvent: Context 刷新事件
4. ContextStartedEvent: Context 启动事件

### 3. SpringBoot 部分事件

1. ApplicationFailedEvent: 应用失败事件



## 七. SpringBoot 扩展分析

### 1.  ApplicationContextInitializer 接口

> ApplicationContextInitializer 接口是在 Spring 容器执行 refreshed 之前的一个回调
>
> 使用步骤: 
>
> 1. 写一个类, 实现 ApplicationContextInitializer  接口
> 2. 注册 ApplicationContextInitializer  
>     - 通过 SpringApplication 的 addInitializers() 方法注册
>     - 通过 application.properties 的 context.initializer.classes 注册, 多个用','隔开
>     - 通过 spring.factories 的 org.springframework.context.ApplicationContextInitializer

```java
//实现 ApplicationContextInitializer   接口
public class MyApplicationContextInitializer implements 
    				ApplicationContextInitializer<ConfigurableApplicationContext> {
    //统计 Spring 容器中 Bean 的个数
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("BeanCount:"+applicationContext.getBeanDefinitionCount());
    }
}
```

```properties
# application.properties 注册 ApplicationContextInitializer
# context.initializer.classes=com.lemting.springboot.MyApplicationContextInitializer
```

```properties
# META-INF/spring.factories 注册 ApplicationContextInitializer
#org.springframework.context.ApplicationContextInitializer=com.lemting.springboot.MyApplicationContextInitializer
```

```java
SpringApplication app = new SpringApplication(App.class);
//注册 ApplicationContextInitializer
app.addInitializers(new MyApplicationContextInitializer()); 
ConfigurableApplicationContext context = app.run(args);
context.close();
```

### 2. CommandLineRunner 接口

> CommandLineRunner 接口是在 Spring 容器启动成功后的最后一步回调
>
> 使用步骤: 
>
> 1. 写一个类, 实现 CommandLineRunner 接口
> 2. 注册 CommandLineRunner 
> 3. 多个 CommandLineRunner 可以通过 @Order 注解设置优先级(数值越小, 优先级越高)

```java
@Order(1)
@Component
public class ServerSuccessReport implements CommandLineRunner {
    public void run(String... args) throws Exception {
        System.out.println("==应用已经成功的启动==, 参数为" + Arrays.asList(args));
    }
}
```

```java
@Order(2)
@Component
public class ServerSuccessReportTwo implements CommandLineRunner {
    public void run(String... args) throws Exception {
        System.out.println("==应用已经成功的启动==, 参数为" + Arrays.asList(args));
    }
}
```

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
context.close();
```

### 3. ApplicationRunner 接口

> ApplicationRunner 效果跟 CommandLineRunner 接口一样, 区别在于方法的参数不一样. 
>
> CommandLineRunner 的参数是原始参数, 没有做任何处理;
>
> ApplicationRunner 的参数是对原始参数做了进一步的封装, 可以解析 "--name=value", "--name value"

```java
@Component
public class StarterApplicationRunner implements ApplicationRunner {
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==应用已经成功的启动==, 参数为" +
                           				Arrays.asList(args.getSourceArgs()));
    }
}
```

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
context.close();
```



## 八. SpringBoot 运行流程分析

> SpringBoot 的启动, 是通过创建 SpringApplication 对象, 之后调用对象的 run() 方法
>
> 1. 创建 SpringApplication 对象
> 2. 判断是否是 Web 环境
> 3. 加载所有 classpath:META-INF/spring.factories 的 ApplicationContextInitializer 类
> 4. 加载所有 classpath:META-INF/spring.factories 的 ApplicationListener 类
> 5. 推断 main 方法所在的类
> 6. SpringApplication 对象创建成功, 开始执行 run()方法
> 7. 设置 java.awt.headless 系统变量
> 8. 加载所有 classpath:META-INF/spring.factories 的 SpringApplicationRunListener 类
> 9. 执行所有 SpringApplicationRunListener 的 starting()方法
> 10. 实例化 ApplicationArguments 对象
> 11. 创建 environment, 配置 environment (主要是配置 run()方法的参数);  执行所有 SpringApplicationRunListener 的 environmentPrepared()方法;  如果不是 Web 环境, 但是是 Web 的 environment, 则把 Web 的 environment 转换成标准的 environment
> 12. 输出 Banner
> 13. 创建 ApplicationContext, 如果不是 Web 环境, 则实例化 AnnotationConfigApplicationContext
> 14. 准备 context, 回调所有的 ApplicationContextInitializer 的方法;  执行所有 SpringApplicationRunListener 的 contextPrepared()方法;  往 Spring 容器中注入 ApplicationArguments,Banner;  将所有的源加载到 context 中;  执行所有 SpringApplicationRunListener 的 contextLoaded()方法;
> 15. 执行 context 的 refresh()方法,并且调用 context 的 registerShutdownHook()方法
> 16. 回调,获取容器中所有的 ApplicationRunner,CommandLineRunner 接口,然后排序依次调用

```java
//1. 创建 SpringApplication 对象
//  SpringApplication 的构造方法
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
	...
	//2. 判断是否是 Web 环境
	this.webApplicationType = WebApplicationType.deduceFromClasspath();
	//3.加载所有 classpath:META-INF/spring.factories 的 ApplicationContextInitializer 类
	//并设置到成员变量 List<ApplicationContextInitializer<?>> initializers 中
	this.setInitializers(this.getSpringFactoriesInstances(
    						ApplicationContextInitializer.class));
	//4. 加载所有 classpath:META-INF/spring.factories 的 ApplicationListener 类
	//并设置到成员变量 List<ApplicationListener<?>> listeners 中
	this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class));
	//5. 推断 main 方法所在的类
	this.mainApplicationClass = this.deduceMainApplicationClass();
}
```

```java
//6. 开始执行 run()方法
// SpringApplication 的 run()方法
public ConfigurableApplicationContext run(String... args) {
    ...
    ConfigurableApplicationContext context = null; //run()方法执行后返回的对象
    Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
    //7. 设置 java.awt.headless 系统变量
    this.configureHeadlessProperty();
    //8. 加载所有 classpath:META-INF/spring.factories 的 SpringApplicationRunListener 类
    SpringApplicationRunListeners listeners = this.getRunListeners(args);
    //9. 执行所有 SpringApplicationRunListener 的 starting()方法
    listeners.starting();
    Collection exceptionReporters;
    try {
        //10. 实例化 ApplicationArguments 对象
        ApplicationArguments applicationArguments = 
            								new DefaultApplicationArguments(args);
		//11. 创建 environment, 配置 environment (主要是配置 run()方法的参数)
        //执行所有 SpringApplicationRunListener 的 environmentPrepared()方法
        //如果不是Web环境,但是是Web的environment,则把Web的environment转换成标准的 environment
        ConfigurableEnvironment environment = 
            				this.prepareEnvironment(listeners, applicationArguments);
        
		this.configureIgnoreBeanInfo(environment);
		//12. 输出 Banner
        Banner printedBanner = this.printBanner(environment);
        //13. 创建 ApplicationContext, 如果不是 Web 环境, 则实例化 AnnotationConfigApplicationContext
		context = this.createApplicationContext();
		exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
        //14. 准备 context, 回调所有的 ApplicationContextInitializer 的方法; 
        //执行所有 SpringApplicationRunListener 的 contextPrepared()方法; 往 Spring 容器中注入 ApplicationArguments,Banner; 将所有的源加载到 context 中;  执行所有 SpringApplicationRunListener 的 contextLoaded()方法;
		this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);
        //15. 执行 context 的 refresh()方法,并且调用 context 的 registerShutdownHook()方法
		this.refreshContext(context);
        this.afterRefresh(context, applicationArguments);
        stopWatch.stop();
        if (this.logStartupInfo) {
        	(new StartupInfoLogger(this.mainApplicationClass)).
                logStarted(this.getApplicationLog(), stopWatch);
		}
        listeners.started(context);
        //16. 回调,获取容器中所有的 ApplicationRunner,CommandLineRunner 接口,然后排序依次调用
        this.callRunners(context, applicationArguments);
	} catch (Throwable var10) {
    	this.handleRunFailure(context, var10, exceptionReporters, listeners);
        throw new IllegalStateException(var10);
	}
	try {
    	listeners.running(context);
        return context;
	} catch (Throwable var9) {
    	this.handleRunFailure(context, var9, exceptionReporters, (SpringApplicationRunListeners)null);
        throw new IllegalStateException(var9);
	}
}
```



## 九. SpringBoot Web

> 需要引入 web 依赖

```xml
<dependencies>
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
</dependencies>
```

### 1. 简单的 Controller

> @Controller: 注解在类上, 注册该类到 Spring 容器中
>
> @RestController: 注解在类上, 给 Controller 的方法都加上了 @ResponseBody

```java
@RequestMapping("/user") //指定路由前缀, 所有的 @RequestMapping 注解的方法都会加上该前缀
@Controller  //注册 Bean
public class UserController {
    @RequestMapping("/{id}/home") 		//指定访问路由 /user/{id}/home
    @ResponseBody 						//该方法的返回值直接用于 body 输出
    //将路由的参数 name 传入赋值给 username, 将路由的 {id} 传入赋值给 id
    public String home(@RequestParam(value = "name",defaultValue="")String username,
                      @PathVariable(value = "id", required = false) Integer id){
        return id + "--" + (StringUtils.isEmpty(username) ? "unknow" : username);
    } //访问 "http://localhost:8080/user/1/home?name=admin" 返回 "1--admin"
    
    @RequestMapping("/test")  @ResponseBody
    public String test(HttpServletRequest request,HttpServletResponse response,
			HttpSession session) { //SpringBoot 自动注入 request, response, session
        return "test";
    }
    
    //通过返回值返回页面
    @RequestMapping("/login") //返回渲染后的页面
    public String loginIndex(Model model, ModelMap map) {
        model.addAttribute("username", "root"); //通过 Model.addAttribute() 为页面传值
        map.addAttribute("password", "root"); //通过 ModelMap.addAttribute() 为页面传值
        return "/login.html"; //返回页面路径: spring.mvc.view.prefix + 返回值 + suffix
    }
```

#### 1.1  @RequestMapping

> 注解在方法上可以将该方法注册, 通过 value 指定的路由, 可以访问到该方法
>
> 注解在类上, 可以为该类的方法注册的路由添加路由前缀

属性

1. value: 访问路由
2. method: 访问方式(RequestMethod.GET, RequestMethod.POST, PUT, DELETE, HEAD, OPTIONS,..)

其他衍生注解

1. @GetMapping: 指定 method=RequestMethod.GET 的 @RequestMapping
2. @PostMapping
3. ....

#### 1.2 @RequestParam

> 注解在方法参数上, 可以将路由的参数赋值给注解的参数

属性

1. value: 路由参数的参数名
2. required: 是否必须传入(默认为 true), 为 true 且没有传入参数且没有设置默认值时会报错
3. defaultValue: 设置默认值, 当设置了默认值后, required=ture且没有传入参数的时候, 也会使用该默认值

#### 1.3 @PathVariable

> 注解在方法参数上, 可以将路由中的 {name} 转成成参数赋值给注解的参数

属性

1. value: 路由中 {name} 的 name
2. required: 是否必须传入(默认为 true)

#### 1.4 @ResponseBody

> 注解在方法上, 表示会将方法的返回值直接返回给客户端

### 2. JSP

> SpringBoot 默认不支持 JSP, 需要添加依赖, 使用 JSP 需要如下步骤
>
> 1. 添加 JSP 依赖
> 2. 创建 webapp\WEB-INF\jsp, webapp 与 java,resources 为同级目录
> 3. 设置 web.xml
> 4. 在 application.properties 中添加 JSP 的前缀和后缀

```xml
<dependencies>
	<!-- Tomcat -->
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
        <!-- <scope>provided</scope> --> <!--注意, 这个scope需要删掉-->
    </dependency>
    <!-- JSP 标签库 -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
    </dependency>
</dependencies>
```

```properties
# application.properties
# 页面默认前缀目录
spring.mvc.view.prefix=/WEB-INF/jsp/	
# 页面默认后缀目录
spring.mvc.view.suffix=.jsp				
```

### 3. Thymeleaf

> 使用 Thymeleaf 需要如下步骤
>
> 1. 添加 Thymeleaf 依赖
> 2. 在 classpath:/templates/ 目录下添加 html 文件
> 3. 在 application.properties 中配置 Thymeleaf

```xml
<dependencies> 
    <!-- 导入 Thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <!-- 导入 Thymeleaf Layout (这个按需导入) -->
    <dependency>
        <groupId>nz.net.ultraq.thymeleaf</groupId>
        <artifactId>thymeleaf-layout-dialect</artifactId>
    </dependency>
</dependencies>
```

```properties
# application.properties
# 页面默认前缀目录
spring.thymeleaf.prefix=classpath:/templates/	
# 页面默认模式
spring.thymeleaf.mode=HTML	
# 页面默认编码
spring.thymeleaf.encoding=UTF-8
# 页面默认开启缓存
spring.thymeleaf.cache=true
```

### 4. Jetty 服务器

> SpringBoot 默认使用 Tomcat 服务器, 如果要用 Jetty 服务器, 需要去除 Tomcat 然后导入 Jetty

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- 在 web 依赖中去除 Tomcat 依赖 -->
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <!-- 导入 Jetty 依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
</dependencies>
```

### 5. 静态资源

> org.springframework.boot.autoconfigure.web.ResourceProperties 下定义了静态资源的位置
>
> 在路径位置下的资源可以直接通过路由访问
>
> 默认 classpath:/META-INF/resources/, classpath:/resources/, classpath: /static/, classpath:/public/

#### 5.1  修改默认静态资源路径

```properties
# application.properties
spring.resources.static-locations=classpath:/html/,file:/html/
```

### 6. Servlet, 过滤器, 监听器

> 在 SpringBoot 中使用 servlet 的 API
>
> 1. 编写 Servlet 然后加上相应注解
> 2. 在启动类上添加 @ServletComponentScan 注解

```java
@ServletComponentScan  //扫描并注册 Servlet(Servlet,过滤器,监听器)
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### 6.1 使用 Servlet

> 在 SpringBoot 中使用 Servlet, 要在启动类上添加 @ServletComponentScan 注解, 才能扫描到 Servlet
>
> 在 Servlet 类上使用 @WebServlet 注解

```java
@WebServlet("/user.do") //Servlet 的访问路由
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("asdsadasd"); //
    }
}
```

#### 6.2  Filter 过滤器

> 使用时也需要 @ServletComponentScan 注解, 在过滤器上使用 @WebFilter 注解 

```java
@WebFilter("/*") //过滤器过滤路由
public class UserFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("过滤路由:"+((HttpServletRequest)request).getRequestURI());
        chain.doFilter(request, response);
    }
    public void init(FilterConfig filterConfig) throws ServletException { }
    public void destroy() { }
}
```

#### 6.3 Listener 监听器

> 使用时也需要 @ServletComponentScan 注解, 在过滤器上使用 @WebListener 注解 

```java
@WebListener //监听器
public class UserListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) { //监听启动
        System.out.println("app startup at " + LocalDateTime.now());
    }
}
```

### 7. 拦截器

> 编写拦截器
>
> 1. 创建类, 实现 HandlerInterceptor 接口, 实现 preHandle(), postHandle(), afterCompletion() 方法
> 2. 创建拦截器配置类, 实现 WebMvcConfigurer 接口, 添加 @Configuration 注解
> 3. 在 拦截器配置类 中添加拦截器

```java
public class LogHandlerInterceptor implements HandlerInterceptor {
    //在请求被处理之前调用, 可用于判断是否执行拦截器. 返回值 是否放行(boolean)
    public boolean preHandle(HttpServletRequest request, 
                    HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
    //在请求被处理后, 视图渲染之前调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }
    //在整个请求结束后调用, 一般做资源清理的工作
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { }
}

```

```java
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    private LogHandlerInterceptor logHandlerInterceptor;
    
    // 这个方法是用来配置静态资源的, 比如 html, js, css, 等等
    public void addResourceHandlers(ResourceHandlerRegistry registry) {}
    // 这个方法用来注册拦截器, 我们自己写好的拦截器需要通过这里添加注册才能生效
    public void addInterceptors(InterceptorRegistry registry) { 
        String[] path = { "/" }; //要拦截的路由
        String[] exclude = { "/login","/register","/logout" }; //排除的路由
        //注册拦截器
    	registry.addInterceptor(logHandlerInterceptor)
            					.addPathPatterns(path).excludePathPatterns(exclude);
    }
}
```

### 8. 异常处理

> 去掉 SpringBoot 默认的异常处理逻辑 
>
> ​		@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)

#### 8.1 配置异常跳转页面(避免与全局异常处理一起使用)

> 创建类实现 ErrorPageRegistrar 接口, 实现 registerErrorPages() 方法, 注册错误页面
>
> ErrorPage(HttpStatus status, String path): 指定错误状态码, 和错误跳转路径
>
> ErrorPage(Class<? extends Throwable> exception, String path): 指定异常,异常跳转路径

```java
@Component //加入到 Spring 容器
public class CommonErrorPageRegistrar implements ErrorPageRegistrar {
    public void registerErrorPages(ErrorPageRegistry registry) {
        //指定错误状态码
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"); //错误页路径
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");
        //指定异常
        ErrorPage out = new ErrorPage(IndexOutOfBoundsException.class, "/out.html");
        registry.addErrorPages(e404,e500,out); //注册错误页面
    }
}
```

#### 8.2 局部异常处理

> 可以在 Controller 的方法上添加 @ExceptionHandler 注解, 指定要处理的异常后, 该 Controller 里面触发的指定异常都会被接收并处理

```java
@Controller
public BaseController {
    @ExceptionHandler(Exception.class)  //要处理的异常(如果为 Exception 则代表全部异常)
    @ResponseBody
    public String error(Exception e) {
        return "局部异常处理, 异常信息: " + e.getMessage();
    }
}
```

#### 8.3  全局异常处理

> 创建全局异常处理类, 添加 @ControllerAdvice 注解
>
> 编写异常处理方法, 添加 @ExceptionHandler 注解, 并指定处理的异常
>
> 当有局部异常处理时, 优先使用局部定义的异常处理

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //要处理的异常(如果为 Exception 则代表全部异常)
    @ResponseBody
    public String errorHandler(Exception e) {
        return "全部异常处理, 错误信息: " + e.getMessage();
    }
}
```



## 十. SpringBoot JDBC

> JDBC 配置成功后, Spring 会自动装配好 DataSource 和 JdbcTemplate 对象, 可以直接使用

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
DataSource ds = context.getBean(DataSource.class); //获取 DataSource 对象
JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);//获取 JdbcTemplate 对象
Connection conn = ds.getConnection(); //获取 Connection 连接
System.out.println(conn.getCatalog());
jdbcTemplate.execute("insert user (name) values('1')"); //使用 JdbcTemplate 执行 SQL 语句
```

### 1. 配置 JDBC

> 1. 引入 JDBC 的依赖
> 2. 在 application.properties 配置数据源(需要引入对应数据库的驱动)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
</dependencies>
```

```properties
# application.properties 
# 需要引入对应数据库的驱动
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/<database>?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=<password>
```

### 2. 事务

> @EnableTransactionManagement: 启用事务功能, 注解在启动类上
>
> @Transactional: 启用事务, 注解在需要使用事务的类或方法上
>
> 1. rollbackFor: 指定回滚的异常, 默认运行时异常
> 2. rollbackForClassName: 指定回滚的异常的类名
> 3. noRollbackFor: 排除异常
> 4. noRollbackForClassName: 排除异常的类名
>
> @Transactional 只会生效在注解的方法上, 如果未注解的方法调用注解的方法, 则不会生效
>
> - a() { b(); } //方法 b()注解了, a() 没有注解, 如果 b() 中抛出异常, 则不会回滚; 直接调用 b(), 则会回滚

```java
public class Test {
	@Autowired
    private JdbcTemplate jdbcTemplate; //自动注入 JdbcTemplate
    @Transactional(rollbackFor=Exception.class) //指定异常,在抛出该种异常时回滚,默认运行时异常
    public void add(String... names) throws Exception {
        for(String name: names) {
            jdbcTemplate.execute("insert product (pname) values('"+name+"')");
            if(name.equals("1")) throw new Exception("手动抛出异常");
        }
    }
}
```

```java
@EnableTransactionManagement //开启事务功能
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws Exception {
    	SpringApplication.run(Application.class, args);
        new Test().add("sss", "1","sd");
    }
}
```



## 一. SpringBoot AOP

> application.properties 的 spring.aop.auto 配置项决定是否启用 AOP, 默认为 true
>
> spring.aop.proxy-target-class 配置项决定是否使用 cglib, 默认 false: 使用 JDK 的动态代理
>
> @EnableAspectJAutoProxy 注解启用 AOP 功能
>
> AOP 开发流程
>
> 1. 引入 AOP 依赖
> 2. 写一个 Aspect,  封装横切关注点(日志, 监控等等), 需要配置通知(前置通知, 后置通知等) 和切入点(哪些包的哪些类的哪些方法等等)
> 3.  添加 @Component 或其他注解加入 Spring 容器, 添加 @Aspect 注解

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
</dependencies>
```

```properties
# application.properties
spring.aop.auto=true
```

```java
@Aspect
@Component
public class LogAspect {
    //前置通知, 切入 com.lemting.springboot 包下的所有类的所有方法
    @Before("execution(* com.lemting.springboot..*.*(..))") 
    public void log() {
        System.out.println("method log done");
    }
    @After("execution(* com.lemting.springboot..*.*(..))")
    public void log2(JoinPoint point) { //可以使用 JoinPoint 获取到切入方法的参数
        Objetc[] objs = point.getArgs(); //获取参数值
        point.getSignature().getName(); //获取方法名
    }
}
```

### 1. @Aspect

> AspectJ 切面注解, 拥有 @Aspect 的任何 bean 将被 Spring 自动识别并应用
>
> 用其注解的类可以有方法和字段,他们也可能包括切入点(pointcut),通知(advice)和引入(introduction)声明
>
> 该注解是不能通过类路径自动检测发现的,所以需要配合使用 @Component 等注解
>
> 一个类的 @Aspect 注解标识他为一个切面, 并且将自己从自动代理中排除

### 2. 切入点 Pointcut, 通知 Advice

#### 2.1 切入点 Pointcut

> Pointcut(切入点): 匹配连接点的断言,在 AOP 中通知和一个切入点表达式关联(理解: 要增强的方法)
>
> @Pointcut 注解属性 value 的值
>
> 1. execution: 用于匹配方法执行的连接点
>
>     ​	语法: execution(<访问修饰符>? <返回值类型> <方法名> (<参数>) <异常>)
>
>     - execution(public * *(..)): 切入点为执行所有 public 方法时
>     - execution(* set*(..)): 切入点为执行所有 set 开始的方法时
>     - execution(* com.AccountService.*(..)): 切入点为执行 AccountService 类中的所有方法时
>     - execution(* com.service..(..)): 切入点为执行 com.service 包下的所有方法时
>     - execution(* com.service...(..)): 切入点为执行 com.service 包及其子包下的所有方法时
>
> 2. winthin: 用于匹配指定类型内的方法执行;(only in Spring AOP)
>
> 3. this: 用于匹配当前AOP代理对象类型的执行方法;(only in Spring AOP)
>
> 4. target: 用于匹配当前目标对象类型的执行方法;(only in Spring AOP)
>
> 5. args: 用于匹配当前执行的方法传入的参数为指定类型的执行方法;(only in Spring AOP)

#### 2.2 通知 Advice

> 切面的某个特定的连接点上执行的动作(理解: 对切入点方法增强)
>
> 1. @Before: 前置通知, 在某连接点之前执行的通知,但不能阻止连接点前的执行(除非他抛出一个异常)
> 2. @AfterReturning: 返回后通知, 在连接点正常完成后执行的通知
> 3. @AfterThrowing: 抛出异常后通知, 在方法抛出异常退出时执行的通知
> 4. @After: 后置通知, 在某连接点退出的时候执行的通知(不论是正常返回还是异常退出)
> 5. @Around: 环绕通知, 包围一个连接点的通知(在方法之前,方法之后都通知)
>
> 注解属性
>
> 1. value: 可使用表达式指定,也可以引用已经定义好的 @Pointcut
> 2. returning: @AfterReturning 属性, 存储返回值的变量
> 3. throwing: @AfterThrowing 属性, 存储异常的变量



## 二. SpringBoot Starter

自己开发一个 SpringBoot starter 的步骤

1. 新建一个项目

2. 需要一个配置类, 配置类里面需要装配好需要提供出去的类

3. 加入到 Spring 容器

    - 使用 @Enable, 使用 @Import 导入需要装配的类
    - classpath:/META-INF/spring.factories, 在 org.springframework.boot.autoconfigure.EnableAutoConfiguration 中配置需要装配的类

    

## 三. SpringBoot 日志

> 日志格式: <时间> <日志级别> <进程PID> <线程名> <打印日志所在类> <日志内容>
>
> 2020-07-19 14:39:40.682  INFO 7796 --- [  restartedMain] com.lemting.springbootdemo.Application: Started Application in 0.964 seconds (JVM running for 2.266)

```java
@Component
public class UserDao {
	//org.slf4j.Logger; org.slf4j.LoggerFactory;
    private Logger log = LoggerFactory.getLogger(UserDao.class);
    public void log() {
        log.debug("user dao debug log");
        log.info("user dao info log");
        log.warn("user dao warn log");
        log.error("user dao error log");
    }
}
```

```java
ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
context.getBean(UserDao.class).log(); //调用打印日志的方法
context.close();
```

### 1.  修改日志属性

#### 1.1  修改日志级别

> 在 application.properties 的 logging.level.* 配置项修改日志级别(info, debug, warn, error, off)
>
> logging.level.root: 代表所有的包都修改
>
> logging.level.包: 代表该包下的所有都修改

```properties
# application.properties, 修改全局级别为 info, 修改 com.lemting.controller 包级别为 debug 
logging.level.root=info
logging.level.com.lemting.controller=debug
```

#### 1.2 修改日志输出

> 日志默认输出在控制台, 
>
> - 可以通过 application.properties 的  logging.file.name 配置项指定输出文件
>
> - 也可以通过 logging.file.path 指定输出路径
>
> 通过 logging.file.max-size 修改日志文件最大大小, 默认 10MB, 超过最大容量会分割
>
> 通过 logging.pattern.console 修改控制台日志输出格式
>
> 通过 logging.pattern.file 修改日志文件输出格式

```properties
# application.properties, 修改日志输出 
#logging.file.path=e:/tmp #默认日志输出在 ${logging.file.path}/spring.log
logging.file.name=e:/tmp/my.log
#默认日志文件最大 10MB, 超过 10MB 会分割
logging.file.max-size=10MB 
#修改控制台日志输出格式
logging.pattern.console=
#修改日志文件输出格式
logging.pattern.file=
```

### 2. 使用其他的日志组件

> 步骤
>
> 1. 排除默认的日志组件: spring-boot-starter-logging
> 2. 加入新的日志组件依赖
> 3. 把相应的配置文件放到 classpath 下

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <exclusions>
            <exclusion> <!-- 排除默认日志组件 -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency> <!-- 引入 log4j 日志组件 -->
        <groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
</dependencies>
```



## 四. SpringBoot 监控和度量

> 引入依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-actuator</artifactId>
    </dependency>
</dependencies>
```



## 五. SpringBoot 测试

> 引入依赖

```xml
<dependencies>
	<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope> <!-- 仅在测试范围有效 -->
    </dependency>
</dependencies>
```

```java
@Component
public class UserDao {
    public Integer addUser(String username) {
        System.out.println("add[username: " + username + "]");
        return StringUtils.isEmpty(username) ? 0 : 1;
	}
}
```

```java
@SpringBootTest
public class UserDaoTests {
    @Autowired
    private UserDao userDao;
    @Test //测试普通方法
    public void testAddUser() {
        Assert.assertEquals(Integer.valueOf(1), userDao.addUser("root"));
        Assert.assertEquals(Integer.valueOf(1), userDao.addUser(" "));
        Assert.assertEquals(Integer.valueOf(0), userDao.addUser(""));
        Assert.assertEquals(Integer.valueOf(0), userDao.addUser(null));
    }
}
//第一种测试 Controller
//@SpringBootTest 会加载整个 Spring 容器
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT) //web 环境
public class UserControllerTests {
    @Autowired
    private TestRestTemplate restTemplate; //该对象需要在 web 环境下才会注入
    @Test //测试 Controller
    public void testHome() {
        String content = restTemplate.getForObject("/user/home", String.class);
        Assert.assertEquals("userhome", content);
    }
}
//第二种测试 Controller
//这种方法只能测试 Controller, Controller 里面的依赖需要自己 mock
//@WebMvcTest 不会加载整个 Spring 容器
@WebMvcTest(controllers=UserController.class) //指定要测试的 Controller
public class UserControllerTests2 {
	@Autowired
    private MockMvc mvc;
    @Test //测试 Controller
    public void testHome() {
        mvc.perform(MockMvcRequestBuilders.get("/user/home")) //设置请求路由
            .param("id", "400")	//设置请求参数
            .andExpect(MockMvcResultMatchers.status().isOk()) //要求响应状态码为 200
            .andExpect(MockMvcResultMatchers.content().string("userhome"));//要求响应体
    }
}
```

> 测试环境可以添加配置文件, SpringBoot 会优先使用测试环境的配置文件 
>
> classpath:/application.properties
>
> @TestConfiguration 跟 @Configuration 一样, 但是只有在测试环境才会生效



## 六. SpringBoot 构建微服务

微服务: 把原先的一个大的系统, 拆分成小的系统; 每个小系统分别开发, 测试, 维护

调用方式: 

1. 服务提供的是什么服务, rest(http), web service, rpc

    ​	rest: 使用 RestTemplate.httpclient











## 七. SpringBoot 服务的注册和发现

## 八. SpringBoot 应用的打包和部署



## * 其他

### 1. 文字/图片 Banner

> 在 SpringBoot 开始运行的时候, 会在控制台(默认控制台)打印 Spring 的 ASSIC 字
>
> 可以通过 SpringApplication 的 setBannerMode() 方法修改 Banner 的显示
>
> - Banner.Mode.OFF: 关闭
> - Banner.Mode.CONSOLE: 显示在控制台
> - Banner.Mode.LOG: 显示在日志
>
> 可以在 classpath:/banner.txt 改变 Banner 输出自定义文本内容
>
> 可以通过 application.properties 的 spring.banner.location 配置项修改 Banner 的文件路径
>
> 可以通过 application.properties 的 spring.banner.charset 配置项修改 Banner 的文件编码
>
> 可以通过 application.properties 的 spring.banner.image.location 配置项修改图片 Banner 的文件路径

```properties
# application.properties
spring.banner.location=myBanner.txt
spring.banner.charset=UTF-8
# 可以打印图片转成的字符, 默认 classpath:/banner.jpg
spring.banner.image.location=banner.jpg
```

```txt
// myBanner.txt
佛曰:
	写字楼里写字间, 写字间里程序员;
 	程序人员写程序, 又拿程序换酒钱.
 	酒醒只在网上坐, 酒醉还来网下眠;
 	酒醉酒醒日复日, 网上网下年复年.
 	但愿老死电脑间, 不愿鞠躬老板前;
 	奔驰宝马贵者趣, 公交自行程序员.
 	别人笑我忒疯癫, 我笑自己命太贱;
 	不见满街漂亮妹, 哪个归得程序员?
 //键盘
   ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
  │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
  └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
  ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
  │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
  ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
  │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
  ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
  │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
  ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
  │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
  ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
  │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
  └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
```

```java
SpringApplication app = new SpringApplication(App.class);
app.setBannerMode(Banner.Mode.CONSOLE); //在控制台输出 Banner(默认在控制台输出)
```

```txt
//斜眼笑图片被转成字符输出
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##888888##@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@&&&ooo:::**********:::ooo&&&@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@888::***                      ***::888@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@888::***                      ***::888@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@&&&***                                ***&&&@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@&&***                                      ***&&@@@@@@@@@@@@@@
  @@@@@@@@@@@888**                                            **888@@@@@@@@@@@
  @@@@@@@@@@@888**                                            **888@@@@@@@@@@@
  @@@@@@@@@@@:::**::::::::::::::................::::::::::::::**:::@@@@@@@@@@@
  @@@@@@@@&&&ooo**              :::**......**:::              **ooo&&&@@@@@@@@
  @@@@@@@@&&&ooo**              :::**......**:::              **ooo&&&@@@@@@@@
  @@@@@@@@ooo***##ooo              ::......::ooo###             ...ooo@@@@@@@@
  @@@@@@@@ooo...##ooo              ..******..&&&###                ooo@@@@@@@@
  @@@@@###ooo:::*****:::oo:::......oo......oo......:::oo:::***..:::ooo###@@@@@
  @@@@@###ooo:::*****:::oo:::......oo......oo......:::oo:::***..:::ooo###@@@@@
  @@@@@888:::::::::::*****...******..........******...*****:::::::::::888@@@@@
  @@@@@888**************................................**************888@@@@@
  @@@@@###***......................................................***###@@@@@
  @@@@@###***......................................................***###@@@@@
  @@@@@@@@:::...::***......................................***::...:::@@@@@@@@
  @@@@@@@@ooo...**ooo......................................ooo**...ooo@@@@@@@@
  @@@@@@@@&&&***..&&&***................................***&&&..***&&&@@@@@@@@
  @@@@@@@@&&&***..&&&***................................***&&&..***&&&@@@@@@@@
  @@@@@@@@@@@:::..***&&&................................&&&***..:::@@@@@@@@@@@
  @@@@@@@@@@@888::...:::&&***......................***&&:::...::888@@@@@@@@@@@
  @@@@@@@@@@@@@@&&***...**888ooo***..........***ooo888**...***&&@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@&&***...**888ooo***..........***ooo888**...***&&@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@&&&:::.....:::&&&88&&&&&&88&&&:::.....:::&&&@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@888::***........******........***::888@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@888::***........******........***::888@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@&&&ooo:::**********:::ooo&&&@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@##888888##@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
```

