### Arthas 

Arthas 是 Alibaba 开源的 Java 诊断工具 [github](https://alibaba.github.io/arthas/)

### WebConsole 通过浏览器连接 arthas

 http://127.0.0.1:3658

### 官网文档

https://arthas.aliyun.com/doc/

### 二. 安装

1. 直接一个可以启动的 jar 包然后用 java -jar 的方式启动

    https://alibaba.github.io/arthas/arthas-boot.jar

    https://arthas.gitee.io/arthas-boot.jar

2. 用官方提供的 as.sh 脚本一键安装
3. 用 rpm 的方式安装

### 三. 启动

用 java -jar 命令直接启动

```shell
[root@localhost ~]# java -jar arthas-boot.jar 
[INFO] arthas-boot version: 3.3.3
[INFO] Can not find java process. Try to pass <pid> in command line.
Please select an available pid.
```

但是这里启动失败了, 这是因为 arthas 在启动时会检测本机运行的 jvm 进程, 然后让用户选择需要绑定的进程, 后面的操作都是针对选定的进程的. 

这里先启动一个 java 应用, 然后再启动 arthas

```shell
[root@localhost ~]# java -jar arthas-boot.jar 
[INFO] arthas-boot version: 3.3.3
[INFO] Found existing java process, please choose one and input the serial number of the process, eg : 1. Then hit ENTER.
* [1]: 2467 jvm-0.0.1-SNAPSHOT.jar
```

下面就列出了本机正在运行的 java 进程, 等待用户输入, 这里输入1 然后回车. 如果是第一次启动需要下载一些必要的文, 等待下载完成即可

```shell
[root@localhost arthas]# java -jar arthas-boot.jar 
[INFO] arthas-boot version: 3.3.3
[INFO] Found existing java process, please choose one and input the serial number of the process, eg : 1. Then hit ENTER.
* [1]: 2467 jvm-0.0.1-SNAPSHOT.jar
1
[INFO] arthas home: /usr/local/arthas
[INFO] Try to attach process 2467
[INFO] Attach process 2467 success.
[INFO] arthas-client connect 127.0.0.1 3658
  ,---.  ,------. ,--------.,--.  ,--.  ,---.   ,---.                           
 /  O  \ |  .--. ''--.  .--'|  '--'  | /  O  \ '   .-'                          
|  .-.  ||  '--'.'   |  |   |  .--.  ||  .-.  |`.  `-.                          
|  | |  ||  |\  \    |  |   |  |  |  ||  | |  |.-'    |                         
`--' `--'`--' '--'   `--'   `--'  `--'`--' `--'`-----' 

wiki      https://alibaba.github.io/arthas                                      
tutorials https://alibaba.github.io/arthas/arthas-tutorials                     
version   3.3.3                                                                 
pid       2467                                                                  
time      2020-06-22 03:02:31                                                   

[arthas@2467]$
```

如果看到这个界面就表示启动并关联成功了

### 四. Arthas 命令

#### 1. 基础命令

| 命令    | 描述                                                         |
| ------- | ------------------------------------------------------------ |
| help    | 查看命令帮助信息                                             |
| cat     | 打印文件内容, 类 linux 的 cat                                |
| echo    | 打印参数, 类 linux 的 echo                                   |
| grep    | 匹配查找, 类 linux 的 grep                                   |
| tee     | 复制标准输入到标准输出和指定的文件, 类 linux 的 tee          |
| pwd     | 返回当前的工作目录, 类 linux 的 pwd                          |
| cls     | 清空当前屏幕区域                                             |
| session | 查看当前会话的信息                                           |
| reset   | 重置增强类, 将被 Arthas 增强过的类全部还原, Arthas 服务端关闭时会重置所有增强过的类 |
| version | 输出当前目标 Java 进程所加载的 Arthas 版本号                 |
| history | 打印命令历史                                                 |
| quit    | 退出当前 Arthas 客户端, 其他 Arthas 客户端不受影响           |
| stop    | 关闭 Arthas 服务端, 所有 Arthas 客户端全部退出               |
| keymap  | Arthas快捷键列表及自定义快捷键                               |

#### 2. jvm 相关

| 命令        | 描述                                            |
| ----------- | ----------------------------------------------- |
| dashboard   | 当前系统的实时数据面板                          |
| thread      | 查看当前 JVM 的线程堆栈信息                     |
| jvm         | 查看当前 JVM 的信息                             |
| sysprop     | 查看和修改 JVM 的系统属性                       |
| sysenv      | 查看JVM的环境变量                               |
| vmoption    | 查看和修改 JVM 里诊断相关的 option              |
| perfcounter | 查看当前 JVM 的 Perf Counter 信息               |
| logger      | 查看和修改 logger                               |
| getstatic   | 查看类的静态属性                                |
| ognl        | 执行 ognl 表达式                                |
| mbean       | 查看 Mbean 的信息                               |
| heapdump    | dump java heap, 类似 jmap 命令的 heap dump 功能 |

##### 2.1 dashboard

`dashboard [-i <val>] [-n <val>] `

参数: 

- -i    刷新实时数据的时间间隔 (ms), 默认5000ms

- -n    刷新实时数据的次数

显示数据说明

- ID: Java级别的线程ID, 注意这个ID不能跟 jstack 中的 nativeID 一一对应
- NAME: 线程名
- GROUP: 线程组名
- PRIORITY: 线程优先级, 1~10之间的数字, 越大表示优先级越高
- STATE: 线程的状态
- CPU%: 线程消耗的 cpu 占比, 采样 100ms, 将所有线程在这 100ms 内的 cpu 使用量求和, 再算出每个线程的 cpu 使用占比
- TIME: 线程运行总时间，数据格式为`分:秒`
- INTERRUPTED: 线程当前的中断位状态
- DAEMON: 是否是daemon线程

##### 2.2 thread

`thread [-b] [-i <val>] [-n <val>] [id]`

参数说明

- id    线程id
- -n    指定最忙的前N个线程并打印堆栈
- -b    找出当前阻塞其他线程的线程
- -i    指定cpu占比统计的采样间隔, 单位为毫秒

##### 2.3 jvm

THREAD 相关显示数据说明

- COUNT: JVM当前活跃的线程数
- DAEMON-COUNT: JVM 当前活跃的守护线程数
- PEAK-COUNT: 从 JVM 启动开始曾经活着的最大线程数
- STARTED-COUNT: 从 JVM 启动开始总共启动过的线程次数
- DEADLOCK-COUNT: JVM 当前死锁的线程数

文件描述符相关

- MAX-FILE-DESCRIPTOR-COUNT：JVM 进程最大可以打开的文件描述符数
- OPEN-FILE-DESCRIPTOR-COUNT：JVM 当前打开的文件描述符数

##### 2.4 sysprop

`sysprop [property-name] [property-value]`

```shell
# 查看所有系统属性
sysprop
# 查看单个属性
sysprop java.version
sysprop user.country
# 修改单个属性
sysprop user.country CN
```

##### 2.5 sysenv

`sysenv [env-name]`

```shell
# 查看所有环境属性
sysenv
# 查看单个属性
sysenv USER
```

##### 2.6 vmoption

`vmoption [name] [value]`

```shell
# 查看所有 option
vmoption
# 查看单个 option
vmoption PrintGCDetails
# 修改单个 option
vmoption PrintGCDetails true
```

##### 2.7 logger

`logger[-c <val>] [-l <val>] [-n <val>] `

```shell
# 查看所有 logger
logger
# 查看指定名字的 logger
logger -n org.springframework.web
# 查看指定 classloader 的 logger (使用 -c 需要先查看 ClassLoader 的 hashcode)
logger -c 2a139a55
logger --classLoaderClass sun.misc.Launcher$AppClassLoader
# 更新 level
logger -n ROOT -l debug
logger -c 2a139a55 -n ROOT -l debug
```



#### 3. class/classloader 相关

| 命令        | 描述                                                         |
| ----------- | ------------------------------------------------------------ |
| sc          | 查看 JVM 已加载的类信息                                      |
| sm          | 查看已加载类的方法信息                                       |
| jad         | 反编译指定已加载类的源码                                     |
| mc          | 内存编译器, 内存编译`.java`文件为`.class`文件                |
| redefine    | 加载外部的`.class`文件, redefine到JVM里                      |
| dump        | dump 已加载类的 byte code 到特定目录                         |
| classloader | 查看classloader的继承树, urls, 类加载信息, 使用 classloader 去 getResource |

#### 4. monitor/watch/trace 相关

> 请注意, 这些命令, 都通过字节码增强技术来实现的, 会在指定类的方法中插入一些切面来实现数据统计和观测, 因此在线上,预发使用时, 请尽量明确需要观测的类,方法以及条件, 诊断结束要执行`stop` 或将增强过的类执行 `reset` 命令

| 命令    | 描述                                                         |
| ------- | ------------------------------------------------------------ |
| monitor | 方法执行监控                                                 |
| watch   | 方法执行数据观测                                             |
| trace   | 方法内部调用路径, 并输出方法路径上的每个节点上耗时           |
| stack   | 输出当前方法被调用的调用路径                                 |
| tt      | 方法执行数据的时空隧道, 记录下指定方法每次调用的入参和返回信息, 并能对这些不同的时间下调用进行观测 |

#### 5. profiler 火焰图

| 命令     | 描述                                       |
| -------- | ------------------------------------------ |
| profiler | 使用 async-profiler 对应用采样，生成火焰图 |

#### 6. options

| 命令    | 描述                       |
| ------- | -------------------------- |
| options | 查看或设置 Arthas 全局开关 |