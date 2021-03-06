## * 其他

#### * MySQL语句规范

	1. 关键字与函数名称全部大写
​	2. 数据库名称,表名称,字段名称全部小写
​	3. SQL语句必须以分号结尾

#### * MySQL配置文件(/data/my.ini)

​	数据库默认字符集
​		default-character-set=utf8
​		character-set-server=utf8
​	数据表的默认存储引擎
​		default-storage-engine=INNODB
​	

#### * 修改默认结束符

​	delimiter 结束符
​

#### * Navicat 连接 MySQL8.0 数据库报 2059 错的解决方案

​		navicat 链接 mysql8 以后的版本会出现 2059 的错误,原因是在 mysql8 之前的版本中加密规则为 mysql_native_password,在 mysql8 以后为caching_sha2_password.
​	a: 解决此问题有两种方法: 
​		1: 一种是更新 navicat 驱动来解决此问题
​		2: 一种是将 mysql 用户登录的加密规则修改为 mysql_native_password
​	b: 代码: 	//更改 root

```mysql
-- 修改加密规则
alter user 'root'@'localhost' identified by 'YourPassword' password expire never; 
-- 更新一下用户的密码
alter user 'root'@'localhost' identified with mysql_native_password by 'YourPassword';
-- 刷新权限
flush privileges;
```

​	

## 1: MySQL简单命令行

#### 	1.1: MySQL登录

```
mysql + <args>
```

1. -D, --database=name :  打开指定数据库
2. --delimiter = name : 指定分隔符
3. -h, --host=name : 服务器名称
4. -p, --password[=name] : 密码
5. -P, --port=# : 端口号
6. --prompt=name 设置提示符
7. -u, --user=name : 用户名
8. -V, --version : 输出版本信息并且退出

#### 	1.2: MySQL退出

1. ```mysql
    mysql> exit;
    ```

2. ```mysql
    mysql> quit;
    ```

3. ```mysql
    mysql> \q;
    ```

#### 	1.3: 修改提示符

1. 连接客户端时通过参数指定

	```
	shell> mysql -u 用户名 -p 密码 --prompt 提示符
	```

2. 连接上客户端后,通过prompt修改
   
    ```mysql
	mysql> prompt 提示符  		
      ​	eg: mysql> prompt qwer>
    ```

3. MySQL提示符
    \D : 完整的日期
    \d : 当前数据库
    \h : 服务器名称
    \u : 当前用户

#### 	1.4: 常用命令

1. 显示当前服务器版本

	```mysql
	select version();
	```

2. 显示当前日期时间

	```mysql
	select now();
	```

3. 显示当前用户

	```mysql
	select user();
	```

#### 	1.5: 创建数据库

```mysql
create {database|schema} [if not exists] DB_NAME [default] character set [=] CHARSET_NAME;
```

#### 	1.6: 查看当前服务器下的数据表列表

```
show {database|schema} [like 'PATTERN' | where EXPR];
```

#### 	1.7: 修改数据库

```mysql
alter {database|schema} [DB_NAME] {default} character set [=] CHARSET_NAME;
```

#### 	1.8: 删除数据库

```mysql
drop {database|schema} [if exists] DB_NAME;
```

#### 	1.9: 打开(使用)数据库

```mysql
use DB_NAME;
```



## 2: 数据类型 

​		整形和浮点型设置无符号: 后面加 unsigned. 	eg: float(4,2) unsigned;

#### 	2.1: 整形

1. tinyint: 2^7 (1字节)
2. smallint: 2^15 (2字节)
3. mediumint: 2^23 (3字节)
4. int: 2^31 (4字节)
5. bigint: 2^63 (8字节)

#### 	2.2: 浮点型

1. float[(M,D)]: -3.4028E38到3.4028E38,精确到大约7位小数 (4字节)		
2. double[(M,D)]: -1.7977E308到1.7977E308 (8字节)
​		M: 数字总位数(整数位+小数位),D: 小数点后位数

#### 	2.3: 日期时间型

1. year
2. time
3. date
4. datetime
5. timestamp

