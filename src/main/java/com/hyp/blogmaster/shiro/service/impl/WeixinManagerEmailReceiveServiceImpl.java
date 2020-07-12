package com.hyp.blogmaster.shiro.service.impl;

import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.shiro.mapper.WeixinManagerEmailReceiveMapper;
import com.hyp.blogmaster.shiro.pojo.modal.WeixinManagerEmailReceive;
import com.hyp.blogmaster.shiro.service.WeixinManagerEmailReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/12 15:26
 * @Description: TODO
 */
@Slf4j
@Service("weixinManagerEmailReceiveService")
public class WeixinManagerEmailReceiveServiceImpl implements WeixinManagerEmailReceiveService {


    @Autowired
    private WeixinManagerEmailReceiveMapper weixinManagerEmailReceiveMapper;

    /**
     * 保存从邮件服务商拉取的邮件信息
     *
     * @param weixinManagerEmailReceive
     * @return 主键
     * @throws MyDefinitionException
     */
    @Override
    public Integer saveWeixinManagerEmailReceiveReturnPK(WeixinManagerEmailReceive weixinManagerEmailReceive) throws MyDefinitionException {

        if (weixinManagerEmailReceive == null) {
            throw new MyDefinitionException("保存从邮件服务商拉取的邮件信息参数不能为空");
        }

        Integer pk = null;

        try {
            int i = weixinManagerEmailReceiveMapper.insertUseGeneratedKeys(weixinManagerEmailReceive);
            if (i > 0) {
                pk = weixinManagerEmailReceive.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存从邮件服务商拉取的邮件信息操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("保存从邮件服务商拉取的邮件信息操作过程错误");
        }
        return pk;
    }

    /**
     * 通过通用参数进行查询
     *
     * @param weixinManagerEmailReceive
     * @return
     * @throws MyDefinitionException
     */
    @Override
    public List<WeixinManagerEmailReceive> getWeixinManagerEmailReceiveByParameter(WeixinManagerEmailReceive weixinManagerEmailReceive) throws MyDefinitionException {

        if (weixinManagerEmailReceive == null) {
            throw new MyDefinitionException("通过通用参数进行查询参数不能为空");
        }


        Example example = new Example(WeixinManagerEmailReceive.class);
        Example.Criteria criteria = example.createCriteria();

        /*接收email的id*/
        String receiveEmailId = weixinManagerEmailReceive.getReceiveEmailId();
        if (StringUtil.isNotEmpty(receiveEmailId)) {
            criteria.andLike("receiveEmailId", receiveEmailId);
        }


        List<WeixinManagerEmailReceive> weixinManagerEmailReceiveList = null;
        try {
            weixinManagerEmailReceiveList = weixinManagerEmailReceiveMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("通过通用参数进行查询操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("通过通用参数进行查询操作过程错误");
        }
        return weixinManagerEmailReceiveList;
    }

    /**
     * 按照主键对有值的数据进行更新
     *
     * @param weixinManagerEmailReceive
     * @return 影响行数
     * @throws MyDefinitionException
     */
    @Override
    public Integer updateSelectiveWeixinManagerEmailReceiveByPK(WeixinManagerEmailReceive weixinManagerEmailReceive) throws MyDefinitionException {
        if (weixinManagerEmailReceive == null) {
            throw new MyDefinitionException("按照主键对有值的数据进行更新参数不能为空");
        }

        int i = 0;
        try {
            i = weixinManagerEmailReceiveMapper.updateByPrimaryKeySelective(weixinManagerEmailReceive);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("按照主键对有值的数据进行更新操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("按照主键对有值的数据进行更新操作过程错误");
        }

        return i;
    }

    /**
     * 按照主键进行删除
     *
     * @param pk
     * @return 影响行数
     * @throws MyDefinitionException
     */
    @Override
    public Integer deleteWeixinManagerEmailReceivePK(Integer pk) throws MyDefinitionException {

        if (pk == null) {
            throw new MyDefinitionException("按照主键进行删除参数不能为空");
        }

        int i = 0;
        try {
            i = weixinManagerEmailReceiveMapper.deleteByPrimaryKey(pk);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("按照主键进行删除操作过程错误，错误原因：{}", e.toString());
            throw new MyDefinitionException("按照主键进行删除操作过程错误");
        }
        return i;
    }
}
