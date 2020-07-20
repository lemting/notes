## 一. Redis 简介

### 1. Redis 简介

> REmote DIctionary Server(Redis) 是一个由 Salvatore Sanfilippo 写的 key-value 存储系统.
>
> Redis 是一个开源的使用 ANSI C 语言编写, 遵守 BSD 协议, 支持网络, 可基于内存亦可持久化的日志型, Key-Value 数据库, 并提供多种语言的 API.
>
> 它通常被称为数据结构服务器, 因为值(value) 可以是 字符串(String), 哈希(Hash), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型.
>
> Redis 是完全开源免费的, 遵守BSD协议, 是一个高性能的 key-value 数据库.

### 2. Redis 特点

> Redis 与其他 key - value 缓存产品有以下三个特点: 
>
> - Redis 支持数据的持久化, 可以将内存中的数据保存在磁盘中, 重启的时候可以再次加载进行使用
> - Redis 不仅仅支持简单的 key-value 类型的数据, 同时还提供list, set, zset, hash 等数据结构的存储
> - Redis支持数据的备份, 即 master-slave 模式的数据备份

### 3. Redis 优势

> - 性能极高 – Redis 能读的速度是 110000次/s, 写的速度是 81000次/s 
> - 丰富的数据类型 – Redis 支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作
> - 原子 – Redis的所有操作都是原子性的, 意思就是要么成功执行要么失败完全不执行. 单个操作是原子性的. 多个操作也支持事务, 即原子性, 通过 MULTI 和 EXEC 指令包起来.
> - 丰富的特性 – Redis 还支持 publish/subscribe, 通知, key 过期等等特性

### 4. Redis 与其他 key-value 存储的不同

> - Redis 有着更为复杂的数据结构并且提供对他们的原子性操作, 这是一个不同于其他数据库的进化路径. Redis 的数据类型都是基于基本数据结构的同时对程序员透明, 无需进行额外的抽象
> - Redis 运行在内存中但是可以持久化到磁盘, 所以在对不同数据集进行高速读写时需要权衡内存, 因为数据量不能大于硬件内存. 在内存数据库方面的另一个优点是, 相比在磁盘上相同的复杂的数据结构, 在内存中操作起来非常简单, 这样Redis可以做很多内部复杂性很强的事情. 同时, 在磁盘格式方面他们是紧凑的以追加的方式产生的, 因为他们并不需要进行随机访问

### 5. Redis 服务器启动

> 在 cmd 窗口执行 redis-server.exe 开启服务器
>
> 打开另一个 cmd 窗口执行 redis-cli.exe -h 127.0.0.1 -p 6379 作为客户端连接服务



## 二. Redis 配置

> Redis 的配置文件位于 Redis 安装目录下, 文件名为 **redis.conf** (Windows 名为 redis.windows.conf)
>
> 你可以通过 **CONFIG** 命令查看或设置配置项

### 1. 查看配置

> **CONFIG** get 查看配置
>
> ```shell
> # 查看全部配置: config get *
> config get <config_setting_name>
> ```

```shel
redis 127.0.0.1:6379> config get loglevel
1) "loglevel"
2) "notice"
```

### 2. 编辑配置

> 你可以通过修改 redis.conf 文件或使用 CONFIG set 命令来修改配置
>
> ```shell
> config set <config_setting_name> <new_config_value>
> ```

```shell
redis 127.0.0.1:6379> CONFIG SET loglevel "debug"
OK
redis 127.0.0.1:6379> CONFIG GET loglevel
1) "loglevel"
2) "debug"
```

### 3. redis.conf  配置项