#### 	2.4: 字符型

1. char(M): M个字节 (0<=M<=255)
2. varchar(M): L+1个字节 (L<=M&&0<=M<=65535) 
3. tinytext: L+1个字节 (L<2^8)
4. text: L+2个字节 (L<2^16)
5. mediumtext: L+3个字节 (L<2^24)
6. longtext: L+4个字节 (L<2^32)
7. enum('value1','value2',...): 1或2个字节,取决于枚举值的个数(最多65535个值)
8. set('value1','value2',...): 1,2,3,4或8个字节,取决于set成员的数目(最多64个成员)


## 3: 数据表

​		数据库最重要的组成部分之一,是其他对象的基础

#### 	3.1: 创建数据表

```mysql
create table [if not exists] TABLE_NAME (COLUMN_NAME DATA_TYPE,...) SELECT_STATEMENT;
```

#### 	3.2: 查看数据表

```mysql
show tables [from DB_NAME] [like 'PATTERN'| where EXPR];
```

#### 	3.3: 查看数据表创建命令

```mysql
show create table TABLE_NAME;
```

#### 	3.4: 查看数据表结构

1. ```mysql
    show columns from TABLE_NAME;
    ```

2. ```mysql
    desc TABLE_NAME;
    ```

#### 	3.5: 插入记录

1. ```mysql
    insert [into] TABLE_NAME [(COLUMN_NAME,...)] {values|value} ({EXPR|default},...),(...),...;
    ```

2. ```mysql
    insert [into] TABLE_NAME set COLUMN_NAME={EXPR|default},COLUMN_NAME={EXPR|default},...;  -- (与第一种的区别在于,此方法可以使用子查询(SubQuery))
    ```

3. ```mysql
    insert [into] TABLE_NAME [(COLUMN_NAME,...)] select ...; 
    -- (此方法可以将查询结果插入到指定数据表)
    ```

#### 	3.6: 查找记录	

```mysql
select [distinct] SELECT_EXPR [,SELECT_EXPR,...] 		-- distinct: 去重复
[
	from TABLE_NAME											-- 要查询的表
	[where WHERE_CONDITION]									-- 查询条件
	[group by {COLUMN_NAME|POSITION} [asc|desc],...]		-- 查询结果分组
	[having WHERE_CONDITION]								-- 分组条件
	[order by {COLUMN_NAME|EXPR|POSITION} [asc|desc],...]	-- 对查询结果排序
	[limit {[OFFSET,] ROW_COUT|ROW_COUNT offset OFFSET}]	-- 限制查询结果返回的数量
];
```

#### 	3.7: 子查询: Subquery,出现在其他SQL语句内的select语句

​	子查询嵌套在查询内部(外层查询可以是: select,insert,update,set或do),且必须出现在圆括号内,可以包含多个关键字或条件(eg: distinct,group by,order by,limit,函数等).
​	子查询可以返回标量,一行,一列或子查询
1. 使用比较运算符的子查询 (=,>,<,>=,<=,<>,!=,<=>)

  ```mysql
  OPERAND COMPARISON_OPERATOR [any|some|all] (子查询); 
  -- (any: 满足任意一条;some=any;all: 满足所有)
  
  eg: select name,price from tab where price >= all(select price from tab where price >= 1000);			
  ```

2. 使用[not]in的子查询

  ```mysql
  OPERAND COMPARISON_OPERATOR [not] in (子查询);  
  -- (=any运算符与in等效;!=all或<>all与not in等效)
  
  eg: select name,price from tab where price not in (select price from tab where price >= 1000);	
  ```

3. 使用[not]exists的子查询
​	如果子查询返回任何行,exists将返回true,否则返回false;

#### 	3.8: 更新记录

1. 单表更新: 

    ```mysql
    update [low_priority] [ignore] TABLE_REFERENCE set COLUMN_NAME={EXPR|default} [,COLUMN_NAME={EXPR|default},...] [where WHERE_CONDITION];
    ```

