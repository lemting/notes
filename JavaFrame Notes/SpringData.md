## 一. SpringData 简介

### 1. SpringData 应用场景

> SpringData 包含多个子项目: 
>
> - Spring Data JPA
> - Spring Data MongoDB
> - Spring Data Redis
> - Spring Data Solr
>
> - ...

## 二. 传统 JDBC

> 传统 JDBC 代码重复度太高

> 获取 Connection
>
> 获取 Statement
>
> 通过 Statement 执行 SQL 语句, 并返回 ResultSet
>
> 遍历 ResultSet 结果集

```java
/**
 * JDBC 工具类
 *  1. 获取 Connection
 *  2. 释放 DB 相关的资源(ResultSet,Statement,Connection)
 */
public class JDBCUtils {
    //获取 Connection
    public static Connection getConnection() throws Exception {
        InputStream inputStream = JDBCUtils.class.getClassLoader() 
            					.getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(inputStream); //加载配置文件
        Class.forName(properties.getProperty("jdbc.driver"));
        Connection conn = DriverManager.getConnection( //获取 Connection
            					properties.getProperty("jdbc.url")
                                ,properties.getProperty("jdbc.username")                                             ,properties.getProperty("jdbc.password"));
        return conn;
    }
    //释放 DB 相关的资源
    public static void release(ResultSet resultSet, 
                               Statement statement, Connection connection) {
        if(resultSet != null)
            try { resultSet.close();} catch (SQLException e) { e.printStackTrace(); }
        if(statement != null)
            try { statement.close();} catch (SQLException e) { e.printStackTrace(); }
        if(connection != null)
            try { connection.close();} catch (SQLException e) { e.printStackTrace();}
    }
}
```

```java
public class StudentDao {
    //查询全部 Student
    public List<Student> getList() {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select id,name,age from student";
        try {
            conn = JDBCUtils.getConnection(); //通过工具类获取 Connection
            statement = conn.prepareStatement(sql); //注入 SQL, 获取 Statement
            resultSet = statement.executeQuery(); //执行 SQL, 获取 ResultSet
            while (resultSet.next()) //遍历 ResultSet 结果集
                list.add(new Student(resultSet.getInt("id") //将数据添加到集合
                		, resultSet.getString("name"), resultSet.getInt("age")));
        } catch (SQLException e) { 
            e.printStackTrace();
        } finally { //最后释放资源
            JDBCUtils.release(resultSet, statement, conn);
        }
        return list;
    }
    //添加 Student
    public int save(Student student) {
        Connection conn = null;
        PreparedStatement statement = null;
        String sql = "insert into student(name,age) values(?,?)";
        try {
            conn = JDBCUtils.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, student.getName()); //设置参数
            statement.setInt(2, student.getAge()); //设置参数
            int row = statement.executeUpdate(); //执行 SQL
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(null, statement, conn);
        }
        return 0;
    }
}
```

## 三. Spring JDBC

> SpringBoot 引入 JDBC 依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
</dependencies>
```

> SpringBoot 在 application.properties 配置了数据源后, 会自动将 DataSource 和 JdbcTemplate 纳入 Spring 容器(使用 Spring 需要手动配置 DataSource 和 JdbcTemplate )
>
> 使用 JdbcTemplate 执行 SQL

```java
@Repository //纳入 Spring 容器管理
public class StudentDao  {
    @Autowired //注入 JdbcTemplate
    private JdbcTemplate jdbcTemplate;
    //查询全部 Student
    public List<Student> getList() {
        List<Student> list = new ArrayList<>();
        String sql = "select id,name,age from student";
        //执行 SQL
        jdbcTemplate.query(sql, (rs) -> { //每一行数据的回调
            list.add(new Student(rs.getInt("id") 
                                 	, rs.getString("name"), rs.getInt("age")));
        });
        return list;
    }
    //添加 Student
    public int save(Student student) {
        String sql = "insert into student(name,age) values(?,?)";
        //设置参数并执行 SQL
        int row = jdbcTemplate.update(sql, student.getName(), student.getAge());
        return row;
    }
}
```



## 四. SpringData JDBC

> SpringBoot 引入SpringData  JDBC 依赖, 引入对应数据库的驱动

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
</dependencies>
```





## 五. SpringData JPA

**可以 Dao 层接口可以继承 JpaSpecificationExecutor 和 JpaRepository 接口** 