| 配置项                          | 说明                                                         |
| :------------------------------ | :----------------------------------------------------------- |
| daemonize no                    | Redis 默认不是以守护进程的方式运行, 可以通过该配置项修改, 使用 yes 启用守护进程(Windows 不支持守护线程的配置为 no) |
| pidfile /var/run/redis.pid      | 当 Redis 以守护进程方式运行时, Redis 默认会把 pid 写入 /var/run/redis.pid 文件, 可以通过 pidfile 指定 |
| port 6379                       | 指定 Redis 监听端口, 默认端口为 6379 (因为 6379 在手机按键上 MERZ 对应的号码, 而 MERZ 取自意大利歌女 Alessia Merz 的名字) |
| bind 127.0.0.1                  | 绑定的主机地址                                               |
| timeout 300                     | 当客户端闲置多长秒后关闭连接, 如果指定为 0 , 表示关闭该功能  |
| loglevel notice                 | 指定日志记录级别, Redis 总共支持四个级别: debug, verbose, notice, warning |
| logfile stdout                  | 日志记录方式, 默认为标准输出, 如果配置 Redis 为守护进程方式运行, 而这里又配置为日志记录方式为标准输出, 则日志将会发送给 /dev/null |
| databases 16                    | 设置数据库的数量, 默认数据库为 0, 可以使用 SELECT 命令在连接上指定数据库 id |
| save <seconds> <changes>        | 指定在多长时间内, 有多少次更新操作, 就将数据同步到数据文件, 可以多个条件配合. Redis 默认配置文件中提供了三个条件: save 900 1, save 300 10, save 60 10000 |
| rdbcompression yes              | 指定本地数据库文件名, 默认值为 dump.rdb                      |
| dir ./                          | 指定本地数据库存放目录                                       |
| slaveof <masterip> <masterport> | 设置当本机为 slave 服务时, 设置 master 服务的 IP 地址及端口, 在 Redis 启动时, 它会自动从 master 进行数据同步 |
| masterauth <master-password>    | 当 master 服务设置了密码保护时, slav 服务连接 master 的密码  |
| requirepass foobared            | 设置 Redis 连接密码, 如果配置了连接密码, 客户端在连接 Redis 时需要通过 AUTH <password> 命令提供密码, 默认关闭 |
| maxclients 128                  | 设置同一时间最大客户端连接数, 默认无限制, Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数, 如果设置 maxclients 0, 表示不作限制. 当客户端连接数到达限制时, Redis 会关闭新的连接并向客户端返回 max number of clients reached 错误信息 |
| maxmemory <bytes>               | 指定 Redis 最大内存限制, Redis 在启动时会把数据加载到内存中, 达到最大内存后, Redis 会先尝试清除已到期或即将到期的 Key, 当此方法处理 后, 仍然到达最大内存设置, 将无法再进行写入操作, 但仍然可以进行读取操作. Redis 新的 vm 机制, 会把 Key 存放内存, Value 会存放在 swap 区 |
| appendonly no                   | 指定是否在每次更新操作后进行日志记录, Redis 在默认情况下是异步的把数据写入磁盘, 如果不开启, 可能会在断电时导致一段时间内的数据丢失. 因为 redis 本身同步数据文件是按上面 save 条件来同步的, 所以有的数据会在一段时间内只存在于内存中. 默认为 no |
| appendfilename appendonly.aof   | 指定更新日志文件名, 默认为 appendonly.aof                    |
| appendfsync everysec            | 指定更新日志条件, 共有 3 个可选值: **no**: 表示等操作系统进行数据缓存同步到磁盘(快); **always**：表示每次更新操作后手动调用 fsync() 将数据写到磁盘(慢,安全);  **everysec**：表示每秒同步一次(折中, 默认值) |
| vm-enabled no                   | 指定是否启用虚拟内存机制, 默认值为 no, VM 机制将数据分页存放, 由 Redis 将访问量较少的页即冷数据 swap 到磁盘上, 访问多的页面由磁盘自动换出到内存中 |
| vm-swap-file /tmp/redis.swap    | 虚拟内存文件路径, 默认值为 /tmp/redis.swap, 不可多个 Redis 实例共享 |
| vm-max-memory 0                 | 将所有大于 vm-max-memory 的数据存入虚拟内存, 无论 vm-max-memory 设置多小, 所有索引数据都是内存存储的(Redis 的索引数据 就是 keys), 也就是说, 当 vm-max-memory 设置为 0 的时候, 其实是所有 value 都存在于磁盘. 默认值为 0 |
| vm-page-size 32                 | Redis swap 文件分成了很多的 page, 一个对象可以保存在多个 page 上面, 但一个 page 上不能被多个对象共享, vm-page-size 是要根据存储的 数据大小来设定的, 建议如果存储很多小对象, page 大小最好设置为 32 或者 64bytes；如果存储很大大对象, 则可以使用更大的 page, 如果不确定, 就使用默认值 |
| vm-pages 134217728              | 设置 swap 文件中的 page 数量, 由于页表(一种表示页面空闲或使用的 bitmap)是在放在内存中的, 在磁盘上每 8 个 pages 将消耗 1byte 的内存 |
| vm-max-threads 4                | 设置访问 swap 文件的线程数, 最好不要超过机器的核数, 如果设置为 0, 那么所有对swap 文件的操作都是串行的, 可能会造成比较长时间的延迟. 默认值为4 |
| glueoutputbuf yes               | 设置在向客户端应答时, 是否把较小的包合并为一个包发送, 默认为开启 |
| activerehashing yes             | 指定是否激活重置哈希, 默认为开启                             |
| include /path/to/local.conf     | 指定包含其它的配置文件, 可以在同一主机上多个Redis实例之间使用同一份配置文件, 而同时各个实例又拥有自己的特定配置文件 |