2. 多表更新: 

  ```mysql
  update TABLE_REFERENCES set COLUMN_NAME={EXPR|default} [,COLUMN_NAME={EXPR|default},...] [where WHRER_CONDITION];
  
  eg: update goods inner join goods_cates on goods.goods_cate = goods_cates.cate_name set goods.goods_cate = goods_cates.cate_id;				
  ```

#### 	3.9: 删除记录

1. 单表删除: 

    ```mysql
    delete from TABLE_NAME [where WHERE_CONDITION]; 
    ```

2. 多表删除: 

    ```mysql
    delete TABLE_NAME [.*] [,TABLE_NAME[.*],...] from TABLE_REFERENCES [where WHERE_CONDITION]
    ```

#### 	3.10: 字段属性: 自动编号(auto_increment)

只能是整形或浮点型,且必须与主键组合使用,默认情况下,起始值为1,每次增量为1
​	eg: create table tab(id smallint auto_increment primary key);

#### 	3.11: 修改数据表结构

1. 添加单列

  ```mysql
  alter table TABLE_NAME add [column] COLUMN_NAME COLUMN_DEFINITION [first|after COLUMN_NAME];
  ```

2. 添加多列

  ```mysql
  alter table TABLE_NAME add [column] (COLUMN_NAME COLUMN_DEFINITION,...);
  ```

3. 删除列

  ```mysql
  alter table TABLE_NAME drop [column] COLUMN_NAME;
  ```

4. 添加约束
    ​添加主键约束

  ```mysql
  alter table TABLE_NAME add [constraint[SYMBOL]] primary key [INDEX_TYPE] (INDEX_COL_NAME,...);
  ```

  添加唯一约束		

  ```mysql
  alter table TABLE_NAME add [constraint[SYMBOL]] unique [index|key] [INDEX_NAME] [INDEX_TYPE] (INDEX_COL_NAME,...);
  ```

  添加外键约束 		

  ```mysql
  alter table TABLE_NAME add [constraint[SYMBOL]] foreign key [INDEX_NAME] (INDEX_COL_NAME,...) REFERENCE_DEFINITION; 
  ```

  添加/删除默认约束		

  ```mysql
  alter table TABLE_NAME alter [column] COLUMN_NAME {set default LITERAL|drop default} 
  ```

5. 删除约束
    ​删除主键约束

  ```mysql
  alter table TABLE_NAME drop primary key;
  ```

  删除唯一约束		

  ```mysql
  alter table TABLE_NAME drop {index|key} INDEX_NAME;
  ```

  删除外键约束		

  ```mysql
  alter table TABLE_NAME drop foreign key FK_SYMBOL;
  ```

6. 修改列定义

  ```mysql
  alter table TABLE_NAME modify [column] COLUMN_NAME COLUMN_DEFINITION [first|after COLUMN_NAME];
  ```

7. 修改列名称

  ```mysql
  alter table TABLE_NAME change [column] OLD_COL_NAME NEW_COL_NAME COLUMN_DEFINITION [first|after COLUMN_NAME]; 
  ```

8. 修改数据表名称

  ```mysql
  -- 方式1
  alter table TABLE_NAME rename [to|as] NEW_TABLE_NAME; 
  -- 方式2
  rename table TABLE_NAME to NEW_TABLE_NAME [,TABLE_NAME_2 to NEW_TABLE_NAME_2,...];
  ```

  ​				

## 4: 约束

约束保证数据的完整性和一致性.约束分为表级约束和列级约束
	列级约束: 对一个数据列建立的约束+(可以在列定义时声明,也可以在列定义后声明);表级约束: 对多个数据列建立的约束(只能在列定义后声明).

#### 	4.1: 非空约束(not null)

字段值禁止为空

```mysql
create table tab(username varchar(20) not null);
```

#### 	4.2: 主键约束(primary key)

主键约束,每张数据表只能存在一个主键,主键保证记录的唯一性,主键自动为not null

```mysql
create table tab(id smallint primary key);
```



#### 	4.3: 唯一约束(unique key)

唯一约束可以保证记录的唯一性,字段值可以为空值(只能有一个空值),每张数据表可以存在多个唯一约束

