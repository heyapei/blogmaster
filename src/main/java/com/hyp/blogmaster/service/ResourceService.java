package com.hyp.blogmaster.service;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.vo.result.MyResultVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/11 12:16
 * @Description: TODO
 */
public interface ResourceService {

    /**
     *
     * @param file
     * @return
     * @throws MyDefinitionException
     */
    MyResultVO saveEmailRes(MultipartFile file) throws MyDefinitionException;
}