## 三. Redis 数据类型

> Redis 支持五种数据类型：string(字符串), hash(哈希), list(列表), set(集合) 及 zset(sorted set: 有序集合).

|      类型       | 简介                                                       | 特性                                                         | 场景                                                         |
| :-------------: | :--------------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| String (字符串) | 二进制安全, 可以是字符串,整数或浮点数                      | 可以包含任何数据, 比如 jpg 图片或者序列化的对象, 一个键最大能存储 512M | ---                                                          |
|   Hash (字典)   | 键值对集合,即编程语言中的Map类型                           | 适合存储对象, 并且可以像数据库中 update一个属性一样只修改某一项属性值(Memcached中需要取出整个字符串反序列化成对象修改完再序列化存回去) | 存储,读取,修改用户属性                                       |
|   List (列表)   | 链表(双向链表)                                             | 增删快, 提供了操作某一段元素的API                            | 最新消息排行等功能(比如朋友圈的时间线);  消息队列            |
|   Set (集合)    | 哈希表实现,元素不重复                                      | 添加,删除,查找的复杂度都是O(1);  为集合提供了求交集, 并集, 差集等操作 | 共同好友;  利用唯一性,统计访问网站的所有独立ip;  好友推荐时,根据tag求交集,大于某个阈值就可以推荐 |
| zset (有序集合) | 将 Set 中的元素增加一个权重参数 score, 元素按score有序排列 | 数据插入集合时, 已经进行天然排序                             | 排行榜;  带权重的消息队列                                    |

### 1. String (字符串)

> string 类型是二进制安全的. 意思是 redis 的 string 可以包含任何数据. 比如 jpg 图片或者序列化的对象. 
>
> string 类型是 Redis 最基本的数据类型, string 类型的值最大能存储 512MB. 
>
> string 类型可以存储 字符串,整形,浮点型 数据

```shell
redis 127.0.0.1:6379> SET runoob "菜鸟教程"
OK
redis 127.0.0.1:6379> GET runoob
"菜鸟教程"
```

### 2. Hash (哈希)

> Redis hash 是一个键值 (key=>value) 对集合.
>
> Redis hash 是一个 string 类型的 field 和 value 的映射表, hash 特别适合用于存储对象.

```shell
redis 127.0.0.1:6379> DEL runoob
redis 127.0.0.1:6379> HMSET runoob field1 "Hello" field2 "World"
"OK"
redis 127.0.0.1:6379> HGET runoob field1
"Hello"
redis 127.0.0.1:6379> HGET runoob field2
"World"
```

