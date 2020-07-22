# 2020年5月31日 管理后台程序 开启


```$xslt
2020年7月22日
添加了ie支持html5的属性的js 不过还没看出来如何使用
      <head th:include="/inc/iehtml5 :: iehtml5"></head>
添加了sweetAlert2的支持（好看的弹窗）
    <head th:include="/inc/commonutil/sweetAlert :: sweetAlert"></head>

```

```$xslt
2020年7月21日
在MyDateUtil方法类中添加了date和local时间互转的方法 为了前端能够顺利的使用h5的datetime-local属性

如果需要判断还需要使用到枚举类中的值 那么可以这样
<tr th:if="${activeDetailWithConfOrgVO?.activeConfSignUp == T(com.hyp.blogmaster.pojo.modal.WeixinVoteConf.ActiveConfSignUpEnum).CAN_SIGN_UP.code}">


使用thymeleaf动态刷新内容
2020年7月21日

这里主要是想讲一下thymeleaf是如何遍历枚举类型的

1. 首先需要创建一个枚举类型的数据  

```Java
public enum StatusEnum {
        NO(1, "你好"),
        YES(2, "大家在哪");
        private Integer code;
        private String msg;
   		//要是静态方法的	
    	public static String getEnumMsg(Integer code) {
            if (code == null) {
                return "";
            }
            for (StatusEnum statusEnum : StatusEnum.values()) {
                if (code.equals(statusEnum.getCode())) {
                    return statusEnum.getMsg();
                }
            }
            return "未定义";
        }
        @Override
        public String toString() {
            return "statusEnum{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
        public Integer getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
        StatusEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }   
```

2. 前端页面中进行显示

```html
   <div class="container">
       <div class="row clearfix">
           <div class="col-md-12 column">
               <h2>创建模态框（Modal）</h2>
               <!-- 按钮触发模态框 -->
               <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                   开始演示模态框
               </button>
               <!-- 模态框（Modal） -->
               <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                   &times;
                               </button>
                               <h4 class="modal-title" id="myModalLabel">
                                   模态框（Modal）标题
                               </h4>
                           </div>
                            <!-- 这样可以调用方法中的内容 但是这个getEnumMsg方法是需要为static静态方法-->
                            <p th:text="${T(com.hyp.dataobject.Journal.StatusEnum).
                        getEnumMsg(myTestQuery?.getCode())}"></p>
                           <div class="modal-body">
                               <select class="form-control" name="messagePath" id="messagePath" >
                                   <option value="">-请选择-</option>
                                   <!-- 首先获取 全部类路径 然后找到该类中的遍历 然后获取其中的值 values() 
										然后定义个变量值进行接收，然后getCode和getMsg获取其中的值，
										myTestQuery这个实体类是查询用的数据getCode 然后判断是否进行回显数据 -->
                                   <option th:each="enum : ${T(com.hyp.dataobject.Journal.StatusEnum).values()}"
                                           th:value="${enum.getCode()}"
                                           th:text="${enum.getMsg()}"
                                           th:selected="${myTestQuery?.getCode() == enum.getCode()}">
                                   </option>
                               </select>
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                               </button>
                               <button type="button" class="btn btn-primary">
                                   提交更改
                               </button>
                           </div>
                       </div><!-- /.modal-content -->
                   </div><!-- /.modal -->
               </div>
           </div>
       </div>
   </div>
```

3.  接口数据

```java 
// 该接口不能使用restful的风格 因为是需要返回的html内容
 @RequestMapping(value = "/jqueryLoad")
    public String jqueryLoad(ModelMap map) {
        MyTestQuery myTestQuery = new MyTestQuery();
        myTestQuery.setCode(1);
        myTestQuery.setMsg("随便写写");
        map.put("myTestQuery", myTestQuery);
        return "myBootstrapTest/jqueryLoad";
    }