```mysql
create table tab(id smallint unique key);
```

#### 	4.4: 默认约束(default)

默认值,当插入记录时,如果没有明确为字段赋值,则自动赋予默认值.

```mysql
create table tab(sex enum('男','女','不详') default '男');
```

#### 	4.5: 外键约束(foreign key)	

​	保持数据一致性,完整性;实现一对一或一对多关系

###### 4.5.1: ​外键约束的要求

1. 父表和子表必须使用相同的存储引擎,而且禁止使用临时表

2. 数据表的存储引擎只能为InnoDB.

3. 外键列和参照列必须具有相似的数据类型.其中数字的长度或是否有符号位必须相同;而字符的长度则可以不同

4. 外键列和参照列必须创建索引.如果参照列不存在索引的话,MySQL将自动创建索引
   
    ```mysql
    -- eg
    create table tab2(pid smallint, foreign key (pid) references tab(id));
    ```
    
    

###### 4.5.2: 外键约束的参照操作

1. 从父表删除或更新且自动删除或更新子表中匹配的行

2. setcascade null: 从父表删除或更新行,并设置子表中的外键列为null.如果使用改选项,必须保证子表列没有指定not null

3. restrict: 拒绝对父表的删除或更新操作

4. no action: 标准SQL的关键字,在MySQL中与restrict相同
   
    ```mysql
    -- eg
    create table tab2(pid smallint, foreign key (pid) references tab(id) on delete cascade);
    ```
    
    



## 5: 连接

```mysql
TABLE_REFERENCE {[inner|cross] join | {left|right} [outer] join} TABLE_REFERENCE on CONDITIONAL_EXPR;
```

```mysql
-- eg
select id1,name1,name2 from tab1 inner join tab2 on tab1.id1 = tab2_id2;
```

```mysql
-- eg
select goods_id,goods_name,cate_name,brand_name,goods_price from goods g
inner join goods_cates c on g.cate_id = c.cate_id
inner join goods_brands b on g.brand_id = b.brand_id;
```

#### 	5.1: 连接类型 

1. inner join: 内连接(等价于cross join)
​	A ∩ B: 仅显示两表符合连接条件的记录
2. left join: 左外连接
​	A + A ∩ B: 显示左表全部记录及右表符合连接条件的记录
3. right join: 右外连接: 
​	A ∩ B + B: 显示右表全部记录及左表符合连接条件的记录 

#### 	5.2: 连接条件

​	使用on关键字设定连接条件,也可以使用where来代替
​	通常使用on关键字设定连接条件,使用where关键字进行结果集记录的过滤

#### 	5.3: 无限分类的数据表设计

```mysql
CREATE TABLE tdb_goods_types(
	type_id   SMALLINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,	 -- id
	type_name VARCHAR(20) NOT NULL,							 -- 类型名称
	parent_id SMALLINT UNSIGNED NOT NULL DEFAULT 0			 -- 父id
);
```

#### 	5.4: 自身连接

​	同一个数据表对其自身进行连接(需要取区别名)



## 6: 内置函数

#### 	6.1: 字符函数

1. concat(String1,String2,...): 字符连接

2. concat_ws('分隔符',String1,String2,...): 使用指定的分隔符进行字符连接

3. format(number,int): 数字格式化,返回字符串,将指定数字精确到小数点后指定位数

4. lower(String): 转成小写字母

5. upper(String): 转成大写字母

6. left(String,len): 获取从左侧开始指定长度的字符

7. right(String,len): 获取从右侧开始指定长度的字符

8. length(String): 获取字符长度

9. ltrim(String): 删除前导空格

10. rtrim(String): 删除后续空格

11. trim(String): 删除两端空格
    ​trim([{leading|trailing|both} '要删除的字符' from] '字符串'): 删除存在于{前导|后续|两端}的指定的字符 

    ```mysql
    -- eg
    trim(both '??' from '?asd??')  =>  '?asd';
    ```

12. substring(String,start[,len]): 字符串截取,从指定位置start(从1开始记位)开始截取len长度的字符