### 3. List (列表)

> Redis 列表是简单的字符串列表, 按照插入顺序排序. 你可以添加元素到列表的头部(左边)或者尾部(右边)
>
> 列表最多可存储 2^32^ - 1 个元素 (4294967295, 每个列表可存储 40 多亿)

```shell
redis 127.0.0.1:6379> DEL runoob
redis 127.0.0.1:6379> lpush runoob redis
(integer) 1
redis 127.0.0.1:6379> lpush runoob mongodb
(integer) 2
redis 127.0.0.1:6379> lpush runoob rabitmq
(integer) 3
redis 127.0.0.1:6379> lrange runoob 0 10
1) "rabitmq"
2) "mongodb"
3) "redis"
```

### 4. Set (集合)

> Redis 的 Set 是 string 类型的无序集合.
>
> 集合是通过哈希表实现的, 所以添加, 删除, 查找的复杂度都是 O(1)
>
> 集合最多可存储 2^32^ - 1 个元素 (4294967295, 每个集合可存储 40 多亿)

#### 4.1 sadd 命令

> 添加一个 string 元素到 key 对应的 set 集合中, 成功返回 1, 如果元素已经在集合中返回 0
>
> ```shell
> sadd <key> <member>
> ```

```shell
redis 127.0.0.1:6379> DEL runoob
redis 127.0.0.1:6379> sadd runoob redis
(integer) 1
redis 127.0.0.1:6379> sadd runoob mongodb
(integer) 1
redis 127.0.0.1:6379> sadd runoob rabitmq
(integer) 1
redis 127.0.0.1:6379> sadd runoob rabitmq
(integer) 0
redis 127.0.0.1:6379> smembers runoob
1) "redis"
2) "rabitmq"
3) "mongodb"
```

### 5. zset (sorted set: 有序集合)

> Redis zset 和 set 一样也是 string 类型元素的集合, 且不允许重复的成员
>
> 不同的是每个元素都会关联一个 double 类型的分数. redis 正是通过分数来为集合中的成员进行从小到大的排序
>
> zset 的成员是唯一的, 但分数 (score) 却可以重复

#### 5.1 zadd 命令

> 添加元素到集合, 元素在集合中存在则更新对应score
>
> ```shell
> zadd <key> <score> <member> 
> ```

```shell
redis 127.0.0.1:6379> DEL runoob
redis 127.0.0.1:6379> zadd runoob 0 redis
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 mongodb
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 rabitmq
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 rabitmq
(integer) 0
redis 127.0.0.1:6379> > ZRANGEBYSCORE runoob 0 1000
1) "mongodb"
2) "rabitmq"
3) "redis"
```



## 四. Redis 命令

> Redis 命令用于在 redis 服务上执行操作
>
> 要在 redis 服务上执行命令需要一个 redis 客户端. Redis 客户端在下载的的 redis 的安装包中

### 1. 连接 Redis

> 启动 redis 服务器, 打开终端并输入命令 **redis-cli**, 该命令会连接本地的 redis 服务
>
> ```shell
> # linux
> $ redis-cli
> # windows
> C:\Users\user> redis-cli
> ```
>
> 在远程服务上执行命令
>
> ```shell
> $ redis-cli -h <host> -p <port> -a <password>
> ```
>
> ```shell
> # 连接到主机为 127.0.0.1, 端口为 6379 , 密码为 mypass 的 redis 服务上
> $redis-cli -h 127.0.0.1 -p 6379 -a "mypass"
> redis 127.0.0.1:6379>
> redis 127.0.0.1:6379> PING
> ```

### 2. 键命令 (Key)

> Redis 键命令用于管理 redis 的键
>
> ```shell
> <command> <key_name>
> ```

Redis keys 命令(大写为命令名, 小写为参数)

