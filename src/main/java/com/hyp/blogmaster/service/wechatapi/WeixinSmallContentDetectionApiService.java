package com.hyp.blogmaster.service.wechatapi;

import com.alibaba.fastjson.JSONObject;
import com.hyp.blogmaster.exception.MyDefinitionException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/25 19:51
 * @Description: TODO
 */
public interface WeixinSmallContentDetectionApiService {

    /**
     * 违规图片检测
     *
     * @param multipartFile 图片
     * @param accessToken   微信token 填写null系统会自动请求
     * @return boolean值
     * @throws MyDefinitionException
     */
    Boolean checkImgSecCheckApi(MultipartFile multipartFile, String accessToken) throws MyDefinitionException;

    /**
     * 违规图片检测
     *
     * @param multipartFile 图片
     * @param accessToken   微信token 填写null系统会自动请求
     * @return
     * @throws MyDefinitionException
     */
    JSONObject getImgSecCheckApiMsg(MultipartFile multipartFile, String accessToken) throws MyDefinitionException;


    /**
     * 违规文字检测
     *
     * @param msgText     文字
     * @param accessToken 微信token 填写null系统会自动请求
     * @return boolean值
     * @throws MyDefinitionException
     */
    Boolean checkMsgSecCheckApi(String msgText, String accessToken) throws MyDefinitionException;


    /**
     * 违规文字检测
     *
     * @param msgText     文字
     * @param accessToken 微信token 填写null系统会自动请求
     * @return
     * @throws MyDefinitionException
     */
    JSONObject getMsgSecCheckApiMsg(String msgText, String accessToken) throws MyDefinitionException;


    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     * 调用绝大多数后台接口时都需使用 access_token，开发者需要进行妥善保存
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     *
     * @return
     * @throws MyDefinitionException
     */
    JSONObject getAccessToken() throws MyDefinitionException;

}
