# MyBatisPlus 

[MyBatis 官方文档](https://baomidou.com/guide/)

# 一. 快速入门

## 1. 简介

[MyBatis-Plus](https://github.com/baomidou/mybatis-plus)(简称 MP)是一个 [MyBatis](http://www.mybatis.org/mybatis-3/) 的增强工具, 在 MyBatis 的基础上只做增强不做改变, 为简化开发、提高效率而生。

### 11. 特性

- **无侵入**：只做增强不做改变, 引入它不会对现有工程产生影响, 如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD, 性能基本无损耗, 直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service, 仅仅通过少量配置即可实现单表大部分 CRUD 操作, 更有强大的条件构造器, 满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式, 方便的编写各类查询条件, 无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略(内含分布式唯一 ID 生成器 - Sequence), 可自由配置, 完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用, 实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入( Write once, use anywhere )
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码, 支持模板引擎, 更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页, 开发者无需关心具体操作, 配置好插件之后, 写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间, 建议开发测试时启用该功能, 能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断, 也可自定义拦截规则, 预防误操作

### 12. 支持数据库

- mysql 、mariadb 、oracle 、db2 、h2 、hsql 、sqlite 、postgresql 、sqlserver 、presto 、Gauss 、Firebird
- Phoenix 、clickhouse 、Sybase ASE 、 OceanBase 、达梦数据库 、虚谷数据库 、人大金仓数据库 、南大通用数据库 



## 2. 快速开始 (SpringBoot)

### 21. 依赖

#### 211. SpringBoot 项目

```xml
<!-- 引入 SpringBoot 依赖, 引入数据库驱动,连接池依赖  -->
<!-- 引入 mybatis plus  -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
```

#### 212. SpringMVC 项目

```xml
<!-- 引入 Spring,SpringMVC 依赖, 引入数据库驱动,连接池依赖  -->
<!-- 引入 mybatis plus  -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus</artifactId>
</dependency>
```

### 22. 配置

#### 221. 配置数据源

在 `application.yml` 配置文件中添加数据库源的相关配置

```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8
    username: root
    password: root
```

#### 222. 配置扫描 Mapper 包

在 Spring Boot 启动类中添加 `@MapperScan` 注解, 扫描 Mapper 文件夹：

```java
@SpringBootApplication
@MapperScan("com.lemting.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }
}
```

### 23. 编码

#### 231. 实体类

```java
@Data  // 使用了 lombok 简化, 需要引入依赖
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

#### 232. Mapper

```java
public interface UserMapper extends BaseMapper<User> {

}
```

### 24. 开始使用

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
```



# 二. 基本使用

## 1. 注解

注解类包：`mybatis-plus-annotation`

### 11. @TableName

表名注解

|       属性       |   类型   | 必须指定 | 默认值 | 描述                                                         |
| :--------------: | :------: | :------: | :----: | ------------------------------------------------------------ |
|      value       |  String  |    否    |   ""   | 表名                                                         |
|      schema      |  String  |    否    |   ""   | schema                                                       |
| keepGlobalPrefix | boolean  |    否    | false  | 是否保持使用全局的 tablePrefix 的值(如果设置了全局 tablePrefix 且自行设置了 value 的值) |
|    resultMap     |  String  |    否    |   ""   | xml 中 resultMap 的 id, 实体映射结果集,                      |
|  autoResultMap   | boolean  |    否    | false  | 是否自动构建 resultMap 并使用(如果设置 resultMap 则不会进行 resultMap 的自动构建并注入) |
| excludeProperty  | String[] |    否    |   {}   | 需要排除的属性名                                             |

### 12. @TableId

主键注解

| 属性  |  类型  | 必须指定 |   默认值    |    描述    |
| :---: | :----: | :------: | :---------: | :--------: |
| value | String |    否    |     ""      | 主键字段名 |
| type  |  Enum  |    否    | IdType.NONE |  主键类型  |

#### 121. IdType

生成 ID 类型枚举类

|        值         |                             描述                             |
| :---------------: | :----------------------------------------------------------: |
|       AUTO        |                        数据库 ID 自增                        |
|       NONE        | 无状态,该类型为未设置主键类型(注解里等于跟随全局,全局里约等于 INPUT) |
|       INPUT       |          insert 前自行 set 主键值(自己维护自己设置)          |
|     ASSIGN_ID     | 分配 ID(主键类型为 Number(Long 和 Integer) 或 String)(since 3.3.0),使用接口`IdentifierGenerator`的方法`nextId`(默认实现类为`DefaultIdentifierGenerator`雪花算法) |
|    ASSIGN_UUID    | 分配 UUID,主键类型为 String(since 3.3.0),使用接口`IdentifierGenerator`的方法`nextUUID`(默认 default 方法) |
|   ~~ID_WORKER~~   |     分布式全局唯一 ID 长整型类型(please use `ASSIGN_ID`)     |
|     ~~UUID~~      |          32 位UUID字符串(please use `ASSIGN_UUID`)           |
| ~~ID_WORKER_STR~~ |     分布式全局唯一ID 字符串类型(please use `ASSIGN_ID`)      |

### 13. @TableField

字段注解(非主键)

|       属性       |             类型             | 必须指定 |          默认值          |                             描述                             |
| :--------------: | :--------------------------: | :------: | :----------------------: | :----------------------------------------------------------: |
|      value       |            String            |    否    |            ""            |                         数据库字段名                         |
|        el        |            String            |    否    |            ""            | 映射为原生 `#{ ... }` 逻辑,相当于写在 xml 里的 `#{ ... }` 部分 |
|      exist       |           boolean            |    否    |           true           |                      是否为数据库表字段                      |
|    condition     |            String            |    否    |            ""            | 字段 `where` 实体查询比较条件,有值设置则按设置的值为准,没有则为默认全局的 `%s=#{%s}`,[参考](https://github.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-annotation/src/main/java/com/baomidou/mybatisplus/annotation/SqlCondition.java) |
|      update      |            String            |    否    |            ""            | 字段 `update set` 部分注入, 例如：update="%s+1"：表示更新时会set version=version+1(该属性优先级高于 `el` 属性) |
|  insertStrategy  |             Enum             |    N     |  FieldStrategy.DEFAULT   | 举例：NOT_NULL: `insert into table_a(column) values (#{columnProperty})` |
|  updateStrategy  |             Enum             |    N     |  FieldStrategy.DEFAULT   | 举例：IGNORED: `update table_a set column=#{columnProperty}` |
|  whereStrategy   |             Enum             |    N     |  FieldStrategy.DEFAULT   |      举例：NOT_EMPTY: `where column=#{columnProperty}`       |
|       fill       |             Enum             |    否    |    FieldFill.DEFAULT     |                       字段自动填充策略                       |
|      select      |           boolean            |    否    |           true           |                     是否进行 select 查询                     |
| keepGlobalFormat |           boolean            |    否    |          false           |              是否保持使用全局的 format 进行处理              |
|     jdbcType     |           JdbcType           |    否    |    JdbcType.UNDEFINED    |           JDBC类型 (该默认值不代表会按照该值生效)            |
|   typeHandler    | Class<? extends TypeHandler> |    否    | UnknownTypeHandler.class |          类型处理器 (该默认值不代表会按照该值生效)           |
|   numericScale   |            String            |    否    |            ""            |                    指定小数点后保留的位数                    |

#### 131. FieldStrategy

字段策略枚举类

|    值     |                           描述                            |
| :-------: | :-------------------------------------------------------: |
|  IGNORED  |                         忽略判断                          |
| NOT_NULL  |                        非NULL判断                         |
| NOT_EMPTY | 非空判断(只对字符串类型字段,其他类型字段依然为非NULL判断) |
|  DEFAULT  |                       追随全局配置                        |

#### 132. FieldFill

字段填充策略枚举类

|      值       |         描述         |
| :-----------: | :------------------: |
|    DEFAULT    |      默认不处理      |
|    INSERT     |    插入时填充字段    |
|    UPDATE     |    更新时填充字段    |
| INSERT_UPDATE | 插入和更新时填充字段 |

### 14. @Version

乐观锁注解、标记 `@Verison` 在字段上

### 15. @EnumValue

通枚举类注解(注解在枚举字段上)

### 16. @TableLogic

表字段逻辑处理注解(逻辑删除)

|  属性  |  类型  | 必须指定 | 默认值 |     描述     |
| :----: | :----: | :------: | :----: | :----------: |
| value  | String |    否    |   ""   | 逻辑未删除值 |
| delval | String |    否    |   ""   |  逻辑删除值  |

### 17. @KeySequence

序列主键策略 `oracle`

| 属性  |  类型  | 必须指定 |   默认值   |                             描述                             |
| :---: | :----: | :------: | :--------: | :----------------------------------------------------------: |
| value | String |    否    |     ""     |                            序列名                            |
| clazz | Class  |    否    | Long.class | id 的类型, 可以指定String.class, 这样返回的Sequence值是字符串"1" |

### 18. ~~@SqlParser~~

租户注解, 支持method上以及mapper接口上

|  属性  |  类型   | 必须指定 | 默认值 |                             描述                             |
| :----: | :-----: | :------: | :----: | :----------------------------------------------------------: |
| filter | boolean |    否    | false  | true: 表示过滤SQL解析, 即不会进入ISqlParser解析链, 否则会进解析链并追加例如tenant_id等条件 |

### 19. @IEnum

自定义枚举接口

### 1A. @InterceptorIgnore

内置插件的一些过滤规则

|       属性       |  类型  | 必须指定 | 默认值 |                  描述                  |
| :--------------: | :----: | :------: | :----: | :------------------------------------: |
|    tenantLine    | String |    否    |   ""   |                行级租户                |
| dynamicTableName | String |    否    |   ""   |                动态表名                |
|   blockAttack    | String |    否    |   ""   | 攻击 SQL 阻断解析器,防止全表更新与删除 |
|    illegalSql    | String |    否    |   ""   |             垃圾 SQL 拦截              |



## 2. 代码生成器





## 3. CRUD 接口

### 31. Service CRUD 接口

> 说明:
>
> - 通用 Service CRUD 封装 `IService` 接口, 进一步封装 CRUD 采用 `get 查询单行` `remove 删除` `list 查询集合` `page 分页` 前缀命名方式区分 `Mapper` 层避免混淆, 
> - 泛型 `T` 为任意实体对象
> - 建议如果存在自定义通用 Service 方法的可能, 请创建自己的 `IBaseService` 继承 `Mybatis-Plus` 提供的基类
> - 对象 `Wrapper` 为条件构造器

```java
public interface UserService extends IService<User> {

}

@Service
class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {

}
```



#### 311. Save

```java
// 插入一条记录(选择字段, 策略插入)
boolean save(T entity);
// 插入(批量)
boolean saveBatch(Collection<T> entityList);
// 插入(批量)
boolean saveBatch(Collection<T> entityList, int batchSize);
```

参数说明

|     类型      |   参数名   |     描述     |
| :-----------: | :--------: | :----------: |
|       T       |   entity   |   实体对象   |
| Collection<T> | entityList | 实体对象集合 |
|      int      | batchSize  | 插入批次数量 |

#### 312.  SaveOrUpdate

```java
// TableId 注解存在更新记录, 否插入一条记录
boolean saveOrUpdate(T entity);
// 根据 updateWrapper 尝试更新, 否继续执行 saveOrUpdate(T)方法
boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
// 批量修改插入
boolean saveOrUpdateBatch(Collection<T> entityList);
// 批量修改插入
boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);
```

参数说明

|     类型      |    参数名     |               描述               |
| :-----------: | :-----------: | :------------------------------: |
|       T       |    entity     |             实体对象             |
|  Wrapper<T>   | updateWrapper | 实体对象封装操作类 UpdateWrapper |
| Collection<T> |  entityList   |           实体对象集合           |
|      int      |   batchSize   |           插入批次数量           |

#### 313. Remove

```java
// 根据 entity 条件, 删除记录
boolean remove(Wrapper<T> queryWrapper);
// 根据 ID 删除
boolean removeById(Serializable id);
// 根据 columnMap 条件, 删除记录
boolean removeByMap(Map<String, Object> columnMap);
// 删除(根据 ID 批量删除)
boolean removeByIds(Collection<? extends Serializable> idList);
```

参数说明

|                类型                |    参数名    |          描述           |
| :--------------------------------: | :----------: | :---------------------: |
|             Wrapper<T>             | queryWrapper | 实体包装类 QueryWrapper |
|            Serializable            |      id      |         主键 ID         |
|        Map<String, Object>         |  columnMap   |     表字段 map 对象     |
| Collection<? extends Serializable> |    idList    |      主键 ID 列表       |

#### 314. Update

```java
// 根据 UpdateWrapper 条件, 更新记录 需要设置sqlset
boolean update(Wrapper<T> updateWrapper);
// 根据 whereEntity 条件, 更新记录
boolean update(T entity, Wrapper<T> updateWrapper);
// 根据 ID 选择修改
boolean updateById(T entity);
// 根据ID 批量更新
boolean updateBatchById(Collection<T> entityList);
// 根据ID 批量更新
boolean updateBatchById(Collection<T> entityList, int batchSize);
```

参数说明

|     类型      |    参数名     |               描述               |
| :-----------: | :-----------: | :------------------------------: |
|  Wrapper<T>   | updateWrapper | 实体对象封装操作类 UpdateWrapper |
|       T       |    entity     |             实体对象             |
| Collection<T> |  entityList   |           实体对象集合           |
|      int      |   batchSize   |           更新批次数量           |

#### 315. Get

```java
// 根据 ID 查询
T getById(Serializable id);
// 根据 Wrapper, 查询一条记录。结果集, 如果是多个会抛出异常, 随机取一条加上限制条件 wrapper.last("LIMIT 1")
T getOne(Wrapper<T> queryWrapper);
// 根据 Wrapper, 查询一条记录
T getOne(Wrapper<T> queryWrapper, boolean throwEx);
// 根据 Wrapper, 查询一条记录
Map<String, Object> getMap(Wrapper<T> queryWrapper);
// 根据 Wrapper, 查询一条记录
<V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
```

参数说明

|            类型             |    参数名    |              描述               |
| :-------------------------: | :----------: | :-----------------------------: |
|        Serializable         |      id      |             主键ID              |
|         Wrapper<T>          | queryWrapper | 实体对象封装操作类 QueryWrapper |
|           boolean           |   throwEx    |   有多个 result 是否抛出异常    |
|              T              |    entity    |            实体对象             |
| Function<? super Object, V> |    mapper    |            转换函数             |

#### 316. List

```java
// 查询所有
List<T> list();
// 查询列表
List<T> list(Wrapper<T> queryWrapper);
// 查询(根据ID 批量查询)
Collection<T> listByIds(Collection<? extends Serializable> idList);
// 查询(根据 columnMap 条件)
Collection<T> listByMap(Map<String, Object> columnMap);
// 查询所有列表
List<Map<String, Object>> listMaps();
// 查询列表
List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
// 查询全部记录
List<Object> listObjs();
// 查询全部记录
<V> List<V> listObjs(Function<? super Object, V> mapper);
// 根据 Wrapper 条件, 查询全部记录
List<Object> listObjs(Wrapper<T> queryWrapper);
// 根据 Wrapper 条件, 查询全部记录
<V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
```

参数说明

|                类型                |    参数名    |              描述               |
| :--------------------------------: | :----------: | :-----------------------------: |
|             Wrapper<T>             | queryWrapper | 实体对象封装操作类 QueryWrapper |
| Collection<? extends Serializable> |    idList    |           主键ID列表            |
|        Map<?String, Object>        |  columnMap   |         表字段 map 对象         |
|    Function<? super Object, V>     |    mapper    |            转换函数             |

#### 317. Page

```java
// 无条件分页查询
IPage<T> page(IPage<T> page);
// 条件分页查询
IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
// 无条件分页查询
IPage<Map<String, Object>> pageMaps(IPage<T> page);
// 条件分页查询
IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);
```

参数说明

|    类型    |    参数名    |              描述               |
| :--------: | :----------: | :-----------------------------: |
|  IPage<T>  |     page     |            翻页对象             |
| Wrapper<T> | queryWrapper | 实体对象封装操作类 QueryWrapper |

#### 318. Count

```java
// 查询总记录数
int count();
// 根据 Wrapper 条件, 查询总记录数
int count(Wrapper<T> queryWrapper);
```

参数说明

|    类型    |    参数名    |              描述               |
| :--------: | :----------: | :-----------------------------: |
| Wrapper<T> | queryWrapper | 实体对象封装操作类 QueryWrapper |

#### 319. Chain

query

```java
// 链式查询 普通
QueryChainWrapper<T> query();
// 链式查询 lambda 式。注意：不支持 Kotlin
LambdaQueryChainWrapper<T> lambdaQuery(); 

// 示例：
query().eq("column", value).one();
lambdaQuery().eq(Entity::getId, value).list();
```

update

```java
// 链式更改 普通
UpdateChainWrapper<T> update();
// 链式更改 lambda 式。注意：不支持 Kotlin 
LambdaUpdateChainWrapper<T> lambdaUpdate();

// 示例：
update().eq("column", value).remove();
lambdaUpdate().eq(Entity::getId, value).update(entity);
```



### 32. Mapper CRUD 接口

> 说明:
>
> - 通用 CRUD 封装 `BaseMapper`接口, 为 `Mybatis-Plus` 启动时自动解析实体表关系映射转换为 `Mybatis` 内部对象注入容器
> - 泛型 `T` 为任意实体对象
> - 参数 `Serializable` 为任意类型主键 `Mybatis-Plus` 不推荐使用复合主键约定每一张表都有自己的唯一 `id` 主键
> - 对象 `Wrapper` 为 条件构造器

#### 321. Insert

```java
// 插入一条记录
int insert(T entity);
```

参数说明

| 类型 | 参数名 |   描述   |
| :--: | :----: | :------: |
|  T   | entity | 实体对象 |

#### 322. Delete

```java
// 根据 entity 条件, 删除记录
int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
// 删除(根据ID 批量删除)
int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
// 根据 ID 删除
int deleteById(Serializable id);
// 根据 columnMap 条件, 删除记录
int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
```

参数说明

|                类型                |  参数名   |                描述                |
| :--------------------------------: | :-------: | :--------------------------------: |
|             Wrapper<T>             |  wrapper  |  实体对象封装操作类(可以为 null)   |
| Collection<? extends Serializable> |  idList   | 主键ID列表(不能为 null 以及 empty) |
|            Serializable            |    id     |               主键ID               |
|        Map<String, Object>         | columnMap |          表字段 map 对象           |

#### 323. Update

```java
// 根据 whereEntity 条件, 更新记录
int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
// 根据 ID 修改
int updateById(@Param(Constants.ENTITY) T entity);
```

参数说明

|    类型    |    参数名     |                             描述                             |
| :--------: | :-----------: | :----------------------------------------------------------: |
|     T      |    entity     |               实体对象 (set 条件值,可为 null)                |
| Wrapper<T> | updateWrapper | 实体对象封装操作类(可以为 null,里面的 entity 用于生成 where 语句) |

#### 324. Select

```java
// 根据 ID 查询
T selectById(Serializable id);
// 根据 entity 条件, 查询一条记录
T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 查询(根据ID 批量查询)
List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
// 根据 entity 条件, 查询全部记录
List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 查询(根据 columnMap 条件)
List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
// 根据 Wrapper 条件, 查询全部记录
List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 根据 Wrapper 条件, 查询全部记录。注意： 只返回第一个字段的值
List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 根据 entity 条件, 查询全部记录(并翻页)
IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 根据 Wrapper 条件, 查询全部记录(并翻页)
IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
// 根据 Wrapper 条件, 查询总记录数
Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
```

参数说明

|                类型                |    参数名    |                  描述                  |
| :--------------------------------: | :----------: | :------------------------------------: |
|            Serializable            |      id      |                 主键ID                 |
|             Wrapper<T>             | queryWrapper |    实体对象封装操作类(可以为 null)     |
| Collection<? extends Serializable> |    idList    |   主键ID列表(不能为 null 以及 empty)   |
|        Map<String, Object>         |  columnMap   |            表字段 map 对象             |
|              IPage<T>              |     page     | 分页查询条件(可以为 RowBounds.DEFAULT) |

### 33. mapper 层 选装件

> 说明:
>
> 选装件位于 `com.baomidou.mybatisplus.extension.injector.methods.additional` 包下 需要配合`Sql 注入器`使用
>
> 使用详细见源码注释

AlwaysUpdateSomeColumnById

```java
int alwaysUpdateSomeColumnById(T entity);
```

insertBatchSomeColumn

```java
int insertBatchSomeColumn(List<T> entityList);
```

deleteByIdWithFill

```java
int deleteByIdWithFill(T entity);
```



## 4. Wrapper 条件构造器

> 说明:
>
> - 以下出现的第一个入参`boolean condition`表示该条件**是否**加入最后生成的sql中
> - 以下代码块内的多个方法均为从上往下补全个别`boolean`类型的入参,默认为`true`
> - 以下出现的泛型`Param`均为`Wrapper`的子类实例(均具有`AbstractWrapper`的所有方法)
> - 以下方法在入参中出现的`R`为泛型,在普通wrapper中是`String`,在LambdaWrapper中是**函数**(例:`Entity::getId`,`Entity`为实体类,`getId`为字段`id`的**getMethod**)
> - 以下方法入参中的`R column`均表示数据库字段,当`R`具体类型为`String`时则为数据库字段名(**字段名是数据库关键字的自己用转义符包裹!**)!而不是实体类数据字段名!!!,另当`R`具体类型为`SFunction`时项目runtime不支持eclipse自家的编译器!!!
> - 以下举例均为使用普通wrapper,入参为`Map`和`List`的均以`json`形式表现!
> - 使用中如果入参的`Map`或者`List`为**空**,则不会加入最后生成的sql中!!!
> - 有任何疑问就点开源码看

### 41. AbstractWrapper

> 说明:
>
> QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类
>
> 用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件
>
> 注意: entity 生成的 where 条件与 使用各个 api 生成的 where 条件**没有任何关联行为**

#### 411. allEq

```java
// 全部 eq
allEq(Map<R, V> params)
// 全部 eq (null2IsNull 为 false 时, 忽略 params 中值为 null 的 eq)
allEq(Map<R, V> params, boolean null2IsNull)
allEq(boolean condition, Map<R, V> params, boolean null2IsNull)
```

- 全部 eq (或个别 isNull)

> 个别参数说明:
>
> `params` : `key`为数据库字段名,`value`为字段值
>
> `null2IsNull` : 为`true`则在`map`的`value`为`null`时调用 `isNull`方法,为`false`时忽略`value`为`null`

- eg: `allEq({id:1,name:"老王",age:null})`--->`id = 1 and name = '老王' and age is null`
- eg: `allEq({id:1,name:"老王",age:null}, false)`--->`id = 1 and name = '老王'`

```java
allEq(BiPredicate<R, V> filter, Map<R, V> params)
allEq(BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull)
allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) 
```

> 个别参数说明:
>
> `filter` : 过滤函数,是否允许字段传入比对条件中
>
> `params` 与 `null2IsNull` : 同上

- eg: `allEq((k,v) -> k.indexOf("a") >= 0, {id:1,name:"老王",age:null})`--->`name = '老王' and age is null`
- eg: `allEq((k,v) -> k.indexOf("a") >= 0, {id:1,name:"老王",age:null}, false)`--->`name = '老王'`

#### 412. 值比较

| 方法 | 描述     | 运算符 |
| ---- | -------- | ------ |
| eq   | 等于     | =      |
| ne   | 不等于   | <>     |
| gt   | 大于     | >      |
| ge   | 大于等于 | \>=    |
| lt   | 小于     | <      |
| le   | 小于等于 | <=     |

```java
// = 值1
eq(R column, Object val)
eq(boolean condition, R column, Object val)
// < 值1 
lt(R column, Object val)
lt(boolean condition, R column, Object val)
```

- eg: `eq("name", "老王")`--->`name = '老王'`

#### 413. 区间

| 方法       | 描述   | 运算符                |
| ---------- | ------ | --------------------- |
| between    | 区间内 | between .. and ..     |
| notBetween | 区间外 | not between .. and .. |

```java
// between 值1 and 值2
between(R column, Object val1, Object val2)
between(boolean condition, R column, Object val1, Object val2)
// not between 值1 and 值2 
notBetween(R column, Object val1, Object val2)
notBetween(boolean condition, R column, Object val1, Object val2)
```

- eg: `between("age", 18, 30)`--->`age between 18 and 30`
- eg: `notBetween("age", 18, 30)`--->`age not between 18 and 30`

#### 415. 模糊

| 方法      | 描述   | 运算符          |
| --------- | ------ | --------------- |
| like      | 匹配   | like '%值%'     |
| notLike   | 不匹配 | not like '%值%' |
| likeLeft  | 左匹配 | like '%值'      |
| likeRight | 右匹配 | like '值%'      |

```java
// like '%值%'
like(R column, Object val)
like(boolean condition, R column, Object val)
// like '%值'
likeLeft(R column, Object val)
likeLeft(boolean condition, R column, Object val)
```

- eg: `like("name", "王")`--->`name like '%王%'`
- eg: `likeRight("name", "王")`--->`name like '王%'`

#### 416. 空

| 方法      | 描述 | 运算符      |
| --------- | ---- | ----------- |
| isNull    | 空   | is null     |
| isNotNull | 非空 | is not null |

```java
// is null
isNull(R column)
isNull(boolean condition, R column)
// is not null
isNotNull(R column)
isNotNull(boolean condition, R column)
```

- eg: `isNull("name")`--->`name is null`
- eg: `isNotNull("name")`--->`name is not null`

#### 417. in (子查询)

| 方法     | 描述          | 运算符            |
| -------- | ------------- | ----------------- |
| in       | in            | in (...)          |
| notIn    | not in        | not in(...)       |
| inSql    | in 子查询     | in (sql 语句)     |
| notInSql | not in 子查询 | not in (sql 语句) |

```java
// in (value.get(0), value.get(1), ...)
in(R column, Collection<?> value)
in(boolean condition, R column, Collection<?> value)
// in (v0, v1, v2, ...)
in(R column, Object... values)
in(boolean condition, R column, Object... values)
// not in (...)
notIn(R column, Collection<?> value)
notIn(boolean condition, R column, Collection<?> value)
// not in (...)
notIn(R column, Object... values)
notIn(boolean condition, R column, Object... values)
```

- eg: `in("age",{1,2,3})`--->`age in (1,2,3)`

- eg: `notIn("age", 1, 2, 3)`--->`age not in (1,2,3)`

```java
// in (sql语句)
inSql(R column, String inValue)
inSql(boolean condition, R column, String inValue)
// not in (sql语句)
notInSql(R column, String inValue)
notInSql(boolean condition, R column, String inValue)
```

- eg: `notInSql("age", "1,2,3,4,5,6")`--->`age not in (1,2,3,4,5,6)`
- eg: `inSql("id", "select id from table where id < 3")`--->`id in (select id from table where id < 3)`

#### 418. 分组 排序

| 方法        | 描述   | 运算符            |
| ----------- | ------ | ----------------- |
| groupBy     | 分组   | group by ..       |
| orderByAsc  | 排序   | order by .. asc   |
| orderByDesc | 排序   | order by .. desc  |
| orderBy     | 排序   | order by ...      |
| having      | having | having (sql 语句) |

```java
// group by ..
groupBy(R... columns)
groupBy(boolean condition, R... columns)
// order by ...
orderByAsc(R... columns)
orderByAsc(boolean condition, R... columns)
orderByDesc(R... columns)
orderByDesc(boolean condition, R... columns)
orderBy(boolean condition, boolean isAsc, R... columns)
// having (sql 语句)
having(String sqlHaving, Object... params)
having(boolean condition, String sqlHaving, Object... params)
```

- eg: `groupBy("id", "name")`--->`group by id,name`
- eg: `orderByAsc("id", "name")`--->`order by id ASC,name ASC`
- eg: `orderBy(true, true, "id", "name")`--->`order by id ASC,name ASC`
- eg: `having("sum(age) > 10")`--->`having sum(age) > 10`
- eg: `having("sum(age) > {0}", 11)`--->`having sum(age) > 11`

#### 419. 条件拼接

| 方法   | 描述                              | 运算符 |
| ------ | --------------------------------- | ------ |
| func   | func 方法                         |        |
| or     | 拼接 or, or 嵌套                  | or     |
| and    | and 嵌套                          | and    |
| nested | 正常嵌套 不带 AND 或者 OR         |        |
| apply  | 拼接 sql                          |        |
| last   | 无视优化规则直接拼接到 sql 的最后 |        |

##### 4191. func

```java
// func 方法(主要方便在出现 if...else 下调用不同方法能不断链)
func(Consumer<Children> consumer)
func(boolean condition, Consumer<Children> consumer)
```

- 例: `func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)})`

##### 4192. or

```java
// 拼接 or, 下一个条件用 or 连接(默认是用 and 连接)
or()
or(boolean condition)
```

- eg: `eq("id",1).or().eq("name","老王")`--->`id = 1 or name = '老王'`

```java
// or 嵌套
or(Consumer<Param> consumer)
or(boolean condition, Consumer<Param> consumer)
```

- eg: `or(i -> i.eq("n", "李白").ne("s", "活"))`--->`or (n = '李白' and s <> '活')`

##### 4193. and

```java
// and 嵌套
and(Consumer<Param> consumer)
and(boolean condition, Consumer<Param> consumer)
```

- eg: `and(i -> i.eq("n", "李白").ne("s", "活"))`--->`and (n= '李白' and s <> '活')`

##### 4194. nested

```java
// 正常嵌套 不带 and 或者 or
nested(Consumer<Param> consumer)
nested(boolean condition, Consumer<Param> consumer)
```

- eg: `nested(i -> i.eq("n", "李白").ne("s", "活"))`--->`(n= '李白' and s <> '活')`

##### 4195. apply

> 该方法可用于数据库**函数** 动态入参的`params`对应前面`applySql`内部的`{index}`部分.这样是不会有sql注入风险的,反之会有!

```java
// 拼接 sql
apply(String applySql, Object... params)
apply(boolean condition, String applySql, Object... params)
```

- eg: `apply("id = 1")`--->`id = 1`
- eg: `apply("date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")`--->`date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")`
- eg: `apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08")`--->`date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")`

##### 4196. last

> 只能调用一次,多次调用以最后一次为准 有 sql 注入的风险,请谨慎使用

```java
// 无视优化规则直接拼接到 sql 的最后
last(String lastSql)
last(boolean condition, String lastSql)
```

- eg: `last("limit 1")`

#### 41A. 存在

| 方法      | 描述   | 运算符                |
| --------- | ------ | --------------------- |
| exists    | 存在   | exists (sql 语句)     |
| notExists | 不存在 | not exists (sql 语句) |

```java
// exists (sql 语句)
exists(String existsSql)
exists(boolean condition, String existsSql)
// not exists (sql 语句)
notExists(String notExistsSql)
notExists(boolean condition, String notExistsSql)
```

- eg: `exists("select id from table where age = 1")`--->`exists (select id from table where age = 1)`
- eg: `notExists("select id from table where age = 1")`--->`not exists (select id from table where age = 1)`



### 42. QueryWrapper

> 继承自`AbstractWrapper` ,自身的内部属性 `entity` 也用于生成 where 条件及 `LambdaQueryWrapper`, 可以通过 `new QueryWrapper().lambda()` 方法获取

#### 421. select

```java
// 设置查询字段
select(String... sqlSelect)
select(Predicate<TableFieldInfo> predicate)
select(Class<T> entityClass, Predicate<TableFieldInfo> predicate)
```

> 以上方法分为两类.
>
> 第二类方法为:过滤查询字段(主键除外),入参不包含 class 的调用前需要`wrapper`内的`entity`属性有值!
>
> 这两类方法重复调用以最后一次为准

- eg: `select("id", "name", "age")`
- eg: `select(i -> i.getProperty().startsWith("test"))`

### 43. UpdateWrapper

> 继承自 `AbstractWrapper` ,自身的内部属性 `entity` 也用于生成 where 条件及 `LambdaUpdateWrapper`, 可以通过 `new UpdateWrapper().lambda()` 方法获取!

#### 431. set

```java
// sql set 字段
set(String column, Object val)
set(boolean condition, String column, Object val)
```

- eg: `set("name", "老李头")`
- eg: `set("name", "")`--->数据库字段值变为**空字符串**
- eg: `set("name", null)`--->数据库字段值变为`null`

#### 432. setSql

```java
// 设置 set 部分 sql
setSql(String sql)
```

- eg: `setSql("name = '老李头'")`



### 44. LambdaQueryWrapper

```java
// 通过 QueryWrapper 获取
LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
// 通过构造器创建
LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
// 通过 Wrappers 获取
LambdaQueryWrapper<User> lambda = Wrappers.lambdaQuery();
```

> LambdaQueryWrapper 的方法与 QueryWrapper 类似, 只是字段部分传入的是一个方法
>
> - 传入实体类的 get 方法, 设置条件的字段为属性对应的表字段

```java
// WHERE name LIKE '%雨%' AND age < 40
lambda.like(User::getName, "雨").le(User::getAge, 40);
List<User> userList = userMapper.selectList(lambda);
userList.forEach(System.out::println);
```



### 45. LambdaUpdateWrapper





### 46. 使用 Wrapper 自定义SQL

> 在使用了`mybatis-plus`之后, 自定义SQL的同时也想使用`Wrapper`的便利应该怎么办？ 在`mybatis-plus`版本`3.0.7`得到了完美解决 版本需要大于或等于`3.0.7`, 以下两种方案取其一即可

Service.java

```java
mysqlMapper.getAll(Wrappers.<MysqlData>lambdaQuery().eq(MysqlData::getGroup, 1));
```

#### 461. 方案一 注解方式 Mapper.java

```java
@Select("select * from mysql_data ${ew.customSqlSegment}")
List<MysqlData> getAll(@Param(Constants.WRAPPER) Wrapper wrapper); 
```

#### 462. 方案二 XML形式 Mapper.xml

```xml
<select id="getAll" resultType="MysqlData">
	SELECT * FROM mysql_data ${ew.customSqlSegment}
</select>
```



## 5. 分页查询

### 51. 编写配置类

```java
//Spring boot方式
@Configuration
@MapperScan("com.lemting.mp.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
```

```java
// 3.4.0
@Configuration
@MapperScan("com.lemting.mp.mapper")
public class MybatisPlusConfig {
    // 新的分页插件,一缓和二缓遵循mybatis的规
    // 需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(
            new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
```

#### 511. PaginationInnerInterceptor

|  属性名  |   类型   | 默认值 |                             描述                             |
| :------: | :------: | :----: | :----------------------------------------------------------: |
| overflow | boolean  | false  | 溢出总页数后是否进行处理(默认不处理,参见 `插件#continuePage` 方法) |
| maxLimit |   Long   |        |  单页分页条数限制(默认无限制,参见 `插件#handlerLimit` 方法)  |
|  dbType  |  DbType  |        | 数据库类型(根据类型获取应使用的分页方言,参见 `插件#findIDialect` 方法) |
| dialect  | IDialect |        |          方言实现类(参见 `插件#findIDialect` 方法)           |



### 52. 自定义分页

- UserMapper.java 方法内容

```java
public interface UserMapper extend BaseMapper<User> { //可以继承或者不继承BaseMapper
    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    <E extends IPage<T>> E selectPage(E page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
```

- UserServiceImpl.java 调用分页方法

```java
public IPage<User> selectUserPage(Page<User> page) {
    // 要点!! 分页返回的对象与传入的对象是同一个
    return userMapper.selectPage(page, null);
}
```

- 测试

```java
@SpringBootTest
class AppTest {
    @Autowired
    private UserService userService;
    @Test
    void pageTest() {
        Page<User> page = new Page<>(2, 2);
        userMapper.selectUserPage(page, null);
        System.out.println("总页数: " + page.getPages());
        System.out.println("总记录数: " + page.getTotal());
        page.getRecords().forEach(System.out::println);
    }
    @Test
    void pageMapTest() {
        Page<Map<String, Object>> page = new Page<>(2, 2);
        userMapper.selectMapsPage(page, null);
        System.out.println("总页数: " + page.getPages());
        System.out.println("总记录数: " + page.getTotal());
        page.getRecords().forEach(System.out::println);
    }
}
```

### 53. Page

| 属性名           | 类型            | 默认值                  | 描述                |
| ---------------- | --------------- | ----------------------- | ------------------- |
| records          | List<T>         | Collections.emptyList() | 查询结果集          |
| total            | long            | 0                       | 总记录数            |
| size             | long            | 10                      | 每页记录数          |
| current          | long            | 1                       | 当前页数            |
| isSearchCount    | boolean         | true                    | 是否 COUNT 查询     |
| optimizeCountSql | boolean         | true                    | 自动优化 COUNT SQL  |
| hitCount         | boolean         | false                   | 是否命中 count 缓存 |
| orders           | List<OrderItem> | new ArrayList()         | 排序字段信息        |
| maxLimit         | Long            |                         |                     |
| countId          | String          |                         |                     |



## 6. ActiveRecord 模式

Active Record(活动记录), 是一种领域模型模式, 特点是一个模型类对应关系型数据库中的一个表, 而模型类的一个实例对应表中的一行记录

```java
// 实体类需要继承 Model
public class User extends Model<User> {
	private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;
}
```

```java
// mapper 需要继承 BaseMapper
public interface UserMapper extends BaseMapper<User> {
}
```

可以直接调用 Model 类的方法实现 ActiveRecord

```java
@Test // 直接调用继承自 Model 类的 insert() 方法, 就可以执行插入语句
void testARInsert() {
    User user = new User();
    user.setName("按时"); user.setAge(22); user.setEmail("as@qq.om");
    user.setManagerId(1); user.setCreateTime(LocalDateTime.now());
    boolean success = user.insert();
}
```

```java
@Test // selectById(), selectById(id), 可以根据 id 查找数据, 返回新的实体
void testARSelect() {
    User selectUser1 = new User().selectById(1); // 传入 id 查询

    User user = new User();
    user.setId(1);
    User selectUser2 = user.selectById(); // 使用设置的 id, 查询

    System.out.println(selectUser1);
    System.out.println(selectUser2);
}
```

```java
@Test // updateById(): 更新 
void testARUpdate() {
    User user = new User();
    user.setId(1);
    user.setEmail("1234@qqq.qqc");
    boolean success = user.updateById();
}
```

```java
@Test // deleteById(id) 方法, 可以根据 id 查找数据, 返回新的实体
void testARDelete() {
    boolean success1 = new User().deleteById(1); // 传入 id 删除

    User user = new User();
    user.setId(1);
    boolean success2 = user.deleteById(); // 使用设置的 id, 删除
}
```



## 7. 主键策略

> 在主键上使用 @TableId 注解(局部主键策略)时, 可以指定主键策略, 默认 IdType.NONE (跟随全局)
>
> 可以在 application.properties 配置全局主键策略, 默认 IdType.ASSIGN_ID (默认使用雪花算法)
>
> ```properties
> mybatis-plus.global-config.db-config.id-type=assign_id
> ```

### 71. 主键

#### 711. IdType.AUTO

自增主键策略, 需要数据库设置主键为自增

```java
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
}
```

#### 712. IdType.NONE

跟随全局的策略

#### 713. IdType.ASSIGN_ID

主键类型为 Number(Long 和 Integer) 或 String), 使用接口 `IdentifierGenerator` 的方法`nextId`(默认实现类为`DefaultIdentifierGenerator`雪花算法

#### 714. IdType.ASSIGN_UUID

分配 UUID,主键类型为 String(since 3.3.0),使用接口 `IdentifierGenerator` 的方法 `nextUUID` (默认 default 方法)

### 72. Sequence 主键

> **主键生成策略必须使用 INPUT**: IdType.INPUT
>
> 支持父类定义@KeySequence子类继承使用
>
> 内置支持：
>
> - DB2KeyGenerator
> - H2KeyGenerator
> - KingbaseKeyGenerator
> - OracleKeyGenerator
> - PostgreKeyGenerator
>
> 如果内置支持不满足你的需求，可实现 IKeyGenerator 接口来进行扩展.

```java
@KeySequence(value = "SEQ_ORACLE_STRING_KEY", clazz = String.class)
public class User {
    @TableId(value = "ID_STR", type = IdType.INPUT)
    private String id;
}
```

#### 721. SpringBoot

- 方式一：使用配置类

```java
@Bean
public IKeyGenerator keyGenerator() {
    return new H2KeyGenerator();
}
```

- 通过 MybatisPlusPropertiesCustomizer 自定义

```java
@Bean
public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
    return plusProperties -> plusProperties.getGlobalConfig().getDbConfig()
        								.setKeyGenerator(new H2KeyGenerator());
}
```

#### 722. Spring

- 方式一: XML配置

```xml
<bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
   <property name="dbConfig" ref="dbConfig"/>
</bean>

<bean id="dbConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig">
   <property name="keyGenerator" ref="keyGenerator"/>
</bean>

<bean id="keyGenerator" class="com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator"/>
```

- 方式二：注解配置

```java
@Bean
public GlobalConfig globalConfig() {
	GlobalConfig conf = new GlobalConfig();
	conf.setDbConfig(new GlobalConfig.DbConfig()
                     			.setKeyGenerator(new H2KeyGenerator()));
	return conf;
}
```

## 8. MP 配置

### 81. 基本配置

|                               | 描述                                 | 类型     | 默认值                           |
| ----------------------------- | ------------------------------------ | -------- | -------------------------------- |
| mybatis-plus.config-location  | MyBatis 配置文件位置                 | String   | null                             |
| mybatis-plus.mapper-locations | MyBatis Mapper 所对应的 XML 文件位置 | String[] | ["classpath*:/mapper/**/ *.xml"] |
|                               |                                      |          |                                  |



# 三. 高级功能

## 1. 逻辑删除

> 只对自动注入的sql起效:
>
> - 插入: 不作限制
> - 查找: 追加where条件过滤掉已删除数据,且使用 wrapper.entity 生成的where条件会忽略该字段
> - 更新: 追加where条件防止更新到已删除数据,且使用 wrapper.entity 生成的where条件会忽略该字段
> - 删除: 转变为 更新
>
> eg: 
>
> - 删除: `update user set deleted=1 where id = 1 and deleted=0`
> - 查找: `select id,name,deleted from user where deleted=0`
>
> 字段类型支持说明:
>
> - 支持所有数据类型(推荐使用 `Integer`,`Boolean`,`LocalDateTime`)
> - 如果数据库字段使用`datetime`,逻辑未删除值和已删除值支持配置为字符串`null`,另一个值支持配置为函数来获取值如`now()`
>
> 附录:
>
> - 逻辑删除是为了方便数据恢复和保护数据本身价值等等的一种方案，但实际就是删除。
> - 如果你需要频繁查出来看就不应使用逻辑删除，而是以一个状态去表示。

### 11. 使用方法

- 步骤1: 在 application.properties 配置文件配置

```yaml
# application.yml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: flag  # 全局逻辑删除的实体字段名(3.3.0,配置后可以忽略不配置步骤2)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

- 步骤2: 实体类字段上加上@TableLogic注解 (3.3.0 以后, 使用全局配置的字段, 可以不用 @TableLogic)

```java
@TableField(select = false) // 查询时不查询该字段
@TableLogic  // 逻辑删除的实体字段名(局部)
private Integer deleted;
```

## 2. 自动填充

实现元对象处理器接口：`com.baomidou.mybatisplus.core.handlers.MetaObjectHandler`

注解填充字段 `@TableField(fill = FieldFill.INSERT)` 生成器策略部分也可以配置！

> - 填充原理是直接给`entity`的属性设置值!!!
> - 注解则是指定该属性在对应情况下必有值,如果无值则入库会是`null`
> - `MetaObjectHandler`提供的默认方法的策略均为:如果属性有值则不覆盖,如果填充值为`null`则不填充
> - 字段必须声明`TableField`注解,属性`fill`选择对应策略,该声明告知`Mybatis-Plus`需要预留注入`SQL`字段
> - 填充处理器`MyMetaObjectHandler`在 Spring Boot 中需要声明`@Component`或`@Bean`注入
> - 要想根据注解`FieldFill.xxx`和`字段名`以及`字段类型`来区分必须使用父类的`strictInsertFill`或者`strictUpdateFill`方法
> - 不需要根据任何来区分可以使用父类的`fillStrategy`方法

实体类注解填充字段

```java
@TableField(fill = FieldFill.INSERT) // 插入时
private LocalDateTime createTime; // 创建时间
@TableField(fill = FieldFill.INSERT_UPDATE) // 插入/更新时
private LocalDateTime updateTime; // 修改时间
```

- 自定义实现类 MyMetaObjectHandler

```java
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createTime", 
       		LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        this.strictUpdateFill(metaObject, "createTime", 
			() -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        // 或者
        this.fillStrategy(metaObject, "createTime", 
			LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateTime", 
			LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        // 或者
        this.strictUpdateFill(metaObject, "updateTime", 
			() -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        // 或者
        this.fillStrategy(metaObject, "updateTime", 
			LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }
}
```

## 3. 乐观锁插件



## 4. 性能分析插件



## 5. 多租户 SQL 解析器



## 6. 动态表名 SQL 解析器



## 7. SQL 注入器





# * eg

## 1. 查询

### 11. 普通查询

1. 根据主键查询

```java
User user = userMapper.selectById(1); // where id = 1
System.out.println(user);
```

2. 根据主键批量查询

```java
// where id in (1, 2, 3, 4)
List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
userList.forEach(System.out::println);
```

3. 根据 map 查询 (map 的 key 为表字段名(非 bean 的属性名))

```java
Map<String, Object> map = new HashMap<>();
map.put("age", 27);
map.put("sex", 1);
List<User> userList = userMapper.selectByMap(map); // where age = 27 and sex = 1
userList.forEach(System.out::println);
```

### 12. 条件构造器查询

```java
// 获取条件构造器, 通过 Wrappers 构建
QueryWrapper<User> query = Wrappers.query();
// 获取条件构造器, 通过构造器构建
QueryWrapper<User> query = new QueryWrapper<>();

// 使用条件构造器设置查询条件
....
    
//查询
List<User> userList = userMapper.selectList(query);
userList.forEach(System.out::println);
```

1. (姓名包含 向, 年龄在 0-60 岁之间, 创建时间小于当前时间) 或者 id 是 2/5/9

```java
// WHERE name LIKE '%向%' AND age BETWEEN 0 AND 60 AND time <= 时间 OR (id IN (2,5,9))
query.like("name","向").between("age",0,60).le("time", LocalDateTime.now()).or(i -> i.in("id", 2,5,9));
```

2. 姓名包含 雨, 年龄小于40 

```java
// WHERE name LIKE '%雨%' AND age < 40
query.like("name", "雨").lt("age", 40);
```

3. 姓名包含 雨, 年龄在 20-40 岁之间, 邮箱不能为空

```java
// WHERE name LIKE '%雨%' AND age BETWEEN 20 AND 40 AND email IS NOT NULL
query.like("name", "雨").between("age", 20, 40).isNotNull("email");
```

4. 姓名为王姓或者年龄大于等于25, 按照年龄降序排列, 年龄相同按照 id 升序排列

```java
// WHERE name LIKE '王%' OR age >= 25 ORDER BY age DESC,id ASC
query.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
```

5. 创建日期为2019年2月14日并且直属上级为名字为王姓

```java
// WHERE date_format(create_time,'%Y-%m-%d') = '2019-02-14' AND manager_id IN (select id from user where name like '王%')
query.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-02-14")
    			.inSql("manager_id", "select id from user where name like '王%'");
```

6. 姓名为王姓并且(年龄小于40或邮箱不为空)

```java
// WHERE name LIKE '王%' AND (age < 40 OR email IS NOT NULL)
query.likeRight("name", "王").and(q -> q.lt("age", 40).or().isNotNull("email"));
```

7. 名字为王姓或者(年龄小于40并且年龄大于20并且邮箱不为空)

```java
// WHERE name LIKE '王%' OR (age < 40 AND age > 20 AND email IS NOT NULL)
query.likeRight("name","王").or(q -> q.lt("age", 40).gt("age", 20).isNotNull("email"));
```

8. (年龄小于40或邮箱不为空)并且名字为王姓

```java
// WHERE (age < 40 OR email IS NOT NULL) AND name LIKE '王%'
query.and(q -> q.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
```

9. 年龄为30/31/34/35

```java
// WHERE age IN (30, 31, 34, 35)
query.in("age", 30, 31, 34, 35);
```

10. 只返回满足条件的其中一条语句

```java
// limit 1
query.last("limit 1");
```

11. 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。并且只取年龄总和小于500的组

```java
// SELECT avg(age) avg_age, min(age) min_age, max(age) max_age FROM user 
// GROUP BY manager_id HAVING sum(age) < 500
 query.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
     					.groupBy("manager_id").having("sum(age) < {0}", 500);
```



### 13. 部分字段查询

1. 名字中包含雨并且年龄小于40, (id,name)

```java
// SELECT id,name FROM user WHERE name LIKE '%雨%' AND age < 40 
query.select("id", "name").like("name", "雨").lt("age", 40);
```

2. 只返回满足条件的其中一条语句, (id, name, age, email)

```java
// SELECT id,name,age,email FROM user limit 1
query.select("id,name,age,email").last("limit 1");
```

### 14. 实体类对象查询

在创建 QueryWrapper 时, 可以传入实体类对象, 查询时会根据对象中不为 null 的数据作为查询条件

```java
// WHERE name='雨' AND age=27
User user = new User();
user.setName("雨");
user.setAge(27);
QueryWrapper<User> query = new QueryWrapper<>(user);
```

默认是使用 = 作为查询条件的比较方式, 可以在指定字段对应的属性上使用 @TableField 的 condition 改变

`SqlCondition` SQL 比较条件枚举

| 枚举       | 描述        | 表达式                          |
| ---------- | ----------- | ------------------------------- |
| EQUAL      | 等于 (默认) | %s=#{%s}                        |
| NOT_EQUAL  | 不等于      | %s\&lt;\&gt;#{%s}               |
| LIKE       | % 两边 %    | %s LIKE CONCAT('%%',#{%s},'%%') |
| LIKE_LEFT  | % 左        | %s LIKE CONCAT('%%',#{%s})      |
| LIKE_RIGHT | 右 %        | %s LIKE CONCAT(#{%s},'%%')      |

```java
@Data
public class User {
    private Long id;
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    @TableField(condition = "%s &lt;= #{%s}") // 自定义条件 <=
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;
}

// WHERE name LIKE CONCAT('%','雨','%') AND age <= 27
User user = new User();
user.setName("雨");
user.setAge(27);
QueryWrapper<User> query = new QueryWrapper<>(user);
```

### 15. lambda 条件构造器

```java
// 使用 Mapper
// 通过 Wrappers 获取
LambdaQueryWrapper<User> lambda = Wrappers.lambdaQuery();
// WHERE name LIKE '%雨%' AND age < 40
lambda.like(User::getName, "雨").le(User::getAge, 40);
List<User> userList = userMapper.selectList(lambda);
userList.forEach(System.out::println);
```

```java
// 使用 Service
// userService.lambdaQuery() 获取链式 lambda 查询条件构造器 LambdaQueryChainWrapper
List<User> userList = 
    userService.lambdaQuery().like(User::getName, "雨").le(User::getAge, 40).list();
userList.forEach(System.out::println);
```



## 2. 更新

### 21. 普通更新

1. 根据 id 更新

```java
User user = new User(); 
user.setId(1); user.setAge(48); user.setEmail("asdsad.@qwqe.com");
userMapper.updateById(user);
```

2. 根据条件构造器更新

```java
User user = new User(); user.setAge(48); user.setEmail("asdsad.@qwqe.com");
UpdateWrapper<User> update = new UpdateWrapper<>();
update.eq("id", 1);
userMapper.updateBy(user, update);
```



### 22. lambda 条件构造器

```java
// 使用 Mapper
// 通过 Wrappers 获取 LambdaUpdateWrapper
LambdaUpdateWrapper<User> lambda = Wrappers.lambdaUpdate();
lambda.set(User::getAge, 66).eq(User::getId, 1);
User user = new User(); user.setAge(11); user.setEmail("asdsad.@qwqe.com");
// UPDATE user SET age=11, email='asdsad.@qwqe.com', age=66 WHERE id = 1
userMapper.update(user, lambda); // 实体和 lambda 重复的 set, 会优先使用 lambda 的 set
```

```java
// 使用 Service
// userService.lambdaUpdate() 获取链式 lambda 更新条件构造器 LambdaUpdateChainWrapper
// 不根据实体更新
userService.lambdaUpdate().set(User::getAge, 66).eq(User::getId, 1).update();
// 根据实体更新
User user = new User(); user.setAge(11); user.setEmail("asdsad.@qwqe.com");
userService.lambdaUpdate().set(User::getAge, 66).eq(User::getId, 1).update(user);
```