| 命令                                      | 描述                                                         |
| :---------------------------------------- | :----------------------------------------------------------- |
| GET key                                   | 获取指定 key 的值                                            |
| SET key value                             | 设置指定 key 的值                                            |
| DEL key                                   | 该命令用于在 key 存在时删除 key.                             |
| DUMP key                                  | 序列化给定 key , 并返回被序列化的值.                         |
| EXISTS key                                | 检查给定 key 是否存在.                                       |
| EXPIRE key seconds                        | seconds 为给定 key 设置过期时间, 以秒计.                     |
| EXPIREAT key timestamp                    | EXPIREAT 的作用和 EXPIRE 类似, 都用于为 key 设置过期时间.  不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp). |
| PEXPIRE key milliseconds                  | 设置 key 的过期时间以毫秒计.                                 |
| PEXPIREAT key milliseconds-timestamp      | 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计           |
| KEYS pattern                              | 查找所有符合给定模式( pattern)的 key .                       |
| MOVE key db                               | 将当前数据库的 key 移动到给定的数据库 db 当中.               |
| PERSIST key                               | 移除 key 的过期时间, key 将持久保持.                         |
| PTTL key                                  | 以毫秒为单位返回 key 的剩余的过期时间.                       |
| TTL key                                   | 以秒为单位, 返回给定 key 的剩余生存时间(TTL, time to live).  |
| RANDOMKEY                                 | 从当前数据库中随机返回一个 key .                             |
| RENAME key newkey                         | 修改 key 的名称                                              |
| RENAMENX key newkey                       | 仅当 newkey 不存在时, 将 key 改名为 newkey .                 |
| SCAN cursor [MATCH pattern] [COUNT count] | 迭代数据库中的数据库键.                                      |
| TYPE key                                  | 返回 key 所储存的值的类型.                                   |

### 3. 字符串命令 (String)

> Redis 字符串数据类型的相关命令用于管理 redis 字符串值

Redis String 命令(大写为命令名, 小写为参数)

| 命令                             | 描述                                                         |
| :------------------------------- | :----------------------------------------------------------- |
| GET key                          | 获取指定 key 的值                                            |
| SET key value                    | 设置指定 key 的值                                            |
| GETRANGE key start end           | 返回 key 中字符串值的子字符                                  |
| GETSET key value                 | 将给定 key 的值设为 value , 并返回 key 的旧值(old value).    |
| GETBIT key offset                | 对 key 所储存的字符串值, 获取指定偏移量上的位(bit).          |
| MGET key1 [key2..]               | 获取所有(一个或多个)给定 key 的值.                           |
| SETBIT key offset value          | 对 key 所储存的字符串值, 设置或清除指定偏移量上的位(bit).    |
| SETEX key seconds value          | 将值 value 关联到 key , 并将 key 的过期时间设为 seconds (以秒为单位). |
| SETNX key value                  | 只有在 key 不存在时设置 key 的值.                            |
| SETRANGE key offset value        | 用 value 参数覆写给定 key 所储存的字符串值, 从偏移量 offset 开始. |
| STRLEN key                       | 返回 key 所储存的字符串值的长度.                             |
| MSET key value [key value ...]   | 同时设置一个或多个 key-value 对.                             |
| MSETNX key value [key value ...] | 同时设置一个或多个 key-value 对, 当且仅当所有给定 key 都不存在. |
| PSETEX key milliseconds value    | 这个命令和 SETEX 命令相似, 但它以毫秒为单位设置 key 的生存时间, 而不是像 SETEX 命令那样, 以秒为单位. |
| INCR key                         | 将 key 中储存的数字值增一.                                   |
| INCRBY key increment             | 将 key 所储存的值加上给定的增量值(increment) .               |
| INCRBYFLOAT key increment        | 将 key 所储存的值加上给定的浮点增量值(increment) .           |
| DECR key                         | 将 key 中储存的数字值减一.                                   |
| DECRBY key decrement             | key 所储存的值减去给定的减量值(decrement) .                  |
| APPEND key value                 | 如果 key 已经存在并且是一个字符串,  APPEND 命令将指定的 value 追加到该 key 原来值(value)的末尾. |

### 4. 哈希命令 (Hash)

