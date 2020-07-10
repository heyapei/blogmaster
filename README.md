# 2020年5月31日 管理后台程序 开启

```$xslt
2020年7月10日
我放弃restful风格了，对我来说限制有点多

00点26分
Java代码12940行

新增了活动管理的页面 目前只做了简单的页面展示以及创建人以及公司信息的展示
```


```$xslt
2020年7月10日
bootstrap中table如果太长了还真的没法自适应好像
需要用div加上标签包裹起来这样table才会自适应
<div class="table-responsive">
table的内容全在这个里面
</div>


自定义了一个等待弹窗 
showMyCustomLoading

```


```$xslt

计划：
1. 活动管理
    活动列表要有的
    活动列表展示完全后 要有对配置文件进行管理的界面（使用模态框/新开页面）
    要有进入对应作品界面的入口
2. 作品管理
    作品列表展示
    作品管理
    

2020年7月9日
用户管理页面 主要涉及 分页 和 页码 以及复制 图片展示 和 查询实体等内容

分离了返回数据类型 优化了MyResultVO ，因为以前的实在是太难用了 每次都要写老长的数据


2020年7月9日
想要思考一个问题 
是不是vue可以做到如果数据一旦发生变化不需要刷新整个页面就可以做到改变页面的显示呢
如果使用js那么只能是根据ID或者是name很麻烦的去改变某个数据的值以达到显示正确结果的作用

```

```$xslt
2020年7月8日
添加了退出功能
```

```$xslt
修复了一些小问题 但是也有一个问题，就是数据库会在八小时后自动断开链接
```

```$xslt
2020年6月25日 01点03分
加入了天气功能
```


```$xslt
2020年6月23日23点40分
学会读取文件json数据，这样就不用存入数据库了
 /**
     * 读取本地json文件中的数据
     */
    @Value("classpath:file/areacode/city.json")
    private Resource demo;
    @Test
    public void demo() {
        try {
            String areaData = IOUtils.toString(demo.getInputStream(), String.valueOf(Charset.forName("UTF-8")));
            log.info("查询结果：{}", areaData);
            List<AreaCode> areaCodes = JSONArray.parseArray(areaData, AreaCode.class);
            log.info("查询结果：{}", areaCodes);
        } catch (IOException e) {
            log.error("", e);
        }

    }
今天还为这个项目加了很多工具类
```


```$xslt
2020年6月21日 23点19分
多数据源的设置
1. 要关闭springboot启动类上加载的数据源管理  
2. 然后就是配置文件中要写上多个数据源的地址和配置
3. 编写数据源的指定配置类，这个类的作用是为了指定各自的mapper使用的是那个数据源
问题：多数据源是应该可以了 但是druid的监控功能丢失了 
问题2：事务的功能还没有试过 不知道事务行不行
```



```$xslt
2020年6月21日

为了解决shiro不能使用问题
更换了shiro-redis版本
 <dependency>
            <groupId>org.crazycake</groupId>
            <artifactId>shiro-redis</artifactId>
            <version>3.2.3</version>
</dependency>
其中redisManager没有设置过期时间的地方了，然后需要设置一下redisManager的过期时间
新版本 作者说是redis的过期时间已经设置为-2会跟着session过期时间走的 不过还需要时间验证

问题2：
thymeleaf无法使用权限列表 主要的问题是shiro不主动去读取数据
这个问题主要是shiro每次只会做一次授权
下一次授权信息就直接从缓存中读取了，所以就会一直出现我拿不到授权的情况
清空redis重来就好了 如果到时候有退出了就可以用代码来实现了


```


```$xslt
2020年6月20日
学会使用thymeleaf引入公用js和css

```

```$xslt
学会使用thymeleaf的公共部分内容的引用
	<!--引用公共的bootstrap的内容-->
	<head th:include="/inc/bootstrap :: bootstrap"></head>
这里引用的的是js和css文件
如果需要引用html文件
则直接是使用
<div  th:include="/inc/copyright"></div>
```

```$xslt
下一步是集成shiro框架 使用这个框架做权限判断
```