13. [not] like: 模式匹配
    ​		%：表示0到多个任意字符  
    ​		_：表示单个任意字符 

    ```mysql
    -- eg
    select 'MySQL' like 'M%';  -- 1(即true)
    ```

14. replace(String1,String2,String3): 字符串替换,将String1中的子字符串String2替换为String3.		

#### 	6.2: 数值运算符与函数

1. ceil(num): 向上取整
2. floor(num): 向下取整
3. div: 整数除法(结果为舍弃小数)
4. mod: %,取余数(取模)
5. power(num1,num2): 幂运算
6. round(num [,num2]): 四舍五入(可以指定四舍五入的位数num2)
7. truncate(num,num2): 数字截取,舍弃指定小数后num2位的后面的数

#### 	6.3: 比较运算符和函数

1. [not] between ...and... : 是否[不]在范围内 
2. [not] in(数据1,数据2,...): 是否[不]在列出值范围内
3. is [not] null: 是否[不]为空

#### 	6.4: 日期时间函数

1. now(): 当前日期和时间

2. curdate(): 当前日期

3. curtime(): 当前时间 

4. date_add(): 日期变化 

  ```mysql
  -- eg
  select date_add('2018-7-23',interval -365 day); -- '2017-7-23';
  ```

5. datediff(方式,日期1,日期2): 以指定方式返回日期差值 

  ```mysql
  -- eg
  datediff('s',date1,date2)
  ```

6. date_format(日期,String): 日期格式化

  ```
  -- eg
  select date_format('2018-7-23','%m/%d/%Y'); -- 07/23/2018;
  ```

#### 	6.5: 信息函数

1. connection_id(): 连接ID	

  ```mysql
  -- eg
  select connection_id();
  ```

2. datebase(): 当前数据库

3. last_insert_id(): 最后插入记录的ID号 

4. user(): 当前用户

5. version(): 版本信息

#### 	6.6: 聚合函数

1. avg(): 返回平均值
2. count(): 返回数据个数 
3. max(): 返回最大值
4. min(): 返回最小值
5. sum(): 返回和

#### 	6.7: 加密函数

1. md5(): 信息摘要算法

2. password(): 加密算法

    


## 7: 自定义函数

​		简称UDF,其用法和内置函数相同

```mysql
-- 查看是否开启自定义函数功能
show variables like 'log_bin_trust_function_creators';
-- 开启自定义函数功能
set global log_bin_trust_function_creators=1; 
```

#### 	7.1: 必要条件

1. 参数列表(参数数量不能超过1024)
2. 返回值

#### 	7.2: 创建自定义函数

```mysql
create function FUNCTION_NAME([参数名 参数类型,...]) returns 返回值类型 [begin] ROUTINE_BODY [end]; 
```

```mysql
-- eg
create function QiuPingJun(num1 smallint unsigned,num2 smallint unsigned)
returns float(10,2) unsigned
return (num1 + num2) / 2;
```

#### 	7.3: 函数体

1. 函数体有合法的SQL语句构成.
2. 函数体可以是简单的select或insert语句.
3. 函数体如果为复合结构则使用begin...end语句.
4. 复合结构可以包含声明,循环,控制结构.

#### 	7.4: 删除自定义函数

```mysql
drop function FUNCTION_NAME;
```



## 8: 存储过程

​		是SQL语句和控制语句的预编译集合,以一个名称存储并作为一个单元处理

#### 	8.1: 优点

1. 增强SQL语句的功能和灵活性
2. 实现较快的执行速度
3. 减少网络流量 

#### 	8.2: 创建存储过程 

```mysql
create [definer={USER|current_user}] procedure SP_NAME ([PROC_PARAMETER [,...]]) [CHARACTERISTIC ...] ROUTINE_BODY;
-- PROC_PARAMETER: [in|out|inout] PARAM_NAME PARAM_TYPE;
-- in: 表示该参数的值必须在调用存储过程时指定
-- out: 表示该参数的值可以被存储过程改变,并且可以返回
-- inout: 表示该参数的调用时指定,并且可以被改变和返回
```