> Redis hash 是一个 string 类型的 field 和 value 的映射表, hash 特别适合用于存储对象. 
>
> Redis 中每个 hash 可以存储 2^32^ - 1 键值对(40多亿)

Redis Hash 命令(大写为命令名, 小写为参数)

| 命令                                           | 描述                                                    |
| :--------------------------------------------- | :------------------------------------------------------ |
| HMSET key field1 value1 [field2 value2]        | 设置指定 key 一个或对个 键值对                          |
| HDEL key field1 [field2]                       | 删除一个或多个哈希表字段                                |
| HEXISTS key field                              | 查看哈希表 key 中, 指定的字段是否存在.                  |
| HGET key field                                 | 获取存储在哈希表中指定 key 的指定字段的值.              |
| HGETALL key                                    | 获取在哈希表中指定 key 的所有字段和值                   |
| HINCRBY key field increment                    | 为哈希表 key 中的指定字段的整数值加上增量 increment .   |
| HINCRBYFLOAT key field increment               | 为哈希表 key 中的指定字段的浮点数值加上增量 increment . |
| HKEYS key                                      | 获取所有哈希表中的字段                                  |
| HLEN key                                       | 获取哈希表中字段的数量                                  |
| HMGET key field1 [field2]                      | 获取所有给定字段的值                                    |
| HSET key field value                           | 将哈希表 key 中的字段 field 的值设为 value .            |
| HSETNX key field value                         | 只有在字段 field 不存在时, 设置哈希表字段的值.          |
| HVALS key                                      | 获取哈希表中所有值.                                     |
| HSCAN key cursor [MATCH pattern] [COUNT count] | 迭代哈希表中的键值对.                                   |

### 5. 列表命令 (List)

> Redis 列表是简单的字符串列表, 按照插入顺序排序. 你可以添加元素到列表的头部(左边)或者尾部(右边)
>
> 一个列表最多可以包含 2^32^ - 1 个元素 (4294967295, 每个列表超过40亿个元素). 

Redis List 命令(大写为命令名, 小写为参数)

| 命令                                  | 描述                                                         |
| :------------------------------------ | :----------------------------------------------------------- |
| BLPOP key1 [key2] timeout             | 移出并获取列表的第一个元素,  如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止. |
| BRPOP key1 [key2 ] timeout            | 移出并获取列表的最后一个元素,  如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止. |
| BRPOPLPUSH source destination timeout | 从列表中弹出一个值, 将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止. |
| LINDEX key index                      | 通过索引获取列表中的元素                                     |
| LINSERT key BEFORE\|AFTER pivot value | 在列表的元素前或者后插入元素                                 |
| LLEN key                              | 获取列表长度                                                 |
| LPOP key                              | 移出并获取列表的第一个元素                                   |
| LPUSH key value1 [value2]             | 将一个或多个值插入到列表头部                                 |
| LPUSHX key value                      | 将一个值插入到已存在的列表头部                               |
| LRANGE key start stop                 | 获取列表指定范围内的元素                                     |
| LREM key count value                  | 移除列表元素                                                 |
| LSET key index value                  | 通过索引设置列表元素的值                                     |
| LTRIM key start stop                  | 对一个列表进行修剪(trim), 就是说, 让列表只保留指定区间内的元素, 不在指定区间之内的元素都将被删除. |
| RPOP key                              | 移除列表的最后一个元素, 返回值为移除的元素.                  |
| RPOPLPUSH source destination          | 移除列表的最后一个元素, 并将该元素添加到另一个列表并返回     |
| RPUSH key value1 [value2]             | 在列表中添加一个或多个值                                     |
| RPUSHX key value                      | 为已存在的列表添加值                                         |

### 6. 集合命令 (Set)

Redis Set 命令(大写为命令名, 小写为参数)