> SpringBoot 引入SpringData  JPA依赖, 引入对应数据库的驱动

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>	
```

```properties
# application.properties 
####################### 配置数据源 #######################
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=lemting
####################### 配置 JPA #######################
# 数据库方言
spring.jpa.database=mysql
# 是否显示 SQL
spring.jpa.show-sql=true
# 格式化 SQL
spring.jpa.properties.hibernate.format_sql=true
# 没有表则自动创建(实体类在数据库没有对应表时, 自动创建数据表)
spring.jpa.hibernate.ddl-auto=update
# 自增主键
spring.jpa.hibernate.use-new-id-generator-mappings=true
```

```java
@EntityScan //开启扫描 @Entity 注解的实体类
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

### 1. Entity 实体类

> 配置实体类
>
> 可以使用 @Entity, @Id, @Column 等注解

```java
@Entity(name="employee") //标识实体类,name 属性指定数据表名,默认为类名转换成的小写下划线字符串
public class Employee {
    @Id //注解主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置自增
   	@Column(length=8)
    private Integer id;
    @Column(name="name",length=20,nullable=false) //注解其他字段
    private String name;
    @Column //注解其他字段
    private Integer age;

    getXxx()/setXxx();
}
```

#### 1.1 @EntityScan

> 注解在启动类上, 自动扫描 @Entity 注解的实体类

#### 1.2 @Entity

> @Entity 注解在实体类上, 标识该实体类对应数据表
>
> name 属性可以指定数据表名, 默认为类名转换成的小写下划线字符串

#### 1.3 @Column 

> @Column 注解在字段 或 字段的 get 方法上, 标识该字段对应数据表的列
>
> @Column 属性
>
> 1. name: 列名, 默认字段名转换成的小写下划线字符串
> 2. unique: 唯一, 默认 false
> 3. nullable: 是否可以为 NULL, 默认 true
> 4. length: 长度
> 5. precision,scale: 精度
> 6. insertable: 使用 INSERT 脚本插入数据时, 是否需要插入该字段的值
> 7. updatable: 在使用 UPDATE 脚本插入数据时, 是否需要更新该字段的值

#### 1.4 @Id

> @Id 注解在字段 或 字段的 get 方法上, 标识该字段为主键

#### 1.5 @GeneratedValue(strategy = GenerationType.IDENTITY)

> 设置字段自增

### 2. Repository 接口实现 CRUD

> Repository 接口是 SpringData 的核心接口, 不提供任何方法

> 编写接口, 进行下面的操作(都可以实现, 任选一个)
>
> - 继承 Repository<T,ID> 接口, T 为操作的实体类, ID 为主键的类型
> - 在接口上使用 @RepositoryDefinition(domainClass = 实体类.class, idClass = 主键类.class)

```java
//继承 Repository<T,ID> 接口, T 为操作的实体类, ID 为主键的类型
public interface UserRepository extends Repository<User, Integer>  {
    //方法......
}

//使用 @RepositoryDefinition 注解
@RepositoryDefinition(domainClass=Employee.class,idClass=Integer.class)
public interface EmployeeRepository {
	//方法......
}
```

#### 2.1  遵循命名 JPA 自动实现

> 在接口里面声明方法, 只需要规范方法名和参数, JPA 自动帮你实现
>
> **方法命名: 语句类型  +[Distinct]+[Top|First]+ By + 字段+条件+ [{And|Or} + ...]+ [OrderBy ..]**
>
> **语句类型**
>
> - 查询: find, get. query, read
>
> - 添加: 
>
> - 删除: remove, delete
>
> - 修改: 
>
> - 其他: count
>
> Distinct: 去重;  First: 取第一条结果;  Top:  做分页;  OrderBy: 排序
>
> 方法名中 By 代表 where , 使用 And Or 连接条件, 使用 Is Not In Null True After Before Between Equals Like GreaterThan LessThanEquals StartingWith  Containing 等等作为条件
>
> eg: getAllByNameLikeAndAgeGreaterThanEquals(name,age): 查询所有姓名like name, 年龄大于等于 age 的数据

```java
//定义方法, 只需要规范方法名和参数, JPA 自动帮你实现 SQL 语句 
//查询全部 Employee
List<Employee> queryAllByIdNotNull(); 
//根据 name 查询
Employee findByName(String name);
//查找所有: name like 且 age 小于
List<Employee> getAllByNameLikeAndAgeLessThan(String name, int age);
```

#### 2.2 手动设置 SQL 实现

> 可以使用 @Query, @Modifying 实现 CRUD, 并且方法名不需要遵循命名规则

##### 2.2.1 @Query

