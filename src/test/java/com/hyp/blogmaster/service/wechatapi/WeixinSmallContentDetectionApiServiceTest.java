package com.hyp.blogmaster.service.wechatapi;

import com.alibaba.fastjson.JSONObject;
import com.hyp.blogmaster.config.weixin.WeChatPropertiesValue;
import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.utils.MyHttpClientUtil;
import com.hyp.blogmaster.utils.redis.MyRedisUtil;
import com.hyp.blogmaster.utils.thumbnailator.MyThumbnailImgOptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/30 20:58
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@PropertySource("classpath:wechat.properties")
public class WeixinSmallContentDetectionApiServiceTest {

    @Autowired
    private WeixinSmallContentDetectionApiService weixinSmallContentDetectionApiService;

    /**
     * 获取token用url
     */
    @Value("${weixin.small.access.token.url}")
    private String GET_ACCESS_TOKEN_URL;

    /**
     * 将获取回来的token存入redis，
     */
    @Value("${weixin.small.access.token.redis.key}")
    private String ACCESS_TOKEN_REDIS_KEY;
    /**
     * 过期时间设置为6000s
     */
    @Value("${weixin.small.access.token.redis.expires.time}")
    private Long ACCESS_TOKEN_REDIS_EXPIRE;

    /**
     * 文字检测链接
     */
    @Value("${weixin.small.check.msg_sec_check.url}")
    private String MSG_SEC_CHECK_URL;


    /**
     * 图片检测链接
     */
    @Value("${weixin.small.check.img_sec_check.url}")
    private String IMG_SEC_CHECK_URL;

    private static final Integer IMG_SEC_CHECK_WIDTH = 750;
    private static final Integer IMG_SEC_CHECK_HEIGHT = 1334;
    private static final Float IMG_SEC_CHECK_QUALITY = 0.8f;


    /**
     * 获取回来的accessToken的json中key值
     */
    private final String JSONOBJECT_KEY_WEIXIN_ACCESS_TOKEN = "access_token";


    @Autowired
    private WeChatPropertiesValue weChatPropertiesValue;

    @Autowired
    private MyHttpClientUtil myHttpClientUtil;
    @Autowired
    private MyRedisUtil myRedisUtil;
    @Autowired
    private MyThumbnailImgOptionUtil myThumbnailImgOptionUtil;

    @Test
    public void checkImgSecCheckApi() {
    }

    @Test
    public void getImgSecCheckApiMsg() {
    }

    @Test
    public void checkMsgSecCheckApi() {
    }

    @Test
    public void getMsgSecCheckApiMsg() {
    }

    @Test
    public void getAccessToken() {

        //myRedisUtil.del(ACCESS_TOKEN_REDIS_KEY);
        JSONObject accessToken = weixinSmallContentDetectionApiService.getAccessToken();
        String string = accessToken.getString(JSONOBJECT_KEY_WEIXIN_ACCESS_TOKEN);
        //log.info(string);
        log.info("查询结果：{}", string);
        Map<String, Object> parameterMap = new HashMap<>(2);
        //parameterMap.put("access_token", string);
        parameterMap.put("begin_date", "20200624");
        parameterMap.put("end_date", "20200630");
        String url = "https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait?" +
                "access_token=" + string;
        log.info("url:{}", url);
        String resultAccessToken = null;
        try {
            resultAccessToken = myHttpClientUtil.postJson(url, parameterMap, null);
        } catch (Exception e) {
            log.error("获取微信token请求失败，失败原因：{}", e.toString());
            throw new MyDefinitionException("获取微信token请求失败");
        }
        log.info("查询结果：{}", resultAccessToken);

    }
}