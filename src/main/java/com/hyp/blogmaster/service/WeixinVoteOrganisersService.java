package com.hyp.blogmaster.service;


import com.hyp.blogmaster.exception.MyDefinitionException;
import com.hyp.blogmaster.pojo.modal.WeixinVoteOrganisers;

import java.util.List;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/1 18:59
 * @Description: TODO
 */
public interface WeixinVoteOrganisersService {

    /*自定义比较多写在上面*/
    /*自定义实体操作*/

    /**
     * 按照公司名称模糊查询
     * @param name 公司名称
     * @return 公司集合
     * @throws MyDefinitionException
     */
    List<WeixinVoteOrganisers> getWeixinVoteOrganisersByLikeName(String name) throws MyDefinitionException;


    /*表格实体操作*/

    /**
     * 保存公司信息 请填写完整的数据 需要返回一个主键
     *
     * @param weixinVoteOrganisers 完成的数据
     * @return 主键
     */
    Integer saveWeixinVoteOrganisers(WeixinVoteOrganisers weixinVoteOrganisers);

    /**
     * 通过主键查询公司信息
     *
     * @param id 主键
     * @return 公司信息
     */
    WeixinVoteOrganisers getWeixinVoteOrganisersByID(Integer id);

    /**
     * 通过活动查询当前活动的公司信息
     *
     * @param baseWorkId 活动ID
     * @return 公司信息
     */
    WeixinVoteOrganisers getWeixinVoteConfByVoteWorkId(Integer baseWorkId);

    /**
     * 更新公司信息 只更新传过来的实体中有值的内容数据 按照主键
     *
     * @param weixinVoteOrganisers 需要更新的实体类
     * @return 受影响的行数
     */
    Integer updateSelectiveWeixinVoteOrganisers(WeixinVoteOrganisers weixinVoteOrganisers);

    /**
     * 保存活动公司信息 保存有值的数据
     *
     * @param weixinVoteOrganisers 实体类
     * @return 影响的行数
     */
    Integer saveSelectiveWeixinVoteOrganisers(WeixinVoteOrganisers weixinVoteOrganisers);
}
