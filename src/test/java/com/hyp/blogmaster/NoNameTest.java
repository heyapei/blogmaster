package com.hyp.blogmaster;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyp.blogmaster.pojo.dto.weather.sojson.AreaCode;
import com.hyp.blogmaster.pojo.dto.weather.sojson.WeatherDTO;
import com.hyp.blogmaster.utils.MyHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/6/11 18:50
 * @Description: TODO
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class NoNameTest {


    @Autowired
    private MyHttpClientUtil httpClientUtil;
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();


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


    @Test
    public void testWeather() {
        String weatherReturn = httpClientUtil.getParameter("http://t.weather.sojson.com/api/weather/city/101020800", null, null, 2000, 2000, 2000);
        log.info("结果：{}", weatherReturn);
        WeatherDTO jsonRootBean = JSONObject.parseObject(weatherReturn, WeatherDTO.class);
        log.info("查询结果：{}", jsonRootBean);
    }

    @Before // 在方法开始前添加一个用户
    public void addUser() {
        simpleAccountRealm.addAccount("wmyskxz", "123456");
    }

    @Test
    public void testAuthentication() {

        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager); // 设置SecurityManager环境
        Subject subject = SecurityUtils.getSubject(); // 获取当前主体

        UsernamePasswordToken token = new UsernamePasswordToken("wmyskxz", "123456");
        subject.login(token); // 登录

        // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功
        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出true

        subject.logout(); // 登出

        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出false
    }
}