```

4. 局部数据刷新
```
// 第一步是要有一个接口
@RequestMapping(value="/test",method=RequestMethod.POST)
public String aaa(Model model) {
    List<ArticleType> articleTypes = articleTypeService.selectLeafArticleTypes();
    ArticleType a = new ArticleType();
    model.addAttribute("articleTypes",articleTypes);     
    return "write_article::article_type";
}
注意返回值是write_article::article_type，write_article是视图名称（html文件名称）,article_type是fragment的名称。这样就只是填充article_type的数据，而不用刷新整个页面，达到动态刷新的目的。
// 第二步有一个页面进行显示
<div class="article_type" th:fragment="article_type">
    文章分类:
    <div th:each="articletype : ${articleTypes}">                      
	    <label class="checkbox-inline">
	        <input type="checkbox" th:text="${articletype.text}" id="inlineCheckbox1" value="option1"> 
	    </label>
    </div>
</div>
//第三步进行Ajax请求然后进行页面显示
$('#btn').click(function () {
    var url = '/blog/test';
    $.ajax({
        url: url,
        type: 'POST',
        success: function (data) {
            $(".article_type").html(data);
        }
    })
})
//其中，也可以这样进行局部刷新 load函数
<script>
    $('#btn').click(function () {
        var url = '/blog/test';
        $('.article_type').load(url);
    });
</script> 
```


```$xslt
2020年7月15日
活动内容的展示基本上完成了 没有什么问题了 
准备使用https://blog.csdn.net/qq_36688143/article/details/79447159这个方案 
在js中使用thymeleaf生成想要的html代码 看看能不能实现

```

```$xslt
2020年7月14日
bootstrap的模态框弹出来 如果关闭了aria-hidden="false" 这样就不会有那个外部的y轴的进度条了
好像也解决了页面抖动的问题 但是好像页面缩小了后还是会出现抖动问题 但是解决一点是一点吧

如果向模态框modal-body中添加该内容 就会出现y轴的进度条 前提是先指定模态框高度
  <div class="modal-body" style="height: 500px;overflow-y: scroll;">
下面这行代码可以出现x轴和y轴两个轴的进度条
 style="width:100%; height:300px;overflow:auto;"

jquery遍历数组
let activeDescImgS = response.data.activeDescImgS;
// activeDescImgS的格式是： [1,2,3,4,5,6,7,8,9];
// index 是数组下标 value是具体值
                        $.each(activeDescImgS, function (index, value) {
                            alert(index + "..." + value);
                        });

```

```$xslt
2020年7月12日
因为spring的定时任务执行是在Autowired之前的，所以需要ApplicationContextForTaskUtil对上下文进行处理
WeixinManagerEmailReceiveService weixinManagerEmailReceiveService = 
(WeixinManagerEmailReceiveService) ApplicationContextForTaskUtil.getBean("weixinManagerEmailReceiveService");
其中getBean方法给的值是@Service("weixinManagerEmailReceiveService")
通过名字进行查找

不知道是我不会还是怎么回事 我没法使用imap拉取邮件

原来thymeleaf还可以直接读取枚举类型的数据
我的实体类是：
这个是直接写在WeixinManagerEmailReceive这个类里面的 然后这个枚举有个方法获取msg
 public enum ReceiveEmailHasDeleteEnum {

        NO_DELETE(0, "未删除"),
        HAS_DELETE(1, "已删除");

        public static String getEnumMsg(Integer code) {
            for (ReceiveEmailHasDeleteEnum receiveEmailHasDeleteEnum : ReceiveEmailHasDeleteEnum.values()) {
                if (code.equals(receiveEmailHasDeleteEnum.getCode())) {
                    return receiveEmailHasDeleteEnum.getMsg();
                }
            }
            return "未定义";
        }
}
在前端模板引擎中直接就可以使用了
 <td th:text="${T(com.hyp.blogmaster.shiro.
pojo.modal.WeixinManagerEmailReceive.
ReceiveEmailHasDeleteEnum).getEnumMsg(weixinManagerEmailReceive?.
receiveEmailHasDelete)}">
                        </td>


thymeleaf真好用
这样的赋值方式也挺好
th:href="@{/admin/manager/email/replyAppoint(receiveEmailId=${weixinManagerEmailReceive?.getId()})}"

