# 2020年5月31日 管理后台程序 开启

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