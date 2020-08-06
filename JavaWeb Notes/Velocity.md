## 一. Velocity 简介

> Velocity (VM) 模板引擎



## 二. Velocity 使用

> Velocity 的语法符号大概分二类
>
> - 一类用 # 开头, 代表控制符号, \#set 表示定义变量
> - 另一类用 $ 开头, 通常用于显示变量

### 1. 定义变量

> #set($var = value): 定义变量
>
> $var: 输出变量值

```velocity
#set($awbpre='112')
#set($awbno='89089011')
#set($airwayBillNo=$awbpre+' - '+$awbno)
$awbpre - $awbno <br/>
$airwayBillNo
```

### 2. 遍历数组

> #foreach($item in $list): 遍历数组, $velocityCount 为索引变量

```velocity
#set($list = ["CTU", "SHA", "LAX"])
#foreach ($item in $list)
	索引: $velocityCount, 值: $item <br/>
#end
```

### 3. 遍历 HashTable

```velocity
#foreach($key in $table.keySet())
	key: $key, value: $table.get($key)<br/>
#end
```

### 4. 判断是否为空

```velocity
## 判断集合是否为空
#if($null.isNull($orderList.orders) || $orderList.orders.size()==0)
	订单列表为空
#else
	订单列表: <br/>
	#foreach ($order in $orderList.orders)
		$velocityCount: $order.id / $order.clientName / $order.amount <br/>
	#end
#end
```

```velocity
## 判断对象是否为空
#if($(orderDto))
	订单对象有值
#else
	订单对象为空
#end 
#if(!$(orderDto))
	订单对象为空
#else
	订单对象有值
#end
```

### 5. 函数

```velocity
## 定义函数
#macro(renderOrderList $orders)
<table border="1">
    <tr>
        <th>Id</th>
        <th>ClientName</th>
        <th>Amount</th>
    </tr>
#foreach($o in $orders)
	<tr><td>$o.id</td><td>$o.clientName</td><td>$o.amount</td></tr>
#end
</table>
#end

## 调用函数
#renderOrderList($orderList.orders)
```

### 6. 数值, 日期格式化

```velocity
$order.createTime<br/>
$date.year - $date.month - $date.day <br/>
$date.format('yyyy-MM-dd HH:mm:ss',$order.createTime,$locale)<br/>  
$date.format('MMMdd',$order.createTime,$locale)<br/>    
$convert.toLocale("en_US") <br/>
$date.format('MMM,dd',$order.createTime,$convert.toLocale("en_US"))<br/>
$date.format('yyyy-MM-dd',$order.createTime,$locale)<br/>
$order.amount<br/>
$number.format('0.00',$order.amount)<br/>
```

### 7. 内置对象

> $request, $session 等内置对象

```velocity
$name = $request.getParameter("name")
```

## 三. velocity-tools

> 引入依赖

```xml
<dependency>
	<groupId>org.apache.velocity</groupId>
	<artifactId>velocity-tools</artifactId>
	<version>1.3</version>
</dependency>
```