```mysql
-- eg
delimiter //
create procedure removeUserById(in id int unsigned)
begin delete from users where id = user_id; end//
delimiter ;
```

#### 	8.3: 特性

```mysql
comment 'String' 
| {contains sql|no sql|reads sql data|modifies sql data} 
| sql security {definer|invoker};
```

1. comment: 注释
2. contains sql: 包含SQL语句,但不包含读或写数据的语句
3. no sql: 不包含SQL语句
4. reads sql data: 包含读数据的语句
5. modifies sql data: 包含写数据的语句
6. sql security {definer|invoker}: 指明谁有权限来执行

#### 	8.4: 过程体

1. 过程体有合法的SQL语句构成.
2. 过程体可以是'任意'SQL语句.
3. 过程体如果为复合结构则使用begin...end语句.
4. 复合结构可以包含声明,循环,控制结构.

#### 	8.5: 调用存储过程

```mysql
call SP_NAME[([参数列表])];
```

#### 	8.6: 修改存储过程

```mysql
alter procedure SP_NAME [CHARACTERISTIC ...]
comment 'String' 
| {contains sql|no sql|reads sql data|modifies sql data} 
| sql security {definer|invoker};
```

#### 	8.7: 删除存储过程

```mysql
drop procedure [if exists] SP_NAME;
```

#### 	8.8: 存储过程与自定义函数函数的区别

1. 存储过程实现的功能要复杂一些,而函数的针对性更强;
2. 存储过程可以返回多个值,函数只能有一个返回值;
3. 存储过程一般独立的来执行,函数可以作为其他SQL语句的组成部分来出现

## 9: 存储引擎

​	MySQL可以将数据以不同的技术存储在文件(内存)中,这种技术就成为存储引擎
​	每一种存储引擎使用不同的存储机制,索引技巧,锁定水平,最终提供广泛且不同的功能

#### 	9.1: MySQL支持的存储引擎

1. MyISAM

2. InnoDB

3. Memory

4. Archive

5. CSV

|   特点   | MyISAM | InnoDB | Memory | Archive |
| :------: | :----: | :----: | :----: | :-----: |
| 存储限制 | 256TB  |  64TB  |   有   |   无    |
| 事务安全 |   --   |  支持  |   --   |   --    |
| 支持索引 |  支持  |  支持  |  支持  |   --    |
|  锁颗粒  |  表锁  |  行锁  |  表锁  |  行锁   |
| 数据压缩 |  支持  |   --   |   --   |  支持   |
| 支持外键 |   --   |  支持  |   --   |   --    |

#### 	9.2: 修改存储引擎

1. 通过修改MySQL配置文件实现

  ```ini
  default-storage-engine=存储引擎
  ```

2. 通过创建数据表命令实现

  ```mysql
  create table TABLE_NAME() engine=存储引擎;
  ```

3. 通过修改数据表命令实现

  ```mysql
  alter table TABLE_NAME engine [=] ENGINE_NAME;
  ```

#### 	9.3: 并发控制

​	当多个连接对记录进行修改时保证数据的一致性和完整性
1. 锁
​	共享锁(读锁): 在同一时间段内,多个用户可以读取同一个资源,读取过程中数据不会发生任何变化
​	排他锁(写锁): 在任何时候只能有一个用户可以写入资源,当进行写锁时会阻塞其他的读锁或写锁操作
2. 锁颗粒
​	表锁,一种开销最小的锁策略
​	行锁,一种开销最大的锁策略

#### 	9.4: 事务		

​	事务用于保证数据库完整性
1: 事务的特性
​	原子性(Atomicity)
​	一致性(Consistency)
​	隔离性(Isolation)
​	持久性(Durability)

#### 	9.5: 外键

​	保证数据一致性的策略

#### 	9.6: 索引

​		对数据表中一列或多列的值进行排序的一种结构
​	普通索引,唯一索引,全文索引,btree索引,hash索引,...


​		
​		
​		
​		
​		
​		
​		
​		
​		
​		
​		
​		
​		
​		
​		