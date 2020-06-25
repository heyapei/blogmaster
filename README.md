# 2020年5月31日 管理后台程序 开启


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