| 命令                                           | 描述                                                |
| :--------------------------------------------- | :-------------------------------------------------- |
| SADD key member1 [member2]                     | 向集合添加一个或多个成员                            |
| SCARD key                                      | 获取集合的成员数                                    |
| SDIFF key1 [key2]                              | 返回给定所有集合的差集                              |
| SDIFFSTORE destination key1 [key2]             | 返回给定所有集合的差集并存储在 destination 中       |
| SINTER key1 [key2]                             | 返回给定所有集合的交集                              |
| SINTERSTORE destination key1 [key2]            | 返回给定所有集合的交集并存储在 destination 中       |
| SISMEMBER key member                           | 判断 member 元素是否是集合 key 的成员               |
| SMEMBERS key                                   | 返回集合中的所有成员                                |
| SMOVE source destination member                | 将 member 元素从 source 集合移动到 destination 集合 |
| SPOP key                                       | 移除并返回集合中的一个随机元素                      |
| SRANDMEMBER key [count]                        | 返回集合中一个或多个随机数                          |
| SREM key member1 [member2]                     | 移除集合中一个或多个成员                            |
| SUNION key1 [key2]                             | 返回所有给定集合的并集                              |
| SUNIONSTORE destination key1 [key2]            | 所有给定集合的并集存储在 destination 集合中         |
| SSCAN key cursor [MATCH pattern] [COUNT count] | 迭代集合中的元素                                    |



### 7. 有序集合 (sorted set)

Redis Sorted Set 命令(大写为命令名, 小写为参数)	

| 命令                                           | 描述                                                         |
| :--------------------------------------------- | :----------------------------------------------------------- |
| ZADD key score1 member1 [score2 member2]       | 向有序集合添加一个或多个成员, 或者更新已存在成员的分数       |
| ZCARD key                                      | 获取有序集合的成员数                                         |
| ZCOUNT key min max                             | 计算在有序集合中指定区间分数的成员数                         |
| ZINCRBY key increment member                   | 有序集合中对指定成员的分数加上增量 increment                 |
| ZINTERSTORE destination numkeys key [key ...]  | 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中 |
| ZLEXCOUNT key min max                          | 在有序集合中计算指定字典区间内成员数量                       |
| ZRANGE key start stop [WITHSCORES]             | 通过索引区间返回有序集合指定区间内的成员                     |
| ZRANGEBYLEX key min max [LIMIT offset count]   | 通过字典区间返回有序集合的成员                               |
| ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT] | 通过分数返回有序集合指定区间内的成员                         |
| ZRANK key member                               | 返回有序集合中指定成员的索引                                 |
| ZREM key member [member ...]                   | 移除有序集合中的一个或多个成员                               |
| ZREMRANGEBYLEX key min max                     | 移除有序集合中给定的字典区间的所有成员                       |
| ZREMRANGEBYRANK key start stop                 | 移除有序集合中给定的排名区间的所有成员                       |
| ZREMRANGEBYSCORE key min max                   | 移除有序集合中给定的分数区间的所有成员                       |
| ZREVRANGE key start stop [WITHSCORES]          | 返回有序集中指定区间内的成员, 通过索引, 分数从高到低         |
| ZREVRANGEBYSCORE key max min [WITHSCORES]      | 返回有序集中指定分数区间内的成员, 分数从高到低排序           |
| ZREVRANK key member                            | 返回有序集合中指定成员的排名, 有序集成员按分数值递减(从大到小)排序 |
| ZSCORE key member                              | 返回有序集中, 成员的分数值                                   |
| ZUNIONSTORE destination numkeys key [key ...]  | 计算给定的一个或多个有序集的并集, 并存储在新的 key 中        |
| ZSCAN key cursor [MATCH pattern] [COUNT count] | 迭代有序集合中的元素(包括元素成员和元素分值)                 |

### 8. 基数统计 (HyperLogLog)