> @Query 注解在 Repository 接口的方法上, 方法名不需要遵循命名规则
>
> @Query 属性
>
> - value:  SQL 语句 或 HQL 语句(在 SQL 语句中使用 类名,字段名 替换 表名,列名) 
>     - 可以使用  `?1` `?2` `?3` ..  表示占位符, 在注解的方法传入的参数对应这些占位符
>     - 可以使用 `:name`  表示占位符, 在注解的方法传入的参数添加 @Param("name")
> - nativeQuery:  为 true 时, value 为 SQL 语句; 为 false (默认)时, value 为 HQL 语句

```java
//HQL 语句, 使用 :name 占位符, 方法参数需要加上 @Param 注解且 value 属性指定占位符的 name
@Query("select e from Employee e where e.name like %:name% and e.age=:age") 
List<Employee> query(@Param("name") String name, @Param("age") int age);
//SQL 语句, 使用 ?1 占位符
@Query(nativeQuery=true,"select e from employee e where e.name like %?1%")
List<Employee> query2(String name); 
```

##### 2.2.2 @Modifying

> @Modifying 注解在 Repository 接口的方法上, 可以让 @Query 执行更新, 删除的操作
>
> 但是在调用该方法的方法上需要使用 @Transactional 开启事务, 如果没有则会报错
>
> 配合 @Query, @Transactional 注解使用 

```java
@Query("update Employee e set e.age=:age where e.id=:id")
@Modifying
int update(@Param("id") int id, @Param("age") int age);

@Query("delete Employee e where e.id=:id")
@Modifying
int delete(@Param("id") int id);
```



### 3. Repository 的子接口实现 CURD

> 编写接口, 继承合适的接口, 在 Service 层可以直接调用这些接口的方法
>
> - CrudRepository: 继承 Repository, 实现了 CRUD 相关的方法
>
> - PagingAndSortingRepository: 继承 CrudRepository 接口, 实现了分页排序的方法
>
> - JpaRepository: 继承 PagingAndSortingRepository, 实现了 JPA 规范相关的方法

#### 3.1 CrudRepository 接口

> **功能: CRUD**
>
> CrudRepository 接口使用详解
>
> - save(entity): 添加或修改 实体对象
> - saveAll(entities): 添加或修改多个 实体对象
> - findById(id): 根据 id 查询 实体对象
> - findAll(): 查询所有 实体对象
> - findAllById(ids): 查询所有 id 列表的 实体对象
> - count(): 查询数据个数
> - existsById(id): 判断指定 id 的 实体对象 是否存在
> - deleteById(id): 删除指定 id 的 实体对象
> - delete(entity): 删除指定的 实体对象
> - deleteAll(entities): 删除多个指定的 实体对象
> - deleteAll(): 删除所有 实体对象

```java
//继承 CrudRepository 接口
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
```

```java
@Autowired
private EmployeeRepository employeeRepository;
@Test //junit 测试
public void test() {
    employeeRepository.findAll();
    employeeRepository.count();
}
```

#### 3.2 PagingAndSortingRepository 接口

> 该接口包含分页和排序的功能, 该接口继承了 CrudRepository 接口
>
> **功能: CRUD + 排序 + 分页**
>
> PagingAndSortingRepository  接口使用详解
>
> - findAll(Sort sort): 带排序查询, 返回 实体对象 集合
>
> - findAll(Pageable pageable): 带排序的分页查询, 返回 Page 集合
>
> Sort 类的静态构建方法( Direction.ASC, Direction.DESC, Order.asc(""), Order.desc("") )
>
> - static Sort by(String... properties): 根据 需要排序的字段 构建
> - static Sort by(Direction direction, String... properties): 根据 排序方式和需要排序的字段 构建
> - static Sort by(Order... orders): 根据 Order 构建
> - static Sort by(List<Order> orders): 根据 Order 构建
> - static Sort unsorted(): 根据 无排序规则 构建
>
> Pageable 接口的子类 PageRequest 的静态构建方法
>
> - static PageRequest of(int page, int size): 根据 当前页数,每页条数 构建
> - static PageRequest of(int page, int size, Sort sort): 根据 当前页数,每页条数,排序规则 构建
> - static PageRequest of(int page, int size, Direction direction, String... properties)
>
> Page 的类方法
>
> - getTotalPages(): 查询的总页数
> - getTotalElements(): 查询的总记录数
> - getNumber(): 查询的当前第几页
> - getContent(): 查询的当前页面的集合
> - getNumberOfElements(): 查询的当前页面的记录数

```java
//继承 PagingAndSortingRepository 接口
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer>{
}
```

