# 2020年5月31日 管理后台程序 开启

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