```



```$xslt
2020年7月12日01点21分
springboot开启定时任务是真的简单 比Linux上部署要快的多 直接跟着项目走 
直接在启动类上开启定时任务@EnableScheduling 然后在需要循环跑的方法上加上  @Scheduled(cron = "${crontab.task.loop.minute.five}")
这样就完成了 厉害了
Seconds (秒) ：可以用数字0－59 表示；
Minutes(分) ：可以用数字0－59 表示；
Hours(时) ：可以用数字0-23表示；
Day-of-Month(天) ：可以用数字1-31 中的任一一个值，但要注意一些特别的月份2月份没有只能1-28，有些月份没有31；
Month(月) ：可以用0-11 或用字符串 “JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV and DEC” 表示；
Day-of-Week(*每周*)*：*可以用数字1-7表示（1 ＝ 星期日）或用字符口串“SUN, MON, TUE, WED, THU, FRI and SAT”表示；
“/”：为特别单位，表示为“每”如“0/10”表示每隔10分钟执行一次,“0”表示为从“0”分开始, “3/20”表示表示每隔20分钟执行一次，“3”表示从第3分钟开始执行；
“?”：表示每月的某一天，或第周的某一天；
“L”：用于每月，或每周，表示为每月的最后一天，或每个月的最后星期几如“6L”表示“每月的最后一个星期五”；
“W”：表示为最近工作日，如“15W”放在每月（day-of-month）字段上表示为“到本月15日最近的工作日”；
“#”：是用来指定“的”每月第n个工作日,例 在每周（day-of-week）这个字段中内容为”6#3” or “FRI#3” 则表示“每月第三个星期五”；
“*” 代表整个时间段。
其中@Scheduled还有很多操作性
@Scheduled(fixedRate = 3000) ：上一次开始执行时间点之后 3 秒再执行（fixedRate 属性：定时任务开始后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
@Scheduled(fixedDelay = 3000) ：上一次执行完毕时间点之后 3 秒再执行（fixedDelay 属性：定时任务执行完成后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
@Scheduled(initialDelay = 1000, fixedRate = 3000) ：第一次延迟1秒后执行，之后按fixedRate的规则每 3 秒执行一次（initialDelay 属性：第一次执行定时任务的延迟时间，需配合fixedDelay或者fixedRate来使用）
@Scheduled(cron="0 0 2 1 * ? *") ：通过cron表达式定义规则
这是从网络上摘抄的内容
https://juejin.im/post/5da7405f6fb9a04e15328382
其中，常用的cron表达式有：
0 0 2 1 * ? * ：表示在每月 1 日的凌晨 2 点执行
0 15 10 ? * MON-FRI ：表示周一到周五每天上午 10:15 执行
0 15 10 ? 6L 2019-2020 ：表示 2019-2020 年的每个月的最后一个星期五上午 10:15 执行
0 0 10,14,16 * * ? ：每天上午 10 点，下午 2 点，4 点执行
0 0/30 9-17 * * ? ：朝九晚五工作时间内每半小时执行
0 0 12 ? * WED ：表示每个星期三中午 12 点执行
0 0 12 * * ? ：每天中午 12点执行
0 15 10 ? * * ：每天上午 10:15 执行
0 15 10 * * ? ：每天上午 10:15 执行
0 15 10 * * ? * ：每天上午 10:15 执行
0 15 10 * * ? 2019 ：2019 年的每天上午 10:15 执行


```

```$xslt
2020年7月11日17点24分
 前端日期不会转那就不转了 用后端做判断好了
保存邮件系统感觉可以了

现在发送简单不带图片的邮件是可以了 
另外需要注意的是 
from这样写   趣互动<15518901416@163.com>  这样邮件名字会好看点
问题：
shiro把我静态图片给拦截了  
然后就好了 估计是缓存问题把 因为我的确加了
filterChainDefinitionMap.put("/upload/**", "anon");

邮件中图片等资源链接地址一定要是完整的url

```


```$xslt
2020年7月11日 
查询条件添加一个默认值
配置了返回顶部的按钮
总代码 163457 
Java   12940
```


```$xslt
2020年7月10日
我放弃restful风格了，对我来说限制有点多

00点26分
Java代码12940行

新增了活动管理的页面 目前只做了简单的页面展示以及创建人以及公司信息的展示

JS中要是想要使用 thymeleaf的东西 需要两步走
1.   <script th:inline="javascript"> 
2.  /*<![CDATA[*/
     let organisersImgUrl = [[#{weixin.organisers.img.url}]];
   /*]]>*/
这样就获得了 messages.properties中的weixin.organisers.img.url内容
可以在下面的js中使用了
 
还添加modal窗口展示图片的功能
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