> 概念: 比如数据集 {1, 3, 5, 7, 5, 7, 8},  那么这个数据集的基数集为 {1, 3, 5 ,7, 8}, 基数(不重复元素)为5.  基数估计就是在误差可接受的范围内, 快速计算基数. 
>
> Redis 在 2.8.9 版本添加了 HyperLogLog 结构. 
>
> Redis HyperLogLog 是用来做基数统计的算法, HyperLogLog 的优点是, 在输入元素的数量或者体积非常非常大时, 计算基数所需的空间总是固定 的,并且是很小的. 
>
> 在 Redis 里面, 每个 HyperLogLog 键只需要花费 12 KB 内存, 就可以计算接近 2^64^ 个不同元素的基 数. 这和计算基数时, 元素越多耗费内存就越多的集合形成鲜明对比. 
>
> 但是, 因为 HyperLogLog 只会根据输入元素来计算基数, 而不会储存输入元素本身, 所以 HyperLogLog 不能像集合那样, 返回输入的各个元素. 

```shell
redis 127.0.0.1:6379> PFADD runoobkey "redis"
1) (integer)1

redis 127.0.0.1:6379> PFADD runoobkey "mongodb"
1) (integer) 1

redis 127.0.0.1:6379> PFADD runoobkey "mysql"
1) (integer) 1

redis 127.0.0.1:6379> PFCOUNT runoobkey
(integer) 3
```

Redis HyperLogLog 命令(大写为命令名, 小写为参数)

| 命令                                      | 描述                                      |
| :---------------------------------------- | :---------------------------------------- |
| PFADD key element [element ...]           | 添加指定元素到 HyperLogLog 中.            |
| PFCOUNT key [key ...]                     | 返回给定 HyperLogLog 的基数估算值.        |
| PFMERGE destkey sourcekey [sourcekey ...] | 将多个 HyperLogLog 合并为一个 HyperLogLog |



## 五. Redis 事务

> Redis 事务可以一次执行多个命令,  并且带有以下三个重要的保证：
>
> 1. 批量操作在发送 EXEC 命令前被放入队列缓存. 
> 2. 收到 EXEC 命令后进入事务执行, 事务中任意命令执行失败, 其余的命令依然被执行. 
> 3. 在事务执行过程, 其他客户端提交的命令请求不会插入到事务执行命令序列中. 
>
> 一个事务从开始到执行会经历以下三个阶段：
>
> 1. 开始事务
> 2. 命令入队
> 3. 执行事务

### 1. 实例

> 以下是一个事务的例子,  它先以 MULTI 开始一个事务,  然后将多个命令入队到事务中,  最后由 EXEC 命令触发事务,  一并执行事务中的所有命令

```shell
redis 127.0.0.1:6379> MULTI
OK
redis 127.0.0.1:6379> SET book-name "Mastering C++ in 21 days"
QUEUED
redis 127.0.0.1:6379> GET book-name
QUEUED
redis 127.0.0.1:6379> SADD tag "C++" "Programming" "Mastering Series"
QUEUED
redis 127.0.0.1:6379> SMEMBERS tag
QUEUED
redis 127.0.0.1:6379> EXEC
1) OK
2) "Mastering C++ in 21 days"
3) (integer) 3
4) 1) "Mastering Series"
   2) "C++"
   3) "Programming"
```

单个 Redis 命令的执行是原子性的, 但 Redis 没有在事务上增加任何维持原子性的机制, 所以 Redis 事务的执行并不是原子性的. 

事务可以理解为一个打包的批量执行脚本, 但批量指令并非原子化的操作, 中间某条指令的失败不会导致前面已做指令的回滚, 也不会造成后续的指令不做. 



### 2. Redis 事务命令

Redis 事务命令(大写为命令名, 小写为参数)

| 命令                | 描述                                                         |
| :------------------ | :----------------------------------------------------------- |
| DISCARD             | 取消事务, 放弃执行事务块内的所有命令.                        |
| EXEC                | 执行所有事务块内的命令.                                      |
| MULTI               | 标记一个事务块的开始.                                        |
| UNWATCH             | 取消 WATCH 命令对所有 key 的监视.                            |
| WATCH key [key ...] | 监视一个(或多个) key , 如果在事务执行之前这个(或这些) key 被其他命令所改动, 那么事务将被打断. |