```java
@Autowired
private EmployeeRepository employeeRepository;
@Test //junit 测试
public void test() {
    //排序
    Sort sort = Sort.by(Direction.DESC, "age", "id"); //构建排序规则(只能指定一种升降排序)
    Sort sort1 =Sort.by(Order.desc("age"),Order.asc("id"));//构建排序规则(可以自由指定升降排序)
    Sort sort2 = Sort.unsorted(); //不指定排序
    //根据排序查询, 传入 Sort
    Iterable<Employee> ages = employeeRepository.findAll(sort); 
    //分页
    Pageable pageable = PageRequest.of(0, 5); //构建分页规则(第几页, 每页条数)
    PageRequest.of(0, 5, Direction.DESC, "age", "id"); //设置排序规则, 构建分页规则
    Pageable pageable2 = PageRequest.of(0, 5, sort); //设置排序规则, 构建分页规则 
    //根据[排序]分页查询, 传入 Pageable
    Page<Employee> page = employeeRepository.findAll(pageable);//第 1 页, 每页 5 条
    System.out.println("查询的总页数: " + page.getTotalPages());
    System.out.println("查询的总记录数: " + page.getTotalElements());
    System.out.println("查询的当前第几页: " + (page.getNumber() + 1));
    System.out.println("查询的当前页面的集合: " + page.getContent());
    System.out.println("查询的当前页面的记录数: " + page.getNumberOfElements());
}
```

#### 3.3 JpaRepository 接口

> 继承了 PagingAndSortingRepository, QueryByExampleExecutor 接口
>
> **功能: CRUD + 排序 + 分页 + 模板查询**
>
> JpaRepository 接口使用详解
>
> - flush(): 刷新
> - saveAndFlush(entity): 保存并刷新
> - deleteInBatch(entities): 删除多个
> - deleteAllInBatch(): 删除所有
> - getOne(id): id 获取
> - findOne(Example): 模板查询一个
> - findAll(Example): 模板查询所有
> - findAll(Example, Sort): 模板查询所有, 并排序
> - findAll(Example, Pageable): 模板查询所有, 并[排序]分页
> - count(Example): 模板查询统计数量
> - exists(Example): 模板查询是否存在结果

```java
//继承 JpaRepository 接口
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
```

```java
@Autowired
private EmployeeRepository employeeRepository;
@Test //junit 测试
public void test() {
    Employee employee = new Employee();
    employee.setAge(20);
    List<Employee> list = employeeRepository.findAll(Example.of(employee));
}
```

### 4. JpaSpecificationExecutor 接口实现复杂查询

> Specification 封装了 JPA Criteria 查询条件
>
> **功能: 复杂查询 + 排序 + 分页**
>
> JpaSpecificationExecutor  接口使用详解
>
> - findOne(Specification): 复杂条件查询一个
> - findAll(Specification): 复杂条件查询所有
> - findAll(Specification, Sort): 复杂条件查询所有, 并排序
> - findAll(Specification, Pageable): 复杂条件查询所有, 并[排序]分页
> - count(Specification): 复杂条件查询统计数量
>
> Specification 接口的方法, 可以连接多个 Specification 
>
> - static Specification where(Specification):  where
> - static Specification not(Specification):   not
> - Specification and(Specification):   and
> - Specification or(Specification):   or
>
> 使用 Specification 接口需要实现 toPredicate() 方法(root 获取条件类型, query 设置条件, cb构建 Predicate)
>
> ```java
> Specification<Employee> specification = new Specification<Employee>() {
>     public Predicate toPredicate(Root<Employee> root
>                                  	, CriteriaQuery<?> query, CriteriaBuilder cb) {
>         return null;
>     }
> };
> ```

```java
//继承 JpaSpecificationExecutor 接口
public interface EmployeeRepository extends JpaSpecificationExecutor<Employee> {
}
```

```java
@Autowired
private EmployeeRepository employeeRepository;
@Test //junit 测试
public void test() {
    //设置查询条件
    Specification<Employee> specification = (root, query, cb) -> {
        Path<Integer> path = root.get("age");
        return cb.gt(path,50);
    };
    //排序分页复杂查询
    Page<Employee> page = employeeRepository.findAll(specification
                    , PageRequest.of(0, 5, Sort.by(Order.desc("age"),Order.asc("id"))));
}
```

### 5. 事务

> @Transactional 注解开启事务
>
> @Transactional 属性
>
> 1. rollbackOn: 需要回滚的异常
> 2. dontRollbackOn: 排除的异常

